package app.eleven.bank.presentation.main_screen

import app.eleven.bank.domain.model.Account

data class AccountState(
	val isLoading: Boolean = false,
	val accounts: List<Account> = emptyList(),
	val error: String = ""
)
