package tashfik.bughunters.iccworldtwenty202016;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.parse.ParseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class Notifications extends AppCompatActivity implements
        TextToSpeech.OnInitListener{
    SQLiteDatabase database;
    String title,message,noticesql;
    Cursor c;
    InterstitialAd mInterstitialAd;
    private CoordinatorLayout coordinatorLayout;
    final Context context = this;
    public ListView l;
    String m;
     View coordinatorLayoutView=null;
    int pos;

    private TextToSpeech tts;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
     coordinatorLayoutView = findViewById(R.id.snackbarPosition);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fanb);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Notifications.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        tts = new TextToSpeech(this, this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7340047085744053/9347415720");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                shareOnFb();
            }
        });

        requestNewInterstitial();

        AdView mAdView = (AdView) findViewById(R.id.adViewNoti);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        try{
            ParseAnalytics.trackAppOpened(getIntent());

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String jsonData = extras.getString("com.parse.Data");

            try{
                JSONObject notification = new JSONObject(jsonData);
                String title = notification.getString("title");
                String message = notification.getString("alert");
                String date=notification.getString("date");

                createDatabase(title,message,date);

            }
            catch(JSONException e){
                Toast.makeText(getApplicationContext(), "Something went wrong with the notification", Toast.LENGTH_SHORT).show();
            }

        }catch(Exception e){
            Log.e("Fuck", "Fuck");
        }
        database = openOrCreateDatabase("newDb",MODE_PRIVATE, null);

        String 	 sql = "CREATE TABLE IF NOT EXISTS noticee (title VARCHAR ,message VARCHAR ,date VARCHAR );";
        database.execSQL(sql);

        l = (ListView) findViewById(R.id.notice);
        l.setAdapter(new ArrayAdapter<String>(this,R.layout.ltextt,readRecords()));


    }
    void createDatabase(String title, String message,String date){
        //Time,Day,Course,Room,Faculty
        database = openOrCreateDatabase("newDb",MODE_PRIVATE, null);


        noticesql = "CREATE TABLE IF NOT EXISTS noticee (title VARCHAR ,message VARCHAR,date VARCHAR  );";
        c = database.rawQuery(noticesql, null);

        database.execSQL(noticesql);


        String insertSql = "INSERT INTO noticee VALUES('"+title+"','"+message+"','"+date+"');";
        database.execSQL(insertSql);


        database.close();



    }
    ArrayList<String> readRecords(){
        ArrayList<String> record = new ArrayList<String>();


        String	 sql = "SELECT * from noticee;";
        Cursor c = database.rawQuery(sql, null);


        while (c.moveToNext()){

            String i ;

            String title = c.getString(c.getColumnIndex("title"));

            String message = c.getString(c.getColumnIndex("message"));


            String date = c.getString(c.getColumnIndex("date"));

            i = ""+message+""+date;




            record.add(i);




        }

if (record.isEmpty()){
    Snackbar snackbar = Snackbar
            .make(coordinatorLayoutView  , "No notifications so far!", Snackbar.LENGTH_LONG)
            .setAction("Back", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

// Changing message text color
    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
    View sbView = snackbar.getView();
    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setTextColor(Color.YELLOW);
    snackbar.show();
}





        //Toast.makeText(this,"selected "+note,Toast.LENGTH_LONG).show();
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long r) {

                st = ((TextView) v).getText().toString();

                pos = position;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);


                alertDialogBuilder.setTitle("Details");

                alertDialogBuilder
                        .setMessage(st)
                        .setCancelable(true)
                        .setPositiveButton("Share", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                if (mInterstitialAd.isLoaded()) {
                                    mInterstitialAd.show();
                                } else {
                                    shareOnFb();
                                }
                            }
                        })
                        .setNegativeButton("Speak Out", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                Log.e("CLicked", "clicked");
                                speakOut();
                            }
                        });


                AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();


            }


        });
        return record;



    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void shareOnFb(){
        String message = st+"\nNotification By-  App . https://play.google.com/store/apps/details?id=tashfik.bughunters.iccworldtwenty202016 ";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "Notification");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Share"));

    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.UK);

            // tts.setPitch(5); // set pitch level

            // tts.setSpeechRate(2); // set speech speed rate

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language is not supported");
            } else {

                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed");
        }

    }

    private void speakOut() {

        String text = st;

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
