package org.vworks.mirai.diceport.data

import net.mamoe.mirai.console.data.AutoSavePluginData

object CustomNames : AutoSavePluginData("custom-names") {
    val altNames: MutableMap<Long, String> = HashMap<Long, String>()
}