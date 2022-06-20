package com.assessment.cartapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.assessment.cartapp.databinding.ActivityLoginBinding
import com.assessment.cartapp.utils.Utils

const val USERNAME = "TestUser"
const val PASSWORD = "Test@123"


/*
 * @author Krish
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val etUserName = binding.username as AppCompatEditText
            val etPassword = binding.password as AppCompatEditText
            val userName = etUserName.text?.toString()?.trim()
            val password = etPassword.text?.toString()?.trim()
            if (userName.equals(USERNAME) && password.equals(PASSWORD)) {
                finish()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Utils.showSnack(this, "Invalid username or password");
            }
        }

    }
}
