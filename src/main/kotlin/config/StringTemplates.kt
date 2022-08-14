package org.vworks.mirai.diceport.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig

object StringTemplates : AutoSavePluginConfig("strings") {
    val jrrpResponse : List<String> = arrayListOf(
        "{{senderName}}今天的人品是{{jrrpNumber}}哦！",
        "{{senderName}}今天的幸运大概是{{jrrpNumber}}这么多"
    )

    val drawResponse : List<String> = arrayListOf(
        "来看看{{senderName}}抽到了什么：\n{{drawResult}}"
    )

    val drawError : List<String> = arrayListOf(
        "没有叫做{{deckName}}的牌堆哦！"
    )

    val drawNoDeckError: List<String> = arrayListOf(
        "请指定牌堆名！"
    )
}