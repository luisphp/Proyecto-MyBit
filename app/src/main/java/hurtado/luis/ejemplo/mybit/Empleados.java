package hurtado.luis.ejemplo.mybit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Empleados extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        ArrayList<clsProducto> datos = new ArrayList<clsProducto>();
        datos.add(new clsProducto(R.drawable.jose_perez, "Jose Perez", "Mercadeo"));
        datos.add(new clsProducto(R.drawable.maria_pena, "Maria Peña", "Mercadeo"));
        datos.add(new clsProducto(R.drawable.rafael_zarate, "Rafael Zarate", "Contenido"));
        datos.add(new clsProducto(R.drawable.raul_portilla, "Raul Portilla", "Contenido"));
        datos.add(new clsProducto(R.drawable.mercedes_cara, "Mercedes Cara", "Servicio al cliente"));
        datos.add(new clsProducto(R.drawable.jhony_sequera, "Jhony Sequera", "Servicio al cliente"));
        datos.add(new clsProducto(R.drawable.andres_antolin, "Andres Antolin", "Tecnologia"));
        datos.add(new clsProducto(R.drawable.roger_estrada, "Roger Estrada", "Tecnologia"));
        datos.add(new clsProducto(R.drawable.luisa_moraga, "Luisa Moraga", "Comercial"));
        datos.add(new clsProducto(R.drawable.teresa_soto, "Teresa Soto", "Comercial"));
        datos.add(new clsProducto(R.drawable.catalina_vincent, "Catalina Vincent", "Recursos Humanos"));
        datos.add(new clsProducto(R.drawable.luis_perez, "Luis Perez", "Legal"));
        datos.add(new clsProducto(R.drawable.pedro_mora, "Pedro Mora", "Logística"));


        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);

        adaptador = new productoAdaptador(datos);
        reciclador.setAdapter(adaptador);
    }
}
