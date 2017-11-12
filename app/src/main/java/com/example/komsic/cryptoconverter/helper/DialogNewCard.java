package com.example.komsic.cryptoconverter.helper;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.activity.MainActivity;
import com.example.komsic.cryptoconverter.model.Currency;

import java.util.ArrayList;
import java.util.Arrays;

public class DialogNewCard extends DialogFragment {
	private Currency.CurrencyType mCurrencyType;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_create_new_card, null);

		ArrayList<Currency.CurrencyType> cType = new ArrayList<>();
		cType.addAll(Arrays.asList(Currency.CurrencyType.values()));
		cType.remove(0);

        final Spinner spinner = (Spinner) dialogView.findViewById(R.id
				.dialog_currency_chooser_spinner);
		spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
				cType));
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
				@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
										   int position, long id) {
					Currency.CurrencyType currencyType = (Currency.CurrencyType)
							spinner.getSelectedItem();
					get(currencyType);
				}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
				}
			});

		final TextView createTxt = (TextView) dialogView.findViewById(R.id.create_txt);
		final TextView cancelTxt = (TextView) dialogView.findViewById(R.id.cancel_txt);


		builder.setView(dialogView).setMessage(getString(R.string.new_currency));

		cancelTxt.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		createTxt.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				MainActivity callingActivity = (MainActivity) getActivity();
				callingActivity.create(mCurrencyType);

				dismiss();
			}
		});
		return builder.create();
	}

	private Currency.CurrencyType get(Currency.CurrencyType currencyType) {
		mCurrencyType = currencyType;
		return mCurrencyType;
	}
}
