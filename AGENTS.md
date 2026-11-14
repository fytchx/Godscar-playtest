# Godscar current project instructions
Read `docs/NEW-PROJECT-START-HERE.md` FIRST. This supersedes outdated branch instructions formerly present in this file.

- **Repo:** `fytchx/Godscar-playtest`, **branch:** `main` is the current Fight/Character skeleton and art-design baseline. Make focused branches/PRs for substantial work, review before merge.
- Only current Fight skeleton is authoritative, originally approved at `1c3adf44f1d77cc9549f8cc9b024ec6276cd253e`; current branch includes later Character and GS-UI changes. Do not restore obsolete red UI, Godot build, old browser UI, or old lab screens.
- `index.html` remains MOCK data only. Do NOT connect UI to simulation, gameplay logic, networking, or persistent saves without a separate owner approval.
- The actual gameplay simulation/harness source is `fytchx/Godscar@slice/paladin-defense`, latest verified source `b02482ded4e118b0b2422677c39463d37dcc9f2d` (SIM_VERSION 8). A disconnected exact code copy is at `simulation/reference-sim-v8.js`. Do not infer current item/skill mechanics from that older simulator; it predates new design.
- UI: Follow `docs/FIGHT-SCREEN-DIRECTION.md` for Fight + approved Character (11 slots; Equipment, Inventory with Items/Materials, Skills placeholder). Other bottom nav stubs remain stubs.
- Itemization: Follow `docs/ITEM-SYSTEM-DIRECTION.md`: NO extra class auto attack, maximum five weapon-owned abilities total, 1–3 found in curated packages + up to two crafted.
- World: Follow `docs/WORLD-PROGRESSION-DIRECTION.md`. Fallfields enemies and Bellkeeper verified from source; new region/dungeon designs are proposals, not implemented.
- Assets: Use `docs/ASSET-MANIFEST.md` IDs. Preserve checked-in GS-UI-1.0 SVG/CSS; only create new graphics when their purpose/style/spec is clear. Full-body enemies and simple combat backdrops; phone-first readability; avoid generic or overly photoreal art. Keep editable source and proper independent exported assets.
- Existing Railway preview service may track `ui/fight-skeleton` rather than `main`; do not change deploy config or deploy/publish without explicit owner consent.
- No simulations or bot tests without explicit request; if run, report repo, branch, SHA, command, seed, fixture and results. No claims of tests or verified balance from design examples.
- Always label VERIFIED data vs PROPOSED design vs UNDECIDED. Update docs for confirmed decisions and discoveries.
