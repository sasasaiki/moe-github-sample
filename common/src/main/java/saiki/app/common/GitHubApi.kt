package saiki.app.common

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface GitHubApi {
    @GET("orgs/SnowdogApps/getRepos")
    fun getRepos(): Single<List<GitHubRepo>>
}

object ServiceGenerator {

    private const val BASE_URL = "https://api.github.com/"

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun <S> createGitHubClient(serviceClass: Class<S>): S {
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}

class Resource<T>(val status: Status, val Value: T) {

    companion object {
        fun <T> success(value: T): Resource<T> {
            return Resource(Status.SUCCESS, value)
        }

        fun <T> error(value: T): Resource<T> {
            return Resource(Status.ERROR, value)
        }

        fun <T> load(value: T): Resource<T> {
            return Resource(Status.LOAD, value)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOAD
    }
}

