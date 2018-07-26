package com.example.komsic.cryptoconverter.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.komsic.cryptoconverter.R;
import com.example.komsic.cryptoconverter.activity.MainActivity;

/**
 * Created by komsic on 11/10/2017.
 */

public class DialogError extends DialogFragment {
    private String mErrorMessage;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_fetch_error, null);

        TextView errorMessageTxt = (TextView) dialogView.findViewById(R.id.error_message_txt);
        TextView fetctTxt = (TextView) dialogView.findViewById(R.id.fetch_txt);
        TextView cancelTxt = (TextView) dialogView.findViewById(R.id.dialog_error_cancel_txt);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setView(dialogView).setMessage(getContext().getString(R.string.error));
        }


        errorMessageTxt.setText(getErrorMessage());
        fetctTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).fetchData();

                dismiss();
            }
        });

        cancelTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorMessage = errorMessage;
    }
}
