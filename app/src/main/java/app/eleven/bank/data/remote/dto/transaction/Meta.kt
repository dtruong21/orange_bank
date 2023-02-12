package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class Meta(
    @Json(name = "FirstAvailableDateTime")
    val firstAvailableDateTime: String?,
    @Json(name = "LastAvailableDateTime")
    val lastAvailableDateTime: String?,
    @Json(name = "TotalPages")
    val totalPages: Int?
)