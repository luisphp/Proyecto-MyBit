package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class EditorDeUsuarios extends AppCompatActivity implements View.OnClickListener {

    EditText codigo, nombre, apellido, cedula, direccion, telefono, pais, mail1, departamento, cargo, gerente, role,tiempoLibre;
    TextView generarcor, email2, generarcon, pass, arreglo,thestatus;
    Button boton1,botondesactivar;
    ImageButton boton2,boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_de_usuarios);

        codigo = (EditText) findViewById(R.id.txtcodigo);
        nombre = (EditText) findViewById(R.id.txtnombre);
        apellido = (EditText) findViewById(R.id.txtapellido);
        cedula = (EditText) findViewById(R.id.txtcedula);
        direccion = (EditText) findViewById(R.id.txtdireccion);
        telefono = (EditText) findViewById(R.id.txttelefono);
        pais = (EditText) findViewById(R.id.txtpais);
        mail1 = (EditText) findViewById(R.id.txtmail1);
        departamento = (EditText) findViewById(R.id.txtdepartamento);
        cargo = (EditText) findViewById(R.id.txtcargo);
        gerente = (EditText) findViewById(R.id.txtgerente);
        role = (EditText) findViewById(R.id.txtrole);
        arreglo = (TextView) findViewById(R.id.txtarreglo);
        tiempoLibre = (EditText) findViewById(R.id.txtdiaslibres);
        thestatus = (TextView)findViewById(R.id.elstatus);

        generarcor = (TextView) findViewById(R.id.txtgenerador1);
        email2 = (TextView) findViewById(R.id.txtemail2);

        generarcon = (TextView) findViewById(R.id.txtgenerador2);
        pass = (TextView) findViewById(R.id.txtcontraseñas);

        boton1 = (Button) findViewById(R.id.btnregistrar);
        boton1.setOnClickListener(this);


        boton2 = (ImageButton) findViewById(R.id.btntraer);
        boton2.setOnClickListener(this);

        boton3 = (ImageButton) findViewById(R.id.btneliminar);
        boton3.setOnClickListener(this);


    }

    public String enviarDatosget(String codEmp, String nombre, String apellido, String cedula, String direccion, String telefono,
                                 String pais, String mail1, String departamento, String cargo, String gerente, String role,
                                 String email2, String pass,String tiempoLibre) {
        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            //url = new URL("http://androidwstest.000webhostapp.com/webservice.php?usu="+usu+"&pas="+pas);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/registro.php?usu=" + usu + "&nombre=" + nombre +"&pais="+pais+"&pas=" + pas);


            url = new URL("http://androidwstest.esy.es/user_updater.php?codEmp=" + codEmp + "&nombre=" + nombre + "&apellido=" + apellido +
                    "&cedula=" + cedula + "&direccion=" + direccion + "&telefono=" + telefono + "&pais=" + pais + "&mail1=" + mail1 + "&departamento="
                    + departamento + "&cargo=" + cargo + "&gerente=" + gerente + "&role=" + role + "&email2=" + email2 + "&pas=" + pass +"&tiempoLibre="+tiempoLibre);


            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);

                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return result.toString();

    }


    public void GenerarCorreo(View v) {
        email2.setText(nombre.getText().toString() + "." + apellido.getText().toString() + "@bit.com");
    }

    public void GenerarContraseña(View v) {
        pass.setText(cedula.getText().toString());
    }

    public int obtenerDatosJSON(String response) {
        int res = 0;
        try {

            JSONArray json = new JSONArray(response);


            if (json.length() > 0) {
                res = 1;


                arreglo.setText(response);


            }

        } catch (Exception e) {

        }
        return res;
    }


    //TODO ACA ESTA EL CODIGO PARA TRAER TODA LA INFORMACION

    public String enviarDatosgetdos(String codEmp) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/webservicetres_bit.php?codEmp=" + codEmp);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);

                }
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return result.toString();

    }


    public int obtenerDatosJSONdos(String responsedos) {
        int resdos = 0;
        try {

            JSONArray json = new JSONArray(responsedos);
            arreglo.setText(responsedos);
            for (int i = 0; i < json.length(); i++) {
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
                String elemail1 = jsonobject.optString("mail");
                String elemail2 = jsonobject.optString("mailemp");
                String eltiempo = jsonobject.optString("tiempoLib");
                String elcargo = jsonobject.optString("cargo");
                String elgerente = jsonobject.optString("gerente");
                String elrole = jsonobject.optString("role");
                String eldepartamento = jsonobject.optString("departamento");
                String status = jsonobject.optString("status");

                codigo.setText(elcodigo);
                nombre.setText(elnombre);
                apellido.setText(elapellido);
                cedula.setText(lacedula);
                direccion.setText(ladireccion);
                telefono.setText(eltelefono);
                pais.setText(elpais);
                mail1.setText(elemail1);
                departamento.setText(eldepartamento);
                cargo.setText(elcargo);
                gerente.setText(elgerente);
                role.setText(elrole);
                email2.setText(elemail2);
                tiempoLibre.setText(eltiempo);
                pass.setText(elpassword);
                pass = (TextView) findViewById(R.id.txtcontraseñas);
                thestatus.setText(status);

            }

            if (json.length() > 0) {
                resdos = 1;

            }

        } catch (Exception e) {

        }
        return resdos;
    }


//TODO ACA EL CODIGO PARA DESACTIVAR



    public String enviarDatosgetDU(String codEmp) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/desactivar_user_bit.php?codEmp=" + codEmp);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public int obtenerDatosJSONDU(String responseDU) {
        int restres = 0;
        try {
            JSONArray json = new JSONArray(responseDU);
            arreglo.setText(responseDU);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);
                String elnombre = jsonobject.optString("nombre");

            }
            if (json.length() > 0) {
                restres = 1;
            }
        } catch (Exception e) {
        }
        return restres;
    }


    //TODO ACA EL CODIGO PARA ACTIVAR



    public String enviarDatosgetAA(String codEmp) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/activar_user_bit.php?codEmp=" + codEmp);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public int obtenerDatosJSONAA(String responseAA) {
        int restres = 0;
        try {
            JSONArray json = new JSONArray(responseAA);
            arreglo.setText(responseAA);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);
                String elnombre = jsonobject.optString("nombre");

            }
            if (json.length() > 0) {
                restres = 1;
            }
        } catch (Exception e) {
        }
        return restres;
    }


    //TODO ACA EL CODIGO PARA ELIMINAR (SENCILLO)



    public String enviarDatosgettres(String codEmp) {

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/eliminar_user_bit.php?codEmp=" + codEmp);
            // url = new URL("http://153.92.9.178/webservice.php?usu="+user+"&pas="+password);
            //url = new URL("http://192.168.1.5:8080/WebService/ServiciosWeb.php?usu=" + usu + "&pas=" + pas);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            respuesta = conection.getResponseCode();

            result = new StringBuilder();
            if (respuesta == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(conection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                while ((linea = reader.readLine()) != null) {
                    result.append(linea);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    public int obtenerDatosJSONtres(String responsetres) {
        int restres = 0;
        try {
            JSONArray json = new JSONArray(responsetres);
            arreglo.setText(responsetres);
            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonobject = (JSONObject) json.get(i);
                String elnombre = jsonobject.optString("nombre");

            }
            if (json.length() > 0) {
                restres = 1;
            }
        } catch (Exception e) {
        }
        return restres;
    }




    public void DesactivarUser (View v){


        Thread trdesactivar = new Thread() {
            @Override
            public void run() {
                final String resultadoDU = enviarDatosgetDU(codigo.getText().toString().replaceAll(" ","%20"));
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int r = obtenerDatosJSONDU(resultadoDU);
                        if (r > 0) {
                            Toast.makeText(getApplicationContext(),"Desactivado", Toast.LENGTH_SHORT).show();

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
                            tiempoLibre.setText("");
                            thestatus.setText("");



                        } else {

                            Toast.makeText(getApplicationContext(),"Usuario NO Desactivado - Intenta Nuevamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        trdesactivar.start();

    }

    public void ActivarUser (View v){



        Thread tractivar = new Thread() {
            @Override
            public void run() {
                final String resultadoA = enviarDatosgetAA(codigo.getText().toString().replaceAll(" ","%20"));
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        int r = obtenerDatosJSONAA(resultadoA);
                        if (r > 0) {
                            Toast.makeText(getApplicationContext(),"Usuario Activado", Toast.LENGTH_SHORT).show();

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
                            tiempoLibre.setText("");
                            thestatus.setText("");



                        } else {

                            Toast.makeText(getApplicationContext(),"Usuario NO Activado - Intenta Nuevamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        tractivar.start();

    }









    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregistrar:
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String resultado = enviarDatosget(codigo.getText().toString().replaceAll(" ","%20"), nombre.getText().toString().replaceAll(" ","%20"), apellido.getText().toString().replaceAll(" ","%20"),
                                cedula.getText().toString().replaceAll(" ","%20"), direccion.getText().toString().replaceAll(" ","%20"), telefono.getText().toString().replaceAll(" ","%20"), pais.getText().toString().replaceAll(" ","%20"),
                                mail1.getText().toString().replaceAll(" ","%20"), departamento.getText().toString().replaceAll(" ","%20"), cargo.getText().toString().replaceAll(" ","%20"), gerente.getText().toString().replaceAll(" ","%20"),
                                role.getText().toString().replaceAll(" ","%20"), email2.getText().toString().replaceAll(" ","%20"), pass.getText().toString().replaceAll(" ","%20"), tiempoLibre.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int r = obtenerDatosJSON(resultado);
                                if (r > 0) {


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
                                    tiempoLibre.setText("");
                                    thestatus.setText("");


                                    Toast.makeText(getApplicationContext(), " Usuario Modificado", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getApplicationContext(), " Usuario No modificado / Problema de Conexión ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                tr.start();
                break;


            case R.id.btntraer:
                Thread tra = new Thread() {
                    @Override
                    public void run() {
                        final String resultadodos = enviarDatosgetdos(codigo.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int r = obtenerDatosJSONdos(resultadodos);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(), "Usuario encontrado ", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "El usuario NO existe", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                tra.start();
                break;

            case R.id.btneliminar:
                Thread trax = new Thread() {
                    @Override
                    public void run() {
                        final String resultadotres = enviarDatosgettres(codigo.getText().toString().replaceAll(" ","%20"));
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                int r = obtenerDatosJSONtres(resultadotres);
                                if (r > 0) {
                                    Toast.makeText(getApplicationContext(),"Usuario Eliminado ", Toast.LENGTH_SHORT).show();
                                } else {
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
                                    tiempoLibre.setText("");
                                    thestatus.setText("");
                                    Toast.makeText(getApplicationContext(),"Usuario Eliminado", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                };
                trax.start();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
