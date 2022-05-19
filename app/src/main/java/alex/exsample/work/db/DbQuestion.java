package alex.exsample.work.db;


import android.annotation.SuppressLint;
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

    public DbQuestion(String table_name, Context context) {
        this.table_name = table_name;
        this.myContext = context;
        dataHelper = new DbHelper(myContext);
        dataHelper.create_db();
        db = dataHelper.open();
    }

    public int getCountFieldPosition(String column) {
       String question = "SELECT " + column + " from '" +  table_name + "'";
       userCursor = db.rawQuery("select * from '" + table_name + "'", null);
       userCursor = db.rawQuery(question,null);
       int count = userCursor.getCount();
       return count;
    }

    public int getCountTablePosition() {
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
        return  item_title;
    }

   @SuppressLint("Range")
   public int getIdWhereTitle(String title) {
       String question = "SELECT id_theme from '" +  table_name + "'" + " Where title = '" + title + "'";
       userCursor = db.rawQuery("select * from '" + table_name + "'", null);
       userCursor = db.rawQuery(question,null);
       userCursor.moveToPosition(0);
       String id = userCursor.getString(userCursor.getColumnIndex("id_theme"));
       return Integer.parseInt(id);
   }

    @SuppressLint("Range")
    public String getIdField(String field, int id, int position) {
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where id = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        userCursor.moveToPosition(position);
        String item_title = userCursor.getString(userCursor.getColumnIndex(field));
        return item_title;
    }

    public int getCountFieldID(String field, int id) {
        String question = "SELECT " + field + " from '" + table_name + "'" + " Where id = " + id;
        userCursor = db.rawQuery("select * from '" + table_name + "'", null);
        userCursor = db.rawQuery(question,null);
        int count = userCursor.getCount();
        return count;
    }



}
