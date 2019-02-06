package hurtado.luis.ejemplo.mybit;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;



public class PlantillaProcesos extends AppCompatActivity {


TextView prueba1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plantilla_procesos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Contenido");
        CollapsingToolbarLayout callapse = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        callapse.setTitle("Contenido");

prueba1 = (TextView)findViewById(R.id.titulo);

        int parameto = 1;

        if (parameto == 1){
            prueba1.setText(R.string.contenido);
        }else{
            prueba1.setText("otro departamento");
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
