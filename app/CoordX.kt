import com.google.gson.annotations.SerializedName

data class CoordX(
    @SerializedName("lat")
    var lat: Double,
    @SerializedName("lon")
    var lon: Double
)