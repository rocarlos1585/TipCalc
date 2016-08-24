package hablemoscodigo.robert.android.tipcalc;

import android.app.Application;

/**
 * Created by rober on 14/07/2016.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL = "https://www.facebook.com/hablemoscodigo/";

    public String getAboutUrl(){
        return ABOUT_URL;
    }
}
