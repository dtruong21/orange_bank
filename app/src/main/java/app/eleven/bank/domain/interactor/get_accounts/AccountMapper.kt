package app.eleven.bank.domain.interactor.get_accounts

import app.eleven.bank.common.Mapper
import app.eleven.bank.data.remote.dto.account.AccountDto
import app.eleven.bank.domain.model.Account

class AccountMapper : Mapper<AccountDto, Account>() {
	override fun map(obj: AccountDto): Account = Account(
		nickname = obj.nickname!!,
		information = obj.account!![0].name ?: "Accounts",
		url = obj.transactionsUrl!!
	)
}