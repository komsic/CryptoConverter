package com.example.komsic.cryptoconverter.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.model.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by komsic on 11/5/2017.
 */

public class JSONSerializer {
    private String mFileName;
    private Context mContext;

    public JSONSerializer(String fileName, Context context) {
        mFileName = fileName;
        mContext = context;
    }

    public void save(List<Currency> currencies) throws IOException, JSONException {
        // Make an array in JSON format
        JSONArray jsonArray = new JSONArray();
        if (currencies != null) {
            for (Currency c : currencies) {
                jsonArray.put(c.convertToJSON());
            }

            // Writing it to a private disk space of our app
            Writer writer = null;
            try {
                OutputStream outputStream = mContext.openFileOutput(mFileName,
                        mContext.MODE_PRIVATE);
                writer = new OutputStreamWriter(outputStream);
                writer.write(jsonArray.toString());
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    public ArrayList<Currency> load() throws IOException, JSONException {
        ArrayList<Currency> currencyArrayList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                currencyArrayList.add(new Currency(jsonArray.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(mContext, mContext.getString(R.id.file_not_found), Toast.LENGTH_SHORT).show();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return currencyArrayList;
    }
}
