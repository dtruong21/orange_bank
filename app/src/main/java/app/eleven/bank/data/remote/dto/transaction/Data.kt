package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class Data(
    @Json(name = "Transaction")
    val transaction: List<TransactionDto>?
)