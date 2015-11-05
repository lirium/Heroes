package com.example.alexk.herodb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class HeroActivity extends AppCompatActivity {
    public int idHero;
    private HeroDB heroDb;
    private Hero hero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
        idHero=(getIntent().getExtras().getInt("HeroObject"));
    }


    public void deleteHero() {

//
        heroDb = new HeroDB(this);
        hero = heroDb.getHero(idHero);
        heroDb.deleteHero(hero);
        Intent intent = new Intent(HeroActivity.this, MainActivity.class);
        startActivity(intent);
        heroDb.close();
    }

    public void editHero() {
        Intent intentRedact = new Intent(HeroActivity.this, EditHero.class);
        intentRedact.putExtra("HeroObject", idHero);
        startActivity(intentRedact);
     //   heroDb.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hero, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_edit) {
            editHero();
        }
        if (id == R.id.action_del) {
            deleteHero();
            goToMainActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(HeroActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
