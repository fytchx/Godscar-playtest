# Godscar Fallfields + UI Asset Manifest
**Status:** Working inventory; IDs frozen when first assigned. Repo: `fytchx/Godscar-playtest@main`. This document is a scope specification, NOT a list of completed images.

## Meaning of status
- **EXISTS**: checked into current UI repo and verified by file path.
- **AUDIT**: code/CSS currently provides a functional representation; review whether artwork or refinements are needed. NOT a claim of finished art.
- **SOURCE-VERIFIED-ART-NEEDED**: entity is present in the gameplay source, but commissioned new art is NOT verified.
- **DESIGN**: an asset has a defined UI role, but no production graphic verified.
- **DESIGN-SOURCE-PENDING**: specific item/material use or acquisition is not approved.
- **SPEC-UNLOCK-NEEDED**: gameplay/ability definition must be approved before final art.

## Production rules
- Keep ID stable even if file is renamed, rejected or replaced.
- Recommended file format `<ID>_<slug>.<ext>` with source version tracking. UI scalable elements should use CSS/SVG, not giant baked bitmap screens.
- Use *current* GS-UI-1.0 visual language, simplified/non-photoreal game artwork; avoid tiny noise and generic AI fantasy.
- Render Fight enemy within mobile portrait combat square, with room for floating text/bars. Export full body transparent images; no background baked into enemy files.
- Review icons at 36–64px in an actual phone-width layout.
- Asset-specific sizes below are proposed export targets, not implementation requirements. Browser can use responsive CSS, WebP and appropriately compressed images.
- A "reference concept sheet" is NEVER the same as individually exported transparent production assets.
- Fallfields verified source entities: Husk, Carrion Swarm, Grave Knight, Hollow Wisp, Hollow Bellkeeper. Wisp and Bellkeeper are the planned gameplay walls. Do not invent more Fallfields bosses or dungeon enemies without an approved design.
- Don't create all 5 rarities for every icon before the equipment visual system is validated. Common+Uncommon share appearances by owner direction.

## Manifest (99 records)
| ID | Asset | Screen/use | Output | Target | Status | Priority | Source if existing |
| --- | --- | --- | --- | --- | --- | --- | --- |
| GS-UI-ICO-001 | activity utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/activity.svg |
| GS-UI-ICO-002 | character utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/character.svg |
| GS-UI-ICO-003 | chevron-down utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/chevron-down.svg |
| GS-UI-ICO-004 | close utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/close.svg |
| GS-UI-ICO-005 | eye utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/eye.svg |
| GS-UI-ICO-006 | filter utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/filter.svg |
| GS-UI-ICO-007 | forge utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/forge.svg |
| GS-UI-ICO-008 | home utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/home.svg |
| GS-UI-ICO-009 | navigation utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/navigation.svg |
| GS-UI-ICO-010 | scroll utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/scroll.svg |
| GS-UI-ICO-011 | settings utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/settings.svg |
| GS-UI-ICO-012 | stash utility icon | Current UI navigation/action | SVG | 24×24 scalable | EXISTS | P0 | assets/ui/icons/stash.svg |
| GS-UI-ORN-001 | corner ornament | Current UI ornament | SVG | scalable | EXISTS | P0 | assets/ui/ornaments/corner.svg |
| GS-UI-ORN-002 | diamond ornament | Current UI ornament | SVG | scalable | EXISTS | P0 | assets/ui/ornaments/diamond.svg |
| GS-UI-CMP-001 | Panel / modal frame | Fight, Character, Forge | SVG/CSS | 9-slice/scalable | AUDIT | P0 | — |
| GS-UI-CMP-002 | Primary button states | All menus | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-003 | Secondary and disabled button | All menus | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-004 | Player HP bar | Fight header | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-005 | Enemy HP bar | Fight scene | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-006 | XP progress bar | Fight header | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-007 | Autocast progress bar | Fight cast stack | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-008 | Nav selected/unselected treatment | Bottom navigation | CSS | responsive | AUDIT | P0 | — |
| GS-UI-CMP-009 | Tab selection treatment | Character/inventory | CSS | responsive | AUDIT | P0 | — |
| GS-UI-CMP-010 | Equipment slot + rarity border | Character gear grid | SVG/CSS | 48–84px | AUDIT | P0 | — |
| GS-UI-CMP-011 | Item tooltip surface | Inventory item inspection | SVG/CSS | responsive | DESIGN | P0 | — |
| GS-UI-CMP-012 | Stats bottom sheet surface | Character stats | CSS | responsive | AUDIT | P0 | — |
| GS-UI-CMP-013 | Enemy picker row + check | Fight zone menu | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-014 | Session stats / feed panel | Fight details | CSS | responsive | AUDIT | P0 | — |
| GS-UI-CMP-015 | Offline recap surface | Launch recap | CSS | responsive | AUDIT | P0 | — |
| GS-UI-CMP-016 | Damage-type status chip | Fight scene | CSS/SVG | responsive | AUDIT | P0 | — |
| GS-UI-CMP-017 | Inventory filter/sort controls | Inventory | SVG/CSS | 24px icons | DESIGN | P0 | — |
| GS-UI-CMP-018 | Forge operation tiles | Forge (mock UI shell) | SVG/CSS | responsive | AUDIT | P0 | assets/ui/menus-v2.css |
| GS-UI-CMP-019 | Skill tree node locked/open | Future Skills | SVG/CSS | 48–72px | DESIGN | P0 | — |
| GS-UI-CMP-020 | Map/location node | Future Navigation | SVG/CSS | responsive | DESIGN | P0 | — |
| GS-UI-CMP-021 | Dungeon reward panel | Future Dungeons | SVG/CSS | responsive | DESIGN | P0 | — |
| GS-UI-CMP-022 | Gold/material count token | Global currency | SVG/CSS | responsive | DESIGN | P0 | — |
| GS-UI-CMP-023 | Gearscore mark | Character | SVG/CSS | 24px | DESIGN | P0 | — |
| GS-UI-CMP-024 | Lock/favorite item badges | Inventory | SVG | 24px | DESIGN | P0 | — |
| GS-UI-CMP-025 | Compare/equip/up/down icons | Inventory | SVG | 24px | DESIGN | P0 | — |
| GS-FF-ENV-001 | Fallfields core combat backdrop | Full Fight square | WEBP | 1024×1280 | DESIGN | P0 | — |
| GS-FF-ENV-002 | Blighted farmland vista | Optional location tile | WEBP | 1024×768 | DESIGN | P1 | — |
| GS-FF-ENV-003 | Grave road and abandoned cemetery | Proposed sub-area art | WEBP | 1024×1280 | DESIGN | P1 | — |
| GS-FF-ENV-004 | Broken bell tower | Bellkeeper boss backdrop | WEBP | 1024×1280 | DESIGN | P0 | — |
| GS-FF-ENV-005 | Atmospheric fog overlay | Shared Fallfields environmental effect | WEBP/CSS | 1024×1280 | DESIGN | P1 | — |
| GS-FF-ENV-006 | Ground shadow/contact layer | Enemy integration | PNG/WEBP | 512×256 transparent | DESIGN | P0 | — |
| GS-FF-ENM-001 | Blighted Husk | Full body; regeneration identity | PNG/WEBP | 1024×1024 transparent | SOURCE-VERIFIED-ART-NEEDED | P1 | fytchx/Godscar web/src/sim.js |
| GS-FF-ENM-002 | Carrion Swarm | Grouped silhouette; rapid bites | PNG/WEBP | 1024×1024 transparent | SOURCE-VERIFIED-ART-NEEDED | P1 | fytchx/Godscar web/src/sim.js |
| GS-FF-ENM-003 | Grave Knight | Full body armor silhouette; not progression wall | PNG/WEBP | 1024×1024 transparent | SOURCE-VERIFIED-ART-NEEDED | P1 | fytchx/Godscar web/src/sim.js |
| GS-FF-ENM-004 | Hollow Wisp | Full body supernatural fire figure; first combat wall | PNG/WEBP | 1024×1024 transparent | SOURCE-VERIFIED-ART-NEEDED | P0 | fytchx/Godscar web/src/sim.js |
| GS-FF-ENM-005 | Hollow Bellkeeper | Full body named Fallfields boss | PNG/WEBP | 1536×1536 transparent | SOURCE-VERIFIED-ART-NEEDED | P0 | fytchx/Godscar web/src/sim.js |
| GS-FX-001 | Physical hit flash | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-002 | Fire impact | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-003 | Burning DoT tick | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-004 | Blood/bleed hit | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-005 | Wisp flare | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-006 | Holy impact | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-007 | Lightning hit | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-008 | Poison damage tick | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-009 | Critical text decoration | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-010 | Shield/ward activation | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-011 | Healing pulse | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-012 | Enemy death fade | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-013 | Enemy idle motion template | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-FX-014 | Boss entrance cue | Combat source-aware feedback | CSS/Sprite/WEBP | small sprites or CSS | DESIGN | P1 | — |
| GS-ITM-WPN-001 | 1H Dagger | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-002 | 1H Sword | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-003 | 1H Mace | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-004 | 1H Wand | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-005 | 1H Shortbow | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-006 | 2H Sword | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-007 | 2H Mace | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-008 | 2H Axe | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-009 | 2H Staff | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-010 | 2H Longbow | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-011 | Shield | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-012 | Spell Tome | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-WPN-013 | Quiver | Base item icon family; rarity art derived separately | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-001 | Helmet | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-002 | Chest | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-003 | Gloves | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-004 | Belt | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-005 | Legs | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-006 | Boots | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-007 | Ring | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-ITM-GEAR-008 | Amulet | Equipment category art (melee/caster variants where approved) | PNG/WEBP | 256×256 transparent | DESIGN | P1 | — |
| GS-UI-RAR-001 | Common/Uncommon rarity frame | Slot and tooltip rarity treatment | CSS/SVG | scalable | DESIGN | P0 | — |
| GS-UI-RAR-002 | Rare rarity frame | Slot and tooltip rarity treatment | CSS/SVG | scalable | DESIGN | P0 | — |
| GS-UI-RAR-003 | Epic rarity frame | Slot and tooltip rarity treatment | CSS/SVG | scalable | DESIGN | P0 | — |
| GS-UI-RAR-004 | Legendary rarity frame | Slot and tooltip rarity treatment | CSS/SVG | scalable | DESIGN | P0 | — |
| GS-MAT-001 | Gold | Currency/material inventory icon | PNG/WEBP | 128×128 transparent | DESIGN | P1 | — |
| GS-MAT-002 | Scar Shards | Currency/material inventory icon | PNG/WEBP | 128×128 transparent | DESIGN | P1 | — |
| GS-MAT-003 | Ember Essence | Currency/material inventory icon | PNG/WEBP | 128×128 transparent | DESIGN-SOURCE-PENDING | P1 | — |
| GS-MAT-004 | Dawn Essence | Currency/material inventory icon | PNG/WEBP | 128×128 transparent | DESIGN-SOURCE-PENDING | P1 | — |
| GS-MAT-005 | Grave Iron | Currency/material inventory icon | PNG/WEBP | 128×128 transparent | DESIGN-SOURCE-PENDING | P1 | — |
| GS-ABL-001 | Cinderbreaker package | Ability icons deferred until spells finalized | PNG/WEBP | 128×128 transparent | SPEC-UNLOCK-NEEDED | P2 | — |
| GS-ABL-002 | Bloodletter package | Ability icons deferred until spells finalized | PNG/WEBP | 128×128 transparent | SPEC-UNLOCK-NEEDED | P2 | — |
| GS-ABL-003 | Deadeye package | Ability icons deferred until spells finalized | PNG/WEBP | 128×128 transparent | SPEC-UNLOCK-NEEDED | P2 | — |
| GS-ABL-004 | Thornrunner package | Ability icons deferred until spells finalized | PNG/WEBP | 128×128 transparent | SPEC-UNLOCK-NEEDED | P2 | — |
| GS-ABL-005 | Stormcaller package | Ability icons deferred until spells finalized | PNG/WEBP | 128×128 transparent | SPEC-UNLOCK-NEEDED | P2 | — |

## First review batch (not automatically approved)
- `GS-UI-CMP-001`: panel/frame visual standard; audit existing CSS first.
- `GS-UI-CMP-002`: button default/active treatment.
- `GS-UI-CMP-005`: enemy health bar.
- `GS-UI-ICO-011`: existing Settings icon; verify visual consistency, don't regenerate by default.
- `GS-FF-ENV-001`: Fallfields combat environment art.
- `GS-FF-ENM-004`: Hollow Wisp, full-body transparent, first enemy art benchmark.

## Acceptance checklist
1. Matches approved current Fight/Character skeleton, no old UI restoration.
2. Consistent visual system across panel, enemy, environment, icon and item.
3. Full-body enemy silhouette clear at phone width and no occlusion of bars.
4. Correct file format, alpha/export bounds, filename/ID and no baked UI text.
5. Effects readable with reduced animation and no excessive GPU work.
6. User reviewed and approved before integration; gameplay/simulation still mocked.

## Menu-shell implementation audit (November 14, 2026)
Copied the authoritative asset manifest from `main` onto the preview UI feature branch for continuity. Existing IDs and statuses preserved except:
- `GS-UI-CMP-018` is **AUDIT**: styled CSS four-operation Forge tile interface exists in `assets/ui/menus-v2.css`. It is not production art or functional crafting.
- `GS-UI-CMP-019` remains DESIGN: specialization skill nodes are visual placeholders only; real locked/open node artwork has not been approved.
- `GS-UI-CMP-020` remains DESIGN: Navigation uses text-based region cards, not a finalized map/location-node library.
- `GS-UI-CMP-021` remains DESIGN: Dungeon shell contains no approved dungeon reward panels or complete dungeon mechanics.
No new visual asset IDs, exports, or game content were introduced.
