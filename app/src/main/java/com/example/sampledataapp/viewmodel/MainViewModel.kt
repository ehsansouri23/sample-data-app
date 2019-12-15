package com.example.sampledataapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampledataapp.data.Repository
import com.example.sampledataapp.data.Result
import com.example.sampledataapp.data.model.Address
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _addresses = MutableLiveData<Result<List<Address>>>()
    val addresses: LiveData<Result<List<Address>>> = _addresses

    private val _addAddress = MutableLiveData<Result<Address>>()
    val addAddress: LiveData<Result<Address>> = _addAddress

    fun getAddresses() {
        _addresses.postValue(Result.Loading)
        viewModelScope.launch {
            _addresses.postValue(repository.getAddresses())
        }
    }

    fun addAddress(address: Address) {
        _addAddress.postValue(Result.Loading)
        viewModelScope.launch {
            _addAddress.postValue(repository.addAddress(address))
        }
    }
}