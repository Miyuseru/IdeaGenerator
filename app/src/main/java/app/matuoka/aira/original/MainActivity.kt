package app.matuoka.aira.original

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener {
            val title: String = titleEditText.text.toString()
            val content: String = contentEditText.text.toString()
            save(title, content)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun save(title: String, content: String) {
        realm.executeTransaction {
            val newMemo: Memo = it.createObject(Memo::class.java, UUID.randomUUID().toString())
            newMemo.title = title
            newMemo.content = content
        }

//        Snackbar.make(container, "保存できました", Snackbar.LENGTH_SHORT).show()
    }

}
