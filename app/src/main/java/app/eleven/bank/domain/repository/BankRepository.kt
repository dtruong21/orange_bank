package app.eleven.bank.domain.repository

import app.eleven.bank.data.remote.dto.account.AccountDto
import app.eleven.bank.data.remote.dto.transaction.TransactionDto

interface BankRepository {
	suspend fun getAccounts(): List<AccountDto>
	suspend fun getTransactionsByAccount(url: String): List<TransactionDto>
}