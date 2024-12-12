package components

import Assets
import Colors
import Constants
import ContentOpacity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import animateColorOnInteraction
import animateOpacityOnInteraction
import animateScaleOnInteraction
import dev.kilua.core.IComponent
import dev.kilua.externals.get
import dev.kilua.form.number.IRange
import dev.kilua.form.number.range
import dev.kilua.html.AlignItems
import dev.kilua.html.Background
import dev.kilua.html.Color
import dev.kilua.html.Cursor
import dev.kilua.html.IDiv
import dev.kilua.html.JustifyContent
import dev.kilua.html.TextOverflow
import dev.kilua.html.div
import dev.kilua.html.divt
import dev.kilua.html.img
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.html.style.style
import dev.kilua.html.vw
import dev.kilua.panel.hPanel
import dev.kilua.panel.vPanel
import dev.kilua.svg.circle
import dev.kilua.svg.path
import dev.kilua.svg.svg
import dev.kilua.utils.rem
import rememberIsHoveredAsState
import web.dom.events.Event

@Composable
fun IComponent.FooterPlayer() {
    var isShuffleSelected by remember { mutableStateOf(true) }
    var repeatState by remember { mutableStateOf(RepeatState.One) }
    var isMusicPlaying by remember { mutableStateOf(false) }
    var isNowPlayingSelected by remember { mutableStateOf(true) }
    var isLyricsSelected by remember { mutableStateOf(false) }
    var isQueueSelected by remember { mutableStateOf(false) }
    var isConnectDevicesSelected by remember { mutableStateOf(false) }
    var isMiniPlayerSelected by remember { mutableStateOf(false) }

    hPanel {
        background(Background(color = Color.Black))
        height(88.px)
        padding(16.px)

        // Current Song Details
        hPanel {
            flexGrow()
            img(src = "assets/now_playing_img.png") {
                borderRadius(4.px)
                style("aspect-ratio", "1/1")
            }
            vPanel(justifyContent = JustifyContent.Center) {
                marginLeft(16.px)
                divt("I'm Good (Blue)") {
                    fontSize(14.px)
                }
                divt("David Guetta, Bebe Rexha") {
                    color(Colors.onContainer)
                    fontSize(12.px)
                    style("font-weight", "300")
                    textOverflow(TextOverflow.Ellipsis)
                }
            }
            controlIcon(Assets.IC_ADD_OUTLINED_PATHS_16) {
                alignSelf(AlignItems.Center)
                marginLeft(8.px)
            }
            div { flexGrow(1) }
        }

        // Controls
        vPanel(justifyContent = JustifyContent.Center) {
            flexGrow()
            hPanel(
                alignItems = AlignItems.Center,
                gap = 24.px
            ) {
                alignSelf(AlignItems.Center)
                controlIcon(
                    paths = Assets.IC_SHUFFLE_PATHS_16,
                    isSelected = isShuffleSelected,
                    onClick = { isShuffleSelected = !isShuffleSelected },
                )
                controlIcon(
                    paths = arrayOf(Assets.IC_PREVIOUS_TRACK_BTN_PATH_16),
                    onClick = {}
                )
                svg(viewBox = Constants.VIEW_BOX_32) {
                    animateOpacityOnInteraction(
                        onHover = ContentOpacity.SELECTED_HOVERED,
                        onPress = ContentOpacity.SELECTED_PRESSED,
                    )
                    animateScaleOnInteraction()
                    fill(Colors.white.value)
                    height(32.px)
                    onClick { isMusicPlaying = !isMusicPlaying }
                    role(Constants.Role.BUTTON)
                    width(32.px)
                    circle(16.px, 16.px, 16.px)
                    path(if (isMusicPlaying) Assets.IC_PAUSE_PATH_16 else Assets.IC_PLAY_PATH) {
                        fill(Colors.black.value)
                        if (isMusicPlaying) transform("translate(8 8)")
                        else transform("translate(-1 -1) scale(1.4 1.4)")
                    }
                }
                controlIcon(
                    paths = arrayOf(Assets.IC_NEXT_TRACK_BTN_PATH_16),
                    onClick = {}
                )
                controlIcon(
                    paths = if (repeatState == RepeatState.One) Assets.IC_REPEAT_ONE_PATH_16
                    else arrayOf(Assets.IC_REPEAT_PATH_16),
                    isSelected = repeatState != RepeatState.Off,
                    onClick = {
                        repeatState = when (repeatState) {
                            RepeatState.Off -> RepeatState.On
                            RepeatState.On -> RepeatState.One
                            RepeatState.One -> RepeatState.Off
                        }
                    },
                )
            }

            // Spacer
            div { height(8.px) }

            hPanel(alignItems = AlignItems.Center) {
                fontSize(12.px)
                style("font-weight", "300")
                divt("0:13") {
                    opacity(ContentOpacity.SELECTED_PRESSED.toDouble())
                }
                SpotifyRange(
                    className = "track-progress-slider",
                    initialValue = 13
                ) {
                    flexGrow(1)
                    marginLeft(8.px)
                    marginRight(8.px)
                }
                divt("2:55") {
                    opacity(ContentOpacity.SELECTED_PRESSED.toDouble())
                }
            }
        }
        hPanel(
            alignItems = AlignItems.Center,
            gap = 16.px,
            justifyContent = JustifyContent.End
        ) {
            alignSelf(AlignItems.Center)
            flexGrow()
            marginLeft(16.px)
            controlIcon(
                paths = Assets.IC_NOW_PLAYING_PATHS_16,
                isSelected = isNowPlayingSelected,
                onClick = { isNowPlayingSelected = !isNowPlayingSelected },
            )
            controlIcon(
                paths = arrayOf(Assets.IC_MIC_PATH_16),
                isSelected = isLyricsSelected,
                onClick = { isLyricsSelected = !isLyricsSelected },
            )
            controlIcon(
                paths = arrayOf(Assets.IC_QUEUE_PATH_16),
                isSelected = isQueueSelected,
                onClick = { isQueueSelected = !isQueueSelected },
            )
            controlIcon(
                paths = Assets.IC_CONNECT_DEVICE_PATHS_16,
                isSelected = isConnectDevicesSelected,
                onClick = { isConnectDevicesSelected = !isConnectDevicesSelected },
            )
            hPanel(alignItems = AlignItems.Center) {
                var progress by remember { mutableIntStateOf(0) }
                val volumeLevel by remember {
                    derivedStateOf {
                        when (progress) {
                            0 -> VolumeLevel.Off
                            in 1..33 -> VolumeLevel.Level1
                            in 33..66 -> VolumeLevel.Level2
                            else -> VolumeLevel.Level3
                        }
                    }
                }

                controlIcon(
                    when (volumeLevel) {
                        VolumeLevel.Off -> Assets.IC_VOLUME_OFF_PATHS_16
                        VolumeLevel.Level1 -> arrayOf(Assets.IC_VOLUME_LEVEL_1_PATH_16)
                        VolumeLevel.Level2 -> arrayOf(Assets.IC_VOLUME_LEVEL_2_PATH_16)
                        VolumeLevel.Level3 -> Assets.IC_VOLUME_LEVEL_3_PATHS_16
                    }
                )
                SpotifyRange(
                    className = "volume-slider",
                    initialValue = 30,
                    onProgressChange = { progress = it }
                ) {
                    marginLeft(8.px)
                    minWidth(48.px)
                    width(6.vw)
                }
            }
            controlIcon(
                paths = Assets.IC_MINIPLAYER_PATHS_16,
                isSelected = isMiniPlayerSelected,
                onClick = { isMiniPlayerSelected = !isMiniPlayerSelected },
            )
            controlIcon(
                paths = arrayOf(Assets.IC_FULLSCREEN_PATH_16),
                onClick = {}
            )
        }
    }
}

@Composable
private fun IComponent.SpotifyRange(
    className: String,
    initialValue: Int? = null,
    onProgressChange: ((Int) -> Unit)? = null,
    config: (@Composable IRange.() -> Unit)? = null
) {
    var progress by remember { mutableIntStateOf(initialValue ?: 0) }
    var isHovered by remember { mutableStateOf(false) }
    val progressColor by remember {
        derivedStateOf { if (isHovered) Color("#1ab853") else Color.White }
    }
    val thumbClassName = style(selector = ".$className::-webkit-slider-thumb") {
        background(Background(color = if (isHovered) Colors.white else Colors.transparent))
        borderRadius(5.px)
        height(10.px)
        style("-webkit-appearance", "none")
        style("appearance", "none")
        style("transform", "translate(0px, -3px)")
        width(10.px)
    }
    val runnableTrackClassName = style(selector = ".$className::-webkit-slider-runnable-track") {
        borderRadius(2.px)
        height(4.px)
        style(
            "background",
            "linear-gradient(to right, ${progressColor.value} $progress%, #4d4c4c $progress%)"
        )
    }
    range(className = className % thumbClassName % runnableTrackClassName) {
        isHovered = rememberIsHoveredAsState().value

        LaunchedEffect(Unit) {
            setValue(progress)
            onProgressChange?.invoke(progress)
        }

        cursor(Cursor.Pointer)
        height(10.px)
        onEvent<Event>("input") {
            progress = it.target?.get("value").toString().toInt()
            onProgressChange?.invoke(progress)
        }
        style("-webkit-appearance", "none")
        style("appearance", "none")
        style("outline", "none")
        width(100.perc)
        config?.invoke(this)
    }
}

/** Ref: [Stackoverflow](https://stackoverflow.com/a/29503264) */
@Composable
private fun IDiv.flexGrow() = style("flex", "1 1 0px")

@Composable
private fun IComponent.controlIcon(
    paths: Array<String>,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null,
    style: (@Composable IDiv.() -> Unit)? = null,
) {
    val size = remember { 16 }
    val contentColor = remember(isSelected) {
        if (isSelected) Colors.greenButtonBG else Colors.white
    }
    vPanel(alignItems = AlignItems.Center) {
        animateOpacityOnInteraction()
        animateScaleOnInteraction()
        onClick { onClick?.invoke() }
        style?.invoke(this)
        div { height((size / 2).px) }
        svg(viewBox = Constants.VIEW_BOX_16) {
            animateColorOnInteraction(
                normalColor = if (isSelected) Colors.greenButtonBG else Colors.onContainer,
                hoverColor = contentColor,
                applyOnFill = true,
            )
            height(size.px)
            minHeight(size.px)
            minWidth(size.px)
            role(Constants.Role.BUTTON)
            width(size.px)
            for (currentPath in paths) path(currentPath)
        }
        svg(viewBox = Constants.VIEW_BOX_4) {
            animateColorOnInteraction(
                normalColor = Colors.greenButtonBG,
                hoverColor = contentColor,
                applyOnFill = true,
            )
            height(4.px)
            marginTop(4.px)
            opacity(if (isSelected) 1.0 else 0.0)
            width(4.px)
            circle(2.px, 2.px, 2.px)
        }
    }
}

private enum class RepeatState { Off, On, One }

private enum class VolumeLevel { Off, Level1, Level2, Level3 }
