package com.example.lotteria.ui.detail

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.lotteria.R
import com.example.lotteria.data.Image
import com.example.lotteria.databinding.ActivityDetailPageBinding

class DetailPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFF"))
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.arrow_back)
            actionBar.setBackgroundDrawable(colorDrawable)
        }
        val data = intent?.getParcelableExtra<Image>(DATA)
        if (data != null) {
            val bitmap = BitmapFactory.decodeFile(data.picture)
            binding.gambarDetail.setImageBitmap(bitmap)
            actionBar?.setTitle(Html.fromHtml("<font color=\"black\">" + data.title + "</font>"))
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_page_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                Toast.makeText(this, R.string.not_working_feature, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.delete -> {
                Toast.makeText(this, R.string.not_working_feature, Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> {
                this.finish()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    companion object {
        const val DATA = "data"
    }
}