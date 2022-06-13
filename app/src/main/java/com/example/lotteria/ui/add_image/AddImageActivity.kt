package com.example.lotteria.ui.add_image

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lotteria.R
import com.example.lotteria.databinding.ActivityAddImageBinding
import com.example.lotteria.helper.createFile
import com.example.lotteria.helper.rotateBitmap
import com.example.lotteria.helper.uriToFile
import java.io.File
import java.io.FileOutputStream

class AddImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddImageBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val myFile = intent.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true) as Boolean

        getFile = myFile
        val result = rotateBitmap(
            BitmapFactory.decodeFile(getFile?.path),
            isBackCamera
        )

        binding.previewImageView.setImageBitmap(result)

        binding.apply {
            saveButton.setOnClickListener {
                val title = edtTitle.text.toString()
                val tag = edtTag.text.toString()
                when {
                    title.contains("/") -> {
                        textInputLayout.error = "jangan gunakan tanda /"
                    }
                    title.contains(".") -> {
                        textInputLayout.error = "jangan gunakan tanda ."
                    }
                    title.isEmpty() -> {
                        textInputLayout.error = "Masukkan judul gambar"
                    }
                    else -> {
                        val filePath = File("/storage/emulated/0/Android/media/com.example.lotteria/image/"
                                + title + ".jpg")
                        if (filePath.exists()) {
                            filePath.delete()
                        }
                        val out = FileOutputStream(filePath)
                        result.compress(Bitmap.CompressFormat.JPEG, 100, out)
                        out.flush()
                        out.close()
                        Toast.makeText(this@AddImageActivity, "Success saving image", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@AddImageActivity)

            getFile = myFile

            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}