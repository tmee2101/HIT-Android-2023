package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
//    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtInput: TextView = findViewById(R.id.txt_input)
        val txtOutput: TextView = findViewById(R.id.txt_output)
        val txt0: TextView = findViewById(R.id.txt0)
        val txt1: TextView = findViewById(R.id.txt1)
        val txt2: TextView = findViewById(R.id.txt2)
        val txt3: TextView = findViewById(R.id.txt3)
        val txt4: TextView = findViewById(R.id.txt4)
        val txt5: TextView = findViewById(R.id.txt5)
        val txt6: TextView = findViewById(R.id.txt6)
        val txt7: TextView = findViewById(R.id.txt7)
        val txt8: TextView = findViewById(R.id.txt8)
        val txt9: TextView = findViewById(R.id.txt9)
        val txtDivide: TextView = findViewById(R.id.txt_divide)
        val txtPlus: TextView = findViewById(R.id.txt_plus)
        val txtSubtract: TextView = findViewById(R.id.txt_subtract)
        val txtMultiple: TextView = findViewById(R.id.txt_multiply)
        val txtEqual: TextView = findViewById(R.id.txt_equal)
        val txtClear: TextView = findViewById(R.id.txt_ac)
        val txtPercent: TextView = findViewById(R.id.txt_percent)
        val txtDecPoint: TextView = findViewById(R.id.txt_dec_point)

        txt0.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "0"
        }

        txt1.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "1"
        }

        txt2.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "2"
        }

        txt3.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "3"
        }

        txt4.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "4"
        }

        txt5.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "5"
        }

        txt6.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "6"
        }

        txt7.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "7"
        }

        txt8.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "8"
        }

        txt9.setOnClickListener {
            txtInput.text = txtInput.text.toString() + "9"
        }

        txtClear.setOnClickListener {
            txtInput.text = ""
            txtOutput.text = ""
        }

        var tempInput: String
        txtPlus.setOnClickListener {
            tempInput = txtInput.text.toString() + "+"
            Log.e("", tempInput)
            if (isValid(tempInput))
                txtInput.text = tempInput
        }

        txtSubtract.setOnClickListener {
            tempInput = txtInput.text.toString() + "-"
            if (isValid(tempInput))
                txtInput.text = tempInput
        }

        txtMultiple.setOnClickListener {
            tempInput = txtInput.text.toString() + "x"
            if (isValid(tempInput))
                txtInput.text = tempInput
        }

        txtDivide.setOnClickListener {
            tempInput = txtInput.text.toString() + "/"
            if (isValid(tempInput))
                txtInput.text = tempInput
        }

        txtPercent.setOnClickListener {
            val input = txtInput.text.toString()
            if (!isMathSymbol(input.last()))
                txtInput.text = calculate(input).toString() + "/100"
        }

        txtDecPoint.setOnClickListener {
            tempInput = txtInput.text.toString() + "."
            if (isValid(tempInput))
                txtInput.text = tempInput
        }

        txtEqual.setOnClickListener {
            val input = txtInput.text.toString()
            if (!isMathSymbol(input.last()))
                txtOutput.text = calculate(input).toString()
        }
    }
}

fun isMathSymbol(char: Char): Boolean {
    return (char == '+' || char == '-' || char == 'x' || char == '/' || char == '.')
}

fun isValid(input: String): Boolean {
    if (input.isEmpty())
        return false

    val firstChar = input.first()
    if (firstChar == '/' || firstChar == 'x' || firstChar == '.')
        return false

    var beforeChar = 'b'
    input.forEach {
        if (isMathSymbol(it) && isMathSymbol(beforeChar))
            return false
        beforeChar = it
    }

    var beforeOperator = 'o'
    input.forEach {
        if (it == '.' && beforeOperator == '.')
            return false
        if (isMathSymbol(it))
            beforeOperator = it
    }

    return true
}

fun calculate(txt: String): Double {
    var input = txt
    if (isMathSymbol(input.first()))
        input = "0" + input

    val valueStringList: List<String> = input.split("+", "-", "x", "/")
    val valueList = ArrayList<Double>()
    val operatorList = ArrayList<Char>()
    valueStringList.forEach {
        valueList.add(it.toDouble())
    }
    operatorList.add('+')
    input.forEach {
        if (it == '+' || it == '-' || it == 'x' || it == '/')
            operatorList.add(it)
    }

    val size = valueList.size
    var i = 0
    var result = 0.0
    while (i < size) {
        if (operatorList[i] == 'x' || operatorList[i] == '/') {
            var j = i
            var tempValue = valueList[j - 1]
            while (j < size && !(operatorList[j] == '+' || operatorList[j] == '-')) {
                if (operatorList[j] == 'x')
                    tempValue *= valueList[j]
                else tempValue /= valueList[j]
                Log.e("", operatorList[j].toString())
                j++
            }

            if (operatorList[i - 1] == '-')
                result -= tempValue - valueList[i - 1]
            else result += tempValue - valueList[i - 1]
            i = j - 1
        }
        if (operatorList[i] == '+')
            result += valueList[i]
        if (operatorList[i] == '-')
            result -= valueList[i]
        i++
    }

    result = Math.round(result * 100000000.0) / 100000000.0
    return result
}

