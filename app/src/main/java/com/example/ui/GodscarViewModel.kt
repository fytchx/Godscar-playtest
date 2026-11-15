package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.GodscarMockData
import com.example.data.PreferencesRepository
import com.example.model.ActiveDialog
import com.example.model.CharacterItemPreview
import com.example.model.CharacterMainTab
import com.example.model.DamageSource
import com.example.model.Enemy
import com.example.model.EquipmentSlotType
import com.example.model.FeedEvent
import com.example.model.FeedEventType
import com.example.model.FloatingDamage
import com.example.model.GearItem
import com.example.model.InventorySubTab
import com.example.model.RecapLootItem
import com.example.model.SessionMetrics
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

data class GodscarUiState(
    val playerName: String = "Player",
    val playerLevel: Int = 1,
    val playerClass: String = "Paladin",
    val xpProgress: Float = 0.37f,
    val playerHp: Int = 121,
    val playerMaxHp: Int = 121,
    val selectedEnemyIds: List<String> = GodscarMockData.defaultSelectedEnemyIds,
    val activeRotationIndex: Int = 0,
    val browseZoneName: String = GodscarMockData.zones.first().name,
    val enemyCurrentHp: Int = 6420,
    val enemyMaxHp: Int = 10000,
    val liveDps: String = "1.2k",
    val hitFlashSource: DamageSource? = null,
    val hitFlashNonce: Long = 0L,
    val floatingDamages: List<FloatingDamage> = emptyList(),
    val feedEvents: List<FeedEvent> = GodscarMockData.initialFeedEvents,
    val feedFilters: Map<FeedEventType, Boolean> = FeedEventType.entries.associateWith { true },
    val isSessionDetailsExpanded: Boolean = false,
    val sessionElapsedSeconds: Long = 42L * 60L + 18L,
    val sessionMetrics: SessionMetrics = GodscarMockData.initialSessionMetrics,
    val showLaunchRecapPref: Boolean = true,
    val activeDialog: ActiveDialog? = null,
    val isCharacterOpen: Boolean = false,
    val characterMainTab: CharacterMainTab = CharacterMainTab.EQUIPMENT,
    val inventorySubTab: InventorySubTab = InventorySubTab.ITEMS,
    val isDetailedStatsOpen: Boolean = false,
    val characterItemPreview: CharacterItemPreview? = null
) {
    val activeEnemies: List<Enemy>
        get() = selectedEnemyIds.mapNotNull { id ->
            GodscarMockData.allEnemies.find { it.id == id }
        }.ifEmpty { listOf(GodscarMockData.allEnemies.first()) }

    val currentEnemy: Enemy
        get() {
            val list = activeEnemies
            return list[activeRotationIndex % list.size]
        }

    val rotationPositionText: String
        get() {
            val list = activeEnemies
            val index = (activeRotationIndex % list.size) + 1
            return "$index OF ${list.size}"
        }

    val visibleFeedEvents: List<FeedEvent>
        get() = feedEvents.filter { feedFilters[it.type] == true }

    val formattedSessionClock: String
        get() {
            val h = sessionElapsedSeconds / 3600
            val m = (sessionElapsedSeconds % 3600) / 60
            val s = sessionElapsedSeconds % 60
            return "%02d:%02d:%02d".format(h, m, s)
        }
}

class GodscarViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = PreferencesRepository(application.applicationContext)

    private val _uiState = MutableStateFlow(GodscarUiState())
    val uiState: StateFlow<GodscarUiState> = _uiState.asStateFlow()

    private val _toastMessages = MutableSharedFlow<String>(extraBufferCapacity = 4)
    val toastMessages: SharedFlow<String> = _toastMessages.asSharedFlow()

    private var nextEventId = 100L
    private var nextFloatId = 1L

    init {
        viewModelScope.launch {
            val showOnLaunch = prefs.getShowRecapOnce()
            _uiState.update { state ->
                state.copy(
                    showLaunchRecapPref = showOnLaunch,
                    activeDialog = if (showOnLaunch) ActiveDialog.LaunchRecap else null
                )
            }
        }

        // Observe preference changes
        viewModelScope.launch {
            prefs.showRecapFlow.collect { show ->
                _uiState.update { it.copy(showLaunchRecapPref = show) }
            }
        }

        // 1-second session clock
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                _uiState.update { it.copy(sessionElapsedSeconds = it.sessionElapsedSeconds + 1L) }
            }
        }

        // 4.2-second mock combat cycle (matches index.html cycleFight)
        viewModelScope.launch {
            while (true) {
                delay(4200L)
                cycleFight()
            }
        }
    }

    private fun cycleFight() {
        val current = _uiState.value
        val defeated = current.currentEnemy
        val nextIndex = (current.activeRotationIndex + 1) % current.activeEnemies.size.coerceAtLeast(1)
        val newHp = 6420 - Random.nextInt(260)

        val cycleSources = listOf(
            DamageSource.PHYSICAL,
            DamageSource.FIRE,
            DamageSource.HOLY,
            DamageSource.FROST,
            DamageSource.POISON,
            DamageSource.BLEED
        )
        val source = cycleSources.random()
        val isCrit = Random.nextFloat() < 0.14f
        val amount = 350 + Random.nextInt(1900)
        val formatted = formatShort(amount)
        val floatId = nextFloatId++
        val floatDamage = FloatingDamage(
            id = floatId,
            text = (if (isCrit) "CRIT " else "") + "−$formatted",
            source = source,
            isCrit = isCrit,
            xFraction = 0.25f + Random.nextFloat() * 0.48f,
            yFraction = 0.36f + Random.nextFloat() * 0.26f
        )

        val newEvents = mutableListOf<FeedEvent>()
        if (Random.nextFloat() < 0.22f) {
            newEvents += FeedEvent(
                id = nextEventId++,
                type = FeedEventType.EFFECT,
                icon = "❄",
                prefix = "",
                highlight = "Chilled",
                suffix = " effect applied.",
                timeLabel = "now"
            )
        }
        if (Random.nextFloat() < 0.35f) {
            val dmg = 15 + Random.nextInt(70)
            newEvents += FeedEvent(
                id = nextEventId++,
                type = FeedEventType.DAMAGE,
                icon = "✦",
                prefix = "",
                highlight = "−$dmg",
                suffix = " incoming damage.",
                timeLabel = "now"
            )
        }
        newEvents += FeedEvent(
            id = nextEventId++,
            type = FeedEventType.KILL,
            icon = "⚔",
            prefix = "Defeated ",
            highlight = defeated.name,
            suffix = ".",
            timeLabel = "now"
        )

        _uiState.update { state ->
            state.copy(
                activeRotationIndex = nextIndex,
                enemyCurrentHp = newHp,
                hitFlashSource = source,
                hitFlashNonce = state.hitFlashNonce + 1L,
                floatingDamages = (state.floatingDamages + floatDamage).takeLast(6),
                sessionMetrics = state.sessionMetrics.copy(kills = state.sessionMetrics.kills + 1),
                feedEvents = (newEvents + state.feedEvents).take(50)
            )
        }

        // Remove floating damage after 1.2s
        viewModelScope.launch {
            delay(1200L)
            _uiState.update { state ->
                state.copy(floatingDamages = state.floatingDamages.filterNot { it.id == floatId })
            }
        }
    }

    fun openZonePanel() {
        _uiState.update { state ->
            state.copy(
                browseZoneName = state.currentEnemy.zone,
                activeDialog = ActiveDialog.ZoneEnemies
            )
        }
    }

    fun openDialog(dialog: ActiveDialog) {
        _uiState.update { it.copy(activeDialog = dialog) }
    }

    fun closeDialog() {
        _uiState.update { it.copy(activeDialog = null) }
    }

    fun browseZone(zoneName: String) {
        _uiState.update { it.copy(browseZoneName = zoneName) }
    }

    fun toggleEnemy(enemyId: String) {
        val target = GodscarMockData.allEnemies.find { it.id == enemyId } ?: return
        if (target.locked) return

        val state = _uiState.value
        val activeZone = state.activeEnemies.firstOrNull()?.zone
        val currentIds = state.selectedEnemyIds

        val updatedIds: List<String> = if (enemyId in currentIds) {
            if (currentIds.size <= 1) {
                emitToast("Keep at least one enemy selected.")
                return
            }
            currentIds - enemyId
        } else if (activeZone != target.zone) {
            listOf(enemyId)
        } else {
            if (currentIds.size >= 4) {
                emitToast("Choose up to four enemies.")
                return
            }
            currentIds + enemyId
        }

        val newHp = 6420 - Random.nextInt(260)
        _uiState.update {
            it.copy(
                selectedEnemyIds = updatedIds,
                activeRotationIndex = 0,
                enemyCurrentHp = newHp
            )
        }
    }

    fun toggleFeedFilter(type: FeedEventType, enabled: Boolean) {
        _uiState.update { state ->
            state.copy(feedFilters = state.feedFilters + (type to enabled))
        }
    }

    fun toggleSessionDetails() {
        _uiState.update { it.copy(isSessionDetailsExpanded = !it.isSessionDetailsExpanded) }
    }

    fun confirmSessionReset() {
        val resetEvent = FeedEvent(
            id = nextEventId++,
            type = FeedEventType.LEVEL,
            icon = "✦",
            prefix = "New session ",
            highlight = "started",
            suffix = ".",
            timeLabel = "now"
        )
        _uiState.update { state ->
            state.copy(
                sessionElapsedSeconds = 0L,
                sessionMetrics = GodscarMockData.zeroSessionMetrics,
                feedEvents = listOf(resetEvent),
                activeDialog = null
            )
        }
        emitToast("Session totals reset.")
    }

    fun setRecapEnabled(enabled: Boolean) {
        _uiState.update { it.copy(showLaunchRecapPref = enabled) }
        viewModelScope.launch {
            prefs.setShowRecap(enabled)
        }
    }

    fun openLootPreview(item: RecapLootItem) {
        _uiState.update { it.copy(activeDialog = ActiveDialog.LootPreview(item)) }
    }

    fun openCharacter() {
        _uiState.update {
            it.copy(
                isCharacterOpen = true,
                characterMainTab = CharacterMainTab.EQUIPMENT,
                isDetailedStatsOpen = false,
                characterItemPreview = null
            )
        }
    }

    fun closeCharacter() {
        _uiState.update {
            it.copy(
                isCharacterOpen = false,
                isDetailedStatsOpen = false,
                characterItemPreview = null
            )
        }
    }

    fun selectCharacterTab(tab: CharacterMainTab) {
        _uiState.update {
            it.copy(
                characterMainTab = tab,
                inventorySubTab = if (tab == CharacterMainTab.INVENTORY) InventorySubTab.ITEMS else it.inventorySubTab
            )
        }
    }

    fun selectInventorySubTab(tab: InventorySubTab) {
        _uiState.update { it.copy(inventorySubTab = tab) }
    }

    fun setDetailedStatsOpen(open: Boolean) {
        _uiState.update { it.copy(isDetailedStatsOpen = open) }
    }

    fun inspectEquipmentSlot(slot: EquipmentSlotType, item: GearItem?) {
        _uiState.update {
            it.copy(
                characterItemPreview = CharacterItemPreview(
                    title = item?.name ?: slot.label,
                    subtitle = if (item != null) "${item.rarity.label.uppercase()} · ${slot.label}" else "EMPTY EQUIPMENT SLOT",
                    stats = item?.stats ?: "Nothing equipped in this slot."
                )
            )
        }
    }

    fun inspectInventoryItem(item: GearItem) {
        _uiState.update {
            it.copy(
                characterItemPreview = CharacterItemPreview(
                    title = item.name,
                    subtitle = "${item.rarity.label.uppercase()} · ${item.slot.label}",
                    stats = item.stats
                )
            )
        }
    }

    fun closeCharacterItemPreview() {
        _uiState.update { it.copy(characterItemPreview = null) }
    }

    fun emitToast(message: String) {
        _toastMessages.tryEmit(message)
    }

    private fun formatShort(n: Int): String = when {
        n >= 1_000_000_000 -> "%.1fb".format(n / 1_000_000_000.0)
        n >= 1_000_000 -> "%.1fm".format(n / 1_000_000.0)
        n >= 1_000 -> "%.1fk".format(n / 1_000.0)
        else -> n.toString()
    }
}
