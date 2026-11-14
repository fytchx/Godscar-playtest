# Approved Fight Screen Direction

This is the owner-approved direction for the current Godscar UI work. It records the decisions from the one-question-at-a-time Fight interview so a new collaborator can continue without re-interviewing the owner or importing a different version.

## Goal and current scope

Create a polished, phone-first Fight home screen as the visual foundation for Godscar. The current pass is a **skeleton with fake data**: layout, interactions, names, enemies, and clearly labeled art slots. The current pass does not connect to the simulation or run bot tests. Only the Fight screen is in scope. Other navigation destinations remain stubs until the owner reviews and approves the Fight screen.

The screen should feel clean, sleek, pretty, compact, and easy to restyle. Do not bring back the old red UI. Keep colors and style controls replaceable; decide final palette and production art with the owner.

## Fight screen layout

1. **Compact header:** player name, class, level, settings access, and a thin XP progress rail attached to the bottom of the header. Show the player health bar directly below it.
2. **Zone selector:** place the current zone name and enemy-rotation count above the arena. Tapping the zone opens a centered panel. Tapping outside closes it.
3. **Combat arena:** a responsive portrait-friendly arena, slightly taller than it is wide. Keep all combat content comfortable inside it.
   - Simple biome art fills the background; use a labeled placeholder for now.
   - A full-body enemy illustration sits in the center; use a labeled placeholder for now. There is no player hero in this arena.
   - Slim auto-cast bars sit at the upper-left and fade into the scene. Each shows its skill name, icon, and source-color progress. They are quick bursts, visible without opening another panel. The auto-attack bar sits with them, muted but clearly distinct.
   - Enemy impact effects match the damage source. Hits should feel impactful; use quick bursts.
   - Floating damage text rises from the enemy. Color it by source; make critical hits stand out. Show incoming damage and applied effects in the activity feed.
   - Put status chips near the upper-right. Put the enemy name and health bar/value below the enemy. Place a live DPS badge at lower-right; abbreviate higher values.
4. **Enemy picker:** list the browsed zone’s enemies with names, status, damage type, weaknesses, and potential material drops. Keep locked enemies visible and marked locked. Use checkboxes.
   - One to four enemies may be selected, and at least one must remain selected.
   - Rotation advances after each kill. Selection changes take effect immediately.
   - Browsing another zone alone does not interrupt the active hunt. Selecting an enemy from another zone clears the previous zone’s selection and moves the rotation there.
5. **Session details below the arena:** show compact session totals and provide an expandable detail area for XP/hour, kills, materials/currency, total damage, and damage by source over time ranges from one hour through 24 hours. Counts begin automatically for each session and after an explicit reset. Ask for confirmation before resetting.
6. **Activity feed:** a fixed-height scroll area below the arena/details. Show events as they happen in one-line entries with small icons. Include kills, item loot, materials/currency, level-ups, deaths, incoming damage, and status effects. Use the damage/effect source color. An options panel uses checkboxes so the player can toggle event types; incoming damage and effects have separate toggles.
7. **Launch recap:** show on each full launch unless disabled in settings. It can be dismissed by tapping outside or its close button. Include XP/levels gained, enemies killed, materials/currency, notable events (especially level-ups, deaths, and rare materials), and the top three notable items. Item rows are compact, with an icon and rarity-colored name; opening an item shows a small detail panel and a route to its inventory entry. Recap ranking/details can be refined later.
8. **Bottom task bar:** fixed order: Home, Character, Stash, Forge, Navigation. Home returns to Fight. These other screens are not part of this Fight-only implementation.
9. **Settings:** include the launch-recap toggle and a placeholder for future appearance controls.

## Color and art direction

Damage-source colors are distinct and restylable: physical is grayish/golden; bleed is blood red; fire fiery; holy radiant gold; frost icy blue; arcane bright purple; shadow shadow purple; poison neon green. Preserve depth and detail when final source colors and art are selected. Keep source colors tied to damage type so hit text, bars, effects, and chips agree.

## Approved existing baseline

- Repository: `fytchx/Godscar-playtest`
- Working branch: `ui/fight-skeleton`
- Baseline screen: root `index.html` from `1c3adf44f1d77cc9549f8cc9b024ec6276cd253e`
- Review URL (Railway Playtest UI service tracking `ui/fight-skeleton`): https://godscar-playtest-ui-godscarplaytest.up.railway.app/

## GS-UI-1.0 visual kit integration (November 13, 2026)

- Source: `fytchx/Godscar`, branch `work/godscar-ui-skeleton`, commit `ad17c6003bf5fcdd9e927ad42e7c251b2cb7858d`, archive `Godscar_UI_Reusable_Starter_Kit.zip`.
- Applied to this repo's approved Fight skeleton, not to the old UI. Local deployable assets now live under `assets/ui/`: reusable CSS, 12 utility SVG icons, and two small ornaments. The reference-sheet PNGs are not production sprites.
- Integrated into buttons, panels/dialogs, bottom navigation, header/XP and health bars, cast indicators, status chips, and activity feed. Existing mock combat, enemy picker, settings, recap and event handlers are preserved.
- First code integration commit `d94760f`; package change `46e84ba`; phone-width 360/390/412/480 browser smoke tests PASS on `4bbc93b`, with 13/13 utility icons loading and no horizontal overflow or JS errors.
- Railway service `Godscar Playtest UI` in environment `Godscarplaytest` was connected to this branch and deployed commit `4bbc93b` successfully. It now tracks future pushes to this branch.
- **UI only:** no simulation import or live gameplay connection was approved; all combat/session content remains fake. All other tabs stay stubs.

## Guardrail for later bot/simulation work

The intended future reference identified so far is `fytchx/Godscar`, branch `slice/paladin-defense`, commit `4e04564d9a134c1f335971c2e52d540a0f5f73c6`. At that commit the simulation is `web/src/sim.js` with `SIM_VERSION = 7`, and the existing bot harness is `web/test/balance.js`.

Before using it, verify the exact commit and inspect the sim, harness, fixtures, and commands. The docs on that same branch conflict with the code: `AGENTS.md` and `START-HERE.md` describe another UI direction and claim the preserved sim is version 4. Do not connect this Fight screen to any other version or accept bot results until that source discrepancy is reconciled with the owner. No integration or bot testing is approved in the current phase.

## Owner-approved Character extension (November 13, 2026)
The owner has separately approved building the Character menu on this Fight-skeleton branch, superseding the earlier Fight-only limit *for this Character menu*. The Fight screen itself must not be redesigned. Character opens as a screen overlay above still-running mock combat; Home closes it. Three top tabs: Equipment (default), Inventory, Skills (placeholder reserved for later). No hero model.
Equipment has exactly 11 slots in this ordered 3-column layout:
- Row 1: Weapon / Helmet / Off-hand. Weapon and Off-hand are taller. Gearscore is in a compact block ABOVE Helmet.
- Row 2: Amulet / Chest / Gloves.
- Row 3: Ring 1 / Belt / Ring 2.
- Row 4: Legs / Boots, centered together.
Stats are below the grid: STR (red), INT (blue), DEX (green), numeric values ABOVE abbreviations; one compact row for Max HP / Armor / Auto Attack Speed. Detailed Stats opens a bottom sheet that covers equipment, leaving an uncovered tappable strip at the top to dismiss it. The detailed sheet is scrollable. Gearscore has no approved formula and is a static sample number.
Inventory opens to Items and has a secondary Materials tab; both sample-only. Skills has only a deferred placeholder. The bottom Stash button remains a stub, to be reconciled later; do not duplicate inventories. No equipment or material actions, storage, simulation data, saves, API calls, or deployments approved by this UI implementation decision.

## Owner-approved menu split (November 14, 2026)
This update supersedes the earlier Character Inventory tab / Stash-stub portion of this document:
- **Character** has two top tabs: **Equipment** (default) and **Skills**. Preserve the existing approved 11-slot Equipment grid, stats, and detailed stats overlay.
- **Stash** is now a working mock-only menu from the bottom taskbar, with **Items** and **Materials** tabs. Relocate the existing sample inventory and material lists here; do not duplicate them under Character. Reuse the shared item preview overlay for both equipped items and Stash items.
- **Skills** shows the *current class* three specialization paths side-by-side, starting with Paladin: **Avenger**, **Templar**, **Justicar**. Under each path, provide only empty visual skill-node placeholders. The count/arrangement of empty nodes is exploratory UI spacing, not an approved skill count, skill tree, unlock order, resource, or gameplay rule. Leave labels, effects, allocation, and respec mechanics for later.
- The existing six-class / 18-specialization art exports remain in the repository library; do not present all 18 as the player's active class skill tree.
- Keep compact dark textured gold UI styling, portrait mobile legibility, and the five-item bottom nav order. Other bottom tasks remain stubs.
- All data and interactions are previews only. No item transfers, equip actions, allocated skill points, persistent storage, simulation wiring, or game changes.
- The review deployment tracks `ui/premium-live-integration` (not `main`); only the playtest UI review service may be updated by the separately approved preview workflow.

## Owner-directed UI foundation expansion (November 14, 2026)
Implemented on `ui/forge-navigation-foundation` for the Railway Playtest UI review service. Supersedes the older note that Forge and Navigation are unimplemented stubs. Keep all data mock-only, and leave the simulation disconnected.

1. **Character/Stash polish:** compact header, equipment and gearscore layout, stat panels, inventory tiles, material rows, and Paladin's three side-by-side specialization columns. The 11 equipment slots, exact stat grouping, separate Stash Items/Materials tabs and existing art stay intact. Skill nodes are unallocated visual placeholders, not designed skill counts.
2. **Forge UI shell:** four accessible preview operations, Salvage / Upgrade / Reforge / Imprint, each with its own panel. All irreversible craft buttons stay disabled. Descriptions reflect `docs/ITEM-SYSTEM-DIRECTION.md`: salvage unwanted gear with Rare+ protection, candidate upgrade +0 to +5 without failure as an accepted paper direction, reforge one ordinary affix with option to keep old result, and imprint up to two compatible crafted weapon automatic abilities with a hard total of five. No crafting cost, recipe, yield, or outcome values are invented.
3. **Navigation UI shell:** Regions / Hunting / Bosses / Dungeons. Fallfields (starting region), Ashen Hollow (second region), the verified Fallfields roster, and The Hollow Bellkeeper use the `docs/WORLD-PROGRESSION-DIRECTION.md` names. Wisp resistance and Bellkeeper damage are explicitly *planned* challenges, not balance measurements. Dungeons are labeled proposed; no named dungeon, stage count, reward, unlock, or offline-policy is treated as approved.
4. **Cross-menu behavior:** five bottom buttons open separate overlays; Home closes them. Visible tab selection, menu close controls, accessible tab roles, and Escape behavior stay intact. Mock combat continues while overlays are open. The Navigation hunt roster does not change the unrelated sample Fight enemy picker.
5. **Art accounting:** UI built with existing CSS/SVG utilities and premium skin. No new production art, skill icons, enemy art, map backgrounds, or manifest asset IDs were invented. `GS-UI-CMP-018` Forge tiles now have a reviewable CSS implementation; actual map nodes `GS-UI-CMP-020` and dungeon reward panel `GS-UI-CMP-021` remain future artwork.

### Verification limits
Static source checks and scripted mock-DOM menu interaction tests are useful but are not mobile-browser screenshots. Review at 320/360/390/412/480 CSS px, and verify menu hit areas, text fitting, scrolling, modal stacking and all existing Fight overlays. No playable features, source simulation imports, or persistent state changes are authorized by this UI work.
