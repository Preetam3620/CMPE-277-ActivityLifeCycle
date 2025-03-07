package com.example.cmpe_277_activitylifecycle

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        findViewById<Button>(R.id.finishB).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("INCREMENT", 5)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
