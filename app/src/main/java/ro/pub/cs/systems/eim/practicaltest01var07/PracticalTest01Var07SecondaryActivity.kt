package ro.pub.cs.systems.eim.practicaltest01var07

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var07SecondaryActivity : AppCompatActivity() {

    // Vom proceda la fel ca in cazul primei activitati
    private lateinit var input11: TextView
    private lateinit var input22: TextView
    private lateinit var input33: TextView
    private lateinit var input44: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        // Va trebui să primim intenția din prima activitate
        val in1=intent.getIntExtra("input1",0)
        val in2=intent.getIntExtra("input2",0)
        val in3=intent.getIntExtra("input3",0)
        val in4=intent.getIntExtra("input4",0)

        // Asociem inputurile definite mai sus cu campurile din interfata
        input11 = findViewById(R.id.input1)
        input22 = findViewById(R.id.input2)
        input33 = findViewById(R.id.input3)
        input44 = findViewById(R.id.input4)

        // Asociem pentru textul din input-uri intentiile definite mai sus
        input11.text=in1.toString();
        input22.text=in2.toString();
        input33.text=in3.toString();
        input44.text=in4.toString();

        // Calculam suma
        val sum=findViewById<TextView>(R.id.sum)
        sum.setOnClickListener{
            val result=in1+in2+in3+in4
            val intent=Intent()
            intent.putExtra("result",result.toString())
            setResult(RESULT_OK,intent)
            finish();
        }

        // Calculam produsul
        val prod=findViewById<TextView>(R.id.prod)
        prod.setOnClickListener{
            val result=in1*in2*in3*in4
            val intent=Intent()
            intent.putExtra("result",result.toString())
            setResult(RESULT_OK,intent)
            finish();
        }
    }
}