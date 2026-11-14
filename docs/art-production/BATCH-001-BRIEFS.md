# Godscar B001: eight independent artwork briefs

**2026-11-14 | STATUS: BLOCKED_SOURCE_MISSING | 0 of 8 native masters created.**
**Repo branch:** art/individual-masters-production-v1
**Inventory:** assets/art-production/catalogue.json

Existing source: current premium art reference at assets/classes/premium-specs/GS-CLS-SPEC-001_avenger-256.webp, a 256px WebP PREVIEW of the Avenger radiant hammer, not a verified master.

## The identical production lock to use with EVERY separate generation

Create ONE genuinely independently rendered 1024×1024 or larger high-quality game illustration, on a TRUE TRANSPARENT alpha background, only the requested asset subject. Godscar dark elegant painterly non-photoreal ARPG style: forged/hammered metal textures, strongly directional light, antique gold accents only when appropriate, crisp high-contrast focal form, restrained surface noise and rich hand-painted brush detail. Strong silhouette and icon legibility at 36px. Keep 6–10% empty transparent border. Exactly one requested asset, never a poster, sheet, frame, icon grid or collection. No labels, no lettering, no graphic UI border, no watermark, no neighboring objects. Never take a small crop and upscale it as a master. Reject any output that violates these conditions.

## Separate prompts, one output file per ID

### B001-01: DRAFT-ART-WA-MAC-01-B | Ember Rupture
Source DRAFT-WA-MAC-01 / Cinderbreaker mace, setup B. **CONCEPT / PENDING APPROVAL.** A diagonal heavy blunt weapon impact cracks blackened iron armor in one precise focal point. Show a deep concave dent plus branching molten-orange fissures exposing the target's broken defense and applying combustion. A partial battered mace head is allowed, but no entire weapon item or fireball. Sooted dark steel, old gold abrasion, intense white-orange fracture center, a very small number of embers. At phone size must read "heavy armor fracture plus burn", not ordinary flame.

### B001-02: GS-CLS-SPEC-003 | Justicar primary emblem
Confirmed Paladin Justicar name, proposed mechanical identity. **ART UNAPPROVED.** One forged upright balance scale in gunmetal and antique gold, with thick readable horizontal arm, one pan carrying a small defensive shield, the other a broken sword fragment. Righteous but weighty and protective, centered strong triangular silhouette. Ivory-gold understated inner light. Detailed handmade metal engraving only at major structural joints, no floating floral clutter. At 36px the beam and two pans must remain unmistakable. Do not resemble Avenger's hammer.

### B001-03: GS-ITM-WPN-003 | One-handed base mace
Frozen weapon-category asset ID; actual art not yet approved. One physically plausible **full standalone one-handed mace** diagonally from lower left to upper right, six thick iron flanges around a compact heavy blunt head, hammered bronze collar, short leather wrapped grip and iron pommel. Complete object, breathing margin. Distinct from fantasy axe/hammer and also distinct from Avenger's sacred prototype. Steel pits, modest patina, coarse leather, no magical aura. This is plain base inventory equipment.

### B001-04: DRAFT-ART-WA-DAG-01-B | Open Vein
Source DRAFT-WA-DAG-01 / Bloodletter setup B, **CONCEPT / PENDING APPROVAL.** A fast hooked dagger tip slicing one sharply defined diagonal incision through dark leather or hide, with one taut narrow blood ribbon trailing along the path and two droplets. Emphasize fine point, violent speed and bleeding precision, not a red fireball or gigantic gore splash. Sharp silver steel and restrained crimson on charcoal. At 36px must clearly read as quick cut plus bleed.

### B001-05: DRAFT-ART-WA-WND-03-A | Volt Dart
Source DRAFT-WA-WND-03 / Thunderglass A, **CONCEPT / PENDING APPROVAL.** One forward-flying slender glasslike electrical dart with a clear crystalline blue point leading, branching yellow-white electrical streamers **behind** it. Dynamic diagonal propulsion, translucent turquoise facet highlights and a sharply controlled core; not a circular spell blast or random giant lightning bolt. At 36px pointed projectile and crackling tail both visible.

### B001-06: DRAFT-ART-WA-GMC-04-B | Stone Ward
Source DRAFT-WA-GMC-04 / Warden Stone defensive setup B, **CONCEPT / PENDING APPROVAL.** A single massive vertically planted rough-hewn stone slab with bold worn bronze clamps fastening three large granite fractures. Quiet amber protective energy glows **within** cracks, not bursting outward. Heavy static silhouette: this is Guard, not an attack. Slate-gray granite, bronze and warm low-intensity gold. At 36px clearly an immovable physical barrier.

### B001-07: DRAFT-ART-IM-GEN-02 | Ward Sigil
Source DRAFT-IM-GEN-02 proposed imprint; **CONCEPT / PENDING APPROVAL.** One compact floating shield-shaped runic device of interlocking short antique-gold plates forming a clearly enclosed shield profile. Three offset shallow arc bands reinforce the protective ward with one ivory central light. Three-quarter perspective; negative space deliberate. This is a brief magical protection pulse, **visually distinct** from Stone Ward's literal slab. Not offensive, fiery or lightning-based.

### B001-08: GS-ITM-WPN-012 | Spell Tome base inventory item
Frozen equipment-category ID. One closed substantial, modestly crafted spell tome at three-quarter angle; aged oxblood leather cover, four chipped iron corner braces, thick visible parchment page block, practical brass clasp, single subtle embossed geometric star knot. Full item in frame, thick and believable; no open floating pages, no wildly magical beams. A tome is a spell-modifying off-hand, never a second independent cast. At 36px book cover, page edges and clasp must remain distinct.

## QA and export requirements

1. One isolated native >=1024px master per request, true alpha for each icon, no other asset in image.
2. Strict reject: clipped silhouette, accidental frame, duplicate composition, neighboring art, grid/crop, poster, absent alpha, generic effect blur, illegible 36px.
3. Do not save failed art as completed. Keep master under the existing assets/art-production/<category>/<ID>/ path. Make 512, 256, 128, 64, 48 and 36px WebPs from the native master only.
4. Use scripts/art_asset_pipeline.py, which imports one independently-created master at a time and automatically leaves its status IN_REVIEW, not APPROVED. Run the audit and inspect 36/48/64 visuals in the gallery before an approval decision.
5. Commit original, exports, source provenance, manifest changes and gallery rebuild together after each successful production batch.
6. Do not integrate into the mock Fight UI, merge, deploy, publish or touch the simulation.

## Truthful execution checkpoint

**Generated original masters: 0.** The native image renderer returned a non-retryable missing-target error, and the connected OpenArt workspace has zero generation credits. Prompts and catalogue entries above are production preparation ONLY, not artwork.
