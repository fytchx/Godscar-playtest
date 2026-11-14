# Godscar Asset Vault — dedicated gallery service

**Status:** Review gallery, live visual inspection only. This is not the game's production interface.

## Inventory and source provenance

**223 verified source SVG paths** collected from existing branches (not re-generated or flattened):

| Group | Count | Source |
| --- | ---: | --- |
| Concept 2 class emblems, glyph-only icons, shared medallion | 13 | `art/class-emblems-concept2-svg` |
| Four batches of dark-gold interface chrome | 156 | `ui/gilded-chrome-svg-library` |
| Earlier Hollow Steel controls, etched frames and utility icons | 28 | `ui/hollow-steel-depth-preview` |
| Original approved Fight/Character utility icons and ornaments | 14 | `main`, inherited in UI review branch |
| Older Arcana-inspired icons | 12 | `ui/hollow-steel-depth-preview`, **archive/not selected** |
| **Total** | **223** | |

The index is `gallery/asset-index.json`, with a browser-friendly mirror `gallery/asset-index.js`. Every record links to a physically present independent SVG file in this branch, and carries path, original ID, use, source collection, type and status. The SVG source is unchanged from its origin branches. Class emblems remain review art. Gilded UI frames remain review sources until actual phone-size integration. Original icons remain existing assets.

## User experience

`index.html` is the gallery landing page, with grouped filters, search, status labels, thumbnail size switch, individual SVG details, resizable-border demo, preview-size selection (24–240px), checkerboard/dark/light backgrounds, open, save and copy-path controls. Default group is class emblems. Full inventory shows 48 tiles at a time to limit memory use on low-end phones.

`fight-preview.html` preserves the current mock Fight HTML on **this gallery branch**. Its network-free simulation boundary is unchanged.

## Deployment boundary

Use a **separate Railway service** following `art/godscar-asset-gallery`, preferably in the already-isolated **Godscar UI Review** project, not the existing UI preview service, not the playable project. `npm start` runs the existing file-only Node static server. The gallery requires no database, storage, authentication, simulator or account. Do not merge into `main` or change any existing Railway sources without the owner's separate merge/deploy approval. The user specifically authorized this standalone gallery deployment.

## Check

Run `node scripts/smoke_asset_gallery.mjs` with Chrome/Puppeteer at a local HTTP server. Validate 223 entries, groups, resource loading, mobile/no-overflow, item detail, preview sizing, and search. Record deployment URL and tests on the PR when verified.
