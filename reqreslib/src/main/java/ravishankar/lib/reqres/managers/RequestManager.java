package ravishankar.lib.reqres.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Collections;
import java.util.Map;

import ravishankar.lib.reqres.callbacks.ConnectionListener;
import ravishankar.lib.reqres.factory.ConnectRequest;
import ravishankar.lib.reqres.factory.Connector;
import ravishankar.lib.reqres.factory.RequestPool;
import ravishankar.lib.reqres.factory.ResponseResults;
import ravishankar.lib.reqres.utils.ReqResUtil;


/**
 * @author Ravishankar Ahirwar
 * @since 12/03/2016
 * @version 1.0
 *
 * This is the manager class use for communicating with server,
 * This is the centralized class we are using for communicate with server
 * all request and response goes through with this class
 */

public class RequestManager extends ConnectRequest implements Connector {

    private static final String TAG = "RequestManager";
    int mConnectionType = Request.Method.POST;
    private String mRequestUrl;
    private String mRequestTag;
    private int mApiTag;

    public RequestManager(Context mContext, ConnectionListener connectionListener, String requestUrl, int dataTag) {
        super();
        this.mConnectionListener = connectionListener;
        this.mContext = mContext;
        this.mRequestUrl = requestUrl;
        this.mApiTag = dataTag;
        mRequestTag = String.valueOf(dataTag);
        mConnectionType = Request.Method.POST;
    }

    public RequestManager(Context mContext, int requestType, ConnectionListener connectionListener, String requestUrl, int dataTag, int connectionType) {
        this(mContext, connectionListener, requestUrl, dataTag);
        this.mConnectionType = requestType;
    }

    @Override
    public void connect() {
        StringRequest postRequest = new StringRequest(Request.Method.GET, mRequestUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mConnectionListener.onResponse(ResponseResults.RESPONSE_OK,response);
//                         parseJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if(response != null && error.networkResponse != null  && error.networkResponse.statusCode == 401){
                    mConnectionListener.onError(ConnectionListener.AUTHENTICATION_FAILED, mApiTag, error.getMessage());
                } else {
                    mConnectionListener.onError(ConnectionListener.CONNECTION_ERROR, mApiTag, error.getMessage());
                }

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                if (mPostParams == null) {
                    return null;
                } else {
                    Log.v(TAG, "Request Params : \n" + mPostParams.toString());
                    return mPostParams;
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (mHeaderParams == null) {
                    return Collections.emptyMap();
                } else {
                    Log.v(TAG, "Header Params : \n" + mHeaderParams.toString());
                    return mHeaderParams;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
//        postRequest.setRetryPolicy(ReqResUtil.getRetryPolicy());
//        postRequest.setTag(mRequestTag);
        RequestPool.getInstance(this.mContext).addToRequestQueue(postRequest);
    }


    @Override
    public void parseJson(String data) {
        this.mConnectionListener.onResponse(ConnectionListener.RESPONSE_OK, data);
    }

    @Override
    public void setPostParams(Map<String, String> postParams) {
        this.mPostParams = postParams;
    }

    @Override
    public void setHeaderParams(Map<String, String> postParams) {
        this.mHeaderParams = postParams;
    }
}
