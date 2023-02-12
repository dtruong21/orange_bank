package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class AmountDto(
    @Json(name = "Amount")
    val amount: String?,
    @Json(name = "Currency")
    val currency: String?
)