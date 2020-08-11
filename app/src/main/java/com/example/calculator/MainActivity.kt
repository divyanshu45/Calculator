package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        one.setOnClickListener{ appendOnExpression("1",true)}
        two.setOnClickListener{ appendOnExpression("2",true)}
        three.setOnClickListener{ appendOnExpression("3",true)}
        four.setOnClickListener{ appendOnExpression("4",true)}
        five.setOnClickListener{ appendOnExpression("5",true)}
        six.setOnClickListener{ appendOnExpression("6",true)}
        seven.setOnClickListener{ appendOnExpression("7",true)}
        eight.setOnClickListener{ appendOnExpression("8",true)}
        nine.setOnClickListener{ appendOnExpression("9",true)}
        zero.setOnClickListener{ appendOnExpression("0",true)}
        dot.setOnClickListener { appendOnExpression(".",true)}


        plus.setOnClickListener{ appendOnExpression("+",false)}
        minus.setOnClickListener{ appendOnExpression("-",false)}
        multiply.setOnClickListener{ appendOnExpression("*",false)}
        divide.setOnClickListener{ appendOnExpression("/",false)}
        open.setOnClickListener{ appendOnExpression("(",false)}
        close.setOnClickListener{ appendOnExpression(")",false)}

        back.setOnClickListener{
            val string = expression.text.toString()
            if(string.isNotEmpty()){
                expression.text = string.substring(0,string.length-1)
            }
            result.text = ""
        }

        btn_clear.setOnClickListener{
            expression.text = ""
            result.text = ""
        }

        btn_equals.setOnClickListener{
            try{
                val expression = ExpressionBuilder(expression.text.toString()).build()
                val resultexp = expression.evaluate()
                val longresult = resultexp.toLong()
                if(resultexp == longresult.toDouble()){
                    result.text = longresult.toString()
                }else{
                    result.text = resultexp.toString()
                }
            }catch(e : Exception){
            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean){
        if(result.text.isNotEmpty()){
            expression.text = ""
        }
        if(canClear){
            result.text = ""
            expression.append(string)
        }else{
            expression.append(result.text)
            expression.append(string)
            result.text = ""
        }
    }
}

