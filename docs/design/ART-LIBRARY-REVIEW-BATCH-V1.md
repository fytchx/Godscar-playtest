# Godscar art-library review batch v1 (November 14, 2026)

**Status:** UNAPPROVED ART CONCEPTS / local ChatGPT output only. **NOT** checked-in production artwork, not deployed, not wired to simulation.

## Produced artifact
A 490-entry individually exported visual-candidate library, supplied as a conversation download named `Godscar_Art_Library_v1_FULL.zip` and a lightweight alternative `Godscar_Art_Library_v1_LIGHT.zip`. Each entry has a stable **draft visual** ID, a 256px square PNG, a 128px WebP preview, a manifest row, source-crop coordinates, and quality notes. Five concept source atlases are retained in the full ZIP. A searchable gallery is also available as a standalone HTML artifact.

| Category | Individual icon/thumbnail concepts |
| --- | ---: |
| Named active ability candidates | 160 |
| Alternate ability effect studies | 150 |
| Specialization emblem candidates (18 primary + 18 alternate) | 36 |
| Weapon / off-hand art studies | 47 |
| Armor / jewelry studies (21 + 42 alternatives) | 63 |
| Materials / status / consumable studies | 27 |
| Extra biome thumbnail studies | 7 |
| **Total** | **490** |

## Important quality / safety notes
- The two transparent ability spritesheets produced **128 higher-detail transparent icons**, mapped provisionally to the `DRAFT-WA-*` and `DRAFT-IM-*` list in `docs/design/WEAPON-ABILITIES-CATALOG-V0.md`.
- The final **32 of the 160** named icons and all **150 alternate abilities** are smaller crops from multi-component concept posters. Their assignment is **not mechanically/visually validated**.
- The 18+18 specialization emblems are also tiny crops; the 18 existing 128px specialization review images and existing Avenger prototype in the Playtest repo remain preferable as source artwork and MUST NOT be overwritten with these lower-quality versions.
- Weapon icon studies are visual candidates, **not** forty uniquely designed, source-accurate weapon models; some archetype-name mappings need corrections. Likewise, item gear/status/material tags are visual placeholders, not confirmed gameplay assets.
- Reusing/remapping and letterboxing a crop into 256px does **not** increase its source resolution. Cropped art may retain adjacent borders, labels or artifacts.
- The asset set is **not yet in GitHub**. This note preserves the asset counts and provenance, not binaries or a permanent public download.
- No changes were made to `docs/ASSET-MANIFEST.md` existing IDs or approved statuses; all new IDs are `DRAFT-*`.
- Nothing has been deployed, published or connected to the simulation.

## Next quality gate
Review the downloadable gallery at 36–64 phone pixels, select the strongest elements, redraw or generate full independent transparent high-resolution items and specialization emblems (not upscaled poster cuts), ensure ability icons match their described mechanics, then assign approved `GS-*` IDs and integrate with a dedicated UI branch if owner authorizes.

## Correction audit — Cropped art library v2 (November 14, 2026)

**The original v1 extraction is superseded as a review gallery.** User correctly identified extensive broken crops. Inspection of the original five atlases identified the specific problem as incorrect grids, not cosmetic edge bleed.

- **490 stable draft IDs retained**, **253 source crop boxes corrected** with original and new coordinates logged. The largest fixes are 98 alternate spell crops from the wrong 14×7 collage grid, 42 alternative equipment crops from the wrong panel origin, and crossed weapon-row, specialization and status crops.
- **128 true transparent-source spell icons** remain the stronger candidates. Full-resolution PNG-sized canvases built from 30–100px poster crops **are not high-resolution original artwork**, and should not be approved for final game export solely because the crop is corrected.
- **253** changed-box composite candidates are marked `REPAIRED_FRAMED_CONCEPT`, **102** composite crops are marked `FRAMED_CONCEPT_REVIEW` and **7** are biome thumbnail studies; these are not approval statuses. The 128 transparent-source icons are marked `TRANSPARENT_ATLAS_CANDIDATE`.
- Produced corrected download artifacts in ChatGPT conversation: `Godscar_Art_Library_v2_FIXED_FULL.zip`, `Godscar_Art_Library_v2_FIXED_LIGHT.zip`, `Godscar_Art_Library_v2_TRANSPARENT_ICONS.zip`, `Godscar_Art_Library_v2_STANDALONE_GALLERY.html` and a before/after comparison. Archive integrity (ZIP CRC checks) passed.
- The new manifest includes previous crop, corrected crop, correction type, true native pixel dimensions and actual alpha-source status. The full pack also includes native unscaled source crops and original atlases. The light pack contains WebP previews, not the source PNGs.
- These binaries live in the conversation output, **not in GitHub**, and are not an approved production asset manifest. Do not replace existing premium specialization art, art IDs or website assets with them.
- **Open task:** retouch or regenerate individual, transparent, large-source item/emblem paintings; check icon-to-ability thematic mapping; review at mobile scale before integrating.

No simulation, GitHub main branch, deployed page or protected gameplay content changed as part of this correction.
