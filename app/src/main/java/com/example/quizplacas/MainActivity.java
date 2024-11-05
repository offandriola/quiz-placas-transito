package com.example.quizplacas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPlaca;
    private RadioGroup rgAlternativas;
    private RadioButton rbAlt1, rbAlt2, rbAlt3, rbAlt4;
    private Button btnResponder;

    // Placas e alternativas (exemplo para 5 placas)
    private int[] placas = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};
    private String[][] alternativas = {
            {"Proibido Estacionar", "Siga", "Ponte", "Cruzamento"},
            {"Velocidade Máxima", "Curva Perigosa", "Siga", "Área Escolar"},
            {"Atenção Pedestres", "Ponte", "Pare", "Curva à esquerda"},
            {"Cruzamento", "Estreitamento de Pista", "Velocidade Máxima", "Sinalização de Obras"},
            {"Animais Selvagens", "Dê a Preferência", "Parada de Ônibus", "Curva à Esquerda"}
    };
    private int[] respostasCorretas = {0, 3, 2, 1, 1}; // índices das alternativas corretas
    private int indexAtual = 0;
    private int totalCorretas = 0; // Variável para contar respostas corretas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Barra
        getWindow().setStatusBarColor(Color.parseColor("#5B5B5B"));
        getWindow().setNavigationBarColor(Color.parseColor("#5B5B5B"));

        imgPlaca = findViewById(R.id.imgPlaca);
        rgAlternativas = findViewById(R.id.rgAlternativas);
        rbAlt1 = findViewById(R.id.rbAlt1);
        rbAlt2 = findViewById(R.id.rbAlt2);
        rbAlt3 = findViewById(R.id.rbAlt3);
        rbAlt4 = findViewById(R.id.rbAlt4);
        btnResponder = findViewById(R.id.btnResponder);

        carregarPlaca(indexAtual);

        rgAlternativas.setOnCheckedChangeListener((group, checkedId) -> btnResponder.setEnabled(true));

        btnResponder.setOnClickListener(v -> {
            verificarResposta();
            proximaPlaca();
        });
    }

    private void carregarPlaca(int index) {
        imgPlaca.setImageResource(placas[index]);
        rbAlt1.setText(alternativas[index][0]);
        rbAlt2.setText(alternativas[index][1]);
        rbAlt3.setText(alternativas[index][2]);
        rbAlt4.setText(alternativas[index][3]);
        rgAlternativas.clearCheck();
        btnResponder.setEnabled(false);
    }

    private void verificarResposta() {
        int selectedId = rgAlternativas.getCheckedRadioButtonId();
        int respostaIndex = -1;

        if (selectedId == R.id.rbAlt1) {
            respostaIndex = 0;
        } else if (selectedId == R.id.rbAlt2) {
            respostaIndex = 1;
        } else if (selectedId == R.id.rbAlt3) {
            respostaIndex = 2;
        } else if (selectedId == R.id.rbAlt4) {
            respostaIndex = 3;
        }

        if (respostaIndex == respostasCorretas[indexAtual]) {
            totalCorretas++;
            Toast.makeText(this, "Você acertou!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Você errou!", Toast.LENGTH_SHORT).show();
        }
    }

    private void proximaPlaca() {
        indexAtual++;
        if (indexAtual < placas.length) {
            carregarPlaca(indexAtual);
        } else {
            // Fim do quiz, exibe o total de respostas corretas
            Intent intent = new Intent(MainActivity.this, Resultado.class);
            intent.putExtra("PONTUACAO", totalCorretas); // Envia os acertos com a chave "PONTUACAO"
            startActivity(intent);
            finish(); // Finaliza a activity atual
        }
    }

    public void Sair(View view) {
        finish();
    }
}
