package app.gamenative.ui.screen.settings

import app.gamenative.BuildConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.security.MessageDigest

class SettingsGeometryTest {
    private fun sourceFile(path: String): java.io.File = sequenceOf(
        java.io.File(path),
        java.io.File("../$path"),
    ).first { it.exists() }

    @Test
    fun settingsDirectory_usesNsTwoColumnBreakpoint() {
        assertEquals(1, gameFoldSettingsColumns(599))
        assertEquals(2, gameFoldSettingsColumns(600))
        assertEquals(2, gameFoldSettingsColumns(840))
    }

    @Test
    fun controllerBackFromDetail_returnsOneLevelOnly() {
        assertEquals(SettingsBackAction.SHOW_SETTINGS_HOME, settingsBackAction(onSettingsHome = false))
        assertEquals(SettingsBackAction.EXIT_SETTINGS, settingsBackAction(onSettingsHome = true))
    }

    @Test
    fun aboutPage_hostsTheOriginalNsXmlInsteadOfAComposeReinterpretation() {
        val source = sourceFile(
            "app/src/main/java/app/gamenative/ui/screen/settings/GameFoldAboutPage.kt",
        ).readText()

        assertTrue(source.contains("AndroidView"))
        assertTrue(source.contains("R.layout.fragment_about"))
        assertTrue(source.contains("modifier = modifier.fillMaxSize()"))
        assertTrue(source.contains("toolbar.title = context.getString(R.string.about)"))
        assertTrue(source.contains("toolbar.setTitleTextColor"))
        assertFalse(source.contains("GameFoldPixelWordmark"))
        assertFalse(source.contains("AboutActionCard"))
        assertFalse(source.contains("gameFoldAboutLayoutMode"))
    }

    @Test
    fun aboutPage_usesThePcCompatibilityLayerDisclaimer() {
        val source = sourceFile(
            "app/src/main/res/values-zh-rCN/gamefold_about_resources.xml",
        ).readText()

        assertTrue(source.contains("基于 Wine、Winlator 及相关开源兼容层"))
        assertFalse(source.contains("Eden"))
    }

    @Test
    fun launcherLabel_usesGameFoldPcWithoutAnInternalSpace() {
        sourceFile("app/src/main/res")
            .walkTopDown()
            .filter { it.name == "strings.xml" }
            .map { it.readText() }
            .filter { it.contains("<string name=\"app_name\">") }
            .forEach { source ->
                assertTrue(source.contains("<string name=\"app_name\">GameFold PC</string>"))
                assertFalse(source.contains("<string name=\"app_name\">Game Fold PC</string>"))
                assertFalse(source.contains("<string name=\"app_name\">GameNative</string>"))
            }
    }

    @Test
    fun launcherIcon_usesTheProvidedPcArtworkAcrossAllAliases() {
        val expectedArtworkSha256 =
            "69f38d6bc63a3ca1958d428305b2d45b07f5185360c3fdcfb4e647b9d9982aba"
        val artwork = sourceFile(
            "app/src/main/res/drawable-nodpi/gamefold_pc_app_icon.png",
        ).readBytes()
        val actualArtworkSha256 = MessageDigest.getInstance("SHA-256")
            .digest(artwork)
            .joinToString("") { byte -> "%02x".format(byte) }
        assertEquals(expectedArtworkSha256, actualArtworkSha256)

        val resources = sourceFile("app/src/main/res")
        listOf("mipmap-mdpi", "mipmap-hdpi", "mipmap-xhdpi", "mipmap-xxhdpi", "mipmap-xxxhdpi")
            .forEach { folder ->
                listOf("ic_launcher", "ic_launcher_round", "ic_launcher_alt", "ic_launcher_alt_round")
                    .forEach { resource ->
                        assertTrue(java.io.File(resources, "$folder/$resource.png").isFile)
                    }
            }
        listOf("mipmap-anydpi", "mipmap-anydpi-v26", "mipmap-anydpi-v33").forEach { folder ->
            listOf("ic_launcher", "ic_launcher_round", "ic_launcher_alt", "ic_launcher_alt_round")
                .forEach { resource ->
                    assertFalse(java.io.File(resources, "$folder/$resource.xml").exists())
                }
        }
    }

    @Test
    fun dualScreenButton_usesTheProvidedOffAndOnArtwork() {
        val expectedHashes = mapOf(
            "gamefold_dual_screen_off_ns.webp" to
                "6925b91c95eacb0ed7f548cdc2cd76537ba65ad05ca047044c8603f2edc0288e",
            "gamefold_dual_screen_on_ns.webp" to
                "5293b3a154d9c17dc34ded947f24c9628f2b6feb45d14000d065f07512d71152",
        )

        expectedHashes.forEach { (name, expectedHash) ->
            val artwork = sourceFile("app/src/main/res/drawable-nodpi/$name").readBytes()
            val actualHash = MessageDigest.getInstance("SHA-256")
                .digest(artwork)
                .joinToString("") { byte -> "%02x".format(byte) }
            assertEquals(expectedHash, actualHash)
        }
    }

    @Test
    fun gameFoldPcOnePointFiveBaseline_usesVersionCode10180AndDisablesRunAs() {
        assertEquals("1.5", BuildConfig.VERSION_NAME)
        assertEquals(10180, BuildConfig.VERSION_CODE)
        assertFalse(BuildConfig.GAMEFOLD_PC_RUN_AS_ENABLED)
    }
}
