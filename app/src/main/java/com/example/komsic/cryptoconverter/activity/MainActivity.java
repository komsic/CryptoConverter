package com.example.komsic.cryptoconverter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.adapter.CurrencyConversionAdapter;
import com.example.komsic.cryptoconverter.helper.DialogError;
import com.example.komsic.cryptoconverter.helper.DialogNewCard;
import com.example.komsic.cryptoconverter.model.Currency;
import com.example.komsic.cryptoconverter.model.ItemResponse;
import com.example.komsic.cryptoconverter.service.RestApiClient;
import com.example.komsic.cryptoconverter.service.RestApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.animation.*;


public class MainActivity extends AppCompatActivity {

	private Animation loadingAnimation;
    private CurrencyConversionAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView btcToEthRateTV;
	private View loadingView;
    private ItemResponse mItemResponse;
	private boolean isDataFetchedSuccessfully;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		
		isDataFetchedSuccessfully = false;
	
		loadingView = findViewById(R.id.loading_view);
		
        btcToEthRateTV = (TextView) findViewById(R.id.btc_to_eth_rate_tv);
		
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);

        mAdapter = new CurrencyConversionAdapter(MainActivity.this);
        mItemResponse = mAdapter.getItemResponse();
        btcToEthRateTV.setText(String.valueOf(mItemResponse.getBTC().getETH()));

        fetchData();

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
        switch (item.getItemId()) {
            case R.id.action_refresh:
                fetchData();
                return true;
            case R.id.action_about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_clear_all:
                if (mAdapter != null) {
                    mAdapter.removeAllCurrencyCards();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAdapter != null) {
            mAdapter.saveCurrencyCardsToMemory();
        }
    }

    public void fetchData() {
		startLoadingAnimation();
		
        RestApiService apiService = new RestApiClient().getClient().create(RestApiService.class);
        Call<ItemResponse> itemResponseCall = apiService.getItemResponse();
        itemResponseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                if(response.isSuccessful()){
                    mItemResponse = response.body();
                    btcToEthRateTV.setText(String.valueOf(mItemResponse.getBTC().getETH()));
                    mAdapter.setItemResponse(mItemResponse);
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
					isDataFetchedSuccessfully = true;
					stopLoadingAnimation();
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                
				stopLoadingAnimation();
				isDataFetchedSuccessfully = false;
                displayErrorDialog("Error Fetching Data." +
                        "\nPlease Check Your Data Network." +
                        "\nAnd Try Again");
            }
        });
    }

	private void stopLoadingAnimation(){
		loadingView.setVisibility(View.GONE);
		loadingView.clearAnimation();
		loadingAnimation.cancel();
	}
	
	private void startLoadingAnimation(){
		loadingView.setVisibility(View.VISIBLE);
		loadingAnimation = AnimationUtils.loadAnimation(this, R.anim.loading);
		loadingAnimation.setDuration(500);
		loadingView.startAnimation(loadingAnimation);
	}

    public void create(Currency.CurrencyType currencyType) {
        if (mAdapter != null) {
            Currency newCurrencyCard = new Currency(currencyType);
            if (isDataFetchedSuccessfully == true) {
                mAdapter.addCurrencyCard(newCurrencyCard);
            } else {
                displayErrorDialog("Oops!!! Seems like you cannot create this card " +
                        "until you fetch data");
            }

        }
    }

    private void displayErrorDialog(String errorMessage) {
        DialogError dialog = new DialogError();
        dialog.setErrorMessage(errorMessage);
        dialog.show(getFragmentManager(), "123");
    }
}
