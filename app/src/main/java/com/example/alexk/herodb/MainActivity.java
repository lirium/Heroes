package com.example.alexk.herodb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView lvHeroes;
    HeroDB db;
    List<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addHero();
            }
        });


        db = new HeroDB(this);//

        //заполнение демо данными
        if (db.getHeroCount() == 0) {
            db.addHero(new Hero("Бэтмен","Бэтмен реал нейм","Летающая мышь, ловит котов", "Бэтмен", "android.resource://com.example.alexk.herodb/drawable/batman"));
            db.addHero(new Hero("Росомаха","Росомаха реал нейм" ,"Когтистый парень, перебрал с приемом железа", "Марвел", "android.resource://com.example.alexk.herodb/drawable/rosomaha"));
            db.addHero(new Hero("Супермен","Супермен реал нейм" ,"Регулярно спасает мир", "DC COMICS", "android.resource://com.example.alexk.herodb/drawable/superman"));
        }
        lvHeroes = (ListView) findViewById(R.id.listView2);
        heroes = db.getAllHero();
        ArrayAdapter adapter = new ImageAdapter(this, heroes);
        lvHeroes.setAdapter(adapter);

        lvHeroes.setOnItemClickListener(new DrawerItemClickListener());
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {


        //клик по списку
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
            Intent intentHero = new Intent(MainActivity.this, HeroActivity.class);
            intentHero.putExtra("HeroName", heroes.get(position).getName());
            intentHero.putExtra("HeroRealName", heroes.get(position).getRealName());
            intentHero.putExtra("HeroAboutInfo", heroes.get(position).getAboutInfo());
            intentHero.putExtra("HeroWorld", heroes.get(position).getWorld());
            intentHero.putExtra("HeroAvatar", heroes.get(position).getPhotoFile());
            intentHero.putExtra("HeroObject", heroes.get(position).getID());

            startActivity(intentHero);


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_addHero) {
            addHero();
        }
        if (id == R.id.action_search) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Данный функционал временно отсутствует", Toast.LENGTH_SHORT);
            toast.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addHero(){
        Intent intent = new Intent(MainActivity.this, AddHeroActivity.class);
        startActivity(intent);
    }
}
