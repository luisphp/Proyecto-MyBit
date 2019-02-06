package hurtado.luis.ejemplo.mybit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.net.URLEncoder;

/**
 * Created by luis.hurtado on 20/02/2017.
 */
public class Fragment_seccion2 extends Fragment {

    View rootView;
    EditText edtcomentario;
    Button enviar;
    String parametro_dos = "";
    String proceso_uno = "";
    String proceso_dos = "";
    String proceso_tres = "";
    String proceso_cuatro = "";
    String proceso_cinco = "";
    String proceso_seis = "";
    String proceso_siete = "";

    TextView mostrador,mostrador2,mostrador3,mostrador4,mostrador5,mostrador6,mostrador7;
    FloatingActionButton flotador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        rootView =  inflater.inflate(R.layout.fragment_seccion2,container,false);

        mostrador = (TextView)rootView.findViewById(R.id.mostradorjson);
        mostrador.setText("Presiona Descargar para ver los Procesos Actualizados ");
        mostrador2 = (TextView)rootView.findViewById(R.id.mostradorjson2);
        mostrador3 = (TextView)rootView.findViewById(R.id.mostradorjson3);
        mostrador4 = (TextView)rootView.findViewById(R.id.mostradorjson4);
        mostrador5 = (TextView)rootView.findViewById(R.id.mostradorjson5);
        mostrador6 = (TextView)rootView.findViewById(R.id.mostradorjson6);
        mostrador7 = (TextView)rootView.findViewById(R.id.mostradorjson7);


        flotador = (FloatingActionButton)rootView.findViewById(R.id.faborito);
        flotador.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                parametro_dos = MenuDepartamentos.parametro.getText().toString();

                Toast.makeText(getActivity().getApplicationContext(),"Descargando Procesos de "+parametro_dos,Toast.LENGTH_SHORT).show();
                Thread tr=new Thread(){

                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(URLEncoder.encode(parametro_dos.toString()));

                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){

                                    Toast.makeText(getActivity().getApplicationContext(),"Listo! ",Toast.LENGTH_SHORT).show();
                                    proceso_uno = "";
                                    proceso_dos = "";
                                    proceso_tres = "";
                                    proceso_cuatro = "";
                                    proceso_cinco = "";
                                    proceso_seis = "";
                                    proceso_siete = "";
                                }
                                else
                                {
                                    Toast.makeText(getActivity().getApplicationContext(),"Intenta de nuevo",Toast.LENGTH_SHORT).show();
                                    proceso_uno = "";
                                    proceso_dos = "";
                                    proceso_tres = "";
                                    proceso_cuatro = "";
                                    proceso_cinco = "";
                                    proceso_seis = "";
                                    proceso_siete = "";
                                }
                            }
                        });
                    }
                };
                tr.start();

            }
        });




        return rootView;

    }


    public String enviarDatosget(String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/descargar_procesos.php?departamento="+departamento);
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

               proceso_uno = json.getJSONObject(i).getString("proceso1");
               proceso_dos = json.getJSONObject(i).getString("proceso2");
               proceso_tres = json.getJSONObject(i).getString("proceso3");
               proceso_cuatro = json.getJSONObject(i).getString("proceso4");
               proceso_cinco = json.getJSONObject(i).getString("proceso5");
               proceso_seis = json.getJSONObject(i).getString("proceso6");
               proceso_siete = json.getJSONObject(i).getString("proceso7");

            } mostrador.setText(proceso_uno);
              mostrador2.setText(proceso_dos);
              mostrador3.setText(proceso_tres);
              mostrador4.setText(proceso_cuatro);
              mostrador5.setText(proceso_cinco);
              mostrador6.setText(proceso_seis);
              mostrador7.setText(proceso_siete);


            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }


}