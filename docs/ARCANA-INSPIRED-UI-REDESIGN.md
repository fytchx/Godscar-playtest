# Godscar UI: Arcana-inspired redesign (review proposal)

**Status:** PROPOSED / NOT YET APPROVED. The approved baseline remains on main.
**Branch:** ui/arcana-inspired-reskin.
**Scope:** Reusable interface visual language across the existing mock Fight and Character screens. No simulation, no gameplay, no deployment.

## Source of inspiration
- fytchx/Arcana@main / next/app.css: slate surfaces, semantic tokens, legible cards, 44px controls, compact bottom navigation.
- fytchx/Arcana@main / styles.css: coherent panel hierarchy and focus/active states.
- fytchx/Arcana@main / src/theme.js: palette tokens and separation of theme colors from game data (architecture pattern only).
- fytchx/Arcana@main / src/arcana-icons.js, blob 081ed5df12a57bd78789c0adcc93ee6660bfd2fe: original utility glyphs.

No Arcana backend, game data, official IdleArc imagery, fonts distributed as files, login, or theme-service calls were copied.

## Review implementation
- assets/ui/arcana-reskin.css adds an override layer **after** the existing GS-UI-1.0 and Character stylesheets; it does not delete either.
- index.html loads that layer and optional Google Fonts Inter. All existing handlers and mock-only gameplay values remain intact.
- assets/ui/arcana-preview-icons/*.svg: 10 adapted Arcana minimalist utility glyphs with a cohesive cool accent; the existing Godscar activity and forge glyphs were recolored for the review kit. Original assets/ui/icons files are untouched.
- The same controls now have Arcana-like soft cards, restrained borders, rounded modal surfaces, quieter button treatments, compact navigation, legible Character slots/stats, and a raised zone-selection row.
- Damage and rarity color semantics remain independent of the interface palette.
- No files were copied wholesale from Arcana's giant application stylesheet. This is a focused adaptation, not an attempt to run two apps together.

## Asset status and IDs
The original 99-record docs/ASSET-MANIFEST.md remains authoritative. This branch is an **exploratory variant** for existing UI IDs, especially GS-UI-CMP-001 through GS-UI-CMP-017 and GS-UI-ICO-001 through GS-UI-ICO-012. Do not mark these newly exported or APPROVED merely because the stylesheet and SVG samples exist.

## Review checklist
1. Visually compare Fight, Character Equipment, Character Inventory, Zone picker, Recap, Settings at 360, 390, 412, and 480 CSS px.
2. Verify the combat square remains the focal point, cast rows remain legible, dialogs can close, and 11 equipment slots fit.
3. Check icon loading, tap areas, contrast, overflow, reduced motion, and source-color differentiation.
4. Approve or reject the overall direction before treating individual pieces as final reusable exports.

## Test and deployment boundary
The branch adapts the existing browser smoke script and GitHub workflow to the new rounded component treatment, including a check that the redesign stylesheet loads. Results must be reported from an actual run; a committed test is not proof it passed. No Railway service was changed. The existing review URL may still deploy ui/fight-skeleton, not this branch.

To revert the look without disturbing the mock skeleton, remove the arcana-reskin.css link, switch the preview icon references back to assets/ui/icons, and restore the old browser meta color. Keep approvals in docs before merging to main.
