package app.eleven.bank.data.remote.dto.account


import com.squareup.moshi.Json

data class AccountDto(
    @Json(name = "Account")
    val account: List<AccountInformationDto>?,
    @Json(name = "AccountId")
    val accountId: String?,
    @Json(name = "AccountSubType")
    val accountSubType: String?,
    @Json(name = "AccountType")
    val accountType: String?,
    @Json(name = "Currency")
    val currency: String?,
    @Json(name = "Nickname")
    val nickname: String?,
    @Json(name = "OpeningDate")
    val openingDate: String?,
    @Json(name = "Status")
    val status: String?,
    @Json(name = "StatusUpdateDateTime")
    val statusUpdateDateTime: String?,
    @Json(name = "transactionsUrl")
    val transactionsUrl: String?
)