package com.adhibuchori.finalsubmission_simpleandroidapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhibuchori.finalsubmission_simpleandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val setActionBar = supportActionBar
        setActionBar?.title = "Home"

        binding.rvCharacters.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    private fun getListHeroes(): ArrayList<Character> {
        val dataCharacterName = resources.getStringArray(R.array.data_character_name)
        val dataCharacterCategory = resources.getStringArray(R.array.data_character_category)
        val dataCharacterImage = resources.getStringArray(R.array.data_character_image)
        val dataCharacterInformation = resources.getStringArray(R.array.data_character_information)
        val listCharacter = ArrayList<Character>()
        for (i in dataCharacterName.indices) {
            val character = Character(
                dataCharacterName[i],
                dataCharacterCategory[i],
                dataCharacterImage[i],
                dataCharacterInformation[i]
            )
            listCharacter.add(character)
        }
        return listCharacter
    }

    private fun moveDataToDetailActivity(data: Character) {
        val intentToDetailActivity = Intent(this@MainActivity, DetailActivity::class.java)
        intentToDetailActivity.putExtra(DetailActivity.EXTRA_CHARACTER, data)

        startActivity(intentToDetailActivity)
    }

    private fun showSelectedCharacter(character: Character) {
        Toast.makeText(this, character.name + " Selected", Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        val listCharacterAdapter = ListCharacterAdapter(list)
        binding.rvCharacters.adapter = listCharacterAdapter

        listCharacterAdapter.setOnItemClickCallback(object :
            ListCharacterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Character) {
                showSelectedCharacter(data)
                moveDataToDetailActivity(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about_me -> {
                val intentToAboutActivity = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentToAboutActivity)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}