package denys.diomaxius.newzealandguide.data.repository

import denys.diomaxius.newzealandguide.domain.model.nzhistory.NewZealandHistory
import denys.diomaxius.newzealandguide.domain.repository.NewZealandHistoryRepository

class NewZealandHistoryRepositoryImpl() : NewZealandHistoryRepository {
    private val paragraphs = listOf(
        "New Zealand — known in Māori as Aotearoa (often translated as “land of the long white cloud”) — has a relatively recent human history shaped by two great waves: the arrival and flourishing of Māori culture, and the later arrival of Europeans which brought deep social, political and economic change. Today it’s a bilingual, multicultural nation with a strong connection to its indigenous heritage and to the natural world.",
        "Polynesian navigators reached New Zealand in oceangoing canoes around the late 1200s–early 1300s. These settlers — the ancestors of today’s Māori — adapted to New Zealand’s climate and environment, developing distinctive iwi (tribes), hapū (sub-tribes) and rich traditions in carving, weaving, song and oral history. Māori social life centered on marae (meeting places), fortified pā sites, kinship, and a deep relationship with land (whenua), sea and whakapapa (ancestry).",
        "European explorers and visitors began arriving in the 17th–18th centuries. Dutch navigator Abel Tasman sighted parts of the islands in 1642; later, from the late 1700s, British and French ships, whalers, sealers, missionaries and traders increased contact. These early exchanges brought new goods, technologies and ideas — and, sadly, new diseases and tensions.",
        "In 1840 many Māori rangatira (chiefs) and representatives of the British Crown signed the Treaty of Waitangi (Te Tiriti o Waitangi). The treaty is widely regarded as the founding document of modern New Zealand, though differences between the English and Māori versions have caused long-term debate about meaning and rights. The Treaty established British governance while promising to protect Māori interests and lands; its legacy remains central to New Zealand’s politics and law.",
        "Following the Treaty, the mid-1800s saw increasing European settlement, land sales (often contested), and the growth of towns and agriculture. Conflicts over land and authority led to wars in some regions. Over decades New Zealand developed colonial institutions: provincial governments, infrastructure (roads, ports, rail), and export industries such as wool, meat and dairy that connected the islands to global markets.",
        "By the late 19th century New Zealand had begun to build a distinctive political identity. It became one of the world’s pioneers in social policy — notably granting women the right to vote in 1893 — and developed public systems such as pensions and health reforms in the 20th century. New Zealanders also forged a reputation for pragmatic social reform and strong civic institutions.",
        "New Zealanders fought alongside Britain and allies in both world wars. The ANZAC tradition (shared with Australia) and battles such as Gallipoli created a powerful national memory of courage and sacrifice and helped shape New Zealand’s sense of identity on the world stage. After WWII New Zealand gradually shifted from a British-dominated foreign policy to a more independent international role.",
        "From the late 20th century, a cultural and political revival strengthened Māori language, arts and tikanga (custom). The government created legal and institutional pathways (including the Waitangi Tribunal and settlement processes) to address historical grievances over land and breaches of the Treaty. These efforts have led to important legal settlements, cultural revitalization and greater recognition of Te Tiriti in public life.",
        "Today New Zealand is a modern, diverse nation with a mixed economy (agriculture, tourism, services, tech and film). It values environmental stewardship, outdoor life and biculturalism, and its cities reflect a multicultural population drawn from Māori, Pākehā (European), Pacific and Asian communities. Political life is shaped by ongoing conversations about equity, climate action, Māori rights, and New Zealand’s place in the world."
    )

    private val paragraphsTitles = listOf(
        "Overview",
        "Māori arrival and early society",
        "First European contacts",
        "Treaty of Waitangi (1840)",
        "Colony, settlement and change",
        "Nation building and social reform",
        "War and international identity",
        "Māori revival and Treaty settlements",
        "Contemporary New Zealand"
    )

    override fun getNewZealandHistory(): NewZealandHistory =
        NewZealandHistory(
            paragraphs = paragraphs,
            paragraphsTitles = paragraphsTitles
        )
}