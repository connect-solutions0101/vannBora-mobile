package com.example.mobilevan.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.tokenStore by preferencesDataStore(name = "token_store")

object TokenStore {

    private val TOKEN_KEY = stringPreferencesKey("JWT_TOKEN")
    private val USER_ID = intPreferencesKey("USER_ID")
    private val USER_NAME = stringPreferencesKey("USER_NAME")

    suspend fun saveToken(context: Context, token: String, userId: Int, userName: String) {
        context.tokenStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_ID] = userId
            preferences[USER_NAME] = userName
        }
    }

    suspend fun clear(context: Context) {
        context.tokenStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun getToken(context: Context): Flow<String?> {
        return context.tokenStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    fun getUserId(context: Context): Flow<Int?> {
        return context.tokenStore.data.map { preferences ->
            preferences[USER_ID]
        }
    }

    fun getUserName(context: Context): Flow<String?> {
        return context.tokenStore.data.map { preferences ->
            preferences[USER_NAME]
        }
    }
}