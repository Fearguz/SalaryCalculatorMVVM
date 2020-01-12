package pl.ptprogramming.kalkulatorwynagrodzen2020.viewModels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

open class ObservableViewModel : ViewModel(), Observable
{
    private val callbacks = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    fun notifyChange() = callbacks.notifyCallbacks(this, 0, null)
}