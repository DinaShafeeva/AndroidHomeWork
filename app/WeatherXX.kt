import com.google.gson.annotations.SerializedName

data class WeatherXX(
    @SerializedName("description")
    var description: String,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: String
)