package com.example.android.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class PetProvider extends ContentProvider {

    private static final String TAG = "PetProvider";

    private static final int PETS = 100;
    private static final int PET_ID = 101;

    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS,PETS);
        mUriMatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS+"/#",PET_ID);
    }

    private PetDbHelper mDbHelper;

    //all overridden methods will be implemented depending on all app possible Uris using UriMatcher

    /**
     * this method will implement the initialization of pet provider by initialing pet database
     * @return always true since always connected to pets database
     */
    @Override
    public boolean onCreate() {
        mDbHelper = new PetDbHelper(getContext());
        return true;
    }

    /**
     * this method implements how query(select) pets data using the following parameters
     * @param uri specifies which data needed
     * @param strings columns selected from database(projection)
     * @param s where clause i.e weight =? or weight >?(selection)
     * @param strings1 assigned values to columns in where clause(selectionArgs)
     * @param s1 column to order by, also ASC and DESC could by added(orderBy)
     * @return data needed in cursor object(table or a row)
     */
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = mUriMatcher.match(uri);


        switch (match){
            case PETS:
                cursor = database.query(PetContract.PetEntry.TABLE_NAME,strings,s,strings1,null,null,null);
                break;
            case PET_ID:
                s = PetContract.PetEntry._ID + "=?";
                strings1 = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PetContract.PetEntry.TABLE_NAME,strings,s,strings1,null,null,null);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown uri");
        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        final int match = mUriMatcher.match(uri);
        switch (match){
            case PETS:
                return PetContract.PetEntry.CONTENT_LIST_TYPE;
            case PET_ID:
                return PetContract.PetEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri+" with match "+match);
        }
    }

    private Uri insertPet(Uri uri,ContentValues contentValues){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        //sanity checks for all inputs
        String name = contentValues.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);
        if (name == null){
            throw new IllegalArgumentException("Pet requires a name");
        }
        String breed = contentValues.getAsString(PetContract.PetEntry.COLUMN_PET_BREED);
        if (breed == null){
            throw new IllegalArgumentException("Pet requires a breed");
        }
        Integer gender = contentValues.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
        if (gender == null || !PetContract.PetEntry.isValidGender(gender)){
            throw new IllegalArgumentException("Pet requires valid gender");
        }
        Integer weight = contentValues.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);
        if (weight == null && weight < 0){
            throw new IllegalArgumentException("Pet requires valid weight");
        }
        long id = database.insert(PetContract.PetEntry.TABLE_NAME,null,contentValues);
        if (id == -1) {
            Log.e(TAG, "Failed to insert row for " + uri);
            return null;
        }

        // Notify all listeners that the data has changed for the pet content URI
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri,id);
    }

    /**
     * this method implements the insertion of data into database
     * @param uri specifies the data where will be added to
     * @param contentValues values of the data
     * @return uri holding the inserted row
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int match = mUriMatcher.match(uri);
        switch (match){
            case PETS:
                return insertPet(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = mUriMatcher.match(uri);
        int rowsDeleted = 0;
        switch (match) {
            case PETS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(PetContract.PetEntry.TABLE_NAME, s, strings);
                break;
            case PET_ID:
                // Delete a single row given by the ID in the URI
                s = PetContract.PetEntry._ID + "=?";
                strings = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted =  database.delete(PetContract.PetEntry.TABLE_NAME, s, strings);
                break;
            default:
                //throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        final int match = mUriMatcher.match(uri);
        switch (match){
            case PETS:
                return updatePet(uri, contentValues, s, strings);
            case PET_ID:
                s = PetContract.PetEntry._ID +"=?";
                strings = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, s, strings);
            default:
                throw new IllegalArgumentException("Update is not supported for "+uri);
        }
    }

    private int updatePet(Uri uri,ContentValues contentValues,String selection,String[] selectionArgs){
        // If the {@link PetEntry#COLUMN_PET_NAME} key is present,
        // check that the name value is not null.
        if (contentValues.containsKey(PetContract.PetEntry.COLUMN_PET_NAME)) {
            String name = contentValues.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Pet requires a name");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
        if (contentValues.containsKey(PetContract.PetEntry.COLUMN_PET_GENDER)) {
            Integer gender = contentValues.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);
            if (gender == null || !PetContract.PetEntry.isValidGender(gender)) {
                throw new IllegalArgumentException("Pet requires valid gender");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (contentValues.containsKey(PetContract.PetEntry.COLUMN_PET_WEIGHT)) {
            // Check that the weight is greater than or equal to 0 kg
            Integer weight = contentValues.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);
            if (weight != null && weight < 0) {
                throw new IllegalArgumentException("Pet requires valid weight");
            }
        }

        if (contentValues.size() == 0){
            return 0;
        }

        // Otherwise, get writeable database to update the data
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        // Returns the number of database rows affected by the update statement
        int rowsUpdated =  database.update(PetContract.PetEntry.TABLE_NAME, contentValues, selection, selectionArgs);
        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
