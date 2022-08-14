package org.vworks.mirai.diceport.data

import net.mamoe.mirai.contact.User

@kotlinx.serialization.Serializable
class EachProbabilityDeck : DeckInterface {
    lateinit var choices : List<String>
    override lateinit var type: String
    override lateinit var name: String

    override suspend fun generateResult(sender: User): String {
        return choices.random()
    }
}