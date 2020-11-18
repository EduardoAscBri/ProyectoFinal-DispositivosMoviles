package com.example.proyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables when create the database
        db.execSQL("CREATE TABLE User(idUser INTEGER PRIMARY KEY ASC, Name TEXT, LasstName TEXT, Email TEXT)");
        db.execSQL("CREATE TABLE Account(idAccount INTEGER PRIMARY KEY ASC, idUser INTEGER, Name TEXT, Type TEXT, FOREIGN KEY(idUser) REFERENCES User(idUser))");
        db.execSQL("CREATE TABLE Category(idCategory INTEGER PRIMARY KEY ASC, Name TEXT, Type TEXT, TransactionType TEXT)");
        db.execSQL("CREATE TABLE Transact(idTransaction INTEGER PRIMARY KEY ASC, idOrigAccount INTEGER, idDestAccount INTEGER, idCategory INTEGER, Amount REAL, " +
                "Concept TEXT, Date NUMERIC, LatLocation REAL, LonLocation REAL, IsAutoCharge INTEGER, " +
                "FOREIGN KEY(idOrigAccount) REFERENCES User(idUser), FOREIGN KEY(idDestAccount) REFERENCES User(idUser), FOREIGN KEY(idCategory) REFERENCES Category(idCategory))");
        db.execSQL("CREATE TABLE Charge(idCharge INTEGER PRIMARY KEY ASC, idOrgAccount INTEGER, idDestAccount INTEGER, idCategory INTEGER, Amount REAL, Concept TEXT, Frecuency INTEGER, Date NUMERIC," +
                "FOREIGN KEY(idOrigAccount) REFERENCES User(idUser), FOREIGN KEY(idDestAccount) REFERENCES User(idUser), FOREIGN KEY(idCategory) REFERENCES Category(idCategory))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS rutas");
        onCreate(db);
    }
}
