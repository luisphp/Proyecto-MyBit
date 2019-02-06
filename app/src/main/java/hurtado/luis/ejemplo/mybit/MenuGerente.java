package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuGerente extends AppCompatActivity {

    ListView listas;
    String[] elementos = {"Revisar solicitudes deVacaciones","Evaluar Objetivos"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gerente);






        listas = (ListView) findViewById(R.id.VisionLista);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,elementos);

        listas.setAdapter(adapter);

        listas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                // es posible utilizar la linea de abajo solo para uqe nos muestre el valor que se toco de la lista por ejempl 0 , 1 el que sea
                // con la linea que esta mas abajo queda mejor
                //Toast.makeText(getApplicationContext(),String.valueOf(i+11),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Lanzando a la seccíon: "+(i+1),Toast.LENGTH_SHORT).show();

                if (i+1 == 1){

                    Intent intent2 = new Intent(getApplicationContext(),SeccionGerentes.class);
                    startActivity(intent2);
                }
                if (i+1 == 2){

                    Intent intent2 = new Intent(getApplicationContext(),EvaluarObjetivos.class);
                    startActivity(intent2);
                }

            }
        });





    }
}
