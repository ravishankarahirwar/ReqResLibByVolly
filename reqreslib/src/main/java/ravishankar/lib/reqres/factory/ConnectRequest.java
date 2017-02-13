package ravishankar.lib.reqres.factory;

import android.content.Context;

import java.util.Map;

import ravishankar.lib.reqres.callbacks.ConnectionListener;


/**
 * @author Ravishankar Ahirwar
 * @version 1.0
 * @since 21/01/2017.
 */

public class ConnectRequest {
    protected Context mContext;
    protected String mRequestUrl;
    protected int mRequestType;
    protected Map<String, String> mHeaderParams;
    protected Map<String, String> mPostParams;
    protected ConnectionListener mConnectionListener;
}
