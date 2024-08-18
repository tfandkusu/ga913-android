package co.kyash.funds.bank

import com.tfandkusu.ga913android.presentation.MyBaseViewModel
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Effect
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.Event
import com.tfandkusu.ga913android.ui.detail.LandmarkDetailViewModel.State

interface BankListViewModel :
    MyBaseViewModel<
        Event,
        State,
        Effect,
        > {
    sealed class Event

    sealed class State

    sealed class Effect
}
