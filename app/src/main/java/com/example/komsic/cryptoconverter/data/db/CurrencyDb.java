package com.example.komsic.cryptoconverter.data.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.komsic.cryptoconverter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Database(entities = {CurrencyCard.class}, version = 1, exportSchema = false)
public abstract class CurrencyDb extends RoomDatabase {
    private static final String TAG = "CurrencyDb";

    private static CurrencyDb INSTANCE;
    public static int i;

    public static CurrencyDb getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (CurrencyDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CurrencyDb.class, "currency_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    new PopulateDbAsync(INSTANCE, context).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract CurrencyCardDao currencyDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CurrencyCardDao mDao;
        private Resources mResources;

        PopulateDbAsync(CurrencyDb db, Context context) {
            mDao = db.currencyDao();
            mResources = context.getResources();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                readCurrenciesFromResources();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void readCurrenciesFromResources() throws IOException, JSONException {
            StringBuilder builder = new StringBuilder();
            InputStream in = mResources.openRawResource(R.raw.cards);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            //Parse resource into key/values
            final String rawJson = builder.toString();
            JSONObject root = new JSONObject(rawJson);
            JSONArray insectArray = root.getJSONArray("currencies");

            for (int i = 0; i < insectArray.length(); i++) {

                JSONObject cardJson = insectArray.getJSONObject(i);

                String currencyType = cardJson.getString("currencyType");
                String currencyName = cardJson.getString("currencyName");
                double ethRate = cardJson.getDouble("ethRate");
                double btcRate = cardJson.getDouble("btcRate");
                String imageAsset = cardJson.getString("imageAsset");
                boolean selectedStatus = cardJson.getBoolean("selectedStatus");

                CurrencyCard currencyCard = new CurrencyCard(currencyType, currencyName, btcRate,
                        ethRate, imageAsset, selectedStatus);

                long j = mDao.insert(currencyCard);
                Log.e(TAG, "readCurrenciesFromResources: " + j);
            }
        }
    }
}
