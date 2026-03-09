package denys.diomaxius.newzealandguide.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.onboarding.GetOnboardingStatusUseCase
import denys.diomaxius.newzealandguide.navigation.NavScreen
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOnboardingStatusUseCase: GetOnboardingStatusUseCase
) : ViewModel() {

    var startDestination = mutableStateOf<String?>(null)

    init {
        viewModelScope.launch {
            getOnboardingStatusUseCase().collect { isCompleted ->
                startDestination.value = if (isCompleted) {
                    NavScreen.Home.route
                } else {
                    NavScreen.Onboarding.route
                }
            }
        }
    }
}