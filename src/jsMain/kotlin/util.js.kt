import dev.kilua.Hot

actual fun webpackHot(): Hot? = js("import.meta.webpackHot").unsafeCast<Hot?>()
