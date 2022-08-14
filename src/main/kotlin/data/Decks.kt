package org.vworks.mirai.diceport.data

import kotlinx.serialization.decodeFromString
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.User
import net.mamoe.yamlkt.Yaml
import org.vworks.mirai.diceport.DicePort.logger
import java.io.File

object Decks {

    var pluginBase: KotlinPlugin? = null

    val decks = HashMap<String, DeckInterface>()

    private val supportedDeckTypes = mapOf(
        "EqualChance" to EqualChanceDeck::class,
        "EachProbability" to EachProbabilityDeck::class
    )

    fun load(plugin: KotlinPlugin) {
        this.pluginBase = plugin
        loadDecks()
    }

    fun loadDecks() {
        if(pluginBase == null) return
        val deckDir = File(this.pluginBase!!.dataFolder.path + File.separator + "decks")

        if(deckDir.exists() and deckDir.isDirectory) {
            val files = deckDir.listFiles { n, s -> s.endsWith(".yml") }
            files?.forEach {
                logger.info("Reading File ${it.name}")
                loadSingleDeck(it, it.readText())
            }
        } else if (!deckDir.exists()) {
            deckDir.mkdirs()
        }
    }

    fun loadSingleDeck(file: File, fileContent: String) {
        val raw = Yaml.decodeYamlMapFromString(fileContent)

        val deckType = raw["type"]?.toString()
        if(deckType == null) {
            logger.warning("File ${file.name} does not have a compatible Deck Type. Ignored.")
        } else {
            try {
                when(deckType) {
                    "EqualChance" -> {
                        val equiChanceDeck: EqualChanceDeck = Yaml.decodeFromString(fileContent)
                        this.decks[equiChanceDeck.name] = equiChanceDeck
                    }
                    "EachProbability" -> {
                        val eachProbabilityDeck: EachProbabilityDeck = Yaml.decodeFromString(fileContent)
                        this.decks[eachProbabilityDeck.name] = eachProbabilityDeck
                    }
                    else -> {
                        logger.warning("File ${file.name} contains unsupported Deck type ${deckType}. Ignored.")
                    }
                }
            } catch (e: java.lang.Exception) {
                logger.warning("Error parsing file ${file.name}. Ignored.")
                e.printStackTrace()
            }
        }
    }

    fun hasDeck(name: String): Boolean = decks.containsKey(name)

    suspend fun generate(deckName: String, user: User) = decks[deckName]?.generateResult(user)
}