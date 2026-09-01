# Current GameFold PC V1.3 baseline

The approved GameFold PC self-use baseline is **V1.3-ADD-GAME-GUIDANCE-10178**. It inherits the V1.2 runtime behavior and self-use `run-as` contract while updating the add-game guidance and download destination.

- APK: `releases/baselines/V1.3/GameFold-PC-V1.3-ADD-GAME-GUIDANCE-10178.apk`
- Package: `com.gamefold.pc`
- Version: `1.3 / 10178`
- SHA-256: `3fac371045a43c90c163962647e15aa18dd430b71c735b70fbd169f3b68e8293`
- APK size: `16,554,215` bytes
- Source commit: `ba081cbbada2709a5f42a7f0d313367ffc89caf7`
- Git tag: `gamefold-pc-v1.3-baseline`
- Previous baseline tag: `gamefold-pc-v1.2-baseline`

## Accepted changes

- The add-game dialog displays `路径：根目录/GameFoldPC/game`.
- The “下载游戏” action opens `https://www.123zyk.com`.
- Android identity is `1.3 / 10178`.
- Legacy Release remains self-use and debuggable with native `run-as`; external signed variants remain non-debuggable.
- Game runtime, controller, gyro, driver, Proton, FEX, save, and external game-directory behavior are unchanged.

## Verification evidence

- All 100 Gradle tasks were forced to rerun and completed successfully.
- 450 Legacy Release unit tests passed with 0 failures, 0 errors, and 0 skipped.
- APK badging reports `com.gamefold.pc`, `1.3 / 10178`, minSdk 26, targetSdk 28, and `application-debuggable`.
- APK Signature Scheme v2 verification passed with certificate SHA-256 `24a37be7ed0f0431fde44018312271152545d689bb3d1fbe9fbf92914bdf84b4`.
- Foldable device `43925730` / vivo `V2436A` reports `1.3 / 10178` after an in-place update at `2026-08-24 22:45:13`.
- First-install time `2026-08-19 01:15:52`, `MANAGE_EXTERNAL_STORAGE`, native `run-as`, and `/sdcard/GameFoldPC/game` were preserved.
- The pulled installed APK matches the frozen baseline APK byte-for-byte.
- On-device UI verification confirmed the new path text, and the download action opened the `123资源库 - 123ZYK Free Share` page in vivo Browser.

## Restore contract

- Restore code from Git tag `gamefold-pc-v1.3-baseline`.
- Restore the exact APK from `releases/baselines/V1.3/` and verify SHA-256 before installation.
- V1.2 remains frozen under `releases/baselines/V1.2/` and tag `gamefold-pc-v1.2-baseline` for rollback.
