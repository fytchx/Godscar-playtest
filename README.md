# Godscar (Android)

Native Android (Kotlin + Jetpack Compose) rewrite of the **Godscar** Fight & Character UI skeleton, preserving the GS-UI-1.0 bronze-and-basalt aesthetic, mock combat presentation, and all interactive dialogs from `fytchx/Godscar-playtest`.

## Features Ported
- **Fight Screen**: Compact Paladin header (`LV 1`, XP rail, Settings), Player Health bar (`121 / 121`), Zone selector (`Gallowsfen · The Sunken Road`, `Ashen Hollow`, `Mourning Wood`) with rotation pill (`1 OF 3`), and a portrait combat arena with biome background art (`Gallowsfen`), full-body `Gravejaw Stalker` illustration (plus labeled placeholders for remaining zones/enemies), 5 animated weapon abilities + 1 muted auto-attack bar, status chips, floating damage popups (with `CRIT` styling), hit-impact flash, enemy HP bar, and `LIVE DPS` badge.
- **Activity Feed & Session Details**: Live auto-updating scrollable combat feed with 7 category filters (`Enemy kills`, `Items looted`, `Materials and currency`, `Level-ups`, `Deaths`, `Incoming damage`, `Status effects`) and expandable Session Details (live ticking session clock, XP/hour, kills, materials, currency, total damage, damage-by-source meter bars, and confirmed session reset).
- **Zone & Enemy Picker**: Browse any zone without interrupting combat; select 1–4 unlocked enemies within a zone or switch zones immediately on selection; locked bosses are clearly marked.
- **Launch Recap & Settings**: Offline session recap dialog with top 3 notable loot previews and "Don't show this on launch" preference persisted via Jetpack DataStore (`PreferencesRepository`), synced with the Settings toggle.
- **Character Overlay**: Opens over still-running combat with 3 tabs (`Equipment` 11-slot layout + `2,485` Gearscore + `STR`/`INT`/`DEX` + combat metrics + scrollable `Detailed Stats` sheet, `Inventory` with `Items`/`Materials` sub-tabs and item preview dialog, and `Skills` placeholder).
- **Bottom Navigation Bar**: Fixed 5-tab bar (`Home`, `Character`, `Stash`, `Forge`, `Navigation`) with stub snackbar notices for `Stash`, `Forge`, and `Navigation`.

## Architecture
- `app/src/main/java/com/example/MainActivity.kt` — Edge-to-edge Compose host, Scaffold with `WindowInsets`, snackbar host, and dialog host.
- `app/src/main/java/com/example/ui/GodscarViewModel.kt` — `StateFlow` state holder, 1s session clock, 4.2s mock combat rotation loop, and DataStore persistence.
- `app/src/main/java/com/example/ui/screens/` — `FightScreen.kt`, `CharacterOverlay.kt`.
- `app/src/main/java/com/example/ui/dialogs/GodscarDialogs.kt` — Zone picker, Launch Recap, Loot Preview, Settings, Feed Filter, Reset Confirmation.
- `app/src/main/java/com/example/ui/components/` — GS-UI-1.0 chamfered bronze/basalt panels, buttons, checkboxes, toggles, progress bars, and bottom navigation bar.
- `app/src/main/java/com/example/ui/theme/` — GS-UI-1.0 color palette and bundled `Lora` + `Inter` typography.
