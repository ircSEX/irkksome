package se.alkohest.irkksome.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import se.alkohest.irkksome.orm.ORMException;
import se.alkohest.irkksome.orm.PersistenceContext;

public class SQLitePersistenceContext implements PersistenceContext {
    private SQLiteAdapter database;

    SQLitePersistenceContext() {

    }

    public SQLitePersistenceContext(Context context) {
        database = new SQLiteAdapter(context);
    }

    @Override
    public long create(String table, ContentValues values) throws ORMException {
        long primaryKey = database.insert(table, values);
        if (primaryKey == -1) {
            throw new ORMException("Could not perform CREATE.");
        }
        return primaryKey;
    }

    @Override
    public Cursor read(String table, String[] projection) {
        return database.read(table, projection, null, null, null);
    }

    @Override
    public int update() throws ORMException {
        return 0;
    }

    @Override
    public int delete() throws ORMException {
        return 0;
    }
}
