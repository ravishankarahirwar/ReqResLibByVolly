package ravishankar.lib.reqres.factory;

/**
 * @author Ravishankar Ahirwar
 * @since 21/01/2017.
 * @version 1.0
 */

import android.content.Context;

import java.util.Map;

import ravishankar.lib.reqres.R;
import ravishankar.lib.reqres.callbacks.ConnectionListener;
import ravishankar.lib.reqres.managers.RequestManager;
import ravishankar.lib.reqres.utils.Util;


public class ConnectionFactory extends ConnectRequest {


    public ConnectionFactory(Context context, int requestType, String requestUrl,
                             ConnectionListener connectionListener) {
        super();
        mConnectionListener = connectionListener;
        this.mRequestType = requestType;
        this.mContext = context;
        this.mRequestUrl = requestUrl;
    }

    public void setPostParams(Map<String, String> postParams) {
        this.mPostParams = postParams;
    }

    public void setHeaderParams(Map<String, String> headerParams) {
        this.mHeaderParams = headerParams;
    }

    public void createConnection(int tag) {
        if(Util.isInternetAvailable(mContext)) {
            Connector connector = null;
            connector = new RequestManager(mContext, mConnectionListener, mRequestUrl, tag);
            if (mPostParams != null) {
                connector.setPostParams(mPostParams);
                connector.setHeaderParams(mHeaderParams);
            }
            connector.connect();
        } else {
            mConnectionListener.onError(ResponseResults.NO_INTERNET, tag, mContext.getString(R.string.error_no_internet));
        }
    }
}
