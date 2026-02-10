package denys.diomaxius.newzealandguide.domain.model.nzfacts

data class NewZealandFacts(
    val facts: List<String>
) {
    fun getRandomFact(): String = facts.random()
}