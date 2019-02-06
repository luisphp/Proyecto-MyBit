package hurtado.luis.ejemplo.mybit;

/**
 * Created by luis.hurtado on 20/02/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment_seccion1 extends Fragment {

    View rootView;
    TextView et11;
    String parametro = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  "Inflamos" el archivo XML correspondiente a esta sección.
        rootView = inflater.inflate(R.layout.fragment_seccion1, container, false);

        parametro = Procesos.recibiendoparametro.getText().toString();

        et11 = (TextView) rootView.findViewById(R.id.edita11);


        if ( parametro.contains("Contenido")) {
            et11.setText(R.string.contenido);

        }if(parametro.contains("Comercial")){
            et11.setText(R.string.Comercial);

        }if(parametro.contains("Shipping")){
            et11.setText(R.string.Shipping);

        }if(parametro.contains("Servicio al Cliente")){
            et11.setText(R.string.Servicio_al_Cliente);

        }if(parametro.contains("Legal")){
            et11.setText(R.string.Legal);

        }if(parametro.contains("Mercadeo")){
            et11.setText(R.string.Marketing);

        }if(parametro.contains("Tecnologia")){
            et11.setText(R.string.Tecnologia);
            
        }if(parametro.contains("RRHH")){
            et11.setText(R.string.RRHH);
        }

        return rootView;
    }
}