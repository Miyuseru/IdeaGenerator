package app.matuoka.aira.original.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.matuoka.aira.original.R
import app.matuoka.aira.original.model.Group
import app.matuoka.aira.original.model.Idea
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_word_list.view.*


class IdeaAdapter(
    private val context: Context,
    private var listener: OnItemClickListener,
    private var ideaList: OrderedRealmCollection<Idea>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Idea, IdeaAdapter.IdeaViewHolder>(ideaList, autoUpdate) {

    override fun getItemCount(): Int = ideaList?.size ?: 0
    override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
        val idea: Idea = ideaList?.get(position) ?: return
        holder.titleTextView.text = idea.title

        holder.container.setOnClickListener{
            listener.onItemClick(idea)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): IdeaViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_idea_list, viewGroup, false)
        return IdeaViewHolder(v)
    }

    class IdeaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val container: LinearLayout = view.container
    }

    interface OnItemClickListener {
        fun onItemClick(item: Idea)
    }

}