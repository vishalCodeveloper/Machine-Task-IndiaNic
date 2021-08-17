package com.example.machine_task_indianic.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.machine_task_indianic.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var mViewBinding: VB
    private var dialog:ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBinding()
    }

    abstract fun getViewBinding(): VB

    fun showDialog(){
        dialog = ProgressDialog(this)
        dialog?.setMessage(resources.getString(R.string.please_wait))
        dialog?.show()
    }

    fun dismissDialog(){
        dialog?.dismiss()
    }

}