package com.example.jailbreak_center;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public Database (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "jailbreakcenter", factory, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE users(id integer primary key autoincrement, username text, password text, email text, image blob)");
        db.execSQL("CREATE TABLE forums(id_forum integer primary key autoincrement, id_user integer, title text, description text, console_forum text)");
        db.execSQL("CREATE TABLE forums_answers(id_answer integer primary key autoincrement, id_user integer, id_forum integer, answer text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion){}

    public Boolean Check_user(String entry, String consult, SQLiteDatabase database){
        String query = "SELECT * FROM users WHERE "+consult+" = '"+entry+"'";
        Cursor res = database.rawQuery(query,null);
        if(res != null && res.moveToFirst()){
            res.close();
            return true;
        }else{
            return false;
        }
    }

    public String Register(String email, String password, String username, byte[] img){
        String res;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("username",username);
        contenedor.put("password",password);
        contenedor.put("email",email);
        contenedor.put("image",img);
        try{
            database.insertOrThrow("users",null,contenedor);
            res = "User Saved Correctly";
        }
        catch(SQLException e){
            res = "Failed to Save User, Try it again";
        }
        database.close();
        return res;
    }

    public String SendAnswer(String id_forum, String id_user, String answer){
        String res;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user",id_user);
        contentValues.put("id_forum",id_forum);
        contentValues.put("answer",answer);
        try{
            database.insertOrThrow("forums_answers",null,contentValues);
            res = "Answer uploaded to the forum.";
        }catch (SQLException e){
            res = "Connection error while trying to upload the answer, try it again later.";
        }
        return res;
    }

    public String UploadForum(String title, String description, String id_user, String console_forum){
        String res;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user",id_user);
        contentValues.put("title",title);
        contentValues.put("description",description);
        contentValues.put("console_forum",console_forum);
        try{
            database.insertOrThrow("forums",null,contentValues);
            res = "Forum created successfully.";
        }
        catch(SQLException e){
            res = "Connection error while trying to upload the forum, try it again later.";
        }
        return res;
    }

    public User_class Login(String username, String password) {
        String userid = "";
        String user = "";
        Bitmap image = null;
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT id,username,image FROM users WHERE username = '"+username+"' AND password = '"+password+"'";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst() && cursor != null){
            userid = cursor.getString(0);
            user = cursor.getString(1);
            byte[] img = cursor.getBlob(2);
            image = BitmapFactory.decodeByteArray(img,0,img.length);
        }
        cursor.close();
        User_class TMPUser = new User_class();
        TMPUser.setId_user(userid);
        TMPUser.setUsername(user);
        TMPUser.setImage(image);
        return TMPUser;
    }

    public List<Forum_class> get_Forums(String console_forum){
        List<Forum_class> Forums = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM forums WHERE console_forum = '"+console_forum+"' ORDER BY id_forum DESC";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst() && cursor != null){
            do{
                Forum_class TMP_Forum = new Forum_class();
                TMP_Forum.set_IDforos(cursor.getString(0));
                TMP_Forum.set_IDusuario(cursor.getString(1));
                TMP_Forum.set_ForumTitle(cursor.getString(2));
                TMP_Forum.set_ForumDescription(cursor.getString(3));
                TMP_Forum.set_ConsoleForum(cursor.getString(4));
                Forums.add(TMP_Forum);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Forums;
    }

    public List<Answers_class> get_AnswersForum(String id_forum){
        List<Answers_class> Answers = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM forums_answers WHERE id_forum = '"+id_forum+"'";
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.moveToFirst() && cursor != null){
            do{
                Answers_class TMPAnswer = new Answers_class();
                TMPAnswer.set_IDAnswer(cursor.getString(0));
                TMPAnswer.set_IDUser(cursor.getString(1));
                TMPAnswer.set_IDForum(cursor.getString(2));
                TMPAnswer.set_Answer(cursor.getString(3));
                Answers.add(TMPAnswer);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Answers;
    }

    public Bitmap get_UserImage(String id_user){
        SQLiteDatabase database = this.getWritableDatabase();
        Bitmap image = null;
        String query = "SELECT image FROM users WHERE id = '"+id_user+"'";
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst() && cursor != null){
            byte[] img = cursor.getBlob(0);
            image = BitmapFactory.decodeByteArray(img,0,img.length);
        }
        cursor.close();
        return image;
    }

    public String get_UserUsername(String id_user){
        SQLiteDatabase database = this.getWritableDatabase();
        String username = "";
        String query = "SELECT username FROM users WHERE id = '"+id_user+"'";
        Cursor cursor = database.rawQuery(query,null);
        if (cursor.moveToFirst() && cursor != null){
            username = cursor.getString(0);
        }
        cursor.close();
        return username;
    }

}
