package hurtado.luis.ejemplo.mybit;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Procesos extends AppCompatActivity implements View.OnClickListener{

    static TextView recibiendoparametro;

    private Adaptador_ViewPagerPrincipal Adaptador_ViewPagerPrincipal;
    private ViewPager ViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesos);


// Iniciamos la barra de herramientas.
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarPrincipal);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Procesos Operacionales");
        toolbar.setSubtitle("Powered Luis Hurtado");

        CollapsingToolbarLayout collapsed = (CollapsingToolbarLayout)findViewById(R.id.collapsingtoolbarly);
        collapsed.setTitle("Procesos operacionales");


// Iniciamos la barra de tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayoutPrincipal);

// Añadimos las 3 tabs de las secciones.
// Le damos modo "fixed" para que todas las tabs tengan el mismo tamaño. También le asignamos una gravedad centrada.
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());





// Iniciamos el viewPager.
        ViewPager = (ViewPager) findViewById(R.id.ViewPagerPrincipal);

// Creamos el adaptador, al cual le pasamos por parámtro el gestor de Fragmentos y muy importante, el nº de tabs o secciones que hemos creado.
        Adaptador_ViewPagerPrincipal = new Adaptador_ViewPagerPrincipal(getSupportFragmentManager(),tabLayout.getTabCount());

// Y los vinculamos.
        ViewPager.setAdapter(Adaptador_ViewPagerPrincipal);

// Y por último, vinculamos el viewpager con el control de tabs para sincronizar ambos.
        tabLayout.setupWithViewPager(ViewPager);



//Aca se recibe la informacion del Menu de Actividades es decir el parametro con el que se busca la data en la DB.
        recibiendoparametro = (TextView)findViewById(R.id.txtheadProcesos);

        Intent intento_3 = getIntent();
        Bundle bundle = intento_3.getExtras();

        if (bundle!=null) {

            String cadena = (String) bundle.get("DATO1");
            recibiendoparametro.setText("Procesos de "+cadena);
        }






    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }


    @Override
    public void onClick(View view) {


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                Intent intento_3 = new Intent(this,Home.class);
                startActivity(intento_3);
                return true;

            case R.id.Salir:
                Intent intento_4 = new Intent(this,MainActivity.class);
                startActivity(intento_4);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
