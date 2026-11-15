package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.R
import com.example.data.GodscarMockData
import com.example.model.CharacterItemPreview
import com.example.model.CharacterMainTab
import com.example.model.EquipmentSlotType
import com.example.model.GearItem
import com.example.model.InventorySubTab
import com.example.model.ItemRarity
import com.example.ui.GodscarUiState
import com.example.ui.components.GsIconButton
import com.example.ui.theme.GodscarColors

@Composable
fun CharacterOverlay(
    state: GodscarUiState,
    onClose: () -> Unit,
    onSelectMainTab: (CharacterMainTab) -> Unit,
    onSelectInventoryTab: (InventorySubTab) -> Unit,
    onInspectSlot: (EquipmentSlotType, GearItem?) -> Unit,
    onInspectItem: (GearItem) -> Unit,
    onOpenDetailedStats: () -> Unit,
    onCloseDetailedStats: () -> Unit,
    onCloseItemPreview: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(enabled = state.isCharacterOpen) {
        when {
            state.characterItemPreview != null -> onCloseItemPreview()
            state.isDetailedStatsOpen -> onCloseDetailedStats()
            else -> onClose()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF24201B), Color(0xFF15120F), Color(0xFF11100F))
                )
            )
            .testTag("character_overlay"),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 520.dp)
        ) {
            // Character Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF37281E), Color(0xFF191612))
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.character_eyebrow),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFB3A28D)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(R.string.character_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = GodscarColors.TextWarm
                    )
                }
                GsIconButton(
                    iconResId = R.drawable.ic_close,
                    contentDescription = "Close Character",
                    onClick = onClose,
                    size = 40.dp,
                    iconSize = 17.dp,
                    modifier = Modifier.testTag("character_close_button")
                )
            }
            HorizontalDivider(color = Color(0xFF5D462D))

            // 3 Top Tabs: Equipment | Inventory | Skills
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF171410))
                    .padding(start = 12.dp, end = 12.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CharacterMainTab.entries.forEach { tab ->
                    val selected = state.characterMainTab == tab
                    CharacterTabButton(
                        title = tab.title,
                        selected = selected,
                        onClick = { onSelectMainTab(tab) },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("character_tab_${tab.name.lowercase()}")
                    )
                }
            }

            // Tab viewport
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (state.characterMainTab) {
                    CharacterMainTab.EQUIPMENT -> EquipmentTabContent(
                        onInspectSlot = onInspectSlot,
                        onOpenDetailedStats = onOpenDetailedStats
                    )
                    CharacterMainTab.INVENTORY -> InventoryTabContent(
                        subTab = state.inventorySubTab,
                        onSelectSubTab = onSelectInventoryTab,
                        onInspectItem = onInspectItem
                    )
                    CharacterMainTab.SKILLS -> SkillsTabPlaceholder()
                }
            }
        }

        // Detailed Stats Bottom Sheet Layer
        AnimatedVisibility(
            visible = state.isDetailedStatsOpen,
            enter = fadeIn() + slideInVertically { it / 2 },
            exit = fadeOut() + slideOutVertically { it / 2 }
        ) {
            DetailedStatsSheet(onDismiss = onCloseDetailedStats)
        }

        // Item Preview Dialog
        state.characterItemPreview?.let { preview ->
            CharacterItemPreviewDialog(
                preview = preview,
                onDismiss = onCloseItemPreview
            )
        }
    }
}

@Composable
private fun CharacterTabButton(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(shape)
            .background(
                if (selected) Brush.verticalGradient(listOf(Color(0xFF3F3023), Color(0xFF292118)))
                else Brush.verticalGradient(listOf(Color(0xFF211C17), Color(0xFF211C17)))
            )
            .border(
                width = 1.dp,
                color = if (selected) Color(0xFF7B5636) else Color.Transparent,
                shape = shape
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge.copy(fontSize = 12.sp),
            color = if (selected) Color(0xFFF4D7AA) else Color(0xFFB9AA95)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(2.dp)
                .background(if (selected) Color(0xFFE1B474) else Color(0xFF67513A))
        )
    }
}

@Composable
private fun EquipmentTabContent(
    onInspectSlot: (EquipmentSlotType, GearItem?) -> Unit,
    onOpenDetailedStats: () -> Unit
) {
    val equipped = GodscarMockData.equippedGear
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 12.dp, vertical = 12.dp)
    ) {
        // Row 1: Weapon (tall) | Gearscore + Helmet | Off-hand (tall)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            EquipmentSlotCard(
                slot = EquipmentSlotType.WEAPON,
                item = equipped[EquipmentSlotType.WEAPON],
                visualHeight = 119.dp,
                iconSize = 38.dp,
                onClick = { onInspectSlot(EquipmentSlotType.WEAPON, equipped[EquipmentSlotType.WEAPON]) },
                modifier = Modifier.weight(1f)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF2B2118),
                    shape = RoundedCornerShape(7.dp),
                    border = BorderStroke(1.dp, Color(0xFF806041))
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "GEARSCORE",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                            color = Color(0xFFC1A988)
                        )
                        Text(
                            text = "2,485",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 17.sp,
                                fontFeatureSettings = "tnum"
                            ),
                            color = Color(0xFFE6C58E)
                        )
                    }
                }
                EquipmentSlotCard(
                    slot = EquipmentSlotType.HELMET,
                    item = equipped[EquipmentSlotType.HELMET],
                    visualHeight = 76.dp,
                    iconSize = 29.dp,
                    onClick = { onInspectSlot(EquipmentSlotType.HELMET, equipped[EquipmentSlotType.HELMET]) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            EquipmentSlotCard(
                slot = EquipmentSlotType.OFF_HAND,
                item = equipped[EquipmentSlotType.OFF_HAND],
                visualHeight = 119.dp,
                iconSize = 38.dp,
                onClick = { onInspectSlot(EquipmentSlotType.OFF_HAND, equipped[EquipmentSlotType.OFF_HAND]) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Row 2: Amulet | Chest | Gloves
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(EquipmentSlotType.AMULET, EquipmentSlotType.CHEST, EquipmentSlotType.GLOVES).forEach { slot ->
                EquipmentSlotCard(
                    slot = slot,
                    item = equipped[slot],
                    visualHeight = 64.dp,
                    iconSize = 29.dp,
                    onClick = { onInspectSlot(slot, equipped[slot]) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Row 3: Ring 1 | Belt | Ring 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(EquipmentSlotType.RING_1, EquipmentSlotType.BELT, EquipmentSlotType.RING_2).forEach { slot ->
                EquipmentSlotCard(
                    slot = slot,
                    item = equipped[slot],
                    visualHeight = 64.dp,
                    iconSize = 29.dp,
                    onClick = { onInspectSlot(slot, equipped[slot]) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Row 4: Legs & Boots centered together (occupying 4 of 6 columns)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            EquipmentSlotCard(
                slot = EquipmentSlotType.LEGS,
                item = equipped[EquipmentSlotType.LEGS],
                visualHeight = 64.dp,
                iconSize = 29.dp,
                onClick = { onInspectSlot(EquipmentSlotType.LEGS, equipped[EquipmentSlotType.LEGS]) },
                modifier = Modifier.weight(1f)
            )
            EquipmentSlotCard(
                slot = EquipmentSlotType.BOOTS,
                item = equipped[EquipmentSlotType.BOOTS],
                visualHeight = 64.dp,
                iconSize = 29.dp,
                onClick = { onInspectSlot(EquipmentSlotType.BOOTS, equipped[EquipmentSlotType.BOOTS]) },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Character Stats Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF1B1713),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color(0xFF694F34))
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "CHARACTER STATS",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFD9C8B0)
                    )
                    Text(
                        text = "SAMPLE VALUES",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = Color(0xFF998975)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    AttributeBox("185", "STR", GodscarColors.AttrStr, Modifier.weight(1f))
                    AttributeBox("126", "INT", GodscarColors.AttrInt, Modifier.weight(1f))
                    AttributeBox("92", "DEX", GodscarColors.AttrDex, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color(0xFF584735))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CombatMetricCol("3,820", "Max HP", Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(28.dp)
                            .background(Color(0xFF584735))
                    )
                    CombatMetricCol("740", "Armor", Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .height(28.dp)
                            .background(Color(0xFF584735))
                    )
                    CombatMetricCol("1.42/s", "Auto Attack Speed", Modifier.weight(1f))
                }

                HorizontalDivider(color = Color(0xFF584735))
                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(7.dp))
                        .clickable(onClick = onOpenDetailedStats)
                        .testTag("detailed_stats_button"),
                    color = Color(0xFF2B2018),
                    shape = RoundedCornerShape(7.dp),
                    border = BorderStroke(1.dp, Color(0xFF705336))
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "DETAILED STATS  ⌃",
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 11.sp),
                            color = Color(0xFFF2D6AE)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EquipmentSlotCard(
    slot: EquipmentSlotType,
    item: GearItem?,
    visualHeight: Dp,
    iconSize: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isEquipped = item != null
    val borderColor = when (item?.rarity) {
        ItemRarity.EPIC -> Color(0xFF9A78BB)
        ItemRarity.LEGENDARY -> Color(0xFFD6AD60)
        ItemRarity.RARE, ItemRarity.UNCOMMON, ItemRarity.COMMON -> Color(0xFFB08554)
        null -> Color(0xFF5A4937)
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .testTag("equip_slot_${slot.name.lowercase()}"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(visualHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.radialGradient(
                        listOf(Color(0xFF4D3C2B), Color(0xFF251F19), Color(0xFF14120F))
                    )
                )
                .border(1.2.dp, borderColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = slot.iconResId),
                contentDescription = if (item != null) "${slot.label} equipped: ${item.name}" else "${slot.label} empty",
                tint = if (isEquipped) Color(0xFFC0AC8E) else Color(0xFF7C7263),
                modifier = Modifier.size(iconSize)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = slot.label,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
            color = Color(0xFFC6B8A4),
            maxLines = 1
        )
    }
}

@Composable
private fun AttributeBox(
    value: String,
    label: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFF171511),
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(1.dp, Color(0xFF43372B))
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 22.sp,
                    fontFeatureSettings = "tnum"
                ),
                color = color
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = color
            )
        }
    }
}

@Composable
private fun CombatMetricCol(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 14.sp,
                fontFeatureSettings = "tnum"
            ),
            color = Color(0xFFEFE3D1)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
            color = Color(0xFFB9A890),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InventoryTabContent(
    subTab: InventorySubTab,
    onSelectSubTab: (InventorySubTab) -> Unit,
    onInspectItem: (GearItem) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            InventorySubTab.entries.forEach { tab ->
                CharacterTabButton(
                    title = tab.title,
                    selected = subTab == tab,
                    onClick = { onSelectSubTab(tab) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("inventory_tab_${tab.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (subTab == InventorySubTab.ITEMS) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Collected Items",
                    style = MaterialTheme.typography.titleMedium,
                    color = GodscarColors.TextWarm
                )
                Text(
                    text = "8 / 30 · SAMPLE",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFB3A086)
                )
            }
            Spacer(modifier = Modifier.height(11.dp))

            val rows = GodscarMockData.inventoryItems.chunked(4)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                rows.forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        rowItems.forEach { item ->
                            val borderColor = when (item.rarity) {
                                ItemRarity.EPIC -> Color(0xFFA782C0)
                                ItemRarity.LEGENDARY -> Color(0xFFD5AE62)
                                else -> Color(0xFF866743)
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { onInspectItem(item) },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            Brush.radialGradient(
                                                listOf(Color(0xFF4A392B), Color(0xFF1B1713))
                                            )
                                        )
                                        .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = item.slot.iconResId),
                                        contentDescription = "Inspect ${item.name}",
                                        tint = Color(0xFFE0BF8C),
                                        modifier = Modifier.size(29.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = item.name,
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.5.sp),
                                    color = Color(0xFFDBCBB3),
                                    textAlign = TextAlign.Center,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Materials",
                    style = MaterialTheme.typography.titleMedium,
                    color = GodscarColors.TextWarm
                )
                Text(
                    text = "MOCK RESOURCES",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFB3A086)
                )
            }
            Spacer(modifier = Modifier.height(11.dp))

            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                GodscarMockData.materials.forEach { mat ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF241D17),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFF59432D))
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .border(1.dp, Color(0xFF85633F), RoundedCornerShape(6.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = mat.glyph,
                                    fontSize = 18.sp,
                                    color = Color(0xFFDDBC87)
                                )
                            }
                            Text(
                                text = mat.name,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = GodscarColors.TextWarm,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = mat.count,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontSize = 13.sp,
                                    fontFeatureSettings = "tnum"
                                ),
                                color = GodscarColors.TextWarm
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillsTabPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF211B16))
                .padding(horizontal = 18.dp, vertical = 26.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                drawRoundRect(
                    color = Color(0xFF765839),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx()),
                    style = Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(14f, 10f), 0f)
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(text = "✦", fontSize = 30.sp, color = Color(0xFFCCA06B))
                Text(
                    text = stringResource(R.string.tab_skills),
                    style = MaterialTheme.typography.headlineMedium,
                    color = GodscarColors.TextWarm
                )
                Text(
                    text = stringResource(R.string.skills_placeholder_body),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFC8B9A3),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.skills_placeholder_tag),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF98816B)
                )
            }
        }
    }
}

@Composable
private fun DetailedStatsSheet(onDismiss: () -> Unit) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xBC050403))
            .testTag("detailed_stats_sheet")
    ) {
        // Uncovered tappable top strip to dismiss
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .clickable(onClick = onDismiss),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFD8B584))
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Tap outside to close",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = Color(0xFFC1AD91)
            )
        }

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = Color(0xFF1A1510),
            shape = RoundedCornerShape(topStart = 17.dp, topEnd = 17.dp),
            border = BorderStroke(1.dp, Color(0xFF88623D))
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "CHARACTER",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFBFA483)
                        )
                        Text(
                            text = "Detailed Stats",
                            style = MaterialTheme.typography.headlineSmall,
                            color = GodscarColors.TextWarm
                        )
                    }
                    GsIconButton(
                        iconResId = R.drawable.ic_close,
                        contentDescription = "Close detailed stats",
                        onClick = onDismiss,
                        size = 40.dp,
                        iconSize = 17.dp
                    )
                }
                HorizontalDivider(color = Color(0xFF654E35))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp, vertical = 13.dp)
                ) {
                    Text(
                        text = "Primary Attributes",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFE4C291)
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        AttributeBox("185", "STR", GodscarColors.AttrStr, Modifier.weight(1f))
                        AttributeBox("126", "INT", GodscarColors.AttrInt, Modifier.weight(1f))
                        AttributeBox("92", "DEX", GodscarColors.AttrDex, Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Combat",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFE4C291)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    GodscarMockData.detailedCombatStats.forEach { (k, v) ->
                        StatDefinitionRow(k, v)
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Resistances",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFE4C291)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    GodscarMockData.detailedResistances.forEach { (k, v) ->
                        StatDefinitionRow(k, v)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Sample values only. Nothing here reads game data.",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                        color = Color(0xFFA3927E)
                    )
                }
            }
        }
    }
}

@Composable
private fun StatDefinitionRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = Color(0xFFC1AF97)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFeatureSettings = "tnum"
            ),
            color = Color(0xFFEEE0C9)
        )
    }
    HorizontalDivider(color = Color(0xFF473A2E))
}

@Composable
private fun CharacterItemPreviewDialog(
    preview: CharacterItemPreview,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF221C16),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF9A7042))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "ITEM PREVIEW",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFBFA483)
                        )
                        Text(
                            text = preview.title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = GodscarColors.TextWarm
                        )
                    }
                    GsIconButton(
                        iconResId = R.drawable.ic_close,
                        contentDescription = "Close item",
                        onClick = onDismiss,
                        size = 38.dp,
                        iconSize = 16.dp
                    )
                }
                HorizontalDivider(color = Color(0xFF654E35))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = preview.subtitle,
                        style = MaterialTheme.typography.labelLarge,
                        color = GodscarColors.Highlight
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = preview.stats,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFD6C6AE)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Comparison and equipment actions are not connected yet.",
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                        color = Color(0xFFA99A84)
                    )
                }
            }
        }
    }
}
