package app.eleven.bank.common

import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_LIVE = "yyyy-MM-dd'T'HH:mm:ssZ"
const val FORMAT_DATE_BANK = "dd-MM-yyyy HH:mm:ss";

fun dateStringToMillis(format: String = DATE_FORMAT_LIVE, dateString: String): Long {
	val sdf = SimpleDateFormat(format, Locale.getDefault())
	try {
		val mDate = sdf.parse(dateString)
		if (mDate != null) {
			return mDate.time
		}
	} catch (e: ParseException) {
		Timber.e(e.message, e)
	}
	return 0
}

fun formatDate(dateInMillis: Long, dateFormat: String = FORMAT_DATE_BANK): String? {
	val convertDate = Date(dateInMillis)
	val dateFormatOutput = SimpleDateFormat(dateFormat, Locale.FRANCE)
	return dateFormatOutput.format(convertDate)
}