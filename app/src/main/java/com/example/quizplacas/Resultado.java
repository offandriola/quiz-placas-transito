package com.example.quizplacas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {

    private TextView txtAcertos;
    private Button btnResponderNovamente;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Barra
        getWindow().setStatusBarColor(Color.parseColor("#5B5B5B"));
        getWindow().setNavigationBarColor(Color.parseColor("#5B5B5B"));

        // Inicializa os elementos da tela
        txtAcertos = findViewById(R.id.txtAcertos);
        btnResponderNovamente = findViewById(R.id.btnResponderNovamente);
        btnSair = findViewById(R.id.btnSair);  // Inicializa o botão Sair

        // Recebe os dados da tela anterior (pontuação)
        Intent intent = getIntent();
        int pontuacao = intent.getIntExtra("PONTUACAO", 0);

        // Atualiza os TextViews com os dados recebidos
        txtAcertos.setText("Acertos: " +pontuacao+"/5");

        // Configura o botão para reiniciar o quiz
        btnResponderNovamente.setOnClickListener(v -> {
            Intent responderIntent = new Intent(Resultado.this, MainActivity.class);
            startActivity(responderIntent);
            finish();
        });

        // Configura o botão para sair do app
        btnSair.setOnClickListener(v -> {
            finish();  // Fecha a aplicação
        });
    }
}
