# Godscar UI palette — Hollow Steel

**Decision:** APPROVED by owner on 2026-11-13.
**Scope:** Godscar user-interface surfaces and controls, not damage-source colors or item rarity colors.
**Implementation:** NOT YET VERIFIED. The Arcana-inspired branch still uses its exploratory violet styling until recolored and reviewed.

## Approved core palette

| Token | Color | UI role |
| --- | --- | --- |
| `--gs-color-ink` | `#11191D` | Main screen background |
| `--gs-color-slate` | `#203039` | Cards, menus, panels |
| `--gs-color-steel` | `#344750` | Raised surfaces, dividers, inactive elements |
| `--gs-color-seaglass` | `#79BFB3` | Active state, selection, primary interaction accent |
| `--gs-color-ivory` | `#E9ECE5` | Primary typography and high-contrast labels |

## Design lock

- Keep Arcana's cleaner component **structure and interaction patterns** while replacing its violet brand palette.
- The aesthetic is atmospheric, modern and welcoming; avoid generic fantasy filigree, dirty grunge textures, purple-over-everything or colored full-screen glows.
- Let the arena and actual combat art be the focal point. UI decoration stays quiet.
- Keep the core palette coherent between Fight, Character, inventory, recap, settings, nav, and later Forge/Navigation.
- Use semantic variables rather than replacing each hexadecimal color ad hoc.
- Keep approved damage-source colors (physical/bleed/fire/holy/frost/arcane/shadow/poison) and rarity colors independent of the interface theme.
- Check contrast and 44px tap targets, with compact labels that remain legible at 360–390px screen widths.
- All icons need the same stroke/weight, and the theme-compatible utility SVGs should not bake in obsolete bronze or Arcana violet.

## Remaining proposals (not approved)

- Exact muted text, subdued border, hover/pressed/disabled, success/warning, health and XP colors.
- Whether active selections use a filled sea-glass surface or a quiet tinted border.
- Any warm accent to supplement Hollow Steel; the palette approved here has only the five colors above.

## Next production review batch

1. **GS-UI-CMP-001–003:** shared panel, primary/secondary/disabled buttons; verify states together.
2. **GS-UI-CMP-008–010:** bottom nav, tabs, equipment slots and rarity frames.
3. **GS-UI-CMP-004–007:** health, XP, enemy and cast progress indicators.
4. **GS-UI-ICO-001–012:** evaluate Arcana-adapted utility glyphs and standardize export colors.
5. **GS-UI-CMP-012–015:** bottom sheets, picker, activity feed and recap.

Create a phone-width Fight/Character visual review first. This palette approval does not authorize gameplay wiring, a Railway deploy or a merge to `main`. Manifest production statuses remain unchanged until the owner approves real component exports.
