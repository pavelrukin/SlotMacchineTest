package com.samsungmalex.slotmachinetest.data.data_store

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.samsungmalex.slotmachinetest.utils.Constants
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map


const val DataStore_NAME = "DataStore_STORE_MANAGER"

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = DataStore_NAME)

class DataStoreManger(private val context: Context) {

    companion object {
        val CREDIT_VALUE = intPreferencesKey("CREDIT_VALUE")
    }

    suspend fun saveCreditValuePreference(credit: Int?) {
        context.datastore.edit { preferences ->
            preferences[CREDIT_VALUE] = credit!!
        }
    }

    fun getCreditValuePreferences() = context.datastore.data.map { preferences ->
        preferences[CREDIT_VALUE] ?: Constants.CREDIT_VALUE
    }
    suspend fun getCreditValuePreferencesInt() = context.datastore.data.firstOrNull()?.get(CREDIT_VALUE)


    suspend fun clearCreditValuePreferences() {
        context.datastore.edit { preferences ->
            preferences[CREDIT_VALUE] = 0
        }
    }


}