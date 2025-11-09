package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.nzfacts.NewZealandFacts
import denys.diomaxius.newzealandguide.domain.repository.NewZealandFactsRepository

class NewZealandFactsRepositoryImpl : NewZealandFactsRepository {
    private val facts = listOf(
        "Aotearoa = “Land of the Long White Cloud”. The Māori name Aotearoa is often translated this way and is widely used alongside “New Zealand.”",
        "Two main islands + 700+ smaller islands. New Zealand’s main landmasses are the North and South Islands; it also includes hundreds of smaller islands.",
        "First self-governing country to give women the vote (1893). New Zealand led the world in national women’s suffrage.",
        "Te Reo Māori is an official language. The Māori language gained official status in 1987 and has been reviving ever since.",
        "Bats are the only native land mammals. Apart from marine mammals, New Zealand’s only native terrestrial mammals are bats — many other mammals arrived after humans.",
        "The kiwi is the national bird and cultural icon. This curious, flightless, nocturnal bird is an unofficial national symbol and very special in Māori culture.",
        "The kakāpō — a flightless, nocturnal parrot — is critically endangered. It’s one of the world’s rarest and most unusual birds.",
        "Tuatara aren’t lizards — they’re the last survivors of an ancient reptile lineage. Often called “living fossils,” tuatara belong to an order once widespread in Gondwana.",
        "Aoraki / Mount Cook is New Zealand’s highest mountain (3,724 m). It’s a major mountaineering and scenic landmark.",
        "Milford Sound / Fiordland is world-famous for dramatic fjords and waterfalls. One of the most photographed natural spots in NZ.",
        "Rotorua is a geothermal wonderland. Geysers, bubbling mud pools and hot springs are part of daily life there.",
        "Hector’s dolphin is one of the world’s smallest and is endemic to NZ waters. A unique coastal species found mainly around the South Island.",
        "New Zealand helped invent modern adventure tourism — commercial bungy started here. A.J. Hackett opened the first commercial bungy site in the late 1980s.",
        "The film industry is huge — LOTR & The Hobbit boosted NZ’s screen profile. Peter Jackson’s films put many NZ locations on the global map.",
        "Marlborough made New Zealand famous for Sauvignon Blanc. Marlborough is the country’s largest wine region and world-renowned for its Sauvignon Blanc.",
        "There are still more sheep than people (though the ratio has fallen). New Zealand’s flock historically outnumbered humans by a large margin; recent stats still show multiple sheep per person.",
        "A very large exclusive economic zone (EEZ). Because of its many islands, NZ’s maritime zone is far larger than its land area, giving vast marine resources.",
        "Unique plants: ancient kauri trees and widespread native ferns. Some kauri trees are thousands of years old and are culturally and ecologically important.",
        "Four seasons in one day — varied weather is normal. Thanks to its size and geography, weather can change quickly across short distances.",
        "The Treaty of Waitangi (1840) is foundational. This treaty between many Māori chiefs and the British Crown remains central to NZ law and society.",
        "Unique insects: giant wētā and many flightless species. Isolation produced unusual invertebrates, including very large wētā.",
        "Māori culture is alive and visible. Marae, carving, haka and traditional arts are an active part of national life across the country."
    )

    override fun getNewZealandFacts(): NewZealandFacts = NewZealandFacts(
        facts = facts
    )
}