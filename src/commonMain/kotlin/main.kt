import components.FooterPlayer
import components.SideBar
import components.TopBar
import dev.kilua.Application
import dev.kilua.BootstrapCssModule
import dev.kilua.BootstrapIconsModule
import dev.kilua.BootstrapModule
import dev.kilua.CoreModule
import dev.kilua.SplitjsModule
import dev.kilua.compose.root
import dev.kilua.html.FlexDirection
import dev.kilua.html.div
import dev.kilua.html.px
import dev.kilua.html.vh
import dev.kilua.panel.flexPanel
import dev.kilua.startApplication

class App : Application() {
    override fun start(state: String?) {
        root("root") {
            flexPanel {
                flexDirection(FlexDirection.Column)
                height(100.vh)
                minHeight(700.px)
                minWidth(850.px)
                TopBar()
                div {
                    flexGrow(1)
                    SideBar()
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
