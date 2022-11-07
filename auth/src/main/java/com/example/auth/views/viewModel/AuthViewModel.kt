package com.example.auth.views.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.NetworkCall
import com.example.data.model.auth_model.Signup
import com.example.data.model.auth_model.login.LoginUser
import com.example.data.model.auth_model.response.LoginResponse
import com.example.data.model.auth_model.response.SignupResponse
import com.example.network.service.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: ProductRepository) : ViewModel() {


    private val _liveSignUp = MutableLiveData<NetworkCall<SignupResponse>>()
    val liveSignUp : LiveData<com.example.common.NetworkCall<SignupResponse>>
    get() =  _liveSignUp

    private val _livelogin = MutableLiveData<com.example.common.NetworkCall<LoginResponse>>()
    val livelogin : LiveData<com.example.common.NetworkCall<LoginResponse>>
    get() =  _livelogin

    fun signUpUser(signup: Signup){
        _liveSignUp.value = com.example.common.NetworkCall.Loading()
        viewModelScope.launch {
            try{
                val response = authRepository.signupUser(signup)

                when{
                    response.isSuccessful ->{
                        _liveSignUp.value =   com.example.common.NetworkCall.Success(response.body()!!)
                    }
                    response.code() == 404 || response.code() == 400 || response.code()== 403 || response.code()== 500|| response.code()== 503->{
                        _liveSignUp.value =  com.example.common.NetworkCall.Error("An Error Occurred")
                    }
                }
            }catch (e : Exception){
                _liveSignUp.value = com.example.common.NetworkCall.Error("No Internet Connection")

            }
        }
    }

    fun loginUser(loginuser: LoginUser){
        _liveSignUp.value = com.example.common.NetworkCall.Loading()
        viewModelScope.launch {
            try{
                val response = authRepository.loginUser(loginuser)
                when{
                    response.isSuccessful ->{
                        _livelogin.value =   com.example.common.NetworkCall.Success(response.body()!!)
                    }
                    response.code() == 404 || response.code() == 400 || response.code()== 403 ||response.code() == 401->{
                        _livelogin.value =  com.example.common.NetworkCall.Error("An Error Occurred")
                    }
                }
            }catch (e : Exception){
                _livelogin.value = com.example.common.NetworkCall.Error("No Internet Connection")

            }
        }
    }

}