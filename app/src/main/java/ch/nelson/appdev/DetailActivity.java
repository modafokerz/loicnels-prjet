package ch.nelson.appdev;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public ArrayList<String> imageUrls;
    public ViewPager pager;
    public String pseudoEscort;
    public String corpulenceEscort;
    public String tailleEscort;
    public String ageEscort;
    public String descriptionEscort;
    public String cheveuxEscort;
    public String origineEscort;
    public String yeuxEscort;
    public String certifieEscort;
    public String disponibleEscort;
    public String villeEscort;
    public String npaEscort;
    public String sexeEscort;

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
        }

        String escortID = Integer.toString(idEscort);

        imageUrls = new ArrayList<String>();

        String info = "escortInfo";
        String infoPhoto = "escortInfoPhoto";

        BackgroundWorker bgW = new BackgroundWorker(this);
        bgW.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, info,escortID);

        BackgroundWorker bgW2 = new BackgroundWorker(this);
        bgW2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,infoPhoto,escortID);






        //initializeViewPager();
    }

    // charge les images de l'escort depuis le serveur
    public void chargerPhoto(ArrayList<String> imageUrls)
    {
        this.imageUrls = imageUrls;
        System.out.println("Charger photoooooooo ");
        System.out.println("imageUrls aaaaaa  ");
        System.out.println(imageUrls);
        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }

    // charge les services de l'escort depuis le serveur
    public void chargerService(ArrayList<String> imageUrls)
    {
        this.imageUrls = imageUrls;
        System.out.println("Charger photoooooooo ");
        System.out.println("imageUrls aaaaaa  ");
        System.out.println(imageUrls);
        pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(this, imageUrls);
        pager.setAdapter(adapter);
    }

    // charge les informations depuis le serveur
    public void chargerDetail(String pseudoEscort, String corpulenceEscort, String tailleEscort, String ageEscort, String descriptionEscort, String cheveuxEscort, String origineEscort, String yeuxEscort, String certifieEscort, String disponibleEscort, String villeEscort, String npaEscort, String sexeEscort) {
        System.out.println("Charger infoooooos ");

        this.pseudoEscort = pseudoEscort;
        this.corpulenceEscort = corpulenceEscort;
        this.tailleEscort = tailleEscort;
        this.ageEscort = ageEscort;
        this.descriptionEscort = descriptionEscort;
        this.cheveuxEscort = cheveuxEscort;
        this.origineEscort = origineEscort;
        this.yeuxEscort = yeuxEscort;
        this.certifieEscort = certifieEscort;
        this.disponibleEscort = disponibleEscort;
        this.villeEscort = villeEscort;
        this.npaEscort = npaEscort;
        this.sexeEscort = sexeEscort;

        executeChangementDetail();
    }

    private void executeChangementDetail() {
        TextView pseudo = (TextView) findViewById(R.id.detailPseudoEscort);
        TextView corpulence = (TextView) findViewById(R.id.detailCorpulenceEscort);
        TextView taille = (TextView) findViewById(R.id.detailTailleEscort);
        TextView age = (TextView) findViewById(R.id.detailAgeEscort);
        //TextView description = (TextView) findViewById(R.id.detailAgeEscort);
        TextView cheveux = (TextView) findViewById(R.id.detailCheveuxEscort);
        TextView origine = (TextView) findViewById(R.id.detailOrigineEscort);
        TextView yeux = (TextView) findViewById(R.id.detailYeuxEscort);

        pseudo.setText(pseudoEscort);
        corpulence.setText(corpulence.getText()+corpulenceEscort);
        taille.setText(taille.getText()+tailleEscort);
        age.setText(age.getText()+ageEscort);
        cheveux.setText(cheveux.getText()+cheveuxEscort);
        origine.setText(origine.getText()+origineEscort);
        yeux.setText(yeux.getText()+yeuxEscort);

    }

    public void initializeViewPager() {
        pager = (ViewPager) findViewById(R.id.pager);

        System.out.println("photo predefiniiiii ");


        imageUrls.add("https://tentazione.fr/apptchoin/imageGirl/monika1.jpeg");
        imageUrls.add("https://tentazione.fr/apptchoin/imageGirl/monika2.jpeg");

        System.out.println(imageUrls);

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
