package com.example.alexk.herodb;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class HeroActivity extends AppCompatActivity {
    public int idHero;
    private HeroDB heroDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        TextView textViewName = (TextView) findViewById(R.id.tvName);
        TextView textViewRealName = (TextView) findViewById(R.id.tvRealName);
        TextView tvAboutInfo = (TextView) findViewById(R.id.tvAboutInfo);
        ImageView ivAvatar = (ImageView) findViewById(R.id.ivPhoto);
        TextView tvWorld = (TextView) findViewById(R.id.tvWorld);


        textViewName.setText(getIntent().getExtras().getString("HeroName"));
        textViewRealName.setText(getIntent().getExtras().getString("HeroRealName"));
        tvAboutInfo.setText(getIntent().getExtras().getString("HeroAboutInfo"));
        tvWorld.setText(getIntent().getExtras().getString("HeroWorld"));

        ivAvatar.setImageURI(Uri.parse(getIntent().getExtras().getString("HeroAvatar")));
        idHero=(getIntent().getExtras().getInt("Id"));
    }


    public void onClickButtonDel (View v) {
//        Intent intent = new Intent(HeroActivity.this, MainActivity.class);
//        startActivity(intent);

        heroDb = new HeroDB(this);
        Log.d("tesing --- ", heroDb.getHero(idHero).getName());
        heroDb.deleteHero(heroDb.getHero(idHero));
        Log.d("test id--", String.valueOf(idHero));
//    db

    }

}
