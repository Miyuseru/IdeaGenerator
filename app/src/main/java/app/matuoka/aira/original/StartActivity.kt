package app.matuoka.aira.original

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        button.setOnClickListener{
          val tomainIntent = Intent(this,MainActivity::class.java)
           startActivity(tomainIntent)
        }

        button2.setOnClickListener{
            val torecyclerviewActivityIntent = Intent(this,WordListActivity::class.java)
           startActivity(torecyclerviewActivityIntent)
        }
    }
    }