package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class BankTransactionCodeDto(
    @Json(name = "Code")
    val code: String?,
    @Json(name = "SubCode")
    val subCode: String?
)