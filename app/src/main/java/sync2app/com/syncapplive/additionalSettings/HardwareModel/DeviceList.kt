package sync2app.com.syncapplive.additionalSettings.HardwareModel

import com.google.gson.annotations.SerializedName

data class DeviceList(
    @SerializedName("devices") val devices: List<DeviceItem>
)
