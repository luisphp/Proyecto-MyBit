package hurtado.luis.ejemplo.mybit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.net.URLEncoder;
import java.text.Normalizer;

public class MenuDepartamentos extends AppCompatActivity {
    static TextView parametro;
    static String direccion1 = "";
    static String direccion2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_departamentos);

        parametro = (TextView) findViewById(R.id.txtparametro);

    }



    public void Contenido (View view){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                parametro.setText("Contenido");
                Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                String dato1 = parametro.getText().toString();
                intento_3.putExtra("DATO1",dato1);
                startActivity(intento_3);

            }
        }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Contenido");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


       /* parametro.setText("Contenido");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */
    }
/*
    public void Contenido2 (View view){
        parametro.setText("Contenido");
        Intent intento_3 = new Intent(this,Flujograma.class);
        startActivity(intento_3);
    }
*/
    public void Comercial (View view){
        /*Intent intentos = new Intent(getApplicationContext(),MenuAdmin.class);
        startActivity(intentos);
        parametro.setText("Comercial");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Comercial");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Comercial");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }

    public void Legal (View view){
/*
        parametro.setText("Legal");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3);

        */



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Legal");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Legal");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
    public void Logistica (View view){

       /* parametro.setText("Shipping");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Shipping");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Shipping");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }

    public void RRHH (View view){
      /*  parametro.setText("RRHH");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("RRHH");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("RRHH");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();



    }


    public void Mercadeo(View view){
      /*  parametro.setText("Mercadeo");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Mercadeo");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Mercadeo");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    public void Tecnologia (View view){
       /* parametro.setText("Tecnologia");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Tecnologia");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Tecnologia");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(parametro.getText().toString());

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    public void ServicioAlCliente (View view){
       /* parametro.setText("Servicio al Cliente");
        Intent intento_3 = new Intent(this,Procesos.class);
        String dato1 = parametro.getText().toString();
        intento_3.putExtra("DATO1",dato1);
        startActivity(intento_3); */

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("").setCancelable(false).setPositiveButton
                ("Procesos y Comentarios", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta

                        parametro.setText("Servicio al Cliente");
                        Intent intento_3 = new Intent(getApplicationContext(),Procesos.class);
                        String dato1 = parametro.getText().toString();
                        intento_3.putExtra("DATO1",dato1);
                        startActivity(intento_3);

                    }
                }).setNegativeButton("Flujograma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                parametro.setText("Servicio al Cliente");

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(URLEncoder.encode(parametro.getText().toString()));

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){


                                    Intent intento_3 = new Intent(getApplicationContext(),Flujograma.class);
                                    startActivity(intento_3);

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Intenta pasar de nuevo ",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }


    public void Regresar (View view){

        Intent intento_3 = new Intent(this,MenuDepartamentos.class);
        startActivity(intento_3);
    }




    public String enviarDatosget(String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/descarga_flujograma.php?departamento="+departamento);
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
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);

                direccion1 = json.getJSONObject(i).getString("imagen1");
                direccion2 = json.getJSONObject(i).getString("imagen2");
            }

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


}
