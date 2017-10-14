package com.example.android.personasmaterialdiplomado;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Persona p;
    private String cedula,nombre,apellido;
    private int Fot,sexo;
    private Bundle bundle;
    private Intent I;
    private ImageView foto;
    private Resources resources;
    private TextView ced,nomb,app,sex;
    private String [] opc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_persona);


       // Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
       // setSupportActionBar(toolbar);
       // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ced=(TextView)findViewById(R.id.lblCedulad);
        nomb =(TextView)findViewById(R.id.lblbNombred);
        app=(TextView)findViewById(R.id.lblApellidod);
        sex=(TextView)findViewById(R.id.lblSexod);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        foto = (ImageView)findViewById(R.id.fotopersona);
        resources = this.getResources();
        I = getIntent();
        bundle = I.getBundleExtra("datos");

        cedula=bundle.getString("cedula");
        nombre=bundle.getString("nombre");
        apellido=bundle.getString("apellido");
        Fot = bundle.getInt("foto");
        sexo = bundle.getInt("sexo");

        opc=resources.getStringArray(R.array.sexo);

        foto.setImageDrawable(ResourcesCompat.getDrawable(resources,Fot,null));
        collapsingToolbarLayout.setTitle(nombre+" "+apellido);
        ced.setText(cedula);
        nomb.setText(nombre);
        app.setText(apellido);
        sex.setText(opc[sexo]);
    }

    public void eliminar(View view){
        String negativo,positivo;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(resources.getString(R.string.Titulo_Eliminar_Mensaje));
        builder.setMessage(resources.getString(R.string.Eliminar_mensaje));
        positivo= resources.getString(R.string.SI_eliminar_persona);
        negativo = resources.getString(R.string.no_eliminar_persona);

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Persona p = new Persona(cedula);
                Datos.eliminarpersona(p);
                onBackPressed();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(DetallePersona.this,Principal.class);
        startActivity(i);

    }
}
