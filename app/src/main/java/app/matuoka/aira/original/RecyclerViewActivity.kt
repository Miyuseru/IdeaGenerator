package app.matuoka.aira.original

import TaskAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_recycler_view.*
import java.util.*

class RecyclerViewActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val taskList = readAll()

        // タスクリストが空だったときにダミーデータを生成する
        if (taskList.isEmpty()) {
            createDummyData()
        }

        val adapter = TaskAdapter(this, taskList, true)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun createDummyData() {
        for (i in 0..10) {
            create(R.drawable.ic_launcher_background, "やること $i")
        }
    }

    fun create(imageId: Int, content: String) {
        realm.executeTransaction {
            val task = it.createObject(Memo::class.java, UUID.randomUUID().toString())
            task.imageId = imageId
            task.content = content
        }
    }

    fun readAll(): RealmResults<Memo> {
        return realm.where(Memo::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

}