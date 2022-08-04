package com.example.mycal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastn = false
    var ldot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
//        Toast.makeText(this,"Button Works", Toast.LENGTH_SHORT ).show()
        textview.append((view as Button).text)
        lastn = true
    }
    fun onClr(view: View){
        textview.setText("")
        lastn = false
        ldot = false
    }
    fun ondecimal(view: View){
        if(lastn && !ldot){
            textview.append(".")
            lastn = false
            ldot = true
        }
    }

    fun Onequal(view: View){
        if (lastn){
            var textvalue = textview.text.toString()
            var prefix = ""
            try {
                if(textvalue.startsWith("-")){
                    prefix = "-"
                    textvalue = textvalue.substring(1)
                }
                if(textvalue.contains("-")){
                    val Splitvalue = textvalue.split("-")
                    var one = Splitvalue[0]
                    var two = Splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textview.text = RemoveZero((one.toDouble() / two.toDouble()).toString())
                }
                if(textvalue.contains("+")){
                    val Splitvalue = textvalue.split("+")
                    var one = Splitvalue[0]
                    var two = Splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textview.text = RemoveZero((one.toDouble() / two.toDouble()).toString())
                }
                if(textvalue.contains("*")){
                    val Splitvalue = textvalue.split("*")
                    var one = Splitvalue[0]
                    var two = Splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textview.text = RemoveZero((one.toDouble() / two.toDouble()).toString())
                }
                if(textvalue.contains("/")){
                    val Splitvalue = textvalue.split("/")
                    var one = Splitvalue[0]
                    var two = Splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    textview.text = RemoveZero((one.toDouble() / two.toDouble()).toString())
                }


            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun RemoveZero(result: String): String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    fun onOperator(view: View){
        if (lastn && !isOperator(textview.text.toString())){
            textview.append((view as Button).text)
            lastn = false
            ldot = false
        }
    }

    private fun isOperator(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("+") || value.contains("*") || value.contains("-")
        }
    }
}