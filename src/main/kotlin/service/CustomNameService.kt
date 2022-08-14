package org.vworks.mirai.diceport.service

import net.mamoe.mirai.contact.User
import net.mamoe.mirai.contact.nameCardOrNick
import org.vworks.mirai.diceport.data.CustomNames

object CustomNameService {
    fun setName(id: Long, name: String) {
        CustomNames.altNames.set(id, name)
    }

    fun getCustomName(user: User): String = CustomNames.altNames[user.id] ?: user.nameCardOrNick
}