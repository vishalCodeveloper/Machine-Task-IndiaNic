package com.example.machine_task_indianic.ui.authentication.activity

import android.os.Bundle
import com.example.machine_task_indianic.databinding.ActivityAuthenticationBinding
import com.example.machine_task_indianic.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun getViewBinding(): ActivityAuthenticationBinding = ActivityAuthenticationBinding.inflate(layoutInflater)
}