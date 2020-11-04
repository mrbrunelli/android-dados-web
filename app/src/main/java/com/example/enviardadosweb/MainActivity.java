package com.example.enviardadosweb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.enviardadosweb.classes.EnviarDadosWeb;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText edtTexto;
    TextView lblRetorno;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTexto = findViewById(R.id.edtTexto);
        lblRetorno = findViewById(R.id.lblRetorno);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarDados();
            }
        });
    }

    private void carregarDados() {
        try {
            EnviarDadosWeb enviarDadosWeb = new EnviarDadosWeb();
            HashMap<String, String> retorno = enviarDadosWeb.execute(edtTexto.getText().toString()).get();
            lblRetorno.setText(retorno.get("texto"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}