package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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

public class EditUser extends AppCompatActivity implements View.OnClickListener{


    EditText entrada;
    TextView resultados;
    ImageButton buscar;
    String devuelve = "";
    String vuelta="";


   // List elementos = new ArrayList();
   // ListView listas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        entrada = (EditText) findViewById(R.id.TXTentrada);
        resultados = (TextView)findViewById(R.id.TXTresultado);
        buscar = (ImageButton)findViewById(R.id.BtnBuscar);
        buscar.setOnClickListener(this);

/*
        listas = (ListView) findViewById(R.id.VisionLista);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,elementos);

        listas.setAdapter(adapter);
*/
    }

    @Override
    public void onClick(View view) {


        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(entrada.getText().toString());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){
                            Toast.makeText(getApplicationContext(),"Usuarios Encontrados",Toast.LENGTH_SHORT).show();
                            devuelve="";
                           /*S Intent intentos = new Intent(getApplicationContext(),Home.class);
                            tring dato1 = arreglo3.getText().toString();
                           intentos.putExtra("DATOusuario",dato1);
                            startActivity(intentos);*/

                        }else{
                            Toast.makeText(getApplicationContext(),"Sin resultados",Toast.LENGTH_SHORT).show();
                            devuelve="";
                           resultados.setText("Sin resultados");


                        }
                    }
                });
            }
        };
        tr.start();


    }

    public String enviarDatosget(String Emp){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/buscador_bit.php?Emp="+Emp);
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
            for(int i=0;i<json.length();i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);
                devuelve = devuelve + "Código de Empleado: "+json.getJSONObject(i).getString("codEmp") + "\n" +
                        "Nombre: "+json.getJSONObject(i).getString("nombre") + "\n" +
                        "Apellido: "+json.getJSONObject(i).getString("apellido") + "\n" +
                        "Departamento: "+ json.getJSONObject(i).getString("departamento") + "\n" +
                        "Status: "+json.getJSONObject(i).getString("status")+ "\n\n";

               //vuelta = "Código de Empleado: "+json.getJSONObject(i).getString("codEmp") + " - " +
                  //      "Nombre: "+json.getJSONObject(i).getString("nombre") + " - " +
                  //      "Apellido: "+json.getJSONObject(i).getString("apellido") + " - " +
                  //      "Departamento: "+ json.getJSONObject(i).getString("departamento") + " - " +
                  //      "Status: "+json.getJSONObject(i).getString("status")+ "\n\n";

               // elementos.add(vuelta);

            }resultados.setText(devuelve);






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
