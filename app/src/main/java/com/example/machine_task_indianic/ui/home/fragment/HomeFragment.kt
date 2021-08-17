package com.example.machine_task_indianic.ui.home.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.machine_task_indianic.R
import com.example.machine_task_indianic.databinding.FragmentHomeBinding
import com.example.machine_task_indianic.ui.base.BaseFragment
import com.example.machine_task_indianic.ui.viewModel.HomeViewModel
import com.example.machine_task_indianic.utils.GPSUtils
import com.example.machine_task_indianic.utils.showMessage
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener, OnMapReadyCallback {
    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        GPSUtils(requireActivity()).turnOnGPS()
        checkLocationPermission()
        initClickListener()
    }

    private fun initClickListener() {
      mViewBinding.buttonChoose.setOnClickListener(this)
    }

    private fun checkLocationPermission() {
        permissionsResultLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    private val permissionsResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            if (!it.value) {
                openPermissionSetting()
                return@registerForActivityResult
            } else {
                getCurrentLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                // getting the last known or current location
                location?.let {
                    currentLocation = it
                    val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                }
            }
            .addOnFailureListener {
                requireContext().showMessage("Failed on getting current location")
            }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
           R.id.buttonChoose->{
               if(::currentLocation.isInitialized){
                   currentLocation.let {
                       homeViewModel.latitude = it.latitude
                       homeViewModel.longitude = it.longitude
                       homeViewModel.address = getAddress(it.latitude,it.longitude)
                       homeViewModel.isDataAdded = false
                       findNavController().navigate(R.id.action_home_fragment_to_weather_info_fragment)
                   }
               }
               else{

               }
           }
        }
    }

    private fun openPermissionSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions =  MarkerOptions().position(latLng).title(getAddress(latLng.latitude,latLng.longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_picker)).draggable(true)
        googleMap?.addMarker(markerOptions)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))

        googleMap?.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(arg0: Marker) {

            }

            override fun onMarkerDragEnd(arg0: Marker) {
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arg0.position, 10f))
                markerOptions.title(getAddress(arg0.position.latitude,arg0.position.longitude))
            }

            override fun onMarkerDrag(arg0: Marker?) {
                val message = arg0!!.position.latitude.toString() + "" + arg0.position.longitude.toString()
            }
        })
    }

    override fun getViewBinding(): FragmentHomeBinding  =
        FragmentHomeBinding.inflate(layoutInflater)

}