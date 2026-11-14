# Godscar — Arsenal, Affixes & Specialization Build System v0.1

**Status: PROPOSAL ONLY. NOT APPROVED, PLAYTESTED OR IMPLEMENTED.**
Date: November 14, 2026. This paper expands the *existing* Godscar item and class direction rather than replacing it. Its purpose is to design big, test combinations, and select a smaller eventual production scope.

**Source-of-truth baseline**: `docs/ITEM-SYSTEM-DIRECTION.md`, `docs/WORLD-PROGRESSION-DIRECTION.md`, `docs/FIGHT-SCREEN-DIRECTION.md`, `docs/NEW-PROJECT-START-HERE.md`. The deployed Fight/Character/Stash/Forge/Navigation preview is mock UI. The authoritative simulator is separate; no change is authorized here.

Companion brainstorming files:
- `docs/design/WEAPON-ABILITIES-CATALOG-V0.md`: 40 archetype packages (120 found automatic ability concepts) and 40 proposed imprint abilities.
- `docs/design/SPECIALIZATION-TREES-V0.md`: 18 proposed trees with 216 passive node concepts.

## 1. Non-negotiable established rules

| Design fact | Established scope |
| --- | --- |
| Main-hand weapon | Owns **all** automatically scheduled abilities, 1–3 found and up to two compatible imprints, five total max |
| Off-hand weapons, shields, tomes, quivers | Stats, affixes, passive/Legendary modifiers; never a second independent ability rotation |
| Class and specialization | Modifies the main-hand abilities through tags, scaling, durability, tactical identity; no added class autocast skills |
| Shared weapon catalogue | 1H dagger/sword/mace/wand/shortbow; 2H sword/mace/axe/staff/longbow |
| Shared armor | 11 equipment slots as described in item paper; five rarities Common through Legendary |
| Forge | Salvage, Upgrade, Reforge, Imprint; no new enchant/socket/jewel/sixth rarity |
| Player input | Auto-fighting, player builds the character and selects targets, not manual rotation |
| Progression | Character levels/trees; equipment/abilities; regions, targeted hunts, bosses and proposed dungeons |
| Canonical world scope | Fallfields has source-verified Husk, Carrion Swarm, Grave Knight, Hollow Wisp, Hollow Bellkeeper; the two *planned* walls are Wisp fire defense and Bellkeeper damage |
| Game state | Existing UI mock-only, simulation isolated; this document is paper design, not a permission to integrate |

**Critical interpretation:** The normal weapon attack is part of the weapon's own first active action or a visual representation of that action. Do not silently restore an automatic baseline attack **plus** five abilities. Five is the entire set. A weapon with only one found action displays one active action. Separate DoT tick and hit feedback is an effect, not an ability slot.

## 2. The five-layer build

| Layer | Owns | Questions answered |
| --- | --- | --- |
| **Weapon family** | Range, hit rhythm, physical/spell tags, 1H/2H tradeoffs | What is the basic cadence? |
| **Weapon archetype** | Synergistic found A/B/C sequence | What does the weapon do? |
| **Imprint recipes** | Up to two compatible extra auto-actions | What holes do I fill: defense, utility, additional payoff? |
| **Class tree** | Modifiers, keystones, defensive interactions | How do I exploit the weapon's behavior? |
| **Gear/affixes/Legendary** | Stats, status quality and limited transformation | How far can I push it, and what do I sacrifice? |

**Proposed build grammar:** Opener (A) → Setup (B) → Payoff (C) → two optional imprints (D, E). This is a *relationship* among actions, not a forced rotation order. A is independently useful; B and C can be missing on early drops. D and E are optional and cannot exceed five. Different weapons should feel different even with the same class and the same two imprints.

### Common action categories (proposed vocabulary)

- **Strike:** normal weapon action; has real damage without a status.
- **Setup:** applies Mark/Bleed/Burn/etc., builds bounded Tempo/Charge or a timed defensive window.
- **Payoff:** rewards a previously established condition, but still has baseline damage when the condition is absent.
- **Guard:** weapon-cast defensive action occupying one ability position; not a free passive timer.
- **Sustain:** weapon-cast restorative action occupying one position, if imprinted; tree enhancements to healing are not additional casts.
- **Hybrid:** a strike can carry status, and a payoff can include recovery or protection, but budgets are shared.

## 3. Damage types, statuses and counters (proposal)

Tags describe an action. Statuses describe its effect on a foe or character. **The same thing is not automatically both.** All tags/status semantics below are provisional.

| Damage family | Common associated mechanics | Build opportunity | Defensive counter |
| --- | --- | --- | --- |
| Physical | blunt, sharp, heavy, Exposed, Bleed | reliable hits, armor counter, burst or wounds | armor, life, Guard, crit reduction |
| Fire | Burn, heated mark | long DoT or Burn-consuming burst | fire resistance, max life, recovery |
| Frost | Chill, brief vulnerability | patient hits, mitigation/control tradeoffs | frost resistance, recovery |
| Lightning | Shock, bounded Charge | rapid pressure then discharge | lightning resistance, reliable mitigation |
| Holy | luminous Mark, ward synergy | deliberate judgment and protection | holy resistance, all-round defenses |
| Arcane | direct spell pressure, temporary marking | flexible payoff and spell scaling | corresponding defense if formally introduced |
| Shadow | Curse and delayed payoffs | conditional damage, attrition | shadow resistance, cleansing/mitigation where defined |
| Poison | Poison, corrosion/Exposed | DoT endurance or toxin-consuming hit | poison resistance and sustain if included in final model |
| Bleed | damage over time sourced from physical hits | ramp, execution, sustain synergy | whatever final status mitigation/cleanse rules establish |

### Status dictionary to settle

| Proposed state | Meaning | Safeguard |
| --- | --- | --- |
| **Bleed** | physical wound damage over time | stack/refresh budgets; no recursive on-tick casts |
| **Burn** | fire damage over time | replace/refresh vs stacking to be tested |
| **Poison** | toxic DoT | bosses remain vulnerable to its damage even if control-immune |
| **Chill** | limited attack-tempo impact or vulnerability | strong bosses have duration/effect resistance, not total Frost immunity |
| **Shock** | vulnerability/setup for lightning finishers | no infinite lightning bounce |
| **Mark** | clear single-target setup flag for precision | short state but reliable enough for deliberate weapons |
| **Curse** | limited shadow debuff; specifics undecided | non-exponential and no soft-lock |
| **Exposed** | bounded reduction in effective protection | cannot erase boss defense entirely |
| **Guard** | temporary player damage mitigation | cannot stack to immortality |
| **Tempo / Charge** | short battle-local counters maintained by actions | not equipment currencies, not shared across attacks forever |

**Do not add every status just because it has a cool name.** One goal of first testing should be to collapse any two states that feel identical. Full crowd-control stuns, permanent freezes, automatic chain-proc loops and arbitrary new resource bars are intentionally excluded from this proposal.

## 4. A bounded automatic execution engine (proposed)

A single attack scheduler is sufficient for Godscar's automatic combat.

1. Read the current main-hand's ordered list of at most five actions. Each ability has declared tags, eligible family, trigger conditions, cooldown or cadence, and one status ledger.
2. At a scheduled decision point choose a ready action. Reserve fair opportunities for all found abilities; never starve the opener just because payoff is ready.
3. Resolve one **root action**: base hit/spell → mitigation → on-hit status → conditional payoff or defensive effect → capped secondary visuals/damage. Weapon ability ownership does not change during a hit.
4. Apply tree, affix, Legendary and off-hand transformations to that root action. Secondary riders cannot initiate new root actions or grant free cast slots.
5. Tick DoTs and expire temporary combat states using a deterministic timing model. DoT ticks should aggregate where sensible.
6. Log source attribution: weapon ability, statuses, tree modifiers and defense. This makes the existing DPS and combat feed useful for testing.
7. On weapon swap, change the action schedule and item-specific imprints. Permanently learned recipes remain known; no permanent free spells on the class.

**Proposed invariants to test:** at most five root ability definitions, finite maximum secondary effects per root, deterministic seeds, bounded buffs, no zero-duration infinite loops, no offline/online arithmetic divergence, stable performance on a budget Android phone. Exact cooldowns, damage multipliers, status caps and proc budgets deliberately have **no values** here.

### Trigger abuse to prevent

- Crit triggers Burn, Burn tick triggers Crit, Crit triggers a second attack, repeat indefinitely.
- Dual-wield off-hand adds an independent entire attack deck.
- Respec switches a weapon to another class while retaining five hidden class skills.
- Stacked statuses produce superlinear damage that no boss can resist.
- Defensive loops make a well-prepared character functionally immortal while still retaining top damage.
- Offline simulation gains more damage/loot from event batching than online play.

## 5. Spell eligibility, imprint recipes and itemization

### Imprint compatibility

Use declared categories rather than vague class locks. Every recipe must have:
- compatible **weapon families**, damage/attack/spell tags, and a condition that it can actually satisfy;
- a valid standalone base outcome when its favorite status is absent;
- an explicit place on the weapon (one of up to two crafted abilities);
- a permanently learned recipe record independent of the weapon;
- a way to preview the resulting action list in Forge before committing;
- cancellation or replacement rules that never destroy a valuable weapon unexpectedly.

**No universal "any spell on anything" rule by default.** A poison dagger imprint does not automatically attach to a physical longbow; a defensive imprint can be broadly available when its animation and timeline fit. Universal imprints still consume a slot.

### Rarities and ability quality

All five approved rarity bands remain. **Do not equate higher rarity with more casts automatically.** A lower-rarity three-ability archetype might be more synergistic than a mismatched Legendary; higher rarity might instead mean stronger bases/affixes or a special passive modifier. The relationship between rarity, number of found abilities and affix count is not settled.

An excellent item creates an interesting choice:
- dependable archetype vs higher base damage;
- found three-action synergy vs incomplete but more powerful new archetype;
- offense vs fire resistance, armor, health or crit defense;
- 1H + shield/tome/quiver, dual-wield, or 2H tradeoffs.

### Equipment slot identities (proposed growth hooks)

| Slot | Candidate support of the weapon system |
| --- | --- |
| Main-hand | owns archetype, all actions and imprints |
| Off-hand shield | block/Guard/armor and protection synergies; no cast |
| Off-hand tome | spell-tag quality, element scaling and recipe modifiers; no cast |
| Quiver with shortbow | projectile mark/tempo/precision modifiers; no second firing skill |
| Compatible dual-wield 1H | bounded speed, physical damage, crit or status support; secondary weapon's actions dormant |
| Helmet / Chest / Legs / Boots | life, armor, resistance, crit-damage reduction and sustain |
| Gloves | attack or spell pacing with opportunity cost to protection |
| Belt | durability/sustain/utility |
| Two rings, amulet | damage/status/defensive mixing with tradeoffs |

### Legendary identity

A Legendary's proposed signature should **transform** an existing action or its condition rather than adding a new autonomous cast. Good examples:
- the next Holy payoff consumes a Guard window for higher damage;
- an Axe payoff prefers Exposed over Bleed but cannot pay both endlessly;
- Shock charges improve Guard quality instead of damage;
- Poison can be paid out early for burst at the expense of DoT duration;
- the first heavy action after taking a critical hit becomes more reliable, not a free retaliatory loop.

Exact Legendary item names, drop sources, affix amounts, rarity rules and unique art remain unapproved.

## 6. Tree investment model: three columns, meaningful tradeoffs

The visual Skills menu already has three specialization columns. Proposed deeper tree anatomy:

| Layer | Per-tree concept count | What it contributes |
| --- | ---: | --- |
| Foundation | 3 | broad damage, basic identity, weapon-family flexibility |
| Branches | 3 | choices tied to physical/element/status/guard setups |
| Engines | 2 | meaningful A→B→C loop improvements |
| Defensives | 2 | answer actual enemy damage patterns |
| Keystone | 1 | strong tradeoff |
| Capstone | 1 | distinctive climax of the tree's existing-action behavior |

Six classes × three trees × 12 nodes gives **216 proposed nodes** in the companion file. Do not imply points are equal to levels or 12 nodes are already final.

### Three progression models to compare

| Model | Rule | Advantage | Problem |
| --- | --- | --- | --- |
| Open mixing | Spend points in any of the three | enormous hybrids | trivial universal best nodes |
| Primary + support | Declare a main tree for deep capstone; take shallow side nodes | recognizable class identity | risks trapping off-meta builds |
| Threshold identity | All trees open, but a capstone requires investment in its own column | flexible while earning identity | threshold tuning might feel like taxes |

**Default hypothesis to test:** threshold identity, with accessible defensive nodes and a respec path. A capstone does not add an active button. Avoid an irreversible choice for a phone-first idle game.

### Node-category guardrails

- **Foundational nodes** should not be limited to a single specific weapon when the class can equip many.
- **Tag-specialists** need clear UI compatibility badges. Equipping an incompatible weapon does not erase the node but may give no specialized bonus.
- **Defensive nodes** must solve multiple encounter types; no mandatory Paladin/Warrior to survive the Wisp.
- **Engines** should modify already scheduled weapon abilities, not generate a free on-every-tick spell.
- **Keystones** should change choices (burst vs duration, 1H speed vs 2H impact, Guard vs damage) rather than merely +100% damage.
- **Capstones** should be powerful but never permanent boss invulnerability, guaranteed rare loot, or uncapped procs.

## 7. Build theory examples (not balance claims)

| Class and tree | Weapon (found 3) | Two optional imprints | Intended decision |
| --- | --- | --- | --- |
| Paladin Avenger with Justicar defense | Cinderbreaker 1H mace | Oath Echo + Ward Sigil | Burn payoff vs safe Guard cycle; shield optional |
| Warrior Savage with Protection support | Bloodharvest 2H axe | Bloodrake + Mender's Pace | Bleed pressure and sustainable boss execution |
| Rogue Assassin / Alchemist | Plague Needle 1H dagger | Ghost Needle + Blight Sever | fast Poison delivery and intentional consumption |
| Mage Lightning | Thunderglass 1H wand | Runic Spark + Ward Sigil | Shock buildup, controlled burst, balanced survival |
| Archer Ranger / Tracker | Thornrunner shortbow + quiver | Hunter's Stitch + Wound Reprise | rapid Mark/Bleed pressure and target preparation |
| Shaman Storm / Nature | Stormcaller staff | Static Crown + Mender's Pace | Shock burst backed by long-fight sustain |

All example imprints must eventually pass family-tag compatibility validation. For instance, a generic sustain imprint is only compatible after the full specification is approved. A build cannot impute its off-hand's learned skills as extra casts.

### Same weapon, different class

**Cinderbreaker mace** with the same three found abilities and two imprints:
- **Avenger:** prioritizes Fire/Holy payoff and timed burst.
- **Justicar:** prioritizes Guard, resilience and retaliatory scaling of the *same* mace strikes.
- **Warrior Savage:** emphasizes Exposed/physical impact, with fewer Holy bonuses.
- **Shaman Nature:** plays a defensive physical/Fire attrition loop.

In every case the weapon executes the **same five actions**. The tree/gear makes the outcome different. This is the core test of whether the item/class system actually works.

## 8. World, bosses, farming and item discovery

- Target hunts should let players farm known weapon archetypes, recipe discoveries and armor resistances by region/family. **No new map content or exact drop percentages are approved here.**
- Wisp: Fire resistance and preparation should matter. Proposed build alternatives include Guard + recovery or higher life with defensive gearing, subject to testing; do not guarantee they clear.
- Bellkeeper: damage preparation should matter. Multiple damage types, mark/burst setups, status pressure and weapon choices should be tested, not force a single essence craft.
- Bosses can resist strong control but should not nullify every Frost/Poison/Mark build. The build must retain a baseline payoff.
- Dungeons, if approved later, use the same one combat scheduler and should offer concentrated rather than exclusive rewards.
- UI should surface "why you lost": damage type, crit occurrence, active defense and source, not only raw DPS.

## 9. Balance programme, eventually, not now

Before implementing the whole catalogue, pick a small **representative slice**:
1. One slow physical/defense weapon (2H mace), one fast Bleed weapon (dagger or shortbow), one elemental spell weapon (wand/staff), and one conditional bow.
2. Four representative trees: Paladin Justicar, Warrior Savage, Mage Lightning, Archer Ranger; other trees can be projected on paper first.
3. Compare 1 vs 3 vs 5 equipped actions, and whether crafting two imprints improves build choice without becoming mandatory.
4. Compare no-counters and mixed counters at the Wisp/Bellkeeper planned walls; compare failure type, time, deaths/hour, DPS and gearing cost across seed-matched bots.
5. Check 1H+shield, dual-wield, 1H+tome and 2H actual performance without assuming balance.
6. Validate offline deterministic replay and device constraints before approving visual effects or 160 production icons.

Every future bot report must state exact repository, branch, full commit, command, seed, fixtures, and measured outputs. Existing SIM v8 is disconnected and predates this system. **This draft runs no simulation** and makes no numeric balance claim.

## 10. Approval gates

- **Approved direction:** main-hand owns up to five weapon abilities; classes modify effectiveness; item types/rarities/forge pillars and 18-spec class matrix as documented, with tentative Archer and Shaman spec names.
- **Proposed concepts:** all 160 ability names/effects, 216 passive nodes, 40 archetype variants, generic imprints, conditional states, build-time scheduler details, tree shapes, capstones and Legendary examples.
- **Decisions still open:** named statuses, skill point pacing/ranks, mixing rules, class weapon affinities, cooldowns, caps, proc limits, recipe learn sources, affix scaling, specific rarity relationships, loot odds, all art exports.
- **Not authorized:** changing the simulation, integrating these mechanics into the UI, exporting hundreds of final icons, deploying, publishing, or marking draft content as confirmed.

The design is intentionally large so the owner can remove weak or duplicative ideas after reviewing how builds connect. A large catalogue is a possibility space, not a requirement to ship all of it.
