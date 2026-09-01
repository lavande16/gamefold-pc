# Current GameFold PC V1.5 baseline

The approved locked baseline is **V1.5-ABOUT-DUAL-SCREEN-LOCKED-10180**.

- APK: `releases/baselines/V1.5/GameFold-PC-V1.5-ABOUT-DUAL-SCREEN-LOCKED-10180.apk`
- Package/version: `com.gamefold.pc`, `1.5 / 10180`
- SHA-256: `beca0d752720a2fcfc5ba52333d0fb390ac5d1613de3904a130d4ae2c9afc5ad`
- Source commit: `e492aee37b58690da7d0440419d20f0bc8203458`
- Git tag: `gamefold-pc-v1.5-baseline`
- Release contract: `debuggable=false`, run-as disabled

V1.5 is based on the exact website-distributed V1.4 candidate with SHA-256 `083f65eb07e6697c7789f45adb87fca093c6255c9f3cacd493b69083715af63c`. It keeps that candidate's Quark download and external-display defaults, reuses the Dribbble destination for the Xiaohongshu icon, directly replaces both `130×130` dual-screen button assets without layout changes, and closes run-as for the default Legacy Release.

All `455` tests and `100` forced Gradle tasks passed. Device verification passed on vivo `43925730 / V2436A`: `1.5 / 10180` is installed in place, first-install time and all-files access are preserved, `run-as` reports `package not debuggable`, and the pulled APK matches the frozen artifact byte-for-byte.
