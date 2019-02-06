package hurtado.luis.ejemplo.mybit;

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

public class Registrar extends AppCompatActivity implements View.OnClickListener{


    EditText codigo,nombre,apellido,cedula,direccion,telefono,pais,mail1,departamento,cargo,gerente,role;
    TextView generarcor,email2,generarcon,pass,arreglo;
    Button boton1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        codigo = (EditText)findViewById(R.id.txtcodigo);
        nombre = (EditText)findViewById(R.id.txtnombre);
        apellido = (EditText)findViewById(R.id.txtapellido);
        cedula = (EditText)findViewById(R.id.txtcedula);
        direccion = (EditText)findViewById(R.id.txtdireccion);
        telefono = (EditText)findViewById(R.id.txttelefono);
        pais = (EditText)findViewById(R.id.txtpais);
        mail1 = (EditText)findViewById(R.id.txtmail1);
        departamento = (EditText)findViewById(R.id.txtdepartamento);
        cargo = (EditText)findViewById(R.id.txtcargo);
        gerente = (EditText)findViewById(R.id.txtgerente);
        role = (EditText)findViewById(R.id.txtrole);
        arreglo = (TextView)findViewById(R.id.txtarreglo);

        generarcor = (TextView)findViewById(R.id.txtgenerador1);
        email2 = (TextView)findViewById(R.id.txtemail2);

        generarcon = (TextView)findViewById(R.id.txtgenerador2);
        pass = (TextView)findViewById(R.id.txtcontraseñas);

        boton1 = (Button)findViewById(R.id.btnregistrar);
        boton1.setOnClickListener(this);


    }


    public String enviarDatosget(String codEmp, String nombre, String apellido, String cedula, String direccion, String telefono,
                                 String pais, String mail1, String departamento, String cargo, String gerente, String role,
                                 String email2, String pass){

        URL url = null;
        String linea="";
        int respuesta=0;
        StringBuilder result = null;

        try {
            //url = new URL("http://androidwstest.000webhostapp.com/webservice.php?usu="+usu+"&pas="+pas);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/registro.php?usu=" + usu + "&nombre=" + nombre +"&pais="+pais+"&pas=" + pas);


            url = new URL("http://androidwstest.esy.es/registro_bit.php?codEmp=" + codEmp + "&nombre=" + nombre + "&apellido="+ apellido+
                    "&cedula=" + cedula + "&direccion=" + direccion + "&telefono=" + telefono + "&pais=" + pais + "&mail1="  + mail1 + "&departamento="
                    + departamento + "&cargo=" + cargo + "&gerente=" + gerente +"&role="+ role + "&email2=" + email2 + "&pas=" + pass);


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



    public void GenerarCorreo (View v){
        email2.setText(nombre.getText().toString()+"."+apellido.getText().toString()+"@bit.com");
    }

    public void GenerarContraseña (View v){
        pass.setText(cedula.getText().toString());
    }

    public  int obtenerDatosJSON(String response){
        int res=0;
        try
        {

            JSONArray json = new JSONArray(response);


            if(json.length()>0){
                res=1;


                arreglo.setText(response);


            }

        }catch (Exception e){

        }
        return res;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregistrar:
                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(codigo.getText().toString(),nombre.getText().toString(),apellido.getText().toString(),
                                cedula.getText().toString(),direccion.getText().toString(),telefono.getText().toString(),pais.getText().toString(),
                                mail1.getText().toString(),departamento.getText().toString(),cargo.getText().toString(),gerente.getText().toString(),
                                role.getText().toString(),email2.getText().toString(),pass.getText().toString());
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    codigo.setText("");
                                    nombre.setText("");
                                    apellido.setText("");
                                    cedula.setText("");
                                    direccion.setText("");
                                    telefono.setText("");
                                    pais.setText("");
                                    mail1.setText("");
                                    departamento.setText("");
                                    cargo.setText("");
                                    gerente.setText("");
                                    role.setText("");
                                    email2.setText("");
                                    pass.setText("");


                                    Toast.makeText(getApplicationContext()," Usuario registrado!",Toast.LENGTH_SHORT).show();


                                }else{
                                    Toast.makeText(getApplicationContext()," Usuario No registrado / Problema de Conexión ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                tr.start();
                break;



        }




    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
