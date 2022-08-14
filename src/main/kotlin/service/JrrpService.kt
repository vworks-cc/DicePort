package org.vworks.mirai.diceport.service

import io.ktor.client.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.vworks.mirai.diceport.config.Config

object JrrpService {
    val client = HttpClient()

    suspend fun getJrrp(botId: Long, userId: Long): Int {
        val response: HttpStatement = client.submitForm(
            url = Config.jrrpEndpoint,
            formParameters = Parameters.build {
                append("QQ", botId.toString())
                append("v", "20190114")
                append("QueryQQ", userId.toString())
            }
        )
        return response.execute().readText().toInt()
    }
}