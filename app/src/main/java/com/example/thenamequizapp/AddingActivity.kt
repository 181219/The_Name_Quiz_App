package com.example.thenamequizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_adding.*

class AddingActivity : AppCompatActivity() {

    var path: String? = null
    var imgId : Int? = 0

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        goBackButton.setOnClickListener {
            var returnInt = this.intent

            returnInt.putExtra("name", enter_name.text)
            returnInt.putExtra("img", imgId)
            setResult(Activity.RESULT_OK, returnInt)
            finish()
        }

        img_add_char.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //Permission denied
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    selectImageGallery()
                    //Permission already granted
                }

            } else {
                // system OS < Marshmallow
                selectImageGallery()
            }
        }
    }

    fun selectImageGallery() {
        val pickImageIntent = Intent(Intent.ACTION_PICK)
        pickImageIntent.type = "image/*"
        startActivityForResult(pickImageIntent, IMAGE_PICK_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            img_add_char.setImageURI(data?.data)
            path = data?.data?.path
            imgId = R.drawable.ic_image_black_24dp

            println("The imgID: $imgId")
            println("The uri: $path")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Pop up result
                    selectImageGallery()
                } else {
                    //Permission denied
                    Toast.makeText(this, "No image added; Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }
}
