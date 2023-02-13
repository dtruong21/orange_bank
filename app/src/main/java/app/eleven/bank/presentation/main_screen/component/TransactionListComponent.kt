package app.eleven.bank.presentation.main_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.eleven.bank.domain.model.Transaction
import app.eleven.bank.domain.model.TransactionType

@Composable
fun TransactionList(
	transactions: List<Transaction>,
	onOpenDetailTransaction: (transaction: Transaction) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
	) {
		Text(text = "Crédit")
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(10.dp)
		)
		val credits =
			transactions.filter { transaction -> transaction.type == TransactionType.CREDIT }
		if (credits.isEmpty()) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(4.dp)
					.align(Alignment.CenterHorizontally)
			) {
				Text(text = "No transaction available")
			}
		} else {
			credits.forEach { item ->
				TransactionItem(transaction = item) {
					onOpenDetailTransaction(it)
				}
			}
		}

		val debits =
			transactions.filter { transaction -> transaction.type == TransactionType.DEBIT }

		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(10.dp)
		)
		Text(text = "Débit")
		if (debits.isEmpty()) {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(4.dp)
					.align(Alignment.CenterHorizontally)
			) {
				Text(text = "No transaction available")
			}
		} else {
			debits.forEach { item ->
				TransactionItem(transaction = item) {
					onOpenDetailTransaction(it)
				}
			}
		}

		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(10.dp)
		)
	}

}