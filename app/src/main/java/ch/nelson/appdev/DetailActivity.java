package ch.nelson.appdev;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int idEscort = 0;
        if (intent.hasExtra("idEscort"))
        {
            idEscort = intent.getIntExtra("idEscort",0);
            System.out.println("Id de l'escort = "+idEscort);
        }/*
        else {
            idEscort = "0";
        }*/

        /* String info = "escortInfo";
        String infoPhoto = "escortInfoPhoto";
        BackgroundWorker bgW = new BackgroundWorker(this);
        bgW.execute(infoPhoto,Integer.toString(idEscort));
        //bgW.execute(infoPhoto,"1"); */


        initializeViewPager();
    }

    public ArrayList<String> imageUrls;
    public ViewPager pager;

  // test pour prendre les photos du serveur
  public void chargerPhoto()
    {
        System.out.println("Charger photoooooooo ");
        System.out.println("imageUrls aaaaaa  ");
        System.out.println(imageUrls);
        imageUrls = null;
        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }


    public void initializeViewPager() {
        pager = (ViewPager) findViewById(R.id.pager);

        imageUrls = new ArrayList<String>();
        imageUrls.add("https://tentazione.fr/apptchoin/imageGirl/monika1.jpeg");
        imageUrls.add("https://tentazione.fr/apptchoin/imageGirl/monika2.jpeg");

        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }

    public class MyPagerAdapter extends PagerAdapter {

        Context mContext;
        ArrayList<String> imageUrls;

        public MyPagerAdapter(Context context, ArrayList<String> pageContents) {
            mContext = context;
            imageUrls = pageContents;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(mContext);
            Picasso.get()
                    .load(imageUrls.get(position))
                    .fit()
                    .centerCrop()
                    .into(imageView);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public int getCount() {
            return imageUrls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
