package pl.ptprogramming.kalkulatorwynagrodzen2020

import android.app.Application
import org.koin.core.context.startKoin
import pl.ptprogramming.kalkulatorwynagrodzen2020.di.calculatorModule

class CalculatorApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(calculatorModule)
        }
    }
}