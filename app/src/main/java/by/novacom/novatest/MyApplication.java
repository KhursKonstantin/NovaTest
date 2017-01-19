package by.novacom.novatest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by al-ev on 19.01.2017.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    public static MyApplication getInstance()
    {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        Stetho.initializeWithDefaults(this);
    }
}
