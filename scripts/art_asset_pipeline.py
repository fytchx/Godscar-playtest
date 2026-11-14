#!/usr/bin/env python3
"""Godscar individual-art QA and export tool. Requires Pillow.

Use ONLY on independently rendered or artist-painted native masters.
NEVER use this script to upsample posters, atlases, crops or legacy previews.
Does not approve an asset or touch the game UI.
"""
import argparse
import hashlib
import json
import shutil
import sys
from pathlib import Path

try:
    from PIL import Image, ImageOps
except ImportError:
    sys.exit("Pillow is required: python -m pip install Pillow")

ROOT = Path(__file__).resolve().parents[1]
INDEX = ROOT / "assets/art-production/catalogue.json"
SIZES = (512, 256, 128, 64, 48, 36)
ALLOWED_SOURCE = ("individual_generation", "original_hand_painting")


def read_index():
    return json.loads(INDEX.read_text(encoding="utf-8"))


def safe_target(asset_id, category):
    if not asset_id.replace("-", "").isalnum() or category not in (
        "abilities", "specializations", "classes", "equipment",
        "materials", "status-effects", "combat-effects", "skill-trees"
    ):
        raise ValueError("Unsafe asset ID or unknown category")
    return ROOT / "assets/art-production" / category / asset_id


def inspect(source, require_alpha=True):
    with Image.open(source) as opened:
        opened.load()
        original_size = opened.size
        if min(original_size) < 1024:
            raise ValueError("Original master must be at least 1024px on BOTH axes")
        im = ImageOps.exif_transpose(opened).convert("RGBA")
    alpha = im.getchannel("A")
    lo, hi = alpha.getextrema()
    if require_alpha and (lo == 255 or hi == 0):
        raise ValueError("Required real transparency is absent, or image is blank")
    mask = alpha.point(lambda a: 255 if a >= 16 else 0)
    bbox = mask.getbbox()
    if not bbox:
        raise ValueError("No visible subject")
    margin = max(12, round(min(im.size) * .02))
    left, top, right, bottom = bbox
    if min(left, top, im.width - right, im.height - bottom) < margin:
        raise ValueError("Subject touches the safe-edge margin, likely clipped")
    return im, {"width": im.width, "height": im.height,
                "alpha_min": lo, "alpha_max": hi,
                "subject_bounds": list(bbox), "safe_margin_px": margin}


def verify_entry(entry):
    master = entry.get("master")
    if not master:
        raise ValueError("Missing verified original master")
    source = ROOT / master
    im, details = inspect(source)
    for export in entry.get("exports", []):
        p = ROOT / export["path"]
        if not p.is_file():
            raise ValueError("Missing export: " + str(p))
        with Image.open(p) as check:
            if check.size != (export["size"], export["size"]):
                raise ValueError("Wrong export dimensions: " + str(p))
            if check.mode not in ("RGBA", "LA", "P"):
                raise ValueError("Export lost alpha: " + str(p))
    return details


def cmd_export(args):
    data = read_index()
    match = [e for e in data["entries"] if e["id"] == args.id]
    if len(match) != 1:
        raise ValueError("ID not found in production inventory")
    if args.source_kind not in ALLOWED_SOURCE:
        raise ValueError("An explicitly original, individually-created source is required")
    if not args.artist_attested:
        raise ValueError("Must attest that this source is NOT from a sheet or crop")
    entry = match[0]
    if entry.get("master"):
        raise ValueError("Existing master protected: review before replacing")
    source = Path(args.source).resolve()
    if not source.is_file():
        raise ValueError("Missing source image")
    im, details = inspect(source)
    dest = safe_target(entry["id"], entry["category"])
    dest.mkdir(parents=True, exist_ok=True)
    master_path = dest / (args.id + "_master.png")
    if master_path.exists():
        raise ValueError("Master destination exists; never silently overwrite")
    im.save(master_path, "PNG", optimize=True)
    exports = []
    for size in SIZES:
        out = dest / (args.id + "_" + str(size) + ".webp")
        icon = im.copy()
        icon.thumbnail((size, size), Image.Resampling.LANCZOS)
        square = Image.new("RGBA", (size, size), (0, 0, 0, 0))
        square.alpha_composite(icon, ((size - icon.width) // 2,
                                      (size - icon.height) // 2))
        square.save(out, "WEBP", quality=89, method=6)
        exports.append({"size": size, "path": out.relative_to(ROOT).as_posix(),
                        "bytes": out.stat().st_size})
    entry.update(master=master_path.relative_to(ROOT).as_posix(),
                 native_resolution=[im.width, im.height],
                 transparency=True, alpha_verified=True,
                 source_kind=args.source_kind,
                 source_sha256=hashlib.sha256(source.read_bytes()).hexdigest(),
                 exports=exports,
                 status="IN_REVIEW",
                 review="UNREVIEWED_AT_36_48_64",
                 quality_metadata=details)
    data["metrics"]["art_master_verified"] = sum(
        bool(e.get("master")) for e in data["entries"])
    data["metrics"]["game_ready_exports_verified"] = sum(
        len(e.get("exports", [])) for e in data["entries"])
    INDEX.write_text(json.dumps(data, indent=2, ensure_ascii=False) + "\n",
                     encoding="utf-8")
    print(json.dumps({"id": args.id, "status": "IN_REVIEW",
                      "master": entry["master"], "exports": exports}, indent=2))
    print("NOT APPROVED: manually review native art and phone sizes.")


def cmd_audit(_args):
    data = read_index()
    issues = []
    masters = 0
    exports = 0
    for e in data["entries"]:
        if not e.get("master"):
            if e.get("status") in ("APPROVED", "IN_REVIEW"):
                issues.append(e["id"] + ": status claims master without a file")
            continue
        try:
            details = verify_entry(e)
            masters += 1
            exports += len(e.get("exports", []))
            if not e.get("source_sha256") or not e.get("source_kind"):
                issues.append(e["id"] + ": missing provenance")
        except Exception as error:
            issues.append(e["id"] + ": " + str(error))
    print(json.dumps({"indexed": len(data["entries"]),
                      "masters_verified": masters,
                      "exports_verified": exports,
                      "issues": issues}, indent=2))
    if issues:
        raise SystemExit(1)


def cmd_gallery(_args):
    data = read_index()
    gallery = ROOT / "review/art-production/index.html"
    page = gallery.read_text(encoding="utf-8")
    begin = page.index("const entries=")
    end = page.index(";\nconst rawBase=", begin) + 1
    keys = ("id", "name", "category", "kind", "class_name", "family",
            "archetype", "slot", "mechanic", "status", "approval", "preview",
            "master", "alternate", "batch", "source_id", "source_ref",
            "native_resolution", "alpha_verified")
    minimal = [{key: e[key] for key in keys if key in e}
               for e in data["entries"]]
    page = (page[:begin] + "const entries=" +
            json.dumps(minimal, ensure_ascii=False, separators=(",", ":")) +
            ";" + page[end:])
    gallery.write_text(page, encoding="utf-8")
    print("Rebuilt gallery inventory:", len(minimal), "entries")


def main():
    p = argparse.ArgumentParser()
    subs = p.add_subparsers(dest="command", required=True)
    exp = subs.add_parser("export", help="Import one native, independently-created master")
    exp.add_argument("--id", required=True)
    exp.add_argument("--source", required=True)
    exp.add_argument("--source-kind", choices=ALLOWED_SOURCE, required=True)
    exp.add_argument("--artist-attested", action="store_true",
                     help="Confirm: source is an independent original, NOT a crop")
    exp.set_defaults(fn=cmd_export)
    audit = subs.add_parser("audit", help="Check file existence, alpha and export sizes")
    audit.set_defaults(fn=cmd_audit)
    build = subs.add_parser("build-gallery", help="Sync gallery with updated JSON inventory")
    build.set_defaults(fn=cmd_gallery)
    args = p.parse_args()
    try:
        args.fn(args)
    except (OSError, ValueError) as err:
        sys.exit("Art QA rejected: " + str(err))


if __name__ == "__main__":
    main()
