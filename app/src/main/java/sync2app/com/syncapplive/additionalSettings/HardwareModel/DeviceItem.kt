package sync2app.com.syncapplive.additionalSettings.HardwareModel

import com.google.gson.annotations.SerializedName

data class DeviceItem(
    @SerializedName("deviceName") val deviceName: String,
    @SerializedName("model") val model: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("osVersion") val osVersion: String,
    @SerializedName("sdkVersion") val sdkVersion: String,
    @SerializedName("buildNumber") val buildNumber: String
)