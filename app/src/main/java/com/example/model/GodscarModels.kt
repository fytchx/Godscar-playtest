package com.example.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.R
import com.example.ui.theme.GodscarColors

enum class DamageSource(
    val displayName: String,
    val glyph: String,
    val color: Color
) {
    PHYSICAL("Physical", "⚔", GodscarColors.SourcePhysical),
    BLEED("Bleed", "🩸", GodscarColors.SourceBleed),
    FIRE("Fire", "✹", GodscarColors.SourceFire),
    HOLY("Holy", "✦", GodscarColors.SourceHoly),
    FROST("Frost", "❄", GodscarColors.SourceFrost),
    ARCANE("Arcane", "◈", GodscarColors.SourceArcane),
    SHADOW("Shadow", "☾", GodscarColors.SourceShadow),
    POISON("Poison", "✧", GodscarColors.SourcePoison)
}

enum class ItemRarity(
    val label: String,
    val textColor: Color,
    val borderColor: Color
) {
    COMMON("Common", GodscarColors.RarityCommon, Color(0xFF5A4937)),
    UNCOMMON("Uncommon", GodscarColors.RarityUncommon, Color(0xFF6B7B5C)),
    RARE("Rare", GodscarColors.RarityRare, Color(0xFF7AA3C4)),
    EPIC("Epic", GodscarColors.RarityEpic, Color(0xFF9A78BB)),
    LEGENDARY("Legendary", GodscarColors.RarityLegendary, Color(0xFFD6AD60))
}

data class Enemy(
    val id: String,
    val zone: String,
    val name: String,
    val kind: String,
    val status: String,
    val damageType: DamageSource,
    val weaknesses: List<String>,
    val drop: String,
    val locked: Boolean,
    @DrawableRes val artResId: Int? = null
)

data class Zone(
    val name: String,
    @DrawableRes val biomeArtResId: Int? = null,
    val enemies: List<Enemy>
)

data class CastAbility(
    val name: String,
    val glyph: String,
    val source: DamageSource,
    val castTimeMillis: Int,
    val offsetFraction: Float,
    val isAutoAttack: Boolean = false
)

enum class FeedEventType(
    val label: String,
    val color: Color
) {
    KILL("Enemy kills", GodscarColors.Accent),
    ITEM("Items looted", GodscarColors.SourceHoly),
    MATERIAL("Materials and currency", GodscarColors.SourcePoison),
    LEVEL("Level-ups", GodscarColors.SourceHoly),
    DEATH("Deaths", GodscarColors.SourceBleed),
    DAMAGE("Incoming damage", GodscarColors.SourcePhysical),
    EFFECT("Status effects", GodscarColors.SourceFrost)
}

data class FeedEvent(
    val id: Long,
    val type: FeedEventType,
    val icon: String,
    val prefix: String,
    val highlight: String,
    val suffix: String,
    val highlightColor: Color? = null,
    val timeLabel: String
)

data class FloatingDamage(
    val id: Long,
    val text: String,
    val source: DamageSource,
    val isCrit: Boolean,
    val xFraction: Float,
    val yFraction: Float
)

data class DamageBreakdownEntry(
    val source: DamageSource,
    val totalFormatted: String,
    val sharePercent: Int
)

data class SessionMetrics(
    val xpPerHour: String = "48.6k",
    val xpSessionNote: String = "+12.4k this session",
    val kills: Int = 164,
    val materials: String = "38",
    val currency: String = "2.4k",
    val totalDamage: String = "8.74m",
    val breakdown: List<DamageBreakdownEntry> = emptyList()
)

enum class EquipmentSlotType(
    val label: String,
    @DrawableRes val iconResId: Int
) {
    WEAPON("Weapon", R.drawable.ic_slot_weapon),
    HELMET("Helmet", R.drawable.ic_slot_helmet),
    OFF_HAND("Off-hand", R.drawable.ic_slot_offhand),
    AMULET("Amulet", R.drawable.ic_slot_amulet),
    CHEST("Chest", R.drawable.ic_slot_chest),
    GLOVES("Gloves", R.drawable.ic_slot_gloves),
    RING_1("Ring 1", R.drawable.ic_slot_ring),
    BELT("Belt", R.drawable.ic_slot_belt),
    RING_2("Ring 2", R.drawable.ic_slot_ring),
    LEGS("Legs", R.drawable.ic_slot_legs),
    BOOTS("Boots", R.drawable.ic_slot_boots)
}

data class GearItem(
    val name: String,
    val slot: EquipmentSlotType,
    val rarity: ItemRarity,
    val stats: String
)

data class MaterialEntry(
    val name: String,
    val count: String,
    val glyph: String
)

data class RecapLootItem(
    val rank: Int,
    val name: String,
    val subtitle: String,
    val category: String,
    val rarity: ItemRarity,
    val glyph: String
)

enum class CharacterMainTab(val title: String) {
    EQUIPMENT("Equipment"),
    INVENTORY("Inventory"),
    SKILLS("Skills")
}

enum class InventorySubTab(val title: String) {
    ITEMS("Items"),
    MATERIALS("Materials")
}

sealed interface ActiveDialog {
    data object ZoneEnemies : ActiveDialog
    data object Settings : ActiveDialog
    data object FeedFilter : ActiveDialog
    data object ResetConfirm : ActiveDialog
    data object LaunchRecap : ActiveDialog
    data class LootPreview(val item: RecapLootItem) : ActiveDialog
}

data class CharacterItemPreview(
    val title: String,
    val subtitle: String,
    val stats: String
)
