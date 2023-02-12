package app.eleven.bank.data.remote.dto.transaction


import com.squareup.moshi.Json

data class TransactionDto(
	@Json(name = "Amount")
    val amount: AmountDto?,
	@Json(name = "BankTransactionCode")
    val bankTransactionCode: BankTransactionCodeDto?,
	@Json(name = "CreditDebitIndicator")
    val creditDebitIndicator: String?,
	@Json(name = "ProprietaryBankTransactionCode")
    val proprietaryBankTransactionCode: ProprietaryBankTransactionCode?,
	@Json(name = "Status")
    val status: String?,
	@Json(name = "TransactionId")
    val transactionId: String?,
	@Json(name = "TransactionInformation")
    val transactionInformation: String?,
	@Json(name = "TransactionReference")
    val transactionReference: String?,
	@Json(name = "ValueDateTime")
    val valueDateTime: String?
)