package com.example.machine_task_indianic.ui.authentication.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.ActivityAuthenticationBinding
import com.example.machine_task_indianic.databinding.FragmentSplashBinding
import com.example.machine_task_indianic.pref.SharedPreferencesManager
import com.example.machine_task_indianic.pref.SharedPreferencesManagerImpl
import com.example.machine_task_indianic.ui.base.BaseActivity
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.utils.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManagerImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            val isLogin = sharedPreferencesManager.getBoolean(Common.IS_LOGIN,false)
            if(isLogin){
                 findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
                 activity?.finish()
            }
            else{
                findNavController().navigate(R.id.action_SplashFragment_to_loginFragment)
            }

        }, Common.SPLASH_TIME)
    }

    override fun getViewBinding(): FragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)
}