package org.vworks.mirai.diceport.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig

object Config : AutoSavePluginConfig("config") {
    val jrrpEndpoint : String = "http://api.kokona.tech:5555/jrrp"
}