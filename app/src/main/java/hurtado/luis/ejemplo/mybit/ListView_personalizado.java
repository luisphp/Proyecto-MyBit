package hurtado.luis.ejemplo.mybit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListView_personalizado extends AppCompatActivity {


    ListView ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_personalizado);

        ListView = (ListView) findViewById(R.id.ListView);

    }


}
