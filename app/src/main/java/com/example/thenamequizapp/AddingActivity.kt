package com.example.thenamequizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_adding.*


class AddingActivity : AppCompatActivity() {

    var path: String? = null
    var imgId : String? = null

    var dbHandler: DogDBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)

        dbHandler = DogDBHelper(this)

        //checkDB()

        goBackButton.setOnClickListener {

            if(!TextUtils.isEmpty(enter_name.text.toString())){
                var dog = Dog()
                dog.img= imgId
                dog.name = enter_name.getText().toString()

                saveToDB(dog)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_LONG).show()
            }
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
            val imgUri: Uri? = data?.data
            img_add_char.setImageURI(imgUri)
            path = data?.data?.path
            val picturePath: String? = getPath(this.applicationContext, data?.data)
            imgId = picturePath.toString()
            Toast.makeText(this, picturePath, Toast.LENGTH_LONG).show()
            println("The imgID: $imgId")
            println("The uri: $path")


        }
    }
    fun getPath(context: Context, uri: Uri?): String? {
        var result: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = context.getContentResolver().query(uri, proj, null, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val column_index: Int = cursor.getColumnIndexOrThrow(proj[0])
                result = cursor.getString(column_index)
            }
            cursor.close()
        }
        if (result == null) {
            result = "Not found"
        }
        return result
    }



    fun saveToDB(dog: Dog){
        dbHandler!!.createDog(dog)
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
