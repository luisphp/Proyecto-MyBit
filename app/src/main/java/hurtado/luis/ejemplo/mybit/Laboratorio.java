package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Laboratorio extends AppCompatActivity {


    TextView textin;
    Typeface LemonMilk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratorio);

    textin = (TextView) findViewById(R.id.textopinLabora);

    LemonMilk = Typeface.createFromAsset(getAssets(),"LemonMilk.otf");
    textin.setTypeface(LemonMilk);

    }

    public void salto1 (View v){
        Intent intento_1 = new Intent(this,Jugando_con_Fuentes.class);
        startActivity(intento_1);
    }

    public void salto2 (View v){
        Intent intento_2 = new Intent(this,ListView_personalizado.class);
        startActivity(intento_2);
    }

    public void Navigation_Drawer (View v){
        Intent intento_2 = new Intent(this,Menu_Navegador_Drawer.class);
        startActivity(intento_2);
    }

}
