package com.example.newdiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.newdiceroller.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rollDice: ( ) -> Int = { Random().nextInt(6) + 1 }
    private lateinit var diceSelected: Triple<ImageView,Int,Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        diceSelected = Triple(binding.diceD6,R.drawable.ic_d6_white,R.drawable.ic_d6_color)
        setListener()
    }

    private fun setListener(){
        binding.apply {
            val clickableView: List<Triple<ImageView,Int,Int>> =
            listOf(
                Triple(diceD4,R.drawable.ic_d4_white,R.drawable.ic_d4_color),
                Triple(diceD6,R.drawable.ic_d6_white,R.drawable.ic_d6_color),
                Triple(diceD8,R.drawable.ic_d8_white,R.drawable.ic_d8_color),
                Triple(diceD12,R.drawable.ic_d12_white,R.drawable.ic_d12_color),
                Triple(diceD20,R.drawable.ic_d20_white,R.drawable.ic_d20_color),
            )

            clickableView.forEach{
                    item : Triple<ImageView,Int,Int> -> item.first.setOnClickListener{ selectDice(item) }
            }

            rollButton.setOnClickListener{
                result.text = ""
                Handler().postDelayed({ result.text = rollDice().toString() }, 500)
            }
        }

    }

    private fun selectDice(dice: Triple<ImageView,Int,Int>){
        diceSelected.first.setImageResource(diceSelected.second)
        diceSelected = dice
        diceSelected.first.setImageResource(diceSelected.third)
        when (dice.first.id) {
            R.id.dice_d4  -> {
                rollDice = { Random().nextInt(4) + 1 }
            }
            R.id.dice_d6  -> {
                rollDice = { Random().nextInt(6) + 1 }
            }
            R.id.dice_d8  -> {
                rollDice = { Random().nextInt(8) + 1 }
            }
            R.id.dice_d12 -> {
                rollDice = { Random().nextInt(12) + 1 }
            }
            R.id.dice_d20 -> {
                rollDice = { Random().nextInt(20) + 1 }
            }
            else -> rollDice = { Random().nextInt(6) + 1 }
        }
    }
}