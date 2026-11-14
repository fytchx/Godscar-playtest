# Fight density pass 1 — pending visual review
Status: **PROPOSED / IMPLEMENTED ON REVIEW BRANCH**, not yet owner-approved, merged, or deployed.
Base: `ui/premium-live-integration`. Source: user-supplied Android Fight screenshot, November 14, 2026.

## Problem
The premium visual pass accidentally expanded decorative chrome: a second 73px title mast, a 94px player header, a large zone card, 31px+ cast rows with substantial gaps, wide arena margins, and a tall navigation bar. On phone the enemy barely enters the first screen.

## Implemented in `assets/ui/godscar-premium-live.css`
- Hide the redundant "Godscar · Home" mast (Paladin identity remains in player header).
- Reduce player header to ~58px, HP bar/section spacing, and zone picker to ~47px.
- Keep full selected zone name in underlying DOM and picker, but ellipsize the narrow single-line bar.
- Tighten five on-arena cast rows to ~22px each; do not remove skill names, types, or bars.
- Reduce status chips, enemy card, DPS tile, arena frame, and surrounding margins.
- Keep the combat arena near-square but still slightly portrait (10:10.8).
- Keep all five bottom-nav touch targets at least 46px tall.
- Preserve antique gold textured frame visual direction and all Fight/Character behavior.
- Add narrow-phone adjustments at <=360px; no change to mock-data policy.

## Acceptance checks still needed
- Actual Android / phone-width browser visual test at 320, 360, 390, 412 and 480 CSS px.
- No unwanted overflow, clipped zone interaction, illegible cast names, occluded enemy art, or overlapping chips.
- Confirm zone popup, Character screen, launch recap, feed, settings, tap targets and fixed nav still work.
- Compare first fold before/after; target is a visible enemy and its HP within first fold on a typical tall mobile viewport.
- This CSS-only branch has NOT been deployed, so no live browser claim is made.

## Boundaries
No simulation imported, no bot tests, no gameplay or item-system changes, no art replacements, no change to current deployed preview.
