package com.example.komsic.cryptoconverter.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.adapter.CurrencyConversionAdapter;
import com.example.komsic.cryptoconverter.helper.DialogNewCard;
import com.example.komsic.cryptoconverter.model.Currency;
import com.example.komsic.cryptoconverter.model.ItemResponse;
import com.example.komsic.cryptoconverter.service.RestApiClient;
import com.example.komsic.cryptoconverter.service.RestApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private CurrencyConversionAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView btcToEthRateTV;
    private ItemResponse mItemResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btcToEthRateTV = (TextView) findViewById(R.id.btc_to_eth_rate_tv);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mItemResponse = new ItemResponse();
        fetchData();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNewCard dialog = new DialogNewCard();
                dialog.show(getFragmentManager(), "123");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.saveCurrencyCardsToMemory();
    }

    private void fetchData() {
        RestApiService apiService = new RestApiClient().getClient().create(RestApiService.class);
        Call<ItemResponse> itemResponseCall = apiService.getItemResponse();
        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if(response.isSuccessful()){
                    // TODO do something clue commit mRe to memmory and find a way to make it not null pointer try new () first
                    mItemResponse = response.body();
                    btcToEthRateTV.setText(String.valueOf(mItemResponse.getBTC().getETH()));
                    mAdapter = new CurrencyConversionAdapter(MainActivity.this, mItemResponse);
                    mRecyclerView.setAdapter(mAdapter);
//                    Currency.CurrencyType.onChangeRatesValue();
//                    btcToEthRateTV.setText(String.valueOf(ItemResponse.getBTC().getETH()));
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error Processing Request",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void create(Currency.CurrencyType currencyType) {
        mAdapter.addCurrencyCard(new Currency(currencyType));
    }
}
