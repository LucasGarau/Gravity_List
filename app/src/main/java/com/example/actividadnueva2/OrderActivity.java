package com.example.actividadnueva2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    private static final String TAG_ACTIVITY = OrderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    //Hace un toast en base a un mensaje.
    public void displayToast(String message) { Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    //Switch que dependiendo de el radio button muestra un toast o otro.
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

    }
}

