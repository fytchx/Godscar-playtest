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
- Existing review URL (currently deployed from `main`, not this branch): https://godscar-playtest-ui-godscarplaytest.up.railway.app/

## Guardrail for later bot/simulation work

The intended future reference identified so far is `fytchx/Godscar`, branch `slice/paladin-defense`, commit `4e04564d9a134c1f335971c2e52d540a0f5f73c6`. At that commit the simulation is `web/src/sim.js` with `SIM_VERSION = 7`, and the existing bot harness is `web/test/balance.js`.

Before using it, verify the exact commit and inspect the sim, harness, fixtures, and commands. The docs on that same branch conflict with the code: `AGENTS.md` and `START-HERE.md` describe another UI direction and claim the preserved sim is version 4. Do not connect this Fight screen to any other version or accept bot results until that source discrepancy is reconciled with the owner. No integration or bot testing is approved in the current phase.
