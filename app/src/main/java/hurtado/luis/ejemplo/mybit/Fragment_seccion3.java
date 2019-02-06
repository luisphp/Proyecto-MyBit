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
import java.text.Normalizer;

/**
 * Created by luis.hurtado on 20/02/2017.
 */
public class Fragment_seccion3 extends Fragment {

    View rootView;
    EditText edtcomentario;
    TextView comentarios;
    Button enviar;
    String arreglo = "";
    FloatingActionButton bajar;
    String version = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  "Inflamos" el archivo XML correspondiente a esta sección.

        rootView = inflater.inflate(R.layout.fragment_seccion3,container,false);

        edtcomentario = (EditText) rootView.findViewById(R.id.entrada);
        comentarios = (TextView)rootView.findViewById(R.id.coments);
        enviar = (Button) rootView.findViewById(R.id.mandarcomentario);
        version = Procesos.recibiendoparametro.getText().toString();

        enviar.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                Thread tr=new Thread(){
                    @Override
                    public void run() {
                        final String resultado=enviarDatosget(MainActivity.arreglo2.getText().toString(),
                                URLEncoder.encode(Normalizer.normalize(edtcomentario.getText().toString(),Normalizer.Form.NFD)),MenuDepartamentos.parametro.getText().toString().replaceAll(" ","%20"));

                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSON(resultado);
                                if(r > 0){

                                    Toast.makeText(getActivity().getApplicationContext(),"Comentario Agregado ",Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                }
                                else
                                {
                                    Toast.makeText(getActivity().getApplicationContext(),"Intenta cargar de nuevo ",Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                }
                            }
                        });
                    }
                };
                tr.start();



            }
        });



bajar = (FloatingActionButton)rootView.findViewById(R.id.bajarcoments);
        bajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity().getApplicationContext(),"Bajando Comentarios . . . ",Toast.LENGTH_SHORT).show();


                Thread trdescarga=new Thread(){
                    @Override
                    public void run() {
                        final String resultadodos=enviarDatosgetdos(MenuDepartamentos.parametro.getText().toString().replaceAll(" ","%20"));

                        getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {

                                int r = obtenerDatosJSONdos(resultadodos);
                                if(r > 0){

                                    Toast.makeText(getActivity().getApplicationContext(),"Listo! ",Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                }
                                else
                                {
                                    Toast.makeText(getActivity().getApplicationContext(),"Intenta de nuevo",Toast.LENGTH_SHORT).show();
                                    arreglo = "";
                                }
                            }
                        });
                    }
                };
                trdescarga.start();
            }
        });



        return rootView;
    }






    public String enviarDatosget(String codEmp, String comentario, String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/carga_comentario_bit.php?codEmp="+codEmp+"&comentario="+comentario+"&departamento="+departamento);
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

               arreglo = arreglo + json.getJSONObject(i).getString("nombre") + " "+
                       json.getJSONObject(i).getString("apellido") + " dice: \n" +
                json.getJSONObject(i).getString("comentario") + "\n\n";


            }comentarios.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }





    // TODO: ASYNC TASK PARA SOLO BAJAR COMENTARIOS


    public String enviarDatosgetdos(String departamento){

        URL url = null;
        String linea = "";
        int respuesta = 0;
        StringBuilder result = null;

        try {
            url = new URL("http://androidwstest.esy.es/descargar_comentarios_bit.php?departamento="+departamento);
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

    public  int obtenerDatosJSONdos(String response){
        int res=0;
        try

        {

            JSONArray json = new JSONArray(response);
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonobject = (JSONObject) json.get(i);

                arreglo = arreglo + json.getJSONObject(i).getString("nombre") + " "
                        +json.getJSONObject(i).getString("apellido") + " dice: \n"+
                        json.getJSONObject(i).getString("comentario") + "\n\n";


            }comentarios.setText(arreglo);

            if(json.length()>0){
                res=1;

            }

        }catch (Exception e){

        }
        return res;
    }





}