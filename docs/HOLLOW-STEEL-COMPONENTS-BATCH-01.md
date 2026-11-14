# Hollow Steel component batch 01

**Status:** Review / proposed, not approved production. Approved palette: docs/UI-PALETTE-HOLLOW-STEEL.md.

Standalone reusable SVG assets: 12 component frame/state files plus one subtle seamless vector texture tile. IDs preserved from existing manifest: GS-UI-CMP-001, 002, 003, 008, 009 and 010. See SVG directory under assets/ui/hollow-steel/batch-01/svg/.

These are actual vector sources, not a screenshot. Panels, buttons, tabs and navigation use CSS border-image nine-slice framing with transparent centers and CSS-owned fills. Resize a panel or button without distorting its corners. The grain is a 64px repeatable SVG tile, not scaled with the panel. Equipment wells are proportional squares that can scale from 48px to 84px and beyond. Text, utility glyphs, rarity colors and handlers are separate HTML/CSS layers, not baked into an SVG.

Preview: review/hollow-steel-batch-01.html. Supporting stylesheet: assets/ui/hollow-steel/batch-01/components.css. Preview is self-contained within the repository and has no external requests. Use a local HTTP server or open the preview HTML directly.

Supporting line colors, elevation, grain intensity and state geometry are proposed, not yet owner-approved. The five approved Hollow Steel values are #11191D, #203039, #344750, #79BFB3 and #E9ECE5. Preserve damage-source and item-rarity colors independently.

The current Fight/Character DOM, simulation wiring, existing SVGs and default styles are untouched. Manifest status remains AUDIT until approved. No merge/deploy.
