# Godscar — Arsenal & Specialization Expansion

**Status:** **V0.1 CREATIVE DRAFT. NOT APPROVED. NOT IMPLEMENTED.**
**Date:** November 14, 2026
**Branch:** `design/arsenal-and-spec-trees-v0` in `fytchx/Godscar-playtest`
**Purpose:** explore a large, interlocking weapon/spell/skill-tree system before editing combat code, creating icons or deploying anything.

## Start here

1. [Combat & Build System V0](COMBAT-BUILD-SYSTEM-V0.md) — actual system architecture, status semantics, item and imprint compatibility, cross-tree spending, survival counters, fairness and deterministic simulation requirements.
2. [Weapon Ability Catalogue V0](WEAPON-ABILITIES-CATALOG-V0.md) — 40 named weapon archetypes, 120 found A/B/C abilities, 40 imprint abilities. **160 named active ability concepts** total.
3. [Specialization Trees V0](SPECIALIZATION-TREES-V0.md) — six classes, three specializations each, **216 proposed passive/keystone/capstone nodes** across 18 trees.

## Approved constraints preserved

- Main-hand owns **all** automatic abilities: 1–3 found + up to two imprinted; **five maximum**.
- Off-hand provides modifiers, not a second rotation.
- Classes and specialization nodes improve existing weapon actions; do not grant new independent autocasts.
- Five rarities; eleven equipment slots; Forge operations Salvage/Upgrade/Reforge/Imprint.
- Current Fight/Character/Stash/Forge/Navigation remains fake-data UI; disconnected sim remains untouched.
- Archer spec names and Shaman Primal are still tentative in source design.

## Deliberately unapproved

- Every new spell/ability name, effect, status definition, action timing, tree node, tree geometry, player-point economy, endgame unlock, rarity chance, affix number, crafting cost, Legendary unique and drop rate.
- The 12-node-per-tree structure is a brainstorming scaffold only.
- These `DRAFT-WA-`, `DRAFT-IM-`, and `DRAFT-SK-` IDs are **proposal IDs**, not new art manifest IDs. Existing `GS-ABL-001..005` are still awaiting approved spell definitions.
- No simulations, bot runs, UI data integration, gameplay changes, deployed revisions or final spell-art production have been done.

## Proposed review order

- First accept/reject the five-layer build grammar and whether the three trees permit mixing or require primary capstone commitment.
- Then agree on a minimal common status set and clear rules to prevent recursive effects.
- Then choose a handful of distinctive archetypes across offense, defense and elemental styles for a reproducible combat prototype.
- Then refine node counts and class identity from actual measured tradeoffs, trimming duplicate abilities before art production.
- Only then authorize any game logic, balancing, or full asset export.

## Internal checks (documentation only)

Verified draft counts: **40** archetypes × **3** named found actions = **120**; **30** family-specific + **10** general compatible imprints = **40**; **160** active named concepts total. **18** spec trees × **12** concept nodes = **216**. All 18 trees have a proposed Keystone and Capstone. Reuses the existing five example archetypes: Cinderbreaker, Bloodletter, Deadeye, Thornrunner, Stormcaller.

These are counts of written proposals, not tests proving game viability.
