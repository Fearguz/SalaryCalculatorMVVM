package pl.ptprogramming.kalkulatorwynagrodzen2020.model

const val MinSalaryGross = 2600
const val TaxFreeAllowance = 43.76
const val DefaultTaxLevel = 0.17
const val CostOfGettingIncome: Double = 112.35

open class EmploymentNetCalculator : Calculator
{
    var taxLevel: Double = .0

    override fun calculate(gross: Double): Calculator.Result {
        if (isLesserThanMinimumSalary(gross))
            return Calculator.Result()

        val socialFees = socialSecurityFee(gross)
        val medicalInsuranceBase = medicalInsuranceBase(gross, socialFees)
        val medicalInsuranceFee = medicalInsuranceFee(medicalInsuranceBase)
        val medicalInsuranceAllowance = medicalInsuranceAllowance(medicalInsuranceBase)
        val taxableBase = taxableBase(gross, socialFees, CostOfGettingIncome)
        val tax = taxableBase * taxLevel - TaxFreeAllowance - medicalInsuranceAllowance
        val net = gross - socialFees - medicalInsuranceFee - tax
        return Calculator.Result(net, socialFees, tax)
    }

    private fun isLesserThanMinimumSalary(gross: Double) = gross < MinSalaryGross

    private fun socialSecurityFee(gross: Double) = 0.0976 * gross + 0.015 * gross + 0.0245 * gross

    private fun medicalInsuranceBase(gross: Double, socialFees: Double) = gross - socialFees

    private fun medicalInsuranceFee(base: Double) = 0.09 * base

    private fun medicalInsuranceAllowance(base: Double) = 0.0775 * base

    private fun taxableBase(gross: Double, socialFees: Double, costOfGettingIncome: Double)
            = gross - socialFees - costOfGettingIncome
}