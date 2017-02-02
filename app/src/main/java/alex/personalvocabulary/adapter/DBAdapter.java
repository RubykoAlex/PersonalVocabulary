package alex.personalvocabulary.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;

import alex.personalvocabulary.WordTransl;


public class DBAdapter {

    private LinkedList<WordTransl> arrayList = new LinkedList<WordTransl>();
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        DBHelper = new DatabaseHelper(context);
    }

    //---opens the database---
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    //---insert a record into the database---
    public long insertRecord(String word, String translation) {
        open(); // SQLiteDatabase db = DBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_TITLE, word);
        contentValues.put(DatabaseHelper.KEY_NOTES, translation);
        long id = db.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
        return id;
    }



    //---retrieves all the records---
    public String getAllWords() {
        db = DBHelper.getReadableDatabase();

        String [] columns = {DatabaseHelper.KEY_TITLE,DatabaseHelper.KEY_NOTES};
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        String thisWord, thisTranslation;
        while (cursor.moveToNext()){
            thisWord = cursor.getString(0);
            thisTranslation = cursor.getString(1);
            WordTransl wordTransl = new WordTransl(thisWord, thisTranslation);
            arrayList.add(wordTransl);

        }
        return null;
    }

    public LinkedList<WordTransl>  getArrayList(){
    return arrayList;
    }




    public int deleteRow(String unusefullWord){
        open();
        String [] whereArgs = {unusefullWord};
        int count = db.delete(DatabaseHelper.DATABASE_TABLE,DatabaseHelper.KEY_TITLE +"=?", whereArgs);
        return count;
    }


    // ------------------------------------------------------------------------------------------
    //Inner class
    class DatabaseHelper extends SQLiteOpenHelper {
        public static final String KEY_ROWID = "_id";
        public static final String KEY_TITLE = "word";
        public static final String KEY_NOTES = "translation";
        private static final String TAG = "DBAdapter";

        private static final String DATABASE_NAME = "VocabularyDB";
        private static final String DATABASE_TABLE = "vocabulary";
        private static final int DATABASE_VERSION = 1;
        public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + DATABASE_TABLE;


        private static final String DATABASE_CREATE =
                "CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY " +
                        "AUTOINCREMENT, " + KEY_TITLE + " VARCHAR, " + KEY_NOTES + " VARCHAR);";

        private Context context;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            //Message.getMessage(context, "Constructor called" );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }







    /*

    //---deletes a particular record---
    public boolean deleteContact(long rowId) {
        return db.delete(DatabaseHelper.DATABASE_TABLE, DatabaseHelper.KEY_ROWID + "=" + rowId, null) > 0;
    }





    //---updates a record---
    public boolean updateRecord(long rowId, String word, String translation) {
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.KEY_TITLE, word);
        args.put(DatabaseHelper.KEY_NOTES, translation);
        return db.update(DatabaseHelper.DATABASE_TABLE, args, DatabaseHelper.KEY_ROWID + "=" + rowId, null) > 0;
    }
    //---retrieves a particular record---
    public String getTransl(String myword) throws SQLException {
        open();
        String [] columns = {DatabaseHelper.KEY_NOTES};
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, columns, DatabaseHelper.KEY_TITLE + " = '"+myword+"'", null, null, null, null);
        StringBuilder stringBuilderl = new StringBuilder();
        while (cursor.moveToNext()){

            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_NOTES);

            String translation = cursor.getString(index2);
            stringBuilderl.append(translation + "\n");
        }
        return stringBuilderl.toString();
    }


    public int updateTranslation(String oldTranslation, String newTranslation){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.KEY_NOTES, newTranslation);
        String[] whereArgs = {oldTranslation};
        int count = db.update(DatabaseHelper.DATABASE_TABLE, contentValues, DatabaseHelper.KEY_NOTES +"=?", whereArgs);
        return  count;

    }


    //---retrieves all the records---
    public String getAllRecords() {
        open();
        String [] columns = {DatabaseHelper.KEY_ROWID, DatabaseHelper.KEY_TITLE,DatabaseHelper.KEY_NOTES};
        Cursor cursor = db.query(DatabaseHelper.DATABASE_TABLE, columns, null, null, null, null, null);
        StringBuilder stringBuilderl = new StringBuilder();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String word = cursor.getString(1);
            String translation = cursor.getString(2);
            stringBuilderl.append(cid + " " + word + " " + translation + "\n");

        }
        return stringBuilderl.toString();
    }*/
}