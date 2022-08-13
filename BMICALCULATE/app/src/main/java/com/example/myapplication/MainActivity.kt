package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Bu yerda buttonni idsi orqali chaqirib olamiz...

        val calcbutton = findViewById<Button>(R.id.btncalc)

// bu yerda buttonga malumotlar yuborlib unda matematik amal bajarish buyrug`i yoziladi...
// buttonga string farmatdagi malumotni yubargan yaxshi xatolik kam bo`ladi...

        calcbutton.setOnClickListener{
            val weight = findViewById<EditText>(R.id.edweight).text.toString()

            val height = findViewById<EditText>(R.id.edheight).text.toString()
            if (seendata(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))

                // bu qator bizning naijamizni butun qismini farmatlashga yordam beradi

                val bmidigit2 = String.format("%.2f", bmi).toFloat()



                getdata(bmidigit2)
            }
        }
    }

    private fun seendata(weight:String?,height:String?):Boolean{

        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"Ma`lumotlar to`liq kiritilmagan!!!",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"Ma`lumotlar to`liq kiritilmagan!!!",Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }


    private fun getdata(bmi:Float){

        val weight = findViewById<EditText>(R.id.edweight).text.toString()

        // Quyidagi o`zgaruvchi bmi dan chiqqan natijani ekranda tasvirlaydi
        val calculate = findViewById<TextView>(R.id.calcresult)

        calculate.text = bmi.toString()

        // bu o`zgaruvchi shaxsning holatini ifodalaydi, ya`ni sog`lom yoki vazndagi muommosini
        val theresult = findViewById<TextView>(R.id.theresult)

        // bu o`zgaruvchi shaxsning normal holatidan qanchaga vaznining kam yoki ortiqligini ko`rsatadi.
        val theresult2 = findViewById<TextView>(R.id.theresult2)

        var resultweight = 0
        var resulttext = ""
        var color = 0

        when{
            bmi<18.5->{
                resultweight = (weight.toFloat()/bmi*(18.5-bmi).toFloat()).toInt()
                resulttext = " kg yetishmayotgan vazn"
                color = R.color.underweight
            }
            bmi in 18.5..24.99 -> {

                resulttext = "k! Sog`lomsiz"
                color = R.color.normal

            }
            bmi in 25.00..29.99 -> {
                resultweight = (weight.toFloat()/bmi*(bmi-24.9).toFloat()).toInt()
                resulttext = " kg ortiqcha vazn"
                color = R.color.overweight
            }
            bmi>30.00 -> {
                resultweight = (weight.toFloat()/bmi*(bmi-24.9).toFloat()).toInt()
                resulttext = " kg ortiqcha vazn"
                color = R.color.obese
            }
        }



        theresult.setTextColor(ContextCompat.getColor(this,color))
        theresult.text = resultweight.toString() + resulttext



    }
}