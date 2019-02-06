package hurtado.luis.ejemplo.mybit;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TiempoLibre extends AppCompatActivity implements View.OnClickListener{

    TextView texto,diaslib,t1,t2,texto2,texto3;
    EditText editText;
    Spinner spinner;
    DatePicker datepicker;
    Button botonvac;
    String fechaselect;
    String comentariolong;
    int dias;
    int diaresta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo_libre);



        t1 = (TextView) findViewById(R.id.visor);
        t2 = (TextView) findViewById(R.id.visual);
        texto2 = (TextView)findViewById(R.id.texto2);
        texto3 = (TextView)findViewById(R.id.texto3);
        editText = (EditText)findViewById(R.id.editText);
        botonvac = (Button)findViewById(R.id.solicitar);
        botonvac.setOnClickListener(this);
        diaslib = (TextView) findViewById(R.id.diaslib);
        diaslib.setText(MainActivity.arreglo16.getText());


        spinner = (Spinner) findViewById(R.id.spinner);
        datepicker = (DatePicker) findViewById(R.id.datepicker1);

        dias = Integer.parseInt(MainActivity.arreglo16.getText().toString());
        List list = new ArrayList();

        for(int i=1;i<=dias;i++) {

            list.add(i);
        }

        fechaselect = String.valueOf(datepicker.getDayOfMonth()
                + "-" + String.valueOf(datepicker.getMonth()+1
                + "-" + String.valueOf(datepicker.getYear())));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TiempoLibre.this,"Dias seleccionados: "+String.valueOf(spinner.getSelectedItem()),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void Visor (View view){
    t2.setText("Solicitud de tiempo Libre \n\n" + "Dias: " + String.valueOf(spinner.getSelectedItem()) +
    "\n\n" + " Desde el " + String.valueOf(datepicker.getDayOfMonth()
            + "-" + String.valueOf(datepicker.getMonth()+1
            + "-" + String.valueOf(datepicker.getYear()))) + "\n\n" + "Comentario: " + editText.getText().toString() +"\n\n");
    }

    // TODO: ENVIAR LA SOLICITUD A LA BASE DE DATOS

    public String enviarDatosget(String codEmp, String cant, String fecha, String coment, String departamento, String diaresta){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;


        try {

            url = new URL("http://androidwstest.esy.es/vacaciones_bit.php?codEmp="+codEmp+"&cant="+cant+"&fecha="+fecha+
                    "&coment="+coment+"&departamento="+departamento+"&diaresta="+diaresta);
            //url = URLEncoder.encode(url, "UTF-8");

            HttpURLConnection conection = (HttpURLConnection)url.openConnection();
            respuesta=conection.getResponseCode();

            result = new StringBuilder();
            if(respuesta == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine())!=null){
                    result.append(linea);

                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return  result.toString();

    }

    public  int obtenerDatosJSON(String response){
        int res=0;
        try
        {

            JSONArray json = new JSONArray(response);

            for(int i=0;i<json.length();i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);

                String lacantidad = jsonobject.optString("cantidad");
                String lafecha = jsonobject.optString("fecha");
                String elstatus = jsonobject.optString("status");
                String elcomentario = jsonobject.optString("comentario");

            }

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.solicitar:

                fechaselect = String.valueOf(datepicker.getDayOfMonth()
                        + "-" + String.valueOf(datepicker.getMonth()+1
                        + "-" + String.valueOf(datepicker.getYear())));

                comentariolong = editText.getText().toString();

                diaresta = dias - Integer.parseInt(String.valueOf(spinner.getSelectedItem()));


        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(MainActivity.arreglo2.getText().toString(),
                        String.valueOf(spinner.getSelectedItem()),
                        fechaselect,comentariolong.replaceAll(" ","%20"),MainActivity.arreglo13.getText().toString(),
                        String.valueOf(diaresta));

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){
                            Toast.makeText(getApplicationContext(),"Solicitud registrada ",Toast.LENGTH_SHORT).show();

                          MainActivity.arreglo16.setText(String.valueOf(diaresta));

                            Intent intent = new Intent(getApplicationContext(),MyInfo.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(),"No registrada",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        tr.start();

                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
