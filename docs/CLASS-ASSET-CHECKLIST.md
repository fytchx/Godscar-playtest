# Godscar — Class Asset Inventory

**Status: PROPOSED asset-production checklist (no class artwork generated).**
**Source of truth:** `docs/NEW-PROJECT-START-HERE.md`, `docs/ITEM-SYSTEM-DIRECTION.md`, `docs/FIGHT-SCREEN-DIRECTION.md`, and `docs/ASSET-MANIFEST.md` on `main`.
**Art direction:** dark charcoal/blue-black with fine antique-gold UI borders from the owner's most recent UI reference, using reusable SVG chrome (separate from symbols). This does not approve unexplored glyph designs.

## Boundary: class identity versus weapon ability

Classes do not own automatic weapon skills. A main-hand weapon carries 1–3 found and up to two crafted abilities, **maximum five total**; the same weapon grants the same abilities to any class. Classes/specializations alter equipment synergy, tags, defense, passives and effectiveness. Do NOT generate six class spellbooks or class-specific automatic cast icon packs. There is **no player model in Fight**.

## 1. Core unique art: 24 identifiable vectors (P0)

These are new slots in the asset pipeline. Proposed IDs begin `GS-CLS-`; no IDs in the existing 99-record manifest are overwritten or marked complete. Each is an independent 128×128-ish vector source, optimized to display at 24, 36, 48 and 64 CSS px. Color, rarity, locked/selected and frame treatment remain separate CSS/asset layers.

| Class | Class icon (new proposed ID) | Three specialization sigils (new proposed IDs) |
|---|---|---|
| Paladin | `GS-CLS-ICO-001` | Avenger (`GS-CLS-SPEC-001`); Templar (`GS-CLS-SPEC-002`); Justicar (`GS-CLS-SPEC-003`) |
| Warrior | `GS-CLS-ICO-002` | Protection (`GS-CLS-SPEC-004`); Savage (`GS-CLS-SPEC-005`); Fury (`GS-CLS-SPEC-006`) |
| Rogue | `GS-CLS-ICO-003` | Subtlety (`GS-CLS-SPEC-007`); Assassin (`GS-CLS-SPEC-008`); Alchemist (`GS-CLS-SPEC-009`) |
| Mage | `GS-CLS-ICO-004` | Pyro (`GS-CLS-SPEC-010`); Frost (`GS-CLS-SPEC-011`); Lightning (`GS-CLS-SPEC-012`) |
| Archer | `GS-CLS-ICO-005` | Marksman (`GS-CLS-SPEC-013`, **name tentative**); Ranger (`GS-CLS-SPEC-014`, **name tentative**); Tracker (`GS-CLS-SPEC-015`, **name tentative**) |
| Shaman | `GS-CLS-ICO-006` | Nature (`GS-CLS-SPEC-016`); Storm (`GS-CLS-SPEC-017`); Primal (`GS-CLS-SPEC-018`, **name tentative**) |

**Count:** 6 unique class emblem sources + 18 unique specialization sigils = **24 unique symbols**. The existing `GS-UI-ICO-002` is a generic **Character navigation icon**, NOT the Paladin icon or any of these six.

### Visual rules

- Distinct recognizable silhouettes, deliberate negative space, clean at 36px and legible at 24px.
- Use one consistent icon language across all 24, but unique graphic identity for every class and specialization.
- Do not default to literal class gear (e.g. a sword for Warrior) in a way that implies weapon locking; weapon options are flexible in Godscar.
- No thin text, tiny filigree, character faces or baked gradient panel art inside the actual class glyph.
- Export source SVG, optional web-optimized SVG, and separately reusable active/disabled/locked presentation through CSS; no duplicate recolored raster set.

## 2. Reusable UI components (P1 as Skills menu develops)

One shared implementation for all classes. These **10 records are reusable compositions**, not a request for ten distinct class illustrations or a new clone of the UI frame library. Proposed mapping to existing UI IDs:

| New planning ID | Component | Reuse existing manifest chrome |
|---|---|---|
| `GS-CLS-UI-001` | Class selection tile composition | `GS-UI-CMP-001`, `GS-UI-CMP-002` |
| `GS-CLS-UI-002` | Class header insignia housing | `GS-UI-CMP-001` |
| `GS-CLS-UI-003` | Specialization selector row/cards | `GS-UI-CMP-009`, `GS-UI-CMP-001` |
| `GS-CLS-UI-004` | Specialization-tree viewport/panel | `GS-UI-CMP-001`, `GS-UI-CMP-019` |
| `GS-CLS-UI-005` | Talent nodes: ordinary/notable/capstone | `GS-UI-CMP-019` |
| `GS-CLS-UI-006` | Tree connection lines and branch markers | `GS-UI-CMP-019` |
| `GS-CLS-UI-007` | Talent-point counter and allocation feedback | `GS-UI-CMP-022` |
| `GS-CLS-UI-008` | Talent and specialization tooltip surface | `GS-UI-CMP-011` |
| `GS-CLS-UI-009` | Specialization summary and reset controls | `GS-UI-CMP-002`, `GS-UI-CMP-003`, `GS-UI-CMP-001` |
| `GS-CLS-UI-010` | Weapon affinity or element label chips | `GS-UI-CMP-016` |

Suggested component/state checklist:
- Class selection card: default, hovered/focused, chosen, unavailable; **screen itself not yet approved**.
- Class header display: class emblem, class name, level and (later) chosen specialization; do not invent a separate class stat.
- Specialization selector: three labeled choices, selected/locked states, spec emblem.
- Skills tree viewport: scrollable and legible at phone width; progression rule and exact layout to be specified.
- Generic tree nodes: ordinary, notable and capstone *visual treatments if content design calls for them*; available/allocated/locked states.
- Shared connector lines, allocation confirmation, point tally, talent-detail tooltip, reset/respec controls.
- Optional shared neutral equipment-affinity label and damage-source tag. These are **not six class-owned abilities**.

### Existing work to reuse

- `GS-UI-CMP-001` panels, `GS-UI-CMP-009` tabs, `GS-UI-CMP-019` tree nodes, `GS-UI-CMP-011` tooltip and `GS-UI-CMP-016` damage tags.
- Reference-only dark-gold chrome project on draft PR #6 (`ui/gilded-chrome-svg-library`) has 156 reusable assets. Do not require one bespoke gold frame for each class.
- Current Character Skills tab is a **placeholder**. A fully interactive Skills tree is neither coded nor approved.

## 3. Talent-specific art (blocked until gameplay design)

| Family | Count | Why deferred |
|---|---|---|
| Unique passive/talent symbols | **TBD** | Names, actual passive effects, total nodes and reused/unique talents are not finalized |
| Capstone talent illustrations | **TBD** | Design mentions capstones, but node counts and rules aren't locked; potentially one per specialization if approved |
| Talent-specific overlays | **TBD, only if needed** | Reuse generic ready/locked/allocated frames before making individual FX |
| Class-specific weapon-affinity glyphs | **Only if UI needs them** | Prefer existing weapon-type icons and shared damage-source tokens; don't reinvent weapon assets |

Do not choose a random number like ten talents per tree and turn it into '180 required spell icons'. Doing so would manufacture unapproved content.

## 4. Optional, future content (not P0)

- **6 class promotional portraits** if a future creation/onboarding screen actually needs them. These do not belong in the current Fight combat square.
- **6 class-themed banner illustrations**, but only if there is sufficient space in a future class-selection or class-guide screen; one common panel frame should be reused.
- **Up to 18 specialization showcase illustrations** only if we explicitly decide to add large spec-preview art. The 18 small specialization sigils are the required pieces.
- **Animation and transitions** such as a specialization-change highlight can initially be lightweight CSS, not separately rendered 18-frame sprite packs.
- Future class lore cards/intro content require approved words and setting. They are not a reason to draw characters before the need is established.

## 5. Non-art definitions needed to create accurate artwork

Before finalizing each spec symbol and especially before talent icons:
- Class visual motifs and silhouettes, color neutrality versus optional class-color accents, and any official symbolism.
- Final specialization names (Archer's **Marksman/Ranger/Tracker** tentative; Shaman's **Primal** tentative).
- A short specialization description and intended synergies for all 18.
- Actual passive tree data: talent names, effects, number of nodes, tiers/prerequisites, capstones and whether nodes have art or use generic symbols.
- Any actual class selection UI need and treatment of one player character versus multiple characters. No decision presumed here.

## 6. Production sequence

1. **Design benchmark:** six class emblems, one from each class, in at least three genuinely different stylistic concepts (same art subjects across style candidates). Choose one recognizable style and apply consistently.
2. **Core:** export the six accepted class emblems; test against the dark-gold UI at 24/36/48/64px.
3. **Spec identities:** produce 18 specialization emblems in three small class-grouped batches, with tentative names clearly identified until approved.
4. **Screen composition:** build class badge and shared selector/stats/Skills frame using existing dark-gold UI art. Mock only; don't wire to simulator.
5. **Passives:** finalize talent content, then commission only the resulting named talent/icon list. Optional portraits/banners remain last.

## Source & approval status

**Verified from project docs:** 6 classes; 18 named spec positions; main-hand weapon auto-ability model; Character tabs and no Fight player model. **Proposed design inventory:** these new IDs, class-specific sigils, reusable compositions, future portraits. **Not specified:** talent node counts, final capstone artwork count, class-specific colors, hero art, final Archer/Primal names. The JSON catalog at `docs/CLASS-ASSET-CATALOG.json` records all individual proposed IDs. No existing manifest status changed; no assets commissioned/deployed.
