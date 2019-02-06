package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SeccionGerentes extends AppCompatActivity implements View.OnClickListener{


    TextView texto;
    FloatingActionButton boton;
    String arreglo = " ";
    EditText recibir;
    Button mandar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion_gerentes);

        texto = (TextView)findViewById(R.id.contenido);
        boton = (FloatingActionButton)findViewById(R.id.faborito);
        boton.setOnClickListener(this);
        recibir = (EditText)findViewById(R.id.txtcodigovac);
        mandar= (Button)findViewById(R.id.aprobar);




        FloatingActionButton Home = (FloatingActionButton) findViewById(R.id.Home);


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);}});


    }




    public String enviarDatosget(String departamento, String codEmp){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/aprobacion_vacaciones.php?departamento="+departamento+"&codEmp="+codEmp);
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
                arreglo = arreglo +"Solicitud "+((json.length()+1)-(i+1))+":\n"+
                        "Código de Solicitud: " + json.getJSONObject(i).getString("codVac")+"\n" +
                        "Cantidad de dias solicitados: " + json.getJSONObject(i).getString("cantidad")+"\n"+
                        "Fecha de Salida: " + json.getJSONObject(i).getString("fecha") + "\n" +
                        "Nombre Empleado: "+json.getJSONObject(i).getString("nombre")
                        +" "+json.getJSONObject(i).getString("apellido")+"\n"+
                        "Departamento: "+json.getJSONObject(i).getString("departamento")+"\n"+
                        "Status de la solicitud: " + json.getJSONObject(i).getString("status") + "\n" +
                        "Comentario realizado: " + json.getJSONObject(i).getString("comentario") + "\n\n";
            } texto.setText(arreglo);

            if(json.length()>0){
                res=1;
            }
        }catch (Exception e){
        }
        return res;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.faborito:
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String resultado = enviarDatosget(MainActivity.arreglo13.getText().toString(),
                                MainActivity.arreglo2.getText().toString());
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(), "Solicitudes encontradas ", Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                } else {
                                    arreglo = "";
                                    texto.setText(" Sin solicitudes registradas ");
                                    Toast.makeText(getApplicationContext(), "Sin Solicitudes realizadas", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();
        break;

            case R.id.aprobar:

                Toast.makeText(getApplicationContext(), "Actualizando... ", Toast.LENGTH_SHORT).show();

                Thread tresa = new Thread() {
                    @Override
                    public void run() {
                        final String resultadotresa = enviarDatosgettresa(recibir.getText().toString(),
                                MainActivity.arreglo13.getText().toString(),
                                MainActivity.arreglo2.getText().toString());
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSONtresa(resultadotresa);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(), "Solicitudes Aprobada ", Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                } else {
                                    arreglo = "";
                                    Toast.makeText(getApplicationContext(), "Intenta de nuevo", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tresa.start();






                break;
        }

    }



  //CODIGO PARA APROBAR

    public String enviarDatosgettresa(String codVac,String departamento,String codEmp){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/editor_aprob_vaca.php?codVac="+codVac+"&departamento="+departamento+"&codEmp="+codEmp);
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



    public  int obtenerDatosJSONtresa(String responsetresa){
        int restresa=0;
        try
        {
            JSONArray json = new JSONArray(responsetresa);
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);
                arreglo = arreglo +"Solicitud "+((json.length()+1)-(i+1))+":\n"+
                        "Código de Solicitud: " + json.getJSONObject(i).getString("codVac")+"\n" +
                        "Cantidad de dias solicitados: " + json.getJSONObject(i).getString("cantidad")+"\n"+
                        "Fecha de Salida: " + json.getJSONObject(i).getString("fecha") + "\n" +
                        "Departamento: "+json.getJSONObject(i).getString("departamento")+"\n"+
                        "Nombre Empleado: "+json.getJSONObject(i).getString("nombre")
                        +" "+json.getJSONObject(i).getString("apellido")+"\n"+
                        "Status de la solicitud: " + json.getJSONObject(i).getString("status") + "\n" +
                        "Comentario realizado: " + json.getJSONObject(i).getString("comentario") + "\n\n";
            } texto.setText(arreglo);

            if(json.length()>0){
                restresa=1;
            }
        }catch (Exception e){
        }
        return restresa;
    }
























    public void Mandar(View v){
//TODO: AQUI VAMOS A  ACTUALIZAR LA LISTA EN CUANTO SE APRUEBE UNA SOLICITUD
// TODO: ES DECIR SE VA A CAMBIAR LE STATUS Y DE PASO A ACTUALIZAR LA LISTA

        Thread tresa = new Thread() {
            @Override
            public void run() {
                final String resultadotresa = enviarDatosgettresa(recibir.getText().toString(),
                        MainActivity.arreglo13.getText().toString(),
                        MainActivity.arreglo2.getText().toString());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        int r = obtenerDatosJSONtresa(resultadotresa);
                        if (r > 0) {
                            Toast.makeText(getApplicationContext(), "Solicitudes Aprobada ", Toast.LENGTH_SHORT).show();
                            arreglo = "";
                        } else {
                            arreglo = "";
                            Toast.makeText(getApplicationContext(), "Intenta de nuevo", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        };
        tresa.start();
    }

}
