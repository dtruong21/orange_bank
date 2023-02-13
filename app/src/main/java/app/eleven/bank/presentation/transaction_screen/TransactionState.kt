package app.eleven.bank.presentation.transaction_screen

import app.eleven.bank.domain.model.Transaction

data class TransactionState(
	val isLoading: Boolean = false,
	val transaction: Transaction? = null,
	val error: String = ""
)
