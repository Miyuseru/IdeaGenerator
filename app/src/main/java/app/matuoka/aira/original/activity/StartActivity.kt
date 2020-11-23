package app.matuoka.aira.original.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.matuoka.aira.original.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        makeIdeaButton.setOnClickListener {
            val intent = Intent(this, ChoiceGroupActivity::class.java)
            startActivity(intent)
        }

        groupListButton.setOnClickListener {
            val intent = Intent(this, GroupListActivity::class.java)
            startActivity(intent)
        }

        showIdeaButton.setOnClickListener {
            val intent = Intent(this, IdeaListActivity::class.java)
            startActivity(intent)
        }
    }
}