package app.eleven.bank

import app.eleven.bank.data.remote.dto.account.AccountDto
import app.eleven.bank.data.remote.dto.transaction.TransactionDto
import app.eleven.bank.domain.interactor.get_accounts.GetAccountsUC
import app.eleven.bank.domain.repository.BankRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AccountTest {

	private lateinit var repository: BankRepository
	private lateinit var getAccountsUC: GetAccountsUC

	@Before
	fun setup() {
		repository = mockk()
		getAccountsUC = GetAccountsUC(repository)
	}

	@Test
	fun `getAccounts should return a list of accounts`() {
		val expectedResult = emptyList<AccountDto>()

		coEvery { repository.getAccounts() } returns expectedResult

		runBlocking {
			val result = getAccountsUC().last().data

			Assert.assertEquals(result, expectedResult)
		}
	}



}