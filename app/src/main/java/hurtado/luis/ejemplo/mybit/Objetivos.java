package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class Objetivos extends AppCompatActivity implements View.OnClickListener {

    String arreglo = "";
    TextView resultados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);


        resultados = (TextView)findViewById(R.id.resultadoasync);

    }

    public String enviarDatosget(String codEmp){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/objetivos_bit.php?codEmp="+codEmp);
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
                arreglo = arreglo +"Objetivo: "+((json.length()+1)-(i+1))+":\n" +
                        "Descripción: " + json.getJSONObject(i).getString("objetivos")+"\n"+
                        "Fecha de Inicio: " + json.getJSONObject(i).getString("fecha") + "\n" +
                        "Evaluación: " + json.getJSONObject(i).getString("cumplimiento") + "\n\n";

            } resultados.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }

    @Override
    public void onClick(View view) {



    }
    public void Home(View v){
        Intent intento_5 = new Intent(this,Home.class);
        startActivity(intento_5);
    }
    public void Carga(View v){
        Intent intento_5 = new Intent(this,CargarObjetivos.class);
        startActivity(intento_5);
    }

    public void Myinfo(View v){
        Intent intento_5 = new Intent(this,MyInfo.class);
        startActivity(intento_5);
    }

    public void descargar(View v){
        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(MainActivity.arreglo2.getText().toString());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){

                            Toast.makeText(getApplicationContext(),"Descargando Objetivos ",Toast.LENGTH_SHORT).show();
                            arreglo = "";
                        }
                        else
                        {
                            arreglo = "";
                            resultados.setText("No ha cargado Objetivos ");

                        }
                    }
                });
            }
        };
        tr.start();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}
