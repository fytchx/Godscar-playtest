# Godscar Gilded Chrome v1 · SVG Source Library

Status: 156 independent SVG SOURCES CREATED, awaiting phone-size visual approval. Four separate saved GitHub commits, one batch each. New chrome is dark charcoal/blue-green with restrained antique-gold rims, as requested by owner. Original vector geometry, not cropped images or traced reference art.

Batch 01: 29 menus/panels/inset frames. Batch 02: 35 button/nav/tab controls. Batch 03: 36 combat/status/bar shells. Batch 04: 56 corner/crest/divider/slot/rim/ornament assets. All four per-batch JSON catalogs include ID, source path, intended use and status.

Existing source manifest IDs GS-UI-CMP-* reused where appropriate. New GS-UI-CHR-* and GS-UI-ORN-003+ are fresh chrome-only source IDs. Existing GS-UI-ORN-001/002, Fight/Character mock, and gameplay code unchanged. No actual menu text, icons, items or game art baked into SVGs.

Use border-image nine-slice (slice=34 for 128x128 panels, ~20px border width) to protect resizable corners, keep center ornaments separate at fixed size, and apply material texture by a distinct CSS background layer. Proportional squares and standalone ornaments are regular img or background SVGs. See gilded-chrome.css for optional preview-only composition helpers. Concept labels suggesting mechanics do not approve gameplay mechanics.

Open review/gilded-chrome-gallery.html using a local HTTP server (`npm start` from the repo root). The QA workflow validates XML and catalogs and renders 390px and desktop screenshots. Design still needs visual signoff in the actual phone UI before integration.
