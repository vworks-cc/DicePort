package org.vworks.mirai.diceport.listener

import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.ListenerHost
import net.mamoe.mirai.event.events.MessageEvent
import org.vworks.mirai.diceport.config.StringTemplates
import org.vworks.mirai.diceport.service.DeckService
import org.vworks.mirai.diceport.service.JrrpService
import java.util.StringTokenizer

class MessageListener(val pluginBase: KotlinPlugin) : ListenerHost {
    @EventHandler
    suspend fun MessageEvent.onMessage() {
        if(message.serializeToMiraiCode()==".jrrp" || message.serializeToMiraiCode()=="。jrrp") {
            jrrpMessage()
        } else if (message.serializeToMiraiCode().startsWith(".draw") || message.serializeToMiraiCode().startsWith("。draw")) {
            drawMessage()
        }
    }

    suspend fun MessageEvent.jrrpMessage() {
        val rp = JrrpService.getJrrp(bot.id, sender.id)
        val message = StringTemplates.jrrpResponse.random()
            .replace("{{senderName}}", sender.nameCardOrNick)
            .replace("{{jrrpNumber}}", rp.toString())
        this.subject.sendMessage(message)
    }

    suspend fun MessageEvent.drawMessage() {
        val cmds = this.message.contentToString().split(Regex("\\s"))
        if(cmds.size == 1) {
            this.subject.sendMessage(StringTemplates.drawNoDeckError.random())
        }

        val deckName = cmds[1]
        val drawn = DeckService.drawCard(sender, deckName)
        if(drawn == null) {
            val message = StringTemplates.drawError.random()
                .replace("{{senderName}}", sender.nameCardOrNick)
                .replace("{{deckName}}", deckName)
            this.subject.sendMessage(message)
        } else {
            val message = StringTemplates.drawResponse.random()
                .replace("{{senderName}}", sender.nameCardOrNick)
                .replace("{{drawResult}}", drawn)
            this.subject.sendMessage(message)
        }
    }
}