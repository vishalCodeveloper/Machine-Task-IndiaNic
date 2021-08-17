package com.example.machine_task_indianic.ui.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.FragmentLoginBinding
import com.example.machine_task_indianic.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        mViewBinding.buttonLogin.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonLogin -> {
                callLogin()
            }
        }
    }

    private fun callLogin() {
        val action  = LoginFragmentDirections.actionLoginFragmentToOtpVerificationFragment()
        action.mobileNumber = "+91 ${mViewBinding.editTextMobileNumber.text?.trim().toString()}"
        findNavController().navigate(action)
    }

    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)
}