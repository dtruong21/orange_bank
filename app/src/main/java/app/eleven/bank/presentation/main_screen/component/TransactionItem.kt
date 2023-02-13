package app.eleven.bank.presentation.main_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.eleven.bank.domain.model.Transaction
import app.eleven.bank.domain.model.TransactionStatus
import app.eleven.bank.domain.model.TransactionType

@Composable
fun TransactionItem(transaction: Transaction, onClickDetail: (transaction: Transaction) -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp)
			.clickable {
				onClickDetail(transaction)
			}
	) {
		Column(horizontalAlignment = Alignment.Start) {
			Text(text = transaction.date)
		}
		Spacer(modifier = Modifier.weight(1f))
		Column(horizontalAlignment = Alignment.End) {
			Text(
				text = buildAnnotatedString {
					append(transaction.amount)
					if (transaction.currency == "EUR") append("€")
				},
				color = if (transaction.type == TransactionType.CREDIT) Color.Green else Color.Red
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionItem() = TransactionItem(
	Transaction(
		"",
		"",
		"12.5",
		"EUR",
		TransactionType.CREDIT,
		TransactionStatus.BOOKED,
		"",
		"11/05/2022 12:55:14"
	)
) {

}