package app.eleven.bank.common

abstract class Mapper<I, O> {

	abstract fun map(obj: I): O

	fun map(collection: Collection<I>): List<O> = ArrayList(collection.map { map(it) })
}