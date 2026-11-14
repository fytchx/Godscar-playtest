# GODSCAR — New Project Start Here
**Current project only • 2026-11-13 • MAIN is the handoff branch**
This file is the primary brief for a fresh ChatGPT project or AI collaborator.

## What belongs in this project
1. The current phone-first **Fight + Character skeleton**, not old experimental layouts. Source: `fytchx/Godscar-playtest@main`, root `index.html`, `assets/ui/`, `docs/FIGHT-SCREEN-DIRECTION.md`.
2. The **current combat simulation** as a disconnected reference snapshot under `simulation/reference-sim-v8.js`, copied unchanged from `fytchx/Godscar@slice/paladin-defense` commit `b02482ded4e118b0b2422677c39463d37dcc9f2d`. The gameplay repository and approved test harness remain canonical for simulation. Do NOT connect it to the UI without explicit owner approval.
3. Our NEW **itemization and world design**, summarized in `docs/ITEM-SYSTEM-DIRECTION.md` and `docs/WORLD-PROGRESSION-DIRECTION.md`.
4. The **Fallfields + reusable interface asset-production project**, IDs and production status in `docs/ASSET-MANIFEST.md`.

## What is excluded
- The rejected Godot project and old `preview/browser` combat interfaces.
- Obsolete art ZIP archives and preapproved-looking mock concepts.
- Older Living Arsenal rule of an innate attack PLUS five extra skills. **Latest direction is five TOTAL weapon-owned automatic abilities.**
- Copying historical item rules, class skills, monster tuning or Ascended systems into new design as if approved.
- Simulation wiring, gameplay changes, deployments, Fandom/public publishing without owner approval.

## Established direction
- Weapon = automatically executing build. Drops carry 1–3 curated, synergistic abilities; crafting adds up to two more; cap FIVE TOTAL. All classes using a weapon receive the same weapon abilities.
- Six classes, 18 specializations: Paladin (Avenger/Templar/Justicar), Warrior (Protection/Savage/Fury), Rogue (Subtlety ranged / Assassin / Alchemist), Mage (Pyro/Frost/Lightning), Archer (Marksman/Ranger/Tracker — names proposed), Shaman (Nature/Storm/Primal — Primal name proposed).
- Shared equipment: 11 slots including main weapon and off-hand. Warrior, Rogue and Shaman dual-wield; main-hand supplies ability set, off-hand provides bounded stats/modifiers, not a second rotation.
- Five rarities: Common, Uncommon, Rare, Epic, Legendary. Common and Uncommon share appearance. Specific affix counts and exact balance values remain design proposals.
- Forge direction: Salvage, Upgrade, Reforge, Imprint. Materials are Gold, Scar Shards and targeted monster materials. Recipes learned permanently. No guaranteed loss of valuable loot to unattended overflow.
- World direction: hunting, targeted drops, region progress, bosses, short automatic dungeons, optional contracts and eventual endgame challenge.
- Fallfields source roster: Blighted Husk, Carrion Swarm, Grave Knight, Hollow Wisp, Hollow Bellkeeper. Husk and Knight are NOT current progression walls; Wisp (resistance) and Bellkeeper (damage) are the two planned walls.

## Current UI rules
- Follow current `docs/FIGHT-SCREEN-DIRECTION.md` and `AGENTS.md` on **main**.
- No hero model in Fight. Center full-body enemy in slightly-tall combat area with biome background. Slim faded cast bars, name/HP, float damage, DPS, event feed. Select 1–4 enemies within a zone.
- Character has 11 slots and Equipment/Inventory/Skills tabs. Inventory has Items/Materials; Skills and bottom Stash/Forge/Navigation remain stub/undecided where not separately approved.
- Reuse GS-UI-1.0's currently checked-in CSS, 12 utility SVG icons and two ornaments. Only produce new art for actual manifest IDs. Mobile-first, small-scale legibility and efficient rendering.
- Art outputs must be separated into **source/reference**, **transparent production asset**, and **integration-ready export**. Never claim an image sheet is finished SVG code.
- Root `index.html` uses fake values. Do not modify that limitation without explicit owner approval.

## Art workflow
Read `docs/ASSET-MANIFEST.md`, select a small batch of IDs (first batch: shared panel/button/bar/icon plus Fallfields background and one enemy), generate consistent concepts, review at phone size, then mark APPROVED and export. Do not auto-create all rarities or every specialization icon before content is locked. Keep asset names tied to stable IDs.

## Simulation and tests
The canonical simulation code and bot harness are on `fytchx/Godscar@slice/paladin-defense`. Source snapshot commit `b02482ded4e118b0b2422677c39463d37dcc9f2d` is SIM_VERSION 8. Before EACH run, record actual repository, branch, full HEAD, command, seed and fixture; do not invent success claims. Prior reports don't validate new itemization. Simulation reference in this repo is archival/disconnected.

## Git/release policy
- **main** of `fytchx/Godscar-playtest` holds the current approved visual skeleton and design/asset work, not a published gameplay application.
- Prefer focused feature branches/PRs; on explicit user authorization merge into main. Do not force-push or auto-deploy.
- The existing Railway review service may still track `ui/fight-skeleton`; moving main doesn't itself switch Railway. Do not claim deployed.
- Keep old repositories unchanged. Don't merge Godot/main, `preview/browser`, or legacy UI code. Capture owner-confirmed decisions in docs, distinguish them from examples and draft mechanics.
