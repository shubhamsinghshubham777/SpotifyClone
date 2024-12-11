
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.ScrollDirection
import components.ScrollPosition
import dev.kilua.html.Background
import dev.kilua.html.Color
import dev.kilua.html.ITag
import dev.kilua.html.helpers.TagEvents
import dev.kilua.html.helpers.TagStyleFun
import dev.kilua.utils.jsObjectOf
import web.dom.HTMLElement
import web.dom.events.Event
import web.dom.events.MouseEvent
import web.dom.observers.ResizeObserver
import web.dom.observers.ResizeObserverOptions
import web.dom.pointerevents.PointerEvent
import web.get
import web.window

@Composable
fun <E : HTMLElement> TagStyleFun<E>.scale(scale: Float = 1f) = style("scale", "$scale $scale")

@Composable
fun <E : HTMLElement> ITag<E>.animateColorOnInteraction(
    normalColor: Color,
    hoverColor: Color? = null,
    pressColor: Color? = null,
    applyOnBackground: Boolean = false,
    applyOnColor: Boolean = false,
    applyOnFill: Boolean = false,
): State<Color> {
    val composeColorState = when {
        hoverColor != null && pressColor != null -> {
            val isHovered by rememberIsHoveredAsState()
            val isPressed by rememberIsPressedAsState()
            animateColorAsState(
                when {
                    isPressed -> pressColor
                    isHovered -> hoverColor
                    else -> normalColor
                }.value.toComposeColor()
            )
        }

        hoverColor == null && pressColor != null -> {
            val isPressed by rememberIsPressedAsState()
            animateColorAsState(
                when {
                    isPressed -> pressColor
                    else -> normalColor
                }.value.toComposeColor()
            )
        }

        hoverColor != null && pressColor == null -> {
            val isHovered by rememberIsHoveredAsState()
            animateColorAsState(
                when {
                    isHovered -> hoverColor
                    else -> normalColor
                }.value.toComposeColor()
            )
        }

        else -> animateColorAsState(normalColor.value.toComposeColor())
    }

    val kiluaColor = remember {
        derivedStateOf {
            composeColorState.value.toKiluaColor()
        }
    }

    if (applyOnBackground) background(Background(color = kiluaColor.value))
    if (applyOnColor) color(kiluaColor.value)
    if (applyOnFill) style("fill", kiluaColor.value.value)

    return kiluaColor
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
fun <E : HTMLElement> TagEvents<E>.onMouseOver(listener: (MouseEvent) -> Unit) =
    onEvent("mouseover", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseOut(listener: (MouseEvent) -> Unit) =
    onEvent("mouseout", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onMouseMove(listener: (MouseEvent) -> Unit) =
    onEvent("mousemove", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onPointerDown(listener: (PointerEvent) -> Unit) =
    onEvent("pointerdown", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onPointerUp(listener: (PointerEvent) -> Unit) =
    onEvent("pointerup", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.rememberIsPressedAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onPointerDown { state.value = true }
    onGlobalPointerUp { state.value = false }
    return state
}

@Composable
fun <E : HTMLElement> TagEvents<E>.rememberIsHoveredAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onMouseOver { state.value = true }
    onMouseOut { state.value = false }
    return state
}
