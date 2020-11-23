package app.matuoka.aira.original.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.matuoka.aira.original.R
import app.matuoka.aira.original.model.Word
import app.matuoka.aira.original.adapter.WordAdapter
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_group_list.*
import kotlinx.android.synthetic.main.activity_word_list.floatingActionButton
import kotlinx.android.synthetic.main.activity_word_list.recyclerView
import java.util.*

class WordListActivity : AppCompatActivity() {

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        val listener = object :
            WordAdapter.OnItemClickListener {
            override fun onItemClick(item: Word) {

                val editText = EditText(this@WordListActivity)
                editText.hint = "単語名"
                editText.setText(item.title)

                AlertDialog.Builder(this@WordListActivity)
                    .setTitle("単語の編集")
                    .setView(editText)
                    .setPositiveButton("更新") { _, _ ->
                        val title: String = editText.text.toString()
                        if (title.isBlank()) {
                            Snackbar.make(container, "単語名を入れてね！！", Snackbar.LENGTH_SHORT).show()
                        } else {
                            update(item, title)
                        }
                    }
                    .setNegativeButton("キャンセル", null)
                    .setNeutralButton("削除") { _, _ ->

                        AlertDialog.Builder(this@WordListActivity)
                            .setTitle(item.title + "を削除しますか？")
                            .setPositiveButton("削除") { _, _ ->
                                delete(item)
                            }
                            .setNegativeButton("キャンセル", null)
                            .show()
                    }
                    .show()

            }
        }

        val groupId = intent.getStringExtra("GroupId") ?: ""
        val wordList = readAll(groupId)
        val adapter =
            WordAdapter(
                this,
                listener,
                wordList,
                true
            )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        floatingActionButton.setOnClickListener {
            val editText = EditText(this)
            editText.hint = "単語名"
            AlertDialog.Builder(this)
                .setTitle("単語の作成")
                .setView(editText)
                .setPositiveButton("作成") { _, _ ->
                    val title: String = editText.text.toString()
                    if (title.isBlank()) {
                        Snackbar.make(container, "単語名を入れてね！！", Snackbar.LENGTH_SHORT).show()
                    } else {
                        create(title, groupId)
                    }
                }
                .setNeutralButton("キャンセル", null)
                .show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun readAll(groupId: String): RealmResults<Word> {
        return realm.where(Word::class.java).equalTo("groupId", groupId).findAll()
            .sort("createdAt", Sort.ASCENDING)
    }

    fun create(title: String, groupId: String) {
        realm.executeTransaction {
            val word: Word = it.createObject(
                Word::class.java, UUID.randomUUID().toString())
            word.title = title
            word.groupId = groupId
        }
    }

    fun update(word: Word, newTitle: String) {
        realm.executeTransaction {
            word.title = newTitle
        }
    }

    fun delete(word: Word) {
        realm.executeTransaction {
            word.deleteFromRealm()
        }
    }

}