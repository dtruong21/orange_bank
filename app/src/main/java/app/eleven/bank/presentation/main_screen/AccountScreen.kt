package app.eleven.bank.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.eleven.bank.domain.model.Transaction
import app.eleven.bank.presentation.Screen
import app.eleven.bank.presentation.main_screen.component.AccountDropDownList
import app.eleven.bank.presentation.main_screen.component.TransactionList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay

@Composable
fun AccountScreen(
	navController: NavController,
	viewModel: AccountViewModel = hiltViewModel()
) {
	AccountScreenContent(
		navController = navController,
		viewModel.accountState.collectAsState().value,
		viewModel.transactionState.collectAsState().value,
		onLoadTransaction = { url ->
			viewModel.onEventChanged(AccountEvent.OnLoadTransaction(url))
		}
	)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountScreenContent(
	navController: NavController,
	accountState: AccountState,
	transactionState: TransactionState,
	onLoadTransaction: (url: String) -> Unit,
) {

	val urlTransaction = remember {
		mutableStateOf("")
	}
	val title = remember {
		mutableStateOf("Accounts")
	}
	val pullRefreshState =
		rememberPullRefreshState(
			refreshing = transactionState.isLoading,
			onRefresh = { onLoadTransaction(urlTransaction.value) })

	var refreshing by remember { mutableStateOf(false) }
	LaunchedEffect(refreshing) {
		if (refreshing) {
			delay(2000)
			refreshing = false
		}
	}

	Scaffold(topBar = {
		TopAppBar(
			modifier = Modifier.fillMaxWidth(),
			backgroundColor = Color.White,
			elevation = 0.dp
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				Text(text = title.value, fontSize = 36.sp, fontWeight = FontWeight.Bold)
			}
		}
	}) {

		SwipeRefresh(
			state = rememberSwipeRefreshState(isRefreshing = refreshing),
			onRefresh = {
				refreshing = true
				onLoadTransaction(urlTransaction.value)
			}) {
			Column(
				modifier = Modifier
					.padding(it)
					.verticalScroll(rememberScrollState())
			) {
				when {
					accountState.isLoading -> {
					}
					accountState.error.isNotEmpty() -> {}
					accountState.accounts.isNotEmpty() -> {
						Spacer(modifier = Modifier.height(20.dp))
						title.value = accountState.accounts[0].information
						AccountDropDownList(
							accounts = accountState.accounts,
							onLoadTransaction = { url ->
								urlTransaction.value = url
								onLoadTransaction(url)
							})
						Spacer(modifier = Modifier.height(20.dp))
					}
				}
				when {
					transactionState.isLoading -> {
					}
					transactionState.error.isNotEmpty() -> {
						Box(
							modifier = Modifier.fillMaxSize(),
							contentAlignment = Alignment.Center
						) {
							Text(text = "There is something not right. Please try again later")
						}
					}
					transactionState.transactions.isNotEmpty() -> {
						TransactionList(transactions = transactionState.transactions) {
							val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
							val jsonAdapter = moshi.adapter(Transaction::class.java).lenient()
							val transactionJson = jsonAdapter.toJson(it)

							navController.navigate(Screen.TransactionScreen.route + "/$transactionJson")
						}
					}
					else -> {
						Box(
							modifier = Modifier.fillMaxSize(),
							contentAlignment = Alignment.Center
						) {
							Text(text = "No transaction available")
						}
					}
				}

			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() =
	AccountScreenContent(
		rememberNavController(),
		AccountState(),
		TransactionState(),
		onLoadTransaction = {})