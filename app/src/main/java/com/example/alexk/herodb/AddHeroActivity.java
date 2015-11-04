package com.example.alexk.herodb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddHeroActivity extends AppCompatActivity {


    static final int GALLERY_REQUEST = 1;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

    }

    public void addHero() {
        HeroDB db = new HeroDB(this);//
        EditText heroName = (EditText) findViewById(R.id.tvName);
        EditText heroRealName = (EditText) findViewById(R.id.tvRealName);
        EditText heroAbout = (EditText) findViewById(R.id.tvAboutInfo);
        Spinner heroWorld = (Spinner) findViewById(R.id.spinnerWorld);
        db.addHero(new Hero(heroName.getText().toString(), heroRealName.getText().toString(), heroAbout.getText().toString(),heroWorld.getSelectedItem().toString(), selectedImage.toString()));
        Intent intent = new Intent(AddHeroActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void addAvatarButton(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

      ImageView myImageView = (ImageView) findViewById(R.id.ivPhoto);
      selectedImage = imageReturnedIntent.getData();
      myImageView.setImageURI(selectedImage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_hero, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_ok) {
            try {
                addHero();
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Заполнены не все данные", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
            return super.onOptionsItemSelected(item);
    }


}
