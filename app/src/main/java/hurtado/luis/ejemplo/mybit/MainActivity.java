package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText user,password;
    TextView arreglo;
    static TextView arreglo2;
    static TextView arreglo3;
    static TextView arreglo4;
    static TextView arreglo5;
    static TextView arreglo6,arreglo7,arreglo8,arreglo9,arreglo10,arreglo11,arreglo12,arreglo13,arreglo14,arreglo15,arreglo16,arreglo17;
    Button boton,boton2;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText) findViewById(R.id.txtusu);
        password =  (EditText)findViewById(R.id.txtpass);
        boton = (Button)findViewById(R.id.btningresar);
        boton2 = (Button)findViewById(R.id.btnsaltar_login);
        arreglo = (TextView)findViewById(R.id.txtarreglo);
        arreglo2 = (TextView)findViewById(R.id.txtarreglo2);
        arreglo3 = (TextView)findViewById(R.id.txtarreglo3);
        arreglo4 = (TextView)findViewById(R.id.txtarreglo4);
        arreglo5 = (TextView)findViewById(R.id.txtarreglo5);
        arreglo6 = (TextView)findViewById(R.id.txtarreglo6);
        arreglo7 = (TextView)findViewById(R.id.txtarreglo7);
        arreglo8 = (TextView)findViewById(R.id.txtarreglo8);
        arreglo9 = (TextView)findViewById(R.id.txtarreglo9);
        arreglo10 = (TextView)findViewById(R.id.txtarreglo10);
        arreglo11 = (TextView)findViewById(R.id.txtarreglo11);
        arreglo12 = (TextView)findViewById(R.id.txtarreglo12);
        arreglo13 = (TextView)findViewById(R.id.txtarreglo13);
        arreglo14 = (TextView)findViewById(R.id.txtarreglo14);
        arreglo15 = (TextView)findViewById(R.id.txtarreglo15);
        arreglo16 = (TextView)findViewById(R.id.txtarreglo16);
        arreglo17 = (TextView)findViewById(R.id.txtarreglo17);
        boton.setOnClickListener(this);

    }

    public void SaltarLogin (View view){
        Intent intentos = new Intent(getApplicationContext(),MenuAdmin.class);
        startActivity(intentos);
    }

    public void Laboratorio (View view){
        Intent intentos2 = new Intent(getApplicationContext(),Laboratorio.class);
        startActivity(intentos2);
    }

    @Override
    public void onClick(View view) {


        Thread tr=new Thread(){
            @Override
            public void run() {
                final String resultado=enviarDatosget(user.getText().toString(),password.getText().toString());
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int r = obtenerDatosJSON(resultado);
                        if(r > 0){
                            Toast.makeText(getApplicationContext(),"Bienvenido "+arreglo3.getText().toString(),Toast.LENGTH_SHORT).show();

                            Intent intentos = new Intent(getApplicationContext(),Home.class);
                            /*String dato1 = arreglo3.getText().toString();
                           intentos.putExtra("DATOusuario",dato1); */
                            startActivity(intentos);

                        }else{

                            Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrectos / Verifica conexion",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        tr.start();
    }

    public String enviarDatosget(String usu, String pas){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/webservicedos.php?usu="+usu+"&pas="+pas);
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
            arreglo.setText(response);
            for(int i=0;i<json.length();i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);
                String elnombre = jsonobject.optString("nombre");
                String elcodigo = jsonobject.optString("codEmp");
                String elpais = jsonobject.optString("pais");
                String elpassword = jsonobject.optString("password");
                String laimagen = jsonobject.optString("foto");
                String elapellido = jsonobject.optString("apellido");
                String lacedula = jsonobject.optString("cedula");
                String ladireccion = jsonobject.optString("direccion");
                String eltelefono = jsonobject.optString("telefono");
                String email1 = jsonobject.optString("mail");
                String email2 = jsonobject.optString("mailemp");
                String tiempo = jsonobject.optString("tiempoLib");
                String cargo = jsonobject.optString("cargo");
                String gerente = jsonobject.optString("gerente");
                String role = jsonobject.optString("role");
                String departamento = jsonobject.optString("departamento");

                arreglo2.setText(elcodigo);
                arreglo3.setText(elnombre);
                arreglo4.setText(elpais);
                arreglo5.setText(elpassword);
                arreglo6.setText(laimagen);
                arreglo7.setText(elapellido);
                arreglo8.setText(lacedula);
                arreglo9.setText(ladireccion);
                arreglo10.setText(eltelefono);
                arreglo11.setText(email1);
                arreglo12.setText(email2);
                arreglo13.setText(departamento);
                arreglo14.setText(cargo);
                arreglo15.setText(gerente);
                arreglo16.setText(tiempo);
                arreglo17.setText(role);


            }

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


    public void RecuperarContraseña (View v){

        if (user.getText().toString().length()>0 ){

          /*  String uriText =
                    "mailto:luis.hurtado@linio.com" +
                            "?subject=" + Uri.encode("REINICIO DE CONTRASEÑA PARA "+user.getText()) +
                            "&body=" + Uri.encode("Buen Día, he olvidado mi contraseña para ingresar " +
                            "a MyBit, por favor reiniciar clave y notificar al correo: "+user.getText());

            Uri uri = Uri.parse(uriText);

            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(uri);
            startActivity(Intent.createChooser(sendIntent, "Send email"));

*/
            String uriText =
                    "mailto:luis.hurtado@linio.com" +
                            "?subject=" + Uri.encode("Reinicio de contraseña para "+user.getText().toString()) +
                            "&body=" + Uri.encode("Buen Día, he olvidado mi contraseña para ingresar a MyBit, por favor reiniciar clave y notificar al correo: "+user.getText().toString());

            Uri uri = Uri.parse(uriText);

            Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
            sendIntent.setData(uri);
            startActivity(Intent.createChooser(sendIntent, "Send email"));



        }

       else {
            Toast.makeText(getApplicationContext(), "Debes ingresar tu cuenta de correo empresarial en el campo Código de usuario ", Toast.LENGTH_SHORT).show();
        }
    }


}
