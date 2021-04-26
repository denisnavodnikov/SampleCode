package ru.navodnikov.denis.karagatantour.ui.contacts

import ru.navodnikov.denis.karagatantour.ui.base.FragmentContract
import ru.navodnikov.denis.karagatantour.ui.utils.Intents

class ContactsContract {
    interface ViewModel : FragmentContract.ViewModel {
    }
    interface View : FragmentContract.View {
        fun getOpenIntent(intent: Intents)
    }
}