package com.example.komsic.cryptoconverter.data.db.util;

import android.arch.persistence.room.TypeConverter;

public class BooleanConverter {

    @TypeConverter
    public static boolean toBoolean(int input) {
        return input == 1;
    }

    @TypeConverter
    public static int toInt(boolean input) {
        return input ? 1 : 0;
    }
}
