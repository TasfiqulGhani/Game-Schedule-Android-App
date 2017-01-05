package tashfik.bughunters.iccworldtwenty202016;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    public	ListView list;
    String m;
    int pos;
    public	double sumOfCr=0;
    double sumOfGr=0;
    String nl="\n\n";
    double cgpa=0.0;
    String st;
    public  String s,kk,res="0";
    TextView resultShow,cgpas,credits;
    SQLiteDatabase database;
    String note="\t\t\tNorth South University\n\t\tThe first private university of bangladesh\n\n";
    private final int REQ_CODE_SPEECH_INPUT = 100;
    ListViewAdapter adapter;
    EditText editsearch;

    Weeks w;
    ArrayList<Weeks> arraylist = new ArrayList<Weeks>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Notifications.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });




        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        list = (ListView) findViewById(R.id.mainlist);


        AdView mAdView = (AdView) findViewById(R.id.adViewGrade);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        w = new Weeks("Zimbabwe v Hong Kong", "Tue, 8 Mar" ,"Nagpur"    );
        arraylist.add(w);
        w = new Weeks("Scotland v Afghanistan", "Tue, 8 Mar" ,"Nagpur"    );
        arraylist.add(w);
        w = new Weeks("Bangladesh v Netherlands", "Wed, 9 Mar" ,"Dharamsala"    );arraylist.add(w);
        w = new Weeks("Ireland v Oman ", "Wed, 9 Mar" ,"Dharamsala"    );arraylist.add(w);


        w = new Weeks("Scotland v Zimbabwe", "Thu, 10 Mar" ,"Nagpur"    );arraylist.add(w);
        w = new Weeks("Hong Kong v Afghanistan", "Thu, 10 Mar" ,"Nagpur"   );arraylist.add(w);


        w = new Weeks("Netherlands v Oman ", "Fri, 11 Mar" ,"Dharamsala"    );arraylist.add(w);
        w = new Weeks("Bangladesh v Ireland", "Fri, 11 Mar" ,"Dharamsala"    );arraylist.add(w);

        w = new Weeks("Zimbabwe v Afghanistan", "Sat, 12 Mar" ,"Nagpur"    );arraylist.add(w);
        w = new Weeks("Scotland v Hong Kong ", "Sat, 12 Mar" ,"Nagpur"   );arraylist.add(w);


        w = new Weeks("Netherlands v Ireland ", "Sun, 13 Mar" ,"Dharamsala"   );arraylist.add(w);
        w = new Weeks("Bangladesh v Oman", "Sun, 13 Mar" ,"Dharamsala"   );arraylist.add(w);




        w = new Weeks("New Zealand v India", "Tue, 15 Mar" ,"Nagpur"    );arraylist.add(w);


        w = new Weeks("West Indies v England ", "Wed, 16 Mar" ,"Mumbai"   );arraylist.add(w);
        w = new Weeks("Pakistan v Q1A", "Wed, 16 Mar" ,"Kolkata"    );arraylist.add(w);


        w = new Weeks("Sri Lanka v Q1B", "Thu, 17 Mar" ,"Kolkata"   );
        arraylist.add(w);


        w = new Weeks("Australia v New Zealand", "Fri, 18 Mar" ,"Dharamsala"    );arraylist.add(w);


        w = new Weeks("South Africa v England", "Fri, 18 Mar" ,"Mumbai"    );arraylist.add(w);
        w = new Weeks("Australia v South Africa", "Fri, 18 Mar" ,"Nagpur"    );arraylist.add(w);




        w = new Weeks("India v Pakistan", "Sat, 19 Mar" ,"Dharamsala"   );arraylist.add(w);


        w = new Weeks("South Africa v Q1B", "Sun, 20 Mar" ,"Mumbai"   );arraylist.add(w);


        w = new Weeks("Sri Lanka v West Indies", "Sun, 20 Mar" ,"Bengaluru"    );arraylist.add(w);



        w = new Weeks("Australia v Q1A", "Mon, 21 Mar" ,"Bengaluru"    );arraylist.add(w);




        w = new Weeks("New Zealand v Pakistan", "Tue, 22 Mar" ,"Mohali"    );arraylist.add(w);


        w = new Weeks("England v Q1B", "Wed, 23 Mar" ,"New Delhi"    );arraylist.add(w);
        w = new Weeks("India v Q1A", "Wed, 23 Mar" ,"Bengaluru"    );arraylist.add(w);


        w = new Weeks("Pakistan v Australia", "Fri, 25 Mar" ,"Mohali"    );arraylist.add(w);
        w = new Weeks("South Africa v WestIndies", "Fri, 25 Mar" ,"Nagpur"    );arraylist.add(w);

        w = new Weeks("Q1A v New Zealand", "Sat, 26 Mar" ,"New Delhi"    );arraylist.add(w);
        w = new Weeks("England v Sri Lanka", "Sat, 26 Mar" ,"New Delhi"    );arraylist.add(w);
        w = new Weeks("India v Australia", "Sun, 27 Mar" ,"Mohali"   );arraylist.add(w);


        w = new Weeks("Q1B v West Indies", "Sun, 27 Mar" ,"Nagpur"   );arraylist.add(w);

        w = new Weeks("South Africa v Sri Lanka", "Mon, 28 Mar" ,"Bengaluru"   );arraylist.add(w);


        w = new Weeks("Super 10 Group 1 1st v Super 10 Group 2 2nd", "Thu, 31 Mar" ,"Bengaluru"   );arraylist.add(w);

        w = new Weeks("Final", "Sun, 3 April" ,"Kolkata"  );arraylist.add(w);





            adapter = new ListViewAdapter(this, arraylist);

            // Binds the Adapter to the ListView
            list.setAdapter(adapter);

            // Locate the EditText in listview_main.xml
            editsearch = (EditText) findViewById(R.id.serch);

            // Capture Text in EditText
            editsearch.addTextChangedListener(new TextWatcher() {






                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                    String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                    adapter.filter(text);
                }







                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                    // TODO Auto-generated method stub

                }






                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    // TODO Auto-generated method stub

                }
            });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,Info.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            return true;
        }
        if (id == R.id.share) {
            shareOnFb();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





    public void micg(View v) {
        promptSpeechInput();

    }












    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    editsearch.setText(result.get(0));
                }
                break;
            }

        }
    }



    public void shareOnFb(){
        String message ="https://play.google.com/store/apps/details?id=tashfik.bughunters.iccworldtwenty202016 ";
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "World T20 2016 android app");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Share this app "));

    }

    @Override
    public void onBackPressed() {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

            alertDialogBuilder.setTitle("Exit");
            alertDialogBuilder.setMessage("Do you want to close the app ?");
            // set positive button: Yes message
            alertDialogBuilder.setPositiveButton("Rate us", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=tashfik.bughunters.iccworldtwenty202016");
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=tashfik.bughunters.iccworldtwenty202016")));
                    }
                }
            });
            // set negative button: No message
            alertDialogBuilder.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // cancel the alert box and put a Toast to the user
                    MainActivity.this.finish();
                }
            });

        alertDialogBuilder.setNegativeButton("Share us", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // cancel the alert box and put a Toast to the user
                shareOnFb();
            }
        });
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show alert
            alertDialog.show();
        }
    }


