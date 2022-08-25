package com.example.myfirstcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstcalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vbOne.setOnClickListener{buttonOnClick("1",symbolOfOperation)}
        binding.vbTwo.setOnClickListener{buttonOnClick("2",symbolOfOperation)}
        binding.vbThree.setOnClickListener{buttonOnClick("3",symbolOfOperation)}
        binding.vbFour.setOnClickListener{buttonOnClick("4",symbolOfOperation)}
        binding.vbFive.setOnClickListener{buttonOnClick("5",symbolOfOperation)}
        binding.vbSix.setOnClickListener{buttonOnClick("6",symbolOfOperation)}
        binding.vbSeven.setOnClickListener{buttonOnClick("7",symbolOfOperation)}
        binding.vbEight.setOnClickListener{buttonOnClick("8",symbolOfOperation)}
        binding.vbNine.setOnClickListener{buttonOnClick("9",symbolOfOperation)}
        binding.vbZero.setOnClickListener{buttonOnClick("0",symbolOfOperation)}
        binding.vbDot.setOnClickListener {buttonOnClick(".",symbolOfOperation) }
        binding.vbClear.setOnClickListener { clearAll() }

        binding.vbPlus.setOnClickListener {
            takeSymbol("+")
            equalsOn = false

        }
        binding.vbMinus.setOnClickListener {
            takeSymbol("-")
            equalsOn = false
        }
        binding.vbMultiplicate.setOnClickListener {
            takeSymbol("x")
            equalsOn = false
        }
        binding.vbDivision.setOnClickListener {
            takeSymbol("÷")
            equalsOn = false
        }
        binding.vbEquals.setOnClickListener {
            operations(symbolOfOperation,number1,number2)
        }
        binding.vbDelete.setOnClickListener { deleteOneDigit() }


    }

    var number1 = ""
    var number2 = ""
    var symbolOfOperation = ""
    var equalsOn = false

    private fun buttonOnClick (digit: String,symbol:String){
        if (symbol==""){
            number1 += digit
            binding.tvMain.text = number1
        }else{
            number2 += digit
            binding.tvMain.text = number2
        }
    }

    private fun clearAll (){
        binding.tvMain.text = ""
        number1 = ""
        number2 = ""
        symbolOfOperation=""
    }

    private fun deleteOneDigit(){
        if (!number2.none() || !number1.none()){
            if (!number2.none()){
                number2.dropLast(1)
//                binding.tvMain.text = number2.joinToString("")
                binding.tvMain.text = number2
            }else if (!number1.none()){
                number1.dropLast(1)
                binding.tvMain.text = number1
//                binding.tvMain.text = number1.joinToString("")
            }else{
                binding.tvMain.text = ""
            }
        }else{
            binding.tvMain.text = ""
        }

        if (equalsOn){
            clearAll()
        }
    }


    private fun takeSymbol(Symbol:String){
        symbolOfOperation = Symbol
    }
    private fun operations(symbol:String,nro1:String,nro2:String){
        var result = 0.0
        if (!equalsOn){
            result = when(symbol){
                "" -> nro1.toDouble()
                "+" -> nro1.toDouble() + nro2.toDouble()
                "-" -> nro1.toDouble() - nro2.toDouble()
                "x" -> nro1.toDouble() * nro2.toDouble()
                "÷" -> nro1.toDouble() / nro2.toDouble()
                else -> 0.0
            }
            }else{ number1 }
            println(result)

            val formatter = DecimalFormat("###,###,###,###.########")
            val finalResult = formatter.format(result)
            println(" el final number es $finalResult")

        if (finalResult == "∞"){
            binding.tvMain.text = "No se puede dividir entre 0"
        }else if(finalResult.endsWith(".0")){
            binding.tvMain.text =  finalResult.replace(".0","")
        }else{
            binding.tvMain.text = finalResult
        }
        number1 = ""
        number2 = ""
        number1 = result.toString()
        equalsOn = true
        }
    }