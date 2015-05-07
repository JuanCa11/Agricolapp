package com.bigbytesoft.agricolapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


public class RegisterActivity extends ActionBarActivity implements View.OnClickListener{

    private ImageButton register_button,back_button;
    private EditText usuario,nombre,password,correo;
    private Handler_sqlite helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuario = (EditText)findViewById(R.id.etUsername);
        nombre = (EditText)findViewById(R.id.etName);
        password = (EditText)findViewById(R.id.etPassword);
        correo = (EditText)findViewById(R.id.etCorreo);
        register_button = (ImageButton) findViewById(R.id.ibSignup);
        back_button = (ImageButton) findViewById(R.id.ibBack);

        register_button.setOnClickListener(this);
        back_button.setOnClickListener(this);

        helper = new Handler_sqlite(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == register_button.getId()) {
            helper.abrir();
            String usu = usuario.getText() + "", nom = nombre.getText() + "", pass = password.getText() + "", cor = correo.getText() + "";
            if (!usu.equalsIgnoreCase("") || !nom.equalsIgnoreCase("") || !pass.equalsIgnoreCase("") || !cor.equalsIgnoreCase("")) {
                helper.insertarReg(usu, nom, pass, cor);
                FragmentManager fragmentManager = getFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta",2);
                Intent j = new Intent(this, MainActivity.class);
                startActivity(j);
            } else {
                FragmentManager fragmentManager = getFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta",1);
            }
            helper.cerrar();
        }else if(v.getId()==back_button.getId()){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
