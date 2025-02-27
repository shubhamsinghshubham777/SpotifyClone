import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.ScrollDirection
import components.ScrollPosition
import dev.kilua.animation.animateColorAsState
import dev.kilua.animation.animateFloatAsState
import dev.kilua.html.Color
import dev.kilua.html.Display
import dev.kilua.html.ITag
import dev.kilua.html.helpers.TagEvents
import dev.kilua.html.helpers.TagStyleFun
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.style.globalStyle
import dev.kilua.utils.jsObjectOf
import web.dom.HTMLElement
import web.dom.events.Event
import web.dom.observers.ResizeObserver
import web.dom.observers.ResizeObserverOptions
import web.get
import web.window

@Composable
fun TagStyleFun.scale(scale: Float = 1f) = style("scale", "$scale $scale")

@Composable
fun TagStyleFun.hideScrollbar(className: String? = null) {
    if (className != null) {
        globalStyle(".$className::-webkit-scrollbar") {
            display(Display.None)
            // For Firefox
            style("scrollbar-width", "none")
        }
    } else {
        style("scrollbar-width", "none")
    }
}

@Composable
fun TagStyleFun.disablePointerEvents() = style("pointer-events", "none")

@Composable
fun TagStyleFun.enablePointerEvents() = style("pointer-events", "auto")

@Composable
fun <E : HTMLElement> ITag<E>.animateColorOnInteraction(
    normalColor: Color,
    hoverColor: Color? = null,
    pressColor: Color? = null,
    applyOnBackground: Boolean = false,
    applyOnColor: Boolean = false,
    applyOnFill: Boolean = false,
): State<Color> {
    val colorState = when {
        hoverColor != null && pressColor != null -> {
            val isHovered by rememberIsHoveredAsState()
            val isPressed by rememberIsPressedAsState()
            animateColorAsState(
                when {
                    isPressed -> pressColor
                    isHovered -> hoverColor
                    else -> normalColor
                }
            )
        }

        hoverColor == null && pressColor != null -> {
            val isPressed by rememberIsPressedAsState()
            animateColorAsState(
                when {
                    isPressed -> pressColor
                    else -> normalColor
                }
            )
        }

        hoverColor != null && pressColor == null -> {
            val isHovered by rememberIsHoveredAsState()
            animateColorAsState(
                when {
                    isHovered -> hoverColor
                    else -> normalColor
                }
            )
        }

        else -> animateColorAsState(normalColor)
    }
    if (applyOnBackground) background(color = colorState.value)
    if (applyOnColor) color(colorState.value)
    if (applyOnFill) style("fill", colorState.value.value)

    return colorState
}

@Composable
fun <E : HTMLElement> ITag<E>.animateScaleOnInteraction(
    onHover: Float = Constants.SCALE_HOVERED,
    onPress: Float = 1f,
) {
    val isHovered by rememberIsHoveredAsState()
    val isPressed by rememberIsPressedAsState()
    scale(
        animateFloatAsState(
            when {
                isPressed -> onPress
                isHovered -> onHover
                else -> 1f
            }
        ).value
    )
}

@Composable
fun <E : HTMLElement> ITag<E>.animateOpacityOnInteraction(
    onHover: Float = 1f,
    onPress: Float = 0.8f,
) {
    val isHovered by rememberIsHoveredAsState()
    val isPressed by rememberIsPressedAsState()
    opacity(
        animateFloatAsState(
            when {
                isPressed -> onPress
                isHovered -> onHover
                else -> 1f
            }
        ).value.toDouble()
    )
}

@Composable
fun <E : HTMLElement> ITag<E>.rememberScrollPosition(
    scrollDirection: ScrollDirection,
    endCorrectionThreshold: Double = 0.5,
): State<ScrollPosition> {
    val scrollPosition = remember { mutableStateOf(ScrollPosition.ReachedStart) }

    DisposableEffect(Unit) {
        val listener = { _: Event ->
            val reachedStart = when (scrollDirection) {
                ScrollDirection.Horizontal -> element.scrollLeft == 0.0
                ScrollDirection.Vertical -> element.scrollTop == 0.0
            }

            val reachedEnd = when (scrollDirection) {
                ScrollDirection.Horizontal -> {
                    element.scrollLeft + element.clientWidth >=
                            element.scrollWidth - endCorrectionThreshold
                }

                ScrollDirection.Vertical -> {
                    element.scrollTop + element.clientHeight >=
                            element.scrollHeight - endCorrectionThreshold
                }
            }

            when {
                reachedStart && scrollPosition.value != ScrollPosition.ReachedStart -> {
                    scrollPosition.value = ScrollPosition.ReachedStart
                }

                reachedEnd && scrollPosition.value != ScrollPosition.ReachedEnd -> {
                    scrollPosition.value = ScrollPosition.ReachedEnd
                }

                scrollPosition.value != ScrollPosition.InBetween -> {
                    scrollPosition.value = ScrollPosition.InBetween
                }
            }
        }

        element.addEventListener(Constants.EventName.SCROLL, listener)
        onDispose { element.removeEventListener(Constants.EventName.SCROLL, listener) }
    }

    return scrollPosition
}

@Composable
fun <E : HTMLElement> ITag<E>.rememberScrollOffset(
    scrollDirection: ScrollDirection
): State<Double> {
    val offsetState = remember { mutableDoubleStateOf(0.0) }

    DisposableEffect(Unit) {
        val listener: (Event) -> Unit = { _: Event ->
            offsetState.value = when (scrollDirection) {
                ScrollDirection.Horizontal -> element.scrollLeft
                ScrollDirection.Vertical -> element.scrollTop
            }
        }

        window.requestAnimationFrame {
            offsetState.value = when (scrollDirection) {
                ScrollDirection.Horizontal -> element.scrollLeft
                ScrollDirection.Vertical -> element.scrollTop
            }
        }

        element.addEventListener(Constants.EventName.SCROLL, listener)

        onDispose { element.removeEventListener(Constants.EventName.SCROLL, listener) }
    }

    return offsetState
}

@Composable
fun <E : HTMLElement> ITag<E>.rememberWidth(): State<Double?> {
    val widthState = remember { mutableStateOf<Double?>(null) }
    DisposableEffect(Unit) {
        val observer = ResizeObserver { entries, _ ->
            entries[0]?.contentRect?.let { rect -> widthState.value = rect.width }
        }
        @Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
        observer.observe(element, jsObjectOf() as ResizeObserverOptions)
        onDispose { observer.disconnect() }
    }
    return widthState
}

@Composable
fun TagEvents.rememberIsPressedAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onPointerDown { state.value = true }
    onGlobalPointerUp { state.value = false }
    return state
}

@Composable
fun TagEvents.rememberIsHoveredAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onMouseOver { state.value = true }
    onMouseOut { state.value = false }
    return state
}
