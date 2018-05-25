package com.camacuasoft.jazzfestivalapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.camacuasoft.jazzfestivalapp.Adapters.BasicImageDownloader;
import com.camacuasoft.jazzfestivalapp.Adapters.NullOnEMptyCOnverterFactory;
import com.camacuasoft.jazzfestivalapp.App.FestivalMainApp;
import com.camacuasoft.jazzfestivalapp.Fragments.ArtistsFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.CalendarFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.DonationFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.FacebookFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.FavsFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.InfoFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.NewsFragment;
import com.camacuasoft.jazzfestivalapp.Fragments.SocialMediaFragment;
import com.camacuasoft.jazzfestivalapp.Interfaces.ArtistService;
import com.camacuasoft.jazzfestivalapp.Models.Artist;
import com.camacuasoft.jazzfestivalapp.Models.Info;
import com.camacuasoft.jazzfestivalapp.Models.News;
import com.camacuasoft.jazzfestivalapp.Models.Show;
import com.camacuasoft.jazzfestivalapp.Models.TimeStamps;
import com.camacuasoft.jazzfestivalapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    RealmResults<Artist> artists;
    RealmResults<News> news;
    RealmResults<Show> shows;
    RealmResults<Info> infos;
    RealmResults<TimeStamps> stampsRealm;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ArtistService artistService;
    List<Artist> artistsBdd;
    boolean updateDB = false;
    boolean downloadImages = false;
    boolean artistUpdated = false;
    private Uri returnUri;

    private final int GENERAL_STAMP_ID = 0;
    private final int INFO_STAMP_ID = 1;
    private final int SHOW_STAMP_ID = 2;
    private final int ARTIST_STAMP_ID = 3;
    private final int BANNERS_STAMP_ID = 4;
    private final int SOCIAL_STAMP_ID = 5;
    private final int SEM_STAMP_ID = 6;

    private final int REQUEST_CODE_PERMISSION = 231;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);

        SetToolBar();

        //** Creating dummy db content
        realm = Realm.getDefaultInstance();
        // Get stamps
        stampsRealm = realm.where(TimeStamps.class).findAllSorted("ID");

        //** Connection with server
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104/androidTest/")
                .addConverterFactory(new NullOnEMptyCOnverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        artistService = retrofit.create(ArtistService.class);

        // Check stamp
        Call<List<TimeStamps>> timeStamps = artistService.getStamps("all");
        timeStamps.enqueue(new Callback<List<TimeStamps>>() {
            @Override
            public void onResponse(Call<List<TimeStamps>> call, Response<List<TimeStamps>> response) {
                List<TimeStamps> stamps = response.body();
                if(stampsRealm.size() > 0) {
                    if (stamps.get(0).getTimeStamp() > stampsRealm.get(0).getTimeStamp()) {
                        updateDatabases(stamps);
                    }
                } else {
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(stamps);
                    realm.commitTransaction();
                    updateDB = true;
                    updateDatabases(stamps);
                }
            }

            @Override
            public void onFailure(Call<List<TimeStamps>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        /*artists = realm.where(Artist.class).findAllSorted("ID");
        news = realm.where(News.class).findAllSorted("date");
        shows = realm.where(Show.class).findAllSorted("date");
        infos = realm.where(Info.class).findAll();

        if((artists.size() == 0) || (news.size() == 0)) {
            Artist dummyArtist_1 = new Artist("Victor Wooden", R.drawable.victor_wooten, "USA"," ");
            Artist dummyArtist_2 = new Artist("Mike Stern", R.drawable.mike_stern, "USA", " ");

            dummyArtist_1.setBio("El menor de 5 hermanos Victor Wooten, con solo dos años, su hermano Regi le enseñó a tocar el bajo , con 5 años Victor podía tocar líneas simples y conciertos. La banda de los Wooten five, formada por Regi, Rudy, Roy, Joseph , Victor y Ross.\n" +
                    "\n" +
                    "Tocó durante los años 1970 en la ciudad de Williamsburg, Virginia en el parque temático Busch Gardens y fue telonero de Curtis Mayfield y War. Tras mudarse a Nashville, Tennessee en 1988 Victor fue inmediatamente reclutado por el cantante de blues y soul Jonell Mosser. Un año más tarde, se unió al maestro en banjo Béla Fleck, junto con Howard Levy, que tocaba el teclado y la armónica y el hermano de Victor, Roy Wooten como baterista. Su grupo, Béla Fleck and the Flecktones, se hizo famoso a base de tocar una mezcla de jazz, funk y Bluegrass, llegando más tarde a ser una de las bandas más desinhibidas estilísticamente del panorama. (Levy dejó el grupo y fue sustituido por el saxofonista y tubista Jeff Coffin).\n" +
                    "\n" +
                    "Wooten también ha sido miembro de varias bandas con miembros ya consagrados, como Bass Extremes (con Steve Bailey, Derico Watson y Oteil Burbridge), Vital Tech Tones (con Scott Henderson y Steve Smith), y el trío Extraction (con Greg Howe y Dennis Chambers). Victor también ha estado de gira con muchas bandas, incluyendo a Dave Matthews Band.");

            dummyArtist_2.setBio("Mike Stern (Boston, 10 de enero de 1953) es un guitarrista estadounidense de jazz. Ha trabajado con músicos como Miles Davis, Stan Getz, Jaco Pastorius, Joe Henderson, Jim Hall, Pat Martino, Tom Harrell, Arturo Sandoval, Tiger Okoshi, Michael Brecker, Bob Berg, David Sanborn, Steps Ahead y los Brecker Brothers.\n" +
                    "\n" +
                    "Graduado en el Berklee College of Music de Boston, se orientó hacia el jazz desde su formación. Se unió a Blood, Sweat & Tears durante una gira en 1977 y participó en tres de sus discos: In Concert, More than Ever y Brand New Day. Desde entonces ha trabajado con multitud de músicos, destacando su colaboración con Miles Davis desde 1981 hasta 1983.\n" +
                    "\n" +
                    "Su primer álbum de estudio, Upside Downside, salió al mercado en 1986 en el sello Atlantic Records, y contó con la colaboración de Jaco Pastorius, David Sanborn y Bob Berg. Su segundo trabajo, titulado Time in Place, fue publicado en 1988 e incluye a Peter Erskine en la batería, Jim Beard en el teclado, Jeff Andrews en el bajo, Don Alias en la percusión y Don Grolnick al órgano. A este disco le siguió Jigsaw un año después, cuando formó un grupo con Berg, Dennis Chambers y Lincoln Goines que duró hasta 1992. En 1993 grabó su mejor disco, Standards (And Other Songs), que le valió para ser proclamado como «Mejor guitarrista del año» por la revista Guitar Player. Ha realizado varios álbumes con su esposa, Leni Stern (1952-). Recibió dos nominaciones a los premios Grammy en 1994 y 1996 por los discos Is What It Is y Between the Lines.");

            Calendar dummyDate_1 = Calendar.getInstance();
            Calendar dummyDate_2 = Calendar.getInstance();
            dummyDate_1.set(2017, 6, 6, 21, 0);
            dummyDate_1.set(2017, 6, 8, 22, 30);
            String dummyInfo_1 = "Uno de los mejores bajistas de los últimos tiempos. Victor Wooten tendra su momento esperado por muchos en el cuadro del evento";
            String dummyInfo_2 = "Con su repertorio de temas increibles y la compañía de dos grandes amigos, esta promete ser la cereza del postre para una fecha mágica del \" La Plata Jazz Festival\"";
            News dummyNews_1 = new News(dummyDate_1.getTime(), "Victor Wooden en La Plata", dummyArtist_1, dummyInfo_1+dummyInfo_2);
            News dummyNews_2 = new News(dummyDate_2.getTime(), "Meet & Greet con Mike Stern", dummyArtist_2, dummyInfo_1+dummyInfo_2);

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(dummyArtist_1);
            realm.copyToRealmOrUpdate(dummyArtist_2);
            realm.copyToRealmOrUpdate(dummyNews_1);
            realm.copyToRealmOrUpdate(dummyNews_2);
            realm.commitTransaction();
        }

        if(shows.size() == 0) {
            Calendar showCal = Calendar.getInstance();
            artists = realm.where(Artist.class).findAllSorted("ID");
            showCal.set(2017, 6, 6, 21, 0);
            Show dummyShow_1 = new Show(showCal.getTime(), artists.get(0), "Victor Wooten");
            showCal.set(2017, 6, 9, 22, 30);
            Show dummyShow_2 = new Show(showCal.getTime(), artists.get(1), "Mike Stern");

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(dummyShow_1);
            realm.copyToRealmOrUpdate(dummyShow_2);
            realm.commitTransaction();
        }

        if(infos.size() == 0) {
            Info dummyInfo_1 = new Info("Jazz Festival en la Plata", "Se llevará a cabo en el Estadio unico entre los días no se y que se yo", " ");
            Info dummyInfo_2 = new Info("Como llegar", "Se puede ir en tren desde Estación Constitución, en colectivo o en auto", " ");

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(dummyInfo_1);
            realm.copyToRealmOrUpdate(dummyInfo_2);
            realm.commitTransaction();
        }*/

        //** Set general Height and Width
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        FestivalMainApp.finalWidth = size.x;
        FestivalMainApp.finalHeight = FestivalMainApp.finalWidth/2;
        FestivalMainApp.totalWidth = size.x;
        FestivalMainApp.totalHeight = size.y;

        //** Banner list initialization
        FestivalMainApp.bannerList = new ArrayList();
        FestivalMainApp.bannerList.add(R.drawable.banner_dangelico);
        FestivalMainApp.bannerList.add(R.drawable.banner_nord_black);
        FestivalMainApp.bannerList.add(R.drawable.banner_qsc);
        FestivalMainApp.bannerList.add(R.drawable.banner_yamaha);

        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(this.getResources(), R.drawable.banner_dangelico, opt);
        FestivalMainApp.bannerHeight = opt.outHeight;

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch(item.getItemId()){
                    case R.id.menu_draw_novedades:
                        fragment = new NewsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_artistas:
                        fragment = new ArtistsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_agenda:
                        fragment = new CalendarFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_fav:
                        fragment = new FavsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_media:
                        fragment = new SocialMediaFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_infoutil:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_donation:
                        fragment = new DonationFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_draw_map:
                        Uri gmmIntentUri = Uri.parse("google.navigation:q=-34.9217636,-57.9644846");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                        drawerLayout.closeDrawers();
                }
                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, fragment).commit();
                    item.setChecked(true);
                    getSupportActionBar().setTitle(item.getTitle());
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new NewsFragment()).commit();
        getSupportActionBar().setTitle("Novedades");

    }

    private void SetToolBar() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_hamb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open nav. drawer
                drawerLayout.openDrawer(GravityCompat.START, true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateDatabases(List<TimeStamps> stamps){

        artistUpdated = false;

        if((updateDB) || (stamps.get(INFO_STAMP_ID).getTimeStamp() > stampsRealm.get(INFO_STAMP_ID).getTimeStamp())){
            realm.beginTransaction();
            stampsRealm.get(INFO_STAMP_ID).setTimeStamp(stamps.get(INFO_STAMP_ID).getTimeStamp());
            realm.copyToRealmOrUpdate(stampsRealm.get(INFO_STAMP_ID));
            realm.commitTransaction();


        }

        if((updateDB) || (stamps.get(ARTIST_STAMP_ID).getTimeStamp() > stampsRealm.get(ARTIST_STAMP_ID).getTimeStamp())){
            final List<TimeStamps> innerStamps = stamps;
            Call<List<Artist>> showCall =  artistService.getAllArtist();

            artistUpdated = true;

            showCall.enqueue(new Callback<List<Artist>>() {
                @Override
                public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                    checkWritingPermission();
                    List<Artist> serverArtists = response.body();

                    for(Artist artist : serverArtists) {
                        artist.setPhotoResUri(updateImage(artist.getPhotoRes(), artist.getName()));
                    }

                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(serverArtists);
                    realm.commitTransaction();

                    //if((innerStamps.get(SHOW_STAMP_ID).getTimeStamp() > stampsRealm.get(SHOW_STAMP_ID).getTimeStamp())){
                        Call<List<Show>> showCall =  artistService.getShows("all", "");
                        showCall.enqueue(new Callback<List<Show>>() {
                            @Override
                            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                                List<Show> serverShows = response.body();
                                for(Show s : serverShows) {
                                    RealmResults<Artist> allArtists = realm.where(Artist.class).findAllSorted("ID");
                                    artists = realm.where(Artist.class).equalTo("ID", s.getArtistID()).findAllSorted("ID");
                                    s.setArtist(artists.get(0));
                                    s.setDate(new Date(s.getDateInt()));
                                }

                                realm.beginTransaction();
                                realm.copyToRealmOrUpdate(serverShows);
                                realm.commitTransaction();
                            }

                            @Override
                            public void onFailure(Call<List<Show>> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Request error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                        realm.beginTransaction();
                        stampsRealm.get(SHOW_STAMP_ID).setTimeStamp(innerStamps.get(SHOW_STAMP_ID).getTimeStamp());
                        realm.copyToRealmOrUpdate(stampsRealm.get(SHOW_STAMP_ID));
                        realm.commitTransaction();
                    //}

                }

                @Override
                public void onFailure(Call<List<Artist>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Request error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            realm.beginTransaction();
            stampsRealm.get(ARTIST_STAMP_ID).setTimeStamp(stamps.get(ARTIST_STAMP_ID).getTimeStamp());
            realm.copyToRealmOrUpdate(stampsRealm.get(ARTIST_STAMP_ID));
            realm.commitTransaction();
        }

        if(!artistUpdated) {
            if((updateDB) || (stamps.get(SHOW_STAMP_ID).getTimeStamp() > stampsRealm.get(SHOW_STAMP_ID).getTimeStamp())){
                Call<List<Show>> showCall =  artistService.getShows("all", "");
                showCall.enqueue(new Callback<List<Show>>() {
                    @Override
                    public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                        List<Show> serverShows = response.body();
                        for(Show s : serverShows) {
                            RealmResults<Artist> allArtists = realm.where(Artist.class).findAllSorted("ID");
                            artists = realm.where(Artist.class).equalTo("ID", s.getArtistID()).findAllSorted("ID");
                            s.setArtist(artists.get(0));
                            s.setDate(new Date(s.getDateInt()));
                        }

                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(serverShows);
                        realm.commitTransaction();
                    }

                    @Override
                    public void onFailure(Call<List<Show>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Request error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                realm.beginTransaction();
                stampsRealm.get(SHOW_STAMP_ID).setTimeStamp(stamps.get(SHOW_STAMP_ID).getTimeStamp());
                realm.copyToRealmOrUpdate(stampsRealm.get(SHOW_STAMP_ID));
                realm.commitTransaction();
            }
        }



        updateDB = false;
    }

    private String updateImage(String imageUrl, String name) {

        Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.JPEG;
        String[] fileName = name.split(" ");
        String newName = "";

        for(String s:fileName){
            newName += s;
        }

        //final String imagePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
          //      File.separator + "Download" + File.separator + newName + "." + mFormat.name().toLowerCase();
        final String imagePath = "/storage/emulated/0/Download/descarga.png";

        final BasicImageDownloader imageDownloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                Toast.makeText(MainActivity.this, "Image downloader error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressChange(int percent) {

            }

            @Override
            public void onComplete(Bitmap result) {
                /* save the image - I'm gonna use JPEG */
                final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.JPEG;
                        /* don't forget to include the extension into the file name */
                final File myImageFile = new File(imagePath); // artistsBdd.get(0).getName()
                BasicImageDownloader.writeToDisk(myImageFile, result, new BasicImageDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Toast.makeText(MainActivity.this, "Image saved as: " + myImageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        Toast.makeText(MainActivity.this, "Error code " + error.getErrorCode() + ": " +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }, mFormat, false);
            }
        });

        imageDownloader.download(imageUrl, false);

        return imagePath;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
            } else {
                // permission wasn't granted
            }
        }
    }

    private void checkWritingPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // permission wasn't granted
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            }
        }
    }
}
