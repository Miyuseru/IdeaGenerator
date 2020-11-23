package app.matuoka.aira.original.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.matuoka.aira.original.R
import app.matuoka.aira.original.model.Word
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_make_idea.*
import kotlin.random.Random

class MakeIdeaActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_idea)

        val firstGroupId = intent.getStringExtra("FIRST_GROUP_ID") ?: ""
        val secondGroupId = intent.getStringExtra("SECOND_GROUP_ID") ?: ""

        val firstGroupWordList = readAll(firstGroupId)
        val secondGroupWordList = readAll(secondGroupId)

        var firstRandomNum = 0
        var secondRandomNum = 0

        ideaTextView1.text = firstGroupWordList[firstRandomNum]?.title
        ideaTextView2.text = secondGroupWordList[secondRandomNum]?.title

        nextButton.setOnClickListener {
            firstRandomNum = Random.nextInt(firstGroupWordList.size)
            secondRandomNum = Random.nextInt(secondGroupWordList.size)
            ideaTextView1.text = firstGroupWordList[firstRandomNum]?.title
            ideaTextView2.text = secondGroupWordList[secondRandomNum]?.title
        }

        memoButton.setOnClickListener {
            val intent = Intent(this, IdeaActivity::class.java)
            intent.putExtra("FIRST_WORD_TITLE", firstGroupWordList[firstRandomNum]?.title)
            intent.putExtra("SECOND_WORD_TITLE", secondGroupWordList[secondRandomNum]?.title)
            startActivity(intent)
        }
    }


    fun readAll(groupId: String): RealmResults<Word> {
        return realm.where(Word::class.java).equalTo("groupId", groupId).findAll()
            .sort("createdAt", Sort.ASCENDING)
    }

}