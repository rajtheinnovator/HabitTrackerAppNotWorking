package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by ABHISHEK RAJ on 10/9/2016.
 */

public final class CodeContract {

    private CodeContract() {
    }

    public static abstract class CodeEntry implements BaseColumns {
        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "code";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BREED = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        //Gender Constants
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }
}
