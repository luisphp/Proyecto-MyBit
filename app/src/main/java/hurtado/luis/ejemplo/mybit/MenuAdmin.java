package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import hurtado.luis.ejemplo.mybit.Empleados;
import hurtado.luis.ejemplo.mybit.Home;
import hurtado.luis.ejemplo.mybit.MenuDepartamentos;
import hurtado.luis.ejemplo.mybit.R;

public class MenuAdmin extends AppCompatActivity {


    ListView listas;
    String[] elementos = {"Home","Empleados","Menu Departamentos","Mi Informacion","Registro","Procesos",
            "Buscador de Usuarios","Editor de Usuarios", "Tiempo Libre","Listado solc. vacaciones",
            "Seccion de Gerentes","Todos los usuarios", "Objetivos","Evaluar Objetivos","Editar Procesos","ListView Personalizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

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
                    Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent2);
                }
                if (i+1 == 2){
                    Toast.makeText(getApplicationContext(),"Empleados", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),Empleados.class);
                    startActivity(intent3);
                }

                if (i+1 == 3){
                    Toast.makeText(getApplicationContext(),"Departamentos",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),MenuDepartamentos.class);
                    startActivity(intent3);
                }
                if (i+1 == 4){
                    Toast.makeText(getApplicationContext(),"Departamentos",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),MyInfo.class);
                    startActivity(intent3);
                }

                if (i+1 == 5){
                    Toast.makeText(getApplicationContext(),"Registro de usuarios",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),Registrar.class);
                    startActivity(intent3);
                }

                if (i+1 == 6){
                    Toast.makeText(getApplicationContext(),"Swipe + Tab",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),Procesos.class);
                    startActivity(intent3);
                }

                if (i+1 == 7){
                    Toast.makeText(getApplicationContext(),"Buscador",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),EditUser.class);
                    startActivity(intent3);
                }
                if (i+1 == 8){
                    Toast.makeText(getApplicationContext(),"Editor",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),EditorDeUsuarios.class);
                    startActivity(intent3);
                }
                if (i+1 == 9){
                    Toast.makeText(getApplicationContext(),"Tiempo Libre",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),TiempoLibre.class);
                    startActivity(intent3);
                }

                if (i+1 == 10){
                    Toast.makeText(getApplicationContext(),"Tiempo Libre",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),vacaUsuario.class);
                    startActivity(intent3);
                }
                if (i+1 == 11){
                    Toast.makeText(getApplicationContext(),"Seccion de Gerentes",Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(getApplicationContext(),MenuGerente.class);
                    startActivity(intent3);
                }
                if (i+1 == 12){

                    Intent intent3 = new Intent(getApplicationContext(),All_users.class);
                    startActivity(intent3);
                }
                if (i+1 == 13){

                    Intent intent3 = new Intent(getApplicationContext(),Objetivos.class);
                    startActivity(intent3);
                }
                if (i+1 == 14){

                    Intent intent3 = new Intent(getApplicationContext(),EvaluarObjetivos.class);
                    startActivity(intent3);
                }
                if (i+1 == 15){

                    Intent intent3 = new Intent(getApplicationContext(),Editor_de_Procesos.class);
                    startActivity(intent3);
                }
                if (i+1 == 16){

                    Intent intent3 = new Intent(getApplicationContext(),ListView_personalizado.class);
                    startActivity(intent3);
                }


            }
        });



    }
}
