package com.example.komsic.cryptoconverter.data.db.util;

import androidx.room.TypeConverter;

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
