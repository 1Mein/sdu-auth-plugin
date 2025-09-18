package org.editz.sduAuth.services

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class SimpleCookieJar : CookieJar {
    private val cookieStore = mutableMapOf<String, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url.host] ?: emptyList()
    }
}

class AuthService {
    private val baseUrl = "https://my.sdu.edu.kz/loginAuth.php"

    fun serverLogin(username: String, password: String): Boolean {
        val client = OkHttpClient.Builder()
            .cookieJar(SimpleCookieJar())
            .build()

        val formBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .add("modstring", "")
            .add("LogIn", "Log In")
            .build()

        val request = Request.Builder()
            .url(baseUrl)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
            .header("Referer", "https://my.sdu.edu.kz/index.php")
            .post(formBody)
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return false
                val body = response.body?.string() ?: ""
                !body.contains("Log in", ignoreCase = true)
            }
        } catch (e: IOException) {
            println("Ошибка: ${e.message}")
            false
        }
    }
}
