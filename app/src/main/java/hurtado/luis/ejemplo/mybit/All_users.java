package hurtado.luis.ejemplo.mybit;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
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

public class All_users extends AppCompatActivity implements View.OnClickListener{

    Button boton;
    TextView texto;


    String IP = "http://androidwstest.esy.es";
    String GET = IP+"/obtener_empleados_bit.php";

    ObtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        boton = (Button) findViewById(R.id.verusers);
        boton.setOnClickListener(this);

        texto = (TextView) findViewById(R.id.txtusers);


    }
    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            texto.setText(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... params) {


            String cadena = params[0];
            URL url = null;
            String devuelve = "";


            if (params[1] == "1") {
                try {
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("user-Agent", "Mozzilla/5.0" + " (Linux; Android 1.5; es-ES) Ejemplo HTTP");

                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == connection.HTTP_OK) {
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        String resultJSON = respuestaJSON.getString("estado");


                        if (resultJSON.equals("1")) {


                            JSONArray usuariosJSON = respuestaJSON.getJSONArray("empleados");
                            for (int i = 0; i < usuariosJSON.length(); i++) {
                                devuelve = devuelve +
                                        "Código de Empleado: "+usuariosJSON.getJSONObject(i).getString("codEmp") + "\n" +
                                        "Nombre de Usuario: "+ usuariosJSON.getJSONObject(i).getString("nombre") + " "
                                        +usuariosJSON.getJSONObject(i).getString("apellido")+ "\n" +
                                        "País: "+usuariosJSON.getJSONObject(i).getString("pais") + "\n" +
                                        "Posición: "+usuariosJSON.getJSONObject(i).getString("cargo") + "\n" +
                                        "Role: "+usuariosJSON.getJSONObject(i).getString("role") + "\n" +
                                        "Departamento: "+usuariosJSON.getJSONObject(i).getString("departamento") + "\n\n";
                            }
                        }

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return devuelve;
        }
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.verusers:

                hiloconexion = new ObtenerWebService();
                hiloconexion.execute(GET,"1");
        }
    }
}
