package miracast.android.to.tv

import android.content.Context
import android.content.SharedPreferences


object Utils {
    private val preferenceName : String="TvCastSharedPreference"


    public fun saveIntToStorage(context: Context, key: String, value: Int) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    public fun getIntFromStorage(context: Context, key: String, defaultValue: Int): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(key, defaultValue)
    }
}