
import components.MiniPlayer
import components.SideBar
import components.TopBar
import dev.kilua.Application
import dev.kilua.BootstrapCssModule
import dev.kilua.BootstrapIconsModule
import dev.kilua.BootstrapModule
import dev.kilua.CoreModule
import dev.kilua.SplitjsModule
import dev.kilua.compose.root
import dev.kilua.startApplication

class App : Application() {
    override fun start(state: String?) {
        root("root") {
            TopBar()
            SideBar()
            MiniPlayer()
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
