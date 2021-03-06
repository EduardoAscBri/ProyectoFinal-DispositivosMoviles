package com.example.proyectofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    public static SQLiteDatabase readableConnection;
    public static SQLiteDatabase writableConnection;

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.readableConnection = getReadableDatabase();
        this.writableConnection = getWritableDatabase();
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


    public Cursor ExpensesPerMonth(SQLiteDatabase conexion, String fechaInicio, String fechaFinal){
        Cursor datos = null;

        String query = "SELECT Account.Name, " +
                "Transact.Amount, Transact.Concept, Transact.Date, Transact.LatLocation, Transact.LonLocation, " +
                "Category.Name, Category.Type, Category.TransactionType " +
                "FROM Transact " +
                "LEFT JOIN Account " +
                "ON Transact.idOrigAccount = Account.idAccount " +
                "LEFT JOIN Category " +
                "ON Transact.idCategory = Category.idCategory " +
                "WHERE (Transact.IsAutCharge = 1) " +
                "AND (Transact.Date BETWEEN " + fechaInicio + " AND " + fechaFinal + ")";

        datos = conexion.rawQuery(query, null);

        return datos;
    }


    public Cursor BalancePerMonth(SQLiteDatabase conexion, String fechaInicio, String fechaFinal){
        Cursor datos = null;

        String query = "SELECT Account.Name, " +
                "Transact.Amount, Transact.Concept, Transact.Date, Transact.LatLocation, Transact.LonLocation, " +
                "Category.Name, Category.Type, Category.TransactionType " +
                "FROM Transact " +
                "LEFT JOIN Account " +
                "ON Transact.idOrigAccount = Account.idAccount " +
                "LEFT JOIN Category " +
                "ON Transact.idCategory = Category.idCategory " +
                "WHERE (Transact.Date BETWEEN " + fechaInicio + " AND " + fechaFinal + ")";

        datos = conexion.rawQuery(query, null);

        return datos;
    }


    public Cursor BalancePerAccount(SQLiteDatabase conexion, Integer idAccount, String fechaInicio, String fechaFinal){
        Cursor datos = null;

        String query = "SELECT Account.Name, " +
                "Transact.Amount, Transact.Concept, Transact.Date, Transact.LatLocation, Transact.LonLocation, " +
                "Category.Name, Category.Type, Category.TransactionType " +
                "FROM Transact " +
                "LEFT JOIN Account " +
                "ON Transact.idOrigAccount = Account.idAccount " +
                "LEFT JOIN Category " +
                "ON Transact.idCategory = Category.idCategory " +
                "WHERE (Account.idAccount = " + idAccount.toString() + ") " +
                "AND (Transact.Date BETWEEN " + fechaInicio + " AND " + fechaFinal + ")";

        datos = conexion.rawQuery(query, null);

        return datos;
    }


    public Cursor TransactionsPerCategory(SQLiteDatabase conexion, Integer idCategory, String fechaInicio, String fechaFinal){
        Cursor datos = null;

        String query = "SELECT Account.Name, " +
                "Transact.Amount, Transact.Concept, Transact.Date, Transact.LatLocation, Transact.LonLocation, " +
                "Category.Name, Category.Type, Category.TransactionType " +
                "FROM Transact " +
                "LEFT JOIN Account " +
                "ON Transact.idOrigAccount = Account.idAccount " +
                "LEFT JOIN Category " +
                "ON Transact.idCategory = Category.idCategory " +
                "WHERE (Category.idCategory = " + idCategory.toString() + ") " +
                "AND (Transact.Date BETWEEN " + fechaInicio + " AND " + fechaFinal + ")";

        datos = conexion.rawQuery(query, null);

        return datos;
    }


    public Cursor TransactionsByCharges(SQLiteDatabase conexion, String fechaInicio, String fechaFinal){
        Cursor datos = null;

        String query = "SELECT Account.Name, " +
                "Transact.Amount, Transact.Concept, Transact.Date, Transact.LatLocation, Transact.LonLocation, " +
                "Category.Name, Category.Type, Category.TransactionType " +
                "FROM Transact " +
                "LEFT JOIN Account " +
                "ON Transact.idOrigAccount = Account.idAccount " +
                "LEFT JOIN Category " +
                "ON Transact.idCategory = Category.idCategory " +
                "WHERE (Transact.IsAutCharge = 0) " +
                "AND (Transact.Date BETWEEN " + fechaInicio + " AND " + fechaFinal + ")";

        datos = conexion.rawQuery(query, null);

        return datos;
    }

}
