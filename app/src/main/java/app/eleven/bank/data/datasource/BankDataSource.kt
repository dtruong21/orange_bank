package app.eleven.bank.data.datasource

import app.eleven.bank.data.remote.api.BankApi
import app.eleven.bank.data.remote.dto.account.AccountDto
import app.eleven.bank.data.remote.dto.transaction.TransactionDto
import app.eleven.bank.domain.repository.BankRepository
import javax.inject.Inject

class BankDataSource @Inject constructor(private val api: BankApi) : BankRepository {
	override suspend fun getAccounts(): List<AccountDto> = api.getAccounts().item?.account ?: listOf()

	override suspend fun getTransactionsByAccount(url: String): List<TransactionDto> =
		api.getTransactions(url).item?.transaction ?: listOf()
}