package app.gamenative.product

import app.gamenative.ui.util.ScreenEffectsConfig
import app.gamenative.utils.LsfgVkManager
import com.winlator.container.Container

object GameFoldPcPerGameDefaults {
    private const val DEFAULT_LOCALE = "zh_CN.UTF-8"
    private const val VERSION_EXTRA = "gamefoldPcDefaults"
    private const val EXTERNAL_DISPLAY_SWAP_VERSION_EXTRA =
        "gamefoldPcExternalDisplaySwapDefaults"

    fun applyTo(container: Container): Boolean {
        val appliedVersion = container.getExtra(VERSION_EXTRA, "")
        val appliedExternalDisplaySwapVersion =
            container.getExtra(EXTERNAL_DISPLAY_SWAP_VERSION_EXTRA, "")
        val needsGeneralDefaults = GameFoldPcDefaults.needsPerGameDefaults(appliedVersion)
        val needsExternalDisplaySwapDefault =
            appliedExternalDisplaySwapVersion !=
                GameFoldPcDefaults.PER_GAME_EXTERNAL_DISPLAY_SWAP_DEFAULTS_VERSION
        if (!needsGeneralDefaults && !needsExternalDisplaySwapDefault) return false

        val appliedVersionNumber = appliedVersion.toIntOrNull() ?: 0
        if (needsGeneralDefaults) {
            if (appliedVersionNumber < 2) {
                container.isShowFPS = GameFoldPcDefaults.CONTAINER_SHOW_FPS
                container.putExtra(
                    LsfgVkManager.EXTRA_ARMED,
                    GameFoldPcDefaults.CONTAINER_LSFG_ENABLED.toString(),
                )
                container.language = GameFoldPcDefaults.GAME_LANGUAGE
                container.setLC_ALL(DEFAULT_LOCALE)
            }

            val flowScale = container.getExtra(LsfgVkManager.EXTRA_FLOW_SCALE)
                ?.toFloatOrNull()
            if (flowScale == null || flowScale == 0.80f) {
                container.putExtra(
                    LsfgVkManager.EXTRA_FLOW_SCALE,
                    GameFoldPcDefaults.LSFG_FLOW_SCALE_VALUE,
                )
            }

            val scalingMode = container.getExtra(ScreenEffectsConfig.KEY_SCALING_MODE)
                ?.toIntOrNull()
            if (scalingMode == null || scalingMode == ScreenEffectsConfig.SCALING_MODE_NONE) {
                container.putExtra(
                    ScreenEffectsConfig.KEY_SCALING_MODE,
                    GameFoldPcDefaults.SCREEN_EFFECT_SCALING_MODE,
                )
            }

            container.putExtra("useSteamInput", GameFoldPcDefaults.USE_STEAM_INPUT.toString())
            container.inputType = GameFoldPcDefaults.CONTROLLER_INPUT_TYPE
            container.dinputMapperType = GameFoldPcDefaults.DINPUT_MAPPER_TYPE
            container.putExtra(VERSION_EXTRA, GameFoldPcDefaults.DEFAULTS_VERSION)
        }

        if (needsExternalDisplaySwapDefault) {
            container.setExternalDisplaySwap(GameFoldPcDefaults.CONTAINER_EXTERNAL_DISPLAY_SWAP)
            container.putExtra(
                EXTERNAL_DISPLAY_SWAP_VERSION_EXTRA,
                GameFoldPcDefaults.PER_GAME_EXTERNAL_DISPLAY_SWAP_DEFAULTS_VERSION,
            )
        }
        return true
    }
}
