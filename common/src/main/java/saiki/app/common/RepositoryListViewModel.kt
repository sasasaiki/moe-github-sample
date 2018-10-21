package saiki.app.common

import io.reactivex.*
import io.reactivex.disposables.Disposable


class RepositoryListPresenterImpl(val view: RepositoryListContract.View) : RepositoryListContract.Presenter {

    override fun getRepositories(subscribeThread: Scheduler, postThread: Scheduler) {
        val gitHubApi = ServiceGenerator.createGitHubClient(GitHubApi::class.java)
        gitHubApi.getRepos()
                .subscribeOn(subscribeThread)
                .observeOn(postThread)
                .subscribe(object : SingleObserver<List<GitHubRepo>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(repoList: List<GitHubRepo>) {
                        view.showRepoList(repoList)
                    }

                    override fun onError(e: Throwable) {}
                })
    }
}

interface RepositoryListContract {
    interface View {
        fun showRepoList(repoList: List<GitHubRepo>)
    }

    interface Presenter {
        fun getRepositories(subscribeThread: Scheduler, postThread: Scheduler)
    }
}