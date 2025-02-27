package components

import Assets
import Breakpoint
import Colors
import Constants
import ContentOpacity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import animateColorOnInteraction
import animateScaleOnInteraction
import dev.kilua.animation.animateColorAsState
import dev.kilua.core.IComponent
import dev.kilua.html.AlignItems
import dev.kilua.html.BoxShadow
import dev.kilua.html.Color
import dev.kilua.html.Display
import dev.kilua.html.FontWeight
import dev.kilua.html.IDiv
import dev.kilua.html.Overflow
import dev.kilua.html.Position
import dev.kilua.html.TextOverflow
import dev.kilua.html.WhiteSpace
import dev.kilua.html.div
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.img
import dev.kilua.html.px
import dev.kilua.html.span
import dev.kilua.svg.ISvgTag
import dev.kilua.svg.path
import dev.kilua.svg.rect
import dev.kilua.svg.svg
import rememberBreakpoint
import rememberIsHoveredAsState

@Composable
fun IComponent.ListItem(
    title: String,
    imageUrl: String,
    onHoverChanged: (Boolean) -> Unit,
) = div {
    val breakpoint by rememberBreakpoint()
    val isNotDesktop by remember { derivedStateOf { breakpoint != Breakpoint.Desktop } }
    val containerSizePx by remember { derivedStateOf { if (isNotDesktop) 48.px else 64.px } }
    val textSizePx by remember { derivedStateOf { if (isNotDesktop) 12.px else 15.px } }
    val greenButtonSize by remember { derivedStateOf { if (isNotDesktop) 32 else 48 } }

    val isHovered by rememberIsHoveredAsState()
    val animatedBGColor by animateColorAsState(
        if (isHovered) Color.rgba(1f, 1f, 1f, ContentOpacity.HOVERED)
        else Color.rgba(1f, 1f, 1f, ContentOpacity.NORMAL)
    )

    LaunchedEffect(isHovered) { onHoverChanged(isHovered) }

    alignItems(AlignItems.Center)
    background(color = animatedBGColor)
    borderRadius(4.px)
    display(Display.Flex)
    flexGrow(1)
    height(containerSizePx)
    overflow(Overflow.Hidden)
    position(Position.Relative)
    role(Constants.Role.BUTTON)

    img(src = imageUrl) {
        boxShadow(
            BoxShadow(
                hOffset = 0.px,
                vOffset = 0.px,
                blurRadius = 16.px,
                spreadRadius = 4.px,
                color = Color.rgba(0f, 0f, 0f, 0.25f),
            )
        )
        height(containerSizePx)
        width(containerSizePx)
    }

    span {
        val margin by remember { derivedStateOf { if (isNotDesktop) 8.px else 16.px } }
        fontSize(textSizePx)
        fontWeight(FontWeight.Bold)
        marginLeft(margin)
        marginRight(margin)
        overflow(Overflow.Hidden)
        textOverflow(TextOverflow.Ellipsis)
        whiteSpace(WhiteSpace.Nowrap)
        +title
    }

    // Spacer
    div { flexGrow(1) }

    // Play Button
    if (isHovered) GreenPlayButton(sizePx = greenButtonSize)
}

@Composable
fun IDiv.GreenPlayButton(
    sizePx: Int = 32,
    marginRightPx: Int = 8,
    marginBottomPx: Int? = null,
    config: (@Composable ISvgTag.() -> Unit)? = null,
) {
    svg(viewBox = Constants.VIEW_BOX_24) {
        animateColorOnInteraction(
            normalColor = Colors.greenButtonBG,
            hoverColor = Colors.greenButtonBGHighlighted,
            pressColor = Colors.greenButtonBGPressed,
            applyOnFill = true
        )
        animateScaleOnInteraction()
        borderRadius((sizePx / 2).px)
        if (marginBottomPx != null) bottom(marginBottomPx.px)
        boxShadow(
            BoxShadow(
                blurRadius = 4.px,
                color = Color.rgba(0f, 0f, 0f, 0.25f),
                hOffset = 0.px,
                spreadRadius = 2.px,
                vOffset = 4.px,
            )
        )
        height(sizePx.px)
        marginRight(marginRightPx.px)
        position(Position.Absolute)
        right(0.px)
        width(sizePx.px)
        config?.invoke(this)
        rect(x = 0.px, y = 0.px, width = 24.px, height = 24.px)
        path(Assets.IC_PLAY_PATH) { fill(Colors.black.value) }
    }
}
