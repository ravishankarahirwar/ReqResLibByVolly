package ravishankar.lib.reqres.callbacks;


import ravishankar.lib.reqres.factory.ResponseResults;

/**
 * @author Ravishankar Ahirwar
 * @since 21/01/2017.
 * @version 1.0
 */

public interface ConnectionListener extends ResponseResults {
    /**
     * @param responseResult There is two possible value of response CONNECTION_ERROR & RESPONSE_OK
     *                       if request not reached at server we return CONNECTION_ERROR otherwise return RESPONSE_OK.
     * @param response       This is the response object we get it from Server side.
     */
    void onResponse(int responseResult, String response);
    void onError(int responseResult, int apiTag, String message);
}
