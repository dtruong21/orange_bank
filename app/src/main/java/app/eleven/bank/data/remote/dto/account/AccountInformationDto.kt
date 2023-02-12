package app.eleven.bank.data.remote.dto.account


import com.squareup.moshi.Json

data class AccountInformationDto(
    @Json(name = "Identification")
    val identification: String?,
    @Json(name = "Name")
    val name: String?,
    @Json(name = "SchemeName")
    val schemeName: String?,
    @Json(name = "SecondaryIdentification")
    val secondaryIdentification: String?
)