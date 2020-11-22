import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.matuoka.aira.original.Memo
import app.matuoka.aira.original.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(

    private val context: Context,
    private var taskList: OrderedRealmCollection<Memo>?,
    private val autoUpdate: Boolean
) :
    RealmRecyclerViewAdapter<Memo, TaskAdapter.TaskViewHolder>(taskList, autoUpdate) {

    override fun getItemCount(): Int = taskList?.size ?: 0

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: Memo = taskList?.get(position) ?: return

        holder.imageView.setImageResource(task.imageId)
        holder.titleTextView.text = task.title
        holder.contentTextView.text = task.content
        holder.dateTextView.text =
            SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(task.createdAt)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TaskViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false)
        return TaskViewHolder(v)
    }

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.imageView
        val titleTextView: TextView = view.titleTextView
        val contentTextView: TextView = view.contentTextView
        val dateTextView: TextView = view.dateTextView
    }

}