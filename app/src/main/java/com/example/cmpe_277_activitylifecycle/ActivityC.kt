package com.example.cmpe_277_activitylifecycle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        findViewById<Button>(R.id.finishC).setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("INCREMENT", 10)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
