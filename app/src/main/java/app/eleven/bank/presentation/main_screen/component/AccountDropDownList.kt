package app.eleven.bank.presentation.main_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.eleven.bank.R.drawable
import app.eleven.bank.domain.model.Account

@Composable
fun AccountDropDownList(accounts: List<Account>, onLoadTransaction: (url: String) -> Unit) {
	val expanded = remember {
		mutableStateOf(false)
	}
	val selectedIndex = remember { mutableStateOf(0) }
	onLoadTransaction(accounts[selectedIndex.value].url)
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.padding(4.dp),
		contentAlignment = Alignment.Center
	) {
		Column(modifier = Modifier.width(300.dp)) {
			Row(modifier = Modifier.clickable { expanded.value = true }) {
				Text(
					text = accounts[selectedIndex.value].nickname,
					modifier = Modifier.weight(1f)
				)
				Icon(
					painter = painterResource(id = drawable.baseline_arrow_drop_down_circle_24),
					contentDescription = "",
					modifier = Modifier.weight(1f)
				)
			}

			DropdownMenu(
				expanded = expanded.value,
				onDismissRequest = { expanded.value = false },
				modifier = Modifier.fillMaxWidth()
			) {
				accounts.forEachIndexed { index, account ->
					DropdownMenuItem(onClick = {
						selectedIndex.value = index
						expanded.value = false
						onLoadTransaction(account.url)
					}) {
						Text(text = account.nickname)
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountDropDownList() = AccountDropDownList(listOf(Account("", "Saving account", ""))) {

}
