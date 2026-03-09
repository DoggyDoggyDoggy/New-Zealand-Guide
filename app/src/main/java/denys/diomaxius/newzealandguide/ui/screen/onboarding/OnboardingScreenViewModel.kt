package denys.diomaxius.newzealandguide.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.newzealandguide.domain.usecase.onboarding.SaveOnboardingStatusUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    private val saveOnboardingStatusUseCase: SaveOnboardingStatusUseCase
) : ViewModel() {
    fun saveOnboardingStatus() = viewModelScope.launch {
        saveOnboardingStatusUseCase()
    }
}