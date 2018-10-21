package saiki.app.moegithubsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import saiki.app.common.GitHubRepo

class RepositoriesAdapter(private val mRepositoriesList: List<GitHubRepo>) : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false)
        return RepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.titleTextView.text = mRepositoriesList[position].name
    }

    override fun getItemCount(): Int {
        return mRepositoriesList.size
    }

    inner class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.titleTextView)

    }
}