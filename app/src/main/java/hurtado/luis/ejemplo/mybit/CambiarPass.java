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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CambiarPass extends AppCompatActivity implements View.OnClickListener {

    String user;
    EditText password;
    TextView texto1,texto2,texto3;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

        texto1 = (TextView) findViewById(R.id.thisone);
        texto2 = (TextView) findViewById(R.id.this2);
        texto3 = (TextView) findViewById(R.id.this3);

        texto3.setText(MainActivity.arreglo5.getText().toString());

        user = MainActivity.arreglo2.getText().toString();
        password =  (EditText)findViewById(R.id.newpass);

        boton = (Button) findViewById(R.id.cambiopas);
        boton.setOnClickListener(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Homes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });
    }

    public String enviarDatosget(String usu, String pas){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/update_pass_bit.php?usu="+usu+"&pas="+pas);
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
            case R.id.cambiopas:
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String resultado = enviarDatosget(user.toString(), password.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int r = obtenerDatosJSON(resultado);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(), "Cambio realizado " + MainActivity.arreglo3.getText().toString(), Toast.LENGTH_SHORT).show();
                                    MainActivity.arreglo5.setText(password.getText().toString());
                                    MyInfo.password.setText(password.getText().toString());
                                    texto3.setText(password.getText().toString());
                                    password.setText("");

                                } else {
                                    Toast.makeText(getApplicationContext(), "Intentalo de nuevo", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                tr.start();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
