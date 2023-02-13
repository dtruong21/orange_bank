package app.eleven.bank.presentation.main_screen

import app.eleven.bank.domain.model.Transaction

data class TransactionState(
	val isLoading: Boolean = false,
	val transactions: List<Transaction> = emptyList(),
	val error: String = ""
)
