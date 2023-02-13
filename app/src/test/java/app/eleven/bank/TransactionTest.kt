package app.eleven.bank

import app.eleven.bank.data.remote.dto.transaction.TransactionDto
import app.eleven.bank.domain.interactor.get_transactions.GetTransactionsUC
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
class TransactionTest {

	private lateinit var repository: BankRepository
	private lateinit var getTransactionsUC: GetTransactionsUC

	@Before
	fun setup() {
		repository = mockk()
		getTransactionsUC = GetTransactionsUC(repository)
	}

	@Test
	fun `getTransactions should return nothing`() {
		val expectedResult = emptyList<TransactionDto>()

		coEvery { repository.getTransactionsByAccount("https://run.mocky.io/v3/6d154c00-ffc7-4624-b17b-83d65843a065") } returns expectedResult

		runBlocking {
			val result =
				getTransactionsUC("https://run.mocky.io/v3/6d154c00-ffc7-4624-b17b-83d65843a065").last().data

			Assert.assertEquals(result, expectedResult)
		}
	}
}