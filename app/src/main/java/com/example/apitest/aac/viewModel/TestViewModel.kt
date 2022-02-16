package com.example.apitest.aac.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitest.aac.IEvent
import com.example.apitest.aac.MutableEventFlow
import com.example.apitest.aac.Resource
import com.example.apitest.aac.asEventFlow
import com.example.apitest.aac.repository.LoginRepository
import com.example.apitest.network.request.GameRequest
import com.example.apitest.network.request.LoginRequest
import com.example.apitest.network.response.AssignmentListResponse
import com.example.apitest.network.response.GameResponse
import com.example.apitest.network.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    val repository: LoginRepository
): ViewModel() {

    suspend fun userLogin(loginRequest: LoginRequest) {
        event(Event.UserLogin(repository.userLogin(loginRequest)))
    }

    suspend fun assignmentInfo(token: String){
        event(Event.AssignmentInfo(repository.getAssignmentList(token)))
    }

    suspend fun getGame(token: String, gameRequest: GameRequest){
        event(Event.GetGame(repository.getGame(token, gameRequest)))
    }



    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event){
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event: IEvent(){
        data class UserLogin(val response: Resource<LoginResponse>): Event()
        data class AssignmentInfo(val response: Resource<AssignmentListResponse>): Event()
        data class GetGame(val response: Resource<GameResponse>): Event()
    }
}