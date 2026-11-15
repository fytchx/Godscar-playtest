package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.GodscarMockData
import com.example.model.ActiveDialog
import com.example.model.CastAbility
import com.example.model.DamageSource
import com.example.model.Enemy
import com.example.model.FeedEvent
import com.example.model.FloatingDamage
import com.example.ui.GodscarUiState
import com.example.ui.components.GsButton
import com.example.ui.components.GsButtonVariant
import com.example.ui.components.GsChamferedPanel
import com.example.ui.components.GsChip
import com.example.ui.components.GsFeedSurfaceBrush
import com.example.ui.components.GsIconButton
import com.example.ui.components.GsProgressBar
import com.example.ui.components.GsSlotFrame
import com.example.ui.theme.GodscarColors
import com.example.ui.theme.LoraFontFamily
import kotlin.math.roundToInt

@Composable
fun FightScreen(
    state: GodscarUiState,
    onOpenSettings: () -> Unit,
    onOpenZonePanel: () -> Unit,
    onOpenFeedFilter: () -> Unit,
    onToggleSessionDetails: () -> Unit,
    onOpenResetConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val enemy = state.currentEnemy
    val activeZone = GodscarMockData.zones.find { it.name == enemy.zone }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF1C1915),
                        Color(0xFF12120F),
                        Color(0xFF13110F)
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 520.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 14.dp)
                .padding(bottom = 18.dp)
        ) {
            FightHeader(
                playerName = state.playerName,
                playerLevel = state.playerLevel,
                playerClass = state.playerClass,
                xpProgress = state.xpProgress,
                onOpenSettings = onOpenSettings
            )

            PlayerHealthLine(
                currentHp = state.playerHp,
                maxHp = state.playerMaxHp
            )

            ZoneSelectorLine(
                zoneTitle = enemy.zone,
                rotationText = state.rotationPositionText,
                onOpenZonePanel = onOpenZonePanel
            )

            CombatArena(
                enemy = enemy,
                biomeArtResId = activeZone?.biomeArtResId,
                enemyCurrentHp = state.enemyCurrentHp,
                enemyMaxHp = state.enemyMaxHp,
                liveDps = state.liveDps,
                hitFlashSource = state.hitFlashSource,
                hitFlashNonce = state.hitFlashNonce,
                floatingDamages = state.floatingDamages
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActivityFeedCard(
                events = state.visibleFeedEvents,
                isDetailsExpanded = state.isSessionDetailsExpanded,
                onOpenFilter = onOpenFeedFilter,
                onToggleDetails = onToggleSessionDetails
            )

            AnimatedVisibility(
                visible = state.isSessionDetailsExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    SessionDetailsCard(
                        state = state,
                        onOpenResetConfirm = onOpenResetConfirm
                    )
                }
            }
        }
    }
}

@Composable
private fun FightHeader(
    playerName: String,
    playerLevel: Int,
    playerClass: String,
    xpProgress: Float,
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0x18CBAA75), Color.Transparent)
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp, start = 2.dp, end = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_paladin_crest),
                    contentDescription = "Paladin emblem",
                    modifier = Modifier.size(38.dp)
                )
                Column {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        Text(
                            text = playerName,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFFF1E4CC)
                        )
                        Text(
                            text = "LV $playerLevel",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = Color(0xFFD5AE75)
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = playerClass.uppercase(),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = Color(0xFFA69981)
                    )
                }
            }

            GsIconButton(
                iconResId = R.drawable.ic_settings,
                contentDescription = "Settings",
                onClick = onOpenSettings,
                size = 44.dp,
                iconSize = 19.dp,
                modifier = Modifier.testTag("settings_button")
            )
        }

        // Thin gold hairline + XP rail
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(Color.Transparent, Color(0x60D0A66F), Color.Transparent)
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color(0xFF302519))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(xpProgress.coerceIn(0f, 1f))
                    .background(
                        Brush.horizontalGradient(
                            listOf(GodscarColors.XpStart, GodscarColors.XpEnd)
                        )
                    )
            )
        }
    }
}

@Composable
private fun PlayerHealthLine(
    currentHp: Int,
    maxHp: Int
) {
    val progress = (currentHp.toFloat() / maxHp.coerceAtLeast(1)).coerceIn(0f, 1f) * 0.86f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.health_label),
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF9C9078)
        )
        GsProgressBar(
            progress = progress,
            fillBrush = Brush.horizontalGradient(
                listOf(GodscarColors.HealthStart, GodscarColors.HealthEnd)
            ),
            height = 9.dp,
            borderColor = Color(0xFF816343),
            trackColor = Color(0xFF191B16),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "$currentHp / $maxHp",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 10.sp,
                fontFeatureSettings = "tnum"
            ),
            color = Color(0xFFD7CDBB)
        )
    }
}

@Composable
private fun ZoneSelectorLine(
    zoneTitle: String,
    rotationText: String,
    onOpenZonePanel: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .minimumInteractiveComponentSize()
                .clip(RoundedCornerShape(6.dp))
                .clickable(onClick = onOpenZonePanel)
                .padding(vertical = 4.dp, horizontal = 2.dp)
                .testTag("zone_selector_button"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Column(modifier = Modifier.weight(1f, fill = false)) {
                Text(
                    text = stringResource(R.string.current_zone_label),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFA59378)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = zoneTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFECDDC1),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_down),
                contentDescription = "Select zone",
                tint = Color(0xFFCDAB78),
                modifier = Modifier.size(15.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Surface(
            color = Color(0xFF221D17),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, Color(0xFF665037))
        ) {
            Text(
                text = rotationText,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFeatureSettings = "tnum",
                    letterSpacing = 1.1.sp
                ),
                color = Color(0xFFD8BF96),
                modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
            )
        }
    }
}

@Composable
private fun CombatArena(
    enemy: Enemy,
    biomeArtResId: Int?,
    enemyCurrentHp: Int,
    enemyMaxHp: Int,
    liveDps: String,
    hitFlashSource: DamageSource?,
    hitFlashNonce: Long,
    floatingDamages: List<FloatingDamage>
) {
    val arenaShape = RoundedCornerShape(8.dp)
    val hitAnim = remember { Animatable(0f) }

    LaunchedEffect(hitFlashNonce) {
        if (hitFlashNonce > 0L) {
            hitAnim.snapTo(1f)
            hitAnim.animateTo(0f, animationSpec = tween(durationMillis = 220))
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(10f / 11f)
            .clip(arenaShape)
            .background(
                Brush.radialGradient(
                    listOf(Color(0xFF4B5144), Color(0xFF292B29), Color(0xFF151816))
                )
            )
            .border(2.dp, Color(0xFF6B5537), arenaShape)
            .testTag("combat_arena")
    ) {
        val arenaWidthPx = constraints.maxWidth.toFloat()
        val arenaHeightPx = constraints.maxHeight.toFloat()

        // Biome art or atmospheric placeholder
        if (biomeArtResId != null) {
            Image(
                painter = painterResource(id = biomeArtResId),
                contentDescription = "${enemy.zone} biome illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.radialGradient(
                            listOf(Color(0xFF3E4A45), Color(0xFF242D2B), Color(0xFF131817))
                        )
                    )
            )
            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 66.dp, end = 10.dp),
                color = Color(0xD917130E),
                shape = RoundedCornerShape(3.dp),
                border = BorderStroke(1.dp, Color(0x948E764E))
            ) {
                Text(
                    text = stringResource(R.string.biome_placeholder),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp),
                    color = Color(0xFFE0C7A3),
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp)
                )
            }
        }

        // Atmospheric scene vignette
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color(0x450A0A0B),
                        0.55f to Color.Transparent,
                        1f to Color(0xB80A0909)
                    )
                )
        )

        // Inner bronze hairline frame
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
                .border(1.dp, Color(0x45C7A16B), RoundedCornerShape(4.dp))
        )

        // Upper-left cast stack
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 11.dp)
                .fillMaxWidth(0.55f),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            GodscarMockData.abilities.forEach { ability ->
                CastAbilityRow(ability = ability)
            }
        }

        // Upper-right status chips
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 9.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            GsChip(
                text = enemy.status,
                accentColor = enemy.damageType.color,
                leadingGlyph = "✦"
            )
            GsChip(
                text = enemy.damageType.displayName,
                accentColor = DamageSource.FROST.color,
                leadingGlyph = "❄"
            )
        }

        // Ambient glow behind enemy
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-6).dp)
                .fillMaxWidth(0.56f)
                .fillMaxHeight(0.32f)
                .background(
                    Brush.radialGradient(
                        listOf(
                            (hitFlashSource?.color ?: Color(0xFFE7A864)).copy(
                                alpha = 0.16f + hitAnim.value * 0.28f
                            ),
                            Color.Transparent
                        )
                    )
                )
        )

        // Center Enemy art or dashed placeholder frame
        val hitScale = 1f + hitAnim.value * 0.04f
        val hitGlowColor = (hitFlashSource ?: enemy.damageType).color
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-8).dp)
                .scale(hitScale),
            contentAlignment = Alignment.Center
        ) {
            if (enemy.artResId != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.54f)
                        .fillMaxHeight(0.50f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = enemy.artResId),
                        contentDescription = "${enemy.name} illustration",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                    if (hitAnim.value > 0.01f) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(14.dp))
                                .background(hitGlowColor.copy(alpha = hitAnim.value * 0.24f))
                        )
                    }
                }
            } else {
                EnemyArtPlaceholder(
                    hitAlpha = hitAnim.value,
                    hitColor = hitGlowColor,
                    modifier = Modifier
                        .fillMaxWidth(0.38f)
                        .fillMaxHeight(0.46f)
                )
            }
        }

        // Floating damage text layer
        floatingDamages.forEach { dmg ->
            FloatingDamageText(
                damage = dmg,
                arenaWidthPx = arenaWidthPx,
                arenaHeightPx = arenaHeightPx
            )
        }

        // Bottom Enemy Summary + LIVE DPS Badge
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Surface(
                modifier = Modifier.weight(1f),
                color = Color(0xEC1B1612),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, Color(0xFF8A6C45))
            ) {
                Column(modifier = Modifier.padding(horizontal = 9.dp, vertical = 7.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = enemy.name,
                            style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp),
                            color = Color(0xFFF6E7CC),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, fill = false)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = enemy.kind,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp),
                            color = Color(0xFFC6B395),
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.enemy_health_label),
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                            color = Color(0xFFC6B395)
                        )
                        Text(
                            text = "${"%,d".format(enemyCurrentHp)} / ${"%,d".format(enemyMaxHp)}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontFeatureSettings = "tnum"
                            ),
                            color = Color(0xFFC6B395)
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    GsProgressBar(
                        progress = enemyCurrentHp.toFloat() / enemyMaxHp.coerceAtLeast(1),
                        fillBrush = Brush.horizontalGradient(
                            listOf(GodscarColors.EnemyHpStart, GodscarColors.EnemyHpEnd)
                        ),
                        height = 8.dp,
                        borderColor = Color(0xFF80573A),
                        trackColor = Color(0xFF181411),
                        angled = false
                    )
                }
            }

            Surface(
                modifier = Modifier.width(70.dp),
                color = Color(0xEE181512),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, Color(0xFF8A6C45))
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 7.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stringResource(R.string.live_dps_label),
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 7.sp),
                        color = Color(0xFFBA9F77)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = liveDps,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontFeatureSettings = "tnum"
                        ),
                        color = Color(0xFFF0C488)
                    )
                }
            }
        }
    }
}

@Composable
private fun CastAbilityRow(ability: CastAbility) {
    val infiniteTransition = rememberInfiniteTransition(label = ability.name)
    val rawProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ability.castTimeMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "${ability.name}_progress"
    )
    val progress = (rawProgress + ability.offsetFraction) % 1f
    val rowAlpha = if (ability.isAutoAttack) 0.77f else 1f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(rowAlpha)
            .clip(RoundedCornerShape(3.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xDB17120E), Color(0xA416130B), Color.Transparent)
                )
            )
            .padding(start = 3.dp, end = 6.dp, top = 2.dp, bottom = 3.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            GsSlotFrame(
                size = 20.dp,
                cut = 4.dp,
                borderColor = ability.source.color.copy(alpha = 0.65f)
            ) {
                Text(
                    text = ability.glyph,
                    fontSize = 10.sp,
                    color = ability.source.color
                )
            }
            Text(
                text = ability.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = if (ability.isAutoAttack) 8.5.sp else 9.5.sp,
                    fontWeight = if (ability.isAutoAttack) FontWeight.Medium else FontWeight.SemiBold
                ),
                color = if (ability.isAutoAttack) Color(0xFFCCBAA3) else Color(0xFFF1E5D1),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (ability.isAutoAttack) 2.dp else 3.dp)
                .background(Color(0x45A77950))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                    .background(
                        Brush.horizontalGradient(
                            listOf(ability.source.color, Color.White.copy(alpha = 0.8f))
                        )
                    )
            )
        }
    }
}

@Composable
private fun EnemyArtPlaceholder(
    hitAlpha: Float,
    hitColor: Color,
    modifier: Modifier = Modifier
) {
    val dashColor = if (hitAlpha > 0.05f) hitColor else Color(0x73BD9A61)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0x18BA965D), Color(0x5C16130E), Color(0x40080908))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = dashColor,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(12.dp.toPx()),
                style = Stroke(
                    width = 1.5.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 8f), 0f)
                )
            )
        }
        if (hitAlpha > 0.01f) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(hitColor.copy(alpha = hitAlpha * 0.25f))
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_enemy_emblem),
                contentDescription = null,
                tint = Color(0xBAE3C697),
                modifier = Modifier.size(36.dp)
            )
            Text(
                text = stringResource(R.string.enemy_placeholder),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp, lineHeight = 11.sp),
                color = Color(0xFFD7BB93),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun FloatingDamageText(
    damage: FloatingDamage,
    arenaWidthPx: Float,
    arenaHeightPx: Float
) {
    val riseAnim = remember(damage.id) { Animatable(0f) }
    LaunchedEffect(damage.id) {
        riseAnim.animateTo(1f, animationSpec = tween(durationMillis = 1100))
    }
    val alpha = when {
        riseAnim.value < 0.15f -> riseAnim.value / 0.15f
        riseAnim.value > 0.72f -> (1f - riseAnim.value) / 0.28f
        else -> 1f
    }.coerceIn(0f, 1f)

    val xPx = (arenaWidthPx * damage.xFraction).roundToInt()
    val yPx = (arenaHeightPx * damage.yFraction - riseAnim.value * 64f).roundToInt()

    Text(
        text = damage.text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = LoraFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = if (damage.isCrit) 20.sp else 15.sp
        ),
        color = damage.source.color,
        modifier = Modifier
            .offset { IntOffset(xPx, yPx) }
            .alpha(alpha)
    )
}

@Composable
private fun ActivityFeedCard(
    events: List<FeedEvent>,
    isDetailsExpanded: Boolean,
    onOpenFilter: () -> Unit,
    onToggleDetails: () -> Unit
) {
    GsChamferedPanel(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("activity_feed_card"),
        cut = 13.dp,
        contentPadding = PaddingValues(12.dp),
        interiorBrush = GsFeedSurfaceBrush
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.activity_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFE8D4B0)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(GodscarColors.GoodBright)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(R.string.activity_live),
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                    color = GodscarColors.Good
                )
                Spacer(modifier = Modifier.weight(1f))
                GsIconButton(
                    iconResId = R.drawable.ic_filter,
                    contentDescription = "Feed options",
                    onClick = onOpenFilter,
                    size = 38.dp,
                    iconSize = 16.dp,
                    modifier = Modifier.testTag("feed_filter_button")
                )
            }

            HorizontalDivider(color = Color(0x80846040))

            if (events.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No events match current filter options.",
                        style = MaterialTheme.typography.bodySmall,
                        color = GodscarColors.TextMuted
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(164.dp),
                    userScrollEnabled = true
                ) {
                    items(events, key = { it.id }) { event ->
                        FeedEventRow(event = event)
                    }
                }
            }

            HorizontalDivider(color = Color(0xFF4B3C2A))
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                GsButton(
                    onClick = onToggleDetails,
                    variant = GsButtonVariant.SECONDARY,
                    cut = 6.dp,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.testTag("session_details_button")
                ) {
                    Text(
                        text = if (isDetailsExpanded) {
                            "${stringResource(R.string.session_details_hide)} ⌃"
                        } else {
                            "${stringResource(R.string.session_details_show)} ⌄"
                        },
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 10.sp),
                        color = Color(0xFFE4D1AE)
                    )
                }
            }
        }
    }
}

@Composable
private fun FeedEventRow(event: FeedEvent) {
    val accent = event.highlightColor ?: event.type.color
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GsSlotFrame(
            size = 24.dp,
            cut = 4.dp,
            borderColor = Color(0xFF745638)
        ) {
            Text(
                text = event.icon,
                fontSize = 10.sp,
                color = accent
            )
        }

        Text(
            text = buildAnnotatedString {
                append(event.prefix)
                withStyle(SpanStyle(color = accent, fontWeight = FontWeight.Bold)) {
                    append(event.highlight)
                }
                append(event.suffix)
            },
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = Color(0xFFD9CBB6),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = event.timeLabel,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                letterSpacing = 0.sp,
                fontFeatureSettings = "tnum"
            ),
            color = Color(0xFF877964)
        )
    }
    HorizontalDivider(color = Color(0x4F5E4835))
}

@Composable
private fun SessionDetailsCard(
    state: GodscarUiState,
    onOpenResetConfirm: () -> Unit
) {
    val metrics = state.sessionMetrics
    GsChamferedPanel(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("session_details_card"),
        cut = 12.dp,
        contentPadding = PaddingValues(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Session details",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFE8D4B0)
                    )
                    Text(
                        text = "TRACKING SINCE SESSION START",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = Color(0xFFA99373)
                    )
                }
                Text(
                    text = state.formattedSessionClock,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontFeatureSettings = "tnum"
                    ),
                    color = Color(0xFFA99373)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 2-column metric grid
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    MetricTile(
                        label = "XP / hour",
                        value = metrics.xpPerHour,
                        note = metrics.xpSessionNote,
                        modifier = Modifier.weight(1f)
                    )
                    MetricTile(
                        label = "Kills",
                        value = metrics.kills.toString(),
                        note = "Enemies defeated",
                        modifier = Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    MetricTile(
                        label = "Materials",
                        value = metrics.materials,
                        note = "Items collected",
                        modifier = Modifier.weight(1f)
                    )
                    MetricTile(
                        label = "Currency",
                        value = metrics.currency,
                        note = "Gold earned",
                        modifier = Modifier.weight(1f)
                    )
                }
                MetricTile(
                    label = "Total damage",
                    value = metrics.totalDamage,
                    note = "Across this session",
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = Color(0xFF4B3C2A))
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Damage by source",
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFFE8D4B0)
            )
            Spacer(modifier = Modifier.height(8.dp))

            metrics.breakdown.forEach { entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = entry.source.displayName,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = entry.source.color,
                        modifier = Modifier.width(64.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Color(0xFF3D3429))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(entry.sharePercent / 100f)
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(entry.source.color, entry.source.color.copy(alpha = 0.7f))
                                    )
                                )
                        )
                    }
                    Text(
                        text = entry.totalFormatted,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp,
                            fontFeatureSettings = "tnum"
                        ),
                        color = Color(0xFFD8DFDB),
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(68.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color(0xFF4B3C2A))
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Start a new session count.",
                    style = MaterialTheme.typography.bodySmall,
                    color = GodscarColors.TextMuted
                )
                GsButton(
                    onClick = onOpenResetConfirm,
                    variant = GsButtonVariant.DANGER,
                    cut = 5.dp,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    modifier = Modifier.testTag("reset_session_button")
                ) {
                    Text(
                        text = "RESET SESSION",
                        style = MaterialTheme.typography.labelLarge.copy(fontSize = 10.sp),
                        color = Color(0xFFE4AD9B)
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricTile(
    label: String,
    value: String,
    note: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = Color(0xFF1A1713),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color(0xFF55432F))
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 9.dp)) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                color = Color(0xFFA99373)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontFeatureSettings = "tnum"
                ),
                color = Color(0xFFF0DFC1)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = note,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 9.sp),
                color = Color(0xFF96846D)
            )
        }
    }
}
