package com.example.cmpe_277_activitylifecycle

import android.os.Bundle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cmpe_277_activitylifecycle.ui.theme.CMPE277ActivityLifeCycleTheme
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var restartCounter = 0
    private lateinit var restartCounterText: TextView
    private var threadCounter = 0
    private lateinit var threadCounterText: TextView
    private var isRunning = true
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        const val REQUEST_CODE_B = 1
        const val REQUEST_CODE_C = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restartCounterText = findViewById(R.id.restartCounter)
        threadCounterText = findViewById(R.id.threadCounter)

        findViewById<Button>(R.id.startActivityB).setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivityForResult(intent, REQUEST_CODE_B);
        }

        findViewById<Button>(R.id.startActivityC).setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivityForResult(intent, REQUEST_CODE_C);
        }

        findViewById<Button>(R.id.openDialog).setOnClickListener {
            val dialog = DialogActivity()
            dialog.show(supportFragmentManager, "DialogActivity")
        }

        findViewById<Button>(R.id.closeApp).setOnClickListener {
            finishAffinity()
        }

        startCounterThread()
    }

    private fun startCounterThread() {
        Thread {
            while (isRunning) {
                Thread.sleep(1000)
                handler.post {
                    threadCounterText.text = String.format("Thread Counter: %04d", threadCounter)
                }
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
        restartCounter++
        restartCounterText.text = "onRestart() Count: $restartCounter"
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
        startCounterThread()
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val increment = data?.getIntExtra("INCREMENT", 0) ?: 0
            threadCounter += increment
            threadCounterText.text = "Thread Counter: $threadCounter"
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CMPE277ActivityLifeCycleTheme {
            Greeting("Android")
        }
    }
}