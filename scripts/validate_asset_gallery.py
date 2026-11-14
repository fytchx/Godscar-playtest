#!/usr/bin/env python3
"""Verify every gallery entry points at a real independent parseable SVG."""
from pathlib import Path
from xml.etree import ElementTree
import json
root=Path(__file__).resolve().parents[1]
index=json.loads((root/'gallery/asset-index.json').read_text(encoding='utf-8'))
assets=index['assets']
assert index['total']==len(assets)==223,(index['total'],len(assets))
expected={'classes':13,'panels':29,'controls':35,'combat':36,'ornaments':56,'hollow':28,'baseline':14,'archive':12}
assert index['counts']==expected,(index['counts'],expected)
seen=set()
for x in assets:
    p=root/x['path']
    assert x['path'] not in seen,('duplicate',x['path'])
    seen.add(x['path'])
    assert p.is_file(),('missing',p)
    assert p.suffix=='.svg',p
    tree=ElementTree.parse(p)
    assert tree.getroot().tag.endswith('svg'),p
    assert x['id'] and x['title'] and x['collection'] and x['status'],p
assert len(seen)==223
for k,n in expected.items():
    assert sum(x['group']==k for x in assets)==n
    print('PASS',k,n)
print('PASS 223 independent source SVG paths, XML valid')
