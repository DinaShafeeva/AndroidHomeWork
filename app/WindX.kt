import com.google.gson.annotations.SerializedName

data class WindX(
    @SerializedName("deg")
    var deg: Int,
    @SerializedName("gust")
    var gust: Int,
    @SerializedName("speed")
    var speed: Int
)