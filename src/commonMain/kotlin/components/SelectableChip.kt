package components

import Colors
import ContentOpacity
import UserSelect
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.kilua.core.IComponent
import dev.kilua.html.Background
import dev.kilua.html.Cursor
import dev.kilua.html.px
import dev.kilua.html.span
import rememberIsHoveredAsState
import rememberIsPressedAsState
import toComposeColor
import toKiluaColor
import userSelect

@Composable
fun IComponent.SelectableChip(
    text: String,
    isSelected: Boolean,
    onSelectionChange: (Boolean) -> Unit,
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
    borderRadius(16.px)
    color(animatedContentColor.toKiluaColor())
    cursor(Cursor.Pointer)
    fontSize(14.px)
    onClick { onSelectionChange(!isSelected) }
    paddingBottom(6.px)
    paddingLeft(12.px)
    paddingRight(12.px)
    paddingTop(6.px)
    style("font-weight", "500")
    userSelect(UserSelect.None)
    +text
}
