package pl.ptprogramming.kalkulatorwynagrodzen2020.viewModels

import androidx.databinding.ObservableField
import org.koin.core.KoinComponent
import org.koin.core.inject
import pl.ptprogramming.kalkulatorwynagrodzen2020.model.DefaultTaxLevel
import pl.ptprogramming.kalkulatorwynagrodzen2020.model.EmploymentNetCalculator
import pl.ptprogramming.kalkulatorwynagrodzen2020.printable

class EmploymentCalculatorViewModel : ObservableViewModel(), KoinComponent
{
    private val calculator: EmploymentNetCalculator by inject()
    var taxLevel = DefaultTaxLevel

    val grossResult = ObservableField<String>()
    val netResult = ObservableField<String>()
    val socialSecurityResult = ObservableField<String>()
    val taxResult = ObservableField<String>()

    fun calculateNet(gross: Double) {
        val result = with(calculator) {
            taxLevel = this@EmploymentCalculatorViewModel.taxLevel
            calculate(gross)
        }
        netResult.set(result.net.printable())
        socialSecurityResult.set(result.socialSecurityFee.printable())
        taxResult.set(result.tax.printable())

        notifyChange()
    }
}
