package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CargarObjetivos extends AppCompatActivity implements View.OnClickListener{

    EditText edtobjetivo;
    Button enviar;
    String arreglo ="";
    String redaccion ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_objetivos);



        edtobjetivo = (EditText)findViewById(R.id.objetivo);
        enviar = (Button)findViewById(R.id.CARGAOBJETIVO);
        enviar.setOnClickListener(this);


    }

    public String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d-M-yyyy");
        return sdf.format(cal.getTime());
    }


    public void Home(View v){
        Intent intento_5 = new Intent(this,Home.class);
        startActivity(intento_5);
    }
    public void descargar(View v){
        Intent intento_5 = new Intent(this,Objetivos.class);
        startActivity(intento_5);
    }

    public void Myinfo(View v){
        Intent intento_5 = new Intent(this,MyInfo.class);
        startActivity(intento_5);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.CARGAOBJETIVO:

                redaccion = edtobjetivo.getText().toString();

        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(MainActivity.arreglo2.getText().toString(),
                        MainActivity.arreglo13.getText().toString(),
                        giveDate().replaceAll(" ","%20"),redaccion.replaceAll(" ","%20"));
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){

                            Toast.makeText(getApplicationContext(),"Objetivos Cargados ",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Intenta cargar de nuevo ",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        };
        tr.start();


        break;

        }

    }


    public String enviarDatosget(String codEmp, String departamento, String fecha, String objetivos){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/carga_objetivos_bit.php?codEmp="+codEmp+"&departamento="+departamento+
                            "&fecha="+fecha+"&objetivos="+objetivos);
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

                        json.getJSONObject(i).getString("objetivos");


            }

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
