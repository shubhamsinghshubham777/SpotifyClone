package components

import Assets
import Colors
import Constants
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import animateColorOnInteraction
import animateScaleOnInteraction
import dev.kilua.core.IComponent
import dev.kilua.html.AlignItems
import dev.kilua.html.Background
import dev.kilua.html.BoxShadow
import dev.kilua.html.Color
import dev.kilua.html.Display
import dev.kilua.html.ISpan
import dev.kilua.html.JustifyContent
import dev.kilua.html.JustifyItems
import dev.kilua.html.Overflow
import dev.kilua.html.Position
import dev.kilua.html.TextAlign
import dev.kilua.html.TextOverflow
import dev.kilua.html.WhiteSpace
import dev.kilua.html.div
import dev.kilua.html.divt
import dev.kilua.html.img
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.html.span
import dev.kilua.html.spant
import dev.kilua.html.style.style
import dev.kilua.panel.flexPanel
import dev.kilua.panel.hPanel
import dev.kilua.panel.vPanel
import dev.kilua.svg.path
import dev.kilua.svg.svg
import rememberIsHoveredAsState
import rememberIsPressedAsState
import rememberScrollPosition
import scale
import toComposeColor
import toHexString
import toKiluaColor

@Composable
fun IComponent.SideBar(
    widthState: State<Int?>,
    splitPanelMinWidth: Int,
    splitPanelSecondBreakpointMinWidth: Int,
    splitPanelSecondBreakpointMaxWidth: Int,
) {
    val isExpanded by remember {
        derivedStateOf { (widthState.value ?: 0) > splitPanelMinWidth }
    }
    val isFullExpandedMin by remember {
        derivedStateOf { (widthState.value ?: 0) >= splitPanelSecondBreakpointMinWidth }
    }
    val isFullExpandedMax by remember {
        derivedStateOf { (widthState.value ?: 0) >= splitPanelSecondBreakpointMaxWidth }
    }

    vPanel {
        var scrollPosition by remember { mutableStateOf(ScrollPosition.ReachedStart) }
        background(Background(color = Colors.containerElevated))
        borderRadius(Constants.CONTAINER_RADIUS.px)
        height(100.perc)

        // Title Bar & Filter Row
        vPanel {
            boxShadow(
                BoxShadow(
                    hOffset = 0.px,
                    vOffset = 6.px,
                    blurRadius = 10.px,
                    color = androidx.compose.ui.graphics.Color.Black
                        .copy(
                            alpha = animateFloatAsState(
                                if (scrollPosition != ScrollPosition.ReachedStart) 0.6f else 0f
                            ).value
                        )
                        .toKiluaColor(),
                )
            )
            zIndex(1)
            TitleBar(isExpanded)
            if (isExpanded) FilterRow(isFullExpanded = isFullExpandedMin)
        }

        // Scrollable Container
        val containerClassName = "libraryScrollableContainer"
        vPanel(className = containerClassName) {
            overflowY(Overflow.Auto)
            paddingBottom(4.px)
            style (".$containerClassName::-webkit-scrollbar") {
                display(Display.None)
                // For Firefox
                style("scrollbar-width", "none")
            }
            scrollPosition = rememberScrollPosition(ScrollDirection.Vertical).value
            if (isExpanded) SearchAndRecentsButtons()
            FakeLibraryList(isExpanded = isExpanded, isFullExpanded = isFullExpandedMax)
        }
    }
}

@Composable
private fun IComponent.FakeLibraryList(
    isExpanded: Boolean,
    isFullExpanded: Boolean
) {
    repeat(24) { index ->
        LibraryItem(
            imageURL = "https://placehold.co/48x48?text=Spotify",
            name = when (index) {
                0 -> "Liked Songs"
                1 -> "New Music Hindi"
                2 -> "Adnan Sami"
                3 -> "2000s Mix"
                4 -> "2010s Mix"
                5 -> "Vishal Mishra"
                6 -> "Pop Mix"
                7 -> "This Is Amit Trivedi"
                8 -> "Punjabi Hits"
                9 -> "Jacob Collier"
                10 -> "Bollywood Jazz"
                11 -> "P&B"
                12 -> "Amit Trivedi"
                13 -> "Pritam"
                14 -> "FrivolousFox ASMR"
                15 -> "Unheard Gems - Ghulam Ali"
                16 -> "G.O.A.T."
                17 -> "The PropheC"
                18 -> "Diljit Dosanjh"
                19 -> "Punjabi Pyar"
                20 -> "The Ranveer Show"
                21 -> "Armaan Malik"
                22 -> "Ankit Tiwari"
                23 -> "Arijit Singh"
                else -> "Playlist $index"
            },
            type = when (index) {
                0 -> "Playlist"
                1 -> "Playlist"
                2 -> "Artist"
                3 -> "Playlist"
                4 -> "Playlist"
                5 -> "Artist"
                6 -> "Playlist"
                7 -> "Playlist"
                8 -> "Playlist"
                9 -> "Artist"
                10 -> "Playlist"
                11 -> "Playlist"
                12 -> "Artist"
                13 -> "Artist"
                14 -> "Artist"
                15 -> "Playlist"
                16 -> "Album"
                17 -> "Artist"
                18 -> "Artist"
                19 -> "Playlist"
                20 -> "Podcast"
                21 -> "Artist"
                22 -> "Artist"
                23 -> "Artist"
                else -> "Type $index"
            },
            sourceOrCount = when (index) {
                0 -> "42 songs"
                1 -> "Spotify"
                3 -> "Spotify"
                4 -> "Spotify"
                6 -> "Spotify"
                7 -> "Spotify"
                8 -> "shubhamsinghshubham7771"
                10 -> "Spotify"
                11 -> "Spotify"
                15 -> "Ghulam Ali"
                16 -> "Diljit Dosanjh"
                19 -> "Spotify"
                20 -> "BeerBiceps"
                else -> null
            },
            isPinned = index == 0,
            dateAdded = when (index) {
                0 -> null
                1 -> "11 May 2019"
                2 -> "18 Oct 2024"
                3 -> "8 Jun 2024"
                4 -> "8 Jun 2024"
                5 -> "24 Mar 2024"
                6 -> "22 Jun 2022"
                7 -> "8 May 2021"
                8 -> "23 Mar 2021"
                9 -> "3 Jan 2021"
                10 -> "1 Jan 2021"
                11 -> "18 Dev 2020"
                12 -> "15 Nov 2020"
                13 -> "15 Nov 2020"
                14 -> "20 Aug 2020"
                15 -> "7 Aug 2020"
                16 -> "1 Aug 2020"
                17 -> "31 Jul 2020"
                18 -> "28 Jun 2020"
                19 -> "3 Jun 2020"
                20 -> "27 Apr 2020"
                21 -> "10 Mar 2019"
                22 -> "10 Mar 2019"
                23 -> "10 Mar 2019"
                else -> null
            },
            datePlayed = when (index) {
                1 -> "3 weeks ago"
                2 -> "18 Oct 2024"
                3 -> "15 Sept 2024"
                else -> null
            },
            isExpanded = isExpanded,
            isFullExpanded = isFullExpanded,
        )
    }
}

@Composable
private fun IComponent.LibraryItem(
    imageURL: String,
    name: String,
    type: String,
    sourceOrCount: String?,
    isPinned: Boolean,
    dateAdded: String?,
    datePlayed: String?,
    isExpanded: Boolean,
    isFullExpanded: Boolean,
) {
    hPanel(alignItems = AlignItems.Center, justifyContent = JustifyContent.Center) {
        val isHovered by rememberIsHoveredAsState()
        animateColorOnInteraction(
            normalColor = Colors.transparent,
            hoverColor = Colors.container,
            pressColor = Colors.black,
            applyOnBackground = true
        )
        borderRadius(8.px)
        color(Colors.onContainer)
        fontSize(14.px)
        marginLeft(if (isExpanded) 8.px else 4.px)
        marginRight(if (isExpanded) 8.px else 4.px)
        role(Constants.Role.BUTTON)

        span {
            val imgBorderRadius = remember { 4 }
            val imgPadding = remember { 8 }
            val imgSize = remember { 64 }
            val overlaySize = remember { (imgSize - (imgPadding * 2)) }
            height(imgSize.px)
            padding(imgPadding.px)
            position(Position.Relative)
            width(imgSize.px)
            img(imageURL) {
                borderRadius(
                    if (type == "Artist") imgSize.div(2).px
                    else imgBorderRadius.px
                )
            }
            // Dark Overlay
            if (isExpanded && isHovered) {
                flexPanel(
                    alignItems = AlignItems.Center,
                    justifyContent = JustifyContent.Center
                ) {
                    background(Background(color = Color.Black))
                    borderRadius(imgBorderRadius.px)
                    height(overlaySize.px)
                    left(imgPadding.px)
                    opacity(0.75)
                    position(Position.Absolute)
                    top(imgPadding.px)
                    width(overlaySize.px)
                    svg(viewBox = Constants.VIEW_BOX_24) {
                        fill(Colors.white.value)
                        path(Assets.IC_PLAY_PATH)
                    }
                }
            }
        }

        if (isExpanded) {
            vPanel {
                fontSize(16.px)
                marginLeft(4.px)
                marginRight(12.px)
                overflow(Overflow.Hidden)
                if (isFullExpanded) width(50.perc)
                divt(name) {
                    overflow(Overflow.Hidden)
                    textOverflow(TextOverflow.Ellipsis)
                    whiteSpace(WhiteSpace.Nowrap)
                }
                hPanel(alignItems = AlignItems.Center) {
                    marginRight(16.px)
                    if (isPinned) {
                        svg(viewBox = Constants.VIEW_BOX_16) {
                            fill(Colors.greenButtonBG.value)
                            height(12.px)
                            marginRight(4.px)
                            width(12.px)
                            path(Assets.IC_PIN_PATH_16)
                        }
                    }
                    divt("$type${if (sourceOrCount != null) " • $sourceOrCount" else ""}") {
                        color(Colors.onContainer)
                        fontSize(14.px)
                        overflow(Overflow.Hidden)
                        textOverflow(TextOverflow.Ellipsis)
                        whiteSpace(WhiteSpace.Nowrap)
                    }
                }
            }

            div { flexGrow(1) }

            if (isFullExpanded) {
                divt(dateAdded.orEmpty()) {
                    color(Colors.onContainer)
                    width(25.perc)
                }
                div { flexGrow(1) }
                divt(datePlayed.orEmpty()) {
                    color(Colors.onContainer)
                    textAlign(TextAlign.Right)
                    width(25.perc)
                }
                span { marginRight(16.px) }
            }
        }
    }
}

@Composable
private fun IComponent.SearchAndRecentsButtons() {
    hPanel {
        marginLeft(16.px)
        marginRight(16.px)
        // Search Button
        flexPanel(
            alignItems = AlignItems.Center,
            justifyContent = JustifyContent.Center
        ) {
            val isHovered by rememberIsHoveredAsState()
            background(
                Background(
                    color = animateColorAsState(
                        when {
                            isHovered -> Colors.containerHighlighted
                            else -> Colors.transparent
                        }.value.toComposeColor()
                    ).value.toKiluaColor()
                )
            )
            borderRadius(16.px)
            height(32.px)
            role(Constants.Role.BUTTON)
            width(32.px)
            svg(viewBox = Constants.VIEW_BOX_16) {
                fill(
                    animateColorAsState(
                        when {
                            isHovered -> Colors.white
                            else -> Colors.onContainer
                        }.value.toComposeColor()
                    ).value.toHexString()
                )
                height(16.px)
                width(16.px)
                path(Assets.IC_SEARCH_PATH_16)
            }
        }

        // Spacer
        div { flexGrow(1) }

        // Recents Button
        hPanel(
            alignItems = AlignItems.Center,
            justifyContent = JustifyContent.Center
        ) {
            val contentColor by animateColorOnInteraction(
                normalColor = Colors.onContainer,
                hoverColor = Colors.white,
                pressColor = Color("#838383")
            )

            animateScaleOnInteraction()
            marginRight(8.px)
            role(Constants.Role.BUTTON)

            spant("Recents") {
                color(contentColor)
                fontSize(13.px)
                marginRight(4.px)
                style("font-weight", "300")
            }

            svg {
                fill(contentColor.value)
                height(16.px)
                width(16.px)
                path(Assets.IC_MENU_PATH_16)
            }
        }
    }
}

@Composable
private fun IComponent.TitleBar(isExpanded: Boolean) {
    hPanel(alignItems = AlignItems.Center) {
        height(
            titleBarHeight
                .plus(paddingVertical.times(2))
                .px
        )
        paddingBottom(paddingVertical.px)
        paddingLeft(paddingHorizontal.px)
        paddingRight(16.px)
        paddingTop(paddingVertical.px)

        hPanel {
            val contentColor by animateColorOnInteraction(
                normalColor = Colors.onContainer,
                hoverColor = Colors.white
            )
            role(Constants.Role.BUTTON)

            svg {
                fill(contentColor.value)
                height(24.px)
                width(24.px)
                path(
                    if (isExpanded) Assets.IC_LIBRARY_EXPANDED_PATH_24
                    else Assets.IC_LIBRARY_COLLAPSED_PATH_24
                )
            }

            if (isExpanded) {
                divt("Your Library") {
                    color(contentColor)
                    marginLeft(12.px)
                    overflow(Overflow.Hidden)
                    style("font-weight", "500")
                    textOverflow(TextOverflow.Ellipsis)
                    whiteSpace(WhiteSpace.Nowrap)
                }
            }
        }

        if (isExpanded) {
            div { flexGrow(1) }

            // Plus Button
            div {
                val isPlusButtonHovered by rememberIsHoveredAsState()
                val isPlusButtonPressed by rememberIsPressedAsState()
                val animatedBGColor by animateColorAsState(
                    when {
                        isPlusButtonPressed -> Colors.black
                        isPlusButtonHovered -> Colors.containerHighlighted
                        else -> Colors.transparent
                    }.value.toComposeColor()
                )
                val animatedPlusButtonContentColor by animateColorAsState(
                    when {
                        isPlusButtonHovered || isPlusButtonPressed -> Colors.white
                        else -> Colors.onContainer
                    }.value.toComposeColor()
                )

                alignItems(AlignItems.Center)
                background(Background(color = animatedBGColor.toKiluaColor()))
                borderRadius(16.px)
                display(Display.Flex)
                height(titleBarHeight.px)
                justifyContent(JustifyContent.Center)
                role(Constants.Role.BUTTON)
                width(32.px)

                svg(viewBox = Constants.VIEW_BOX_16) {
                    fill(animatedPlusButtonContentColor.toHexString())
                    height(16.px)
                    width(16.px)

                    path(Assets.IC_PLUS_PATH_16)
                }
            }
        }
    }
}

@Composable
private fun IComponent.FilterRow(isFullExpanded: Boolean) {
    var selectedChipIndex by remember { mutableStateOf<Int?>(null) }
    var scrollPosition by remember { mutableStateOf(ScrollPosition.ReachedStart) }
    val startArrowAnimatedOpacity by animateFloatAsState(
        if (scrollPosition == ScrollPosition.ReachedStart) 0f else 1f
    )
    val endArrowAnimatedOpacity by animateFloatAsState(
        if (scrollPosition == ScrollPosition.ReachedEnd || isFullExpanded) 0f else 1f
    )

    div {
        marginBottom(8.px)
        marginTop(2.px)
        position(Position.Relative)

        if (startArrowAnimatedOpacity > 0) {
            FilterScrollButton {
                left(0.px)
                opacity(startArrowAnimatedOpacity.toDouble())
                position(Position.Absolute)
            }
        }

        if (endArrowAnimatedOpacity > 0) {
            FilterScrollButton(isStartButton = false) {
                opacity(endArrowAnimatedOpacity.toDouble())
                position(Position.Absolute)
                right(0.px)
            }
        }

        hPanel {
            overflowX(Overflow.Scroll)
            paddingLeft(16.px)
            paddingRight(8.px)
            style("scrollbar-width", "none")
            scrollPosition = rememberScrollPosition(ScrollDirection.Horizontal).value
            filters.forEachIndexed { index, filter ->
                SelectableChip(
                    text = filter,
                    isSelected = selectedChipIndex == index,
                    onSelectionChange = {
                        selectedChipIndex = if (selectedChipIndex == index) null else index
                    }
                ) {
                    marginRight(8.px)
                    style("flex", "0 0 auto")
                }
            }
        }
    }
}

@Composable
private fun IComponent.FilterScrollButton(
    isStartButton: Boolean = true,
    config: @Composable ISpan.() -> Unit,
) {
    span {
        background(Background(Color.Black))
        style(
            name = "background",
            value = "linear-gradient(to ${if (isStartButton) "right" else "left"}, ${Colors.containerElevated.value} 40%, ${Colors.transparent.value})"
        )
        style("pointer-events", "none")
        width(100.px)
        config()

        span {
            val isHovered by rememberIsHoveredAsState()
            val animatedBackgroundColor by animateColorAsState(
                when {
                    isHovered -> Colors.containerHighlighted.value.toComposeColor()
                    else -> Colors.container.value.toComposeColor()
                }
            )
            val animatedContentColor by animateColorAsState(
                when {
                    isHovered -> Colors.white.value.toComposeColor()
                    else -> Colors.onContainer.value.toComposeColor()
                }
            )

            alignItems(AlignItems.Center)
            background(Background(color = animatedBackgroundColor.toKiluaColor()))
            borderRadius(16.px)
            display(Display.Flex)
            height(32.px)
            justifyContent(JustifyContent.Center)
            if (!isStartButton) justifySelf(JustifyItems.End)
            if (isStartButton) marginLeft(16.px) else marginRight(16.px)
            role(Constants.Role.BUTTON)
            style("pointer-events", "auto")
            width(32.px)
            svg(viewBox = Constants.VIEW_BOX_16) {
                fill(animatedContentColor.toHexString())
                height(16.px)
                width(16.px)
                if (!isStartButton) scale(-1f)
                path(Assets.IC_ARROW_LEFT_PATH_16)
            }
        }
    }
}

private const val paddingHorizontal = 24
private const val paddingVertical = 16
private const val titleBarHeight = 32
private val filters = listOf("Playlists", "Artists", "Albums", "Podcasts & Shows")

enum class ScrollPosition { ReachedStart, InBetween, ReachedEnd }
enum class ScrollDirection { Horizontal, Vertical }
