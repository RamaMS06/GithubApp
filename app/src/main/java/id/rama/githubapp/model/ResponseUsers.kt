package id.rama.githubapp.model

data class ResponseUsers(
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<ModelUsers>
)