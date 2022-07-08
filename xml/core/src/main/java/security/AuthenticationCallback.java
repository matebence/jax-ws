package security;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.Callback;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationCallback implements CallbackHandler {

    public final static String SIGNED_ENCRYPTED_CONSUMER_USERNAME = "myclientkey";
    public final static String SIGNED_ENCRYPTED_PRODUCER_USERNAME = "myservicekey";
    public final static String PASSWORD_USERNAME = "ecneb";

    public final static Map<String, String> database = new HashMap<>();

    static {
        database.put(PASSWORD_USERNAME, "pwd123");
        database.put(SIGNED_ENCRYPTED_PRODUCER_USERNAME, "skpass");
        database.put(SIGNED_ENCRYPTED_CONSUMER_USERNAME, "ckpass");
    }

    @Override
    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            WSPasswordCallback passwordCallBack = (WSPasswordCallback) callback;
            String userName = passwordCallBack.getIdentifier();
            switch (passwordCallBack.getUsage()) {
                case WSPasswordCallback.USERNAME_TOKEN:
                case WSPasswordCallback.SIGNATURE:
                case WSPasswordCallback.DECRYPT:
                    passwordCallBack.setPassword(database.get(userName));
                    break;
            }
        }
    }
}
