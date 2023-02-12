package app.eleven.bank.framework.di

import app.eleven.bank.common.Constant
import app.eleven.bank.data.datasource.BankDataSource
import app.eleven.bank.data.remote.api.BankApi
import app.eleven.bank.domain.repository.BankRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BankModule {

	@Provides
	@Singleton
	fun provideBankApi(): BankApi = Retrofit.Builder()
		.baseUrl(Constant.BASE_URL)
		.addConverterFactory(
			MoshiConverterFactory.create(
				Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
			)
		)
		.addCallAdapterFactory(CoroutineCallAdapterFactory())
		.build()
		.create(BankApi::class.java)

	@Provides
	@Singleton
	fun provideBankRepository(api: BankApi): BankRepository = BankDataSource(api)
}