package ro.pub.cs.systems.eim.practicaltest01var07

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07MainActivity : AppCompatActivity() {

    // Am pus 4 pentru că avem 4 câmpuri pentru EditText
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText
    private lateinit var input4: EditText

    private val messageBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val n1=intent.getIntExtra("n1",0);
                val n2=intent.getIntExtra("n2",0);
                val n3=intent.getIntExtra("n3",0);
                val n4=intent.getIntExtra("n4",0);

                input1.setText(n1.toString())
                input2.setText(n2.toString())
                input3.setText(n3.toString())
                input4.setText(n4.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var07_main)

        val serviceIntent=Intent(this, PracticalTest01Service::class.java)
        startService(serviceIntent)

        // Asocierea dintre variabilele input si EditText-ele definite in interfata
        input1 = findViewById(R.id.EditText1)
        input2 = findViewById(R.id.EditText2)
        input3 = findViewById(R.id.EditText3)
        input4 = findViewById(R.id.EditText4)

        // Pentru partea de Toast (C2)
        val activityResultsLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val message = result.data?.getStringExtra("result")
                    if (message != null) {
                        Toast.makeText(
                            this,
                            "The activity returned with result OK",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        // Pentru a naviga către activitatea secundară
        val navigateToSecondaryActivityButton =
            findViewById<Button>(R.id.Buton1) // Id-ul de la butonul de set
        navigateToSecondaryActivityButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var07SecondaryActivity::class.java)
            // Aici putem butoanele noastre, în acest caz, cele 4 butoane text
            intent.putExtra("input1", Integer.valueOf(input1.text.toString()))
            intent.putExtra("input2", Integer.valueOf(input2.text.toString()))
            intent.putExtra("input3", Integer.valueOf(input3.text.toString()))
            intent.putExtra("input4", Integer.valueOf(input4.text.toString()))
            activityResultsLauncher.launch(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter("ProcessingThread")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(messageBroadcastReceiver, filter, RECEIVER_EXPORTED)
        } else {
            registerReceiver(messageBroadcastReceiver, filter)
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageBroadcastReceiver)
    }

    override fun onDestroy() {
        val intent = Intent(applicationContext, PracticalTest01Service::class.java)
        applicationContext.stopService(intent)
        super.onDestroy()
    }

}