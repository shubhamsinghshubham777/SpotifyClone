import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.kilua.Hot
import web.document
import web.dom.events.Event
import web.dom.pointerevents.PointerEvent
import web.window

expect fun webpackHot(): Hot?

@Composable
fun rememberMediaQueryAsState(query: String): State<Boolean> {
    val mediaQuery = remember(query) { window.matchMedia(query) }
    val matches = remember { mutableStateOf(mediaQuery.matches) }
    LaunchedEffect(Unit) {
        mediaQuery.onchange = {
            matches.value = mediaQuery.matches
        }
    }
    return matches
}

enum class Breakpoint {
    Mobile,
    SmallTablet,
    Tablet,
    Laptop,
    Desktop;

    infix fun isGreaterThan(breakpoint: Breakpoint) = this.ordinal > breakpoint.ordinal
    infix fun isSmallerThan(breakpoint: Breakpoint) = this.ordinal < breakpoint.ordinal
    fun isBetween(smallBreakpoint: Breakpoint, largeBreakpoint: Breakpoint): Boolean {
        return (smallBreakpoint.ordinal..largeBreakpoint.ordinal).contains(this.ordinal)
    }
}

@Composable
fun rememberBreakpoint(): State<Breakpoint> {
    val isMobile by rememberMediaQueryAsState("(max-width: 480px)")
    val isSmallTablet by rememberMediaQueryAsState("(min-width: 481px) and (max-width: 768px)")
    val isTablet by rememberMediaQueryAsState("(min-width: 769px) and (max-width: 991px)")
    val isLaptop by rememberMediaQueryAsState("(min-width: 992px) and (max-width: 1199px)")
    return remember {
        derivedStateOf {
            when {
                isMobile -> Breakpoint.Mobile
                isSmallTablet -> Breakpoint.SmallTablet
                isTablet -> Breakpoint.Tablet
                isLaptop -> Breakpoint.Laptop
                else -> Breakpoint.Desktop
            }
        }
    }
}

@Composable
fun onGlobalPointerUp(callback: () -> Unit) {
    DisposableEffect(Unit) {
        val listener = { _: Event -> callback() }
        document.addEventListener(Constants.EventName.POINTER_UP, listener)
        onDispose { document.removeEventListener(Constants.EventName.POINTER_UP, listener) }
    }
}

@Composable
fun onGlobalPointerMove(callback: (e: PointerEvent) -> Unit) {
    DisposableEffect(Unit) {
        val listener = { e: Event -> callback(e as PointerEvent) }
        document.addEventListener(Constants.EventName.POINTER_MOVE, listener)
        onDispose { document.removeEventListener(Constants.EventName.POINTER_MOVE, listener) }
    }
}
