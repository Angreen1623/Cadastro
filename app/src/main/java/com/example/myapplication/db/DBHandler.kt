package com.example.myapplication.db;

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.model.UserModel

class DBHandler
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase){

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + NEIGHBORHOOD_COL + " TEXT,"
                + ZIP_COL + " TEXT,"
                + CITY_COL + " TEXT,"
                + STATE_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + CELLPHONE_COL + " TEXT)")

        db.execSQL(query)
    }

    fun addNewUser(
        userNome: String?,
        userEndereco: String?,
        userBairro: String?,
        userCep: String?,
        userCidade: String?,
        userEstado: String?,
        userTelefone: String?,
        userCelular: String?
    ){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL, userNome)
        values.put(ADDRESS_COL, userEndereco)
        values.put(NEIGHBORHOOD_COL, userBairro)
        values.put(ZIP_COL, userCep)
        values.put(CITY_COL, userCidade)
        values.put(STATE_COL, userEstado)
        values.put(PHONE_COL, userTelefone)
        values.put(CELLPHONE_COL, userCelular)

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)

    }

    companion object{

        private const val DB_NAME = "coursedb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "myusers"
        private const val ID_COL = "id"
        private const val NAME_COL = "nome"
        private const val ADDRESS_COL = "endereco"
        private const val NEIGHBORHOOD_COL = "bairro"
        private const val ZIP_COL= "cep"
        private const val CITY_COL = "cidade"
        private const val STATE_COL = "estado"
        private const val PHONE_COL = "telefone"
        private const val CELLPHONE_COL = "celular"


    }

}

