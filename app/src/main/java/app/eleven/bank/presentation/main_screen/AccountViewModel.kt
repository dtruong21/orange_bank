package app.eleven.bank.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.eleven.bank.common.Resource
import app.eleven.bank.domain.interactor.get_accounts.GetAccountsUC
import app.eleven.bank.domain.interactor.get_transactions.GetTransactionsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
	private val getAccountsUC: GetAccountsUC,
	private val getTransactionsUC: GetTransactionsUC
) : ViewModel() {

	private val _accountState = MutableStateFlow(AccountState())
	val accountState: StateFlow<AccountState> = _accountState

	private val _transactionState = MutableStateFlow(TransactionState())
	val transactionState: StateFlow<TransactionState> = _transactionState

	init {
		getAccounts()
	}

	private fun getAccounts() {
		getAccountsUC().onEach { resource ->
			when (resource) {
				is Resource.Error -> _accountState.value =
					AccountState(error = resource.message ?: "An unexpected error happened")
				is Resource.Loading -> _accountState.value = AccountState(isLoading = true)
				is Resource.Success -> _accountState.value =
					AccountState(accounts = resource.data ?: emptyList())
			}
		}.launchIn(viewModelScope)
	}

	private fun getTransactionsByAccount(url: String) {
		getTransactionsUC(url).onEach { resource ->
			when (resource) {
				is Resource.Error -> _transactionState.value =
					TransactionState(error = resource.message ?: "An unexpected error happened")
				is Resource.Loading -> _transactionState.value = TransactionState(isLoading = true)
				is Resource.Success -> _transactionState.value =
					TransactionState(transactions = resource.data ?: emptyList())
			}
		}.launchIn(viewModelScope)
	}

	fun onEventChanged(event: AccountEvent){
		when(event){
			is AccountEvent.OnLoadTransaction -> getTransactionsByAccount(event.url)
		}
	}
}