package org.vworks.mirai.diceport.service

import net.mamoe.mirai.contact.User
import org.vworks.mirai.diceport.data.Decks

object DeckService {
    suspend fun drawCard(user: User, deckName: String): String? {
        return Decks.generate(deckName, user)
    }
}