# Godscar — Current UI + Asset Design
**Main is the current project baseline** for the Fight/Character skeleton, UI asset work, and design documentation. This is NOT the old Godot project or the former browser game.

Start with [docs/NEW-PROJECT-START-HERE.md](docs/NEW-PROJECT-START-HERE.md), then [AGENTS.md](AGENTS.md), [docs/FIGHT-SCREEN-DIRECTION.md](docs/FIGHT-SCREEN-DIRECTION.md), and [docs/ASSET-MANIFEST.md](docs/ASSET-MANIFEST.md).

- UI: root `index.html`, `assets/ui/`; **fake values only**.
- Design: `docs/ITEM-SYSTEM-DIRECTION.md` and `docs/WORLD-PROGRESSION-DIRECTION.md`.
- Disconnected simulation snapshot: `simulation/reference-sim-v8.js`, copied unchanged from `fytchx/Godscar@slice/paladin-defense` commit `b02482ded4e118b0b2422677c39463d37dcc9f2d`. Canonical test harness remains there; do not wire it to UI without explicit approval.
- Existing Railway preview URL: https://godscar-playtest-ui-godscarplaytest.up.railway.app/ (verify service branch and deployed SHA before claiming main is live; it previously tracked `ui/fight-skeleton`).

The previous actualart*.zip archives and outdated root sim.js were deliberately excluded from the current main tree to keep this project focused. They remain in Git history, not in the current working tree. No gameplay modifications, bot runs, or deployments are implied by this repository handoff.
