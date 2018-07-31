package com.example.komsic.cryptoconverter.view.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.data.db.CurrencyCardSubset;

import java.util.ArrayList;
import java.util.List;

public class DialogNewCard extends DialogFragment {
	private String currencyType;
	private Spinner mSpinner;
	private ArrayAdapter<String> mAdapter;

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.dialog_create_new_card, null);

		mSpinner = (Spinner) dialogView.findViewById(R.id
				.dialog_currency_chooser_spinner);

        mSpinner.setAdapter(mAdapter);

		mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
									   int position, long id) {

				String cur = (String) mSpinner.getSelectedItem();
				currencyType = cur.substring(0, 3);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
			}
		});

		final TextView createTxt = (TextView) dialogView.findViewById(R.id.create_txt);
		final TextView cancelTxt = (TextView) dialogView.findViewById(R.id.cancel_txt);


		builder.setView(dialogView).setMessage(getActivity().getString(R.string.new_currency));

		cancelTxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		createTxt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.addCurrencyToList(currencyType);

				dismiss();
			}
		});
		return builder.create();
	}

    public void init(Context context) {
        mAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,
                new ArrayList<String>());
    }

	public void setUnselectedCards(List<CurrencyCardSubset> unselectedCards) {
		ArrayList<String> unselectedCardsString = new ArrayList<>();
		for (CurrencyCardSubset currencyCardSubset : unselectedCards) {
			unselectedCardsString.add(currencyCardSubset.currencyType + " - "
					+ currencyCardSubset.currencyName);
		}

		mAdapter.clear();
		mAdapter.addAll(unselectedCardsString);
		mAdapter.notifyDataSetChanged();
	}
}
