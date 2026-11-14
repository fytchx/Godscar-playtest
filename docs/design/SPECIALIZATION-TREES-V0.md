# Godscar — Specialization Trees v0.1

**Status: PROPOSED CONCEPTS ONLY. NOT APPROVED OR IMPLEMENTED.** All class tree nodes are passive weapon-action modifiers, conditional riders or defensive improvements; none introduces a new independently scheduled cast. The player still uses 1–3 weapon-found abilities + up to 2 imprints (maximum 5). Names, costs, unlocks, number of nodes and mechanic numbers are provisional.

## Structure worth exploring

Character → Skills shows the **current class's three trees side by side**. This proposal sketches **12 nodes per tree** in six horizontal layers: three foundation nodes, three branches, two setup/payoff engines, two defensive nodes, one keystone, and one capstone. This gives 18 trees and 216 nodes total. These 12 slots are **not approved tree size**, actual UI art, or skill points.

**Possible build philosophy:** freely mix foundational/defensive nodes across the three columns but make one late capstone a real commitment. Compare against unrestricted mixing before approval. Node ranks, respec method, point rewards, entry gates, primary spec choice and prerequisites are deliberately unspecified. No class locks a weapon family; tag-specific bonuses can become dormant when the chosen weapon lacks that tag, so the UI must explain compatibility.

## 18 class and specialization concepts

## Paladin

### Avenger — holy execution, heavy hits, burn synergy

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-PAL-AVG-01` | Foundation A | **Radiant Discipline** | Holy-tagged weapon hits gain stable value |
| `DRAFT-SK-PAL-AVG-02` | Foundation B | **Weight of Judgment** | Heavy hits reward deliberate weapon cadence |
| `DRAFT-SK-PAL-AVG-03` | Foundation C | **Smoldered Oath** | Existing Fire tags strengthen Holy payoffs |
| `DRAFT-SK-PAL-AVG-04` | Branch A | **Consecrated Edge** | Marks support Holy damage |
| `DRAFT-SK-PAL-AVG-05` | Branch B | **Blazing Zeal** | Burn increases conditional burst |
| `DRAFT-SK-PAL-AVG-06` | Branch C | **Hammer Doctrine** | Mace and heavy choices emphasize different offense |
| `DRAFT-SK-PAL-AVG-07` | Engine A | **Brand Executor** | Mark payoffs recover limited momentum |
| `DRAFT-SK-PAL-AVG-08` | Engine B | **Dawn Momentum** | Distinct weapon actions build brief offensive rhythm |
| `DRAFT-SK-PAL-AVG-09` | Defense A | **Courageous Guard** | After strong incoming damage enhance the next existing hit |
| `DRAFT-SK-PAL-AVG-10` | Defense B | **Cleansing Aegis** | Defensive stats blunt status spikes |
| `DRAFT-SK-PAL-AVG-11` | Keystone | **Adjudicator** | Physical and Holy weapon payoffs cooperate without extra casts |
| `DRAFT-SK-PAL-AVG-12` | Capstone | **Final Verdict** | Weapon finishing actions become decisive Holy punishments |

### Templar — hybrid Holy/Fire, tome and shield, sustained recovery

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-PAL-TMP-01` | Foundation A | **Vigilant Scripture** | Spell tags improve baseline accuracy of output |
| `DRAFT-SK-PAL-TMP-02` | Foundation B | **Tome Litany** | Tomes strengthen Holy or Fire existing spells |
| `DRAFT-SK-PAL-TMP-03` | Foundation C | **Shield Catechism** | Shield grants longer protective windows |
| `DRAFT-SK-PAL-TMP-04` | Branch A | **Sanctified Pattern** | Alternate damage and ward effects for efficiency |
| `DRAFT-SK-PAL-TMP-05` | Branch B | **Ember Benediction** | Burn enhances Holy payoff hits |
| `DRAFT-SK-PAL-TMP-06` | Branch C | **Healing Measure** | Recovery affixes improve sustained output uptime |
| `DRAFT-SK-PAL-TMP-07` | Engine A | **Seal Weaver** | Existing Mark can amplify ward quality |
| `DRAFT-SK-PAL-TMP-08` | Engine B | **Twin Benediction** | Two present elements collaborate without free elements |
| `DRAFT-SK-PAL-TMP-09` | Defense A | **Bastion of Faith** | Resistance contributes to reliable defense |
| `DRAFT-SK-PAL-TMP-10` | Defense B | **Restorative Rite** | Excess recovery becomes bounded Guard |
| `DRAFT-SK-PAL-TMP-11` | Keystone | **Sanctuary Doctrine** | Balanced weapon cycles outperform one-stat stacking |
| `DRAFT-SK-PAL-TMP-12` | Capstone | **Eternal Vigil** | Sustained warding preserves damage during long fights |

### Justicar — armor, shields, Guard, defensive payoffs

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-PAL-JST-01` | Foundation A | **Iron Psalm** | Armor adds tangible physical resilience |
| `DRAFT-SK-PAL-JST-02` | Foundation B | **Shieldmaster** | Shield quality shapes Guard strength |
| `DRAFT-SK-PAL-JST-03` | Foundation C | **Patient Justice** | Slower weapon cycles reward survival |
| `DRAFT-SK-PAL-JST-04` | Branch A | **Retaliatory Weight** | A blocked hit amplifies the next existing action |
| `DRAFT-SK-PAL-JST-05` | Branch B | **Aegis Geometry** | Guard and resistance affixes complement |
| `DRAFT-SK-PAL-JST-06` | Branch C | **Stalwart Refusal** | Life investment steadies chip-damage pressure |
| `DRAFT-SK-PAL-JST-07` | Engine A | **Sentence of Steel** | Exposed enemies invite defensive-counter payoffs |
| `DRAFT-SK-PAL-JST-08` | Engine B | **Guardian Cycle** | Guard builders and spenders work together |
| `DRAFT-SK-PAL-JST-09` | Defense A | **Vigilant Blood** | Crit reduction improves survival consistency |
| `DRAFT-SK-PAL-JST-10` | Defense B | **Resilience in Ash** | Fire defense supports controlled counterpressure |
| `DRAFT-SK-PAL-JST-11` | Keystone | **Unbroken Edict** | Retaliation effects never chain into themselves |
| `DRAFT-SK-PAL-JST-12` | Capstone | **The Last Bastion** | Shield/heavy weapons survive long while applying counterdamage |

## Warrior

### Protection — armor, physical Guard and reprisal

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-WAR-PRO-01` | Foundation A | **Plate Discipline** | Armor-rich gear gains higher defensive efficiency |
| `DRAFT-SK-WAR-PRO-02` | Foundation B | **Stout Grip** | Shield and 1H improve protection identity |
| `DRAFT-SK-WAR-PRO-03` | Foundation C | **Battle Poise** | Slower weapon pace allows defensive consistency |
| `DRAFT-SK-WAR-PRO-04` | Branch A | **Brace for Impact** | Guard is readied for strong incoming strikes |
| `DRAFT-SK-WAR-PRO-05` | Branch B | **Steel Answer** | Guard use strengthens next existing weapon hit |
| `DRAFT-SK-WAR-PRO-06` | Branch C | **Hold the Line** | Reliable protection reduces repeat-death spirals |
| `DRAFT-SK-WAR-PRO-07` | Engine A | **Reprisal Engine** | Mitigated damage improves a planned physical payoff |
| `DRAFT-SK-WAR-PRO-08` | Engine B | **Fortress Rhythm** | Alternate offense and Guard within the same weapon |
| `DRAFT-SK-WAR-PRO-09` | Defense A | **Thickened Skin** | Smooth sustained physical chip damage |
| `DRAFT-SK-WAR-PRO-10` | Defense B | **Crackproof** | Crit damage reduction becomes more valuable |
| `DRAFT-SK-WAR-PRO-11` | Keystone | **Iron Protocol** | Slow survival cycles regain bounded momentum |
| `DRAFT-SK-WAR-PRO-12` | Capstone | **Citadel** | Extended survival yields consistent boss output without immunity |

### Savage — Bleed, Exposed, aggression and survival tradeoffs

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-WAR-SAV-01` | Foundation A | **Rough Edge** | Physical hits pressure enemy protection |
| `DRAFT-SK-WAR-SAV-02` | Foundation B | **Open the Wound** | Existing Bleed buildup becomes reliable |
| `DRAFT-SK-WAR-SAV-03` | Foundation C | **Wild Axes** | Dual-wield and axes offer different aggression advantages |
| `DRAFT-SK-WAR-SAV-04` | Branch A | **Rending Path** | Exposed amplifies Bleed payoffs |
| `DRAFT-SK-WAR-SAV-05` | Branch B | **Blood Scent** | Accumulated Bleed makes finishers matter |
| `DRAFT-SK-WAR-SAV-06` | Branch C | **Unarmored Fury** | Offense gains have real defense costs |
| `DRAFT-SK-WAR-SAV-07` | Engine A | **Hemorrhage Cycle** | Bleed setup leads to measured payoff |
| `DRAFT-SK-WAR-SAV-08` | Engine B | **Relentless Split** | Heavy cleave becomes bounded boss impacts |
| `DRAFT-SK-WAR-SAV-09` | Defense A | **Hunter's Vitality** | Real hits drive capped recovery |
| `DRAFT-SK-WAR-SAV-10` | Defense B | **Hard Bargain** | Low-life damage helps but cannot guarantee survival |
| `DRAFT-SK-WAR-SAV-11` | Keystone | **Ravager's Calculus** | Bleed duration and hit power present a genuine choice |
| `DRAFT-SK-WAR-SAV-12` | Capstone | **Red Reckoning** | Wound-focused weapons deliver decisive executions |

### Fury — Tempo, fast weapon cadence, Fire

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-WAR-FUR-01` | Foundation A | **Rapid Hands** | Attack speed improves real cadence within caps |
| `DRAFT-SK-WAR-FUR-02` | Foundation B | **Battle Spark** | Physical hits favor existing Fire weapon tags |
| `DRAFT-SK-WAR-FUR-03` | Foundation C | **Dual Rhythm** | Off-hand stats improve main-hand timing only |
| `DRAFT-SK-WAR-FUR-04` | Branch A | **Rising Heat** | Distinct actions build Tempo |
| `DRAFT-SK-WAR-FUR-05` | Branch B | **Frenzied Precision** | Critical hits complement speed with proc limits |
| `DRAFT-SK-WAR-FUR-06` | Branch C | **Scalding Tempo** | Burn improves fast finishing hits |
| `DRAFT-SK-WAR-FUR-07` | Engine A | **Overdrive Cycle** | Tempo setup feeds weapon payoff actions |
| `DRAFT-SK-WAR-FUR-08` | Engine B | **Flame Rush** | Faster offense trades protection |
| `DRAFT-SK-WAR-FUR-09` | Defense A | **Hotblood Defense** | Maintained rhythm earns a little Guard |
| `DRAFT-SK-WAR-FUR-10` | Defense B | **Second Wind** | Repeat hits give limited recovery |
| `DRAFT-SK-WAR-FUR-11` | Keystone | **Fury's Measure** | Tempo has a ceiling and no recursive generation |
| `DRAFT-SK-WAR-FUR-12` | Capstone | **Unchained Engine** | Rapid physical/Fire weapons sustain aggressive bursts |

## Rogue

### Subtlety — marks, shadow ranged pressure, Bleed

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ROG-SUB-01` | Foundation A | **Silent Aim** | Mark improves precision on weapon actions |
| `DRAFT-SK-ROG-SUB-02` | Foundation B | **Veil Step** | Shadow-tagged attacks give a brief defensive benefit |
| `DRAFT-SK-ROG-SUB-03` | Foundation C | **Thin Edge** | Bleed setup still deals useful base damage |
| `DRAFT-SK-ROG-SUB-04` | Branch A | **Hidden Thread** | Mark improves existing wound pressure |
| `DRAFT-SK-ROG-SUB-05` | Branch B | **Night Courier** | Projectiles and fast 1H share precision paths |
| `DRAFT-SK-ROG-SUB-06` | Branch C | **Patient Sting** | Deliberate shots reward timing |
| `DRAFT-SK-ROG-SUB-07` | Engine A | **Veiled Sequence** | Mark or Curse feeds existing finishing hits |
| `DRAFT-SK-ROG-SUB-08` | Engine B | **Sniper's Shadow** | Shadow payoffs benefit from ranged traits |
| `DRAFT-SK-ROG-SUB-09` | Defense A | **Ghost Defense** | Dodge remains bounded and unreliable alone |
| `DRAFT-SK-ROG-SUB-10` | Defense B | **Soft Footing** | Avoidance complements armor and life |
| `DRAFT-SK-ROG-SUB-11` | Keystone | **False Opening** | An avoided hit can improve one later weapon action |
| `DRAFT-SK-ROG-SUB-12` | Capstone | **Quietus** | Marks and wound exploitation define precise burst |

### Assassin — daggers, critical windows, dual-wield

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ROG-ASN-01` | Foundation A | **Knife Language** | Fast physical attacks reward accurate timing |
| `DRAFT-SK-ROG-ASN-02` | Foundation B | **Twin Hand Trickery** | Dual-wield enhances main-hand stats, not casts |
| `DRAFT-SK-ROG-ASN-03` | Foundation C | **Fatal Timing** | Crit chance interacts with actual cadence |
| `DRAFT-SK-ROG-ASN-04` | Branch A | **Exposed Artery** | Bleed and Exposed reinforce damage |
| `DRAFT-SK-ROG-ASN-05` | Branch B | **Hidden Edge** | Existing Shadow tags improve ambush |
| `DRAFT-SK-ROG-ASN-06` | Branch C | **Cold Nerve** | Burst windows include survival tradeoffs |
| `DRAFT-SK-ROG-ASN-07` | Engine A | **Execution Rhythm** | Distinct actions sharpen the finishing hit |
| `DRAFT-SK-ROG-ASN-08` | Engine B | **Cut and Return** | Payoff cycles refund bounded Tempo |
| `DRAFT-SK-ROG-ASN-09` | Defense A | **Slip Through** | Dodge has diminishing defensive value |
| `DRAFT-SK-ROG-ASN-10` | Defense B | **Poisoned Escape** | Poison pressure supports modest sustain |
| `DRAFT-SK-ROG-ASN-11` | Keystone | **Perfect Ambush** | Crit payoff reads current Mark or Curse |
| `DRAFT-SK-ROG-ASN-12` | Capstone | **Last Whisper** | High-precision burst sacrifices sustained stability |

### Alchemist — Poison, Shadow, physical-to-toxic conversion

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ROG-ALC-01` | Foundation A | **Reagent Knowledge** | Poison application improves consistency |
| `DRAFT-SK-ROG-ALC-02` | Foundation B | **Glass Vials** | Wand/tome modifiers improve spells not casts |
| `DRAFT-SK-ROG-ALC-03` | Foundation C | **Corrosive Edge** | Physical hits enhance existing Poison |
| `DRAFT-SK-ROG-ALC-04` | Branch A | **Slow Distillation** | Long Poison duration trades burst value |
| `DRAFT-SK-ROG-ALC-05` | Branch B | **Caustic Recipe** | Exposed adds venom vulnerability |
| `DRAFT-SK-ROG-ALC-06` | Branch C | **Venom Measure** | Different actions build pressure efficiently |
| `DRAFT-SK-ROG-ALC-07` | Engine A | **Volatile Mixture** | Finisher converts some Poison into direct damage |
| `DRAFT-SK-ROG-ALC-08` | Engine B | **Black Solvent** | Shadow/Poison tags cooperate with Curse |
| `DRAFT-SK-ROG-ALC-09` | Defense A | **Antidote Craft** | Resistance sustains longer toxin fights |
| `DRAFT-SK-ROG-ALC-10` | Defense B | **Controlled Reaction** | Reactions never chain recursively |
| `DRAFT-SK-ROG-ALC-11` | Keystone | **Perfect Formula** | Mark and Poison create reliable payoff choices |
| `DRAFT-SK-ROG-ALC-12` | Capstone | **Grand Distillation** | Choose durable poison damage or sharp detonations |

## Mage

### Pyro — Burn, direct Fire, timed detonation

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-MAG-PYR-01` | Foundation A | **Tinder Studies** | Fire weapons gain steady damage value |
| `DRAFT-SK-MAG-PYR-02` | Foundation B | **Clear Flame** | Spell-tagged Fire sustains offense |
| `DRAFT-SK-MAG-PYR-03` | Foundation C | **Furnace Breath** | Burn duration rises within caps |
| `DRAFT-SK-MAG-PYR-04` | Branch A | **Flare Logic** | Setup improves the next existing finisher |
| `DRAFT-SK-MAG-PYR-05` | Branch B | **Hot Core** | Direct Fire and Burn share affix investments |
| `DRAFT-SK-MAG-PYR-06` | Branch C | **Ash Memory** | Burned enemies retain a brief weakness |
| `DRAFT-SK-MAG-PYR-07` | Engine A | **Controlled Detonation** | Finishers convert Burn buildup into direct impact |
| `DRAFT-SK-MAG-PYR-08` | Engine B | **Kindling Cycle** | Refresh is useful without multiplier loops |
| `DRAFT-SK-MAG-PYR-09` | Defense A | **Cinder Ward** | Fire resistance supports survival |
| `DRAFT-SK-MAG-PYR-10` | Defense B | **Heat Sink** | Defensive affixes prevent flame glass-cannons |
| `DRAFT-SK-MAG-PYR-11` | Keystone | **Wildfire Theorem** | Hit-focused and Burn-focused paths compete |
| `DRAFT-SK-MAG-PYR-12` | Capstone | **Heart of Embers** | Planned Fire payoffs become formidable without infinite explosions |

### Frost — Chill, reliable protection, shatter-like payoffs

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-MAG-FRO-01` | Foundation A | **Ice Pattern** | Frost weapons gain dependable effect rate |
| `DRAFT-SK-MAG-FRO-02` | Foundation B | **Deep Chill** | Chill pressure remains relevant on tough targets |
| `DRAFT-SK-MAG-FRO-03` | Foundation C | **Crystal Script** | Spell effects gain Frost scaling |
| `DRAFT-SK-MAG-FRO-04` | Branch A | **Brittle Window** | Chill opens a short direct-damage opportunity |
| `DRAFT-SK-MAG-FRO-05` | Branch B | **Winter Calculation** | Slow and fast weapons gain different Chill uses |
| `DRAFT-SK-MAG-FRO-06` | Branch C | **Rime Sequence** | Setup and payoff timing is readable |
| `DRAFT-SK-MAG-FRO-07` | Engine A | **Glacier Rhythm** | Chilled targets reward deliberate spell strikes |
| `DRAFT-SK-MAG-FRO-08` | Engine B | **Cold Snap Accounting** | Control duration cannot freeze a boss forever |
| `DRAFT-SK-MAG-FRO-09` | Defense A | **Frozen Mantle** | Cold protection benefits defense affixes |
| `DRAFT-SK-MAG-FRO-10` | Defense B | **Calm Under Ice** | Recovery helps during long threats |
| `DRAFT-SK-MAG-FRO-11` | Keystone | **Absolute Zero Theory** | Chill favors damage tradeoffs, not permanent stun |
| `DRAFT-SK-MAG-FRO-12` | Capstone | **Winter Crown** | Frost survival and patient burst form a reliable loop |

### Lightning — Shock, charge banking, bounded chaining

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-MAG-LIG-01` | Foundation A | **Conductivity** | Lightning weapons build Shock consistently |
| `DRAFT-SK-MAG-LIG-02` | Foundation B | **Quick Current** | Faster actions bank charge under caps |
| `DRAFT-SK-MAG-LIG-03` | Foundation C | **Arc Mathematics** | Physical/Spell Lightning share relevant scaling |
| `DRAFT-SK-MAG-LIG-04` | Branch A | **Static Link** | Shock enhances the next scheduled finisher |
| `DRAFT-SK-MAG-LIG-05` | Branch B | **Charge Ledger** | Stored charges never self-generate |
| `DRAFT-SK-MAG-LIG-06` | Branch C | **Thunder Timing** | Delayed payoff cycles matter |
| `DRAFT-SK-MAG-LIG-07` | Engine A | **Discharge Logic** | Weapon payoff spends charge for burst |
| `DRAFT-SK-MAG-LIG-08` | Engine B | **Branching Current** | Chain visuals remain bounded child effects |
| `DRAFT-SK-MAG-LIG-09` | Defense A | **Insulated Mind** | Resistance supports defensive reliability |
| `DRAFT-SK-MAG-LIG-10` | Defense B | **Storm Shelter** | Ward and life support high offense |
| `DRAFT-SK-MAG-LIG-11` | Keystone | **Perfect Circuit** | Charge and Shock interact once per action |
| `DRAFT-SK-MAG-LIG-12` | Capstone | **Eye of the Tempest** | Stored pressure becomes one deliberate Lightning burst |

## Archer

### Marksman — longbow precision, Mark and execution

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ARC-MRK-01` | Foundation A | **Steady Draw** | Slower projectiles gain consistent direct damage |
| `DRAFT-SK-ARC-MRK-02` | Foundation B | **Calibrated Sight** | Mark setup becomes reliable |
| `DRAFT-SK-ARC-MRK-03` | Foundation C | **Long View** | Longer cycles offer proportionate payoffs |
| `DRAFT-SK-ARC-MRK-04` | Branch A | **Piercing Practice** | Exposed and Mark raise physical damage |
| `DRAFT-SK-ARC-MRK-05` | Branch B | **Single Target Doctrine** | Boss focus outranks visual multi-target fireworks |
| `DRAFT-SK-ARC-MRK-06` | Branch C | **Full Draw** | Large projectile crits scale with limits |
| `DRAFT-SK-ARC-MRK-07` | Engine A | **Kill Window** | Mark plus low life improves existing finishers |
| `DRAFT-SK-ARC-MRK-08` | Engine B | **Measured Release** | Aiming and payoff have readable timing |
| `DRAFT-SK-ARC-MRK-09` | Defense A | **Watchful Ground** | Survivability remains while aiming |
| `DRAFT-SK-ARC-MRK-10` | Defense B | **Hardened Strings** | Gear keeps projectile uptime under pressure |
| `DRAFT-SK-ARC-MRK-11` | Keystone | **Sniper's Proof** | Marks matter even when a boss resists control |
| `DRAFT-SK-ARC-MRK-12` | Capstone | **One Clean Shot** | Deliberate mark execution rewards preparation |

### Ranger — shortbow, quiver, Tempo, swift defense

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ARC-RNG-01` | Foundation A | **Fleet Fingers** | Rapid bows benefit from speed investment |
| `DRAFT-SK-ARC-RNG-02` | Foundation B | **Quiver Discipline** | Quivers modify the active bow only |
| `DRAFT-SK-ARC-RNG-03` | Foundation C | **Briar Sense** | Bleed/Poison tags support ranged damage |
| `DRAFT-SK-ARC-RNG-04` | Branch A | **Thread the Needles** | Fast attacks build bounded Tempo |
| `DRAFT-SK-ARC-RNG-05` | Branch B | **Second Feather** | Volleys are bounded child hits |
| `DRAFT-SK-ARC-RNG-06` | Branch C | **Rapid Recovery** | Repeat attacks give modest sustain |
| `DRAFT-SK-ARC-RNG-07` | Engine A | **Arrow Rhythm** | Setup volleys feed existing payoffs |
| `DRAFT-SK-ARC-RNG-08` | Engine B | **Running Pressure** | Mobility fantasy translates into defense windows |
| `DRAFT-SK-ARC-RNG-09` | Defense A | **Windguard** | Dodge is capped and paired with durability |
| `DRAFT-SK-ARC-RNG-10` | Defense B | **Light Pack** | Armor and life remain valuable |
| `DRAFT-SK-ARC-RNG-11` | Keystone | **Endless Fletching** | Tempo overflow cannot generate free casts |
| `DRAFT-SK-ARC-RNG-12` | Capstone | **Thousand Cuts** | Many small shots ramp into a deliberate finish |

### Tracker — prey knowledge, marks, targeted farming

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-ARC-TRK-01` | Foundation A | **Monster Lore** | Reveal enemy family traits for planning |
| `DRAFT-SK-ARC-TRK-02` | Foundation B | **Scent of Blood** | Bleed weapons gain persistent pressure |
| `DRAFT-SK-ARC-TRK-03` | Foundation C | **Trail Mark** | Marked targets take reliable sustained damage |
| `DRAFT-SK-ARC-TRK-04` | Branch A | **Focused Harvest** | Item-find tradeoffs help targeted hunting, not guaranteed loot |
| `DRAFT-SK-ARC-TRK-05` | Branch B | **Anatomical Study** | Physical weak-point payoffs use Mark |
| `DRAFT-SK-ARC-TRK-06` | Branch C | **Huntcraft** | Enemy family knowledge clarifies imprint compatibility |
| `DRAFT-SK-ARC-TRK-07` | Engine A | **Quarry Cycle** | Repeated wins improve combat consistency, not drop tables |
| `DRAFT-SK-ARC-TRK-08` | Engine B | **Persistent Quarry** | Marks stay useful in long boss fights |
| `DRAFT-SK-ARC-TRK-09` | Defense A | **Field Dressing** | Sustain makes unattended hunting safer |
| `DRAFT-SK-ARC-TRK-10` | Defense B | **Cold Camp** | Recovery never erases boss attempt costs |
| `DRAFT-SK-ARC-TRK-11` | Keystone | **Master of Signs** | Known weaknesses reward equipment selection |
| `DRAFT-SK-ARC-TRK-12` | Capstone | **Prime Hunter** | Prepared equipment answers target prey better than automatic loot inflation |

## Shaman

### Nature — sustain, Guard, physical and Fire

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-SHM-NAT-01` | Foundation A | **Living Root** | Max-life gear increases sustain consistency |
| `DRAFT-SK-SHM-NAT-02` | Foundation B | **Earthhand** | Physical attacks enhance short Guard windows |
| `DRAFT-SK-SHM-NAT-03` | Foundation C | **Ember Hearth** | Existing Fire tags support recovery |
| `DRAFT-SK-SHM-NAT-04` | Branch A | **Seeded Rhythm** | Warding and damage actions cooperate |
| `DRAFT-SK-SHM-NAT-05` | Branch B | **Green Renewal** | Recovery increases but cannot loop forever |
| `DRAFT-SK-SHM-NAT-06` | Branch C | **Wild Communion** | Tome or shield modifiers strengthen durability |
| `DRAFT-SK-SHM-NAT-07` | Engine A | **Rootbound Engine** | Weapon damage uptime creates measured protection |
| `DRAFT-SK-SHM-NAT-08` | Engine B | **Harvest Pulse** | Physical/Fire weapons need no Poison to function |
| `DRAFT-SK-SHM-NAT-09` | Defense A | **Barkskin Form** | Armor improves sustained survival |
| `DRAFT-SK-SHM-NAT-10` | Defense B | **Rain of Mercy** | Crit reduction handles burst risk |
| `DRAFT-SK-SHM-NAT-11` | Keystone | **Living Covenant** | Defensive effects share a cap budget |
| `DRAFT-SK-SHM-NAT-12` | Capstone | **Ancient Grove** | Long survival supports better existing weapon actions |

### Storm — Lightning, Shock, melee/spell charge

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-SHM-STM-01` | Foundation A | **Storm Sense** | Lightning damage builds Shock reliability |
| `DRAFT-SK-SHM-STM-02` | Foundation B | **Thunder Scripture** | Staff or wand paths enrich spells |
| `DRAFT-SK-SHM-STM-03` | Foundation C | **Storm Grip** | One-hand weapons deliver Shock via hit tags |
| `DRAFT-SK-SHM-STM-04` | Branch A | **Charged Spirit** | Shocked enemies suffer stronger hits |
| `DRAFT-SK-SHM-STM-05` | Branch B | **Sky Pulse** | Tempo and charge cooperate within caps |
| `DRAFT-SK-SHM-STM-06` | Branch C | **Cloud Thread** | Existing Fire/Lightning elements may synergize |
| `DRAFT-SK-SHM-STM-07` | Engine A | **Rolling Thunder** | Weapon finisher converts charge to damage |
| `DRAFT-SK-SHM-STM-08` | Engine B | **Eye of Storm** | Shock payoff timing cannot loop endlessly |
| `DRAFT-SK-SHM-STM-09` | Defense A | **Grounded Body** | Lightning resistance indirectly supports uptime |
| `DRAFT-SK-SHM-STM-10` | Defense B | **Static Ward** | Defensive weapon abilities create limited protection |
| `DRAFT-SK-SHM-STM-11` | Keystone | **Sky Covenant** | Crit and charge have per-action limits |
| `DRAFT-SK-SHM-STM-12` | Capstone | **Stormheart** | One active weapon rotation yields heavy Lightning payoffs |

### Primal — fast dual wield, physical, Fire, Lightning

| Draft ID | Layer | Node | Proposed mechanical identity |
| --- | --- | --- | --- |
| `DRAFT-SK-SHM-PRI-01` | Foundation A | **Wild Grip** | Dual 1H weapons provide stats, never two rotations |
| `DRAFT-SK-SHM-PRI-02` | Foundation B | **Feral Pace** | Fast main-hand attacks build Tempo |
| `DRAFT-SK-SHM-PRI-03` | Foundation C | **Claw and Flame** | Present physical and Fire tags cooperate |
| `DRAFT-SK-SHM-PRI-04` | Branch A | **Stormblood** | Lightning/Physical payoffs share chosen scaling |
| `DRAFT-SK-SHM-PRI-05` | Branch B | **Predatory Focus** | Mark or Exposed supports consecutive hits |
| `DRAFT-SK-SHM-PRI-06` | Branch C | **Heat in Motion** | Burn pressure rewards faster timing |
| `DRAFT-SK-SHM-PRI-07` | Engine A | **Primal Loop** | Main-hand sequences benefit from off-hand modifiers |
| `DRAFT-SK-SHM-PRI-08` | Engine B | **Bestial Resolve** | Defensive fallback keeps aggression viable |
| `DRAFT-SK-SHM-PRI-09` | Defense A | **Stonehide** | Armor remains essential to fast melee |
| `DRAFT-SK-SHM-PRI-10` | Defense B | **Heartfire** | Actual weapon hits give bounded recovery |
| `DRAFT-SK-SHM-PRI-11` | Keystone | **Untamed Doctrine** | Hybrid perks never grant absent element tags |
| `DRAFT-SK-SHM-PRI-12` | Capstone | **First Thunder** | Physical, Fire and Lightning form one fast rotation |

## Cross-tree and item examples (still proposals)

- **Avenger + Justicar**: an Avenger using a Cinderbreaker mace can invest in Holy and Fire payoffs while borrowing armor/Guard from Justicar. The main-hand still casts Cinderbreaker moves only.
- **Warrior Fury + Protection**: a fast 1H sword builds Tempo while protection nodes improve boss survivability. Off-hand shield provides modifiers but no extra casts.
- **Rogue Assassin + Alchemist**: a Plague Needle dagger benefits from both rapid strikes and Poison pressure, choosing sustained DoT or burst through weapon abilities.
- **Mage Lightning + Frost**: a Thunderglass wand stores Shock charge; Frost branch defense can be useful even without any Frost-tagged damage, but Frost-only offensive nodes should visibly say 'not applicable'.
- **Archer Ranger + Tracker**: a Thornrunner shortbow can combine rapid Bleed shots with prey knowledge and survival, without free guaranteed drops.
- **Shaman Primal + Nature**: a dual-1H fast physical/Fire weapon uses bounded Tempo and life sustain, not a second off-hand rotation.

## Mechanic guardrails

1. A tree node may transform, scale, protect, condition, refresh or convert an existing weapon action; it must not schedule an unbudgeted extra independent ability.
2. Broad weapon-neutral nodes remain useful on many loadouts; niche weapon-tag nodes are optional and shown as compatible/incompatible to prevent trap allocations.
3. Guard, recovery, armor, elemental resistance and crit reduction are all defensive solutions. No tree should become mandatory for Wisp or Bellkeeper.
4. Proc chains, status feedback loops and automatic 'retaliation' triggers must have explicit event caps to remain reproducible offline.
5. Bosses resist control without nullifying entire Frost, Mark, Bleed or Poison builds; exact caps and coefficients are for a later balance pass.
6. Leave `GS-UI-CMP-019` production art as **DESIGN** until actual node ranks and unlocked-state variants are approved.

## Source alignment and caveats

- Current class names and most specialization names come from `docs/ITEM-SYSTEM-DIRECTION.md`: Paladin, Warrior, Rogue, Mage, Archer, Shaman.
- Archer **Marksman/Ranger/Tracker** and Shaman **Primal** remain *proposed names* in the existing paper design.
- The detailed tree nodes and even the 12-node blueprint are **creative proposals** in this document, not verified game data.
- For proposed status definitions, balancing constraints and cross-system rules see `docs/design/COMBAT-BUILD-SYSTEM-V0.md`. For proposed named active abilities see `docs/design/WEAPON-ABILITIES-CATALOG-V0.md`.

