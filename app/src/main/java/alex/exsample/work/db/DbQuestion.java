package alex.exsample.work.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alex.exsample.work.db.DbHelper;

public class DbQuestion {
    private SQLiteDatabase db;
    private Cursor userCursor;
    private DbHelper dataHelper;
    private Context myContext;
    String table_name;

    public DbQuestion(String table_name, Context context){
        this.table_name = table_name;
        this.myContext = context;
        dataHelper = new DbHelper(myContext);
        dataHelper.create_db();
        db = dataHelper.open();
    }

    public int getCountFieldPosition(String column){
       String question = "SELECT " + column + " from '" +  table_name + "'";
       userCursor = db.rawQuery("select * from '" + table_name + "'", null);
       userCursor = db.rawQuery(question,null);
       int count = userCursor.getCount();
       return count;
    }

    public int getCountTablePosition(){
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        int count = userCursor.getCount();
        return count;
    }

    @SuppressLint("Range")
    public String getField(String field, int position){
        String question = "SELECT " + field + " from '" +  table_name + "'";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String item_title = userCursor.getString(userCursor.getColumnIndex(field));
        return item_title;
    }

    @SuppressLint("Range")
    public int getIdPosition(String field, int position){
        String question = "SELECT " + field + " from '" +  table_name + "'";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        int id = userCursor.getInt(userCursor.getColumnIndex(field));
        return id;
    }

   @SuppressLint("Range")
   public int getIdWhereTitle(String title, String idName, String filed){
       String question = "SELECT " + idName + " from '" +  table_name + "'" + " Where " + filed + " = '" + title + "'";
       userCursor = db.rawQuery("select * from '" + table_name + "'", null);
       userCursor = db.rawQuery(question,null);
       userCursor.moveToPosition(0);
       String id = userCursor.getString(userCursor.getColumnIndex(idName));
       return Integer.parseInt(id);
   }

    @SuppressLint("Range")
    public int getIdWhereId(int idWhere, String idName, String filed, int position){
        String question = "SELECT " + idName + " from '" +  table_name + "'" + " Where " + filed + " = " + idWhere + "";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String id = userCursor.getString(userCursor.getColumnIndex(idName));
        return Integer.parseInt(id);
    }

    @SuppressLint("Range")
    public String getIdWhereId(String idWhere, String idName, String filed, int position){
        String question = "SELECT " + idName + " from '" +  table_name + "'" + " Where " + filed + " = '" + idWhere + "'";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String id = userCursor.getString(userCursor.getColumnIndex(idName));
        return id;
    }

    @SuppressLint("Range")
    public String getIdField(String field, int id, String idName, int position){
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where " + idName + " = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String item_title = userCursor.getString(userCursor.getColumnIndex(field));
        return item_title;
    }

    @SuppressLint("Range")
    public String getIdFieldid(String field, int id, String name_id, int position){
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where " + name_id + " = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String item_title = userCursor.getString(userCursor.getColumnIndex(field));
        return item_title;
    }

    public int getCountFieldID(String field, String id_name, int id){
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where " + id_name  + " = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        int count = userCursor.getCount();
        return count;
    }

    @SuppressLint("Range")
    public String getPDF (int id){ // изменить вместе с getFavorite
        String question = "SELECT id_pdf from '" + table_name + "'" + " Where id_topic = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(0);
        String item_title = userCursor.getString(userCursor.getColumnIndex("id_pdf"));
        return item_title;
    }

    @SuppressLint("Range")
    public String getFavorite(int id){
        String question = "SELECT favorite from '" + table_name + "'" + " Where id_topic = " + Integer.toString(id + 1);
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(0);
        return userCursor.getString(userCursor.getColumnIndex("favorite"));
    }

    @SuppressLint("Range")
    public String getCheck(int position){ // временный метод для проверки бд
        String question = "SELECT " + " right " + " from '" + table_name + "'" + " Where number_question = " + position;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(0);
        return userCursor.getString(userCursor.getColumnIndex("right"));
    }

    public void setResulTest(int point, int position){
        ContentValues values = new ContentValues();
        values.put("points", point);
        db.update("Question",values, "number_question=?",new String[]{String.valueOf(position)});
    }

    public void setFavorite(String check, int position){
        ContentValues values = new ContentValues();
        values.put("favorite", check);
        db.update(table_name,values, "id_topic=?",new String[]{String.valueOf(position)});
    }
    public void setNumberPoints(int check, int position){
        ContentValues values = new ContentValues();
        values.put("number_points", check);
        db.update(table_name,values, "id_test=?",new String[]{String.valueOf(position)});
    }

    public void setRightAnswer(int check, int position){
        ContentValues values = new ContentValues();
        values.put("right_answers", check);
        db.update(table_name,values, "id_test=?",new String[]{String.valueOf(position)});
    }

    public void setNote(String text_notes, String name_notes, int id){
        ContentValues values = new ContentValues();
        values.put("name_notes", name_notes);
        values.put("text_notes", text_notes);
        db.update(table_name,values, "id_notes=?",new String[]{String.valueOf(id)});
    }
    public void setNoteId(int id, int position){
        ContentValues values = new ContentValues();
        values.put("id_notes", position);
        db.update(table_name,values, "id_notes=?",new String[]{String.valueOf(id)});
    }

    public void insertNote(int id) {
        ContentValues cv = new ContentValues();
        cv.put("text_notes", "");
        cv.put("name_notes", "Новая заметка");
        cv.put("id_notes", id);
        db.insert(table_name, null, cv);
    }

    public void deliteField(int id, String id_mame){
        db.delete(table_name,  id_mame + " = ?", new String[]{String.valueOf(id)});
    }
}