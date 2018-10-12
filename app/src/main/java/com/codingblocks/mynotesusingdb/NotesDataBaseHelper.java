package com.codingblocks.mynotesusingdb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NotesDataBaseHelper extends SQLiteOpenHelper {
    public NotesDataBaseHelper(Context context) {
        super(context, "NotesDB", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE task_table(id INTEGER PRIMARY KEY,"+"" +
                "title TEXT NOT NULL,"+"" +
                "description TEXT NOT NULL,"+"" +
                "timestamp TEXT NOT NULL,"+
                "isDone INTEGER );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Note note)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",note.getTitle());
        contentValues.put("description",note.getDescription());
        contentValues.put("timestamp",note.getTimeStamp());
        contentValues.put("isDone",note.getIsDone());
        getReadableDatabase().insert("task_table",
                null,
                contentValues);
    }

    public ArrayList<Note> getAllNotes()
    {
        ArrayList<Note>notes=new ArrayList<>();
        Cursor cursor=getReadableDatabase().query("task_table",
                null,
                null,
                null,
                null,
                null,
                "timeStamp DESC"
        );

        while(cursor.moveToNext())
        {
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String description =cursor.getString(cursor.getColumnIndex("description"));
            Integer isDone =cursor.getInt(cursor.getColumnIndex("isDone"));
            String timeStamp=cursor.getString(cursor.getColumnIndex("timestamp"));
            Integer id=cursor.getInt(0);

            Note note=new Note(title,description,isDone,timeStamp,id);
            notes.add(note);
        }

        cursor.close();

        return notes;
    }

    public Note getNoteById(Integer givenId)
    {
        Note note=null;
        Cursor cursor=getReadableDatabase().query("task_table",
                null,
                "id=?",
                new String[]{givenId.toString()},
                null,
                null,
                null);

        if(cursor.moveToNext())
        {
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String description =cursor.getString(cursor.getColumnIndex("description"));
            Integer isDone =cursor.getInt(cursor.getColumnIndex("isDone"));
            String timeStamp=cursor.getString(cursor.getColumnIndex("timestamp"));
            Integer id=cursor.getInt(0);

             note=new Note(title,description,isDone,timeStamp,id);
        }

        return note;
    }

    public void updateNote(Note note){
        ContentValues contentValues=new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getDescription());
        contentValues.put("timeStamp", note.getTimeStamp());
        contentValues.put("isDone", note.getIsDone());
        getWritableDatabase().update("task_table",
                contentValues,
                "id=?",
                new String[]{note.getId().toString()});
    }

    public void deleteNote(Note note)
    {
        getWritableDatabase().delete("task_table",
                "id = ?",
                new String[]{note.getId().toString()});
    }
}

