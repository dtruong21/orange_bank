package app.eleven.bank.domain.model

data class Transaction(
	val id: String,
	val ref: String,
	val amount: String,
	val currency: String,
	val type: TransactionType,
	val status: TransactionStatus,
	val information: String,
	val date: String,
)

enum class TransactionType {
	CREDIT, DEBIT
}

enum class TransactionStatus {
	BOOKED, CANCELLED
}
