package hurtado.luis.ejemplo.mybit;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MyInfo extends AppCompatActivity implements View.OnClickListener{

    TextView nombre,apellido,cedula,direccion,telefono,email,email2,pais,departamento,cargo,gerente,tiempo_libre;
    static TextView password;
    Button boton1,boton2;
    ImageView profile;
int local = Integer.parseInt(MainActivity.arreglo16.getText().toString());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profile = (ImageView)findViewById(R.id.la_imagen);

        nombre = (TextView) findViewById(R.id.el_nombre);
        nombre.setText(MainActivity.arreglo3.getText().toString());

        apellido = (TextView) findViewById(R.id.el_apellido);
        apellido.setText(MainActivity.arreglo7.getText().toString());

        cedula = (TextView) findViewById(R.id.la_cedula);
        cedula.setText(MainActivity.arreglo8.getText().toString());

        direccion = (TextView) findViewById(R.id.la_direccion);
        direccion.setText(MainActivity.arreglo9.getText().toString());

        telefono = (TextView) findViewById(R.id.el_telefono);
        telefono.setText(MainActivity.arreglo10.getText().toString());

        email = (TextView) findViewById(R.id.el_email1);
        email.setText(MainActivity.arreglo11.getText().toString());

        email2 = (TextView) findViewById(R.id.el_email2);
        email2.setText(MainActivity.arreglo12.getText().toString());

        pais = (TextView) findViewById(R.id.el_pais);
        pais.setText(MainActivity.arreglo4.getText().toString());

        departamento = (TextView) findViewById(R.id.el_departamento);
        departamento.setText(MainActivity.arreglo13.getText().toString());

        cargo = (TextView) findViewById(R.id.el_cargo);
        cargo.setText(MainActivity.arreglo14.getText().toString());

        gerente = (TextView) findViewById(R.id.el_gerente);
        gerente.setText(MainActivity.arreglo15.getText().toString());

        tiempo_libre = (TextView) findViewById(R.id.el_tiempo);
        tiempo_libre.setText(MainActivity.arreglo16.getText().toString());

        password = (TextView) findViewById(R.id.el_password);
        password.setText(MainActivity.arreglo5.getText());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Home);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
            }
        });


        Picasso.with(this).load(MainActivity.arreglo6.getText().toString()).into(profile);
    }


    public void CambiarPassword (View view){
        Intent intentos = new Intent(getApplicationContext(),CambiarPass.class);
        startActivity(intentos);
    }


    public void PedirVacaciones (View view){

        if (local == 0){
            Toast.makeText(getApplicationContext(),"Debe contar con mínimo 1 día libre disponible",Toast.LENGTH_SHORT).show();
        }else
        {
        Intent intentos = new Intent(getApplicationContext(),TiempoLibre.class);
        startActivity(intentos);
        }
    }

    public void VerSolicitudes (View view){
        Intent intentos = new Intent(getApplicationContext(),vacaUsuario.class);
        startActivity(intentos);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
