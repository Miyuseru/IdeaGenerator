package app.matuoka.aira.original.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.matuoka.aira.original.R
import app.matuoka.aira.original.model.Word
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_idea.*
import java.util.*

class IdeaActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea)

        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            save(title)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun save(title: String) {
        realm.executeTransaction {
            val word: Word = it.createObject(
                Word::class.java, UUID.randomUUID().toString())
            word.title = title

        }

//        Snackbar.make(container, "保存できました", Snackbar.LENGTH_SHORT).show()
    }

}
