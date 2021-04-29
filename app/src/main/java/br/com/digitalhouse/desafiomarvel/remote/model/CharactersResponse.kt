package br.com.digitalhouse.desafiomarvel.remote.model

data class CharactersResponse (
    val `data`: Data,
) {
    data class Data(
        val count: Int,
        val limit: Int,
        val offset: Int,
        val results: List<Result>,
        val total: Int
    ) {
        data class Result(
            val comics: Comics,
            val description: String,
            val id: Int,
            val modified: String,
            val name: String,
            val resourceURI: String,
        ) {
            data class Comics(
                val available: Int,
                val collectionURI: String,
                val items: List<Item>,
                val returned: Int
            ) {
                data class Item(
                    val name: String,
                    val resourceURI: String
                )
            }
        }
    }
}