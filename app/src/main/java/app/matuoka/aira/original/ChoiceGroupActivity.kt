package app.matuoka.aira.original

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_choice_group.*


class ChoiceGroupActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_group)
        val groupList = readAll()
        val titleList = mutableListOf<String>()

        groupList.forEach {
            titleList.add(it.title)
        }

        ideaTextView1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("１つ目のグループの選択")
                .setItems(titleList.toTypedArray()) { _, index ->
                    ideaTextView1.text = titleList[index]
                }
                .show()
        }
        ideaTextView2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("２つ目のグループの選択")
                .setItems(titleList.toTypedArray()) { _, index ->
                    ideaTextView2.text = titleList[index]
                }
                .show()
        }
    }


    fun readAll(): RealmResults<Group> {
        return realm.where(Group::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

}