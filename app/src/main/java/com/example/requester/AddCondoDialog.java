package com.example.requester;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import butterknife.ButterKnife;

public class AddCondoDialog extends Dialog {
    private Context context;

    public AddCondoDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_condo);
        ButterKnife.bind(this);
        Condo condo = null;

        condo = new Condo();
        EditText id = (EditText) findViewById(R.id.editTextId);
        EditText societe = (EditText) findViewById(R.id.editTextSociete);
        EditText cleunik = (EditText) findViewById(R.id.editTextCleunik);
        EditText copro = (EditText) findViewById(R.id.editTextCopro);
        EditText nom = (EditText) findViewById(R.id.editTextNom);
        EditText cp = (EditText) findViewById(R.id.editTextCp);
        EditText ville = (EditText) findViewById(R.id.editTextVille);

        Button create_btn = (Button) findViewById(R.id.buttonCreateDialog);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id.getText().toString().isEmpty() && !societe.getText().toString().isEmpty() && !cleunik.getText().toString().isEmpty() && !copro.getText().toString().isEmpty() && !nom.getText().toString().isEmpty() && !cp.getText().toString().isEmpty() && !ville.getText().toString().isEmpty())
                {
                    String Cid = id.getText().toString();
                    String Csociete = societe.getText().toString();
                    String Ccleunik = cleunik.getText().toString();
                    String Ccopro = copro.getText().toString();
                    String Cnom = nom.getText().toString();
                    String Ccp = cp.getText().toString();
                    String Cville = ville.getText().toString();


                    Toast.makeText(context, "Created", Toast.LENGTH_SHORT).show();
                    Condo item = new Condo(Cid, Csociete, Ccleunik, Ccopro, Cnom, Ccp, Cville);
                    DatabaseManager.getInstance(context).condoDao().insert(item);
                    dismiss();
                }else{
                    Toast.makeText(context, "Please fill informations", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = this;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }








}
