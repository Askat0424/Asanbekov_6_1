package com.example.asanbekov_6_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.asanbekov_6_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.etWords.setText(result.data?.getStringExtra(KEY_FOR_TEXT))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.btnNext.setOnClickListener {
            if (binding.etWords.text.isEmpty()) {
                Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(KEY_FOR_TEXT, binding.etWords.text.toString())
                activityLauncher.launch(intent)
            }
        }
    }

    companion object {
        const val KEY_FOR_TEXT = "text_key"
    }
}