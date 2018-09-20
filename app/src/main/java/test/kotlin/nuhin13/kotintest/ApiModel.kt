package test.kotlin.nuhin13.kotintest

data class ApiModel(val message: String,
                    val code: Long,
                    val order: Order,
                    val jobs: Jobs,
                    val version: String)

data class Jobs(    val id: Long,
                    val status: String)

data class Order(   val id: Long,
                    val customer_mobile: String,
                    val customer_name: String)