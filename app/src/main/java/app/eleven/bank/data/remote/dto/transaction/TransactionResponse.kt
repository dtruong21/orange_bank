package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class TransactionResponse(
    @Json(name = "Data")
    val item: Data?,
    @Json(name = "Meta")
    val meta: Meta?
)