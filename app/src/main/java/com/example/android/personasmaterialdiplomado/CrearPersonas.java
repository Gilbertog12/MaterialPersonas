package com.example.android.personasmaterialdiplomado;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CrearPersonas extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCedula;
    private TextInputLayout cajaApellido, cajaNombre, cajaCedula;

    private ArrayList<Integer>fotos;
    private ArrayAdapter<String>adapter;
    private Spinner sexo;
    private Resources res;
    private String []opc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        txtCedula = (EditText)findViewById(R.id.txtCedula);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        cajaNombre = (TextInputLayout)findViewById(R.id.cajaApellido);
        cajaNombre = (TextInputLayout)findViewById(R.id.cajaNombre);
        cajaApellido = (TextInputLayout)findViewById(R.id.cajaApellido);
        res= this.getResources();
        sexo = (Spinner)findViewById(R.id.cmbsexo);
        opc= res.getStringArray(R.array.sexo);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,opc);
        sexo.setAdapter(adapter);



        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }

    public void guardar(View V){
        Persona p = new Persona(Metodos.fotoAleatoria(fotos),txtCedula.getText().toString(),txtNombre.getText().toString(), txtApellido.getText().toString(),sexo.getSelectedItemPosition());
        p.guardar();
        Snackbar.make(V,res.getString(R.string.mensaje),Snackbar.LENGTH_LONG)
                .setAction("Action",null).show();

        limpiar();
    }
    public void  limpiar(View V){
        limpiar();
    }

    private  void limpiar(){
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        sexo.setSelection(0);
        txtNombre.requestFocus();


    }
    public void onBackPressed(){
        finish();
        Intent i = new Intent(CrearPersonas.this,Principal.class);
        startActivity(i);

    }
}
