# GODSCAR — Roadmap to Version 1.0

**Status:** WORKING ROADMAP / owner review pending. This is a release plan, **not approval to change gameplay, connect the simulator, or deploy**.
**Prepared:** 2026-11-15.
**Current UI/design repository:** \`fytchx/Godscar-playtest\`, \`main\` at \`caaaff2a3efa08b66d9041937464d4c5e16a04ad\`.
**Simulation source:** \`fytchx/Godscar\`, \`slice/paladin-defense\` at \`b02482ded4e118b0b2422677c39463d37dcc9f2d\` (SIM_VERSION 8); linked Playtest copy \`simulation/reference-sim-v8.js\` is **disconnected**.

> **Important platform discrepancy (requires decision at milestone 0.1):** Playtest \`main\` was changed after the November 13 handoff and now contains a Kotlin/Jetpack Compose Android mock UI; the former root browser \`index.html\` is absent. \`docs/FIGHT-SCREEN-DIRECTION.md\` and \`docs/NEW-PROJECT-START-HERE.md\` still document the browser Fight skeleton as the current direction. Do not silently treat either as the new owner-approved product. Resolve the shipping platform and preserve the exact approved visual baseline before implementing functionality. Do not port both clients in parallel by default.

## 1. Product promise

Godscar is a phone-first, fully automatic dark fantasy ARPG. The player makes meaningful decisions about weapon packages, class specialization, equipment, crafting, enemy selection and challenge preparation while combat runs automatically. Changing build is possible without an irreversible trap. The fun is finding a weapon with a compelling combination, improving it, and exploring areas where that build succeeds or fails.

**Core loop:** hunt → loot → improve/experiment → defeat a prepared challenge → unlock the next source of items and recipes → repeat.

### Non-negotiable design anchors

1. The **main-hand weapon** provides the complete autocast package: **1–3 curated found abilities plus at most 2 crafted abilities; 5 TOTAL**. There is **no** separate class automatic attack, and the older "base attack plus five extra" proposal is superseded.
2. The same weapon abilities work for any class allowed to equip the weapon. The **specialization tree** strengthens or modifies compatible tags, weapon types, defenses and statuses.
3. Six classes, three specializations each: Paladin (Avenger/Templar/Justicar); Warrior (Protection/Savage/Fury); Rogue (ranged Subtlety/Assassin/Alchemist); Mage (Pyro/Frost/Lightning); Archer (Marksman/Ranger/Tracker, names provisional); Shaman (Nature/Storm/Primal, last name provisional).
4. Shared **11 equipment slots**, including one main hand and one off-hand. Warrior, Rogue and Shaman dual-wield; off-hand weapons do not create a second 5-skill rotation. Shortbow can use quiver; two-handed longbow sacrifices off-hand flexibility.
5. Five rarities: Common, Uncommon, Rare, Epic, Legendary. Common/Uncommon share art. Rarity stats and precise affix counts are not yet balanced.
6. Forge direction: Salvage, Upgrade, Reforge, Imprint. Gold, Scar Shards and targeted monster materials rather than many currencies.
7. Region hunting, selected 1–4 enemies in one zone, bosses, automatic dungeons, loot protection, safe recovery farm, deterministic and bounded offline progress.
8. Current Fight/Character presentation is the visual baseline; new data integration is **NOT approved** yet. No hero sprite in battle.

## 2. Proposed version 1.0 release scope

These are **planning targets**, not fixed balance/content commitments. Avoid multiplying content until one representative example of each category works.

| Surface | Proposed 1.0 target |
| --- | --- |
| Platform | **One** approved shipping client first, mobile-first (browser OR Android; explicitly decide in 0.1) |
| Classes | 6 playable classes, 18 distinct specialization trees |
| Specialization depth | Approximately 6–8 meaningful nodes per tree, including a distinctive final choice (design target only) |
| Weapons | 10 primary weapon categories (5 one-handed, 5 two-handed) and shield, spell tome, quiver |
| Weapon archetypes | Approximately **2 curated archetypes per weapon category** = 20 package designs as a first planning budget; re-use ability primitives when appropriate |
| Automatic abilities | Up to 5 weapon-owned per build; dropped 1–3, crafted up to 2; finite cooldowns and no proc recursion |
| Other equipment | 11 slots, 5 rarities, limited understandable affix pool, simple Legendary modifiers |
| Crafting | 4 Forge operations, recipe discovery, targeted materials, clear costs, bounded upgrade progression |
| World | **3 complete regions**: Fallfields, Ashen Hollow, and **one third region to be approved** |
| Combat encounters | About **4 distinct ordinary enemy types + 1 major boss per region** as an initial content budget |
| Dungeons | **1 short automated dungeon per region**, with themed encounters and rewards (3 total) |
| Other activities | Optional hunting contracts, Codex / source discovery, **one** modest repeatable endgame challenge |
| Player experience | Character, Fight, Stash/Inventory, Forge, Skills and Navigation all functional, with offline recap and useful combat statistics |
| Persistence | Safe save/load, migrations, offline catch-up, no silent destruction of unreviewed valuable drops |
| Release readiness | Deterministic bot tests, mobile/device QA, balanced progression, accessibility, backup/recovery process and release checklist |

**Release scope control:** If this becomes excessive, cut extra archetypes, a third region or the endgame activity **before** cutting stable loot, build switching, safe saves, offline farming, and the complete first-region experience. Class and content totals are targets to evaluate at 0.6/0.7, not commitments to sacrifice quality.

### Deliberately OUT of v1.0

Multiplayer, trading, guilds, raids, PvP, monetization/pay-to-win, a native client and browser client developed concurrently, prestige/Ascended rarity, jewel transforms, sockets and parallel inscription/enchantment systems, more than five active weapon abilities, unlimited user-generated crafting outcomes, endless live-service chores, and dozens of separate regional currencies.

## 3. Where we actually stand now (verified versus not)

**Verified in repository as of the above commit:**
- Fight/Character design and assets manifest are present in \`docs/\`; manifest contains **99 ID-tagged asset records** with distinct status labels, including checked-in legacy GS-UI source paths and proposed Fallfields work.
- The current \`main\` has an Android Kotlin/Compose mock presentation and project files; browser \`index.html\` is **not present at root**. The accepted browser skeleton remains in Git history. Treat platform status as unresolved.
- A disconnected \`SIM_VERSION=8\` JS reference exists in Playtest; canonical gameplay and bot harness live in the *other* \`fytchx/Godscar\` repo on \`slice/paladin-defense\`. Existing simulation has **older item/skill rules**, not our newly designed weapon package system.
- Fallfields names grounded in older simulation: Blighted Husk, Carrion Swarm, Grave Knight, Hollow Wisp; boss Hollow Bellkeeper. Wisp fire-resistance and Bellkeeper DPS are the **two planned progression walls**. Husk/Knight are not current boss/wall targets.
- Other established place: Ashen Hollow, with historical Woundwarden boss. Third-region names, new dungeon bosses and content are proposals only.

**Not verified complete:** playable class trees, curated weapon package generator, item system v1, Forge, connected live Fight UI, working dungeons, final 1.0 quality/performance or balanced first-wall pacing. Don't turn simulated reports or mock UI into release claims.

## 4. Milestone sequence with acceptance gates

The labels \`0.1\` through \`1.0\` denote **feature gates**, not dates or promises. Work can overlap in separate art/design branches, but each gate has a clear demonstration and test requirement.

### 0.1 — Source-of-truth and technical direction (NEXT)

**Goal:** Everybody builds the same game from the same source.

- [ ] **GS-RD-001:** Owner decides shipping priority: browser or native Android; existing repo's Android port and older browser skeleton are compared **without rewriting either** before approval.
- [ ] **GS-RD-002:** Choose final home for product UI and source. Preserve the exact approved Fight + Character presentation and its art/export specification.
- [ ] **GS-RD-003:** Reconcile \`AGENTS.md\`, \`README.md\`, UI docs and the current GitHub tree. Clearly document \`main\` branch, canonical simulator branch and safe PR workflow.
- [ ] **GS-RD-004:** Reconfirm new weapon model (5 TOTAL) and approve basic compatibility rules for 1H/2H, dual-wield, quiver, shield and tome.
- [ ] **GS-RD-005:** Publish the proposed release content budget and explicit cut list. Track open gameplay/branding decisions in one decisions log.
- [ ] **GS-RD-006:** Define the asset acceptance template: ID, usage, size, transparent export, source, reviewed state and actual integrated path.
- [ ] **GS-RD-007:** Keep the gameplay simulator and screen separate until a distinct owner-approved connection task.

**Gate:** One current architecture/ownership document, one approved platform priority, source boundaries unambiguous, all six class specs named or flagged provisional; no old UI mistakenly represented as current.

### 0.2 — Shared UI and Fallfields art production

**Goal:** A convincing, consistent visual product shell at real phone dimensions.

- [ ] **GS-RD-010:** Audit existing GS-UI reusable CSS/SVG components; retain useful ones, revise weak pieces rather than regenerating everything.
- [ ] **GS-RD-011:** Finalize component families for panels, button states, bars, dialogs, filters, item frames, tooltips, equipment slots, navigation, bottom sheets and Forge/Skills shells.
- [ ] **GS-RD-012:** Use \`docs/ASSET-MANIFEST.md\` stable IDs; mark existing assets by verified files, not assumed references; expand IDs only for approved requirements.
- [ ] **GS-RD-013:** Produce one fully production-ready benchmark kit: panel, button, HP bar, Fallfields battlefield backdrop and Hollow Wisp art, including reusable source and mobile optimized export.
- [ ] **GS-RD-014:** Produce approved Fallfields environment backgrounds, 4 enemy illustrations, Bellkeeper, impact effects, status symbols, starting gear icons and material icons.
- [ ] **GS-RD-015:** Implement visual shells for Character, Inventory/Stash, Skills, Forge and Navigation under the chosen platform with **mock data only**.
- [ ] **GS-RD-016:** Create gallery/preview and test at 360/390/412/480 CSS px, low-memory Android hardware and reduced effects.

**Gate:** All required Fallfields/UI art used in an actual preview, consistent phone readability, no giant crop sheets pretending to be separate production files, no generic placeholder art labeled final, no gameplay wiring.

### 0.3 — Combat and weapon-package engine

**Goal:** Prove the NEW game rules in a deterministic simulation, independent of UI.

- [ ] **GS-RD-020:** Compare V8 simulator against new design; explicitly list rules to keep, replace or discard. Do not merely connect V8 and hope.
- [ ] **GS-RD-021:** Define stable schema for class, specialization, equipment, damage tags, weapon bases, archetype, ordered abilities, status effects and cooldowns.
- [ ] **GS-RD-022:** Implement weapon-generated autocast rotation: 1–3 found + 0–2 crafted = max 5 TOTAL; no class auto attack.
- [ ] **GS-RD-023:** Validate off-hand damage/stat rules and two-hand tradeoffs; prevent a second off-hand skill rotation.
- [ ] **GS-RD-024:** Reuse trusted damage/defense principles where compatible, including physical armor, elemental resistance, monster criticals, capped crit-reduction and death recovery. Crit reduction must have a real gear/tree source.
- [ ] **GS-RD-025:** Preserve seeded deterministic live/offline/boss outcomes, bounded work per simulated time, no ability-trigger recursion, no negative/infinite timers.
- [ ] **GS-RD-026:** Run test fixtures for one physical, one fire and one bow archetype using at least two specializations. Record repo/branch/commit/seed/fixture/command.
- [ ] **GS-RD-027:** Design and verify the **read-only UI data contract**, but do **not** connect to UI without a separate owner approval.

**Gate:** A reproducible test reports every auto-cast ability and its source, no extra sixth ability, item/offhand swaps are deterministic, the same weapon works across classes, and offline simulation matches online rules within documented limits.

### 0.4 — Playable Fallfields vertical slice

**Goal:** One genuinely enjoyable stretch of the real game, not a giant content demo.

- [ ] **GS-RD-030:** The selected shipping UI (after explicit authorization) shows the real combat state, health, status effects, full-body enemy art, ability progress, DPS/session detail and loot events.
- [ ] **GS-RD-031:** Implement Fallfields hunting for Husk, Swarm, Knight and Wisp; zone picker selects 1–4 in same zone, with stage changes and correctly described enemy weaknesses.
- [ ] **GS-RD-032:** Implement a minimal functional Character screen with Paladin and one contrasting ranged or dual-wield example, equipment swaps and stats.
- [ ] **GS-RD-033:** Loot a functional 1–3-ability weapon, equip it and see automatic skills change; basic armor and resources drop.
- [ ] **GS-RD-034:** Implement Wisp's meaningful Fire resistance preparation check and Bellkeeper's damage/survivability challenge without requiring a lucky rare-only item.
- [ ] **GS-RD-035:** Establish safe farming after failed pushes, a recoverable death policy, first boss rewards and the next region unlock path.
- [ ] **GS-RD-036:** Bot-test a fresh character with optimizer and lazy play policies at fixed seeds; measure first wall, deaths/hour, resources/hour and recovery. Former **20–30-minute first-wall goal** is a target to validate, not a known outcome.

**Gate:** Starting from nothing, a player can progress, struggle, alter build, defeat Bellkeeper and reach a new area. No mandatory manual combat action, no economic soft lock, no silent drop destruction. Signed off on actual phone.

### 0.5 — Full equipment, loot, inventory and Forge

**Goal:** Make the weapon-centered ARPG itemization genuinely playable.

- [ ] **GS-RD-040:** Ten weapon categories, three offhand types, 11 equipped slots and 5 rarities; Common/Uncommon use shared art.
- [ ] **GS-RD-041:** Balanced meaningful item bases and affix pool; rarity changes equipment quality without always making Legendary best.
- [ ] **GS-RD-042:** Define and implement curated archetype package tables, with clear possible drops and source locations.
- [ ] **GS-RD-043:** Craft up to two compatible imprinted abilities; permanently unlock recipes, avoid class-only cloning and prevent overwrite of the three found abilities.
- [ ] **GS-RD-044:** Four Forge operations: Salvage, Upgrade (proposed +0..+5), Reforge (keep old result option), Imprint.
- [ ] **GS-RD-045:** Create coherent Gold/Scar Shard/targeted-monster-material economy; avoid six different essence requirements for ordinary crafting.
- [ ] **GS-RD-046:** Inventory, long-term Stash, search/sort/filter, item locks, side-by-side comparison and clear selected weapon abilities.
- [ ] **GS-RD-047:** Protect high-value loot during offline overflow, including bounded queue and visible "drop pause" behavior; test save roundtrip and full-storage cases.
- [ ] **GS-RD-048:** Ensure dual-wield Warrior/Rogue/Shaman, offhands and 2H weapons all have a legitimate advantage in at least one build.

**Gate:** Multiple viable gear paths, all five rarities, crafted abilities persist, changing weapon predictably changes rotation, no irreversible recipe loss, overflow never silently destroys protected gear.

### 0.6 — All classes, specializations and buildcraft

**Goal:** Deliver the six-class vision without writing six separate combat engines.

- [ ] **GS-RD-050:** Confirm provisional Archer spec names and Shaman third spec name, and finalize Rogue's ranged specialization.
- [ ] **GS-RD-051:** Define 18 spec trees using shared passive mechanics: weapon affinity, damage/status tags, defense/utility and a capstone. Approx. 6–8 meaningful nodes each as a target.
- [ ] **GS-RD-052:** Implement attribute/level/skill point rules, free or reasonable respec, class starts and clear skill descriptions.
- [ ] **GS-RD-053:** Test each specialization with at least **two** different relevant archetype builds; test cross-class use of the same weapon.
- [ ] **GS-RD-054:** Balance shortbow+quiver vs longbow, 1H+shield/tome, dual-wield vs 2H; ensure weird but compatible weapon choices don't hard-crash or become traps.
- [ ] **GS-RD-055:** Build complete functional Skills UI with allocation previews, reset and specialization selection.
- [ ] **GS-RD-056:** Finish base class/spec icon assets only after their icons and names are approved.

**Gate:** All 18 specs have a functioning meaningful build, not filler nodes; each class can handle early progression with readily available equipment; changing spec doesn't brick saves or delete items.

### 0.7 — Complete world, targeted farms and bosses

**Goal:** Turn the vertical slice into a complete first journey.

- [ ] **GS-RD-060:** Finish and balance Ashen Hollow using grounded existing enemies and Woundwarden only after its final mechanics are approved.
- [ ] **GS-RD-061:** Approve/design the third distinct region. Proposals such as Thornwild and Stormbreak Coast are NOT established content.
- [ ] **GS-RD-062:** Set up 4-ish enemy roles per region, distinct resistances/status pressure, artwork and biome.
- [ ] **GS-RD-063:** Every enemy family has a worthwhile farm reason: weapon archetype, specific material, recipe or item family.
- [ ] **GS-RD-064:** Finish story/regional bosses with distinct preparation checks, guaranteed first-clear progression/recipe rewards and repeatable material rewards.
- [ ] **GS-RD-065:** Implement clear region access, stage progress, map/navigation UI, return-to-earlier-area farming and safe retreat.
- [ ] **GS-RD-066:** Check early/mid/late progression rates; avoid more stages that only multiply HP while providing no meaningful new decision.

**Gate:** Three distinct regions, each with a complete content loop and no irreversible gate; each region has content worth revisiting after unlock.

### 0.8 — Dungeons, contracts, Codex and sustainable endgame

**Goal:** Make repeat play rewarding without adding a second combat engine.

- [ ] **GS-RD-070:** One short automated dungeon per complete region, using the SAME deterministic combat and current weapon rotation.
- [ ] **GS-RD-071:** Dungeon encounter sequences, boss, reward identity, fail/stop/retry behavior, progress recap and offline completion rules.
- [ ] **GS-RD-072:** Exactly one active combat reward timeline: hunting does not pay full rewards in parallel with a dungeon run.
- [ ] **GS-RD-073:** Optional contracts tied to ordinary hunting, without mandatory daily chore timers.
- [ ] **GS-RD-074:** Codex includes discovered enemies, regions, archetypes, recipes, materials and where to find them.
- [ ] **GS-RD-075:** One small repeatable endgame challenge that reuses enemies/mechanics with difficulty modifiers; no mandatory sixth rarity or currency.
- [ ] **GS-RD-076:** Verify dungeon reward/hour versus hunting and boss farming; no activity must be obviously dominant for all goals.

**Gate:** Three dungeons that reward different preparations and resources, functional source lookup, meaningful replay and no infinite/offline reward exploit.

### 0.9 — Closed beta, performance and launch-hardening

**Goal:** Replace design assumptions with evidence and protect real player progress.

- [ ] **GS-RD-080:** Full end-to-end fresh-run automation for all classes/specs at fixed seeds, with exact repo, branch, SHA, harness, command, fixtures and raw results.
- [ ] **GS-RD-081:** Balance stage progress, first wall, deaths/hour, long-run loot, rarity frequency, ability package drop rates, crafting cost/income and dungeon payouts.
- [ ] **GS-RD-082:** Save versioning and migrations, backup/export/import, reload/offline catch-up correctness, bounded 24-hour-or-agreed cap and clock tampering handling.
- [ ] **GS-RD-083:** Test full bag/stash/overflow, missing assets, interrupted dungeons, failed boss, no materials, item unequip/swap, respec, dual wield and repeated long offline runs.
- [ ] **GS-RD-084:** Profile battery, memory and animation costs on lower-midrange Android phones; test 360/390/412/480 px layouts. Reduced effects option required.
- [ ] **GS-RD-085:** Accessibility: readable contrast, reduced motion, touch targets, color+shape for source/status, feedback and errors that explain how to recover.
- [ ] **GS-RD-086:** Closed playtest reports: confusing menus, lack of good drops, too many clicks, early boredom, inaccessible counter materials; prioritize measured blockers.
- [ ] **GS-RD-087:** Final art licensing/source audit and load-size optimization; source files and individual transparent production exports accounted for.
- [ ] **GS-RD-088:** Security/privacy/deployment checklist appropriate to the selected platform, rollback process, crash logging policy and clear version labeling.

**Gate:** No known progression-blocking bug or permanent item loss, deterministic simulation acceptance checks pass, smoke tests and real-device tests pass, known game balance meets agreed bounds, and rollback/recovery paths are verified.

### 1.0 — First complete release

- [ ] **GS-RD-090:** Owner approves final 1.0 scope and signs off on all required gates.
- [ ] **GS-RD-091:** Tag the exact code version and record assets, simulation version, migration path and release notes.
- [ ] **GS-RD-092:** Verify launch/review URL or approved Android release artifact against exact commit, not merely a successful push.
- [ ] **GS-RD-093:** Provide a brief first-run guide, controls/automation explanation and feedback/bug-report path.
- [ ] **GS-RD-094:** Keep a post-launch hotfix branch and backup/rollback plan; freeze feature additions through launch verification.
- [ ] **GS-RD-095:** Create separate **post-1.0 backlog** for additional regions, extra classes, more archetypes, optional Ascended/sets, cosmetics, trading/multiplayer etc. None are launch requirements.

**Definition of done:** A new player can pick any class, equip an automatically attacking weapon with coherent skills, earn equipment and materials offline, improve or change their build, finish the three-region adventure, complete dungeons, participate in repeatable endgame, and resume safely on the selected phone-first platform.

## 5. Dependency map (what must come before what)

\`\`\`mermaid
flowchart TD
  A[0.1 Platform and source truth] --> B[0.2 UI and art pipeline]
  A --> C[0.3 Simulation and ability model]
  C --> D[0.4 Fallfields playable]
  B --> D
  C --> E[0.5 Item and Forge systems]
  D --> E
  E --> F[0.6 Classes and specialization trees]
  F --> G[0.7 World expansion]
  E --> G
  G --> H[0.8 Dungeons and endgame]
  H --> I[0.9 Beta and QA]
  I --> J[1.0 Release]
\`\`\`

Parallel-safe activities before the simulation link is approved: asset production, design specs, icon exploration, mock-data UI shells, enemy and ability data tables, test fixture design and docs. The actual simulator-to-screen connection requires a **distinct owner sign-off**, even if other milestones are ready.

## 6. Content-production budgeting and ordering

1. **Design first:** A region's 4 enemy roles, boss, farm rewards, materials and weapon archetype tables; approval before final art.
2. **One style benchmark:** panel/button, enemy, battlefield and item/ability icon shown together at phone width.
3. **Fallfields production:** finish only confirmed enemies and mechanics from the 99-ID asset manifest; do not assume proposed dungeons or new bosses are approved.
4. **Weapons:** after archetype names and ability effects are finalized, design grouped ability icons and 10 weapon base families, using four appearance tiers if Common/Uncommon share art.
5. **Armor:** set melee/caster visual rules; reuse base silhouettes/rarity decorations where practical, with enough variation to tell pieces apart.
6. **Region-by-region:** new locale, 4 enemies, boss and dungeon designs; reuse the UI system and technical asset format.
7. **QA:** every final asset has stable ID, approved source/style, transparent or correct background, export at intended sizes, correctly cropped canvas, no baked labels, preview and actual integration record.

**Asset budget is not image count:** one enemy may require art, effects, a portrait/thumb and animations; a nine-slice panel can cover many UI screens. Track completed *production-ready components* instead of raw image generations.

## 7. Testing and balance scorecard

Set concrete numerical thresholds during the relevant milestone, based on gameplay reports; don't invent final values now. Minimum scorecard:

- Fresh start progression milestones to first Wisp resistance problem, Bellkeeper, first dungeon and region 2.
- Deaths/hour, XP/hour, gold/hour, material/hour and recovery time after dying.
- Item drops/hour split by rarity, weapon archetype and ability count; proportion of immediately useful upgrades.
- Two archetype builds per specialization; class completion and failure rates for identical stage fixtures.
- Sustain versus DPS versus defenses; dual-wield, shield/tome/quiver and 2H tradeoffs.
- Time/materials needed to obtain 1st and 2nd imprint and to reroll one useful affix.
- Offline 1h, 8h and cap-length consistency vs online seeded simulation.
- Full-storage overflow behavior, save/import/version migration, crash/restart and dungeon interruption.
- Main UI frame/load responsiveness, battery and memory on reference phone; no horizontal clipping at 360/390/412/480 width.

Historical 20–30 minute first-wall goal remains **not yet met consistently** in prior Paladin bot work. Reset baselines when new weapon rules replace the simulator; don't compare old and new experiments as identical systems.

## 8. Release controls and risk register

| Risk | Mitigation / hard rule |
| --- | --- |
| Platform drift: browser skeleton versus newly committed Android port | Select one shipping UI and document the choice before engine integration |
| Huge art packs before content lock | Stable ID manifest, small reviewed batches, per-asset exports and QA |
| Item/ability combinations explode | Curated archetypes; common tags; bounded 5 skills, cooldown and no proc recursion |
| All six classes balanced at once | First two contrasting specs, then all 18 behind common rules and fixture-based tests |
| A rare drop can be silently lost during idle | Protected overflow queue/drop pause, explicit status and test cases |
| Six currency types required for basic progress | Gold + Scar Shards + targeted monster materials; no arbitrary recipe bloat |
| Dungeon and passive farming both award full XP/items | Single combat reward timeline |
| Old simulation misrepresented as new item system | Reconcile v8 data contracts and write explicit migration/new-version tests |
| Deployment triggered by code or branch edit | No live deployment without owner approval; verify service branch/actual SHA |
| Scope creep before playable core | No additional region/class feature gates until Fallfields is enjoyable and reliable |
| Permanent class trap | Reasonable respec, viable safe farm and no permanent loss of learned recipes |

## 9. Work tracking and reviews

**Task ID format:** \`GS-RD-###\` above, separate from \`GS-FF-ENV-###\` / \`GS-UI-CMP-###\` stable art asset IDs. These are *proposed tasks*, not completed work.

For each task track: status \`Backlog → Specified → In progress → Review → Tested → Approved\`, owner decision required, dependencies, branch + exact commit, links to asset IDs, acceptance screenshot/fixture/test, and known unresolved issues.

Do not count a milestone done because a feature is visible in a static mock. Label it DESIGN, UI MOCK, SIMULATED, TESTED, or RELEASED with proof. Scope approval and deployment approval are separate.

### Immediate ordered next work

1. Decide whether the Kotlin Android port (current \`main\`) replaces the earlier browser-first Fight target, or whether browser remains the shipping product. No engine wiring until resolved.
2. Approve or modify the proposed 1.0 content budget (6/18 classes, 3 regions, 3 dungeons, 20 archetypes target).
3. Finalize **Fallfields gameplay content sheet**: enemy mechanics, Wisp/Bellkeeper rewards, materials and item/weapon archetype sources.
4. Audit and approve the first 6 manifest assets; produce **one** benchmark environment and enemy with correct export/scale.
5. Write the new **weapon item/ability data contract** and a small deterministic proof-of-rules simulation independent of UI.
6. Build the playable vertical slice; then increase content only after its tests and pacing make sense.

**Review point:** Once 0.1 has an approved source/platform contract and Fallfields content sheet, 0.2 art production and 0.3 simulation-design work can proceed in parallel. No automatic UI/simulation integration.
