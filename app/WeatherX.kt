import com.google.gson.annotations.SerializedName

data class WeatherX(
    @SerializedName("base")
    var base: String,
    @SerializedName("clouds")
    var clouds: CloudsX,
    @SerializedName("cod")
    var cod: Int,
    @SerializedName("coord")
    var coord: CoordX,
    @SerializedName("dt")
    var dt: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("main")
    var main: MainX,
    @SerializedName("name")
    var name: String,
    @SerializedName("sys")
    var sys: SysX,
    @SerializedName("timezone")
    var timezone: Int,
    @SerializedName("visibility")
    var visibility: Int,
    @SerializedName("weather")
    var weather: List<WeatherXX>,
    @SerializedName("wind")
    var wind: WindX
)