package ravishankar.lib.reqres.factory;

/**
 * @author Ravishankar Ahirwar
 * @since 21/01/2017.
 * @version 1.0
 */

import java.util.Map;

public interface Connector {
    void setPostParams(Map<String, String> postParams);

    void setHeaderParams(Map<String, String> postParams);

    void connect();

    void parseJson(String response);

}
