package components

import Colors
import Constants
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import dev.kilua.core.IComponent
import dev.kilua.html.Background
import dev.kilua.html.Display
import dev.kilua.html.IDiv
import dev.kilua.html.Overflow
import dev.kilua.html.Position
import dev.kilua.html.div
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.panel.hPanel
import dev.kilua.panel.vPanel
import dev.kilua.utils.jsObjectOf
import rememberScrollOffset
import toKiluaColor
import web.dom.observers.ResizeObserver
import web.dom.observers.ResizeObserverOptions
import web.get

@Composable
fun IComponent.MainBody() {
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    var hoveredListItemIndex by remember { mutableStateOf(0) }
    val hoveredColor by remember {
        derivedStateOf {
            Color(
                when (hoveredListItemIndex) {
                    0 -> 0XFF3B5C56
                    1 -> 0XFF3B4650
                    2 -> 0XFF461610
                    3 -> 0XFF301A17
                    4 -> 0XFF4F5E2B
                    5 -> 0XFF561B26
                    6 -> 0XFF202020
                    else -> 0XFF614C3D
                }
            )
        }
    }

    vPanel {
        val scrollOffset by rememberScrollOffset(ScrollDirection.Vertical)
        val headerBackgroundOpacity by remember {
            derivedStateOf {
                val percentage = (scrollOffset / 100).coerceIn(0.0, 1.0)
                when (percentage) {
                    0.0 -> 0f
                    in 0.0..0.25 -> 0.25f
                    in 0.25..0.75 -> 0.5f
                    else -> 1f
                }
            }
        }

        background(Background(color = Colors.containerElevated))
        borderRadius(Constants.CONTAINER_RADIUS.px)
        flexGrow(1)
        overflowY(Overflow.Auto)
        position(Position.Relative)

        // Filter Chips
        hPanel(gap = 8.px) {
            background(
                Background(
                    color = animateColorAsState(
                        targetValue = hoveredColor.copy(alpha = headerBackgroundOpacity),
                        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing),
                    ).value.toKiluaColor()
                ),
            )
            padding(contentPadding)
            position(Position.Sticky)
            top(0.px)
            zIndex(1)
            mediaTypeFilters.forEachIndexed { index, filter ->
                SelectableChip(
                    text = filter,
                    isSelected = index == selectedFilterIndex,
                    onSelectionChange = { selectedFilterIndex = index },
                )
            }
        }

        // Main Content
        vPanel {
            val animatedBackgroundColorState = animateColorAsState(
                targetValue = hoveredColor,
                animationSpec = tween(durationMillis = 1200, easing = LinearOutSlowInEasing)
            )
            width(100.perc)
            position(Position.Absolute)

            // Playlist Grid
            PlaylistGrid(animatedBackgroundColorState) { hoveredListItemIndex = it }
        }
    }
}

@Composable
private fun IDiv.PlaylistGrid(
    animatedBackgroundColorState: State<Color>,
    onHoveredIndexChanged: (Int) -> Unit,
) {
    div {
        var gridWidth by remember { mutableStateOf<Double?>(null) }
        val isLargeGrid by remember { derivedStateOf { (gridWidth ?: 0.0) >= 815 } }
        columnGap(8.px)
        display(Display.Grid)
        gridTemplateColumns("1fr 1fr${if (isLargeGrid) " 1fr 1fr" else ""}")
        paddingBottom(56.px)
        paddingLeft(16.px)
        paddingRight(16.px)
        paddingTop(64.px)
        rowGap(8.px)
        style(
            name = "background",
            value = "linear-gradient(" +
                    "${animatedBackgroundColorState.value.toKiluaColor().value} 40%, " +
                    "${Colors.transparent.value})"
        )

        DisposableEffect(Unit) {
            val observer = ResizeObserver { entries, _ ->
                entries[0]?.contentRect?.let { rect -> gridWidth = rect.width }
            }
            @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
            observer.observe(element, jsObjectOf() as ResizeObserverOptions)
            onDispose { observer.disconnect() }
        }

        fakePlaylists.forEachIndexed { index, title ->
            ListItem(
                title = title,
                imageUrl = "https://placehold.co/48x48?text=Spotify",
                onHoverChanged = { isHovered ->
                    onHoveredIndexChanged(if (isHovered) index else 0)
                }
            )
        }
    }
}

private val contentPadding = 16.px
private val mediaTypeFilters = listOf("All", "Music", "Podcasts")

private val fakePlaylists = listOf(
    "The PropheC Radio",
    "New Music Hindi",
    "Arijit Singh Radio",
    "Daily Mix 5",
    "Emraan Hashmi 2003 to 2020",
    "This Is Sanam",
    "Happy Vibes",
    "Daily Mix 6"
)
