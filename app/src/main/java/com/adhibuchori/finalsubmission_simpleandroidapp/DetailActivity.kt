package com.adhibuchori.finalsubmission_simpleandroidapp

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adhibuchori.finalsubmission_simpleandroidapp.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_CHARACTER = "extra_character"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setActionBar = supportActionBar
        setActionBar?.title = "Detail Character"
        setActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.actionShare.setOnClickListener(this)

        val character = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_CHARACTER, Character::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CHARACTER)
        }

        if (character != null) {

            Glide.with(this)
                .load(character.image)
                .into(binding.imgItemCharacter)

            with(binding) {
                tvItemName.text = character.name
                tvItemCategory.text = character.category
                tvItemInformation.text = character.information
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        this.onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                Toast.makeText(this,"Character Shared", Toast.LENGTH_SHORT).show()
            }
        }
    }
}