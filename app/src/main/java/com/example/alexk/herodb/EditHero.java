package com.example.alexk.herodb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditHero extends AppCompatActivity{

    static final int GALLERY_REQUEST = 1;
    public int idHero;
    private HeroDB heroDb;
    private Hero hero;
    private Uri selectedImage;
    ImageView heroAvatar;
    EditText heroName;
    EditText heroRealName;
    EditText heroAbout;
    Spinner heroWorld;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hero);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        idHero=(getIntent().getExtras().getInt("HeroObject"));
        heroDb = new HeroDB(this);
        hero = heroDb.getHero(idHero);

        heroAvatar = (ImageView) findViewById(R.id.ivPhoto);
        heroName = (EditText) findViewById(R.id.tvName);
        heroRealName = (EditText) findViewById(R.id.tvRealName);
        heroAbout = (EditText) findViewById(R.id.tvAboutInfo);
        heroWorld = (Spinner) findViewById(R.id.spinnerWorld);



        heroAvatar.setImageURI(Uri.parse(hero.getPhotoFile()));
        heroName.setText(hero.getName());
        heroRealName.setText(hero.getRealName());
        heroAbout.setText(hero.getAboutInfo());

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) heroWorld.getAdapter();
        int position = adapter.getPosition(hero.getWorld());
        heroWorld.setSelection(position);


    }

    public void updateHero() {
        hero.setName(heroName.getText().toString());
        hero.setRealName(heroRealName.getText().toString());
        hero.setAboutInfo(heroAbout.getText().toString());

            try {
                hero.setPhotoFile(selectedImage.toString());
            } catch (NullPointerException e) {
                hero.setPhotoFile(Uri.parse(hero.getPhotoFile()).toString());
            }

        hero.setWorld(heroWorld.getSelectedItem().toString());

            if (hero.getName() != null & hero.getRealName() != null & hero.getAboutInfo() != null) {
                heroDb.updateHero(hero);
                heroDb.close();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),
                        R.string.toast_nodata, Toast.LENGTH_SHORT);
                toast.show();
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
       super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


        try {
            selectedImage = imageReturnedIntent.getData();
            heroAvatar.setImageURI(selectedImage);
        } catch (NullPointerException e) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    R.string.toast_selectimage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void addAvatar(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
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
            updateHero();
            goToMainActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(EditHero.this, MainActivity.class);
        startActivity(intent);
    }



}
