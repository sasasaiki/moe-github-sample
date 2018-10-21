package saiki.app.moegithubsample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import saiki.app.common.GitHubRepo
import saiki.app.common.RepositoryListContract
import saiki.app.common.RepositoryListPresenterImpl

class RepositoryListViewModel: ViewModel(),RepositoryListContract.View {
    val gitHubRepoLiveData : MutableLiveData<List<GitHubRepo>> = MutableLiveData()
    private val presenter : RepositoryListContract.Presenter = RepositoryListPresenterImpl(this)

    override fun showRepoList(repoList: List<GitHubRepo>) {
        gitHubRepoLiveData.value = repoList
    }

    fun getRepoList(){
        presenter.getRepositories(Schedulers.io(), AndroidSchedulers.mainThread())
    }
}