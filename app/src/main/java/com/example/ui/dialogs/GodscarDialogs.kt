package com.example.ui.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.data.GodscarMockData
import com.example.model.ActiveDialog
import com.example.model.Enemy
import com.example.model.FeedEventType
import com.example.model.RecapLootItem
import com.example.ui.GodscarUiState
import com.example.ui.components.GsButton
import com.example.ui.components.GsButtonVariant
import com.example.ui.components.GsChamferedPanel
import com.example.ui.components.GsCheckbox
import com.example.ui.components.GsIconButton
import com.example.ui.components.GsToggleSwitch
import com.example.ui.components.chamferedShape
import com.example.ui.theme.GodscarColors

@Composable
fun GodscarDialogHost(
    state: GodscarUiState,
    onDismiss: () -> Unit,
    onBrowseZone: (String) -> Unit,
    onToggleEnemy: (String) -> Unit,
    onSetRecapPref: (Boolean) -> Unit,
    onToggleFilter: (FeedEventType, Boolean) -> Unit,
    onConfirmReset: () -> Unit,
    onOpenLootPreview: (RecapLootItem) -> Unit,
    onShowStashStub: () -> Unit
) {
    when (val dialog = state.activeDialog) {
        null -> Unit
        is ActiveDialog.ZoneEnemies -> ZoneEnemiesDialog(
            state = state,
            onBrowseZone = onBrowseZone,
            onToggleEnemy = onToggleEnemy,
            onDismiss = onDismiss
        )
        is ActiveDialog.Settings -> SettingsDialog(
            showRecap = state.showLaunchRecapPref,
            onToggleRecap = onSetRecapPref,
            onDismiss = onDismiss
        )
        is ActiveDialog.FeedFilter -> FeedFilterDialog(
            filters = state.feedFilters,
            onToggleFilter = onToggleFilter,
            onDismiss = onDismiss
        )
        is ActiveDialog.ResetConfirm -> ResetConfirmDialog(
            onConfirm = onConfirmReset,
            onDismiss = onDismiss
        )
        is ActiveDialog.LaunchRecap -> LaunchRecapDialog(
            recapDisabled = !state.showLaunchRecapPref,
            onSetDontShowOnLaunch = { dontShow -> onSetRecapPref(!dontShow) },
            onOpenLootPreview = onOpenLootPreview,
            onViewAllLoot = onShowStashStub,
            onDismiss = onDismiss
        )
        is ActiveDialog.LootPreview -> LootItemDialog(
            item = dialog.item,
            onOpenInStash = {
                onDismiss()
                onShowStashStub()
            },
            onDismiss = onDismiss
        )
    }
}

@Composable
private fun GsDialogFrame(
    eyebrow: String,
    title: String,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        GsChamferedPanel(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(max = 640.dp),
            cut = 12.dp,
            contentPadding = PaddingValues(0.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x0BB78C4A))
                        .padding(horizontal = 15.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = eyebrow,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFFB69C77)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = title,
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFFF1E1C5)
                        )
                    }
                    GsIconButton(
                        iconResId = R.drawable.ic_close,
                        contentDescription = "Close",
                        onClick = onDismiss,
                        size = 38.dp,
                        iconSize = 16.dp
                    )
                }
                HorizontalDivider(color = Color(0xFF73593C))
                Box(modifier = Modifier.fillMaxWidth()) {
                    content()
                }
            }
        }
    }
}

@Composable
private fun ZoneEnemiesDialog(
    state: GodscarUiState,
    onBrowseZone: (String) -> Unit,
    onToggleEnemy: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var dropdownOpen by remember { mutableStateOf(false) }
    val browsedZone = GodscarMockData.zones.find { it.name == state.browseZoneName }
        ?: GodscarMockData.zones.first()
    val activeZone = state.activeEnemies.firstOrNull()?.zone

    GsDialogFrame(
        eyebrow = "HUNT SETTINGS",
        title = "Zone & enemies",
        onDismiss = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(14.dp)
        ) {
            Text(
                text = "BROWSE A ZONE",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFB69C77)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(chamferedShape(9.dp))
                        .background(Color(0xFF201D1D))
                        .border(1.dp, Color(0xFF8C6E49), chamferedShape(9.dp))
                        .clickable { dropdownOpen = true }
                        .padding(horizontal = 13.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = browsedZone.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = GodscarColors.TextIvory
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chevron_down),
                        contentDescription = "Choose zone",
                        tint = GodscarColors.Highlight,
                        modifier = Modifier.size(16.dp)
                    )
                }

                DropdownMenu(
                    expanded = dropdownOpen,
                    onDismissRequest = { dropdownOpen = false },
                    modifier = Modifier.background(Color(0xFF1D1A18))
                ) {
                    GodscarMockData.zones.forEach { zone ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = zone.name,
                                    color = GodscarColors.TextIvory,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                dropdownOpen = false
                                onBrowseZone(zone.name)
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = "Browsing zones won't interrupt combat. Selecting an enemy in another zone moves your hunt there. Choose one to four enemies.",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = Color(0xFFB7A992)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hunt rotation",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color(0xFFEEDBBD)
                )
                Surface(
                    color = Color(0xFF30251B),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, Color(0xFF856640))
                ) {
                    Text(
                        text = "${state.selectedEnemyIds.size} / 4 selected",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = Color(0xFFE0BA7E),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                browsedZone.enemies.forEach { enemy ->
                    EnemyOptionCard(
                        enemy = enemy,
                        selected = enemy.id in state.selectedEnemyIds,
                        crossZoneHint = activeZone != null && activeZone != browsedZone.name,
                        onToggle = { onToggleEnemy(enemy.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun EnemyOptionCard(
    enemy: Enemy,
    selected: Boolean,
    crossZoneHint: Boolean,
    onToggle: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(if (enemy.locked) 0.65f else 1f)
            .clip(RoundedCornerShape(5.dp))
            .clickable(enabled = !enemy.locked, onClick = onToggle)
            .testTag("enemy_option_${enemy.id}"),
        color = if (selected) Color(0xFF28221A) else Color(0xFF1A1712),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(
            width = if (selected) 1.5.dp else 1.dp,
            color = if (selected) Color(0xFFC49A5D) else Color(0xFF594731)
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = enemy.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFF0D8B5)
                    )
                    Text(
                        text = enemy.kind,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = Color(0xFFB7A992)
                    )
                }
                if (enemy.locked) {
                    Text(
                        text = "🔒 LOCKED",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFC0AAA0)
                    )
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        GsCheckbox(
                            checked = selected,
                            onCheckedChange = { onToggle() }
                        )
                        Text(
                            text = if (selected) "SELECTED" else "SELECT",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = Color(0xFFE6D6B9)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Surface(
                    color = Color(0xFF342D24),
                    shape = RoundedCornerShape(3.dp),
                    border = BorderStroke(1.dp, Color(0xFF67533C))
                ) {
                    Text(
                        text = "${enemy.status} · ${enemy.damageType.displayName}",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = Color(0xFFD8C8AD),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Surface(
                    color = Color(0xFF3C3020),
                    shape = RoundedCornerShape(3.dp),
                    border = BorderStroke(1.dp, Color(0xFF8F6E43))
                ) {
                    Text(
                        text = "Weak to ${enemy.weaknesses.joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = Color(0xFFE7C58D),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = buildAnnotatedString {
                    append("Potential drop: ")
                    withStyle(SpanStyle(color = Color(0xFFE0BD82), fontWeight = FontWeight.Bold)) {
                        append(enemy.drop)
                    }
                    if (crossZoneHint && !selected && !enemy.locked) {
                        append(" · Select to move hunt here")
                    }
                },
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.5.sp),
                color = Color(0xFFB7A992)
            )
        }
    }
}

@Composable
private fun SettingsDialog(
    showRecap: Boolean,
    onToggleRecap: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    GsDialogFrame(
        eyebrow = "GAME OPTIONS",
        title = "Settings",
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Show launch recap",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = GodscarColors.TextIvory
                )
                GsToggleSwitch(
                    checked = showRecap,
                    onCheckedChange = onToggleRecap,
                    modifier = Modifier.testTag("settings_recap_toggle")
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF221D16),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, Color(0xFF816A4A))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Appearance",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFE8D7BD)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Theme controls will be added later. The visual system is built to be restyled.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFC8B79B)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedFilterDialog(
    filters: Map<FeedEventType, Boolean>,
    onToggleFilter: (FeedEventType, Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    GsDialogFrame(
        eyebrow = "ACTIVITY FEED",
        title = "Feed options",
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) {
            FeedEventType.entries.forEachIndexed { index, type ->
                val checked = filters[type] ?: true
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onToggleFilter(type, !checked) }
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = type.label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFDFCFB6)
                    )
                    GsCheckbox(
                        checked = checked,
                        onCheckedChange = { onToggleFilter(type, it) }
                    )
                }
                if (index < FeedEventType.entries.lastIndex) {
                    HorizontalDivider(color = Color(0xFF4B3D2C))
                }
            }
        }
    }
}

@Composable
private fun ResetConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    GsDialogFrame(
        eyebrow = "SESSION TRACKING",
        title = "Start a new count?",
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                text = "This clears the current session totals and starts counting from zero.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFC2CCC6)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GsButton(
                    onClick = onDismiss,
                    variant = GsButtonVariant.SECONDARY,
                    cut = 5.dp,
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFFD9E0DC)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                GsButton(
                    onClick = onConfirm,
                    variant = GsButtonVariant.DANGER,
                    cut = 5.dp,
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                    modifier = Modifier.testTag("confirm_reset_button")
                ) {
                    Text(
                        text = "RESET TOTALS",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(0xFFE4AD9B)
                    )
                }
            }
        }
    }
}

@Composable
private fun LaunchRecapDialog(
    recapDisabled: Boolean,
    onSetDontShowOnLaunch: (Boolean) -> Unit,
    onOpenLootPreview: (RecapLootItem) -> Unit,
    onViewAllLoot: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        GsChamferedPanel(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(max = 660.dp)
                .testTag("launch_recap_dialog"),
            cut = 12.dp,
            contentPadding = PaddingValues(0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0x20C99F65), Color.Transparent)
                            )
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = "SESSION RECAP",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFE1BD86)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Welcome back, Player",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color(0xFFF1E1C5)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Here’s what happened while you were away.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB8A88D)
                    )
                }
                HorizontalDivider(color = Color(0xFF58442E))

                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                        RecapStatBox("Experience", "+42.8k XP · +1 level", Modifier.weight(1f))
                        RecapStatBox("Enemies defeated", "164", Modifier.weight(1f))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                        RecapStatBox("Materials", "38 collected", Modifier.weight(1f))
                        RecapStatBox("Currency", "+2.4k Gold", Modifier.weight(1f))
                    }
                }

                HorizontalDivider(color = Color(0xFF5C4831))

                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Notable loot · top 3",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xFFF0D8B5)
                        )
                        TextButton(onClick = onViewAllLoot) {
                            Text(
                                text = "VIEW ALL LOOT",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color(0xFFE2BA7A)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        GodscarMockData.recapLoot.forEach { item ->
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable { onOpenLootPreview(item) },
                                color = Color(0xFF1E1913),
                                shape = RoundedCornerShape(5.dp),
                                border = BorderStroke(1.dp, Color(0xFF594731))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(9.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFF33281C))
                                            .border(1.dp, Color(0xFF795E40), RoundedCornerShape(4.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = item.glyph,
                                            fontSize = 14.sp,
                                            color = Color(0xFFDBB57C)
                                        )
                                    }
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = item.rarity.textColor,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = item.subtitle,
                                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
                                            color = Color(0xFFB8A88D)
                                        )
                                    }
                                    Text(
                                        text = item.rank.toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color(0xFFD5AE75)
                                    )
                                }
                            }
                        }
                    }
                }

                HorizontalDivider(color = Color(0xFF5C4831))

                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Notable events",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color(0xFFF0D8B5)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Paladin reached level 2 · 1 death · rare crafting material found",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB7A992)
                    )
                }

                HorizontalDivider(color = Color(0xFF5C4831))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSetDontShowOnLaunch(!recapDisabled) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GsCheckbox(
                            checked = recapDisabled,
                            onCheckedChange = onSetDontShowOnLaunch
                        )
                        Text(
                            text = "Don’t show this on launch",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                            color = Color(0xFFAEB6B2)
                        )
                    }
                    GsButton(
                        onClick = onDismiss,
                        variant = GsButtonVariant.ACTIVE,
                        cut = 5.dp,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        modifier = Modifier.testTag("recap_close_button")
                    ) {
                        Text(
                            text = "CLOSE",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xFFFFF0D1)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecapStatBox(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFF1A1612),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color(0xFF594731))
    ) {
        Column(modifier = Modifier.padding(9.dp)) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                color = Color(0xFFB8A88D)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                color = Color(0xFFE8D7BD)
            )
        }
    }
}

@Composable
private fun LootItemDialog(
    item: RecapLootItem,
    onOpenInStash: () -> Unit,
    onDismiss: () -> Unit
) {
    GsDialogFrame(
        eyebrow = "ITEM DETAILS · STASH PREVIEW",
        title = item.name,
        onDismiss = onDismiss
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xFF3A2D1F))
                        .border(1.dp, Color(0xFF795E40), RoundedCornerShape(5.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.glyph, fontSize = 20.sp, color = Color(0xFFDBB57C))
                }
                Column {
                    Text(
                        text = item.category,
                        style = MaterialTheme.typography.labelLarge,
                        color = item.rarity.textColor
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Selected in your future Stash screen. Its item-detail window will open there.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFB8A88D)
                    )
                }
            }
            Spacer(modifier = Modifier.height(14.dp))
            GsButton(
                onClick = onOpenInStash,
                variant = GsButtonVariant.SECONDARY,
                cut = 5.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "OPEN IN STASH",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color(0xFFE4D1AE)
                )
            }
        }
    }
}
