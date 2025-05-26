package sync2app.com.syncapplive.additionalSettings.USBCamera.CamModel


data class CamDisplaySettings(
    val start_height: String,
    val end_height: String,
    val start_width: String,
    val end_width: String,
    val display_time: String,
    val hide_time: String,
    ////  add more
    val usb_live_stream: Boolean = false,
    val stream_video: Boolean = false,
    val allowfloatandexpand: Boolean = false,
    val use_api: Boolean = false,
    val disable_audio: Boolean = false,
    val enable_window_display_interval: Boolean = false,
)

