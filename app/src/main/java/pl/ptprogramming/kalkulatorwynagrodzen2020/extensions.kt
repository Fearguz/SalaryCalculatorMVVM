package pl.ptprogramming.kalkulatorwynagrodzen2020

fun CharSequence?.toDoubleOrNull(): Double? = this?.toString().toDoubleOrNull()

fun Double.printable(): String = "%.2f".format(this)
