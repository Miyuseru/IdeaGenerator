package app.matuoka.aira.original.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.matuoka.aira.original.R
import app.matuoka.aira.original.model.Idea
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_idea.*
import java.util.*

class IdeaActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea)

        val ideaId = intent.getStringExtra("IDEA_ID") ?: ""
        val idea = read(ideaId)
        var firstWordTitle = ""
        var secondWordTitle = ""
        if (idea == null) {
            firstWordTitle = intent.getStringExtra("FIRST_WORD_TITLE") ?: ""
            secondWordTitle = intent.getStringExtra("SECOND_WORD_TITLE") ?: ""
        } else {
            titleEditText.setText(idea.title)
            contentEditText.setText(idea.content)
            firstWordTitle = idea.firstWord
            secondWordTitle = idea.secondWord
        }

        firstWordTextView.text = firstWordTitle
        secondWordTextView.text = secondWordTitle

        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            if (idea == null) {
                create(title, content, firstWordTitle, secondWordTitle)
                Toast.makeText(this, title + "を作成しました", Toast.LENGTH_SHORT)
                    .show()
            } else {
                update(idea, title, content)
                Toast.makeText(this, title + "を更新しました", Toast.LENGTH_SHORT)
                    .show()
            }
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun read(ideaId: String): Idea? {
        return realm.where(Idea::class.java).equalTo("id", ideaId).findFirst()
    }

    fun create(title: String, content: String, firstWord: String, secondWord: String) {
        realm.executeTransaction {
            val idea: Idea = it.createObject(
                Idea::class.java, UUID.randomUUID().toString()
            )
            idea.title = title
            idea.content = content
            idea.firstWord = firstWord
            idea.secondWord = secondWord
        }
    }

    fun update(idea: Idea, newTitle: String, newContent: String) {
        realm.executeTransaction {
            idea.title = newTitle
            idea.content = newContent
        }
    }
}
