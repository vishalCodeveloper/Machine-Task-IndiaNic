package com.example.machine_task_indianic.ui.authentication.fragment

import `in`.aabhasjindal.otptextview.OTPListener
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.FragmentPhoneVerificationBinding
import com.example.machine_task_indianic.pref.SharedPreferencesManagerImpl
import com.example.machine_task_indianic.ui.base.BaseActivity
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.utils.Common
import com.example.machine_task_indianic.utils.hide
import com.example.machine_task_indianic.utils.showMessage
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class PhoneVerificationFragment : BaseFragment<FragmentPhoneVerificationBinding>(),
    View.OnClickListener {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManagerImpl

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var phoneNumber: String? = null
    private lateinit var verification: String
    private var otpCode: String? = null
    private var otpCountDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = PhoneVerificationFragmentArgs.fromBundle(
                it
            )
            phoneNumber = args.mobileNumber
        }

        initViews()
        initClickListener()
        initCallbacks()
        sendVerificationCode()
    }

    private fun initViews() {
        otpCountDownTimer = object : CountDownTimer(2000 * 60, Common.COUNTDOWN_INTERVAL) {
            override fun onFinish() {
                mViewBinding.textViewTimerCount.hide()
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                mViewBinding.textViewTimerCount.text = "$minutes:$seconds"
            }
        }
        otpCountDownTimer?.start()
    }

    private fun initClickListener() {
        mViewBinding.buttonVerify.setOnClickListener(this)
        mViewBinding.otpView.otpListener = object : OTPListener {
            override fun onInteractionListener() {
            }

            override fun onOTPComplete(otp: String) {
                otpCode = otp
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonVerify -> {
                otpCode?.let {
                    (requireActivity() as BaseActivity<*>).showDialog()
                    callLogin()
                }
            }
        }
    }

    private fun callLogin() {
        val credential = PhoneAuthProvider.getCredential(verification, otpCode ?: "")
        signInWithPhoneAuthCredential(credential)
    }

    private fun initCallbacks() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                requireActivity().runOnUiThread {
                    signInWithPhoneAuthCredential(credential)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        requireContext().showMessage(e.message ?: "")
                    }
                    is FirebaseTooManyRequestsException -> {
                        requireContext().showMessage(e.message ?: "")
                    }
                    else -> {
                        requireContext().showMessage(e.message ?: "")
                    }
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                verification = verificationId
            }
        }
    }

    private fun sendVerificationCode() {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber ?: "")
            .setTimeout(60, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                (requireActivity() as BaseActivity<*>).dismissDialog()
                if (task.isSuccessful) {
                    sharedPreferencesManager.set(Common.IS_LOGIN, true)
                    findNavController().navigate(R.id.action_splashFragment_to_homeActivity)
                    activity?.finish()
                } else {
                    requireContext().showMessage(resources.getString(R.string.something_went_wrong))
                }
            }
    }

    override fun getViewBinding(): FragmentPhoneVerificationBinding =
        FragmentPhoneVerificationBinding.inflate(layoutInflater)
}