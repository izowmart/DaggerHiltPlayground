package ps.room.daggerhiltplayground.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ps.room.daggerhiltplayground.model.Blog
import ps.room.daggerhiltplayground.repository.MainRepository
import ps.room.daggerhiltplayground.util.DataState

@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private var savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvents -> {
                    mainRepository.getBlog().onEach {
                        _dataState.value = it
                    }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None ->{
                    //Who cares
                }
            }
        }
    }
}

sealed class MainStateEvent {
    object GetBlogEvents : MainStateEvent()
    object None : MainStateEvent()
}