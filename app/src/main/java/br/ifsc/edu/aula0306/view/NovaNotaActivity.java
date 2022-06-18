package br.ifsc.edu.aula0306.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.ifsc.edu.aula0306.R;
import br.ifsc.edu.aula0306.controller.NotaController;
import br.ifsc.edu.aula0306.model.Nota;

public class NovaNotaActivity extends AppCompatActivity {

    NotaController notaController;
    EditText edTitulo, edTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);
        notaController = new NotaController(getApplicationContext());
        edTitulo = findViewById(R.id.txtTitulo);
        edTexto = findViewById(R.id.txtTexto);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long id = extras.getLong("id");

            Nota n = notaController.getNota(id);

            edTitulo.setText(n.getTitulo());
            edTexto.setText(n.getTexto());
        }
    }

    public void novaNota(View view){
        String titulo = edTitulo.getText().toString();
        String texto = edTexto.getText().toString();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long id = extras.getLong("id");

            notaController.updateNota(new Nota(id, titulo, texto));
            finish();
            return;
        }

        Nota nota = notaController.cadastrarNota(new Nota(titulo, texto));

        if(nota.getId() != null){
            Toast.makeText(getApplicationContext(), "Nota Cadastrada - " + Long.toString(nota.getId()), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Nota não cadastrada", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
