package com.example.alexk.herodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;




public class HeroDB extends SQLiteOpenHelper implements BaseColumns{

    public HeroDB (Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public HeroDB(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    private static final String DATABASE_NAME = "herodatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "HeroDB";

    HeroDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_HERO_NAME = "heroname";
    public static final String COLUMN_HERO_REAL_NAME = "herorealname";
    public static final String COLUMN_ABOUT_HERO = "abouthero";
    public static final String COLUMN_WORLD = "world";
    public static final String COLUMN_PHOTOFILE = "photofile";


        private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + COLUMN_HERO_NAME + " text not null, " + COLUMN_HERO_REAL_NAME
            + " text not null, " + COLUMN_ABOUT_HERO + " text not null, " + COLUMN_WORLD
            + " text not null, " + COLUMN_PHOTOFILE  + " text not null);";





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);

    }

    // Добавляем новый контакт
    public void addHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HERO_NAME, hero.getName());
        values.put(COLUMN_HERO_REAL_NAME, hero.getRealName());
        values.put(COLUMN_ABOUT_HERO, hero.getAboutInfo());
        values.put(COLUMN_WORLD, hero.getWorld());
        values.put(COLUMN_PHOTOFILE, hero.getPhotoFile());

        // Вставляем строку в таблицу
        db.insert(DATABASE_TABLE, null, values);
        db.close();

    }

    // Получить контакт
    public Hero getHero(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_HERO_NAME, COLUMN_HERO_REAL_NAME, COLUMN_ABOUT_HERO, COLUMN_WORLD, COLUMN_PHOTOFILE }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Hero hero = new Hero(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return hero;

    }

    // Получить всех героев
    public List<Hero> getAllHero() {
        List<Hero> heroList = new ArrayList<>();
        // Выбираем всю таблицу
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проходим по всем строкам и добавляем в список
        if (cursor.moveToFirst()) {
        do {
                Hero hero = new Hero();
            hero.setID(Integer.parseInt(cursor.getString(0)));
                hero.setName(cursor.getString(1));
                hero.setRealName(cursor.getString(2));
                hero.setAboutInfo(cursor.getString(3));
                hero.setWorld(cursor.getString(4));
                hero.setPhotoFile(cursor.getString(5));
                heroList.add(hero);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return heroList;

    }


    // Получить число контактов
    public int getHeroCount() {
        String countQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();

        cursor.close();

        return count;

    }

    // Обновить контакт
    public int updateHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HERO_NAME, hero.getName());
        values.put(COLUMN_HERO_REAL_NAME, hero.getRealName());
        values.put(COLUMN_ABOUT_HERO, hero.getAboutInfo());
        values.put(COLUMN_WORLD, hero.getWorld());
        values.put(COLUMN_PHOTOFILE, hero.getPhotoFile());

        // обновляем строку
        return db.update(DATABASE_TABLE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(hero.getID()) });

    }

    // Удалить контакт
    public void deleteHero(Hero hero) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(hero.getID()) });
        db.close();
    }


}
