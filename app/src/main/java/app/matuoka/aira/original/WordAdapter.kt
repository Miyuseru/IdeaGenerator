package app.matuoka.aira.original

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_word_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class WordAdapter(
    private val context: Context,
    private var listener: WordAdapter.OnItemClickListener,
    private var wordList: OrderedRealmCollection<Word>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Word, WordAdapter.WordViewHolder>(wordList, autoUpdate) {

    override fun getItemCount(): Int = wordList?.size ?: 0

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word: Word = wordList?.get(position) ?: return

        holder.titleTextView.text = word.title
        holder.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(word.createdAt)

        holder.container.setOnClickListener {
            listener.onItemClick(word)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_word_list, viewGroup, false)
        return WordViewHolder(v)
    }

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.titleTextView
        val dateTextView: TextView = view.dateTextView
        val container: LinearLayout = view.container
    }

    interface OnItemClickListener {
        fun onItemClick(item: Word)
    }

}