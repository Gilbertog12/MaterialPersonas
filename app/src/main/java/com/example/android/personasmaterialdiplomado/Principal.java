package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorPersona.onpersonaClickLIstener{
    private RecyclerView listado;
    private ArrayList<Persona> personas;
    private Resources res;
    private AdaptadorPersona adapter;
    private LinearLayoutManager llm;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listado = (RecyclerView)findViewById(R.id.lstOpciones);

        res = this.getResources();
        personas = Datos.obtenerPersonas();


        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new AdaptadorPersona(this.getApplicationContext(),personas,this);

        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               i = new Intent(Principal.this,CrearPersonas.class);
                startActivity(i);
            }
        });
    }


    @Override
    public void onpersonaClick(Persona p) {
        Intent I = new Intent(Principal.this, DetallePersona.class);
        Bundle b = new Bundle();
        b.putInt("foto", p.getFoto());
        b.putString("cedula", p.getCedula());
        b.putString("nombre", p.getNombre());
        b.putString("apellido", p.getApellido());
        b.putInt("sexo", p.getSexo());
        I.putExtra("datos", b);
        startActivity(I);
    }

}
