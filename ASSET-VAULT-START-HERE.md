# GODSCAR — MASTER ASSET VAULT

**Single GitHub branch for the existing game UI and class-art sources.** Created 2026-11-14.

## Start here

- [Repository asset index](./asset-vault/REPOSITORY-ASSET-INDEX.json): machine-readable listing of **273** current files under `assets/`, each with path, SHA and file size.
- [Current asset folders](./assets): premium Fight/Character UI, full 18-specialization review artwork, class emblem SVGs, legacy/gilded/hollow-steel SVG libraries.
- [Class artwork](./assets/classes): six class emblems and 18 premium specialization review illustrations; plus earlier class-symbol variants.
- [UI artwork](./assets/ui): 12 utility icons, premium chrome, gilded/hollow-steel variant libraries and appearance CSS.
- [Existing complete art archives](./actualart1.zip), [part 2](./actualart2.zip), [part 3](./actualart3.zip): previously committed ZIP assets (not automatically assumed to be production-ready).
- [Gallery index/data](./gallery/asset-index.json), [gallery scripts](./gallery): review tools included from the earlier art gallery branch.

## Sources consolidated

Branch derived from `ui/premium-live-integration` (latest preview UI content at consolidation) and copied **219 missing original art/gallery files by existing Git blob SHA** from `art/godscar-asset-gallery`. No redrawing, conversions, original artwork replacement, or loss of source precision occurred. Overlapping paths retain the premium UI branch version.

## Chat-generated artwork is a separate archive until uploaded

Other work **does not yet reside as GitHub binaries on this branch**:

- `Godscar_Art_Library_v2_FIXED_FULL.zip`: 490 individually exported *concept candidates* plus native crops and original atlases; 253 repaired crop boundaries; only 128 transparent-atlas source candidates.
- `Godscar_HQ_Art_Batch_01.zip`: one independently rendered, 1254×1254 transparent Avenger sunhammer and size-optimized exports.
- Newer composite poster images: visual references only, **not individual finished assets**.
- The erroneous v1 cropped library: archived as superseded evidence, not recommended for integration.

A **single downloadable consolidated master package** containing those chat-generated files is provided in the ChatGPT conversation and can be stored in the ChatGPT Library; it is intentionally **not claimed to exist in GitHub** until uploaded through a suitable binary-capable path. Do not replace existing GitHub premium art with lower-quality poster crops.

## Quality definitions

- **CURRENT REPO ART:** existing files on this branch; code/SVG sources are editable, other renderings may be review-sized.
- **INDEPENDENT HQ CANDIDATE:** high-resolution artwork generated individually; requires review before approval.
- **TRANSPARENT ATLAS CANDIDATE:** isolated transparent icon from sprite atlas; requires motif, halo and phone-size checks.
- **POSTER-DERIVED CONCEPT:** visual study cropped from a composite poster; generally low-res and not an independent transparent master.
- **HISTORICAL / REJECTED:** broken v1 crops or overview posters; not suitable as production assets.

## Production workflow

Future final masters should be committed to a dedicated `assets/production/` path with stable ID, actual pixel size and alpha QA, plus individual optimized exports. Update this index and `docs/ASSET-MANIFEST.md` carefully. Preserve all existing IDs and do not promote proposal names/mechanics into established gameplay.

This consolidation creates **a library branch, not a deployment or gameplay integration**. The main branch, simulation and Railway service remain unchanged.