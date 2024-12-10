package components

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import dev.kilua.core.IComponent
import dev.kilua.html.Background
import dev.kilua.html.Cursor
import dev.kilua.html.IDiv
import dev.kilua.html.JustifyItems
import dev.kilua.html.div
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.panel.hPanel
import onGlobalPointerMove
import onGlobalPointerUp
import onPointerDown
import rememberIsHoveredAsState
import toKiluaColor
import web.document
import kotlin.math.roundToInt

@Composable
fun IComponent.SplitPanel(
    collapsedMinWidth: Int = 70,
    collapsedMaxWidth: Int = 500,
    breakpoints: List<Pair<Int, Int>>,
    gutterHorizontalPadding: Int = 10,
    gutterVerticalPadding: Int = 8,
    gutterWidth: Int = 1,
    leftItem: @Composable IDiv.() -> Unit,
    rightItem: @Composable IDiv.() -> Unit,
    onResize: ((Int) -> Unit)? = null,
    config: (@Composable IDiv.() -> Unit)? = null,
) {
    var leftItemWidth by remember { mutableIntStateOf(collapsedMinWidth) }
    val boundLeftItemWidth by remember(collapsedMinWidth, collapsedMaxWidth, breakpoints) {
        derivedStateOf {
            val width = breakpoints
                .firstOrNull { range -> (range.first..range.second).contains(leftItemWidth) }
                ?.let { range ->
                    val midpoint = (range.first + range.second) / 2
                    when {
                        leftItemWidth <= midpoint -> range.first
                        leftItemWidth > midpoint && leftItemWidth < range.second -> range.second
                        else -> null
                    }
                }
                ?: leftItemWidth

            width.coerceIn(collapsedMinWidth..collapsedMaxWidth)
        }
    }
    var isGutterPressed by remember { mutableStateOf(false) }

    onGlobalPointerUp {
        isGutterPressed = false
        document.body?.style?.cursor = Cursor.Default.value
    }
    onGlobalPointerMove { event ->
        if (isGutterPressed) leftItemWidth = event.pageX.roundToInt() - gutterHorizontalPadding
    }

    LaunchedEffect(boundLeftItemWidth) { onResize?.invoke(boundLeftItemWidth) }

    hPanel {
        config?.invoke(this)

        // Left Item
        div {
            leftItem()
            // Keeping width after the item layout is important to override any width-related
            // configurations made by the the item
            width(boundLeftItemWidth.px)
        }

        // Gutter
        div {
            val isHovered by rememberIsHoveredAsState()
            val animatedColor by animateColorAsState(
                Color.White.copy(
                    alpha = when {
                        isGutterPressed -> 1f
                        isHovered -> 0.4863f
                        else -> 0f
                    }
                )
            )
            if (!isGutterPressed) cursor(Cursor.Grab)
            onPointerDown {
                isGutterPressed = true
                document.body?.style?.cursor = Cursor.Grabbing.value
            }
            marginBottom(gutterVerticalPadding.px)
            marginTop(gutterVerticalPadding.px)
            width(gutterHorizontalPadding.px)
            div {
                background(Background(animatedColor.toKiluaColor()))
                height(100.perc)
                justifySelf(JustifyItems.Center)
                width(gutterWidth.px)
            }
        }

        rightItem()
    }
}
