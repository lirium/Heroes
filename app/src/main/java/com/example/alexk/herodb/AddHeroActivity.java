package com.example.alexk.herodb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class AddHeroActivity extends AppCompatActivity {

//
    static final int GALLERY_REQUEST = 1;
    Uri selectedImage;
//    String selectedImage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);
    }

    public void addHeroButton(View v) {
        HeroDB db = new HeroDB(this);//
        EditText heroName = (EditText) findViewById(R.id.inputName);
        EditText heroRealName = (EditText) findViewById(R.id.inputRealName);
        EditText heroAbout = (EditText) findViewById(R.id.inputAbout);
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

      ImageView myImageView = (ImageView) findViewById(R.id.ImageViewAvatar);
      selectedImage = imageReturnedIntent.getData();
      myImageView.setImageURI(selectedImage);

    }

}
