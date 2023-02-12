package app.eleven.bank.data.remote.dto.account


import com.squareup.moshi.Json

data class AccountResponse(
    @Json(name = "Data")
    val item: Data?,
    @Json(name = "Meta")
    val meta: Meta?
)