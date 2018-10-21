package saiki.app.common

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

class RepositoryListPresenterImple(){

    val repositoriesObservable: PublishSubject<Resource<List<GitHubRepo>>> = PublishSubject.create<Resource<List<GitHubRepo>>>()

    fun getRepositories(postThread: Scheduler, subscribeThread: Scheduler) {
        val gitHubApi = ServiceGenerator.createGitHubClient(GitHubApi::class.java)
        gitHubApi.repos
                .subscribeOn(subscribeThread)
                .observeOn(postThread)
                .subscribe(object : SingleObserver<List<GitHubRepo>> {
                    override fun onSubscribe(d: Disposable) {
                        repositoriesObservable.onNext(Resource.load(listOf()))
                    }

                    override fun onSuccess(repoList: List<GitHubRepo>) {
                        repositoriesObservable.onNext(Resource.success(repoList))
                    }

                    override fun onError(e: Throwable) {
                        repositoriesObservable.onNext(Resource.error(listOf()))
                    }
                })
    }
}
