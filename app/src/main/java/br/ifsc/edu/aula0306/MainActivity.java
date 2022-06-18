package br.ifsc.edu.aula0306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.ifsc.edu.aula0306.controller.NotaController;
import br.ifsc.edu.aula0306.model.Nota;
import br.ifsc.edu.aula0306.view.NovaNotaActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Button button;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.lista);
        lista.setOnItemClickListener(this);

        button = findViewById(R.id.btnAdicionar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NovaNotaActivity.class);
                startActivity(i);
            }
        });

        carregaNotas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaNotas();
    }

    public void carregaNotas(){
        NotaController nc = new NotaController(getApplicationContext());
        ArrayList<Nota> notas = nc.getListaNota();

        if(notas != null){
            ArrayList<String> txt = new ArrayList<String>();

            for(int i = 0; i < notas.size(); i++){
                String t = notas.get(i).getTitulo();
                Long id = notas.get(i).getId();

                txt.add(t + "\t" + Long.toString(id));
            }

            String[] texto = txt.toArray(new String[0]);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, texto);
            lista.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long l) {
        String txt = ((TextView) view).getText().toString();

        String id = "";

        for (int i = txt.length(); i > 0; i--){
            if (txt.charAt(i - 1) == '\t')
                break;

            id += txt.charAt(i - 1);
        }

        Intent i = new Intent(getApplicationContext(), NovaNotaActivity.class);
        i.putExtra("id", Long.parseLong(id));

        startActivity(i);
    }
}
