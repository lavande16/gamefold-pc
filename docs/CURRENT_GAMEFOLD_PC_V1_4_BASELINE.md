# Current GameFold PC V1.4 baseline

The approved self-use baseline is **V1.4-EMPTY-HOME-10179**.

- APK: `releases/baselines/V1.4/GameFold-PC-V1.4-EMPTY-HOME-10179.apk`
- Package/version: `com.gamefold.pc`, `1.4 / 10179`
- SHA-256: `59fe4294d204eb135685ade51095e53b55d3a292db0ed140be00fb06539a15e1`
- Candidate source SHA-256: `05bf19b4285fa1787a49cae15eb87bc2d62ce2a5266918161a2a0e3ddede3e39`
- Source commit: `3f009034664efb61776e1a2c33e0d26aed1781bc`
- Git tag: `gamefold-pc-v1.4-baseline`

V1.4 shows only the Add Game cartridge after a completed empty scan. It preserves the V1.3 self-use run-as/debuggable contract and the existing `https://www.123zyk.com` download destination. The withdrawn 3DM URL change is excluded.

Device verification passed on vivo `43925730 / V2436A`: in-place update preserved first-install time, all-files access, and run-as; the pulled APK matched the frozen baseline byte-for-byte.
