package saiki.app.common

import retrofit2.http.GET
import rx.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface GitHubApi {
    @get:GET("orgs/SnowdogApps/repos")
    val repos: Observable<List<GitHubRepo>>
}

object ServiceGenerator {

    private val BASE_URL = "https://api.github.com/"

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

    fun <S> createGitHubClient(serviceClass: Class<S>): S {
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}

