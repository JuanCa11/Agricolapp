package com.bigbytesoft.agricolapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private View signup,login;
    public Handler_sqlite helper;
    public TextView user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.tvSignUp);
        login = findViewById(R.id.ibLogin);
        user = (TextView)findViewById(R.id.etUsername);
        pass = (TextView)findViewById(R.id.etPassword);

        helper = new Handler_sqlite(this);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        helper.abrir();
        if(v.getId()==signup.getId()) {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        }else if (v.getId() == login.getId()) {
            String usuario = user.getText() + "", contra = pass.getText() + "";
            if (!usuario.equalsIgnoreCase("") || !contra.equalsIgnoreCase("")) {
                ArrayList<String> usuarios = helper.leer();
                int size = usuarios.size();
                if (size != 0) {
                    for (int i = 0; i < size; i++) {
                        String[] cadena = usuarios.get(i).split(",");
                        if (usuario.equalsIgnoreCase(cadena[1]) && contra.equalsIgnoreCase(cadena[3])) {
                            Intent j = new Intent(this, AccountActivity.class);
                            j.putExtra("usuario",usuario);
                            startActivity(j);
                        } else {
                            Intent k = new Intent(this, RegisterActivity.class);
                            startActivity(k);
                        }
                    }
                } else {
                    Intent y = new Intent(this, RegisterActivity.class);
                    startActivity(y);
                }
            } else {
                FragmentManager fragmentManager = getFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager,"tagAlerta",1);
            }
        }
        helper.cerrar();
    }
}
