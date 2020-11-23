package app.matuoka.aira.original

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        ideaTextView1.text = firstGroupWordList[0]?.title
        ideaTextView2.text = secondGroupWordList[0]?.title
        
        nextButton.setOnClickListener {
            val firstRandomNum = Random.nextInt(firstGroupWordList.size)
            val secondRandomNum = Random.nextInt(secondGroupWordList.size)
            ideaTextView1.text = firstGroupWordList[firstRandomNum]?.title
            ideaTextView2.text = secondGroupWordList[secondRandomNum]?.title
        }
    }


    fun readAll(groupId: String): RealmResults<Word> {
        return realm.where(Word::class.java).equalTo("groupId", groupId).findAll()
            .sort("createdAt", Sort.ASCENDING)
    }

}