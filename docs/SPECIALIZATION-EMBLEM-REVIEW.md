# Godscar — 18 specialization emblem concept review

**Status:** Earlier flat SVG icon drafts were reviewed and rejected as visually below concept-sheet quality. Only **GS-CLS-SPEC-001 Avenger** has received a new hybrid art production prototype, still pending owner acceptance. The shared medallion and base class palette are inherited from the six checked-in Concept 2 class icons (`assets/classes/concept-02/svg/` in this gallery branch).

## Confirmed names

The owner confirmed **Marksman / Ranger / Tracker** (Archer) and **Nature / Storm / Primal** (Shaman) as final names for this pass. The other four groups follow existing class direction. Do not revert these to tentative without a new owner decision.

## Matching visual rules

- Each specialization keeps the approved 128 × 128 medallion structure and subtle antique-gold frame, small cardinal ornaments and dark radial inset.
- Parent class color stays consistent across all three child specializations; avoid arbitrary new colors.
- **Revised owner direction:** Do NOT recreate detailed concepts as simplistic flat SVG symbols. Use the original Concept 2 medallion geometry as a reusable SVG frame, and high-quality, separately exported transparent illustration for expressive specialization artwork. Layer them in the game or use properly sized raster composites. SVG remains appropriate for UI chrome and simple navigation symbols.
- Review concepts at 128px and 32–40px before approving. Concepts are not production assets.
- No gameplay, Forge, simulation, or UI wiring is authorized by this concept pass.

## Review concept IDs

| ID | Class | Specialization | Motif | Status |
| --- | --- | --- | --- | --- |
| GS-CLS-SPEC-001 | Paladin | Avenger | Radiant gold-white holy hammer | HYBRID PROTOTYPE / OWNER VISUAL REVIEW |
| GS-CLS-SPEC-002 | Paladin | Templar | Holy cross shield | CONCEPT / REVIEW |
| GS-CLS-SPEC-003 | Paladin | Justicar | Justice scales | CONCEPT / REVIEW |
| GS-CLS-SPEC-004 | Warrior | Protection | Strong decorated defensive shield | CONCEPT / REVIEW |
| GS-CLS-SPEC-005 | Warrior | Savage | Two bloody swords | CONCEPT / REVIEW |
| GS-CLS-SPEC-006 | Warrior | Fury | Heavy two-handed mace | CONCEPT / REVIEW |
| GS-CLS-SPEC-007 | Rogue | Subtlety | Dark hood | CONCEPT / REVIEW |
| GS-CLS-SPEC-008 | Rogue | Assassin | Two bloody daggers | CONCEPT / REVIEW |
| GS-CLS-SPEC-009 | Rogue | Alchemist | Bright-green poison flask | CONCEPT / REVIEW |
| GS-CLS-SPEC-010 | Mage | Pyro | Vivid red/orange flame | CONCEPT / REVIEW |
| GS-CLS-SPEC-011 | Mage | Frost | Icy blue and white crystalline snowflake | CONCEPT / REVIEW |
| GS-CLS-SPEC-012 | Mage | Lightning | Radiant yellow lightning | CONCEPT / REVIEW |
| GS-CLS-SPEC-013 | Archer | Marksman | Arrow bullseye | CONCEPT / REVIEW |
| GS-CLS-SPEC-014 | Archer | Ranger | Wild bow and leaf | CONCEPT / REVIEW |
| GS-CLS-SPEC-015 | Archer | Tracker | Tracking eye | CONCEPT / REVIEW |
| GS-CLS-SPEC-016 | Shaman | Nature | Rooted sprout | CONCEPT / REVIEW |
| GS-CLS-SPEC-017 | Shaman | Storm | Storm arc | CONCEPT / REVIEW |
| GS-CLS-SPEC-018 | Shaman | Primal | Dual axes and spirit knot | CONCEPT / REVIEW |

## Draft artifact boundary

The initial review sheet, offline HTML and 18 draft SVGs were generated as conversation attachments, **not yet committed to this branch or deployed**. After art review, commit the approved individual icons to an asset branch, update the asset manifest with stable IDs, and include them in the gallery. Keep the approved class emblems unchanged.

## Approved workflow / Avenger prototype handoff (2026-11-14)

The owner authorized **one** Avenger production prototype using the **original gold medallion as reusable SVG** plus **high-quality transparent illustrated hammer**. The prototype was generated and packaged as a downloadable conversation attachment named `Godscar_Avenger_Production_Prototype.zip`, **not committed as binary image files to GitHub**. This doc tracks the direction; a reviewed upload/commit of the binary sources and exports remains a separate step.

- Preserve independently editable `shared-specialization-medallion.svg` and foreground rim SVG using Concept 2 frame geometry.
- Keep unmodified transparent source art and cropped transparent illustration separate from raster composites.
- Export PNG + WebP at 256, 128, 64, and 40px, plus 1024px master composite.
- Check against the colorful 18-specialization concept sheet and at real phone dimensions; 390px Character placement is a **static proposed placement mock**, not the current game or an approved feature.
- Only Avenger was produced in this revised process. Do not expand to other 17 until this quality benchmark is reviewed.
- All existing gameplay/UI branches, simulation sources, Railway services and main remain unchanged.
