package ch.nelson.appdev;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG ="NavigationActivity";

    private ImageView mCardImage;
    private TextView mCardTitle;


    private ListView mListView;
    public ArrayList<Femme> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// ----- nouvelle methode test
        //Appelle un layout qui contiendra la list du layout content_navigation
        setContentView(R.layout.content_navigation);

        mListView = (ListView) findViewById(R.id.listViewP);

        list = new ArrayList<>();

        String type = "afficheEscort";
        BackgroundWorker bgW = new BackgroundWorker(this);
        bgW.execute(type);

        //La liste des gouzesses
        /*list.add(new Femme("drawable://" + R.drawable.penelop3, "Pelenop no 1"));
        list.add(new Femme("https://tentazione.fr/apptchoin/imageGirl/Valerie.jpeg", "Valerie no 2"));
        list.add(new Femme("https://tentazione.fr/apptchoin/imageGirl/Valerie2.jpeg", "Biaaatch"));
        list.add(new Femme("https://tentazione.fr/apptchoin/imageGirl/Valerie3.jpeg", "Nique ta mere"));
        list.add(new Femme("https://tentazione.fr/apptchoin/imageGirl/Valerie4.jpeg", "enculeee"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.activity_afterlogin, list);
        mListView.setAdapter(adapter);*/
// fin nouvelle methode

// ancienne methode
      /* setContentView(R.layout.activity_afterlogin);

        mCardImage = (ImageView) findViewById(R.id.cardImage);
        mCardTitle = (TextView) findViewById(R.id.cardTitle);


        int imageRessource = getResources().getIdentifier("@drawable/penelop3",null,this.getPackageName());
        mCardImage.setImageResource(imageRessource);

        mCardTitle.setText("Nom de Tchoin");

        mCardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewDetails = new Intent(getApplicationContext(), DetailsActivity.class);
                startActivity(viewDetails);
            }
        });*/
// fin ancienne methode

       /* NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/
    }

    public void updateListGirl()
    {
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.activity_afterlogin, list);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
