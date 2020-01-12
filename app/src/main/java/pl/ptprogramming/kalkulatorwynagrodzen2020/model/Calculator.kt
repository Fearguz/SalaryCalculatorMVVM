package pl.ptprogramming.kalkulatorwynagrodzen2020.model

interface Calculator
{
    data class Result(val net: Double = .0, val socialSecurityFee: Double = .0, val tax: Double = .0)

    fun calculate(gross: Double): Result
}