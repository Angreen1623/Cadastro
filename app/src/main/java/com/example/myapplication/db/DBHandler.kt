package com.example.myapplication.db;

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplication.model.UserModel

class DBHandler
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase){

        val query = ("CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + "TEXT,"
                + PHONE_COL + "TEXT,"
                + ORIGIN_COL + "TEXT,"
                + OBSERVATION_COL + "TEXT)")

        db.execSQL(query)
    }

    fun addNewUser(
        userName: String?,
        userTelefone: String?,
        userOrigem: String?,
        userObservacoes: String?
    ){
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL, userName)
        values.put(PHONE_COL, userTelefone)
        values.put(ORIGIN_COL, userOrigem)
        values.put(OBSERVATION_COL, userObservacoes)

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
        private const val PHONE_COL = "telefone"
        private const val ORIGIN_COL = "origem"
        private const val OBSERVATION_COL = "descricao"


    }

}

