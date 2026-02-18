package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.maoriwords.MaoriWords
import denys.diomaxius.newzealandguide.domain.repository.MaoriWordsRepository

class MaoriWordsRepositoryImpl(
) : MaoriWordsRepository {
    private val words = mapOf(
        "tama" to "son, young man, youth",
        "Aotearoa" to "New Zealand",
        "aroha" to "love",
        "awa" to "river",
        "haka" to "generic term for Māori dance",
        "hāngī" to "traditional feast prepared in an earth oven",
        "hapū" to "kinship group, sub-tribe; pregnant",
        "hui" to "gathering, meeting",
        "hīkoi" to "walk, march",
        "iti" to "small",
        "iwi" to "tribe",
        "kai" to "food",
        "kaikōrero" to "speaker, orator",
        "karakia" to "prayer, incantation",
        "kaumātua" to "elder",
        "kauri" to "large native conifer",
        "koha" to "gift, present",
        "mahi" to "work, activity",
        "mana" to "prestige, reputation, authority",
        "manuhiri" to "guests, visitors",
        "maunga" to "mountain",
        "moana" to "sea",
        "motu" to "island",
        "nui" to "large, many, big",
        "taihoa" to "to delay, to wait, to hold off",
        "tamariki" to "children",
        "tamāhine" to "daughter",
        "tāne" to "man, husband",
        "wahine" to "woman, wife",
        "wai" to "water",
        "waiata" to "song or chant"
    )

    override fun getMaoriWords(): MaoriWords =
        MaoriWords(
            words = words
        )
}