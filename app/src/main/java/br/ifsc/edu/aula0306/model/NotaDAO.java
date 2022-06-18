package br.ifsc.edu.aula0306.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class NotaDAO {
    private Context mContext;
    private SQLiteDatabase db;

    public NotaDAO(Context mContext) {
        this.mContext = mContext;
        db = mContext.openOrCreateDatabase("notaDB", Context.MODE_PRIVATE, null);
        db.execSQL("create table if not exists NOTA(ID integer primary key autoincrement, TITULO text, TEXTO text)");
    }

    public Nota criarNovaNota(Nota n){
        ContentValues cv = new ContentValues();
        cv.put("TITULO",n.getTitulo());
        cv.put("TEXTO",n.getTexto());

        n.setId(db.insert("NOTA", null, cv));
        return n;
    }

    public boolean salvarNota(Nota n){
        ContentValues cv = new ContentValues();
        cv.put("TITULO",n.getTitulo());
        cv.put("TEXTO",n.getTexto());
        db.update("NOTA", cv, "ID = ?", new String[]{Long.toString(n.getId())});
        return true;
    }

    public boolean excluirNota(Nota n){
        long var = db.delete("NOTA", "ID = ?",new String[]{Long.toString(n.getId())});
        if (var == 0 )
            return false;

        return true;
    }

    public ArrayList<Nota> getListaNome(){
        ArrayList<Nota> notas = new ArrayList<Nota>();
        Cursor c = db.rawQuery("select * from NOTA", null);

        if (c.getCount() <= 0){
            Toast.makeText(mContext.getApplicationContext(), "Sem notas no sistema", Toast.LENGTH_SHORT).show();
            return null;
        }

        c.moveToFirst();

        do {
            int id = c.getInt(c.getColumnIndex("ID"));
            String titulo = c.getString(c.getColumnIndex("TITULO"));
            String txt = c.getString(c.getColumnIndex("TEXTO"));

            notas.add(new Nota(id, titulo, txt));

        }while(c.moveToNext());

        return notas;
    }


    public Nota getNota(long id) {
        String titulo = "", txt = "";
        Cursor c = null;

        c = db.rawQuery("select * from NOTA where ID = " + Long.toString(id), null);
        c.moveToFirst();

        titulo = c.getString(1);
        txt = c.getString(2);

        Nota n = new Nota(titulo, txt);;
        return n;
    }
}
