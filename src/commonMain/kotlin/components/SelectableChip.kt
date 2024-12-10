package components

import Colors
import Constants
import ContentOpacity
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.kilua.core.IComponent
import dev.kilua.html.Background
import dev.kilua.html.ISpan
import dev.kilua.html.px
import dev.kilua.html.span
import rememberIsHoveredAsState
import rememberIsPressedAsState
import toComposeColor
import toKiluaColor

@Composable
fun IComponent.SelectableChip(
    text: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
    config: (@Composable ISpan.() -> Unit)? = null,
) = span {
    val isHovered by rememberIsHoveredAsState()
    val isPressed by rememberIsPressedAsState()
    val animatedBackgroundColor by animateColorAsState(
        Color.White.copy(
            alpha = when {
                isSelected && isPressed -> ContentOpacity.SELECTED_PRESSED
                isSelected && isHovered -> ContentOpacity.SELECTED_HOVERED
                isSelected -> ContentOpacity.SELECTED
                isPressed -> ContentOpacity.PRESSED
                isHovered -> ContentOpacity.HOVERED
                else -> ContentOpacity.NORMAL
            }
        )
    )
    val animatedContentColor by animateColorAsState(
        if (isSelected) Colors.black.value.toComposeColor()
        else Colors.white.value.toComposeColor()
    )
    background(Background(color = animatedBackgroundColor.toKiluaColor()))
    borderRadius(20.px)
    color(animatedContentColor.toKiluaColor())
    fontSize(12.px)
    onClick { onSelectionChange(!isSelected) }
    paddingBottom(7.px)
    paddingLeft(12.px)
    paddingRight(12.px)
    paddingTop(7.px)
    role(Constants.Role.BUTTON)
    // Keep custom config in last to override all other styles above if needed
    config?.invoke(this)

    +text
}
