package app.eleven.bank.data.remote.dto.account


import com.squareup.moshi.Json

data class Meta(
    @Json(name = "TotalPages")
    val totalPages: Int?
)