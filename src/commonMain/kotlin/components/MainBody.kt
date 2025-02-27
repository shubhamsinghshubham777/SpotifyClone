package components

import Assets
import Breakpoint
import Colors
import Constants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import animateColorOnInteraction
import dev.kilua.animation.MotionAnimation
import dev.kilua.animation.animateColorAsState
import dev.kilua.animation.animateFloatAsState
import dev.kilua.animation.animateIntAsState
import dev.kilua.core.IComponent
import dev.kilua.html.AlignItems
import dev.kilua.html.BoxShadow
import dev.kilua.html.Color
import dev.kilua.html.Cursor
import dev.kilua.html.Display
import dev.kilua.html.FlexWrap
import dev.kilua.html.FontWeight
import dev.kilua.html.IDiv
import dev.kilua.html.JustifyContent
import dev.kilua.html.Overflow
import dev.kilua.html.Position
import dev.kilua.html.TextDecoration
import dev.kilua.html.TextDecorationLine
import dev.kilua.html.TextOverflow
import dev.kilua.html.div
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.img
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.html.rem
import dev.kilua.html.spant
import dev.kilua.panel.flexPanel
import dev.kilua.panel.gridPanel
import dev.kilua.panel.hPanel
import dev.kilua.panel.hPanelRef
import dev.kilua.panel.vPanel
import dev.kilua.svg.path
import dev.kilua.svg.svg
import disablePointerEvents
import hideScrollbar
import models.PlaylistBasicInfo
import models.PlaylistType
import rememberBreakpoint
import rememberIsHoveredAsState
import rememberIsPressedAsState
import rememberScrollOffset
import rememberScrollPosition
import rememberWidth
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun IComponent.MainBody() {
    var selectedFilterIndex by remember { mutableIntStateOf(0) }
    var hoveredListItemIndex by remember { mutableStateOf(0) }
    val hoveredColor by remember {
        derivedStateOf {
            Color.hex(
                when (hoveredListItemIndex) {
                    0 -> 0x3B5C56
                    1 -> 0x3B4650
                    2 -> 0x461610
                    3 -> 0x301A17
                    4 -> 0x4F5E2B
                    5 -> 0x561B26
                    6 -> 0x202020
                    else -> 0x614C3D
                }
            )
        }
    }

    vPanel {
        val scrollOffset by rememberScrollOffset(ScrollDirection.Vertical)
        val headerBackgroundOpacity by remember {
            derivedStateOf {
                val percentage = (scrollOffset / 100).coerceIn(0.0, 1.0)
                when (percentage) {
                    0.0 -> 0f
                    in 0.0..0.25 -> 0.25f
                    in 0.25..0.75 -> 0.5f
                    else -> 1f
                }
            }
        }

        background(color = Colors.containerElevated)
        borderRadius(Constants.CONTAINER_RADIUS.px)
        flexGrow(1)
        overflowX(Overflow.Hidden)
        overflowY(Overflow.Auto)
        position(Position.Relative)

        // Filter Chips
        hPanel(gap = 8.px) {
            background(
                color = animateColorAsState(
                    value = hoveredColor.copy(alpha = headerBackgroundOpacity),
                    animation = MotionAnimation.Tween(
                        duration = 500.milliseconds,
                        cubicBezier = listOf(0.0, 0.0, 0.2, 1.0)
                    ) // LinearOutSlowInEasing
                ).value,
            )
            paddingBottom(verticalPadding.px)
            paddingLeft(horizontalPadding.px)
            paddingRight(horizontalPadding.px)
            paddingTop(verticalPadding.px)
            position(Position.Sticky)
            top(0.px)
            zIndex(2)
            mediaTypeFilters.forEachIndexed { index, filter ->
                SelectableChip(
                    text = filter,
                    isSelected = index == selectedFilterIndex,
                    onSelectionChange = { selectedFilterIndex = index },
                )
            }
        }

        // Main Content
        vPanel {
            width(100.perc)
            val animatedBackgroundColorState = animateColorAsState(
                value = hoveredColor,
                animation = MotionAnimation.Tween(
                    duration = 1200.milliseconds,
                    cubicBezier = listOf(0.0, 0.0, 0.2, 1.0)
                ) // LinearOutSlowInEasing
            )
            position(Position.Absolute)

            PlaylistGrid(animatedBackgroundColorState) { hoveredListItemIndex = it }

            repeat(10) { listIndex ->
                CategorisedPlaylists(
                    categoryTitle = when (listIndex) {
                        0 -> "Made For You"
                        1 -> "Your top mixes"
                        2 -> "Recently played"
                        3 -> "Recommended Stations"
                        4 -> "Jump back in"
                        5 -> "Popular radio"
                        6 -> "2024 India Wrapped"
                        7 -> "More like 00's Love Hits"
                        8 -> "More of what you like"
                        else -> "Today's biggest hits"
                    },
                    playlists = List(15) { index ->
                        val imageUrl = remember {
                            "assets/playlist_images/img_${Random.nextInt(1, 40)}.png"
                        }
                        PlaylistBasicInfo(
                            imageUrl = imageUrl,
                            artists = when (listIndex) {
                                0 -> when (index) {
                                    0 -> "Pritam, Shankar-Ehsaan-Loy, Vishal Shekhar"
                                    1 -> "Manavgeet Gill, Diljit Dosanjh, Akhil, and more"
                                    2 -> "Garciia, Luna Novina, Deep Vibrations and more"
                                    3 -> "Adnan Sami, Ghulam Ali, Shankar-Ehsaan-Loy and more"
                                    4 -> "Olivia Line, Ageena, Aksel Møller and more"
                                    5 -> "Your weekly mixtape of fresh music"
                                    else -> "Catch all the latest music from artists"
                                }

                                1 -> when (index) {
                                    0 -> "Pritam, Abhijeet and Yusuf Mohammed"
                                    1 -> "Darshan Raval, Vishal Mishra, Adnan Sami"
                                    2 -> "AKASA, David Guetta and more"
                                    3 -> "Sachin-Jigar, Salim-Sulaiman, Benny Dayal"
                                    4 -> "Sia, Ava Max, Shakira and more"
                                    5 -> "Faris Shafi, David Guetta, Wajid and more"
                                    else -> "Vishal-Shekhar, Pritam, Salim-Sulaiman"
                                }

                                2 -> when (index) {
                                    0 -> "Pritam, Shankar-Ehsaan-Loy, Vishal Shekhar"
                                    1 -> "AKASA, David Guetta and more"
                                    2 -> "With Tegi Pannu, Harnoor, AP Dhillon and more"
                                    3 -> "Groove with the tribe because 'Naina da kehna'"
                                    4 -> "Aditi Rikhari, Wajid and Vishal Mishra"
                                    5 -> "With Atif Aslam, Vishal-Shekhar, and more"
                                    else -> "This is Sanam. The essential tracks, all here."
                                }

                                3 -> when (index) {
                                    0 -> "With Karan Aujla, Shubh, Sidhu Moosewala"
                                    1 -> "With Uttam Singh, Lata Mangeshkar, and more"
                                    2 -> "With Pritam, Mohit Chauhan, Vishal-Shekhar"
                                    3 -> "With Mustafa Zahid, Atif Aslam, Vishal-Shekhar"
                                    4 -> "With Darshan Raval, Sachet Tandon, Atif Aslam"
                                    5 -> "With Pritam, Sachin-Jigar, Mohit Chauhan"
                                    else -> "With Darshan Raval, Ankit Tiwari, Vishal Mishra"
                                }

                                4 -> when (index) {
                                    0 -> "2003 to 2020 emraan hashmi hits"
                                    1 -> "Fall in love with 00's Bollywood like never before"
                                    2 -> "Adnan Sami"
                                    3 -> "Hottest tracks from Coke Studio"
                                    4 -> "Artist"
                                    5 -> "Pritam"
                                    else -> "Artist"
                                }

                                5 -> when (index) {
                                    0 -> "With A.R. Rahman, Vishal-Shekhar, Atif Aslam"
                                    1 -> "With Pritam, Mohit Chauhan, Armaan Malik"
                                    2 -> "With Harris Jayaraj, Anirudh..."
                                    3 -> "With Anuradha Paudwal, Asha Bhosale"
                                    4 -> "With A.R. Rahman, Swarnalatha,..."
                                    5 -> "With Shubh, Diljit Dosanjh, Sidhu Moosewala"
                                    else -> "With Badshah, Diljit Dosanjh, Guru Randhawa"
                                }

                                6 -> when (index) {
                                    0 -> "The most streamed songs of 2024 in India"
                                    1 -> "The most streamed songs of 2024 in Hindi"
                                    2 -> "I-Pop tracks we loved the most in 2024"
                                    3 -> "The most streamed tracks of 2024 in Punjabi"
                                    4 -> "The most streamed tracks of 2024 in Telugu"
                                    5 -> "The most streamed tracks of 2024 in Tamil"
                                    else -> "The most streamed tracks of 2024 in Malayalam"
                                }

                                7 -> when (index) {
                                    0 -> "Bollywood songs that ruled hearts in 00's"
                                    1 -> "Soulful tunes for the heart that has love"
                                    2 -> "Celebrate the Queen of melodies Shreya Ghoshal"
                                    3 -> "Essential tracks of Emraan Hashmi, all in one playlist"
                                    4 -> "Essential tracks of Shah Rukh Khan, all in one playlist"
                                    5 -> "Bollywood's mesmerising ode to sufi"
                                    else -> "Essential tracks of Alia Bhatt all in one playlist"
                                }

                                8 -> when (index) {
                                    0 -> "Turn down the lights, these are the..."
                                    1 -> "Catch all the celebrations and dancing"
                                    2 -> "A perfect travel mix for your journey."
                                    3 -> "Bollywood's mesmerising ode to sufi."
                                    4 -> "Let these songs help you walk that extra mile"
                                    5 -> "Essential tracks of Alia Bhatt all in one playlist"
                                    else -> "Essential tracks of Shahid Kapoor all in one playlist"
                                }

                                else -> when (index) {
                                    0 -> "Every track you're listening/should be listening to"
                                    1 -> "Hottest Hindi music that India is listening to"
                                    2 -> "Hottest tracks from your favourite I-Pop icons"
                                    3 -> "Catch the hottest Punjabi tracks. Cover Diljit Dosanjh."
                                    4 -> "Catch all the top Haryanvi hits. Cover"
                                    5 -> "Ultimate 101 Punjabi Hits with Arjan Velly"
                                    else -> "Our editor's picks for best Malayalam songs in 2024"
                                }
                            },
                            type = when {
                                listIndex == 4 && (index == 4 || index == 6) -> PlaylistType.Artist
                                else -> PlaylistType.Songs
                            },
                            title = when (listIndex) {
                                2 -> when (index) {
                                    0 -> "Daily Mix 1"
                                    1 -> "Dance/Electronic Mix"
                                    2 -> "The PropheC Radio"
                                    3 -> "New Music Hindi"
                                    4 -> "Shreya Ghoshal Mix"
                                    5 -> "Arijit Singh Radio"
                                    else -> "This Is Sanam"
                                }

                                4 -> when (index) {
                                    0 -> "Emraan Hashmi 2003 to 2020"
                                    1 -> null
                                    2 -> "Tera Chehra"
                                    3 -> null
                                    4 -> "Adnan Sami"
                                    5 -> "Laal Singh Chadha"
                                    else -> "Harsh Kargeti"
                                }

                                else -> null
                            }
                        )
                    }
                )
            }

            // Footer Links
            flexPanel(justifyContent = JustifyContent.SpaceBetween) {
                flexWrap(FlexWrap.Wrap)
                marginBottom(32.px)
                paddingLeft(32.px)
                paddingRight(32.px)
                style("gap", "48px")
                FooterLinks(title = "Company", links = listOf("About", "Jobs", "For the Record"))
                FooterLinks(
                    title = "Communities",
                    links = listOf(
                        "For Artists",
                        "Developers",
                        "Advertising",
                        "Investors",
                        "Vendors"
                    ),
                )
                FooterLinks(title = "Useful links", links = listOf("Support", "Free Mobile App"))
                FooterLinks(
                    title = "Spotify Plans",
                    links = listOf(
                        "Premium Individual",
                        "Premium Duo",
                        "Premium Family",
                        "Premium Student",
                        "Spotify Free",
                    )
                )

                // Social Media Buttons
                hPanel(gap = 16.px) {
                    SocialMediaButton(*Assets.IC_INSTAGRAM_LOGO_PATHS_16)
                    SocialMediaButton(Assets.IC_TWITTER_LOGO_PATH_16)
                    SocialMediaButton(Assets.IC_FACEBOOK_LOGO_PATH_16)
                }
            }

            // Divider
            div {
                background(color = Colors.containerHighlighted)
                flexGrow(1)
                height(1.px)
                marginBottom(32.px)
                marginLeft(32.px)
                marginRight(32.px)
            }

            // Quick Links and Copyright
            hPanel(gap = 16.px, rowGap = 8.px) {
                flexWrap(FlexWrap.Wrap)
                marginBottom(80.px)
                marginLeft(32.px)
                marginRight(32.px)

                for (link in quickLinks) QuickLink(link)

                // Spacer
                div { flexGrow(1) }

                spant("© 2024 Spotify AB") {
                    color(Colors.onContainer)
                    cursor(Cursor.Text)
                    fontSize(14.px)
                    marginRight(16.px)
                    style("font-weight", "300")
                    style("user-select", "text")
                }
            }
        }
    }
}

@Composable
private fun IDiv.CategorisedPlaylists(categoryTitle: String, playlists: List<PlaylistBasicInfo>) {
    vPanel(justifyContent = JustifyContent.Stretch) {
        var scrollPosition by remember { mutableStateOf(ScrollPosition.ReachedStart) }
        var arePlaylistsHovered by remember { mutableStateOf(false) }

        val startOverlayOpacity by animateFloatAsState(
            if (scrollPosition == ScrollPosition.ReachedStart) 0f else 0.4f
        )
        val endOverlayOpacity by animateFloatAsState(
            if (scrollPosition == ScrollPosition.ReachedEnd) 0f else 0.4f
        )

        val startArrowAnimatedOpacity by animateFloatAsState(
            when (scrollPosition) {
                ScrollPosition.InBetween, ScrollPosition.ReachedEnd ->
                    if (arePlaylistsHovered) 1f else 0f

                else -> 0f
            }
        )
        val endArrowAnimatedOpacity by animateFloatAsState(
            when (scrollPosition) {
                ScrollPosition.ReachedStart, ScrollPosition.InBetween ->
                    if (arePlaylistsHovered) 1f else 0f

                else -> 0f
            }
        )
        val arrowAnimatedOffsetX by animateIntAsState(if (arePlaylistsHovered) 0 else -16)

        position(Position.Relative)

        val titlePanel = hPanelRef(
            alignItems = AlignItems.Center,
            justifyContent = JustifyContent.SpaceBetween
        ) {
            marginBottom(8.px)
            paddingLeft(horizontalPadding.px)
            paddingRight(horizontalPadding.plus(16).px)
            spant(categoryTitle) {
                val isHovered by rememberIsHoveredAsState()
                color(Colors.white)
                fontSize(24.px)
                fontWeight(FontWeight.Bold)
                role(Constants.Role.BUTTON)
                if (isHovered) textDecoration(TextDecoration(line = TextDecorationLine.Underline))
            }
            spant("Show all") {
                val isHovered by rememberIsHoveredAsState()
                color(Colors.onContainer)
                fontSize(14.px)
                fontWeight(FontWeight.Bold)
                role(Constants.Role.BUTTON)
                if (isHovered) textDecoration(TextDecoration(line = TextDecorationLine.Underline))
            }
        }

        // Mask/Gradient on both left and right sides
        div {
            val startColor by remember {
                derivedStateOf { "rgba(18, 18, 18, $startOverlayOpacity)" }
            }
            val endColor by remember {
                derivedStateOf { "rgba(18, 18, 18, $endOverlayOpacity)" }
            }
            bottom(0.px)
            disablePointerEvents()
            position(Position.Absolute)
            style(
                "background-image",
                "linear-gradient(to right, $startColor, transparent 7%, transparent 93%, $endColor)"
            )
            style("height", "calc(100% - ${titlePanel.element.clientHeight}px)")
            width(100.perc)
            zIndex(1)
        }

        val filterScrollButtonHeight = remember(titlePanel.element.clientHeight) {
            "calc(100% - ${titlePanel.element.clientHeight}px)"
        }
        val filterScrollButtonTranslationY = remember { "translate(0px, -40px)" }

        FilterScrollButton(isStartButton = true, showGradient = false) {
            val isHovered by rememberIsHoveredAsState()
            bottom(0.px)
            left(if (isHovered) 0.px else arrowAnimatedOffsetX.px)
            opacity(if (isHovered) 1.0 else startArrowAnimatedOpacity.toDouble())
            position(Position.Absolute)
            style("height", filterScrollButtonHeight)
            style("transform", filterScrollButtonTranslationY)
            zIndex(1)
        }

        FilterScrollButton(isStartButton = false, showGradient = false) {
            val isHovered by rememberIsHoveredAsState()
            bottom(0.px)
            opacity(if (isHovered) 1.0 else endArrowAnimatedOpacity.toDouble())
            position(Position.Absolute)
            right(if (isHovered) 0.px else arrowAnimatedOffsetX.px)
            style("height", filterScrollButtonHeight)
            style("transform", filterScrollButtonTranslationY)
            zIndex(1)
        }

        hPanel {
            arePlaylistsHovered = rememberIsHoveredAsState().value
            scrollPosition = rememberScrollPosition(ScrollDirection.Horizontal).value
            hideScrollbar()
            marginBottom(48.px)
            overflowX(Overflow.Scroll)
            paddingLeft(horizontalPadding.minus(playlistItemPadding).px)
            paddingRight(horizontalPadding.plus(playlistItemPadding).px)
            position(Position.Relative)
            style("scroll-snap-type", "x mandatory")

            for (playlist in playlists) PlaylistItem(playlist)
        }
    }
}

@Composable
private fun IDiv.PlaylistItem(playlist: PlaylistBasicInfo) {
    val breakpoint by rememberBreakpoint()
    val itemSize by remember {
        derivedStateOf {
            if (breakpoint.isSmallerThan(Breakpoint.Desktop)) 171.px else 196.px
        }
    }
    vPanel {
        val isContainerHovered by rememberIsHoveredAsState()
        animateColorOnInteraction(
            normalColor = Colors.transparent,
            hoverColor = Colors.container,
            pressColor = Colors.black,
            applyOnBackground = true
        )
        borderRadius(8.px)
        padding(playlistItemPadding.px)
        role(Constants.Role.BUTTON)
        width(itemSize)
        minWidth(itemSize)
        flexPanel {
            position(Position.Relative)
            img(playlist.imageUrl) {
                borderRadius(
                    when (playlist.type) {
                        PlaylistType.Songs -> 8.px
                        PlaylistType.Artist -> 50.perc
                    }
                )
                boxShadow(
                    BoxShadow(
                        hOffset = 0.px,
                        vOffset = 4.px,
                        blurRadius = 8.px,
                        spreadRadius = 0.px,
                        color = Color.rgba(0f, 0f, 0f, 0.25f),
                    )
                )
                width(100.perc)
                style("aspect-ratio", "1/1")
                style("object-fit", "cover")
            }
            GreenPlayButton(sizePx = 48, marginBottomPx = 8) {
                val animatedOpacity by animateFloatAsState(
                    if (isContainerHovered) 1f else 0f
                )
                val animatedTranslationY by animateFloatAsState(
                    if (isContainerHovered) 0f else 4f
                )
                opacity(animatedOpacity.toDouble())
                transform("translate(0 $animatedTranslationY)")
            }
        }
        if (playlist.title != null) {
            spant(playlist.title) {
                color(Colors.white)
                marginTop(12.px)
            }
        }
        spant(playlist.artists) {
            color(Colors.onContainer)
            fontSize(0.8125.rem)
            marginTop(if (playlist.title != null) 4.px else 12.px)
            overflowY(Overflow.Hidden)
            style("-webkit-box-orient", "vertical")
            style("-webkit-line-clamp", "2")
            style("display", "-webkit-box")
            textOverflow(TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun IDiv.QuickLink(label: String) {
    spant(label) {
        val isHovered by rememberIsHoveredAsState()
        color(if (isHovered) Colors.white else Colors.onContainer)
        fontSize(14.px)
        role(Constants.Role.BUTTON)
        style("font-weight", "300")
        style("user-select", "text")
    }
}

@Composable
private fun IDiv.SocialMediaButton(vararg paths: String) {
    flexPanel(alignItems = AlignItems.Center, justifyContent = JustifyContent.Center) {
        val isPressed by rememberIsPressedAsState()
        // TODO: Extract these colors out
        animateColorOnInteraction(
            normalColor = Color.hex(0x292929),
            hoverColor = Color.hex(0x727272),
            pressColor = Color.hex(0x555555),
            applyOnBackground = true,
        )
        borderRadius(20.px)
        height(40.px)
        role(Constants.Role.BUTTON)
        width(40.px)
        svg(viewBox = Constants.VIEW_BOX_16) {
            fill(if (isPressed) "#b8b8b8" else Colors.white.value)
            height(16.px)
            width(16.px)
            for (currentPath in paths) path(currentPath)
        }
    }
}

@Composable
private fun IDiv.FooterLinks(
    title: String,
    links: List<String>,
) {
    vPanel(gap = 8.px) {
        spant(title) {
            color(Colors.white)
            fontWeight(FontWeight.Bold)
            style("user-select", "text")
        }
        for (link in links) {
            spant(link) {
                val isHovered by rememberIsHoveredAsState()
                color(if (isHovered) Colors.white else Colors.onContainer)
                if (isHovered) textDecoration(TextDecoration(line = TextDecorationLine.Underline))
                role(Constants.Role.BUTTON)
                style("font-weight", "300")
                style("user-select", "text")
            }
        }
    }
}

@Composable
private fun IDiv.PlaylistGrid(
    animatedBackgroundColorState: State<Color>,
    onHoveredIndexChanged: (Int) -> Unit,
) {
    gridPanel {
        val breakpoint by rememberBreakpoint()
        val gridWidth by rememberWidth()
        val isLargeGrid by remember { derivedStateOf { (gridWidth ?: 0.0) >= 815 } }
        val isNotDesktop by remember { derivedStateOf { breakpoint != Breakpoint.Desktop } }
        columnGap(8.px)
        display(Display.Grid)
        gridTemplateColumns("1fr 1fr${if (isLargeGrid) " 1fr 1fr" else ""}")
        paddingBottom(56.px)
        paddingLeft(horizontalPadding.px)
        paddingRight(horizontalPadding.px)
        paddingTop(64.px)
        rowGap(if (isNotDesktop) 8.px else 12.px)
        style(
            name = "background",
            value = "linear-gradient(" +
                    "${animatedBackgroundColorState.value.value}, " +
                    "${Colors.transparent.value} 70%)"
        )
        fakePlaylists.forEachIndexed { index, title ->
            ListItem(
                title = title,
                imageUrl = when (index) {
                    0 -> Assets.Images.GRID_IMAGE_1
                    1 -> Assets.Images.GRID_IMAGE_2
                    2 -> Assets.Images.GRID_IMAGE_3
                    3 -> Assets.Images.GRID_IMAGE_4
                    4 -> Assets.Images.GRID_IMAGE_5
                    5 -> Assets.Images.GRID_IMAGE_6
                    6 -> Assets.Images.GRID_IMAGE_7
                    else -> Assets.Images.GRID_IMAGE_8
                },
                onHoverChanged = { isHovered ->
                    onHoveredIndexChanged(if (isHovered) index else 0)
                }
            )
        }
    }
}

private const val horizontalPadding = 40
private const val playlistItemPadding = 12
private const val verticalPadding = 16
private val mediaTypeFilters = listOf("All", "Music", "Podcasts")

private val fakePlaylists = listOf(
    "The PropheC Radio",
    "New Music Hindi",
    "Arijit Singh Radio",
    "Daily Mix 5",
    "Emraan Hashmi 2003 to 2020",
    "This Is Sanam",
    "Happy Vibes",
    "Daily Mix 6"
)

private val quickLinks = listOf(
    "Legal",
    "Safety & Privacy Center",
    "Privacy Policy",
    "Cookies",
    "About Ads",
    "Accessibility",
)
