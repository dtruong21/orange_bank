package app.eleven.bank.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import app.eleven.bank.presentation.main_screen.AccountScreen
import app.eleven.bank.presentation.transaction_screen.TransactionDetailScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

sealed class Screen(val route: String) {
	object MainScreen : Screen("main")
	object TransactionScreen : Screen("transaction")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController, padding: PaddingValues) {
	AnimatedNavHost(
		navController = navController,
		startDestination = Screen.MainScreen.route,
		Modifier.padding(padding)
	) {
		composable(Screen.MainScreen.route) {
			AccountScreen(navController)
		}
		composable(
			Screen.TransactionScreen.route + "/{transaction}",
			arguments = listOf(navArgument("transaction") { type = NavType.StringType })
		) {
			TransactionDetailScreen(navController = navController)
		}
	}
}