package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class ProprietaryBankTransactionCode(
    @Json(name = "Code")
    val code: String?,
    @Json(name = "Issuer")
    val issuer: String?
)