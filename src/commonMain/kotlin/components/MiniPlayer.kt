package components

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.html.Background
import dev.kilua.html.Color
import dev.kilua.html.Position
import dev.kilua.html.div
import dev.kilua.html.perc
import dev.kilua.html.px
import dev.kilua.html.vw

@Composable
fun IComponent.MiniPlayer() {
    div {
        background(Background(color = Color.Black))
        bottom(0.px)
        height(88.px)
        padding(16.px)
        position(Position.Fixed)
        width(100.vw)
        div {
            background(Background(color = Color.Red))
            height(100.perc)
            width(100.perc)
        }
    }
}
