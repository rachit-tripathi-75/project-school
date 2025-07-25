package com.example.schoolapp.activities

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.schoolapp.R
import com.example.schoolapp.classes.ApiClient
import com.example.schoolapp.classes.PrefsManager
import com.example.schoolapp.databinding.ActivityLeaveRequestBinding
import com.example.schoolapp.fragments.LeaveRequestFragment
import com.example.schoolapp.fragments.LeaveRequestListFragment
import com.example.schoolapp.responses.GetStudentLeaveRequestResponse
import com.example.schoolapp.viewmodels.LeaveRequestListViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import kotlin.getValue

class LeaveRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaveRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLeaveRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialisers()
        listeners()
    }
    private fun initialisers() {
        // setting the leave request fragment, by default.......
        setInitialFragment(LeaveRequestFragment())
        changeBtnBackground(true)
    }

    private fun listeners() {
        binding.btnLeaveRequest.setOnClickListener {
            setFragment(LeaveRequestFragment(), false)
            changeBtnBackground(true)

        }
        binding.btnLeaveRequestList.setOnClickListener {
            setFragment(LeaveRequestListFragment(), true)
            changeBtnBackground(false)
        }

        binding.ivBack.setOnClickListener{
            onBackPressed();
        }

    }


    private fun setInitialFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun setFragment(fragment: Fragment, isGoingRight: Boolean) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (isGoingRight) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        }
        fragmentTransaction.replace(R.id.flContainer, fragment)
        fragmentTransaction.commit()
    }

    private fun changeBtnBackground(flag: Boolean) {
        if (flag) { // first button is selected.......
            binding.btnLeaveRequest.background = ContextCompat.getDrawable(this, R.drawable.rounded_button_purple)
            binding.btnLeaveRequest.setTextColor(Color.WHITE)
            binding.btnLeaveRequestList.background = ContextCompat.getDrawable(this, R.drawable.rounded_button_outline)
            binding.btnLeaveRequestList.setTextColor(Color.BLACK)
        } else { // second button is selected.......
            binding.btnLeaveRequestList.background = ContextCompat.getDrawable(this, R.drawable.rounded_button_purple)
            binding.btnLeaveRequestList.setTextColor(Color.WHITE)
            binding.btnLeaveRequest.background = ContextCompat.getDrawable(this, R.drawable.rounded_button_outline)
            binding.btnLeaveRequest.setTextColor(Color.BLACK)
        }
    }
}