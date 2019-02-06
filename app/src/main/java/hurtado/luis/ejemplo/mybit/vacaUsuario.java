package hurtado.luis.ejemplo.mybit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class vacaUsuario extends AppCompatActivity implements View.OnClickListener {

    TextView texto;
    FloatingActionButton boton;
    String arreglo = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaca_usuario);

        texto = (TextView)findViewById(R.id.contenido);
        boton = (FloatingActionButton)findViewById(R.id.faborito);
        boton.setOnClickListener(this);


        FloatingActionButton Home = (FloatingActionButton) findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {
        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(MainActivity.arreglo2.getText().toString());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){

                            Toast.makeText(getApplicationContext(),"Solicitudes encontradas ",Toast.LENGTH_SHORT).show();
                        arreglo = "";
                        }
                        else
                        {
                            arreglo = "";
                            texto.setText(" Sin solicitudes de vacaciones realizadas ");
                            Toast.makeText(getApplicationContext(),"Sin Solicitudes realizadas",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        };
        tr.start();

    }


    public String enviarDatosget(String codEmp){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/vacaciones_por_user.php?codEmp="+codEmp);
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
                arreglo = arreglo +"Solicitud "+((json.length()+1)-(i+1))+":\n" + "Cantidad de dias solicitados: " + json.getJSONObject(i).getString("cantidad")+"\n"+
                        "Fecha de Salida: " + json.getJSONObject(i).getString("fecha") + "\n" +
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
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


}
