package hurtado.luis.ejemplo.mybit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Flujograma extends AppCompatActivity {


    String parametro = MenuDepartamentos.parametro.getText().toString();
    ImageView proceso1,proceso2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flujograma);


        proceso1 = (ImageView)findViewById(R.id.imageview1);
        proceso2 = (ImageView)findViewById(R.id.imageview2);


        Glide.with(this).load(MenuDepartamentos.direccion1.toString()).into(proceso1);
        Glide.with(this).load(MenuDepartamentos.direccion2.toString()).into(proceso2);


        PhotoViewAttacher photoview = new PhotoViewAttacher(proceso1);
        photoview.update();

        PhotoViewAttacher photoview2 = new PhotoViewAttacher(proceso2);
        photoview2.update();

    }
}
