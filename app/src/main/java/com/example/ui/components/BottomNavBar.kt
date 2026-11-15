package com.example.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R

@Composable
fun GodscarBottomNavBar(
    isCharacterActive: Boolean,
    onHomeClick: () -> Unit,
    onCharacterClick: () -> Unit,
    onStashClick: () -> Unit,
    onForgeClick: () -> Unit,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xF530271C), Color(0xF814120E), Color(0xFB100F0D))
                )
            )
            .navigationBarsPadding()
    ) {
        HorizontalDivider(color = Color(0xFFA77950), thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            BottomNavTab(
                label = stringResource(R.string.nav_home),
                iconResId = R.drawable.ic_home,
                active = !isCharacterActive,
                onClick = onHomeClick,
                modifier = Modifier
                    .weight(1f)
                    .testTag("nav_home")
            )
            BottomNavTab(
                label = stringResource(R.string.nav_character),
                iconResId = R.drawable.ic_character,
                active = isCharacterActive,
                onClick = onCharacterClick,
                modifier = Modifier
                    .weight(1f)
                    .testTag("nav_character")
            )
            BottomNavTab(
                label = stringResource(R.string.nav_stash),
                iconResId = R.drawable.ic_stash,
                active = false,
                onClick = onStashClick,
                modifier = Modifier
                    .weight(1f)
                    .testTag("nav_stash")
            )
            BottomNavTab(
                label = stringResource(R.string.nav_forge),
                iconResId = R.drawable.ic_forge,
                active = false,
                onClick = onForgeClick,
                modifier = Modifier
                    .weight(1f)
                    .testTag("nav_forge")
            )
            BottomNavTab(
                label = stringResource(R.string.nav_navigation),
                iconResId = R.drawable.ic_navigation,
                active = false,
                onClick = onNavigationClick,
                modifier = Modifier
                    .weight(1f)
                    .testTag("nav_navigation")
            )
        }
    }
}

@Composable
private fun BottomNavTab(
    label: String,
    @DrawableRes iconResId: Int,
    active: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgBrush = if (active) {
        Brush.linearGradient(listOf(Color(0xFF5B3D27), Color(0xFF33251F)))
    } else {
        Brush.linearGradient(listOf(Color.Transparent, Color.Transparent))
    }

    Box(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .height(52.dp)
            .background(bgBrush)
            .then(
                if (active) Modifier.border(1.dp, Color(0xFFB57B44))
                else Modifier
            )
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                tint = if (active) Color(0xFFF3D9AF) else Color(0xFFA29379),
                modifier = Modifier
                    .size(20.dp)
                    .alpha(if (active) 1f else 0.82f)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 9.sp,
                    letterSpacing = 0.2.sp
                ),
                color = if (active) Color(0xFFF3D9AF) else Color(0xFFA29379),
                maxLines = 1
            )
        }
    }
}
