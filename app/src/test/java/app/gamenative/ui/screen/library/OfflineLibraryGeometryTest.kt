package app.gamenative.ui.screen.library

import app.gamenative.gamefold.ui.gameFoldPixelGlyphRows
import app.gamenative.gamefold.ui.gameFoldPixelMinimumWidthDp
import app.gamenative.gamefold.ui.gameFoldPixelWordmarkUnits
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import androidx.compose.ui.graphics.Color
import org.junit.Test

class OfflineLibraryGeometryTest {

    @Test
    fun emptyLibraryAfterScan_usesCartridgeGridInsteadOfEmptyPanel() {
        assertEquals(
            OfflineLibraryContent.CARTRIDGE_GRID,
            offlineLibraryContent(
                hasStoragePermission = true,
                isRefreshing = false,
                gameCount = 0,
            ),
        )
    }

    @Test
    fun gameFoldPcWordmark_hasRenderableCGlyph() {
        assertArrayEquals(
            intArrayOf(0b01110, 0b10001, 0b10000, 0b10000, 0b10000, 0b10001, 0b01110),
            gameFoldPixelGlyphRows('C'),
        )
    }

    @Test
    fun gameFoldPcWordmark_usesExpectedHeaderWidth() {
        assertEquals(65, gameFoldPixelWordmarkUnits("GAMEFOLD PC"))
        assertEquals(168, gameFoldPixelMinimumWidthDp("GAMEFOLD PC", heightDp = 18))
    }
    @Test
    fun nsCartridgeArtwork_matchesTheSquareImageSlot() {
        val ratio = gamefoldCartridgeArtworkTopRatio()
        assertEquals(8f / 100f, ratio, 0.0001f)
        assertEquals(80f / 100f, gamefoldCartridgeArtworkWidthRatio(), 0.0001f)
    }

    @Test
    fun portraitLibraryUsesMoreInsetToKeepCartridgesCompact() {
        assertTrue(gameFoldHomeGridHorizontalPaddingDp(portrait = true) > gameFoldHomeGridHorizontalPaddingDp(portrait = false))
    }

    @Test
    fun nsFocusLayerIsLargerThanNormalCartridge_andTitlesShareOneGap() {
        assertEquals(3, gamefoldCartridgeContentInsetDp())
        assertEquals(4, gamefoldCartridgeTitleGapDp())
    }

    @Test
    fun addGameCartridge_usesTheSameVisibleInsetAsNormalCartridges() {
        assertEquals(gamefoldCartridgeContentInsetDp(), gamefoldAddGameContentInsetDp())
    }

    @Test
    fun selectedHeaderAction_usesWhiteIconTint() {
        val visual = gameFoldHeaderActionVisual(
            selected = true,
            focused = false,
            semanticFocused = false,
        )

        assertEquals(Color.White, visual.iconTint)
        assertFalse(visual.showForegroundFocus)
    }

    @Test
    fun headerFocus_isRenderedAsForegroundForPhysicalOrSemanticFocus() {
        assertTrue(
            gameFoldHeaderActionVisual(
                selected = false,
                focused = true,
                semanticFocused = false,
            ).showForegroundFocus,
        )
        assertTrue(
            gameFoldHeaderActionVisual(
                selected = false,
                focused = false,
                semanticFocused = true,
            ).showForegroundFocus,
        )
        assertFalse(
            gameFoldHeaderActionVisual(
                selected = false,
                focused = false,
                semanticFocused = false,
            ).showForegroundFocus,
        )
    }
}
