package test.kotlin.nuhin13.kotlintest

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(ApiService.Factory.create())
    }
}