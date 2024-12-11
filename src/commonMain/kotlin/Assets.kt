// TODO: Use the same naming convention for all variables
object Assets {
    const val ICON_SPOTIFY_LOGO = "ic_spotify_logo.svg"

    val IC_ADD_OUTLINED_PATHS_16 = arrayOf(
        // Circle
        "M8 1.5a6.5 6.5 0 1 0 0 13 6.5 6.5 0 0 0 0-13zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z",
        // Plus
        "M11.75 8a.75.75 0 0 1-.75.75H8.75V11a.75.75 0 0 1-1.5 0V8.75H5a.75.75 0 0 1 0-1.5h2.25V5a.75.75 0 0 1 1.5 0v2.25H11a.75.75 0 0 1 .75.75z"
    )

    const val IC_ARROW_LEFT_PATH_16 =
        "M11.03.47a.75.75 0 0 1 0 1.06L4.56 8l6.47 6.47a.75.75 0 1 1-1.06 1.06L2.44 8 9.97.47a.75.75 0 0 1 1.06 0z"

    const val IC_BELL_OUTLINED_PATH =
        "M8 1.5a4 4 0 0 0-4 4v3.27a.75.75 0 0 1-.1.373L2.255 12h11.49L12.1 9.142a.75.75 0 0 1-.1-.374V5.5a4 4 0 0 0-4-4zm-5.5 4a5.5 5.5 0 0 1 11 0v3.067l2.193 3.809a.75.75 0 0 1-.65 1.124H10.5a2.5 2.5 0 0 1-5 0H.957a.75.75 0 0 1-.65-1.124L2.5 8.569V5.5zm4.5 8a1 1 0 1 0 2 0H7z"

    const val IC_BELL_FILLED_PATH =
        "M8 0a5.5 5.5 0 0 0-5.5 5.5v3.069L.307 12.376A.75.75 0 0 0 .25 13h15.5a.75.75 0 0 0-.057-.624L13.5 8.567V5.5A5.5 5.5 0 0 0 8 0zm1.937 14.5H6.063a2 2 0 0 0 3.874 0z"

    const val IC_BROWSER_FILLED_PATH =
        "M4 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v4H4V2zM1.513 9.37A1 1 0 0 1 2.291 9H21.71a1 1 0 0 1 .978 1.208l-2.17 10.208A2 2 0 0 1 18.562 22H5.438a2 2 0 0 1-1.956-1.584l-2.17-10.208a1 1 0 0 1 .201-.837zM12 17.834c1.933 0 3.5-1.044 3.5-2.333 0-1.289-1.567-2.333-3.5-2.333S8.5 14.21 8.5 15.5c0 1.289 1.567 2.333 3.5 2.333z"

    val IC_BROWSER_OUTLINED_PATHS_16 = arrayOf(
        "M1.51283 9.37C1.60674 9.25424 1.72533 9.16094 1.85994 9.09692C1.99455 9.03291 2.14177 8.99979 2.29083 9H21.7088C21.8579 8.99987 22.0051 9.03306 22.1396 9.09714C22.2742 9.16123 22.3928 9.25458 22.4866 9.37038C22.5804 9.48617 22.6472 9.62148 22.682 9.76641C22.7169 9.91133 22.7188 10.0622 22.6878 10.208L20.3488 21.208C20.3012 21.4321 20.1781 21.633 20 21.7771C19.822 21.9213 19.5999 22 19.3708 22H4.62983C4.40076 22 4.17864 21.9213 4.00062 21.7771C3.8226 21.633 3.69948 21.4321 3.65183 21.208L1.31283 10.208C1.28189 10.0623 1.28389 9.91157 1.31866 9.76677C1.35343 9.62196 1.42011 9.48674 1.51383 9.371L1.51283 9.37ZM3.52483 11L5.43783 20H18.5608L20.4738 11H3.52483ZM3.99983 2C3.99983 1.73478 4.10519 1.48043 4.29272 1.29289C4.48026 1.10536 4.73461 1 4.99983 1H18.9998C19.265 1 19.5194 1.10536 19.7069 1.29289C19.8945 1.48043 19.9998 1.73478 19.9998 2V6H17.9998V3H5.99983V6H3.99983V2Z",
        "M15 15.5C15 16.605 13.657 17.5 12 17.5C10.343 17.5 9 16.605 9 15.5C9 14.395 10.343 13.5 12 13.5C13.657 13.5 15 14.395 15 15.5Z"
    )

    val IC_CONNECT_DEVICE_PATHS_16 = arrayOf(
        "M6 2.75C6 1.784 6.784 1 7.75 1h6.5c.966 0 1.75.784 1.75 1.75v10.5A1.75 1.75 0 0 1 14.25 15h-6.5A1.75 1.75 0 0 1 6 13.25V2.75zm1.75-.25a.25.25 0 0 0-.25.25v10.5c0 .138.112.25.25.25h6.5a.25.25 0 0 0 .25-.25V2.75a.25.25 0 0 0-.25-.25h-6.5zm-6 0a.25.25 0 0 0-.25.25v6.5c0 .138.112.25.25.25H4V11H1.75A1.75 1.75 0 0 1 0 9.25v-6.5C0 1.784.784 1 1.75 1H4v1.5H1.75zM4 15H2v-1.5h2V15z",
        "M13 10a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm-1-5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z",
    )

    const val IC_DOWNLOAD_ARROW_PATH =
        "M4.995 8.745a.75.75 0 0 1 1.06 0L7.25 9.939V4a.75.75 0 0 1 1.5 0v5.94l1.195-1.195a.75.75 0 1 1 1.06 1.06L8 12.811l-.528-.528a.945.945 0 0 1-.005-.005L4.995 9.805a.75.75 0 0 1 0-1.06z"

    const val IC_DOWNLOAD_BORDER_PATH =
        "M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-6.5a6.5 6.5 0 1 0 0 13 6.5 6.5 0 0 0 0-13z"

    const val IC_FACEBOOK_LOGO_PATH_16 =
        "M16 8a8 8 0 1 0-9.25 7.903v-5.59H4.719V8H6.75V6.237c0-2.005 1.194-3.112 3.022-3.112.875 0 1.79.156 1.79.156V5.25h-1.008c-.994 0-1.304.617-1.304 1.25V8h2.219l-.355 2.313H9.25v5.59A8.002 8.002 0 0 0 16 8z"

    const val IC_FULLSCREEN_PATH_16 =
        "M6.53 9.47a.75.75 0 0 1 0 1.06l-2.72 2.72h1.018a.75.75 0 0 1 0 1.5H1.25v-3.579a.75.75 0 0 1 1.5 0v1.018l2.72-2.72a.75.75 0 0 1 1.06 0zm2.94-2.94a.75.75 0 0 1 0-1.06l2.72-2.72h-1.018a.75.75 0 1 1 0-1.5h3.578v3.579a.75.75 0 0 1-1.5 0V3.81l-2.72 2.72a.75.75 0 0 1-1.06 0z"

    const val IC_HOME_FILLED_PATH =
        "M13.5 1.51521C13.0439 1.2519 12.5266 1.11328 12 1.11328C11.4734 1.11328 10.9561 1.2519 10.5 1.51521L3 5.8452C2.69597 6.02074 2.4435 6.27321 2.26796 6.57723C2.09243 6.88126 2.00001 7.22614 2 7.57721V21.0002C2 21.2654 2.10536 21.5198 2.29289 21.7073C2.48043 21.8948 2.73478 22.0002 3 22.0002H9C9.26522 22.0002 9.51957 21.8948 9.70711 21.7073C9.89464 21.5198 10 21.2654 10 21.0002V15.0002H14V21.0002C14 21.2654 14.1054 21.5198 14.2929 21.7073C14.4804 21.8948 14.7348 22.0002 15 22.0002H21C21.2652 22.0002 21.5196 21.8948 21.7071 21.7073C21.8946 21.5198 22 21.2654 22 21.0002V7.57721C22 7.22614 21.9076 6.88126 21.732 6.57723C21.5565 6.27321 21.304 6.02074 21 5.8452L13.5 1.51521Z"

    const val IC_HOME_OUTLINED_PATH =
        "M12.5 3.247a1 1 0 0 0-1 0L4 7.577V20h4.5v-6a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v6H20V7.577l-7.5-4.33zm-2-1.732a3 3 0 0 1 3 0l7.5 4.33a2 2 0 0 1 1 1.732V21a1 1 0 0 1-1 1h-6.5a1 1 0 0 1-1-1v-6h-3v6a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V7.577a2 2 0 0 1 1-1.732l7.5-4.33z"

    val IC_INSTAGRAM_LOGO_PATHS_16 = arrayOf(
        "M8 1.44c2.136 0 2.389.009 3.233.047.78.036 1.203.166 1.485.276.348.128.663.332.921.598.266.258.47.573.599.921.11.282.24.706.275 1.485.039.844.047 1.097.047 3.233s-.008 2.389-.047 3.232c-.035.78-.166 1.204-.275 1.486a2.65 2.65 0 0 1-1.518 1.518c-.282.11-.706.24-1.486.275-.843.039-1.097.047-3.233.047s-2.39-.008-3.232-.047c-.78-.035-1.204-.165-1.486-.275a2.5 2.5 0 0 1-.921-.599 2.5 2.5 0 0 1-.599-.92c-.11-.282-.24-.706-.275-1.486-.038-.844-.047-1.096-.047-3.232s.009-2.39.047-3.233c.036-.78.166-1.203.275-1.485.129-.348.333-.663.599-.921a2.5 2.5 0 0 1 .92-.599c.283-.11.707-.24 1.487-.275.843-.038 1.096-.047 3.232-.047L8 1.441zm.001-1.442c-2.172 0-2.445.01-3.298.048-.854.04-1.435.176-1.943.373a3.9 3.9 0 0 0-1.417.923c-.407.4-.722.883-.923 1.417-.198.508-.333 1.09-.372 1.942S0 5.826 0 8c0 2.172.01 2.445.048 3.298.04.853.174 1.433.372 1.941.2.534.516 1.017.923 1.417.4.407.883.722 1.417.923.508.198 1.09.333 1.942.372s1.126.048 3.299.048 2.445-.01 3.298-.048c.853-.04 1.433-.174 1.94-.372a4.1 4.1 0 0 0 2.34-2.34c.199-.508.334-1.09.373-1.942S16 10.172 16 7.999s-.01-2.445-.048-3.298c-.04-.853-.174-1.433-.372-1.94a3.9 3.9 0 0 0-.923-1.418A3.9 3.9 0 0 0 13.24.42c-.508-.197-1.09-.333-1.942-.371-.851-.041-1.125-.05-3.298-.05z",
        "M8 3.892a4.108 4.108 0 1 0 0 8.216 4.108 4.108 0 0 0 0-8.216m0 6.775a2.668 2.668 0 1 1 0-5.335 2.668 2.668 0 0 1 0 5.335m4.27-5.978a.96.96 0 1 0 0-1.92.96.96 0 0 0 0 1.92"
    )

    const val IC_LIBRARY_COLLAPSED_PATH_24 =
        "M14.5 2.134a1 1 0 0 1 1 0l6 3.464a1 1 0 0 1 .5.866V21a1 1 0 0 1-1 1h-6a1 1 0 0 1-1-1V3a1 1 0 0 1 .5-.866zM16 4.732V20h4V7.041l-4-2.309zM3 22a1 1 0 0 1-1-1V3a1 1 0 0 1 2 0v18a1 1 0 0 1-1 1zm6 0a1 1 0 0 1-1-1V3a1 1 0 0 1 2 0v18a1 1 0 0 1-1 1z"

    const val IC_LIBRARY_EXPANDED_PATH_24 =
        "M3 22a1 1 0 0 1-1-1V3a1 1 0 0 1 2 0v18a1 1 0 0 1-1 1zM15.5 2.134A1 1 0 0 0 14 3v18a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V6.464a1 1 0 0 0-.5-.866l-6-3.464zM9 2a1 1 0 0 0-1 1v18a1 1 0 1 0 2 0V3a1 1 0 0 0-1-1z"

    const val IC_MENU_PATH_16 =
        "M15 14.5H5V13h10v1.5zm0-5.75H5v-1.5h10v1.5zM15 3H5V1.5h10V3zM3 3H1V1.5h2V3zm0 11.5H1V13h2v1.5zm0-5.75H1v-1.5h2v1.5z"

    const val IC_MIC_PATH_16 =
        "M13.426 2.574a2.831 2.831 0 0 0-4.797 1.55l3.247 3.247a2.831 2.831 0 0 0 1.55-4.797zM10.5 8.118l-2.619-2.62A63303.13 63303.13 0 0 0 4.74 9.075L2.065 12.12a1.287 1.287 0 0 0 1.816 1.816l3.06-2.688 3.56-3.129zM7.12 4.094a4.331 4.331 0 1 1 4.786 4.786l-3.974 3.493-3.06 2.689a2.787 2.787 0 0 1-3.933-3.933l2.676-3.045 3.505-3.99z"

    val IC_MINIPLAYER_PATHS_16 = arrayOf(
        "M16 2.45c0-.8-.65-1.45-1.45-1.45H1.45C.65 1 0 1.65 0 2.45v11.1C0 14.35.65 15 1.45 15h5.557v-1.5H1.5v-11h13V7H16V2.45z",
        "M15.25 9.007a.75.75 0 0 1 .75.75v4.493a.75.75 0 0 1-.75.75H9.325a.75.75 0 0 1-.75-.75V9.757a.75.75 0 0 1 .75-.75h5.925z"
    )

    const val IC_NEXT_TRACK_BTN_PATH_16 =
        "M12.7 1a.7.7 0 0 0-.7.7v5.15L2.05 1.107A.7.7 0 0 0 1 1.712v12.575a.7.7 0 0 0 1.05.607L12 9.149V14.3a.7.7 0 0 0 .7.7h1.6a.7.7 0 0 0 .7-.7V1.7a.7.7 0 0 0-.7-.7h-1.6z"

    val IC_NOW_PLAYING_PATHS_16 = arrayOf(
        "M11.196 8 6 5v6l5.196-3z",
        "M15.002 1.75A1.75 1.75 0 0 0 13.252 0h-10.5a1.75 1.75 0 0 0-1.75 1.75v12.5c0 .966.783 1.75 1.75 1.75h10.5a1.75 1.75 0 0 0 1.75-1.75V1.75zm-1.75-.25a.25.25 0 0 1 .25.25v12.5a.25.25 0 0 1-.25.25h-10.5a.25.25 0 0 1-.25-.25V1.75a.25.25 0 0 1 .25-.25h10.5z"
    )

    const val IC_PAUSE_PATH_16 =
        "M2.7 1a.7.7 0 0 0-.7.7v12.6a.7.7 0 0 0 .7.7h2.6a.7.7 0 0 0 .7-.7V1.7a.7.7 0 0 0-.7-.7H2.7zm8 0a.7.7 0 0 0-.7.7v12.6a.7.7 0 0 0 .7.7h2.6a.7.7 0 0 0 .7-.7V1.7a.7.7 0 0 0-.7-.7h-2.6z"

    const val IC_PIN_PATH_16 =
        "M8.822.797a2.72 2.72 0 0 1 3.847 0l2.534 2.533a2.72 2.72 0 0 1 0 3.848l-3.678 3.678-1.337 4.988-4.486-4.486L1.28 15.78a.75.75 0 0 1-1.06-1.06l4.422-4.422L.156 5.812l4.987-1.337L8.822.797z"

    const val IC_PLAY_PATH = "M18 12L9 17.1962L9 6.80385L18 12Z"

    const val IC_PLUS_PATH_16 =
        "M15.25 8a.75.75 0 0 1-.75.75H8.75v5.75a.75.75 0 0 1-1.5 0V8.75H1.5a.75.75 0 0 1 0-1.5h5.75V1.5a.75.75 0 0 1 1.5 0v5.75h5.75a.75.75 0 0 1 .75.75z"

    const val IC_PREVIOUS_TRACK_BTN_PATH_16 =
        "M3.3 1a.7.7 0 0 1 .7.7v5.15l9.95-5.744a.7.7 0 0 1 1.05.606v12.575a.7.7 0 0 1-1.05.607L4 9.149V14.3a.7.7 0 0 1-.7.7H1.7a.7.7 0 0 1-.7-.7V1.7a.7.7 0 0 1 .7-.7h1.6z"

    const val IC_QUEUE_PATH_16 =
        "M15 15H1v-1.5h14V15zm0-4.5H1V9h14v1.5zm-14-7A2.5 2.5 0 0 1 3.5 1h9a2.5 2.5 0 0 1 0 5h-9A2.5 2.5 0 0 1 1 3.5zm2.5-1a1 1 0 0 0 0 2h9a1 1 0 1 0 0-2h-9z"

    const val IC_REPEAT_PATH_16 =
        "M0 4.75A3.75 3.75 0 0 1 3.75 1h8.5A3.75 3.75 0 0 1 16 4.75v5a3.75 3.75 0 0 1-3.75 3.75H9.81l1.018 1.018a.75.75 0 1 1-1.06 1.06L6.939 12.75l2.829-2.828a.75.75 0 1 1 1.06 1.06L9.811 12h2.439a2.25 2.25 0 0 0 2.25-2.25v-5a2.25 2.25 0 0 0-2.25-2.25h-8.5A2.25 2.25 0 0 0 1.5 4.75v5A2.25 2.25 0 0 0 3.75 12H5v1.5H3.75A3.75 3.75 0 0 1 0 9.75v-5z"

    val IC_REPEAT_ONE_PATH_16 = arrayOf(
        "M0 4.75A3.75 3.75 0 0 1 3.75 1h.75v1.5h-.75A2.25 2.25 0 0 0 1.5 4.75v5A2.25 2.25 0 0 0 3.75 12H5v1.5H3.75A3.75 3.75 0 0 1 0 9.75v-5ZM12.25 2.5a2.25 2.25 0 0 1 2.25 2.25v5A2.25 2.25 0 0 1 12.25 12H9.81l1.018-1.018a.75.75 0 0 0-1.06-1.06L6.939 12.75l2.829 2.828a.75.75 0 1 0 1.06-1.06L9.811 13.5h2.439A3.75 3.75 0 0 0 16 9.75v-5A3.75 3.75 0 0 0 12.25 1h-.75v1.5h.75Z",
        "m8 1.85.77.694H6.095V1.488c.697-.051 1.2-.18 1.507-.385.316-.205.51-.51.583-.913h1.32V8H8V1.85Z",
    )

    const val IC_SEARCH_PATH_16 =
        "M7 1.75a5.25 5.25 0 1 0 0 10.5 5.25 5.25 0 0 0 0-10.5zM.25 7a6.75 6.75 0 1 1 12.096 4.12l3.184 3.185a.75.75 0 1 1-1.06 1.06L11.304 12.2A6.75 6.75 0 0 1 .25 7z"

    const val IC_SEARCH_PATH_24 =
        "M10.533 1.27883C5.35298 1.27883 1.12598 5.41883 1.12598 10.5578C1.12598 15.6968 5.35198 19.8368 10.533 19.8368C12.767 19.8368 14.823 19.0668 16.44 17.7788L20.793 22.1318C20.8852 22.2273 20.9956 22.3035 21.1176 22.3559C21.2396 22.4083 21.3708 22.4359 21.5036 22.4371C21.6364 22.4382 21.768 22.4129 21.8909 22.3627C22.0138 22.3124 22.1255 22.2381 22.2194 22.1442C22.3133 22.0503 22.3875 21.9387 22.4378 21.8158C22.4881 21.6929 22.5134 21.5612 22.5122 21.4284C22.5111 21.2957 22.4835 21.1644 22.4311 21.0424C22.3787 20.9204 22.3025 20.8101 22.207 20.7178L17.863 16.3738C19.2077 14.734 19.9418 12.6785 19.94 10.5578C19.94 5.41783 15.714 1.27783 10.533 1.27783M3.12598 10.5568C3.12598 6.55083 6.42798 3.27683 10.533 3.27683C14.638 3.27683 17.94 6.55083 17.94 10.5568C17.94 14.5628 14.638 17.8358 10.533 17.8358C6.42798 17.8358 3.12598 14.5628 3.12598 10.5558"

    val IC_SHUFFLE_PATHS_16 = arrayOf(
        "M13.151.922a.75.75 0 1 0-1.06 1.06L13.109 3H11.16a3.75 3.75 0 0 0-2.873 1.34l-6.173 7.356A2.25 2.25 0 0 1 .39 12.5H0V14h.391a3.75 3.75 0 0 0 2.873-1.34l6.173-7.356a2.25 2.25 0 0 1 1.724-.804h1.947l-1.017 1.018a.75.75 0 0 0 1.06 1.06L15.98 3.75 13.15.922zM.391 3.5H0V2h.391c1.109 0 2.16.49 2.873 1.34L4.89 5.277l-.979 1.167-1.796-2.14A2.25 2.25 0 0 0 .39 3.5z",
        "m7.5 10.723.98-1.167.957 1.14a2.25 2.25 0 0 0 1.724.804h1.947l-1.017-1.018a.75.75 0 1 1 1.06-1.06l2.829 2.828-2.829 2.828a.75.75 0 1 1-1.06-1.06L13.109 13H11.16a3.75 3.75 0 0 1-2.873-1.34l-.787-.938z"
    )

    const val IC_TWITTER_LOGO_PATH_16 =
        "M13.54 3.889a2.968 2.968 0 0 0 1.333-1.683 5.937 5.937 0 0 1-1.929.738 2.992 2.992 0 0 0-.996-.706 2.98 2.98 0 0 0-1.218-.254 2.92 2.92 0 0 0-2.143.889 2.929 2.929 0 0 0-.889 2.15c0 .212.027.442.08.691a8.475 8.475 0 0 1-3.484-.932A8.536 8.536 0 0 1 1.532 2.54a2.993 2.993 0 0 0-.413 1.523c0 .519.12 1 .361 1.445.24.445.57.805.988 1.08a2.873 2.873 0 0 1-1.373-.374v.04c0 .725.23 1.365.69 1.92a2.97 2.97 0 0 0 1.739 1.048 2.937 2.937 0 0 1-1.365.056 2.94 2.94 0 0 0 1.063 1.5 2.945 2.945 0 0 0 1.77.603 5.944 5.944 0 0 1-3.77 1.302c-.243 0-.484-.016-.722-.048A8.414 8.414 0 0 0 5.15 14c.905 0 1.763-.12 2.572-.361.81-.24 1.526-.57 2.147-.988a9.044 9.044 0 0 0 1.683-1.46c.5-.556.911-1.155 1.234-1.798a9.532 9.532 0 0 0 .738-1.988 8.417 8.417 0 0 0 .246-2.429 6.177 6.177 0 0 0 1.508-1.563c-.56.249-1.14.407-1.738.476z"

    val IC_VOLUME_PATHS_16 = arrayOf(
        "M13.86 5.47a.75.75 0 0 0-1.061 0l-1.47 1.47-1.47-1.47A.75.75 0 0 0 8.8 6.53L10.269 8l-1.47 1.47a.75.75 0 1 0 1.06 1.06l1.47-1.47 1.47 1.47a.75.75 0 0 0 1.06-1.06L12.39 8l1.47-1.47a.75.75 0 0 0 0-1.06z",
        "M10.116 1.5A.75.75 0 0 0 8.991.85l-6.925 4a3.642 3.642 0 0 0-1.33 4.967 3.639 3.639 0 0 0 1.33 1.332l6.925 4a.75.75 0 0 0 1.125-.649v-1.906a4.73 4.73 0 0 1-1.5-.694v1.3L2.817 9.852a2.141 2.141 0 0 1-.781-2.92c.187-.324.456-.594.78-.782l5.8-3.35v1.3c.45-.313.956-.55 1.5-.694V1.5z",
    )

    object Images {
        const val GRID_IMAGE_1 = "/assets/grid_images/1.png"
        const val GRID_IMAGE_2 = "/assets/grid_images/2.png"
        const val GRID_IMAGE_3 = "/assets/grid_images/3.png"
        const val GRID_IMAGE_4 = "/assets/grid_images/4.png"
        const val GRID_IMAGE_5 = "/assets/grid_images/5.png"
        const val GRID_IMAGE_6 = "/assets/grid_images/6.jpg"
        const val GRID_IMAGE_7 = "/assets/grid_images/7.png"
        const val GRID_IMAGE_8 = "/assets/grid_images/8.png"
    }
}
