package hurtado.luis.ejemplo.mybit;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Menu_Navegador_Drawer extends AppCompatActivity {


    DrawerLayout drawerLayout;
    ListView navlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__navegador__drawer);


    }
}
