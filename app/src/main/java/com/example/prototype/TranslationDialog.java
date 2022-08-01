package com.example.prototype;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatDialogFragment;

public class TranslationDialog extends AppCompatDialogFragment {

    TextView translationText;
    private String text;

    public TranslationDialog(String _text){
        text = _text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.translation_dialog, null);

        translationText = view.findViewById(R.id.translationText);
        translationText.setText(text);

        builder.setView(view).setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });



        return builder.create();

    }
}
