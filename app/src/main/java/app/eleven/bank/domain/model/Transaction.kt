package app.eleven.bank.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
	val id: String,
	val ref: String,
	val amount: String,
	val currency: String,
	val type: TransactionType,
	val status: TransactionStatus,
	val information: String,
	val date: String,
) : Parcelable

enum class TransactionType {
	CREDIT, DEBIT
}

enum class TransactionStatus {
	BOOKED, CANCELLED
}
