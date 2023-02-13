package app.eleven.bank.domain.interactor.get_transactions

import app.eleven.bank.common.Resource
import app.eleven.bank.domain.model.Transaction
import app.eleven.bank.domain.model.TransactionStatus
import app.eleven.bank.domain.model.TransactionType
import app.eleven.bank.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTransactionsUC @Inject constructor(private val repository: BankRepository) {
	operator fun invoke(url: String): Flow<Resource<List<Transaction>>> = flow {
		try {
			emit(Resource.Loading())
			val response = repository.getTransactionsByAccount(url).map { transaction ->
				TransactionMapper().map(transaction)
			}.filter { transaction -> transaction.status == TransactionStatus.BOOKED }
			val listCredit =
				response.filter { transaction -> transaction.type == TransactionType.CREDIT }
					.sortedByDescending { transaction -> transaction.date }.take(2)
			val listDebit =
				response.filter { transaction -> transaction.type == TransactionType.DEBIT }
					.sortedByDescending { transaction -> transaction.date }.take(2)
			emit(Resource.Success(listCredit + listDebit))
		} catch (e: HttpException) {
			emit(
				Resource.Error(
					e.localizedMessage ?: "An unexpected error happened with ${e.code()}"
				)
			)
		} catch (e: IOException) {
			emit(Resource.Error("Couldn't reach server. Check your internet connection."))
		}
	}
}