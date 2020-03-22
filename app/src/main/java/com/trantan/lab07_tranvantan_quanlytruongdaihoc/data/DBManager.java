package com.trantan.lab07_tranvantan_quanlytruongdaihoc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.Khoa;
import com.trantan.lab07_tranvantan_quanlytruongdaihoc.model.TruongDaiHoc;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QlTruongHoc";

    private static final String BANG_TRUONG_HOC = "bang_truong_hoc";
    private static final String ID_TRUONG_HOC = "id_truong_hoc";
    private static final String TEN_TRUONG_HOC = "ten_truong_hoc";
    private static final String MA_TRUONG_HOC = "ma_truong_hoc";


    private static final String BANG_KHOA = "bang_khoa";
    private static final String ID_KHOA = "id_khoa";
    private static final String TEN_TRUONG = "ten_truong";
    private static final String TOA_NHA = "toa_nha";
    private static final String TEN_KHOA = "ten_khoa";
    private static final String SO_DIEN_THOAI_KHOA = "so_dien_thoai_khoa";


    private static final String TAG = "DBManager";

    private static String taoBangTruongHoc =  "CREATE TABLE IF NOT EXISTS " + BANG_TRUONG_HOC + " ("
            + ID_TRUONG_HOC + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MA_TRUONG_HOC + " TEXT, "
            + TEN_TRUONG_HOC + " TEXT)";

    private String taoBangKhoa = "CREATE TABLE IF NOT EXISTS " + BANG_KHOA + " ("
            + ID_KHOA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEN_TRUONG + " INTERGER, "
            + TOA_NHA + " TEXT, "
            + TEN_KHOA + " TEXT, "
            + SO_DIEN_THOAI_KHOA + " INTEGER)";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(taoBangTruongHoc);
        db.execSQL(taoBangKhoa);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BANG_TRUONG_HOC);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_KHOA);

        onCreate(db);
        Log.d(TAG, "onUpgrade: ");
    }

    public void addTruongDaiHoc(TruongDaiHoc truongDaiHoc) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MA_TRUONG_HOC, truongDaiHoc.getMaTruong());
        contentValues.put(TEN_TRUONG_HOC, truongDaiHoc.getTenTruong());

        database.insert(BANG_TRUONG_HOC, null, contentValues);
        database.close();

        Log.d(TAG, "addTruongDaiHoc: ");
    }

    public List<TruongDaiHoc> getAllTruongDaiHoc() {
        List<TruongDaiHoc> listTruongDaiHoc = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + BANG_TRUONG_HOC;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String maTruong = cursor.getString(1);
                String tenTruong = cursor.getString(2);

                TruongDaiHoc truongDaiHoc = new TruongDaiHoc(id, maTruong, tenTruong);
                listTruongDaiHoc.add(truongDaiHoc);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listTruongDaiHoc;
    }

    public void addKhoa(Khoa khoa) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN_TRUONG, khoa.getTenTruongHoc());
        contentValues.put(TOA_NHA, khoa.getToaNha());
        contentValues.put(TEN_KHOA, khoa.getTenKhoa());
        contentValues.put(SO_DIEN_THOAI_KHOA, khoa.getSoDienThoai());

        database.insert(BANG_KHOA, null, contentValues);
        database.close();

        Log.d(TAG, "themKhoa: ");
    }

    public List<Khoa> getAllKhoa() {
        List<Khoa> listKhoa = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + BANG_KHOA;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String tenTruong = cursor.getString(1);
                String maKhoa = cursor.getString(2);
                String tenKhoa = cursor.getString(3);
                int soDienThoai = cursor.getInt(4);

                Khoa khoa = new Khoa(id, tenTruong, maKhoa, tenKhoa, soDienThoai);
                listKhoa.add(khoa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listKhoa;
    }

    public int deleteTruongHoc(TruongDaiHoc truongDaiHoc) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(BANG_TRUONG_HOC, ID_TRUONG_HOC + "=?", new String[]{String.valueOf(truongDaiHoc.getId())});
    }

    public int deleteKhoa(Khoa khoa) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(BANG_KHOA, ID_KHOA + "=?", new String[]{String.valueOf(khoa.getId())});
    }


}
