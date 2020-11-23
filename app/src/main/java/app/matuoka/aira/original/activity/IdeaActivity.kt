package app.matuoka.aira.original.activity

import android.os.Bundle
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

        val firstWordTitle = intent.getStringExtra("FIRST_WORD_TITLE") ?: ""
        val secondWordTitle = intent.getStringExtra("SECOND_WORD_TITLE") ?: ""

        firstWordTextView.text = firstWordTitle
        secondWordTextView.text = secondWordTitle

        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()

            save(title, content, firstWordTitle, secondWordTitle)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun save(title: String, content: String, firstWord: String, secondWord: String) {
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

}
