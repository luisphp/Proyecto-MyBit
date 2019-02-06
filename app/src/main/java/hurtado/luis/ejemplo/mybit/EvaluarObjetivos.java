package hurtado.luis.ejemplo.mybit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.List;

public class EvaluarObjetivos extends AppCompatActivity implements View.OnClickListener{

    EditText codigoobj,comentario;
    Button enviar,bajar;
    ImageButton buscar;
    TextView mostrar;
    String arreglo="";
    String evaluacion = "";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluar_objetivos);

        codigoobj = (EditText)findViewById(R.id.vertodos);
        comentario = (EditText) findViewById(R.id.comenteval);
        enviar = (Button)findViewById(R.id.enviareval);
        enviar.setOnClickListener(this);
        bajar = (Button)findViewById(R.id.btnvertodos);
        bajar.setOnClickListener(this);
        buscar = (ImageButton)findViewById(R.id.buscador2);
        buscar.setOnClickListener(this);
        mostrar = (TextView)findViewById(R.id.txtdtobjetivo);

        spinner = (Spinner) findViewById(R.id.spinner);


        List list = new ArrayList();
        list.add("10%");
        list.add("20%");
        list.add("30%");
        list.add("40%");
        list.add("50%");
        list.add("60%");
        list.add("70%");
        list.add("80%");
        list.add("90%");
        list.add("100%");

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


    //TODO: ASYNC TASK PARA VER TODO LOS OBJETIVOS DE TODOS LOS EMPLEADOS DEL DEPARTAMENTO AL QUE PERTENECE EL GERENTE.
    //TODO:  el webservice se llama evaluar_objetivos_bit1.php


    public String enviarDatosget(String codEmp, String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/evaluar_objetivos_bit1.php?codEmp="+codEmp+"&departamento="+departamento);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);
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
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);

                arreglo = arreglo + "Código: " +json.getJSONObject(i).getString("codObjetivo") + "\n" +
                        "Objetivo: "+json.getJSONObject(i).getString("objetivos") + "\n" +
                        "Nombre: "+json.getJSONObject(i).getString("nombre") + " " +json.getJSONObject(i).getString("apellido") + "\n" +
                        "Departamento: "+json.getJSONObject(i).getString("departamento") + "\n" +
                        "Evaluación: "+json.getJSONObject(i).getString("cumplimiento") + "\n" +
                        "Inicio el: "+json.getJSONObject(i).getString("fecha") + "\n\n";
            } mostrar.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


    //TODO: ASYNC TASK PARA VER EL OBJETIVO ASIGNADO A UN NUMERO DE CODIGO
    //TODO:  el webservice se llama evaluar_objetivos_bit2.php


    public String enviarDatosgetdos(String codEmp, String departamento, String codObjetivo){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/evaluar_objetivos_bit2.php?codEmp="+codEmp+"&departamento="+departamento
            +"&codObjetivo="+codObjetivo);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);

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

    public  int obtenerDatosJSONdos(String response){
        int res=0;
        try

        {

            JSONArray json = new JSONArray(response);
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);

                arreglo = arreglo + "Código: " +json.getJSONObject(i).getString("codObjetivo") + "\n" +
                        "Objetivo: "+json.getJSONObject(i).getString("objetivos") + "\n" +
                        "Nombre: "+json.getJSONObject(i).getString("nombre") + " " +json.getJSONObject(i).getString("apellido") + "\n" +
                        "Departamento: "+json.getJSONObject(i).getString("departamento") + "\n" +
                        "Evaluación: "+json.getJSONObject(i).getString("cumplimiento") + "\n" +
                        "Inicio el: "+json.getJSONObject(i).getString("fecha") + "\n\n";

            }
            mostrar.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


    //TODO: ASYNC TASK PARA SUBIR LA EVALUACION
    //TODO:  el webservice se llama evaluar_objetivos_bit3.php


    public String enviarDatosgettres(String codObjetivo, String eval){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {

        url = new URL("http://androidwstest.esy.es/evaluar_objetivos_bit3.php?codObjetivo="+codObjetivo+"&evaluacion="+eval);
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

    public  int obtenerDatosJSONtres(String response){
        int res=0;
        try

        {

            JSONArray json = new JSONArray(response);
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);

                arreglo = arreglo + "Código: " +json.getJSONObject(i).getString("codObjetivo") + "\n" +
                        "Objetivo: "+json.getJSONObject(i).getString("objetivos") + "\n" +
                        "Nombre: "+json.getJSONObject(i).getString("nombre") + " " +json.getJSONObject(i).getString("apellido") + "\n" +
                        "Departamento: "+json.getJSONObject(i).getString("departamento") + "\n" +
                        "Evaluación: "+json.getJSONObject(i).getString("cumplimiento") + "\n" +
                        "Inicio el: "+json.getJSONObject(i).getString("fecha") + "\n\n";
            } mostrar.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }

// EJECUCION DE LOS BOTONES CON SUS RESPECTIVOS HILOS

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnvertodos:

                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String resultado = enviarDatosget(MainActivity.arreglo2.getText().toString().replaceAll(" ","%20"),
                                MainActivity.arreglo13.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if (r > 0) {

                                    Toast.makeText(getApplicationContext(), "Descargando Objetivos del departamento " +
                                            MainActivity.arreglo13.getText().toString(), Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                } else {
                                    arreglo = "";
                                    mostrar.setText("No han cargado Objetivos ");

                                }
                            }
                        });
                    }
                };
                tr.start();


                break;

            case R.id.buscador2:
                Thread trdos = new Thread() {
                    @Override
                    public void run() {
                        final String resultadodos = enviarDatosgetdos(MainActivity.arreglo2.getText().toString().replaceAll(" ","%20"),
                                MainActivity.arreglo13.getText().toString().replaceAll(" ","%20"), codigoobj.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSONdos(resultadodos);
                                if (r > 0) {

                                Toast.makeText(getApplicationContext(), "Descargando Objetivos del código " +
                                            codigoobj.getText().toString(), Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                } else {
                                    arreglo = "";
                                    mostrar.setText("Sin resultados de la busqueda del código" +
                                            codigoobj.getText().toString());

                                }
                            }
                        });
                    }
                };
                trdos.start();
                break;
            case R.id.enviareval:

                evaluacion = comentario.getText().toString()+" - Obtuviste una calificacion del "
                        +String.valueOf(spinner.getSelectedItem()).replaceAll("%","%20");

                Thread trtres = new Thread() {
                    @Override
                    public void run() {
                        final String resultadoepa = enviarDatosgettres(codigoobj.getText().toString().replaceAll(" ","%20"),evaluacion.replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSONtres(resultadoepa);
                                if (r > 0) {

                                    Toast.makeText(getApplicationContext(), "Objetivo " +
                                    codigoobj.getText().toString()+" evaluado ", Toast.LENGTH_SHORT).show();
                                    comentario.setText("");
                                    arreglo = "";

                                }
                                else
                                {
                                    arreglo = "";
                                    Toast.makeText(getApplicationContext(), "Intenta cargar nuevamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                trtres.start();


        }
    }


    public void subirevaluacion (View v){



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
