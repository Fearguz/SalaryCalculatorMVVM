package pl.ptprogramming.kalkulatorwynagrodzen2020.di

import org.koin.dsl.module
import pl.ptprogramming.kalkulatorwynagrodzen2020.model.EmploymentNetCalculator

val calculatorModule = module {
    factory { EmploymentNetCalculator() }
}