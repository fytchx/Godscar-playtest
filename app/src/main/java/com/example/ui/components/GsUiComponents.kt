package com.example.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.GodscarColors

/**
 * GS-UI-1.0 chamfered octagonal shape matching `clip-path: polygon(cut 0, ...)`
 */
@Composable
fun chamferedShape(cut: Dp = 12.dp): Shape {
    val cutPx = with(LocalDensity.current) { cut.toPx() }
    return GenericShape { size, _ ->
        val c = cutPx.coerceAtMost(minOf(size.width, size.height) / 2f)
        moveTo(c, 0f)
        lineTo(size.width - c, 0f)
        lineTo(size.width, c)
        lineTo(size.width, size.height - c)
        lineTo(size.width - c, size.height)
        lineTo(c, size.height)
        lineTo(0f, size.height - c)
        lineTo(0f, c)
        close()
    }
}

@Composable
fun angledTrackShape(tip: Dp = 6.dp): Shape {
    val tipPx = with(LocalDensity.current) { tip.toPx() }
    return GenericShape { size, _ ->
        val t = tipPx.coerceAtMost(size.width / 3f)
        val midY = size.height / 2f
        moveTo(t, 0f)
        lineTo(size.width - t, 0f)
        lineTo(size.width, midY)
        lineTo(size.width - t, size.height)
        lineTo(t, size.height)
        lineTo(0f, midY)
        close()
    }
}

val GsBronzeBorderBrush = Brush.linearGradient(
    colors = listOf(
        GodscarColors.BronzeLight,
        GodscarColors.BronzeMid,
        GodscarColors.BronzeDark,
        Color(0xFFAA774C),
        Color(0xFFDEB176),
        Color(0xFF634129)
    )
)

val GsPanelRecessedBrush = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF28231E),
        Color(0xFF191716),
        Color(0xFF141312)
    )
)

val GsFeedSurfaceBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFF252120),
        Color(0xFF171514),
        Color(0xFF141312)
    )
)

@Composable
fun GsChamferedPanel(
    modifier: Modifier = Modifier,
    cut: Dp = 12.dp,
    borderThickness: Dp = 1.5.dp,
    contentPadding: PaddingValues = PaddingValues(14.dp),
    interiorBrush: Brush = GsPanelRecessedBrush,
    content: @Composable BoxScope.() -> Unit
) {
    val outerShape = chamferedShape(cut)
    val innerShape = chamferedShape((cut - 1.dp).coerceAtLeast(2.dp))
    Box(
        modifier = modifier
            .clip(outerShape)
            .background(GsBronzeBorderBrush)
            .padding(borderThickness)
            .clip(innerShape)
            .background(interiorBrush)
            .padding(contentPadding),
        content = content
    )
}

enum class GsButtonVariant { DEFAULT, ACTIVE, DANGER, SECONDARY }

@Composable
fun GsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: GsButtonVariant = GsButtonVariant.DEFAULT,
    enabled: Boolean = true,
    cut: Dp = 8.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
    content: @Composable RowScope.() -> Unit
) {
    val borderBrush = when (variant) {
        GsButtonVariant.ACTIVE -> Brush.linearGradient(
            listOf(Color(0xFFFAC37C), Color(0xFFD58D40), Color(0xFF985C2C), Color(0xFFE8A15D))
        )
        GsButtonVariant.DANGER -> Brush.linearGradient(
            listOf(Color(0xFFC68070), Color(0xFF8C3F38), Color(0xFF4B2826))
        )
        GsButtonVariant.SECONDARY -> Brush.linearGradient(
            listOf(Color(0xFF8C6E49), Color(0xFF5C4731), Color(0xFF78603F))
        )
        GsButtonVariant.DEFAULT -> Brush.linearGradient(
            listOf(Color(0xFFDBA66B), Color(0xFF9A6238), Color(0xFF513727))
        )
    }
    val innerBrush = when (variant) {
        GsButtonVariant.ACTIVE -> Brush.verticalGradient(listOf(Color(0xFF785834), Color(0xFF47341F)))
        GsButtonVariant.DANGER -> Brush.verticalGradient(listOf(Color(0xFF462829), Color(0xFF28191A)))
        GsButtonVariant.SECONDARY -> Brush.verticalGradient(listOf(Color(0xFF392F23), Color(0xFF221E18)))
        GsButtonVariant.DEFAULT -> Brush.verticalGradient(listOf(Color(0xFF2F2822), Color(0xFF1A1816)))
    }
    val outerShape = chamferedShape(cut)
    val innerShape = chamferedShape((cut - 1.dp).coerceAtLeast(2.dp))

    Box(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .clip(outerShape)
            .background(borderBrush)
            .padding(1.5.dp)
            .clip(innerShape)
            .background(innerBrush)
            .clickable(enabled = enabled, role = Role.Button, onClick = onClick)
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
fun GsIconButton(
    @DrawableRes iconResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    iconSize: Dp = 19.dp,
    tint: Color = GodscarColors.Highlight
) {
    val shape = chamferedShape(7.dp)
    val innerShape = chamferedShape(6.dp)
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .size(size)
            .clip(shape)
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFFDBA66B), Color(0xFF705B3B), Color(0xFF4B3725))
                )
            )
            .padding(1.2.dp)
            .clip(innerShape)
            .background(
                Brush.linearGradient(
                    listOf(Color(0xFF383022), Color(0xFF1A1814))
                )
            )
            .clickable(role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun GsCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    boxSize: Dp = 20.dp
) {
    val outerShape = chamferedShape(4.dp)
    val innerShape = chamferedShape(3.dp)
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clickable(role = Role.Checkbox) { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(outerShape)
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFFDBA468), Color(0xFF6F482D))
                    )
                )
                .padding(1.5.dp)
                .clip(innerShape)
                .background(Color(0xFF1C191A)),
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Box(
                    modifier = Modifier
                        .size(boxSize * 0.52f)
                        .clip(chamferedShape(2.dp))
                        .background(GodscarColors.Highlight)
                )
            }
        }
    }
}

@Composable
fun GsToggleSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 24.dp else 3.dp,
        animationSpec = spring(),
        label = "toggleThumb"
    )
    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .clickable(role = Role.Switch) { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(26.dp)
                .clip(CircleShape)
                .background(if (checked) Color(0xFF5E3D22) else Color(0xFF171618))
                .border(
                    width = 1.dp,
                    color = if (checked) Color(0xFFDCA360) else Color(0xFF8E6546),
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .offset(x = thumbOffset, y = 3.dp)
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.verticalGradient(
                            if (checked) listOf(Color(0xFFF8D69C), Color(0xFFBF7E31))
                            else listOf(Color(0xFFB7AAA0), Color(0xFF70655E))
                        )
                    )
            )
        }
    }
}

@Composable
fun GsSlotFrame(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    cut: Dp = 5.dp,
    borderColor: Color? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val outerShape = chamferedShape(cut)
    val innerShape = chamferedShape((cut - 1.dp).coerceAtLeast(1.dp))
    val brush = if (borderColor != null) {
        Brush.linearGradient(listOf(borderColor, borderColor.copy(alpha = 0.55f)))
    } else {
        Brush.linearGradient(listOf(Color(0xFFF0BE77), Color(0xFF935D34), Color(0xFF603D28)))
    }
    Box(
        modifier = modifier
            .size(size)
            .clip(outerShape)
            .background(brush)
            .padding(1.2.dp)
            .clip(innerShape)
            .background(Brush.linearGradient(listOf(Color(0xFF1A1615), Color(0xFF242023)))),
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun GsProgressBar(
    progress: Float,
    fillBrush: Brush,
    modifier: Modifier = Modifier,
    height: Dp = 9.dp,
    borderColor: Color = Color(0xFF816343),
    trackColor: Color = Color(0xFF141210),
    angled: Boolean = true
) {
    val shape = if (angled) angledTrackShape(4.dp) else androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
    Box(
        modifier = modifier
            .height(height)
            .clip(shape)
            .background(trackColor)
            .border(1.dp, borderColor, shape)
            .padding(1.5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .background(fillBrush)
        )
    }
}

@Composable
fun GsChip(
    text: String,
    accentColor: Color,
    modifier: Modifier = Modifier,
    leadingGlyph: String? = null
) {
    Surface(
        modifier = modifier,
        color = Color(0xDB181416),
        shape = chamferedShape(3.dp),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.45f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (leadingGlyph != null) {
                androidx.compose.material3.Text(
                    text = leadingGlyph,
                    style = MaterialTheme.typography.labelSmall,
                    color = accentColor
                )
            }
            androidx.compose.material3.Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(
                    letterSpacing = androidx.compose.ui.unit.TextUnit(0.4f, androidx.compose.ui.unit.TextUnitType.Sp)
                ),
                color = accentColor
            )
        }
    }
}
