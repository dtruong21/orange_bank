package app.eleven.bank.presentation.transaction_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.eleven.bank.domain.model.Transaction
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
	ViewModel() {

	private val _transaction = MutableStateFlow(TransactionState())
	val transaction: StateFlow<TransactionState> = _transaction

	private val transactionJson: String = checkNotNull(savedStateHandle["transaction"])

	init {
		getTransaction()
	}

	private fun getTransaction() {
		Timber.e("transaction: $transactionJson")
		viewModelScope.launch {
			val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
			val jsonAdapter = moshi.adapter(Transaction::class.java).lenient()
			val transactionObject = jsonAdapter.fromJson(transactionJson)
			_transaction.value = TransactionState(transaction = transactionObject)
		}
	}
}