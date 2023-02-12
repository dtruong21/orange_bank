package app.eleven.bank.domain.interactor.getTransations

import app.eleven.bank.common.Mapper
import app.eleven.bank.common.dateStringToMillis
import app.eleven.bank.common.formatDate
import app.eleven.bank.data.remote.dto.transaction.TransactionDto
import app.eleven.bank.domain.model.Transaction
import app.eleven.bank.domain.model.TransactionStatus
import app.eleven.bank.domain.model.TransactionType

class TransactionMapper : Mapper<TransactionDto, Transaction>() {
	override fun map(obj: TransactionDto): Transaction =
		Transaction(
			id = obj.transactionId ?: "",
			ref = obj.transactionReference ?: "",
			amount = obj.amount?.amount ?: "",
			currency = obj.amount?.currency ?: "EUR",
			type = if (obj.creditDebitIndicator == "Credit") TransactionType.CREDIT else TransactionType.DEBIT,
			status = if (obj.status == "Booked") TransactionStatus.BOOKED else TransactionStatus.CANCELLED,
			information = obj.transactionInformation ?: "",
			date = formatDate(dateStringToMillis(dateString = obj.valueDateTime!!))!!
		)
}