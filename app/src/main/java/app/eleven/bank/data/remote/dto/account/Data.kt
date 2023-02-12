package app.eleven.bank.data.remote.dto.account


import com.squareup.moshi.Json

data class Data(
    @Json(name = "Account")
    val account: List<AccountDto>?
)