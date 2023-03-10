package com.hirkanico.carconsumable.library;

public class DailyTaskContentProvider /*extends ContentProvider*/ {
//
//    static final String PROVIDER_NAME = "com.hirkanico.dailyplanner.library.DailyTaskContentProvider";
//    static final String URL = "content://" + PROVIDER_NAME + "/"+ DatabaseHelper.DAILY_PLANNER_TABLE_NAME;
//    static final Uri CONTENT_URI = Uri.parse(URL);
//
//    static final String _ID = "_id";
//    static final String NAME = "name";
//    static final String GRADE = "grade";
//
//    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;
//
//    static final int DAILY_TASK = 1;
//    static final int DAILY_TASK_ID = 2;
//
//    static final UriMatcher uriMatcher;
//    static{
//        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(PROVIDER_NAME, DatabaseHelper.ALL_PLAN_TABLE_NAME, DAILY_TASK);
//        uriMatcher.addURI(PROVIDER_NAME, DatabaseHelper.ALL_PLAN_TABLE_NAME+"/#", DAILY_TASK_ID);
//    }
//
//    /**
//     * Database specific constant declarations
//     */
//
//    private SQLiteDatabase db;
//    //static final String DATABASE_NAME = "College";
//    static final String DAILY_TASK_TABLE_NAME = DatabaseHelper.ALL_PLAN_TABLE_NAME;
//    /*
//    static final int DATABASE_VERSION = 1;
//    static final String CREATE_DB_TABLE =
//            " CREATE TABLE " + DAILY_TASK_TABLE_NAME +
//                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                    " name TEXT NOT NULL, " +
//                    " grade TEXT NOT NULL);";
//
//     */
//
//    /**
//     * Helper class that actually creates and manages
//     * the provider's underlying data repository.
//     */
///*
//    private static class DatabaseHelper extends SQLiteOpenHelper {
//        DatabaseHelper(Context context){
//            super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        }
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(CREATE_DB_TABLE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
//            onCreate(db);
//        }
//    }
//*/
//    @Override
//    public boolean onCreate() {
//        Context context = getContext();
//        DatabaseHelper dbHelper = new DatabaseHelper(context);
//
//        /**
//         * Create a write able database which will trigger its
//         * creation if it doesn't already exist.
//         */
//
//        db = dbHelper.getWritableDatabase();
//        return (db == null)? false:true;
//    }
//
//    @Override
//    public Uri insert(Uri uri, ContentValues values) {
//        /**
//         * Add a new student record
//         */
//        long rowID = db.insert(DAILY_TASK_TABLE_NAME, "", values);
//
//        /**
//         * If record is added successfully
//         */
//        if (rowID > 0) {
//            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
//            getContext().getContentResolver().notifyChange(_uri, null);
//            return _uri;
//        }
//
//        throw new SQLException("Failed to add a record into " + uri);
//    }
//
//    @Override
//    public Cursor query(Uri uri, String[] projection,
//                        String selection,String[] selectionArgs, String sortOrder) {
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//        qb.setTables(DAILY_TASK_TABLE_NAME);
//
//        switch (uriMatcher.match(uri)) {
//            case DAILY_TASK:
//                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
//                break;
//
//            case DAILY_TASK_ID:
//                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
//                break;
//
//            default:
//        }
//
//        if (sortOrder == null || sortOrder == ""){
//            /**
//             * By default sort on student names
//             */
//            sortOrder = NAME;
//        }
//
//        Cursor c = qb.query(db,	projection,	selection,
//                selectionArgs,null, null, sortOrder);
//        /**
//         * register to watch a content URI for changes
//         */
//        c.setNotificationUri(getContext().getContentResolver(), uri);
//        return c;
//    }
//
//    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)){
//            case DAILY_TASK:
//                count = db.delete(DAILY_TASK_TABLE_NAME, selection, selectionArgs);
//                break;
//
//            case DAILY_TASK_ID:
//                String id = uri.getPathSegments().get(1);
//                count = db.delete(DAILY_TASK_TABLE_NAME, _ID +  " = " + id +
//                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues values,
//                      String selection, String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)) {
//            case DAILY_TASK:
//                count = db.update(DAILY_TASK_TABLE_NAME, values, selection, selectionArgs);
//                break;
//
//            case DAILY_TASK_ID:
//                count = db.update(DAILY_TASK_TABLE_NAME, values,
//                        _ID + " = " + uri.getPathSegments().get(1) +
//                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri );
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }
//
//    @Override
//    public String getType(Uri uri) {
//        switch (uriMatcher.match(uri)){
//            /**
//             * Get all student records
//             */
//            case DAILY_TASK:
//                return "vnd.android.cursor.dir/vnd.example.students";
//            /**
//             * Get a particular student
//             */
//            case DAILY_TASK_ID:
//                return "vnd.android.cursor.item/vnd.example.students";
//            default:
//                throw new IllegalArgumentException("Unsupported URI: " + uri);
//        }
//    }
}