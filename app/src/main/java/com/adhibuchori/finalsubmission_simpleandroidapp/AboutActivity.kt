package com.adhibuchori.finalsubmission_simpleandroidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adhibuchori.finalsubmission_simpleandroidapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionContactMe.setOnClickListener(this)

        val setActionBar = supportActionBar
        setActionBar?.title = "About Me"
        setActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_contact_me -> {
                val email = "adhi.buchori@gmail.com"
                val contactEmailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: $email"))
                startActivity(contactEmailIntent)
            }
        }
    }
}