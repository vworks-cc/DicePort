package org.vworks.mirai.diceport.data

import net.mamoe.mirai.contact.User

interface DeckInterface {
    var type: String
    var name: String
    suspend fun generateResult(sender: User): String = ""
}