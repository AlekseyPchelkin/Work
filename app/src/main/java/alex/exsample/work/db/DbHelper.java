package alex.exsample.work.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DbHelper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static String DB_NAME = "DB3.db";
    private static final int VER = 1;
    private Context myContext;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VER);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath() + "/" + DB_NAME;
    }

    public void create_db() {
        InputStream myInpyt = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if(!file.exists()) { // если файл существует
                this.getReadableDatabase();
                // получаем локальную бд как поток
                myInpyt = myContext.getAssets().open(DB_NAME);
                // путь к новой бд
                String outFileName = DB_PATH;
                //Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                //побайтово копируем данные
                byte[] buffer = new byte[1024];
                int lengh;
                while ((lengh = myInpyt.read(buffer)) > 0){
                    myOutput.write(buffer,0,lengh);
                }
                myOutput.flush();
                myOutput.close();
                myInpyt.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SQLiteDatabase open() throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH,null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}