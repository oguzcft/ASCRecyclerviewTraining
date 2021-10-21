package com.ouz.ascrecyclerview02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ouz.ascrecyclerview02.databinding.ActivityDetailBinding
import com.ouz.ascrecyclerview02.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var getUser=intent.getSerializableExtra("user") as DataType
        binding.imageViewImage.setImageResource(getUser.image)
        binding.textViewNo.text=getUser.telNo
        binding.textViewUname.text=getUser.name
    }

    override fun onBackPressed() {
        super.onBackPressed()
       // startActivity(Intent(DetailActivity@this,MainActivity::class.java))
    }
}