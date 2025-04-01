package com.abhiram.databeatassessment.core.di

import com.abhiram.databeatassessment.core.util.Endpoint
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.plugin
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpClientModule = module {
    single {
        HttpClient {
            defaultRequest {
                url(Endpoint.BASE_URL)
                contentType(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000
                socketTimeoutMillis = 60_000
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = ""
                        BearerTokens(
                            accessToken = token,
                            refreshToken = null
                        )
                    }
                }
            }
        }.apply {
            plugin(HttpSend).intercept { req ->
                val token = ""
                val modifiedRequest = req.apply {
                    headers[HttpHeaders.Authorization] = "Bearer $token"
                }
                execute(modifiedRequest)
            }
        }
    }
}