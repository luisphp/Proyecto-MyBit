package hurtado.luis.ejemplo.mybit;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class NuestraEmpresa extends AppCompatActivity implements View.OnClickListener{

    ImageView face, twi,insta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuestra_empresa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Conoce nuestra empresa");
        face = (ImageView)findViewById(R.id.facebook);
        twi = (ImageView)findViewById(R.id.twitter);
        insta = (ImageView)findViewById(R.id.instagram);
    }

public void facebook (View v){
    Intent intent = null;
    try {
        getPackageManager().getPackageInfo("com.facebook.katana", 0);
        String url = "https://www.facebook.com/LinioVenezuela";
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href="+url));
    } catch (Exception e) {
        // no Facebook app, revert to browser
        String url = "https://facebook.com/LinioVenezuela";
        intent = new Intent(Intent.ACTION_VIEW);
        intent .setData(Uri.parse(url));
    }
    this.startActivity(intent);
}
    public void IrInicio (View v){
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivity(intent);
    }


    public void twitter (View v){
        Intent intent = null;
        try {
            // get the Twitter app if possible
            this.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=624262368"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/@LinioVenezuela"));
        }
        this.startActivity(intent);
    }

    public void Instagram (View v){
        Uri uri = Uri.parse("http://instagram.com/_u/liniovenezuela");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/liniovenezuela")));
        }
    }


    @Override
    public void onClick(View view) {

    }
}
