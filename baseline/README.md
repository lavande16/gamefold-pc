# GameFold PC V1.5 正式基线

V1.5 基于网站当前提供的 `V1.4-QUARK-EXTERNAL-DISPLAY-RC1`（SHA-256 `083f65eb07e6697c7789f45adb87fca093c6255c9f3cacd493b69083715af63c`）冻结，完整继承夸克下载入口和外屏默认策略。

- APK：`GameFold-PC-V1.5-ABOUT-DUAL-SCREEN-LOCKED-10180.apk`
- 包名/版本：`com.gamefold.pc`，`1.5 / 10180`
- SHA-256：`beca0d752720a2fcfc5ba52333d0fb390ac5d1613de3904a130d4ae2c9afc5ad`
- 源码提交：`e492aee37b58690da7d0440419d20f0bc8203458`
- Git 标签：`gamefold-pc-v1.5-baseline`
- 正式合同：`debuggable=false`，run-as 关闭

## 修改

- 关于页小红书图标与 Dribbble 图标都打开 `https://dribbble.com/lavande`。
- 外屏开关的关态和开态素材直接替换为用户提供的两张 `130×130 WebP`；资源名、引用位置和布局规则均未改变。
- Legacy Release 默认关闭 run-as；显式 Debug 构建仍保持正常调试能力。

## 验证

- `455` 项 Legacy Release 单测全部通过，`0 failures / 0 errors / 0 skipped`。
- `100` 个 Gradle 任务强制重跑成功，包括 R8、资源压缩和 Lint Vital。
- APK 身份为 `com.gamefold.pc`、`1.5 / 10180`，minSdk `26`、targetSdk `28`。
- APK Signature Scheme v2 验证通过；证书 SHA-256 为 `24a37be7ed0f0431fde44018312271152545d689bb3d1fbe9fbf92914bdf84b4`。
- vivo `43925730 / V2436A` 原位升级成功，首次安装时间和全文件访问权限保留。
- 设备回读 `run-as` 返回 `package not debuggable`；拉回 APK 与归档逐字节一致。
- 应用启动冒烟验证通过；主界面关态新素材已在真机显示。

回退包为 `V1.4-QUARK-EXTERNAL-DISPLAY-RC1`：`../../candidates/V1.4-QUARK-EXTERNAL-DISPLAY-RC1/GameFold-PC-V1.4-QUARK-EXTERNAL-DISPLAY-RC1-10179.apk`。
