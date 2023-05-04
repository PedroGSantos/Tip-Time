package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root

        setContentView(view)

        viewBinding.calculate.setOnClickListener {
            viewBinding.tipAmount.text = getCurrencyMoney(calculateTip());
        }
    }

    private fun calculateTip(): Double {
        val cost = viewBinding.costOfService.text.toString().toDoubleOrNull()

        if (cost == null){
            viewBinding.tipAmount.text = " "
            return 0.0;
        }
        val review = viewBinding.tipOptions.checkedRadioButtonId

        val percent = when(review) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }

        return roundUp(cost * percent);
    }

    private fun roundUp(tipNotRounded: Double): Double {
        if (viewBinding.roundUp.isChecked)
                return kotlin.math.ceil(tipNotRounded)
        return tipNotRounded;
    }

    private fun getCurrencyMoney(tipAmount: Double) = getString(R.string.amount, NumberFormat.getCurrencyInstance().format(tipAmount));
}