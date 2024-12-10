import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import components.FooterPlayer
import components.MainBody
import components.SideBar
import components.SplitPanel
import components.TopBar
import dev.kilua.Application
import dev.kilua.BootstrapCssModule
import dev.kilua.BootstrapIconsModule
import dev.kilua.BootstrapModule
import dev.kilua.CoreModule
import dev.kilua.SplitjsModule
import dev.kilua.compose.root
import dev.kilua.html.Background
import dev.kilua.html.Color
import dev.kilua.html.Overflow
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.panel.vPanel
import dev.kilua.startApplication

class App : Application() {
    override fun start(state: String?) {
        root("root") {
            val breakpoint by rememberBreakpoint()
            val sidebarWidthState = remember { mutableStateOf<Int?>(null) }
            val splitPanelBreakpoints = remember {
                listOf(
                    Constants.SIDEBAR_BREAKPOINT_1_START to Constants.SIDEBAR_BREAKPOINT_1_END,
                    Constants.SIDEBAR_BREAKPOINT_2_START to Constants.SIDEBAR_BREAKPOINT_2_END
                )
            }

            vPanel {
                background(Background(Color.Black))
                height(100.perc)
                TopBar()
                SplitPanel(
                    breakpoints = splitPanelBreakpoints,
                    collapsedMinWidth = splitPanelMinWidth,
                    collapsedMaxWidth = when (breakpoint) {
                        Breakpoint.Mobile, Breakpoint.SmallTablet -> 360
                        Breakpoint.Tablet -> 430
                        Breakpoint.Laptop -> 710
                        Breakpoint.Desktop -> 1080
                    },
                    leftItem = {
                        SideBar(
                            widthState = sidebarWidthState,
                            splitPanelMinWidth = splitPanelMinWidth,
                            splitPanelSecondBreakpointMinWidth = splitPanelBreakpoints.last().first,
                            splitPanelSecondBreakpointMaxWidth = splitPanelBreakpoints.last().second,
                        )
                    },
                    rightItem = { MainBody() },
                    onResize = { width -> sidebarWidthState.value = width }
                ) {
                    flexGrow(1)
                    margin(8.px)
                    overflowY(Overflow.Hidden)
                }
                FooterPlayer()
            }
        }
    }

    override fun dispose(): String? = null
}

fun main() {
    startApplication(
        ::App,
        webpackHot(),
        BootstrapModule,
        BootstrapCssModule,
        BootstrapIconsModule,
        SplitjsModule,
        CoreModule,
    )
}

private const val splitPanelMinWidth = 70
