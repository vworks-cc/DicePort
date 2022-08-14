package org.vworks.mirai.diceport.data

import net.mamoe.mirai.contact.User

@kotlinx.serialization.Serializable
class EqualChanceDeck : DeckInterface {
    override lateinit var type: String
    override lateinit var name: String
    lateinit var choices : List<String>

    override suspend fun generateResult(sender: User): String {
        return choices.random()
    }
}