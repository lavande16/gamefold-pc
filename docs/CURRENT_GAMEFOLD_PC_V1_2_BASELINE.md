# Current GameFold PC V1.2 baseline

The approved GameFold PC self-use baseline is **V1.2-RUN-AS-SELFUSE-10177**. It preserves the V1.1 feature behavior while changing the Android identity to `1.2 / 10177` and keeping native `run-as` access enabled by default.

- APK: `releases/baselines/V1.2/GameFold-PC-V1.2-RUN-AS-SELFUSE-10177.apk`
- Package: `com.gamefold.pc`
- Version: `1.2 / 10177`
- SHA-256: `050d10bc00a62e27b32f52aeaac00bee88ceb5ef4e4c77d422123056e95d636b`
- APK size: `16,554,211` bytes
- Source commit: `e2479127ecf4f9ce73968b1a5cd92c756fcf7b55`
- Git tag: `gamefold-pc-v1.2-baseline`
- Previous baseline tag: `gamefold-pc-v1.1-baseline`

## Baseline policy

- Legacy Release defaults `GAMEFOLD_PC_RUN_AS_ENABLED=true` and produces `android:debuggable=true`.
- The baseline is intended for the user's long-term private use and diagnostics.
- `release-signed` and `release-gold` remain explicitly non-debuggable, but are not part of this baseline.
- All functional behavior from V1.1 remains unchanged.

## Verification evidence

- Forced Gradle run executed all 100 tasks and completed successfully.
- 449 Legacy Release unit tests passed with 0 failures, 0 errors, and 0 skipped.
- APK badging reports `com.gamefold.pc`, `1.2 / 10177`, minSdk 26, targetSdk 28, and `application-debuggable`.
- APK Signature Scheme v2 verification passed with certificate SHA-256 `24a37be7ed0f0431fde44018312271152545d689bb3d1fbe9fbf92914bdf84b4`.
- Device `43925730` / vivo `V2436A` reports `1.2 / 10177` after an in-place update at `2026-08-23 19:08:39`.
- First-install time `2026-08-19 01:15:52`, application data, and external-storage permission were preserved.
- `run-as com.gamefold.pc` resolves to `/data/user/0/com.gamefold.pc`.
- The pulled installed APK matches the frozen baseline APK byte-for-byte.
- The NFS 2010 target container retained `1067x600`, `Chinese (Traditional)` / `zh_TW`, and the same config SHA-256 after the application update.

## Restore contract

- Restore code from Git tag `gamefold-pc-v1.2-baseline`.
- Restore the exact APK from `releases/baselines/V1.2/` and verify SHA-256 before installation.
- V1.1 remains frozen under `releases/baselines/V1.1/` and tag `gamefold-pc-v1.1-baseline` for rollback.
- The NFS 2010 resolution/language changes live in its private Wine container and are not embedded in the APK.

