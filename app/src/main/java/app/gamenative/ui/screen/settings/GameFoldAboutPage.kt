package app.gamenative.ui.screen.settings

import android.content.Intent
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import app.gamenative.R
import com.google.android.material.appbar.MaterialToolbar

/**
 * Mechanical host for Game Fold NS's original About view hierarchy.
 *
 * The page itself lives in fragment_about.xml and is copied byte-for-byte from NS. This
 * wrapper only connects that View implementation to the PC app's Compose navigation.
 */
@Composable
internal fun AboutPage(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val currentOnBack by rememberUpdatedState(onBack)

    AndroidView(
        factory = { context ->
            inflateGameFoldNsAbout(context).apply {
                val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_about)
                toolbar.title = context.getString(R.string.about)
                toolbar.setTitleTextColor(ContextCompat.getColor(context, R.color.pixel_text_dark))
                toolbar.setNavigationOnClickListener { currentOnBack() }

                findViewById<TextView>(R.id.text_version_name).text =
                    context.getString(R.string.gamefold_brand_name)

                setLinkClickListener(R.id.button_website, R.string.website_link)
                setLinkClickListener(R.id.button_xiaohongshu, R.string.xiaohongshu_link)
                setLinkClickListener(R.id.button_feedback, R.string.dribbble_link)
                setLinkClickListener(R.id.button_dribbble, R.string.dribbble_link)
            }
        },
        modifier = modifier.fillMaxSize(),
    )
}

internal fun inflateGameFoldNsAbout(context: Context): View {
    val themedContext = ContextThemeWrapper(context, R.style.Theme_GameFoldNsAboutHost)
    return LayoutInflater.from(themedContext).inflate(R.layout.fragment_about, null, false)
}

private fun View.setLinkClickListener(viewId: Int, linkId: Int) {
    findViewById<View>(viewId).setOnClickListener {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(linkId))))
    }
}
