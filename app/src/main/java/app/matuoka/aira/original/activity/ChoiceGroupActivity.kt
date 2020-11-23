package app.matuoka.aira.original.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import app.matuoka.aira.original.model.Group
import app.matuoka.aira.original.R
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
        var firstIndex = 0
        var secondIndex = 0

        groupList.forEach {
            titleList.add(it.title)
        }

        ideaTextView1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("１つ目のグループの選択")
                .setItems(titleList.toTypedArray()) { _, index ->
                    ideaTextView1.text = titleList[index]
                    firstIndex = index
                }
                .show()
        }
        ideaTextView2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("２つ目のグループの選択")
                .setItems(titleList.toTypedArray()) { _, index ->
                    ideaTextView2.text = titleList[index]
                    secondIndex = index
                }
                .show()
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, MakeIdeaActivity::class.java)
            intent.putExtra("FIRST_GROUP_ID", groupList[firstIndex]?.id)
            intent.putExtra("SECOND_GROUP_ID", groupList[secondIndex]?.id)
            startActivity(intent)
        }
    }


    fun readAll(): RealmResults<Group> {
        return realm.where(Group::class.java).findAll().sort("createdAt", Sort.ASCENDING)
    }

}