#!/usr/bin/env python3
"""Validate catalog and SVG-only assets (no images, text or unsafe markup)."""
from pathlib import Path
from xml.etree import ElementTree as ET
import json

ROOT=Path(__file__).resolve().parents[1]
BASE=ROOT/"assets/ui/gilded-chrome/v1"
expected=(29,35,36,56)
paths=set()
for batch,count in enumerate(expected,1):
    cat=BASE/"catalog"/f"batch-{batch:02d}.json"
    data=json.loads(cat.read_text(encoding="utf-8"))
    items=data["files"]
    assert len(items)==count,(batch,len(items),count)
    for item in items:
        filename=ROOT/item["path"]
        assert filename.is_file(),str(filename)
        assert filename.suffix==".svg",str(filename)
        assert str(filename) not in paths,("duplicate path",filename)
        paths.add(str(filename))
        source=filename.read_text(encoding="utf-8")
        assert source.startswith("<svg"),item["path"]
        tree=ET.fromstring(source)
        assert tree.tag.endswith("svg"),item["path"]
        assert "viewBox" in tree.attrib,item["path"]
        assert "aria-label" in source,item["path"]
        disallowed={"image","text","script","foreignObject","animate","animateMotion","animateTransform"}
        for element in tree.iter():
            assert element.tag.split("}")[-1] not in disallowed,(item["path"],element.tag)
        assert item["id"] and item["use"],item
    print(f"PASS batch {batch}/4: {count} SVG assets; XML and paths valid")
assert len(paths)==156,len(paths)
actual={str(p) for p in BASE.glob("batch-*/*.svg")}
assert actual==paths,("catalog missing",actual-paths,"not on disk",paths-actual)
print("PASS total: 156 individual cataloged SVG sources")
