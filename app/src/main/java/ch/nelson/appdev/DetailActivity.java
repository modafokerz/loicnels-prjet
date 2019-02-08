package ch.nelson.appdev;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeViewPager();
    }

    public String[] imageUrls;
    public ViewPager pager;

  /* test pour prendre les photos du serveur
  public void chargerPhoto()
    {
        System.out.println("Charger photoooooooo ");
        System.out.println("Avant  ");
        System.out.println(imageUrls);
        imageUrls = null;
        String info = "escortInfo";
        String infoPhoto = "escortInfoPhoto";
        BackgroundWorker bgW = new BackgroundWorker(this);
        //bgW.execute(info,Integer.toString(holder.idEscort));
        bgW.execute(infoPhoto,"1");

        System.out.println("Apres  ");
        System.out.println(imageUrls);
        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }*/


    public void initializeViewPager() {
        pager = (ViewPager) findViewById(R.id.pager);

        imageUrls = new String[]{
                "https://tentazione.fr/apptchoin/imageGirl/monika1.jpeg",
                "https://tentazione.fr/apptchoin/imageGirl/monika2.jpeg"/*,
                "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"*/
        };

        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }

    public class MyPagerAdapter extends PagerAdapter {

        Context mContext;
        String[] imageUrls;

        public MyPagerAdapter(Context context, String[] pageContents) {
            mContext = context;
            imageUrls = pageContents;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(mContext);
            Picasso.get()
                    .load(imageUrls[position])
                    .fit()
                    .centerCrop()
                    .into(imageView);
            container.addView(imageView);

            return imageView;
        }

        @Override
        public int getCount() {
            return imageUrls.length;
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
