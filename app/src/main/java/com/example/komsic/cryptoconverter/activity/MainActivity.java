package com.example.komsic.cryptoconverter.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.adapter.CurrencyConversionAdapter;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.data.db.CurrencyCardSubset;
import com.example.komsic.cryptoconverter.helper.DialogError;
import com.example.komsic.cryptoconverter.helper.DialogNewCard;
import com.example.komsic.cryptoconverter.viewmodel.CurrencyListViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements CurrencyConversionAdapter.OnItemClicked{

    private static final String TAG = "MainActivity";

    private CurrencyConversionAdapter mAdapter;
    private TextView btcToEthRateTV;
	private CurrencyListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btcToEthRateTV = findViewById(R.id.btc_to_eth_rate_tv);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        mAdapter = new CurrencyConversionAdapter(this);

        recyclerView.setAdapter(mAdapter);
        mViewModel = ViewModelProviders.of(this).get(CurrencyListViewModel.class);
        mViewModel.getSelectedCurrencies().observe(this, new Observer<List<CurrencyCard>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyCard> currencyCards) {
                if (currencyCards != null && currencyCards.size() > 0) {
                    btcToEthRateTV.setText(String.valueOf(currencyCards.get(0).btcRate));
                    currencyCards.remove(0);
                    mAdapter.setCurrenciesList(currencyCards);
                }
            }
        });
        mViewModel.getRemoteDataFetchingStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean status) {
                if (status != null && !status) {
                    displayErrorDialog(getString(R.string.error_fetching_data));
                }
            }
        });

        final DialogNewCard dialog = new DialogNewCard();
        dialog.init(this);
        mViewModel.getUnselectedCurrencies().observe(this, new Observer<List<CurrencyCardSubset>>() {
            @Override
            public void onChanged(@Nullable List<CurrencyCardSubset> currencyCardSubsets) {
                dialog.setUnselectedCards(currencyCardSubsets);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show(getFragmentManager(), "123");

//                Intent intent = new Intent(MainActivity.this, TestingActivity.class);
//                startActivity(intent);
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
        switch (item.getItemId()) {
            case R.id.action_refresh:
                fetchData();
                return true;
            case R.id.action_about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_clear_all:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fetchData() {
		mViewModel.updateRates();
    }

    public void addCurrencyToList(String currencyType) {
        mViewModel.updateSelectedStatus(currencyType, true);
        Log.e(TAG, "addCurrencyToList: " + currencyType);
    }

    private void displayErrorDialog(String errorMessage) {
        DialogError dialog = new DialogError();
        dialog.setErrorMessage(errorMessage);
        dialog.show(getFragmentManager(), "123");
    }

    @Override
    public void onItemClicked(CurrencyCard card) {
        Toast.makeText(this, card.toString(), Toast.LENGTH_SHORT).show();
        mViewModel.updateSelectedStatus(card.currencyType, false);
    }
}
