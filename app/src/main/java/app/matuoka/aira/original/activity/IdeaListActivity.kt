package app.matuoka.aira.original.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.matuoka.aira.original.R
import app.matuoka.aira.original.adapter.IdeaAdapter
import app.matuoka.aira.original.model.Idea
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_group_list.*

class IdeaListActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea_list)

        val ideaList = readAll()
        val listener = object :
            IdeaAdapter.OnItemClickListener {
            override fun onItemClick(item: Idea) {
                val intent = Intent(applicationContext, IdeaActivity::class.java)
                intent.putExtra("IDEA_ID", item.id)
                startActivity(intent)
            }
        }
        val adapter = IdeaAdapter(
            this,
            listener,
            ideaList,
            true
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun readAll(): RealmResults<Idea> {
        return realm.where(Idea::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

}