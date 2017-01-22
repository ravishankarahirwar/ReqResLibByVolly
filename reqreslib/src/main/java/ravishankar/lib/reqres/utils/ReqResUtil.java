package ravishankar.lib.reqres.utils;

import com.android.volley.DefaultRetryPolicy;

/**
 * @author Ravishankar Ahirwar
 * @since 21/01/2017.
 * @version 1.0
 */

public class ReqResUtil {
    private static final int API_RETRY_TIME = 60000;
    public static final String BODY_CONTENT_TYPE = "application/json";

public static DefaultRetryPolicy getRetryPolicy() {
    return new DefaultRetryPolicy(API_RETRY_TIME,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
}
}

