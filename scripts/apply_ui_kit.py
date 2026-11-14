#!/usr/bin/env python3
"""Apply GS-UI-1.0 to the approved Fight skeleton. Visual presentation only."""
from pathlib import Path
import re
import shutil
import sys

ROOT = Path(__file__).resolve().parent.parent
KIT = Path(sys.argv[1]).resolve()
assert (KIT / "styles/godscar-ui.css").is_file(), KIT
assert (ROOT / "index.html").is_file()

ui_dir = ROOT / "assets/ui"
ui_dir.mkdir(parents=True, exist_ok=True)
shutil.copy2(KIT / "styles/godscar-ui.css", ui_dir / "godscar-ui.css")
for folder in ("icons", "ornaments"):
    shutil.copytree(KIT / folder, ui_dir / folder, dirs_exist_ok=True)

html_file = ROOT / "index.html"
s = html_file.read_text(encoding="utf-8")
assert "gs-ui-integration" not in s, "Integration already applied"
assert '<body>' in s and 'id="zonePanel"' in s and 'function toggleEnemy' in s
assert '1c3adf' not in s, "Unexpected baseline marker"

def swap(before, after, count=1):
    global s
    assert s.count(before) >= count, f"Missing HTML source: {before[:120]}"
    s = s.replace(before, after, count)

def regex_once(pattern, replacement):
    global s
    s, count = re.subn(pattern, replacement, s, count=1, flags=re.S)
    assert count == 1, f"Missing regex match: {pattern[:80]}"

swap("<body>", '<body class="gs-theme">')
swap('<section class="feed-card"', '<section class="feed-card gs-feed-shell"')
swap('<div class="feed-head">', '<div class="feed-head gs-feed__heading">')
swap('<div class="feed-list" id="feedList"', '<div class="feed-list gs-feed__list" id="feedList"')
swap('<section class="details-card" id="detailsCard"', '<section class="details-card gs-panel gs-panel--recessed" id="detailsCard"')
swap('<nav class="bottom-nav"', '<nav class="bottom-nav gs-navbar"')
swap('class="nav-button active"', 'class="nav-button gs-nav-item active"')
s = s.replace('class="nav-button"', 'class="nav-button gs-nav-item"')
swap('class="settings-button"', 'class="settings-button gs-button gs-button--icon"')
swap('class="icon-button"', 'class="icon-button gs-button gs-button--icon"')
s = s.replace('class="close-button"', 'class="close-button gs-button gs-button--icon"')
s = s.replace('class="primary-button"', 'class="primary-button gs-button gs-button--active"')
s = s.replace('class="secondary-button"', 'class="secondary-button gs-button"')
s = s.replace('class="reset-button"', 'class="reset-button gs-button gs-button--danger"')
swap('class="details-button" id="detailsButton"', 'class="details-button gs-button" id="detailsButton"')
s = s.replace('class="panel"', 'class="panel gs-panel gs-panel--recessed gs-dialog"')
swap('class="panel recap-panel"', 'class="panel gs-panel gs-panel--recessed gs-dialog recap-panel"')
swap('<select id="zoneSelect" onchange="browseZone(this.value)"></select>',
     '<div class="gs-select"><select id="zoneSelect" onchange="browseZone(this.value)"></select></div>')
swap('class="xp-rail" aria-label="Experience progress"', 'class="xp-rail gs-progress gs-progress--xp gs-progress--thin" style="--value:37%" aria-label="Experience progress"')
swap('<div class="health-track"><i></i></div>', '<div class="health-track gs-progress gs-progress--health gs-progress--thin" style="--value:86%"><i class="gs-progress__fill"></i></div>')
swap('<div class="enemy-health-track"><i id="enemyHealthFill"></i></div>', '<div class="enemy-health-track gs-progress gs-progress--thin" style="--bar-color:#ab623d"><i class="gs-progress__fill" id="enemyHealthFill"></i></div>')
swap('<div class="cast-stack" aria-label="Automatic combat abilities">', '<div class="cast-stack" aria-label="Automatic combat abilities">')
for source in ("fire", "frost", "holy", "poison", "arcane", "physical"):
    if source == "physical":
        swap('class="cast-row attack-row physical"', 'class="cast-row attack-row physical gs-source--physical"')
    else:
        swap('class="cast-row ' + source + '"', 'class="cast-row ' + source + ' gs-source--' + source + '"')
s = s.replace('class="skill-icon"', 'class="skill-icon gs-slot gs-slot--tiny"')
s = s.replace('class="effect-chip fire"', 'class="effect-chip fire gs-chip gs-chip--status gs-source--fire"')
s = s.replace('class="effect-chip frost"', 'class="effect-chip frost gs-chip gs-chip--status gs-source--frost"')
swap('class="effect-chip \'+(colorMap[e.type]||"holy")+\'"', 'class="effect-chip gs-chip gs-chip--status gs-source--\'+(colorMap[e.type]||"holy")+\'"')
swap('class="event \'+e.type+\'"', 'class="event gs-feed__row \'+e.type+\'"')
swap('class="event-icon"', 'class="event-icon gs-event-icon"', count=1)
swap('class="event-text"', 'class="event-text gs-event-text"', count=1)
swap('class="event-time"', 'class="event-time"', count=1)
# Four dynamic strings are not functionally changed; CSS classes only.

regex_once(r'(<button class="settings-button[^"]*"[^>]*>)\s*<svg.*?</svg>',
   r'\1<img class="gs-icon" src="assets/ui/icons/settings.svg" alt="" aria-hidden="true">')
regex_once(r'(<button class="icon-button[^"]*"[^>]*>)\s*<svg.*?</svg>',
   r'\1<img class="gs-icon" src="assets/ui/icons/filter.svg" alt="" aria-hidden="true">')
regex_once(r'<svg class="chevron".*?</svg>',
   '<img class="chevron gs-icon" src="assets/ui/icons/chevron-down.svg" alt="" aria-hidden="true">')
for title, icon in [("Home","home"), ("Character","character"), ("Stash","stash"), ("Forge","forge"), ("Navigation","navigation")]:
    # Keep exact original onclick and aria-current attributes for each nav entry.
    regex_once(r'(<button class="nav-button gs-nav-item[^"]*"[^>]*>)\s*<svg.*?</svg>(<span>'+title+r'</span>)',
        r'\1<img class="gs-icon" src="assets/ui/icons/'+icon+r'.svg" alt="" aria-hidden="true">\2')
# Give every existing close action a consistent library icon. Preserve onclick.
s, n = re.subn(r'(<button class="close-button gs-button gs-button--icon"[^>]*>)×(</button>)',
    r'\1<img class="gs-icon" src="assets/ui/icons/close.svg" alt="" aria-hidden="true">\2', s)
assert n >= 4, f"Close buttons missing ({n})"

# Existing settings recap control continues to use the same id and change handler.
swap('<div class="settings-toggle"><label for="recapSetting">Show launch recap</label><input id="recapSetting" type="checkbox" checked onchange="setRecapSetting(this.checked)"></div>',
     '<div class="settings-toggle"><label for="recapSetting">Show launch recap</label><label class="gs-toggle"><input id="recapSetting" type="checkbox" checked onchange="setRecapSetting(this.checked)"><span class="gs-toggle__track" aria-hidden="true"></span></label></div>')
# Preserve all non-UI gameplay-free scripted mock behavior.
adapter = """
<link rel="stylesheet" href="assets/ui/godscar-ui.css">
<style id="gs-ui-integration">
/* GS-UI-1.0 presentation adapter. This retains original Fight skeleton DOM/events. */
body.gs-theme {background:radial-gradient(ellipse 90% 48% at 50% -12%,#57442a62 0,transparent 78%),#0c0b09}
.gs-theme .feed-card.gs-feed-shell {padding:12px 12px 10px;border:0;border-radius:0;margin-top:12px}
.gs-theme .feed-card.gs-feed-shell .feed-head {padding:0 0 8px;margin:0 0 5px}
.gs-theme .feed-card.gs-feed-shell .feed-list.gs-feed__list {overflow:auto;max-height:240px}
.gs-theme .feed-list .event.gs-feed__row {grid-template-columns:24px minmax(0,1fr) auto;gap:8px;padding:5px 0;min-height:34px}
.gs-theme .feed-list .event.gs-feed__row .event-icon {border-color:#745638}
.gs-theme .details-card.gs-panel {padding:14px;border:0;border-radius:0}
.gs-theme .panel.gs-panel.gs-dialog {padding:0;border:0;border-radius:0;max-height:84dvh}
.gs-theme .panel.gs-panel.gs-dialog .panel-body {position:relative;z-index:1}
.gs-theme .panel.gs-panel.gs-dialog .panel-head {position:relative;z-index:1}
.gs-theme .gs-button {font-size:10px;letter-spacing:.035em}
.gs-theme .settings-button.gs-button--icon,.gs-theme .icon-button.gs-button--icon,.gs-theme .close-button.gs-button--icon{min-height:38px;width:38px;min-width:38px;padding:9px;border:0;border-radius:0}
.gs-theme .icon-button.gs-button--icon,.gs-theme .close-button.gs-button--icon{min-height:33px;width:33px;min-width:33px;padding:7px}
.gs-theme .settings-button .gs-icon,.gs-theme .icon-button .gs-icon,.gs-theme .close-button .gs-icon{width:19px;height:19px}
.gs-theme .gs-navbar.bottom-nav {padding:3px 5px;gap:2px}
.gs-theme .nav-button.gs-nav-item{min-height:52px;border-radius:0}
.gs-theme .nav-button.gs-nav-item.active{background:linear-gradient(160deg,#5b3d27,#33251f);box-shadow:inset 0 0 0 1px #b57b44;color:#f3d9af}
.gs-theme .nav-button.gs-nav-item .gs-icon{width:21px;height:21px}
.gs-theme .gs-select {margin:3px 0 9px}
.gs-theme .gs-select select {font-size:12px}
.gs-theme .zone-button .gs-icon {display:inline-block;width:14px;height:14px}
.gs-theme .xp-rail.gs-progress{position:absolute;left:0;right:0;bottom:0;height:5px;padding:0;border:0;clip-path:none;background:#302519}
.gs-theme .xp-rail.gs-progress i{width:var(--value);height:100%;background:linear-gradient(90deg,#875c31,#e1bb7d)}
.gs-theme .health-track.gs-progress{height:9px;padding:1px;border:1px solid #816343}
.gs-theme .health-track.gs-progress i {height:100%;width:var(--value);background:linear-gradient(90deg,#466d41,#9bc58a)}
.gs-theme .enemy-health-track.gs-progress{height:9px;padding:1px;border:1px solid #80573a}
.gs-theme .enemy-health-track.gs-progress i{background:linear-gradient(90deg,#914029,#dfa165);height:100%}
.gs-theme .cast-row{grid-template-columns:22px minmax(0,1fr);grid-template-rows:20px 3px;gap:3px 6px;padding:1px 4px 3px 2px;border-left-color:#a77950}
.gs-theme .cast-row .skill-icon.gs-slot{width:21px;height:21px;aspect-ratio:1;grid-column:1;grid-row:1;--ui-cut:5px;font-size:11px;line-height:1}
.gs-theme .cast-row strong{grid-column:2;grid-row:1;align-self:center}
.gs-theme .cast-row .cast-track{grid-column:1 / -1;grid-row:2;height:3px;border:0;background:linear-gradient(90deg,#a7795045,#18161800);clip-path:polygon(0 0,calc(100% - 7px) 0,100% 50%,calc(100% - 7px) 100%,0 100%)}
.gs-theme .cast-row .cast-track i{background:linear-gradient(90deg,var(--type),color-mix(in srgb,var(--type),white 25%))}
.gs-theme .cast-row.attack-row{opacity:.77}
.gs-theme .effect-chip.gs-chip{font-size:8px;padding:3px 5px;border-radius:2px;background:#181416db}
.gs-theme .effect-chip.gs-chip.gs-chip--status{color:var(--source,var(--holy))}
.gs-theme .recap-panel .recap-footer{position:relative;z-index:1}
.gs-theme .gs-panel .rarity-rare{color:#79b8e5}
.gs-theme .gs-panel .rarity-epic{color:#c08af1}
.gs-theme .gs-panel .rarity-legendary{color:#e3bc6e}
@media(max-width:360px){.gs-theme .cast-row{grid-template-columns:20px minmax(0,1fr)}.gs-theme .cast-row .skill-icon.gs-slot{width:19px;height:19px}.gs-theme .nav-button.gs-nav-item{min-height:49px}}
</style>
"""
swap("</style>\n</head>", "</style>\n" + adapter + "</head>")
html_file.write_text(s, encoding="utf-8")

assert s.count("function toggleEnemy") == 1
assert s.count("function paintEnemy") == 1
assert s.count("function setRecapSetting") == 1
assert s.count("setInterval(cycleFight,4200)") == 1
assert "sim.js" not in s, "Do not wire the simulation"
print("Integrated GS-UI-1.0 kit into current Fight skeleton:", len(s), "bytes")
print("Copied icons:", len(list((ui_dir / "icons").glob("*.svg"))))
