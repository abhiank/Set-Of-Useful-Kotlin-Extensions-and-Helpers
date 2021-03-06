package com.crazylegend.rxbindings

import android.widget.TextView
import com.crazylegend.rx.mainThreadScheduler
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit


/**
 * Created by crazy on 3/18/20 to long live and prosper !
 */


fun TextView.textChanges(debounce: Long = 300L, debounceTime: TimeUnit = TimeUnit.MILLISECONDS, skipInitialValue: Boolean = true,
                         compositeDisposable: CompositeDisposable,
                         callback: (text: String) -> Unit = {}) {
    val changes = textChanges()
    if (skipInitialValue) {
        changes.skipInitialValue()
    }

    changes.debounce(debounce, debounceTime)
            .map { it.toString() }

            .observeOn(mainThreadScheduler)
            .subscribe({
                callback(it.toString())
            }, {
                it.printStackTrace()
            }).addTo(compositeDisposable)
}


