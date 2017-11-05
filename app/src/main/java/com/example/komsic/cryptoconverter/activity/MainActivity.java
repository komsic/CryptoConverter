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

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.adapter.CurrencyConversionAdapter;
import com.example.komsic.cryptoconverter.helper.DialogNewCard;
import com.example.komsic.cryptoconverter.model.Currency;
import com.example.komsic.cryptoconverter.model.CurrencyRate;
import com.example.komsic.cryptoconverter.model.ItemResponse;




public class MainActivity extends AppCompatActivity {

    private CurrencyConversionAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView btcToEthRateTV;

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

        fetchData();

        mAdapter = new CurrencyConversionAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

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
        mAdapter.saveCurrencyCards();
    }

    private void fetchData() {

        //RestApiService apiService = new RestApiClient().getClient().create(RestApiService.class);
        //Call<ItemResponse> userListCall
        CurrencyRate btC = new CurrencyRate(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0,
                12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0, 21.0);
        CurrencyRate etH = new CurrencyRate(22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35,
                36, 37, 38, 39, 40, 41, 42);

        ItemResponse.setBTC(btC);
        ItemResponse.setETH(etH);
        btcToEthRateTV.setText(String.valueOf(ItemResponse.getBTC().getETH()));
    }

    public void create(Currency.CurrencyType currencyType) {
        mAdapter.addCurrencyCard(new Currency(currencyType));
    }
}
