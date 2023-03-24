package com.example.mycbseguide

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycbseguide.ApiClient.ApiClient
import com.example.mycbseguide.Model.ErrorModel
import com.example.mycbseguide.Model.GetCategoryModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel:ViewModel() {
    var CategoryResponse = MutableLiveData<GetCategoryModel>()
    var errorResponse = MutableLiveData<String>()

    fun requestCategory(
        context: Context,
        progress: ProgressBar
    ): MutableLiveData<GetCategoryModel> {
        progress.visibility = View.VISIBLE


        try {
            progress.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val apiService = ApiClient.apiClient().create(ApiClient.ApiInterface::class.java)
                apiService.getcategories()
                    .enqueue(object : Callback<GetCategoryModel> {
                        override fun onFailure(call: Call<GetCategoryModel>, t: Throwable) {
                            progress.visibility = View.GONE
                            Log.i("errrrrr", call.toString())
                            Log.i("errrr", t.toString())

                            Toast.makeText(
                                context,
                                t.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<GetCategoryModel>,
                            response: Response<GetCategoryModel>
                        ) {
                            progress.visibility = View.GONE

                            if (response.code() == 200) {

                                CategoryResponse.value = response.body()
                            } else {
                                Log.i("errorrrrr", response.errorBody().toString())
                                val error = Gson().fromJson(
                                    response.errorBody()!!.charStream(),
                                    ErrorModel::class.java
                                )
                                errorResponse.value = error.message

                                errorMessage()
                            }

                        }
                    })


            }
        } catch (e: Exception) {
            progress.visibility = View.GONE

        }

        return CategoryResponse
    }
    fun errorMessage(): MutableLiveData<String>? {
        return errorResponse

    }
}