package com.example.data

import com.example.R
import com.example.model.CastAbility
import com.example.model.DamageBreakdownEntry
import com.example.model.DamageSource
import com.example.model.Enemy
import com.example.model.EquipmentSlotType
import com.example.model.FeedEvent
import com.example.model.FeedEventType
import com.example.model.GearItem
import com.example.model.ItemRarity
import com.example.model.MaterialEntry
import com.example.model.RecapLootItem
import com.example.model.SessionMetrics
import com.example.model.Zone
import com.example.ui.theme.GodscarColors

object GodscarMockData {
    val zones: List<Zone> = listOf(
        Zone(
            name = "Gallowsfen · The Sunken Road",
            biomeArtResId = R.drawable.img_biome_gallowsfen,
            enemies = listOf(
                Enemy(
                    id = "gravejaw",
                    zone = "Gallowsfen · The Sunken Road",
                    name = "Gravejaw Stalker",
                    kind = "BLIGHTED · BEAST",
                    status = "Blighted",
                    damageType = DamageSource.PHYSICAL,
                    weaknesses = listOf("Fire", "Holy"),
                    drop = "Grave Resin",
                    locked = false,
                    artResId = R.drawable.img_enemy_gravejaw
                ),
                Enemy(
                    id = "mirehexer",
                    zone = "Gallowsfen · The Sunken Road",
                    name = "Mire Hexer",
                    kind = "CORRUPTED · CASTER",
                    status = "Cursed",
                    damageType = DamageSource.SHADOW,
                    weaknesses = listOf("Holy", "Arcane"),
                    drop = "Witchglass Dust",
                    locked = false
                ),
                Enemy(
                    id = "sallow",
                    zone = "Gallowsfen · The Sunken Road",
                    name = "Sallow Wretch",
                    kind = "UNDEAD · COMMON",
                    status = "Rotting",
                    damageType = DamageSource.PHYSICAL,
                    weaknesses = listOf("Fire"),
                    drop = "Pallid Thread",
                    locked = false
                ),
                Enemy(
                    id = "lantern",
                    zone = "Gallowsfen · The Sunken Road",
                    name = "Lantern Widow",
                    kind = "BLIGHTED · ELITE",
                    status = "Burning",
                    damageType = DamageSource.FIRE,
                    weaknesses = listOf("Frost"),
                    drop = "Ashen Filament",
                    locked = false
                ),
                Enemy(
                    id = "deepbell",
                    zone = "Gallowsfen · The Sunken Road",
                    name = "Deep Bell Abomination",
                    kind = "BOSS · LOCKED",
                    status = "Resistant",
                    damageType = DamageSource.HOLY,
                    weaknesses = listOf("Bleed"),
                    drop = "Bell Core",
                    locked = true
                )
            )
        ),
        Zone(
            name = "Ashen Hollow",
            biomeArtResId = null,
            enemies = listOf(
                Enemy(
                    id = "cinderling",
                    zone = "Ashen Hollow",
                    name = "Cinderling",
                    kind = "ASHEN · BEAST",
                    status = "Burning",
                    damageType = DamageSource.FIRE,
                    weaknesses = listOf("Frost", "Physical"),
                    drop = "Ember Shard",
                    locked = false
                ),
                Enemy(
                    id = "glassknight",
                    zone = "Ashen Hollow",
                    name = "Glass Knight",
                    kind = "HOLLOW · ELITE",
                    status = "Armored",
                    damageType = DamageSource.PHYSICAL,
                    weaknesses = listOf("Arcane", "Holy"),
                    drop = "Prism Core",
                    locked = false
                ),
                Enemy(
                    id = "hollowseer",
                    zone = "Ashen Hollow",
                    name = "Hollow Seer",
                    kind = "HOLLOW · CASTER",
                    status = "Ward",
                    damageType = DamageSource.ARCANE,
                    weaknesses = listOf("Bleed"),
                    drop = "Voidglass",
                    locked = false
                ),
                Enemy(
                    id = "ashmaw",
                    zone = "Ashen Hollow",
                    name = "Ashmaw Brute",
                    kind = "ASHEN · BEAST",
                    status = "Enraged",
                    damageType = DamageSource.PHYSICAL,
                    weaknesses = listOf("Poison"),
                    drop = "Ashen Hide",
                    locked = false
                ),
                Enemy(
                    id = "kilnking",
                    zone = "Ashen Hollow",
                    name = "The Kiln King",
                    kind = "BOSS · LOCKED",
                    status = "Molten",
                    damageType = DamageSource.FIRE,
                    weaknesses = listOf("Frost"),
                    drop = "Kiln Heart",
                    locked = true
                )
            )
        ),
        Zone(
            name = "Mourning Wood",
            biomeArtResId = null,
            enemies = listOf(
                Enemy(
                    id = "thornwight",
                    zone = "Mourning Wood",
                    name = "Thornwight",
                    kind = "ROOTBOUND · BEAST",
                    status = "Bleeding",
                    damageType = DamageSource.BLEED,
                    weaknesses = listOf("Fire"),
                    drop = "Blackroot",
                    locked = false
                ),
                Enemy(
                    id = "mothpriest",
                    zone = "Mourning Wood",
                    name = "Moth Priest",
                    kind = "VEILED · CASTER",
                    status = "Hexed",
                    damageType = DamageSource.SHADOW,
                    weaknesses = listOf("Holy"),
                    drop = "Pale Pollen",
                    locked = false
                ),
                Enemy(
                    id = "bogwidow",
                    zone = "Mourning Wood",
                    name = "Bog Widow",
                    kind = "VENOMED · BEAST",
                    status = "Poisoned",
                    damageType = DamageSource.POISON,
                    weaknesses = listOf("Fire", "Physical"),
                    drop = "Venom Sac",
                    locked = false
                ),
                Enemy(
                    id = "woodknell",
                    zone = "Mourning Wood",
                    name = "Woodknell",
                    kind = "UNDEAD · ELITE",
                    status = "Chilled",
                    damageType = DamageSource.FROST,
                    weaknesses = listOf("Fire"),
                    drop = "Knell Fragment",
                    locked = false
                ),
                Enemy(
                    id = "rootmother",
                    zone = "Mourning Wood",
                    name = "The Root Mother",
                    kind = "BOSS · LOCKED",
                    status = "Rooted",
                    damageType = DamageSource.POISON,
                    weaknesses = listOf("Holy"),
                    drop = "Heartwood Seal",
                    locked = true
                )
            )
        )
    )

    val allEnemies: List<Enemy> = zones.flatMap { it.enemies }

    val defaultSelectedEnemyIds: List<String> = listOf("gravejaw", "mirehexer", "sallow")

    val abilities: List<CastAbility> = listOf(
        CastAbility("Ember Brand", "✹", DamageSource.FIRE, 2800, 0.42f),
        CastAbility("Rime Lash", "❄", DamageSource.FROST, 3600, 0.56f),
        CastAbility("Radiant Verdict", "✦", DamageSource.HOLY, 3200, 0.22f),
        CastAbility("Venom Thread", "✧", DamageSource.POISON, 4000, 0.62f),
        CastAbility("Starfall Sigil", "◈", DamageSource.ARCANE, 4500, 0.38f),
        CastAbility("Mace Strike", "⚔", DamageSource.PHYSICAL, 1900, 0.48f, isAutoAttack = true)
    )

    val initialFeedEvents: List<FeedEvent> = listOf(
        FeedEvent(1L, FeedEventType.DAMAGE, "✦", "", "−42", " incoming damage.", timeLabel = "now"),
        FeedEvent(2L, FeedEventType.MATERIAL, "✧", "", "+2 Grave Resin", " collected.", timeLabel = "1m"),
        FeedEvent(3L, FeedEventType.KILL, "⚔", "Defeated ", "Mire Hexer", ".", timeLabel = "1m"),
        FeedEvent(
            4L,
            FeedEventType.ITEM,
            "⚒",
            "Found ",
            "Witchglass Signet",
            ".",
            highlightColor = GodscarColors.RarityRare,
            timeLabel = "2m"
        ),
        FeedEvent(5L, FeedEventType.EFFECT, "❄", "", "Chilled", " effect applied.", timeLabel = "2m"),
        FeedEvent(6L, FeedEventType.LEVEL, "✦", "Reached ", "Level 2", ".", timeLabel = "4m"),
        FeedEvent(7L, FeedEventType.DEATH, "✕", "", "Player", " was defeated.", timeLabel = "6m")
    )

    val initialSessionMetrics = SessionMetrics(
        xpPerHour = "48.6k",
        xpSessionNote = "+12.4k this session",
        kills = 164,
        materials = "38",
        currency = "2.4k",
        totalDamage = "8.74m",
        breakdown = listOf(
            DamageBreakdownEntry(DamageSource.PHYSICAL, "3.84m · 44%", 44),
            DamageBreakdownEntry(DamageSource.FIRE, "1.92m · 22%", 22),
            DamageBreakdownEntry(DamageSource.HOLY, "1.22m · 14%", 14),
            DamageBreakdownEntry(DamageSource.FROST, "699k · 8%", 8),
            DamageBreakdownEntry(DamageSource.POISON, "524k · 6%", 6),
            DamageBreakdownEntry(DamageSource.BLEED, "350k · 4%", 4)
        )
    )

    val zeroSessionMetrics = SessionMetrics(
        xpPerHour = "0",
        xpSessionNote = "+0 this session",
        kills = 0,
        materials = "0",
        currency = "0",
        totalDamage = "0",
        breakdown = initialSessionMetrics.breakdown.map {
            it.copy(totalFormatted = "0 · 0%", sharePercent = 0)
        }
    )

    val equippedGear: Map<EquipmentSlotType, GearItem> = mapOf(
        EquipmentSlotType.WEAPON to GearItem(
            "Cinderbound Mace",
            EquipmentSlotType.WEAPON,
            ItemRarity.RARE,
            "+42 Physical Damage · +8% Attack Speed"
        ),
        EquipmentSlotType.OFF_HAND to GearItem(
            "Hearthguard Shield",
            EquipmentSlotType.OFF_HAND,
            ItemRarity.EPIC,
            "+90 Armor · +12 Fire Resistance"
        ),
        EquipmentSlotType.HELMET to GearItem(
            "Worn Greathelm",
            EquipmentSlotType.HELMET,
            ItemRarity.UNCOMMON,
            "+16 Armor"
        ),
        EquipmentSlotType.CHEST to GearItem(
            "Ashen Plate",
            EquipmentSlotType.CHEST,
            ItemRarity.RARE,
            "+70 Armor · +30 Max HP"
        ),
        EquipmentSlotType.RING_1 to GearItem(
            "Smoldering Band",
            EquipmentSlotType.RING_1,
            ItemRarity.LEGENDARY,
            "+18 Fire Resistance"
        ),
        EquipmentSlotType.BOOTS to GearItem(
            "Marcher Boots",
            EquipmentSlotType.BOOTS,
            ItemRarity.UNCOMMON,
            "+11 Armor"
        )
    )

    val inventoryItems: List<GearItem> = listOf(
        GearItem("Cinderbound Mace", EquipmentSlotType.WEAPON, ItemRarity.RARE, "+42 Physical Damage · +8% Attack Speed"),
        GearItem("Pilgrim Helm", EquipmentSlotType.HELMET, ItemRarity.COMMON, "+9 Armor"),
        GearItem("Graveguard Shield", EquipmentSlotType.OFF_HAND, ItemRarity.EPIC, "+105 Armor"),
        GearItem("Sable Amulet", EquipmentSlotType.AMULET, ItemRarity.RARE, "+12 INT"),
        GearItem("Iron Gloves", EquipmentSlotType.GLOVES, ItemRarity.UNCOMMON, "+10 Armor"),
        GearItem("Ember Ring", EquipmentSlotType.RING_2, ItemRarity.LEGENDARY, "+18 Fire Resistance"),
        GearItem("Warden Belt", EquipmentSlotType.BELT, ItemRarity.RARE, "+40 Max HP"),
        GearItem("Travel Boots", EquipmentSlotType.BOOTS, ItemRarity.COMMON, "+8 Armor")
    )

    val materials: List<MaterialEntry> = listOf(
        MaterialEntry("Scar Shards", "146", "◇"),
        MaterialEntry("Ember Essence", "8", "✹"),
        MaterialEntry("Dawn Essence", "5", "✦"),
        MaterialEntry("Grave Resin", "32", "◆"),
        MaterialEntry("Witchglass Dust", "17", "✧")
    )

    val recapLoot: List<RecapLootItem> = listOf(
        RecapLootItem(1, "Dawnbreaker", "Legendary · Mace · Gearscore #1", "Legendary · Mace", ItemRarity.LEGENDARY, "⚒"),
        RecapLootItem(2, "Gallowsguard", "Epic · Chest Armor · Gearscore #2", "Epic · Chest Armor", ItemRarity.EPIC, "⬡"),
        RecapLootItem(3, "Witchglass Signet", "Rare · Ring · Gearscore #3", "Rare · Ring", ItemRarity.RARE, "◉")
    )

    val detailedCombatStats: List<Pair<String, String>> = listOf(
        "Max HP" to "3,820",
        "Armor" to "740",
        "Auto Attack Speed" to "1.42/s",
        "Critical Chance" to "14.5%",
        "Critical Damage" to "175%",
        "HP Regeneration" to "38/s"
    )

    val detailedResistances: List<Pair<String, String>> = listOf(
        "Fire" to "40%",
        "Frost" to "25%",
        "Lightning" to "15%",
        "Shadow" to "20%",
        "Holy" to "10%"
    )
}
