# Eating Animation Fork

A simple, client-side **Fabric and NeoForge** mod that adds smooth 3-stage sprite animations for eating food and drinking potions in Minecraft. Watch your apple get smaller bite-by-bite, your bread tear apart, your potion bottle empty sip-by-sip — all in your hand, in the hotbar, and in the inventory.

> **Unofficial fork** of the beloved [Eating Animation](https://modrinth.com/mod/eating-animation) mod by `theone_ss`, ported to Fabric and NeoForge for modern Minecraft versions using the new 1.21.4+ item model definition system. Original mod is MIT licensed; this fork is MIT licensed too. Full credit to the original author.

---

## ✨ Features

- **43 vanilla foods & drinks animated** — every food item in the game (apple, bread, cooked beef, golden apple, chorus fruit, suspicious stew, milk bucket, honey bottle, ominous bottle, and 35 more) has its own custom 3-frame eating animation.
- **Animated potions** — all potion types play a 3-frame drinking animation with the correct liquid color (the potion tint is preserved during drinking, so a healing potion stays red, night vision stays blue, etc.).
- **Bundled mod-support resource pack** — ships with a built-in, default-enabled resource pack that adds eating animations for **271 food items** across **15 popular mods**:
  - Adorn (4 items) — hot chocolate, glow berry tea, sweet berry juice, nether wart coffee
  - Additional Additions (4 items) — berry pie, chicken nugget, fried egg, honeyed apple
  - BetterEnd (14 items)
  - BetterNether (7 items)
  - Botania (2 items)
  - Create (5 items)
  - Duckling (1 item)
  - Expanded Delight (33 items)
  - Farmer's Delight (75 items)
  - Food Plus ID (93 items)
  - Hybrid-Aquatic (12 items)
  - Naturalist (11 items)
  - Snow Pig (2 items)
  - The Bumblezone (5 items)
  - Winterly (3 items)
- **Works everywhere** — in your hand, in the hotbar, in the inventory, in item frames, and on other players. Anywhere items render, the animation plays.
- **Pure client-side** — no server install required. Drop the jar in your `mods/` folder and you're done. Works on vanilla servers too.
- **No performance impact** — uses Minecraft's native item model system, no tick events, no mixins, no per-frame rendering hooks.
- **Dual loader support** — available for both **Fabric** and **NeoForge**. Pick the jar that matches your loader.
- **Wide version support** — a single jar covers Minecraft 1.21.4 through 1.21.11 (Fabric), 26.1 through 26.1.2 (Fabric), and 26.2 (Fabric & NeoForge). See the version list on this page for the exact compatibility matrix.

---

## 📦 Requirements

| Requirement | Fabric (1.21.x) | Fabric (26.1.x) | Fabric (26.2) | NeoForge (1.21.4 – 26.2) |
|-------------|-----------------|------------------|----------------|---------------------------|
| Minecraft | 1.21.4 – 1.21.11 | 26.1, 26.1.1, 26.1.2 | 26.2 | 1.21.4 – 26.2 |
| Loader | Fabric Loader 0.16+ | Fabric Loader 0.19.3+ | Fabric Loader 0.19.3+ | NeoForge (matching version) |
| API | Fabric API | Fabric API | Fabric API | NeoForge (bundled) |
| Java | 21+ | 25+ | 25+ | 21+ (1.21.x) / 25+ (26.x) |

Pick the file that matches your loader and Minecraft version from the versions list.

---

## 🚀 Installation

### Fabric
1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version
2. Download [Fabric API](https://modrinth.com/mod/fabric-api) for your Minecraft version and drop it in your `mods/` folder
3. Download **Eating Animation Fork** (Fabric jar) from this page and drop it in your `mods/` folder
4. Launch Minecraft — that's it, no config needed

### NeoForge
1. Install [NeoForge](https://neoforged.net/) for your Minecraft version
2. Download **Eating Animation Fork** (NeoForge jar) from this page and drop it in your `mods/` folder
3. Launch Minecraft — that's it, no config needed

The bundled mod-support resource pack is enabled by default. If you don't want the third-party mod animations, you can disable it in `Options → Resource Packs`.

---

## 🛠️ What's different from the original mod

This fork is a full rewrite of the rendering pipeline to use Minecraft 1.21.4's new **data-driven item model definition** system (the original mod used `ModelPredicateProviderRegistry` + JSON `"overrides"` blocks, which Mojang removed in 1.21.4).

Key changes:

- **314 item model JSON files** (`assets/<namespace>/items/<food>.json`) generated from the original mod's legacy override files, using `minecraft:condition` (`using_item`) + `minecraft:range_dispatch` (`use_duration` scaled by 1/30) to drive the 3-stage animation
- **All 939 original per-stage frame models** (`apple_eating_0.json`, `apple_eating_1.json`, `apple_eating_2.json`, etc.) preserved unchanged — same artwork as the original mod
- **All 957 original textures** preserved unchanged
- **Zero Java rendering code** — the mod's only Java class just registers the bundled resource pack. All animation logic lives in the JSON files, which means better performance, easier maintenance, and no mixins to break on Minecraft updates
- **Potion rendering fixed** — potions now use the correct vanilla model (`minecraft:item/potion`) with the proper packed-int tint (`default: -13083194`), and the tint is applied to all 3 drinking frames so the liquid color stays correct during the drinking animation
- **Animation timing fixed** — every `range_dispatch` block has `"scale": 0.033333` (1/30) to convert raw use-duration ticks to the 0.0–1.0+ range the thresholds expect. Without this, the animation skips straight to the last frame on the first bite
- **Dual loader support** — the Fabric jar (built with Loom) and the NeoForge jar (built with NeoForge's MDK) ship the same data-driven JSON assets, so behavior is identical across loaders

---

## 🐛 Known issues / FAQ

**Q: The mod doesn't load / shows as incompatible in MultiMC / Prism Launcher.**
A: Make sure you downloaded the jar that matches your loader (Fabric or NeoForge) and your Minecraft version. The Fabric and NeoForge jars are NOT interchangeable — check the filename before installing.

**Q: I see a black/pink missing-texture box on potions.**
A: That was a bug in an early build of this fork. Make sure you're using version **1.0.2 or newer** (Fabric 1.21.x), **2.0.0 or newer** (Fabric 26.1.x), or **3.0.0 or newer** (Fabric 26.2 / NeoForge).

**Q: The eating animation skips straight to the last frame.**
A: Same as above — fixed in 1.0.1+ on Fabric. Make sure you're on a recent version.

**Q: Does this work on servers?**
A: Yes — it's purely client-side. You can join any vanilla server and the animations will work. The server doesn't need this mod installed.

**Q: Can I use this in my modpack?**
A: Yes — MIT licensed, no permission needed. Just include the jar in your modpack and credit the original author (`theone_ss`) and this fork.

**Q: Will this be ported to Forge / Quilt?**
A: Not by me. The mod is MIT licensed, so anyone is welcome to port it. Quilt users can probably run the Fabric version directly via Quilt's Fabric compatibility layer.

**Q: Which jar should I download — Fabric or NeoForge?**
A: If you use the Fabric loader, download the Fabric jar. If you use NeoForge, download the NeoForge jar. The two jars contain the same animations and behavior — only the loader glue differs.

**Q: Why are there separate jars for 1.21.x and 26.x?**
A: Minecraft 26.1 was the first unobfuscated release, which broke compatibility with all mods built for 1.21.x and earlier. You need the 2.0.0+ jar for 26.1.x and the 3.0.0+ jar for 26.2. The 1.0.x jar still works for 1.21.4–1.21.11.

---

## 📜 License & Credits

**MIT Licensed** — see [LICENSE](https://github.com/Fring-BS/Eating-Animation-Fork/blob/main/LICENSE).

- **Original mod**: [Eating Animation](https://modrinth.com/mod/eating-animation) by `theone_ss`, `spusik_`, `PinkGoosik`, `DoctorNight1` — MIT licensed
- **This fork**: maintained by **Fring** ([github.com/Fring-BS](https://github.com/Fring-BS)), ported to Fabric and NeoForge for modern Minecraft versions using the new item model definition system
- **All artwork** (food/drink sprites, mod icon) is from the original mod, used under the MIT license

If you find a bug or want to request support for a new mod's food items, please open an issue on the [project repository](https://github.com/Fring-BS/Eating-Animation-Fork/issues).

---

## 🔗 Links

- **Source code**: [GitHub](https://github.com/Fring-BS/Eating-Animation-Fork)
- **Issue tracker**: [GitHub Issues](https://github.com/Fring-BS/Eating-Animation-Fork/issues)
- **Original mod**: [Modrinth](https://modrinth.com/mod/eating-animation) · [CurseForge](https://curseforge.com/minecraft/mc-mods/eating-animation-fabric) · [GitHub](https://github.com/Theoness1/EatingAnimation)
- **Original author's Discord**: [discord.gg/DcemWeskeZ](https://discord.gg/DcemWeskeZ)
