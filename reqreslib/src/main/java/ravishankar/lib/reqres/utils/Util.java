package ravishankar.lib.reqres.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Ravishankar Ahirwar
 * @since 21/01/2017.
 * @version 1.0
 */
public class Util {

    /**
     *
     * @param context activity/appcontext
     * @return if internet connection available return true otherwise false
     */
    public static boolean isInternetAvailable(Context context) {
        if(context == null) {
            return false;
        }

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        return connected;
    }
}
