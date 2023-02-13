package app.eleven.bank.presentation.main_screen

sealed class AccountEvent {
	class OnLoadTransaction(val url: String) : AccountEvent()
}
