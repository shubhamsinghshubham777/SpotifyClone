import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.kilua.html.Background
import dev.kilua.html.helpers.TagEvents
import dev.kilua.html.helpers.TagStyleFun
import dev.kilua.html.style.CssStyle
import dev.kilua.html.style.PClass
import dev.kilua.html.style.style
import dev.kilua.utils.rem
import web.dom.HTMLElement
import web.dom.events.Event
import web.dom.events.MouseEvent

@Composable
fun <E : HTMLElement> TagStyleFun<E>.scale(scale: Float = 1f) = style("scale", "$scale $scale")

@Composable
fun <E : HTMLElement> TagStyleFun<E>.hideBackground() = background(
    Background(color = Colors.transparent)
)

@Composable
fun <E : HTMLElement> TagStyleFun<E>.userSelect(type: UserSelect) = style(
    name = "user-select",
    value = type.name.lowercase()
)

// TODO: Use this function in older code
@Composable
fun classNameFromStyle(
    onHover: (@Composable CssStyle.() -> Unit)? = null,
    onPress: (@Composable CssStyle.() -> Unit)? = null,
): String? {
    val hoverStyle = onHover?.let { safeOnHover ->
        style(pClass = PClass.Hover, content = safeOnHover)
    }
    val pressStyle = onPress?.let { safeOnPress ->
        style(pClass = PClass.Active, content = safeOnPress)
    }
    return hoverStyle % pressStyle
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
fun <E : HTMLElement> TagEvents<E>.onPointerDown(listener: (Event) -> Unit) =
    onEvent("pointerdown", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.onPointerUp(listener: (Event) -> Unit) =
    onEvent("pointerup", listener)

@Composable
fun <E : HTMLElement> TagEvents<E>.rememberIsPressedAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onPointerDown { state.value = true }
    onPointerUp { state.value = false }
    return state
}

@Composable
fun <E : HTMLElement> TagEvents<E>.rememberIsHoveredAsState(): State<Boolean> {
    val state = remember { mutableStateOf(false) }
    onMouseOver { state.value = true }
    onMouseOut { state.value = false }
    return state
}

enum class UserSelect { Auto, None, Text, All }
