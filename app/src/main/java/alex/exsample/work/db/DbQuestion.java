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
        String question = "SELECT " + idName + " from '" +  table_name + "'" + " Where " + filed + " = '" + idWhere + "'";
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String id = userCursor.getString(userCursor.getColumnIndex(idName));
        return Integer.parseInt(id);
    }


    @SuppressLint("Range")
    public String getIdField(String field, int id, int position){
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where id = " + id;
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

    public void setResulTest(String check, int position){
        ContentValues values = new ContentValues();
        values.put("right", check);
        db.update(table_name,values, "number_question=?",new String[]{String.valueOf(position)});
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
}
