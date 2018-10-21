package saiki.app.moegithubsample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import saiki.app.common.GitHubRepo
import android.widget.LinearLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(RepositoryListViewModel::class.java) }

    private fun showRepoList(repoList: List<GitHubRepo>) {
        val mRepositoriesAdapter = RepositoriesAdapter(repoList)
        repository_recycler_view.adapter = mRepositoriesAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository_recycler_view.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
                repository_recycler_view.context,
                LinearLayout.VERTICAL
        )
        repository_recycler_view.addItemDecoration(dividerItemDecoration)


        viewModel.gitHubRepoLiveData.observe(this, Observer<List<GitHubRepo>> { repoList ->
            if (repoList == null) return@Observer
            showRepoList(repoList)
        })

        viewModel.getRepoList()
    }

}

