package sync2app.com.syncapplive.additionalSettings.ApITVorAppMode

data class DomainTVModeSettings(
    val install_TV_mode: Boolean,
    val hide_TV_mode_label: Boolean,
    val full_Screen: Boolean,
    val hide_Full_Screen_Label: Boolean,
    val immersive_Mode: Boolean,
    val hide_Immersive_Mode_Label: Boolean,
    val hide_Bottom_Bar: Boolean,
    val hide_Bottom_Bar_Label: Boolean,
    val hide_Bottom_Menu_Icon: Boolean,
    val hide_Bottom_Menu_Icon_Label: Boolean,
    val hide_Floating_Button: Boolean,
    val hide_Floating_Button_Label: Boolean,

    /// newly added
    val use_local_schedule: Boolean,
    val show_local_schedule_label: Boolean
)

data class ApiResponse(
    val InstallAppSettings: DomainTVModeSettings
)
