import dev.kilua.html.Color
import androidx.compose.ui.graphics.Color as ComposeColor

object Colors {
    val avatarBackground = Color("#7d4b32")
    val black = Color("#000000")
    val container = Color("#1f1f1f")
    val containerHighlighted = Color("#2a2a2a")
    val greenButtonBG = Color("#1ED760")
    val greenButtonBGHighlighted = Color("#3ae075")
    val offWhite = Color("#f0f0f0")
    val onContainer = Color("#b3b3b3")
    val outline = Color("#404040")
    val transparent = Color("#00000000")
    val white = Color("#ffffff")
}

object ContentOpacity {
    const val HOVERED = 0.2f
    const val NORMAL = 0.16f
    const val PRESSED = 0.27f
    const val SELECTED = 1f
    const val SELECTED_HOVERED = 0.94f
    const val SELECTED_PRESSED = 0.78f
}

fun ComposeColor.toKiluaColor(): Color = Color(this.toHexString())

fun ComposeColor.toHexString(): String {
    val alpha = (this.alpha * 255).toInt()
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()
    return "#${red.toString(16).padStart(2, '0').uppercase()}" +
            green.toString(16).padStart(2, '0').uppercase() +
            blue.toString(16).padStart(2, '0').uppercase() +
            alpha.toString(16).padStart(2, '0').uppercase()
}

fun String.toComposeColor(): ComposeColor {
    val sanitizedHex = this.removePrefix("#")

    return when (sanitizedHex.length) {
        3 -> ComposeColor(
            red = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
            green = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
            blue = sanitizedHex[2].toString().toInt(16).toFloat() / 15
        )

        4 -> ComposeColor(
            alpha = sanitizedHex[0].toString().toInt(16).toFloat() / 15,
            red = sanitizedHex[1].toString().toInt(16).toFloat() / 15,
            green = sanitizedHex[2].toString().toInt(16).toFloat() / 15,
            blue = sanitizedHex[3].toString().toInt(16).toFloat() / 15
        )

        6 -> ComposeColor(
            red = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
            green = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
            blue = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255
        )

        8 -> ComposeColor(
            alpha = sanitizedHex.substring(0, 2).toInt(16).toFloat() / 255,
            red = sanitizedHex.substring(2, 4).toInt(16).toFloat() / 255,
            green = sanitizedHex.substring(4, 6).toInt(16).toFloat() / 255,
            blue = sanitizedHex.substring(6, 8).toInt(16).toFloat() / 255
        )

        else -> throw IllegalArgumentException("Invalid hex color format")
    }
}
