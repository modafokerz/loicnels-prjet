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

    private void initializeViewPager() {
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        String[] imageUrls = new String[]{
                "https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/10/10/15/28/butterfly-2837589_960_720.jpg"
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
