package hurtado.luis.ejemplo.mybit;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import static java.nio.charset.StandardCharsets.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Editor_de_Procesos extends AppCompatActivity implements View.OnClickListener{

    Spinner spinner,spinner2;
    EditText pro1;
    Button clean,save;
    FloatingActionButton descargar;
    String query1,proceso_uno,proceso_dos,proceso_tres,proceso_cuatro,proceso_cinco,marcador,marcador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_de__procesos);

        pro1 = (EditText)findViewById(R.id.p1);

        clean = (Button)findViewById(R.id.Limpiar);
        save = (Button)findViewById(R.id.Guardar);

        descargar = (FloatingActionButton)findViewById(R.id.bajarproceso);
        descargar.setOnClickListener(this);

        // SPINNER 1 SELECCION DE DEPARTAMENTO:
        spinner = (Spinner) findViewById(R.id.spinner);

        List list = new ArrayList();
        list.add("Contenido");
        list.add("Shipping");
        list.add("Comercial");
        list.add("Legal");
        list.add("Servicio al Cliente");
        list.add("RRHH");
        list.add("Mercadeo");
        list.add("Tecnologia");

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




        // SPINNER 2: SELECCION DE PROCESO

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        List list2 = new ArrayList();
        list2.add("proceso1");
        list2.add("proceso2");
        list2.add("proceso3");
        list2.add("proceso4");
        list2.add("proceso5");

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView2, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void onClick(View view) {

        marcador = String.valueOf(spinner.getSelectedItem());
        marcador2 = String.valueOf(spinner2.getSelectedItem());

        Toast.makeText(this.getApplicationContext(),"Descargando "+ marcador2 +" de " + marcador,Toast.LENGTH_SHORT).show();

        Thread tr=new Thread(){

            @Override
            public void run() {
                final String resultado=enviarDatosget(marcador.toString().replaceAll(" ","%20"));

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){

                            Toast.makeText(getApplicationContext(),"Listo! ",Toast.LENGTH_SHORT).show();
                            proceso_uno = "";
                            proceso_dos = "";
                            proceso_tres = "";
                            proceso_cuatro = "";
                            proceso_cinco = "";
                        }
                        else
                        {

                            Toast.makeText(getApplicationContext(),"Intenta de nuevo",Toast.LENGTH_SHORT).show();
                            proceso_uno = "";
                            proceso_dos = "";
                            proceso_tres = "";
                            proceso_cuatro = "";
                            proceso_cinco = "";

                            pro1.setText("");

                        }
                    }
                });
            }
        };
        tr.start();


    }


    public void Limpiar (View v){

        pro1.setText("");

    }

    public void Guardar (View v){

        marcador = String.valueOf(spinner.getSelectedItem());
        marcador2 = String.valueOf(spinner2.getSelectedItem());

        String var = pro1.getText().toString();
        if(TextUtils.isEmpty(var)) {
            pro1.setError("No puede estar Vacio");
            return;
        } else {

                    Thread tr = new Thread() {

                        @TargetApi(Build.VERSION_CODES.KITKAT)
                        @Override
                        public void run() {





 final String resultado = enviarDatosgetdos(marcador.toString().replaceAll(" ","%20"),marcador2.toString().replaceAll(" ","%20"),
         URLEncoder.encode(pro1.getText().toString()));

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    int r = obtenerDatosJSONdos(resultado);
                                    if (r > 0) {

                                        Toast.makeText(getApplicationContext(), "Listo! ", Toast.LENGTH_SHORT).show();
                                        proceso_uno = "";
                                        proceso_dos = "";
                                        proceso_tres = "";
                                        proceso_cuatro = "";
                                        proceso_cinco = "";
                                    } else {

                                        Toast.makeText(getApplicationContext(), "Intenta de nuevo", Toast.LENGTH_SHORT).show();
                                        proceso_uno = "";
                                        proceso_dos = "";
                                        proceso_tres = "";
                                        proceso_cuatro = "";
                                        proceso_cinco = "";

                                        pro1.setText("");


                                    }
                                }
                            });
                        }
                    };
                    tr.start();


        }

    }


    //TODO ASYNC TASK PARA BAJAR LOS PROCESOS Y COLORCARLOS EN LOS EDITABLES
    public String enviarDatosget(String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/descargar_procesos.php?departamento="+departamento);
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
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
            return "ERROR_IN_CODE";
        } catch (IOException e) {
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
            return "ERROR_IN_CODE";
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

                proceso_uno = json.getJSONObject(i).getString("proceso1");
                        proceso_dos = json.getJSONObject(i).getString("proceso2");
                        proceso_tres = json.getJSONObject(i).getString("proceso3");
                        proceso_cuatro = json.getJSONObject(i).getString("proceso4");
                        proceso_cinco = json.getJSONObject(i).getString("proceso5");



            }

            if (marcador2.equals("proceso1") ){

            pro1.setText(proceso_uno);

            }else if (marcador2.equals("proceso2")){

            pro1.setText(proceso_dos);

            }else if (marcador2.equals("proceso3")){

            pro1.setText(proceso_tres);

             }else if (marcador2.equals("proceso4")){

            pro1.setText(proceso_cuatro);
              }
            else if
                (marcador2.equals("proceso5")){

            pro1.setText(proceso_cinco);
              }


            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }




    //TODO ASYNC TASK PARA SUBIR LA ACTUALIZACION ***************************

    public String enviarDatosgetdos(String departamento,
                                    String proceso, String carga){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/cargar_procesos.php?departamento="+departamento+
            "&proceso="+proceso+"&carga="+carga);
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
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
            return "ERROR_IN_CODE";
        } catch (IOException e) {
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());
            e.printStackTrace();
            return "ERROR_IN_CODE";
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

                proceso_uno = json.getJSONObject(i).getString("proceso1");
                proceso_dos = json.getJSONObject(i).getString("proceso2");
                proceso_tres = json.getJSONObject(i).getString("proceso3");
                proceso_cuatro = json.getJSONObject(i).getString("proceso4");
                proceso_cinco = json.getJSONObject(i).getString("proceso5");



            }    if (marcador2.equals("proceso1") ){

            pro1.setText(proceso_uno);

        }else if (marcador2.equals("proceso2")){

            pro1.setText(proceso_dos);

        }else if (marcador2.equals("proceso3")){

            pro1.setText(proceso_tres);

        }else if (marcador2.equals("proceso4")){

            pro1.setText(proceso_cuatro);
        }
        else if
                (marcador2.equals("proceso5")){

            pro1.setText(proceso_cinco);
        }


            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }

}
