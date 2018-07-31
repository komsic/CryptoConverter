package com.example.komsic.cryptoconverter.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.data.CurrencyConverter;
import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.viewmodel.CurrencyDetailViewModel;

public class CurrencyConverterActivity extends AppCompatActivity {
    private Spinner mConverterSpinner;
    private EditText mConvertEdt;
    private LinearLayout mResultLayout;
    private TextView mCurrentCurrencyResultTxt;
    private TextView mBTCResultTxt;
    private TextView mETHResultTxt;

    private String selectedCurrencyType;
    private CurrencyCard mCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        CurrencyDetailViewModel viewModel = ViewModelProviders.of(this)
                .get(CurrencyDetailViewModel.class);
        viewModel.getCurrencyCard(selectedCurrencyType).observe(this,
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

    // TODO Remember to set conversion_result_layout visible
    public void convert(View view) throws Exception {
        String edtInput = mConvertEdt.getText().toString();
        try {
            double amountTobeConverted = Double.parseDouble(edtInput);
            if (amountTobeConverted > 0.0) {
                String selectedCurrencyToBeConverted = (String) mConverterSpinner.getSelectedItem();
                CurrencyConverter currencyConverter = new CurrencyConverter(mCard);
                double[] convertedAmount =
                        currencyConverter.convert((selectedCurrencyToBeConverted != null ?
                                        selectedCurrencyToBeConverted : mCard.currencyType),
                                amountTobeConverted);

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
                Toast.makeText(this, getString(R.string.valid_amount), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
