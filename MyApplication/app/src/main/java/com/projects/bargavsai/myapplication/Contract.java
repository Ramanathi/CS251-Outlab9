package com.projects.bargavsai.myapplication;

import android.provider.BaseColumns;

public class Contract {

    public static final class Entry implements BaseColumns{
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_DATE = "date";
    }
}
