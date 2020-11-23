package app.matuoka.aira.original

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_word_list.view.*

class GroupAdapter(
    private val context: Context,
    private var listener: OnItemClickListener,
    private var groupList: OrderedRealmCollection<Group>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Group, GroupAdapter.GroupViewHolder>(groupList, autoUpdate) {

    override fun getItemCount(): Int = groupList?.size ?: 0
    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group: Group = groupList?.get(position) ?: return
        holder.titleTextView.text = group.title

        holder.container.setOnClickListener{
            listener.onItemClick(group)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GroupViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_group_list, viewGroup, false)
        return GroupViewHolder(v)
    }

    class GroupViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val container:  LinearLayout = view.container
    }

    interface OnItemClickListener {
        fun onItemClick(item: Group)
    }

}