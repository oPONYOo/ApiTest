package com.example.apitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.apitest.aac.IEvent
import com.example.apitest.aac.Resource
import com.example.apitest.aac.viewModel.TestViewModel
import com.example.apitest.databinding.ActivityMainBinding
import com.example.apitest.extensions.repeatOnStared
import com.example.apitest.network.request.GameRequest
import com.example.apitest.network.request.LoginRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IEvent.Handler {

    private lateinit var binding: ActivityMainBinding
    private val testViewModel: TestViewModel by viewModels()
    private lateinit var access: String
    private var gameRequest: GameRequest? = null
    lateinit var uuid: String
    lateinit var key: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            lifecycleOwner = this@MainActivity
        }

        binding.loginBtn.setOnClickListener {
            val id = binding.userIdTxt.text.toString()
            val pw = binding.userPwTxt.text.toString()

            lifecycleScope.launch {
                testViewModel.userLogin(
                    LoginRequest(id, pw, "HOSPITAL")
                )
            }
        }

        binding.assignmentBtn.setOnClickListener {
            lifecycleScope.launch {
                testViewModel.assignmentInfo(access)
            }
        }

        binding.gameBtn.setOnClickListener {
            lifecycleScope.launch {
                gameRequest = GameRequest(uuid, key)
                testViewModel.getGame(access, gameRequest!!)
            }
        }

repeatOnStared {
    testViewModel.eventFlow.collect { event -> onHandle(event) }
}
    }



    override suspend fun onHandle(event: IEvent) {
        when(event){
            is TestViewModel.Event.UserLogin -> {
                val it = event.response
                if (it.status == Resource.Status.SUCCESS){
                    val result = it.data!!.result
                    Log.e("access", result!!.access)
                    Log.e("refresh", result.refresh)
                    access = result.access

                }else if(it.status == Resource.Status.ERROR){
                    Log.e("error", "error")
                }
            }

            is TestViewModel.Event.AssignmentInfo -> {
                val it = event.response
                if (it.status == Resource.Status.SUCCESS){
                    val result = it.data!!.result!![0]
                    uuid = result.key!!
                    key = result.units!![0].key

                    Log.e("result", "$result")
                    Log.e("uuid", "$uuid")
                    Log.e("key", "${key}")
                }else if(it.status == Resource.Status.ERROR){
                    Log.e("Aerror", "error")
                }
            }

            is TestViewModel.Event.GetGame -> {
                val it = event.response
                if (it.status == Resource.Status.SUCCESS){
                    val result = it.data!!.result
                    Log.e("result", "$result")
                }else if(it.status == Resource.Status.ERROR){
                    Log.e("Berror", "error")
                }
            }
        }
    }
}