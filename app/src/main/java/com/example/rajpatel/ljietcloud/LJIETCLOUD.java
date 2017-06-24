package com.example.rajpatel.ljietcloud;

import android.app.Application;

import com.firebase.client.Firebase;
import com.orm.SugarContext;

/**
 * Created by himangi on 20/03/16.
 */
public class LJIETCLOUD extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
