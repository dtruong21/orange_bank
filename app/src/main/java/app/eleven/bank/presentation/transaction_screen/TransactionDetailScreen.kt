package app.eleven.bank.presentation.transaction_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.eleven.bank.R.drawable

@Composable
fun TransactionDetailScreen(
	navController: NavController,
	viewModel: TransactionDetailViewModel = hiltViewModel()
) {
	val state = viewModel.transaction.collectAsState()
	val title = remember {
		mutableStateOf("")
	}

	Scaffold(topBar = {
		TopAppBar(
			modifier = Modifier.fillMaxWidth(),
			backgroundColor = Color.White,
			elevation = 0.dp
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Column(modifier = Modifier.weight(1f)) {
					IconButton(onClick = {
						navController.popBackStack()
					}) {
						Icon(
							painterResource(id = drawable.baseline_arrow_back_24),
							contentDescription = null,
						)
					}
				}
				Column(
					modifier = Modifier.weight(1f),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(text = title.value, fontSize = 36.sp, fontWeight = FontWeight.Bold)
				}
				Spacer(modifier = Modifier.weight(1f))

			}
		}
	}) {
		Box(modifier = Modifier.padding(it)) {
			Column(modifier = Modifier.padding(10.dp)) {
				when {
					state.value.isLoading -> {}
					state.value.error.isNotEmpty() -> {}
					state.value.transaction != null -> {
						title.value = state.value.transaction!!.id
						Text(text = "Reference: ${state.value.transaction!!.ref}")
						Text(text = buildAnnotatedString {
							append("Amount: ${state.value.transaction!!.amount}")
							if (state.value.transaction!!.currency == "EUR") append("€")
						})
						Text(text = "Type transaction: ${state.value.transaction!!.type.name}")
						Text(text = "Information: ${state.value.transaction!!.information}")
						Text(text = "Date: ${state.value.transaction!!.date}")
					}
				}
			}

		}
	}


}
