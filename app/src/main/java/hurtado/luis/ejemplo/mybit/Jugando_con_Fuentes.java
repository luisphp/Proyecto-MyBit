package hurtado.luis.ejemplo.mybit;

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

public class Jugando_con_Fuentes extends AppCompatActivity {

    EditText gett;
    TextView text,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,textin;
    Typeface Caviar_Dreams, Caviar_Dreams_Bold, Caviar_Dreams_Boldt_Italic, Caviar_Dreams_Italic,
            Champagne_Limon, Champagne_Limon_Bold, Embossed, Fluted, LemonMilk, Plain, Sensation, Shadow;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugando_con__fuentes);


        textin = (TextView) findViewById(R.id.textopin);

        gett = (EditText) findViewById(R.id.get);
        text = (TextView) findViewById(R.id.textop);
        text2 = (TextView) findViewById(R.id.textop2);
        text3 = (TextView) findViewById(R.id.textop3);
        text4 = (TextView) findViewById(R.id.textop4);
        text5 = (TextView) findViewById(R.id.textop5);
        text6 = (TextView) findViewById(R.id.textop6);
        text7 = (TextView) findViewById(R.id.textop7);
        text8 = (TextView) findViewById(R.id.textop8);
        text9 = (TextView) findViewById(R.id.textop9);
        text10 = (TextView) findViewById(R.id.textop10);
        text11 = (TextView) findViewById(R.id.textop11);
        text12 = (TextView) findViewById(R.id.textop12);



        Caviar_Dreams = Typeface.createFromAsset(getAssets(),"CaviarDreams.ttf");
        text.setTypeface(Caviar_Dreams);

        Caviar_Dreams_Bold = Typeface.createFromAsset(getAssets(),"CaviarDreams_Bold.ttf");
        text2.setTypeface(Caviar_Dreams_Bold);

        Caviar_Dreams_Boldt_Italic = Typeface.createFromAsset(getAssets(),"CaviarDreams_BoldItalic.ttf");
        text3.setTypeface(Caviar_Dreams_Boldt_Italic);

        Caviar_Dreams_Italic = Typeface.createFromAsset(getAssets(),"CaviarDreams_Italic.ttf");
        text4.setTypeface(Caviar_Dreams_Italic);

        Champagne_Limon = Typeface.createFromAsset(getAssets(),"Champagne & Limousines.ttf");
        text5.setTypeface(Champagne_Limon);

        Champagne_Limon_Bold = Typeface.createFromAsset(getAssets(),"Champagne & Limousines Bold.ttf");
        text6.setTypeface(Champagne_Limon_Bold);

        Embossed = Typeface.createFromAsset(getAssets(),"Embossed Germanica.ttf");
        text7.setTypeface(Embossed);

        Fluted = Typeface.createFromAsset(getAssets(),"Fluted Germanica.ttf");
        text8.setTypeface(Fluted);

        LemonMilk = Typeface.createFromAsset(getAssets(),"LemonMilk.otf");
        text9.setTypeface(LemonMilk);

        Plain = Typeface.createFromAsset(getAssets(),"Plain Germanica.ttf");
        text10.setTypeface(Plain);

        Sensation = Typeface.createFromAsset(getAssets(),"Sensations and Qualities.ttf");
        text11.setTypeface(Sensation);

        Shadow = Typeface.createFromAsset(getAssets(),"Shadowed Germanica.ttf");
        text12.setTypeface(Shadow);


        // SPINNER 1 SELECCION DE Tipo de Letra:
        spinner = (Spinner) findViewById(R.id.spinners);

        List list = new ArrayList();

        list.add("Caviar_Dreams");
        list.add("Caviar_Dreams_Bold");
        list.add("Caviar_Dreams_Boldt_Italic");
        list.add("Caviar_Dreams_Italic");
        list.add("Champagne_Limon");
        list.add("Champagne_Limon_Bold");
        list.add("Embossed");
        list.add("Fluted");
        list.add("LemonMilk");
        list.add("Plain");
        list.add("Sensation");
        list.add("Shadow");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public  void CambiodeFuente(View v) {



        if (spinner.getSelectedItem().equals("Caviar_Dreams")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Caviar_Dreams);
        } else if (spinner.getSelectedItem().equals("Caviar_Dreams_Bold")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Caviar_Dreams_Bold);

        } else if (spinner.getSelectedItem().equals("Caviar_Dreams_Boldt_Italic")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Caviar_Dreams_Boldt_Italic);

        } else if (spinner.getSelectedItem().equals("Champagne_Limon")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Champagne_Limon);
        } else if (spinner.getSelectedItem().equals("Champagne_Limon_Bold")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Champagne_Limon_Bold);
        }else if (spinner.getSelectedItem().equals("Embossed")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Embossed);
        }else if (spinner.getSelectedItem().equals("Fluted")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Fluted);
        }else if (spinner.getSelectedItem().equals("LemonMilk")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(LemonMilk);
        }else if (spinner.getSelectedItem().equals("Plain")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Plain);
        }else if (spinner.getSelectedItem().equals("Sensation")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Sensation);
        }else if (spinner.getSelectedItem().equals("Shadow")) {
            textin.setText(gett.getText().toString());
            textin.setTypeface(Shadow);
        }





    }

}
