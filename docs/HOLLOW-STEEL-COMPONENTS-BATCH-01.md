# Hollow Steel component batch 01

**Status:** Review / proposed, not approved production. Approved palette: docs/UI-PALETTE-HOLLOW-STEEL.md.

Standalone reusable SVG assets: 12 component frame/state files plus one subtle seamless vector texture tile. IDs preserved from existing manifest: GS-UI-CMP-001, 002, 003, 008, 009 and 010. See SVG directory under assets/ui/hollow-steel/batch-01/svg/.

These are actual vector sources, not a screenshot. Panels, buttons, tabs and navigation use CSS border-image nine-slice framing with transparent centers and CSS-owned fills. Resize a panel or button without distorting its corners. The grain is a 64px repeatable SVG tile, not scaled with the panel. Equipment wells are proportional squares that can scale from 48px to 84px and beyond. Text, utility glyphs, rarity colors and handlers are separate HTML/CSS layers, not baked into an SVG.

Preview: review/hollow-steel-batch-01.html. Supporting stylesheet: assets/ui/hollow-steel/batch-01/components.css. Preview is self-contained within the repository and has no external requests. Use a local HTTP server or open the preview HTML directly.

Supporting line colors, elevation, grain intensity and state geometry are proposed, not yet owner-approved. The five approved Hollow Steel values are #11191D, #203039, #344750, #79BFB3 and #E9ECE5. Preserve damage-source and item-rarity colors independently.

The current Fight/Character DOM, simulation wiring, existing SVGs and default styles are untouched. Manifest status remains AUDIT until approved. No merge/deploy.

## Application in current UI (owner-approved integration)

The owner approved applying Batch 01 on the 2026-11-13 design pass. The existing Fight/Character mock now loads `assets/ui/hollow-steel-integration.css` **after** `arcana-reskin.css`, and its 12 utility icons point to `assets/ui/hollow-steel/icons/`. The integration uses the same batch SVG nine-slice panel/button/tab/navigation edge assets and equipment-well vector files. No layout rewrite, no sim binding, no data migration, and no deployment. It remains a review branch until explicitly merged. The 99-record manifest retains its original status semantics.

Mobile verification: `scripts/smoke_ui.mjs` checks 360/390/412/480px, interface interactions, 11 equipment slots, SVG resource load and that the integration styles and actual SVG borders are used. Report test results only after GitHub CI runs.
