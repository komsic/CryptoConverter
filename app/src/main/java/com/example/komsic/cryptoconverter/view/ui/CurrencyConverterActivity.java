package com.example.komsic.cryptoconverter.view.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.CurrencyApplication;
import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.data.CurrencyConverter;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.di.view.ViewComponent;
import com.example.komsic.cryptoconverter.di.view.ViewModule;
import com.example.komsic.cryptoconverter.viewmodel.CurrencyDetailViewModel;
import com.example.komsic.cryptoconverter.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class CurrencyConverterActivity extends AppCompatActivity {
    private Spinner mConverterSpinner;
    private EditText mConvertEdt;
    private LinearLayout mResultLayout;
    private TextView mCurrentCurrencyResultTxt;
    private TextView mBTCResultTxt;
    private TextView mETHResultTxt;

    private String selectedCurrencyType;
    private CurrencyDetailViewModel mViewModel;
    private CurrencyCard mCard;

    @Inject
    ViewModelFactory mViewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getViewComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        mConverterSpinner = findViewById(R.id.convert_spinner);
        mConvertEdt = findViewById(R.id.convert_edt);
        mResultLayout = findViewById(R.id.conversion_result_layout);
        TextView currentCurrencyNameTxt = findViewById(R.id.current_currency_name_tv);
        mCurrentCurrencyResultTxt = findViewById(R.id.current_currency_result_tv);
        mBTCResultTxt = findViewById(R.id.btc_currency_result_tv);
        mETHResultTxt = findViewById(R.id.eth_currency_result_tv);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            finish();
        } else {
            selectedCurrencyType = bundle.getString("currencyName");
        }

        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(CurrencyDetailViewModel.class);
        mViewModel.getCurrencyCard(selectedCurrencyType).observe(this,
                new Observer<CurrencyCard>() {
                    @Override
                    public void onChanged(@Nullable CurrencyCard card) {
                        mCard = card;
                    }
                });

        currentCurrencyNameTxt.setText(selectedCurrencyType);

        String[] spinnerArray = {selectedCurrencyType, getString(R.string.btc), getString(R.string.eth)};
        mConverterSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, spinnerArray));
    }

    @UiThread
    protected ViewComponent getViewComponent() {
        return ((CurrencyApplication) getApplication())
                .getApplicationComponent()
                .newViewComponent(new ViewModule(this));
    }

    public void convert(View view) {

        String edtInput = mConvertEdt.getText().toString();
        try {
            double amountTobeConverted = Double.parseDouble(edtInput);
            if (amountTobeConverted > 0.0) {
                String selectedCurrencyToBeConverted = (String) mConverterSpinner.getSelectedItem();

                double[] convertedAmount = mViewModel.getConvertedAmount(
                        mCard,
                        (selectedCurrencyToBeConverted != null ? selectedCurrencyToBeConverted :
                                mCard.currencyType),
                        amountTobeConverted
                );

                mResultLayout.setVisibility(View.VISIBLE);
                mCurrentCurrencyResultTxt.setText(String
                        .valueOf(convertedAmount[CurrencyConverter.CURRENT_CURRENCY]));
                mBTCResultTxt.setText(String.valueOf(convertedAmount[CurrencyConverter.BTC]));
                mETHResultTxt.setText(String.valueOf(convertedAmount[CurrencyConverter.ETH]));
            } else {
                Toast.makeText(this, getString(R.string.valid_amount), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            if (edtInput.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.valid_amount) + 4555555, Toast.LENGTH_SHORT).show();
                Log.e("Act", "convert: " + e.getMessage());
            }
        }
    }
}
