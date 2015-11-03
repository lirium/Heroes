package com.example.alexk.herodb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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
        idHero=(getIntent().getExtras().getInt("HeroObject"));
        heroDb = new HeroDB(this);
        hero = heroDb.getHero(idHero);

        heroAvatar = (ImageView) findViewById(R.id.ivPhoto);
        heroName = (EditText) findViewById(R.id.inputName);
        heroRealName = (EditText) findViewById(R.id.inputRealName);
        heroAbout = (EditText) findViewById(R.id.inputAbout);
        heroWorld = (Spinner) findViewById(R.id.spinnerWorld);



        heroAvatar.setImageURI(Uri.parse(hero.getPhotoFile()));
        heroName.setText(hero.getName());
        heroRealName.setText(hero.getRealName());
        heroAbout.setText(hero.getAboutInfo());

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) heroWorld.getAdapter();
        int position = adapter.getPosition(hero.getWorld());
        heroWorld.setSelection(position);


    }

    public void updateHeroButton(View v) {
        hero.setName(heroName.getText().toString());
        hero.setRealName(heroRealName.getText().toString());
        hero.setAboutInfo(heroAbout.getText().toString());
        try {
            hero.setPhotoFile(selectedImage.toString());
        } catch (NullPointerException e) {
            hero.setPhotoFile(Uri.parse(hero.getPhotoFile()).toString());
        }
        hero.setWorld(heroWorld.getSelectedItem().toString());
        Intent intent = new Intent(EditHero.this, MainActivity.class);
        startActivity(intent);
        heroDb.updateHero(hero);
        heroDb.close();
    }

    public void addAvatarButton(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

       selectedImage = imageReturnedIntent.getData();
       heroAvatar.setImageURI(selectedImage);

    }


}
