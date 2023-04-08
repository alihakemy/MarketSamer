package com.hakemy.marketsamer.utils

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hakemy.marketsamer.base.BaseApp
import com.hakemy.marketsamer.ui.register.serviceModel.response.User
import com.hakemy.marketsamer.ui.register.serviceModel.response.login.LoginResponse


object SharePreferenceManager {
    var sharedPreferences: SharedPreferences? =
        BaseApp.context?.getSharedPreferences("MarketSharedPreference", MODE_PRIVATE)

    fun storeIsShowOnboarding(isPassOnboarding: Boolean) {
        sharedPreferences?.edit()?.putBoolean("ShowOnboarding", isPassOnboarding)?.apply()

    }

    fun getIsShowOnboarding(): Boolean {
        return sharedPreferences?.getBoolean("ShowOnboarding", false)  ?: false

    }



    fun storeIsVerified(Verified: Boolean) {
        sharedPreferences?.edit()?.putBoolean("Verified", Verified)?.apply()

    }

    fun getIsVerified(): Boolean {
       return sharedPreferences?.getBoolean("Verified", false) == true

    }

    private fun <T> modelToJson(t:T): String? {
        val gson = Gson()
       return gson.toJson(t)
    }

    fun storeVerificationCode (verificationCode:String){
        sharedPreferences?.edit()?.putString("verificationCode",verificationCode)?.apply()

    }
    fun getVerificationCode(): String? {
        return sharedPreferences?.getString("verificationCode", "")

    }
    fun storeToken (token:String){
        sharedPreferences?.edit()?.putString("token",token)?.apply()

    }
    fun getToken (): String? {
        return sharedPreferences?.getString("token", "")

    }
    fun storeUserObject(user: User){

        sharedPreferences?.edit()?.putString("userData", modelToJson(user))?.apply()
    }

    fun getUserObject(): ResultState<User> {
        return try {
            val gson = Gson()
            val json: String = sharedPreferences?.getString("userData","").toString()

            ResultState.Success(gson.fromJson(json, User::class.java))
        }catch (e:java.lang.Exception){
            ResultState.Error(e.localizedMessage)
        }

    }

    fun getUser(): User {
        val gson = Gson()
        val json: String = sharedPreferences?.getString("userData","").toString()
       return gson.fromJson(json, User::class.java)

    }




    fun storelat (token:String){
        sharedPreferences?.edit()?.putString("lat",token)?.apply()

    }
    fun getlat (): String? {
        return sharedPreferences?.getString("lat", "")

    }


    fun storelng (token:String){
        sharedPreferences?.edit()?.putString("lng",token)?.apply()

    }
    fun getlng (): String? {
        return sharedPreferences?.getString("lng", "")

    }




    fun storeaddress(token:String){
        sharedPreferences?.edit()?.putString("address",token)?.apply()

    }
    fun getaddress (): String? {
        return sharedPreferences?.getString("address", "")

    }


    fun storeLang (lang:String){
        sharedPreferences?.edit()?.putString("Lang",lang)?.apply()

    }
    fun getLang(): String? {
        return sharedPreferences?.getString("Lang", "ar")

    }




}