# Godscar — Weapon Ability Catalogue v0.1

> **PROPOSAL / NOT APPROVED / NOT IMPLEMENTED.** Creative design inventory for owner selection and testing, 2026-11-14.
> Current source-of-truth rules: `docs/ITEM-SYSTEM-DIRECTION.md`, `docs/WORLD-PROGRESSION-DIRECTION.md`.
> Do not assign `GS-ABL-*` production art IDs to any entry until mechanics are approved. Names, tags, numbers, unlocks, classifications and recipes below are exploratory.

## Catalogue counts and reading guide

- **10 active main-hand weapon families** × **4 proposed archetype packages** × **3 named found abilities** = **120 proposed found abilities** across **40 archetypes**.
- **30 family-specific imprint candidates** (3 × 10) + **10 general imprint candidates** = **40 extra weapon abilities**.
- **Total: 160 distinct named ability concepts**, not 160 abilities equipped at once.
- A weapon drop carries **1–3 synergistic found abilities**, and the player can attach **0–2 learned compatible imprint abilities**. **Maximum 5 total**. No class slot or extra independent auto-attack is added.
- In each archetype, the A move is useful alone, B establishes a condition/defense, and C capitalizes on B. A drop with fewer found abilities still works. The weapon's ordinary strike should be represented by its first/own ability, not a sixth cast.
- Labels like *Mark*, *Exposed*, *Tempo*, *Guard* or *Curse* are proposed vocabulary until a shared status model is approved. Names don't indicate confirmed rarity, drop chances, skill unlocks, animation or damage formulas.
- Temporary concept ID format `DRAFT-WA-FAMILY-##` is **not** a production/manifest asset ID. All packages are class-agnostic.

## Found weapon archetypes (40)

### 1H dagger — fast precision, poison, bleed, curse

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-DAG-01` **Bloodletter** | Red Cut — fast physical hit | Open Vein — applies Bleed | Crimson Recall — bonus against Bleeding; cashes out a portion of accumulated pressure | Physical/Bleed |
| `DRAFT-WA-DAG-02` **Quickfang** | Flicker Jab — rapid light strike | Twin Puncture — builds Tempo on consecutive hits | Fang Finale — spends Tempo for a heavier precision strike | Physical/Tempo |
| `DRAFT-WA-DAG-03` **Shadecarver** | Dusk Slice — mixed physical/shadow strike | Shade Hook — attaches a Curse | Night Rend — amplified damage and recovery against Cursed enemies | Shadow/Curse |
| `DRAFT-WA-DAG-04` **Plague Needle** | Green Prick — quick poison attack | Venom Suture — builds Poison and weakens recovery | Black Tincture — amplified hit based on Poison pressure | Poison |

### 1H sword — balanced rhythm, guard, radiant/fire, versatile counterplay

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-SWD-01` **Dawnblade** | Radiant Cut — holy-edged strike | Hallow Mark — marks the target with Holy pressure | Verdict Arc — spends the mark for a stronger blow | Physical/Holy/Mark |
| `DRAFT-WA-SWD-02` **Riposte Edge** | Measured Slash — stable physical strike | Guard Bait — briefly strengthens Guard after attacking | Reprisal Cut — extra damage while protected by Guard | Physical/Guard |
| `DRAFT-WA-SWD-03` **Ember Sabre** | Kindling Sweep — short fire-edged slash | Ember Trace — lays down Burn | Brand Eruption — stronger damage to Burning foes | Fire/Burn |
| `DRAFT-WA-SWD-04` **Stormcut** | Spark Edge — lightning-infused slash | Static Rake — applies Shock | Thunder Return — amplifies the next strike against Shocked foes | Physical/Lightning/Shock |

### 1H mace — impact, stability, holy judgments, burn and enemy fracture

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-MAC-01` **Cinderbreaker** | Ashen Impact — solid physical mace hit | Ember Rupture — burns and exposes the target | Forgebrand — payoff that exploits Burn and Exposed | Physical/Fire/Burn |
| `DRAFT-WA-MAC-02` **Oathhammer** | Stalwart Blow — reliable close-range strike | Oath Seal — builds a self-ward on an enemy hit | Sanctuary Strike — strike enhanced by an active ward | Physical/Holy/Ward |
| `DRAFT-WA-MAC-03` **Bonecrusher** | Heavystep — blunt impact | Ribfracture — reduces enemy protection through Exposed | Fracture Crush — heavier payoff versus Exposed foes | Physical/Exposed |
| `DRAFT-WA-MAC-04` **Gravebell** | Toll Stroke — ominous impact | Dread Resonance — Curses the enemy | Funeral Knell — shadow-and-physical payoff against Curse | Physical/Shadow/Curse |

### 1H wand — quick elemental projectiles, spell tags and controlled status buildup

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-WND-01` **Flamequill** | Flare Sigil — quick fire bolt | Searing Script — applies Burn | Incinerate Rune — burst powered by Burn | Spell/Fire/Burn |
| `DRAFT-WA-WND-02` **Rime Scribe** | Ice Quill — cold projectile | Frigid Glyph — builds Chill | Crystal Reap — stronger hit against Chilled target | Spell/Frost/Chill |
| `DRAFT-WA-WND-03` **Thunderglass** | Volt Dart — lightning dart | Conducting Thread — builds Shock | Arc Discharge — shock-fed lightning burst | Spell/Lightning/Shock |
| `DRAFT-WA-WND-04` **Hexwand** | Umbral Needle — shadow shot | Misfortune Script — applies Curse | Hex Consumption — decisive hit against Cursed enemy | Spell/Shadow/Curse |

### Shortbow — rapid shots, stacking debuffs, sustained momentum

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-SBW-01` **Thornrunner** | Split Arrow — fast physical shot | Briar Mark — inflicts Bleed and a Mark | Thornstorm — rapid volley benefiting from both | Projectile/Physical/Bleed |
| `DRAFT-WA-SBW-02` **Ashrunner** | Ember Arrow — small fire shot | Smolder Trace — spreads Burn pressure | Brushfire Volley — burst against Burning enemy | Projectile/Fire/Burn |
| `DRAFT-WA-SBW-03` **Windneedle** | Swift Shot — quick physical arrow | Fletching Echo — builds Tempo with repeated shots | Gale Cascade — consecutive shots rewarded by Tempo | Projectile/Physical/Tempo |
| `DRAFT-WA-SBW-04` **Venom Reed** | Toxic Bolt — poisoned arrow | Spore Mark — marks the Poisoned target | Bitter Rain — a flurry that exploits Poison and Mark | Projectile/Poison/Mark |

### 2H sword — deliberate sweeping strikes, execution and large conditional payoffs

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-GSW-01` **Sunrend** | Halo Greatcut — wide holy-physical attack | Bright Scar — marks with Holy pressure | Sunfall — heavy strike exploiting Mark | Heavy/Physical/Holy |
| `DRAFT-WA-GSW-02` **Headsman** | Cleaving Swing — wide physical strike | Execution Mark — prepares a weakened target | Guillotine Arc — finishing blow against Mark or low life | Heavy/Physical/Execute |
| `DRAFT-WA-GSW-03` **Frostcleaver** | Pale Sweep — ice-edged wide cut | Frost Wound — builds Chill | Avalanche Cut — heavy payoff against Chilled enemy | Heavy/Frost/Chill |
| `DRAFT-WA-GSW-04` **Gravebrand** | Grave Sweep — heavy shadow cut | Doombind — inflicts Curse | Tombfall — shadow-finisher exploiting Curse | Heavy/Shadow/Curse |

### 2H mace — slow colossal impacts, defense conversion, boss damage

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-GMC-01` **Hammerfall** | Earth Blow — crushing physical strike | Stagger Echo — exposes enemy vulnerabilities | Ground Rupture — heavy payoff against Exposed enemies | Heavy/Physical/Exposed |
| `DRAFT-WA-GMC-02` **Bellbreaker** | Bellstrike — resonant heavy hit | Resonant Scar — applies Mark | Last Toll — devastating marked-target impact | Heavy/Physical/Mark |
| `DRAFT-WA-GMC-03` **Scorchmaul** | Molten Strike — blunt fire impact | Ignition Weight — builds Burn | Furnace Fall — powerful hit scaling with Burn pressure | Heavy/Fire/Burn |
| `DRAFT-WA-GMC-04` **Warden Stone** | Iron Mercy — stable heavy hit | Stone Ward — defensive weapon ability creating Guard | Bulwark Slam — impact strengthened while guarded | Heavy/Physical/Guard |

### 2H axe — aggressive cleave, bleeding, fire, storm

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-AXE-01` **Warreaver** | Wide Hew — broad physical hit | Sundering Chop — opens an Exposed weakness | Warpath — heavy cleave exploiting Exposed | Heavy/Physical/Exposed |
| `DRAFT-WA-AXE-02` **Bloodharvest** | Ripper — violent physical attack | Bloodprice — inflicts Bleed | Red Harvest — damage and bounded self-sustain against Bleeding foes | Heavy/Bleed/Sustain |
| `DRAFT-WA-AXE-03` **Firebrand** | Flame Hew — fire-physical chop | Kindling Chop — builds Burn | Wildfire Axe — strong burning-target strike | Heavy/Fire/Burn |
| `DRAFT-WA-AXE-04` **Thunderjaw** | Crash — physical opener | Static Gash — applies Shock | Skybreak — lightning-heavy finishing smash | Heavy/Lightning/Shock |

### Staff — extended elemental sequences, area-style single-target presentation and spell depth

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-STF-01` **Stormcaller** | Spark Lance — lightning spell | Charge Conduit — builds Shock | Storm Verdict — lightning payoff against Shock | Spell/Lightning/Shock |
| `DRAFT-WA-STF-02` **Pyreloom** | Kindling Bolt — fire spell | Flame Loom — builds Burn | Phoenix Fall — heavy spell fed by Burn | Spell/Fire/Burn |
| `DRAFT-WA-STF-03` **Winterspire** | Shard Lance — precise frost spell | Rime Spiral — accumulates Chill | Glacier Burst — frost payoff against Chilled target | Spell/Frost/Chill |
| `DRAFT-WA-STF-04` **Voidloom** | Rift Needle — arcane-shadow pulse | Entropy Brand — attaches Curse | Abyss Pulse — arcane payoff to Curse | Spell/Arcane/Shadow |

### Longbow — slower deliberate ranged bursts, marks and precision

| Draft ID · archetype | A: works independently | B: setup | C: payoff | Tags |
| --- | --- | --- | --- | --- |
| `DRAFT-WA-LBW-01` **Deadeye** | True Shot — steady ranged hit | Hunter's Mark — establishes Mark | Killing Horizon — large aimed strike exploiting Mark | Projectile/Physical/Mark |
| `DRAFT-WA-LBW-02` **Nightglass** | Dusk Arrow — shadow bolt | Midnight Sight — sets Curse and Mark | Eclipse Piercer — powerful cursed/marked strike | Projectile/Shadow/Curse |
| `DRAFT-WA-LBW-03` **Sunpiercer** | Golden Bolt — holy projectile | Sun Seal — applies radiant Mark | Dawn Rain — holy salvo exploiting Mark | Projectile/Holy/Mark |
| `DRAFT-WA-LBW-04` **Skyrend** | Current Arrow — lightning shot | Static Quiver — applies Shock | Thunderfall — lightning rain concentrated on Shocked enemy | Projectile/Lightning/Shock |

## Additional imprints (40): learned recipes, not free class abilities

All 40 below occupy a weapon ability position when imprinted and automatically execute by that weapon's scheduler. Each is still subject to its stated compatible weapon families/tags, a two-crafted-ability maximum, the five-total maximum, recipe discovery and future balance validation. They are **not** passive rune slots, class-granted casts or off-hand rotations.

### 30 family-specific imprint concepts

| Draft ID | Family | Imprint ability | Proposed purpose |
| --- | --- | --- | --- |
| `DRAFT-IM-DAG-1` | 1H dagger | **Razor's Afterthought** | a delayed follow-up hit that cannot itself spawn another follow-up |
| `DRAFT-IM-DAG-2` | 1H dagger | **Vein Stinger** | a Bleed-leaning timed thrust |
| `DRAFT-IM-DAG-3` | 1H dagger | **Ghost Needle** | a small Shadow attack exploiting Curse |
| `DRAFT-IM-SWD-1` | 1H sword | **Shieldline Cut** | a quick Guard-building strike |
| `DRAFT-IM-SWD-2` | 1H sword | **Bright Parry** | a protective Holy-edged counter ability |
| `DRAFT-IM-SWD-3` | 1H sword | **Flame Feint** | a short Fire hit that starts Burn |
| `DRAFT-IM-MAC-1` | 1H mace | **Oath Echo** | a Holy strike that fortifies an existing ward |
| `DRAFT-IM-MAC-2` | 1H mace | **Crackbone** | a heavy Exposed starter |
| `DRAFT-IM-MAC-3` | 1H mace | **Searbound** | a Fire smack that adds Burn pressure |
| `DRAFT-IM-WND-1` | 1H wand | **Runic Spark** | a mixed lightning spell that exploits Shock |
| `DRAFT-IM-WND-2` | 1H wand | **Frost Key** | a frost dart that extends Chill within limits |
| `DRAFT-IM-WND-3` | 1H wand | **Black Letter** | a Shadow spell that exploits Curse |
| `DRAFT-IM-SBW-1` | Shortbow | **Hunter's Stitch** | a fast Mark-building arrow |
| `DRAFT-IM-SBW-2` | Shortbow | **Splinterflight** | a physical arrow that exploits Bleed |
| `DRAFT-IM-SBW-3` | Shortbow | **Foxfire Fletch** | a Fire arrow that can Burn |
| `DRAFT-IM-GSW-1` | 2H sword | **Enduring Cut** | a weighty hit with a bounded defensive benefit |
| `DRAFT-IM-GSW-2` | 2H sword | **Last Light** | a heavy Holy execute conditional on Mark |
| `DRAFT-IM-GSW-3` | 2H sword | **Serrated Fate** | a heavy Bleed payoff |
| `DRAFT-IM-GMC-1` | 2H mace | **Iron Toll** | a Guard-enhanced heavy hit |
| `DRAFT-IM-GMC-2` | 2H mace | **Searing Anvil** | a high-impact Fire attack |
| `DRAFT-IM-GMC-3` | 2H mace | **Grave Resonance** | a physical/Shadow strike stronger against Curse |
| `DRAFT-IM-AXE-1` | 2H axe | **Sunderwake** | a heavy Exposed starter |
| `DRAFT-IM-AXE-2` | 2H axe | **Raging Ember** | a Fire cleave exploiting Burn |
| `DRAFT-IM-AXE-3` | 2H axe | **Bloodrake** | a Bleed-finishing strike |
| `DRAFT-IM-STF-1` | Staff | **Static Crown** | a Lightning burst exploiting Shock |
| `DRAFT-IM-STF-2` | Staff | **Aether Nail** | an Arcane burst with independent base damage |
| `DRAFT-IM-STF-3` | Staff | **Cold Singularity** | a focused Frost payoff against Chill |
| `DRAFT-IM-LBW-1` | Longbow | **Piercing Oath** | a deliberate Mark payoff arrow |
| `DRAFT-IM-LBW-2` | Longbow | **Blackflight** | a Shadow arrow that applies Curse |
| `DRAFT-IM-LBW-3` | Longbow | **Stormshaft** | a Lightning arrow that exploits Shock |

### 10 general conditional imprint concepts

| Draft ID | Imprint ability | Compatibility | Proposed purpose |
| --- | --- | --- | --- |
| `DRAFT-IM-GEN-01` | **Mender's Pace** | compatible melee/bow/wand/staff | on-schedule bounded self-heal based on the weapon's damage profile; counts as a weapon ability |
| `DRAFT-IM-GEN-02` | **Ward Sigil** | compatible melee/bow/wand/staff | on-schedule small protection pulse; counts as a weapon ability |
| `DRAFT-IM-GEN-03` | **Purging Stroke** | compatible physical attack weapons | hit plus controlled reduction of an enemy status advantage |
| `DRAFT-IM-GEN-04` | **Elemental Relay** | compatible elemental attack weapons | deals the currently supported element of that weapon; no free element on purely physical drops |
| `DRAFT-IM-GEN-05` | **Markbreaker** | compatible mark-capable weapons | reliable hit with bonus versus Mark |
| `DRAFT-IM-GEN-06` | **Wound Reprise** | compatible Bleed-capable weapons | damage payment to an already Bleeding target |
| `DRAFT-IM-GEN-07` | **Blight Sever** | compatible Poison-capable weapons | damage payment to an already Poisoned target |
| `DRAFT-IM-GEN-08` | **Resonant Blow** | compatible Shock-capable weapons | damage payment to an already Shocked target |
| `DRAFT-IM-GEN-09` | **Winter Echo** | compatible Chill-capable weapons | damage payment to an already Chilled target |
| `DRAFT-IM-GEN-10` | **Cleansing Flame** | compatible Fire/Burn-capable weapons | damage payment to an already Burning target |

## Generation guidelines for future packages

1. Build **A as a complete ability**, B as a meaningful setup or protection option, C as an optional amplification. Never create a mandatory empty A.
2. No imprinted ability may demand an element/status the weapon could never produce unless its own action supplies it.
3. Every archetype has a baseline against status-immune bosses. A conditional payoff must not become useless when a boss reduces status duration or threshold.
4. Treat damage-over-time ticks, critical effects, conditional conversions, shields and extra strikes as **effects of existing ability events**, not recursive abilities that consume or silently create a sixth slot.
5. Thematically expressive is good, mechanically identical in 20 different hues is not. Review each family for genuine pacing/defense/pressure tradeoffs before implementation.
6. The presence of a title in this file does not approve its exact class, rarity, item distribution, acquisition, spell art, or balance.

## Source alignment

- Retains the five example archetypes from the item direction: **Cinderbreaker**, **Bloodletter**, **Deadeye**, **Thornrunner**, **Stormcaller**. Their triple moves here are *new proposals*, not canonical effects.
- Uses all ten active 1H/2H weapon families described in the item direction. Shield, Spell Tome, Quiver and dual-wield off-hand are **modifier providers**, not additional ability-source families.
- Use `docs/design/COMBAT-BUILD-SYSTEM-V0.md` for the status, item, proc and offline guardrails. Use `docs/design/SPECIALIZATION-TREES-V0.md` for the 18 class tree proposals.

