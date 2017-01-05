package tashfik.bughunters.iccworldtwenty202016;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApp extends Application{
	
	@Override
	public void onCreate() { 
		super.onCreate();

		Parse.initialize(this, "mwuS3EWoE08XWunnP3Q7KEt8WZPQu28xfJAaECWR", "rri66WB7txkmgPK1GOXTwFr96uaW4VjYUnwu0eJE");



		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
