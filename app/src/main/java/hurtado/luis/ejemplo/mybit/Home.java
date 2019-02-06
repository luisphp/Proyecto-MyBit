package hurtado.luis.ejemplo.mybit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context = this;
    TextView txtusuario,texto,my_name,pais;

    String role = MainActivity.arreglo17.getText().toString();
    String grado = MainActivity.arreglo14.getText().toString();


    String IP = "http://androidwstest.esy.es";
    String GET = IP+"/obtener_datos_por_id_bit.php";
    ObtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtusuario = (TextView) findViewById(R.id.txtusuario4);
        texto = (TextView) findViewById(R.id.texto);

        if(MainActivity.arreglo3!=null) {
            txtusuario.setText(MainActivity.arreglo3.getText().toString());

        }else{
            txtusuario.setText("Sin Datos");
        }


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bajando info del Web-Service...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



                hiloconexion = new ObtenerWebService();
                //String cadena2llamada = GET + "?codEmp=" + txtusuario.getText().toString();
                String cadena2llamada = GET + "?codEmp=" + MainActivity.arreglo2.getText().toString();
                hiloconexion.execute(cadena2llamada,"1");
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //TODO datos que se muestran en el Drawer
        /*try {
            URL urlimagen = new URL(MainActivity.arreglo6.getText().toString());
             Bitmap bitmap = BitmapFactory.decodeStream(urlimagen.openConnection().getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        View hearder = navigationView.getHeaderView(0);
        TextView here = (TextView) hearder.findViewById(R.id.my_namel);
        here.setText(MainActivity.arreglo3.getText().toString());
        TextView here2 = (TextView) hearder.findViewById(R.id.my_country);
        here2.setText(MainActivity.arreglo4.getText().toString());
        ImageView foto_perfil = (ImageView) hearder.findViewById(R.id.imageView_perfil);
        Picasso.with(this).load(MainActivity.arreglo6.getText().toString()).into(foto_perfil);


    }

    public void Nuestraempresa(View v){
        Intent intento_4 = new Intent(this,NuestraEmpresa.class);
        startActivity(intento_4);
    }


        //TODO  Acá hacemos el request del webservice

    public class ObtenerWebService extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {}

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

            String cadena2 = params[0];
            URL url = null;
            String devuelve = "";

            if (params[1] == "1") {

                try {
                    url = new URL(cadena2);
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


                            devuelve = devuelve + respuestaJSON.getJSONObject("empleado").getString("nombre");

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intento_4 = new Intent(this,MainActivity.class);
            startActivity(intento_4);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Departamentos(View v) {
        Intent intent = new Intent(this,MenuDepartamentos.class);
        startActivity(intent);
    }

    public void MyInfo(View v) {

        Intent intent = new Intent(this,MyInfo.class);
        startActivity(intent);
    }

    public void Team (View v) {
        Intent intent = new Intent(this,Empleados.class);
        startActivity(intent);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            Intent intent = new Intent(this,CambiarPass.class);
            startActivity(intent);


        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this,Objetivos.class);
            startActivity(intent);


        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,Empleados.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,MenuDepartamentos.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {


//TODO ACA VAMOS A A AVISAR SI EL USUARIO QUE INGRESO AL SISTEMA ES GERENTE PEUDE PROCEDER A LA SECCION DE GERENTES

            if (grado.contains("Gerente") ) {

                Intent intent = new Intent(this,MenuGerente.class);
                startActivity(intent);
            }else {
                // LEEME ESCRIBE UNA DEDICATORIA
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Necesitas ser Gerente para tener acceso a esta sección").setCancelable(false).setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta
                    }
                }).setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }




        } else if (id == R.id.nav_send) {

            if (role.contains("admin") ) {

                Intent intent = new Intent(this,MenuAdmin.class);
                startActivity(intent);
            }else {
                // LEEME ESCRIBE UNA DEDICATORIA
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Necesitas ser Adminsitrador para tener acceso a esta sección").setCancelable(false).setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // en este apartado colocas lo que quieras que haga si es POSOTIVA la respuesta
                    }
                }).setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // En este apartado colocas lo que quiera que haga si es NEGATIVA la respuesta
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
                    }



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
