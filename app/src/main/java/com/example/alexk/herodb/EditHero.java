package com.example.alexk.herodb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditHero extends AppCompatActivity{

    static final int GALLERY_REQUEST = 1;
    public int idHero;
    private HeroDB heroDb;
    private Hero hero;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);
        idHero=(getIntent().getExtras().getInt("HeroObject"));
        heroDb = new HeroDB(this);
        hero = heroDb.getHero(idHero);



    }

    public void addHeroButton(View v) {
//        HeroDB db = new HeroDB(this);//
//        EditText heroName = (EditText) findViewById(R.id.inputName);
//        EditText heroRealName = (EditText) findViewById(R.id.inputRealName);
//        EditText heroAbout = (EditText) findViewById(R.id.inputAbout);
//        Spinner heroWorld = (Spinner) findViewById(R.id.spinnerWorld);
//      //  db.addHero(new Hero(heroName.getText().toString(), heroRealName.getText().toString(), heroAbout.getText().toString(),heroWorld.getSelectedItem().toString(), selectedImage.toString()));
//        Intent intent = new Intent(EditHero.this, MainActivity.class);
//        startActivity(intent);
    }

    public void addAvatarButton(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }


}
