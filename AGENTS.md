# Godscar Playtest AI instructions

Read this file and `docs/FIGHT-SCREEN-DIRECTION.md` before making changes. The owner has already completed the Fight-screen interview and approved that spec; do not restart it or ask the same questions.

## Source of truth

- Work in repository `fytchx/Godscar-playtest`, branch `ui/fight-skeleton`.
- Confirm the current repository and branch before editing. If you are on `main` or any other branch, stop and switch to `ui/fight-skeleton`.
- The current visual baseline is the root `index.html` inherited from commit `1c3adf44f1d77cc9549f8cc9b024ec6276cd253e` (“Refine Fight art slot spacing”). Continue from the file on this branch; do not restore the old red UI or substitute a different version.
- The owner’s approved screen direction is in `docs/FIGHT-SCREEN-DIRECTION.md`. It takes precedence over historical UI experiments and stale notes in other repositories.
- The Railway review URL in the README is now connected to `ui/fight-skeleton` (switched November 13, 2026). Its first GS-UI-1.0 deployment succeeded at commit `4bbc93b25cd7aacd1bfb95505a559487fd440cd0`. Verify the latest Railway deployment SHA before saying any subsequent branch commit is live.

## Current phase: Fight presentation and art

- Build the approved Fight screen skeleton and its art direction first.
- Use fake/sample values only. The owner has not approved simulation integration yet.
- Do not wire or modify the simulation, bot harness, game rules, saves, or balance.
- Work only on the Fight screen and its combat art slots. Leave Character, Stash, Forge, Navigation, and all other screens as their existing inactive stubs.
- Preserve the portrait, compact phone-first layout, existing visual hierarchy, and restylable CSS tokens. Do not redesign the screen or bring back the previous red version.
- Keep art slots clearly labeled until the owner approves actual assets. Do not fill them with generic AI art.
- Continue one small, reviewable change at a time. Ask at most one necessary question at a time. After a change, stop for the owner’s visual review.

## Future simulation and bot boundary

The intended future bot/simulation reference is `fytchx/Godscar` at branch `slice/paladin-defense`, commit `4e04564d9a134c1f335971c2e52d540a0f5f73c6`. At that exact commit, `web/src/sim.js` declares `SIM_VERSION = 7` and the existing bot harness is `web/test/balance.js`.

**This is a pinned reference, not permission to integrate it now.** Before any future bot run or UI connection, verify the full commit and inspect the actual simulation, harness, fixtures, and commands. Never substitute `main`, `preview/browser`, another UI preview, an old ZIP, or another simulator.

There is a known documentation conflict at that Godscar reference: its `AGENTS.md` and `START-HERE.md` describe a different active UI direction and claim `SIM_VERSION=4`, while the actual `sim.js` at the pinned commit says version 7. Do not resolve that conflict by guessing or silently following the stale version claim. Stop before reporting bot results or wiring the UI until the owner has approved a reconciled source contract.

## Scope and delivery

- Do not modify `main`, `preview/browser`, or the separate `fytchx/Godscar` repository during the current visual phase.
- Do not change other screens, add tests or reports, or perform bot testing unless the owner asks for that stage.
- Push work to `ui/fight-skeleton`. If the owner requests a review URL, verify that the deployment contains the exact branch commit before sharing it.
- Report plainly what changed and what was not verified. Never claim a test or deployment that was not actually performed.
