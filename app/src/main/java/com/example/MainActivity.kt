package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.ActiveDialog
import com.example.ui.GodscarViewModel
import com.example.ui.components.GodscarBottomNavBar
import com.example.ui.dialogs.GodscarDialogHost
import com.example.ui.screens.CharacterOverlay
import com.example.ui.screens.FightScreen
import com.example.ui.theme.GodscarColors
import com.example.ui.theme.GodscarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(GodscarColors.Page.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(GodscarColors.Page.toArgb())
        )
        setContent {
            GodscarTheme {
                GodscarApp()
            }
        }
    }
}

@Composable
fun GodscarApp(
    viewModel: GodscarViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.toastMessages.collect { message ->
            snackbarHostState.currentSnackbarData?.dismiss()
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(GodscarColors.Page),
        containerColor = GodscarColors.Page,
        contentWindowInsets = WindowInsets.safeDrawing.only(
            WindowInsetsSides.Top + WindowInsetsSides.Horizontal
        ),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Surface(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    color = Color(0xFF282116),
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, Color(0xFFA17B4C)),
                    shadowElevation = 8.dp
                ) {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = Color(0xFFF2DDBA),
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    )
                }
            }
        },
        bottomBar = {
            GodscarBottomNavBar(
                isCharacterActive = state.isCharacterOpen,
                onHomeClick = { viewModel.closeCharacter() },
                onCharacterClick = { viewModel.openCharacter() },
                onStashClick = { viewModel.emitToast("Stash screen will be built separately.") },
                onForgeClick = { viewModel.emitToast("Forge screen will be built separately.") },
                onNavigationClick = { viewModel.emitToast("Navigation screen is to be designed.") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            FightScreen(
                state = state,
                onOpenSettings = { viewModel.openDialog(ActiveDialog.Settings) },
                onOpenZonePanel = { viewModel.openZonePanel() },
                onOpenFeedFilter = { viewModel.openDialog(ActiveDialog.FeedFilter) },
                onToggleSessionDetails = { viewModel.toggleSessionDetails() },
                onOpenResetConfirm = { viewModel.openDialog(ActiveDialog.ResetConfirm) }
            )

            AnimatedVisibility(
                visible = state.isCharacterOpen,
                enter = fadeIn() + slideInVertically { it / 10 },
                exit = fadeOut() + slideOutVertically { it / 10 }
            ) {
                CharacterOverlay(
                    state = state,
                    onClose = { viewModel.closeCharacter() },
                    onSelectMainTab = { viewModel.selectCharacterTab(it) },
                    onSelectInventoryTab = { viewModel.selectInventorySubTab(it) },
                    onInspectSlot = { slot, item -> viewModel.inspectEquipmentSlot(slot, item) },
                    onInspectItem = { item -> viewModel.inspectInventoryItem(item) },
                    onOpenDetailedStats = { viewModel.setDetailedStatsOpen(true) },
                    onCloseDetailedStats = { viewModel.setDetailedStatsOpen(false) },
                    onCloseItemPreview = { viewModel.closeCharacterItemPreview() }
                )
            }
        }
    }

    GodscarDialogHost(
        state = state,
        onDismiss = { viewModel.closeDialog() },
        onBrowseZone = { viewModel.browseZone(it) },
        onToggleEnemy = { viewModel.toggleEnemy(it) },
        onSetRecapPref = { viewModel.setRecapEnabled(it) },
        onToggleFilter = { type, enabled -> viewModel.toggleFeedFilter(type, enabled) },
        onConfirmReset = { viewModel.confirmSessionReset() },
        onOpenLootPreview = { viewModel.openLootPreview(it) },
        onShowStashStub = { viewModel.emitToast("Stash screen will be built separately.") }
    )
}
