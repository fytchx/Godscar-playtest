# Item System — Current Direction (Paper Design)
**Status:** Working design, approved philosophy but not final numerical balance. Supersedes older innate+five-extra architecture. No gameplay implementation authorized by this document.

## Item anatomy
Base type; item level; rarity; base stats; ordinary affixes; upgrade state. Weapons additionally own an archetype and ability package, with 1–3 found abilities and 0–2 crafted abilities. Legendary equipment may add a non-casting, build-changing modifier. Exact affix counts remain proposals: Common 0, Uncommon 1, Rare 2, Epic 3, Legendary 2 + special.

## Weapon catalogue and roles
1H: dagger, sword, mace, wand, shortbow.
2H: sword, mace, axe, staff, longbow.
Off-hand: shield, spell tome, quiver, or compatible 1H weapon for dual-wield.
- Weapon abilities autocast without player input. Ability #1 functions alone, #2 sets up an interaction, #3 pays it off. Archetype packages are curated; do not randomly combine incompatible powers.
- The same weapon executes the same abilities regardless of class. Class specialization changes effectiveness and synergies through tags, stats and passives, not per-class cloned spells.
- Each weapon may gain TWO crafted abilities, never more; dropped 1+2=3, 2+2=4, 3+2=5. To reach five requires a three-ability dropped weapon.
- Imprint recipes are permanently learned by progression/first-clear/targeted source; new imprints remain attached to item; item replacement does not erase learned recipe knowledge.
- Ability triggers/cooldowns must be finite; no recursive proc explosion. Effects and status DoTs do not constitute bonus auto-cast slots.
- Main-hand is the active ability source; off-hand weapon may contribute limited damage/stats/Legendary passive but must not run its own skills. The off-hand weapon's own abilities activate only when moved main-hand. Balance 1H+offhand, dual-wield and 2H as equally viable tradeoffs.
- Shortbow+Quiver versus 2H Longbow are different playstyles; 2H Longbow ordinarily can't equip quiver. No hard class-only weapon restrictions currently settled.

## Six classes × three specializations
| Class | Specializations | Intended affinities |
| --- | --- | --- |
| Paladin | Avenger / Templar / Justicar | shield physical/holy; tome holy/fire; 2H heavy |
| Warrior | Protection / Savage / Fury | 1H shield; 1H/dual wield physical+bleed; 2H physical/fire |
| Rogue | Subtlety / Assassin / Alchemist | Subtlety ranged bleed, Assassin fast 1H/dual, Alchemist 1H+tome poison |
| Mage | Pyro / Frost / Lightning | staff fire, 1H tome/shield frost, staff/1H lightning |
| Archer | Marksman / Ranger / Tracker | longbow precision, shortbow+quiver rapid, ranged marks/bleed; names tentative |
| Shaman | Nature / Storm / Primal | 1H+tome/shield sustain, staff/1H lightning, 1H dual-wield physical/fire/lightning; Primal name tentative |

Spec trees modify existing weapon behaviors: equipment affinity, elements, survivability and capstone. No class autocast slots. Respecs allowed; changes may incur ordinary costs, never irreversible trap.

## Armor and jewelry
11 equip slots: Weapon, Off-hand, Helmet, Chest, Gloves, Belt, Legs, Boots, Ring1, Ring2, Amulet. No hero model and no Pauldrons slot in approved latest UI.
Helm/chest/legs/boots chiefly survival. Gloves can mix offense/defense. Belt sustain/utility. Rings and amulet flexible and specialized. Shield defense/block; tome spell/element boosts; quiver bow modifiers. Other gear doesn't own independent auto-cast abilities.
Defense must allow armor, HP, elemental resistance, sustain and an obtainable but not yet numerically assigned crit-damage reduction source, to support the existing simulation's defensive walls.

## Crafting and currencies
Salvage (convert unwanted drops), Upgrade (candidate +0 to +5, no failures), Reforge (one ordinary affix, can keep old result), Imprint (up to two compatible extra weapon abilities or swap imprinted ability). Currency direction: Gold, Scar Shards, and a few targeted monster materials (Ember/Dawn are source references, exact drop recipe unapproved). Do not require every element for ordinary armor upgrades.
No extra enchantment, sockets, Ascension, jewels, arbitrary destruction, or class-exclusive parallel crafting trees in v1.

## Loot and safety
Target regions/enemy families for archetypes/materials, bosses for guaranteed first-clear recipes, repeat bosses for materials, dungeons for concentrated reward categories. Character levels strengthen specialization; weapon findings/crafts determine ability counts. Safe farm always available. Protect Rare+ and notable loot from silent loss or forced auto-salvage; bounded overflow with explicit behavior and pause on further gear if full. Exact numbers/drops await simulation.

## Sample archetype packages — examples only
- Cinderbreaker (mace): physical+burn → exploit burning → weaken burning targets.
- Bloodletter (dagger): rapid strike → apply bleeding → payoff on bleeding target.
- Deadeye (longbow): aimed shot → mark → execute marked.
- Thornrunner (shortbow): quick shot → bleed → bonus against bleeding.
- Stormcaller (staff): lightning strike → charges → discharge.
Do not commission final ability icon art until names and effects are approved.
