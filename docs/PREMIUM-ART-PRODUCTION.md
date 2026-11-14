# Godscar GS-UI-2.0 — Premium Hybrid Production Handoff

**Date:** 2026-11-14  
**Repo:** `fytchx/Godscar-playtest`  
**Review branch:** `art/premium-hybrid-ui-production`  
**State:** ACTIVE REVIEW. Do not merge, deploy or connect simulation without separate explicit owner approval.

## Reference and art bar

Primary visual target: the owner-provided portrait Fight reference screenshot from the chat of Nov 14, 2026 (dark blue-green/charcoal surfaces, refined antique-gold frames with corners and beveled edges, deep subtle textures, thin elegant text, strong readability). This reference **is not approved gameplay data**. Use the existing approved Fight/Character skeleton for layout and interactions, not names from the reference screenshot.

Quality benchmark: earlier `GS-CLS-SPEC-001` Avenger independently rendered **gold/white radiant hammer**, with transparent artwork composited beneath the existing Concept 2 gold medallion SVG. Never reproduce illustrated icon art as a few simplistic SVG strokes. Use proper transparent PNG/WebP for art and true SVG/CSS for scalable geometric chrome.

### Confirmed names and motifs

| ID | Class | Specialization | Art subject | Current production status |
| --- | --- | --- | --- | --- |
| GS-CLS-SPEC-001 | Paladin | Avenger | Radiant holy hammer, gold and white | HIGH-RES PROTOTYPE, awaiting user acceptance for family-wide style |
| GS-CLS-SPEC-002 | Paladin | Templar | Embossed shield with red cross | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-003 | Paladin | Justicar | Holy radiant scales | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-004 | Warrior | Protection | Heavy decorated defensive shield | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-005 | Warrior | Savage | Two bloody swords | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-006 | Warrior | Fury | Massive two-handed mace | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-007 | Rogue | Subtlety | Dark hood | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-008 | Rogue | Assassin | Two bloody daggers | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-009 | Rogue | Alchemist | Bright-green toxic flask | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-010 | Mage | Pyro | Vivid red/orange/gold fire | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-011 | Mage | Frost | Icy blue-white crystal | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-012 | Mage | Lightning | Radiant yellow lightning | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-013 | Archer | Marksman | Precise arrow/target | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-014 | Archer | Ranger | Bow/forest-green details | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-015 | Archer | Tracker | Distinct hunt/tracking mark | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-016 | Shaman | Nature | Green leaf/root energy | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-017 | Shaman | Storm | Electric blue storm/lightning | CONCEPT-SHEET CROP ONLY |
| GS-CLS-SPEC-018 | Shaman | Primal | Feral dual-wield with elemental energy | CONCEPT-SHEET CROP ONLY |

All 18 names are owner-confirmed. This **does not approve abilities, mechanical values or skill effects**.

## Completed as review source

- **12 independently editable scalable chrome SVGs** checked into `assets/ui/premium-chrome/v2/`, with IDs corresponding to panel, button, nav, slot, enemy HP frame, ornamental divider/corner/crest. Sources are new and do not replace old SVG library.
- Separate offline working package produced in the conversation: `Godscar_Premium_UI_Art_Working_Set.zip`. It includes source CSS, standalone mock Fight/Character HTML, a local gallery, 18 concept-sheet-derived icon extractions (except Avenger independent art), and screenshot checks. This ZIP is NOT itself uploaded to GitHub.
- Chromium tested the isolated mock page at width 360/390/412/480 via `page.set_content` with inline assets: no overflow, no JS errors, basic enemy selection and 11-slot Character mock interactions passed. **This is not a test of the actual repo root app.**
- The standalone Fallfields backdrop is a procedural art-direction **study**, not an approved environment asset.

## Outstanding work, explicitly NOT complete

1. Six separate high-resolution parent **class** emblem masters using the Avenger level of craftsmanship.
2. Seventeen independent high-resolution specialization illustration masters, each transparent and true to the individual detailed concept. Do not upscale crops and call them high-res.
3. Remaining interface elements and all visual review refinements to reach screenshot depth, texture and icon richness.
4. Copy reviewed CSS/art assets into this branch, update existing Asset Vault gallery inventory, and apply them to the **actual** approved mock Fight/Character HTML on an isolated integration branch.
5. Real phone hardware QA (Samsung A15 4GB), load/performance checks, and owner acceptance.
6. Never deploy or merge automatically.

## Original rules remain

- One to four enemies in a single hunt rotation, at least one selected.
- Up to **five total** weapon-owned automatic abilities, not the six illustrated bars in the screenshot.
- Fully automatic combat, no hero model in Fight.
- Character 11 equipment slots; Skills and other unapproved screens remain placeholders.
- Any mock UI must remain disconnected from `simulation/reference-sim-v8.js`.
