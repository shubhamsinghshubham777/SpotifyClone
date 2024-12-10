package models

data class PlaylistBasicInfo(
    val title: String? = null,
    val imageUrl: String,
    val artists: String,
    val type: PlaylistType,
)

enum class PlaylistType { Songs, Artist }
