package com.example.actividadnueva2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    private static final String TAG_ACTIVITY = OrderActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Bundle extras = getIntent().getExtras();
       String nombre= extras.getString("nombre");
      String  numero= extras.getString("numero");
        TextView textnombre = findViewById(R.id.nombre);
        TextView textnombre2 = findViewById(R.id.nombre2);
        textnombre.setText(nombre);
        textnombre2.setText(numero);
    }

    }


