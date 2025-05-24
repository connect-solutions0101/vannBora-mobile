package com.example.mobilevan.ui.screens.splash

import android.content.Context
import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel : ViewModel() {

    var isTokenValid by mutableStateOf<Boolean?>(null)
        private set

    fun onScreenLoad(context: Context) {
        viewModelScope.launch {
            val token = TokenStore.getToken(context).firstOrNull()
            isTokenValid = !token.isNullOrEmpty() && !isTokenExpired(token)
        }
    }

    private fun isTokenExpired(token: String): Boolean {
        return try {
            val parts = token.split(".")
            if (parts.size != 3) return true

            val payload = String(Base64.decode(parts[1], Base64.DEFAULT))
            val json = JSONObject(payload)
            val exp = json.getLong("exp")
            val now = System.currentTimeMillis() / 1000
            now > exp
        } catch (e: Exception) {
            true
        }
    }
}