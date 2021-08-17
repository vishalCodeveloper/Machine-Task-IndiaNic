package com.example.machine_task_indianic.ui.base

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.machine_task_indianic.pref.SharedPreferencesManagerImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewBinding = getViewBinding()
    }

    abstract fun getViewBinding(): VB

    fun getAddress(latitude:Double,longitude:Double):String {
        val addresses: List<Address>
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        addresses = geocoder.getFromLocation(latitude,longitude, 1)

        val address = ""
        addresses[0].getAddressLine(0)?.let {
            return  it
        }
        return address
    }
}