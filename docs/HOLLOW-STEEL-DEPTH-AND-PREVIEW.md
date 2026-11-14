# Hollow Steel darker depth pass and isolated preview

**Status:** Owner-requested review implementation, not a finalized production asset library.
**Branch:** `ui/hollow-steel-depth-preview`, based on `ui/hollow-steel-components-batch-01`; PR #4 predecessor.

This pass deliberately darkens Godscar's Fight, Character, panels and menus while retaining Hollow Steel's sea-glass interaction color and ivory text. Base approved palette: `docs/UI-PALETTE-HOLLOW-STEEL.md`; darker derived surfaces are proposed refinements. Rarity and elemental colors remain independent.

Three reusable scalable SVG sources:
- `assets/ui/hollow-steel/depth/GS-UI-CMP-001_etched-frame.svg`: refined double-line rim and thin corner brackets for cards/modals, nine-slice.
- `assets/ui/hollow-steel/depth/GS-UI-CMP-001_combat-frame.svg`: combat-only double border, four machined corners, subtle sea-glass pins, nine-slice.
- `assets/ui/hollow-steel/depth/etched-grain.svg`: 96px tile of tiny intentional marks, no raster or expensive SVG filters.

`assets/ui/hollow-steel-depth.css` layers *after* the existing styles; the approved Fight/Character DOM stays intact with fake data. `index.html` references the new stylesheet. `preview-server.mjs` and `package.json` support a static HTTP preview (no sim, no database, no storage, no user account); command `npm start`, listens to `PORT` on all interfaces. The service should follow only this feature branch.

Review at 360,390,412,480 CSS px: menu/readability, no horizontal overflow, existing interactions, 11 slots, new combat 9-slice asset loaded. Do not merge into `main` until review. No modifications to the authoritative simulation. Railway preview URL is intentionally separate from any old Godscar gameplay service.
