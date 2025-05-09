package com.example.fragmentinteraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentinteraction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerA.id, FragmentA())
            .replace(binding.fragmentContainerB.id, FragmentB())
            .commit()
    }
}
