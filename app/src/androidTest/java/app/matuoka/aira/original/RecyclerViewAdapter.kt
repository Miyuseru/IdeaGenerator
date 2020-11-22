package app.matuoka.aira.original

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context : Context) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHOlder>()
    {
        class ViewHOlder(view: View) : RecyclerView.ViewHolder(view) {
            val characterImage: ImageView = view.findViewById(R.id.characterimageView)
            val characterNameTextView: TextView = view.findViewById(R.id.courseNameTextView)

        }

        override fun onBindViewHolder(holder: ViewHOlder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHOlder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }


    }


