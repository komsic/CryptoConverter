package com.example.komsic.cryptoconverter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.komsic.cryptoconverter.R;
import android.widget.*;
import android.content.*;
import com.example.komsic.cryptoconverter.model.*;

public class CurrencyConverterActivity extends AppCompatActivity {
	private Spinner mConverterSpinner;
	private EditText mConvertEdt;
	private LinearLayout mResultLayout;
	private TextView mCurrentCurrencyNameTxt;
	private TextView mCurrentCurrencyResultTxt;
	private TextView mBTCResultTxt;
	private TextView mETHResultTxt;

	private String selectedCurrencyName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
	
		mConverterSpinner = (Spinner) findViewById(R.id.convert_spinner);
		mConvertEdt = (EditText) findViewById(R.id.convert_edt);
		mResultLayout = (LinearLayout) findViewById(R.id.conversion_result_layout);
		mCurrentCurrencyNameTxt = (TextView) findViewById(R.id.current_currency_name_tv);
		mCurrentCurrencyResultTxt = (TextView) findViewById(R.id.current_currency_result_tv);
		mBTCResultTxt = (TextView) findViewById(R.id.btc_currency_result_tv);
		mETHResultTxt = (TextView) findViewById(R.id.eth_currency_result_tv);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            finish();
        }else{
            selectedCurrencyName = bundle.getString("currencyName");
        }

		mCurrentCurrencyNameTxt.setText(selectedCurrencyName);

		String[] spinnerArray = {selectedCurrencyName, "BTC", "ETH"};
		mConverterSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
				spinnerArray));

		getSupportActionBar().setTitle(selectedCurrencyName);
    }

    // TODO Remember to set conversion_result_layout visible
    public void convert(View view) throws Exception{
		String edtInput = mConvertEdt.getText().toString();
		try{
			double amountTobeConverted = Double.parseDouble(edtInput);
			if (amountTobeConverted > 0.0){
				double[] convertedAmount =
                        (new Currency(Currency.CurrencyType.valueOf(selectedCurrencyName)))
                                .convert(mConverterSpinner.getSelectedItem().toString(),
                                        amountTobeConverted);

				mResultLayout.setVisibility(View.VISIBLE);
				mCurrentCurrencyResultTxt.setText(String
						.valueOf(convertedAmount[Currency.CURRENT_CURRENCY]));
				mBTCResultTxt.setText(String.valueOf(convertedAmount[Currency.BTC]));
				mETHResultTxt.setText(String.valueOf(convertedAmount[Currency.ETH]));
			} else {
				Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show();
			}
		} catch(Exception e){
			if (edtInput.isEmpty()){
				Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
			}
		}
    }
}
