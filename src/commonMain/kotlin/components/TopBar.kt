package components

import Assets
import Breakpoint
import Colors
import Constants
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.kilua.core.IComponent
import dev.kilua.form.text.textRef
import dev.kilua.html.AlignItems
import dev.kilua.html.Background
import dev.kilua.html.Border
import dev.kilua.html.BorderStyle
import dev.kilua.html.Cursor
import dev.kilua.html.Display
import dev.kilua.html.FontWeight
import dev.kilua.html.JustifyContent
import dev.kilua.html.Outline
import dev.kilua.html.OutlineStyle
import dev.kilua.html.Overflow
import dev.kilua.html.TextOverflow
import dev.kilua.html.WhiteSpace
import dev.kilua.html.button
import dev.kilua.html.div
import dev.kilua.html.img
import dev.kilua.html.px
import dev.kilua.html.style.PClass
import dev.kilua.svg.path
import dev.kilua.svg.svg
import dev.kilua.utils.rem
import onMouseOut
import onMouseOver
import rememberBreakpoint
import scale
import toComposeColor
import toKiluaColor

@Composable
fun IComponent.TopBar() {
    var focusedComponent: FocusedComponent? by remember { mutableStateOf(null) }
    var isSearchBarHovered by remember { mutableStateOf(false) }

    var backgroundColor by remember { mutableStateOf(Colors.container) }
    val animatedBackgroundColor by animateColorAsState(
        if (focusedComponent == FocusedComponent.SearchBar) {
            Colors.containerHighlighted.value.toComposeColor()
        } else backgroundColor.value.toComposeColor()
    )

    var outlineColor by remember { mutableStateOf(Colors.transparent) }
    val animatedOutlineColor by animateColorAsState(
        if (focusedComponent == FocusedComponent.SearchBar) Colors.white.value.toComposeColor()
        else outlineColor.value.toComposeColor()
    )

    var textFieldRef: dev.kilua.form.text.Text? = null
    val breakpoint by rememberBreakpoint()

    div {
        display(Display.Flex)
        marginLeft(24.px)
        marginRight(24.px)
        marginTop(8.px)

        // Spotify Logo
        img(Assets.ICON_SPOTIFY_LOGO) {
            alignSelf(AlignItems.Center)
            draggable(false)
            height(32.px)
            marginRight(24.px)
            role(Constants.Role.BUTTON)
            width(32.px)
        }

        // Spacer
        if (breakpoint == Breakpoint.Tablet || breakpoint isGreaterThan Breakpoint.Laptop) {
            div { flexGrow(2) }
        }

        // Home Button
        val hoverStyle = dev.kilua.html.style.style(pClass = PClass.Hover) {
            scale(Constants.SCALE_HOVERED)
        }
        val pressStyle = dev.kilua.html.style.style(pClass = PClass.Active) {
            opacity(0.8)
            scale(1.0f)
        }
        div(className = hoverStyle % pressStyle) {
            alignSelf(AlignItems.Center)
            background(Background(color = Colors.container))
            borderRadius(24.px)
            svg(viewBox = Constants.VIEW_BOX_24) {
                draggable(false)
                fill(
                    if (focusedComponent == FocusedComponent.HomeButton) Colors.white.value
                    else Colors.onContainer.value
                )
                onClick { focusedComponent = FocusedComponent.HomeButton }
                padding(12.px)
                role(Constants.Role.BUTTON)
                width(48.px)
                path(
                    if (focusedComponent == FocusedComponent.HomeButton) Assets.IC_HOME_FILLED_PATH
                    else Assets.IC_HOME_OUTLINED_PATH
                )
            }
        }

        // Search Bar
        div {
            alignItems(AlignItems.Center)
            alignSelf(AlignItems.Center)
            background(Background(color = animatedBackgroundColor.toKiluaColor()))
            border(
                Border(
                    width = if (focusedComponent == FocusedComponent.SearchBar) 2.px else 1.px,
                    style = BorderStyle.Solid,
                    color = animatedOutlineColor.toKiluaColor()
                )
            )
            borderRadius(25.px)
            if (focusedComponent != FocusedComponent.SearchBar) cursor(Cursor.Pointer)
            display(Display.Flex)
            height(50.px)
            onClick { textFieldRef?.focus() }
            onMouseOut {
                backgroundColor = Colors.container
                isSearchBarHovered = false
                outlineColor = Colors.transparent
            }
            onMouseOver {
                backgroundColor = Colors.containerHighlighted
                isSearchBarHovered = true
                outlineColor = Colors.outline
            }
            marginLeft(8.px)
            marginRight(8.px)
            width(450.px)

            // Search Icon
            svg(viewBox = Constants.VIEW_BOX_24) {
                draggable(false)
                fill(
                    if (
                        isSearchBarHovered ||
                        focusedComponent == FocusedComponent.SearchBar
                    ) Colors.white.value
                    else Colors.onContainer.value
                )
                height(24.px)
                paddingLeft(12.px)
                paddingRight(12.px)
                path(Assets.IC_SEARCH_PATH_24)
            }

            // TextField
            textFieldRef = textRef {
                border(Border(style = BorderStyle.None))
                color(Colors.white)
                if (focusedComponent != FocusedComponent.SearchBar) cursor(Cursor.Pointer)
                flexGrow(1)
                onBlur { focusedComponent = null }
                onFocus { focusedComponent = FocusedComponent.SearchBar }
                outline(Outline(style = OutlineStyle.None))
                paddingBottom(12.px)
                paddingTop(12.px)
                placeholder("What do you want to play?")
                style("font-weight", "500")
            }

            // Vertical Divider
            div {
                alignSelf(AlignItems.Center)
                background(Background(color = Colors.onContainer))
                height(28.px)
                opacity(0.5)
                style("width", "0.5px")
            }

            // Browser Icon
            val browseButtonHoverStyle = dev.kilua.html.style.style(pClass = PClass.Hover) {
                scale(Constants.SCALE_HOVERED)
                style("fill", Colors.white.value)
            }
            val browseButtonPressStyle = dev.kilua.html.style.style(pClass = PClass.Active) {
                opacity(0.8)
                scale(1.0f)
                style("fill", Colors.onContainer.value)
            }
            svg(
                className = browseButtonHoverStyle % browseButtonPressStyle,
                viewBox = Constants.VIEW_BOX_24
            ) {
                draggable(false)
                fill(
                    if (focusedComponent == FocusedComponent.SearchBar) Colors.white.value
                    else Colors.onContainer.value
                )
                height(24.px)
                marginLeft(12.px)
                marginRight(16.px)
                role(Constants.Role.BUTTON)
                width(24.px)
                if (focusedComponent == FocusedComponent.SearchBar) {
                    path(Assets.IC_BROWSER_FILLED_PATH)
                } else for (currentPath in Assets.IC_BROWSER_OUTLINED_PATHS_16) {
                    path(currentPath)
                }
            }
        }

        // Spacer
        div { flexGrow(1) }

        if (breakpoint isGreaterThan Breakpoint.Tablet) {
            // Explore Premium Button
            button(className = hoverStyle % pressStyle) {
                alignSelf(AlignItems.Center)
                background(Background(color = Colors.offWhite))
                border(Border(style = BorderStyle.None))
                borderRadius(18.px)
                color(Colors.black)
                fontSize(14.px)
                fontWeight(FontWeight.Bold)
                overflow(Overflow.Hidden)
                paddingBottom(6.px)
                paddingLeft(16.px)
                paddingRight(16.px)
                paddingTop(6.px)
                textOverflow(TextOverflow.Ellipsis)
                whiteSpace(WhiteSpace.Nowrap)
                +"Explore Premium"
            }
        }

        // Install App Button
        div(className = hoverStyle % pressStyle) {
            alignItems(AlignItems.Center)
            display(Display.Flex)
            marginLeft(16.px)
            role(Constants.Role.BUTTON)

            // Download Icon
            svg(viewBox = Constants.VIEW_BOX_16) {
                fill(Colors.white.value)
                height(16.px)
                path(Assets.IC_DOWNLOAD_ARROW_PATH)
                path(Assets.IC_DOWNLOAD_BORDER_PATH)
                width(16.px)
            }

            // Download Text
            div {
                color(Colors.offWhite)
                fontSize(14.px)
                fontWeight(FontWeight.Bold)
                marginLeft(4.px)
                overflow(Overflow.Hidden)
                textOverflow(TextOverflow.Ellipsis)
                whiteSpace(WhiteSpace.Nowrap)
                +"Install App"
            }
        }

        // Notification Icon Button
        val notificationButtonHoverStyle = dev.kilua.html.style.style(pClass = PClass.Hover) {
            scale(Constants.SCALE_HOVERED)
            style("fill", Colors.white.value)
        }
        val notificationButtonPressStyle = dev.kilua.html.style.style(pClass = PClass.Active) {
            opacity(0.8)
            scale(1.0f)
            style("fill", Colors.onContainer.value)
        }
        svg(
            className = notificationButtonHoverStyle % notificationButtonPressStyle,
            viewBox = Constants.VIEW_BOX_16
        ) {
            alignSelf(AlignItems.Center)
            fill(
                if (focusedComponent == FocusedComponent.BellIcon) Colors.white.value
                else Colors.onContainer.value
            )
            height(16.px)
            marginLeft(32.px)
            marginRight(24.px)
            onClick { focusedComponent = FocusedComponent.BellIcon }
            role(Constants.Role.BUTTON)
            width(16.px)
            path(
                if (focusedComponent == FocusedComponent.BellIcon) Assets.IC_BELL_FILLED_PATH
                else Assets.IC_BELL_OUTLINED_PATH
            )
        }

        // User Avatar
        div(className = notificationButtonHoverStyle % notificationButtonPressStyle) {
            alignItems(AlignItems.Center)
            alignSelf(AlignItems.Center)
            background(Background(color = Colors.container))
            borderRadius(24.px)
            display(Display.Flex)
            height(48.px)
            justifyContent(JustifyContent.Center)
            role(Constants.Role.BUTTON)
            width(48.px)
            div {
                alignItems(AlignItems.Center)
                background(Background(color = Colors.avatarBackground))
                borderRadius(16.px)
                color(Colors.black)
                display(Display.Flex)
                fontSize(13.px)
                fontWeight(FontWeight.Bold)
                height(32.px)
                justifyContent(JustifyContent.Center)
                width(32.px)
                +"S"
            }
        }
    }
}

private enum class FocusedComponent { HomeButton, SearchBar, BellIcon }
