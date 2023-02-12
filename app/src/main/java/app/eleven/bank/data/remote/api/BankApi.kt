package app.eleven.bank.data.remote.api

import app.eleven.bank.data.remote.dto.account.AccountResponse
import app.eleven.bank.data.remote.dto.transaction.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface BankApi {

	@GET("ea42529b-1a24-4f3e-9ba4-8e6665666d6b")
	suspend fun getAccounts(): AccountResponse

	@GET
	suspend fun getTransactions(@Url url: String): TransactionResponse
}