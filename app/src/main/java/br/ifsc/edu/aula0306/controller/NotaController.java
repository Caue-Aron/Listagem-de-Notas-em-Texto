package br.ifsc.edu.aula0306.controller;

import android.content.Context;

import java.util.ArrayList;

import br.ifsc.edu.aula0306.model.Nota;
import br.ifsc.edu.aula0306.model.NotaDAO;

public class NotaController {
    private NotaDAO notaDAO;

    public NotaController(Context c){
        notaDAO = new NotaDAO(c);
    }

    public Nota cadastrarNota(Nota nota){
        if((nota.getTexto().length()<3) || ((nota.getTitulo().length()<3))){
            return null;
        }else{
            return this.notaDAO.criarNovaNota(nota);
        }
    }

    public ArrayList<Nota> getListaNota(){
        return notaDAO.getListaNome();
    }

    public Nota getNota(long id){
        return notaDAO.getNota(id);
    }

    public boolean updateNota(Nota n){
        return notaDAO.salvarNota(n);
    }
}
