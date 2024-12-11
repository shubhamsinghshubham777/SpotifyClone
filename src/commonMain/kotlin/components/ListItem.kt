package components

import Assets
import Breakpoint
import Colors
import Constants
import ContentOpacity
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import animateColorOnInteraction
import animateScaleOnInteraction
import dev.kilua.core.IComponent
import dev.kilua.html.AlignItems
import dev.kilua.html.Background
import dev.kilua.html.BoxShadow
import dev.kilua.html.Display
import dev.kilua.html.FontWeight
import dev.kilua.html.IDiv
import dev.kilua.html.Overflow
import dev.kilua.html.Position
import dev.kilua.html.TextOverflow
import dev.kilua.html.WhiteSpace
import dev.kilua.html.div
import dev.kilua.html.img
import dev.kilua.html.px
import dev.kilua.html.span
import dev.kilua.svg.ISvgTag
import dev.kilua.svg.path
import dev.kilua.svg.rect
import dev.kilua.svg.svg
import rememberBreakpoint
import rememberIsHoveredAsState
import toKiluaColor

@Composable
fun IComponent.ListItem(
    title: String,
    imageUrl: String,
    onHoverChanged: (Boolean) -> Unit,
) = div {
    val breakpoint by rememberBreakpoint()
    val isNotDesktop by remember { derivedStateOf { breakpoint != Breakpoint.Desktop } }
    val sizePx by remember { derivedStateOf { if (isNotDesktop) 48.px else 64.px } }
    val textSizePx by remember { derivedStateOf { if (isNotDesktop) 12.px else 15.px } }

    val isHovered by rememberIsHoveredAsState()
    val animatedBGColor by animateColorAsState(
        if (isHovered) Color.White.copy(alpha = ContentOpacity.HOVERED)
        else Color.White.copy(alpha = ContentOpacity.NORMAL)
    )

    LaunchedEffect(isHovered) { onHoverChanged(isHovered) }

    alignItems(AlignItems.Center)
    background(Background(color = animatedBGColor.toKiluaColor()))
    borderRadius(4.px)
    display(Display.Flex)
    flexGrow(1)
    height(sizePx)
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
                color = Color.Black.copy(alpha = 0.25f).toKiluaColor(),
            )
        )
        height(sizePx)
        width(sizePx)
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
    if (isHovered) GreenPlayButton()
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
                color = Color.Black.copy(alpha = 0.25f).toKiluaColor(),
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
