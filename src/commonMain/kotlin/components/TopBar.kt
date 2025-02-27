package components

import Assets
import Breakpoint
import Colors
import Constants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import animateColorOnInteraction
import animateOpacityOnInteraction
import animateScaleOnInteraction
import dev.kilua.animation.animateColorAsState
import dev.kilua.core.IComponent
import dev.kilua.form.text.textRef
import dev.kilua.html.*
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.helpers.TagStyleFun.Companion.border
import dev.kilua.svg.path
import dev.kilua.svg.svg
import rememberBreakpoint

@Composable
fun IComponent.TopBar() {
    var focusedComponent: FocusedComponent? by remember { mutableStateOf(FocusedComponent.HomeButton) }
    var isSearchBarHovered by remember { mutableStateOf(false) }

    var backgroundColor by remember { mutableStateOf(Colors.container) }
    val animatedBackgroundColor by animateColorAsState(
        if (focusedComponent == FocusedComponent.SearchBar) {
            Colors.containerHighlighted
        } else backgroundColor
    )

    var outlineColor by remember { mutableStateOf(Colors.transparent) }
    val animatedOutlineColor by animateColorAsState(
        if (focusedComponent == FocusedComponent.SearchBar) Colors.white
        else outlineColor
    )

    var textFieldRef: dev.kilua.form.text.Text? = null
    val breakpoint by rememberBreakpoint()

    div {
        display(Display.Flex)
        marginLeft(28.px)
        marginRight(8.px)
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
        div {
            alignSelf(AlignItems.Center)
            animateOpacityOnInteraction()
            animateScaleOnInteraction()
            background(color = Colors.container)
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
            background(color = animatedBackgroundColor)
            border(
                Border(
                    width = if (focusedComponent == FocusedComponent.SearchBar) 2.px else 1.px,
                    style = BorderStyle.Solid,
                    color = animatedOutlineColor
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
                border(style = BorderStyle.None)
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
                background(color = Colors.onContainer)
                height(28.px)
                opacity(0.5)
                style("width", "0.5px")
            }

            // Browser Icon
            svg(viewBox = Constants.VIEW_BOX_24) {
                animateColorOnInteraction(
                    normalColor = if (focusedComponent == FocusedComponent.SearchBar) Colors.white
                    else Colors.onContainer,
                    hoverColor = Colors.white,
                    pressColor = Colors.onContainer,
                    applyOnFill = true,
                )
                animateOpacityOnInteraction()
                animateScaleOnInteraction()
                draggable(false)
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
            button {
                alignSelf(AlignItems.Center)
                animateOpacityOnInteraction()
                animateScaleOnInteraction()
                background(color = Colors.white)
                border(style = BorderStyle.None)
                borderRadius(18.px)
                color(Colors.black)
                fontSize(13.px)
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
        div {
            alignItems(AlignItems.Center)
            animateOpacityOnInteraction()
            animateScaleOnInteraction()
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
        svg(viewBox = Constants.VIEW_BOX_16) {
            alignSelf(AlignItems.Center)
            animateColorOnInteraction(
                normalColor = if (focusedComponent == FocusedComponent.BellIcon) Colors.white
                else Colors.onContainer,
                hoverColor = Colors.white,
                pressColor = Colors.onContainer,
                applyOnFill = true,
            )
            animateOpacityOnInteraction()
            animateScaleOnInteraction()
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
        div {
            alignItems(AlignItems.Center)
            animateOpacityOnInteraction()
            animateScaleOnInteraction()
            alignSelf(AlignItems.Center)
            background(color = Colors.container)
            borderRadius(24.px)
            display(Display.Flex)
            height(48.px)
            justifyContent(JustifyContent.Center)
            role(Constants.Role.BUTTON)
            width(48.px)
            div {
                alignItems(AlignItems.Center)
                background(color = Colors.avatarBackground)
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
