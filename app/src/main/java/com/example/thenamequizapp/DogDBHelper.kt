package com.example.thenamequizapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.util.Log

import java.util.ArrayList

    class DogDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase?) {
            var CREATE_DOG_TABLE: String = "CREATE TABLE " + TABLE_NAME + "("+
                    KEY_ID + "INTEGER PRIMARY KEY, " +
                    KEY_DOG_NAME + " TEXT, " +
                    KEY_DOG_IMG + " TEXT" + ");"

            db?.execSQL(CREATE_DOG_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }

        fun createDog(dog: Dog){
            var db: SQLiteDatabase = writableDatabase

            //hashmap of SQL/Dog values
            var values = ContentValues()

            values.put(KEY_DOG_NAME, dog.name)
            values.put(KEY_DOG_IMG, dog.img)

            //Add to DB
            db.insert(TABLE_NAME, null, values)

            //log entry for debug
            Log.d("DATA INSERTED", "SUCCESS")

            db.close()
        }

        fun readADog(id: Int): Dog{

            var db: SQLiteDatabase = readableDatabase

            var cursor: Cursor = db.query(
                TABLE_NAME, arrayOf(
                    KEY_ID,
                    KEY_DOG_NAME,
                    KEY_DOG_IMG
                ), KEY_ID + "=?", arrayOf(id.toString()),
                null, null, null, null
                )
            var dog = Dog()

            if(cursor != null){
                cursor.moveToFirst()

                dog.name = cursor.getString(cursor.getColumnIndex(KEY_DOG_NAME))
                dog.img = cursor.getString(cursor.getColumnIndex(KEY_DOG_IMG))
            }
            cursor.close()

            return dog
        }

        //Read all dog in DB
        fun readDogs(): ArrayList<Dog> {
            var db: SQLiteDatabase = readableDatabase
            var list: ArrayList<Dog> = ArrayList()

            //Select all dog from DB
            var selectAll: String = "SELECT * FROM " + TABLE_NAME

            var cursor: Cursor = db.rawQuery(selectAll, null)

            //Loop through all dogs
                if (cursor!=null && cursor.moveToFirst()) {
                    do {
                        var dog = Dog()

                        dog.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                        dog.name = cursor.getString(cursor.getColumnIndex(KEY_DOG_NAME))
                        dog.img = cursor.getString(cursor.getColumnIndex(KEY_DOG_IMG))

                        list.add(dog)
                    } while (cursor.moveToNext())
                }
            cursor.close()

            return list
        }

        //Delete
        fun deleteDog(id: Int) {
            var db: SQLiteDatabase = writableDatabase
            db.delete(TABLE_NAME, KEY_ID + "=?", arrayOf(id.toString()))
            db.close()
        }

        //Returns number of Dog in DB
        fun getDogsCount(): Int {
            var db: SQLiteDatabase = readableDatabase
            var countQuery: String = "SELECT * FROM " + TABLE_NAME
            var cursor: Cursor = db.rawQuery(countQuery, null)
            return cursor.count
        }

    }