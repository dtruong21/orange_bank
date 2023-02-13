package app.eleven.bank.domain.interactor.get_accounts

import app.eleven.bank.common.Resource
import app.eleven.bank.domain.model.Account
import app.eleven.bank.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAccountsUC @Inject constructor(private val repository: BankRepository) {

	operator fun invoke(): Flow<Resource<List<Account>>> = flow {
		try {
			emit(Resource.Loading())
			val response = repository.getAccounts().map { account ->
				AccountMapper().map(account)
			}
			emit(Resource.Success(response))
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