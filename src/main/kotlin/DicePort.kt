package org.vworks.mirai.diceport

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.utils.info
import org.vworks.mirai.diceport.config.Config
import org.vworks.mirai.diceport.config.StringTemplates
import org.vworks.mirai.diceport.data.CustomNames
import org.vworks.mirai.diceport.data.Decks
import org.vworks.mirai.diceport.listener.MessageListener

object DicePort : KotlinPlugin(
    JvmPluginDescription(
        id = "org.vworks.mirai.diceport",
        name = "DicePort",
        version = "0.1.0",
    ) {
        author("MetricVoid")
    }
) {
    override fun onEnable() {
        Config.reload()
        StringTemplates.reload()
        CustomNames.reload()
        Decks.load(this)
        logger.info { "Plugin loaded" }

        val msgListener = MessageListener(this)
        GlobalEventChannel.registerListenerHost(msgListener)
    }
}