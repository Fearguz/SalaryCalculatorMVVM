package pl.ptprogramming.kalkulatorwynagrodzen2020.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_employment_calculator.*

import pl.ptprogramming.kalkulatorwynagrodzen2020.R
import pl.ptprogramming.kalkulatorwynagrodzen2020.viewModels.EmploymentCalculatorViewModel
import pl.ptprogramming.kalkulatorwynagrodzen2020.databinding.ActivityEmploymentCalculatorBinding
import pl.ptprogramming.kalkulatorwynagrodzen2020.toDoubleOrNull

class EmploymentCalculatorActivity : AppCompatActivity() {

    private val calculatorViewModel by lazy {
        ViewModelProviders.of(this)[EmploymentCalculatorViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEmploymentCalculatorBinding = DataBindingUtil.setContentView(this, R.layout.activity_employment_calculator)
        binding.viewmodel = calculatorViewModel
        binding.lifecycleOwner = this

        grossEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val gross = p0.toDoubleOrNull()
                updateValues(gross)
            }
        })

        taxLevelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) { }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                calculatorViewModel.taxLevel = resources.getStringArray(R.array.taxLevels)[p2].toDouble()
                val gross = grossEditText.text.toDoubleOrNull()
                updateValues(gross)
            }
        }
    }

    private fun updateValues(gross: Double?) = gross?.let {
        calculatorViewModel.calculateNet(gross)
    }
}
