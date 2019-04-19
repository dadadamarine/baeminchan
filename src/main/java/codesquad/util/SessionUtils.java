package codesquad.util;

import codesquad.domain.Account;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static final String USER_SESSION_KEY = "sessionedUser";

    public static boolean isLogin(HttpSession session) {
        if (session.getAttribute(USER_SESSION_KEY) == null) {
            return false;
        }
        return true;
    }

    public static Object getLoginUser(HttpSession session) {
        if(!isLogin(session)){
            return null;
        }
        return (Account) session.getAttribute(USER_SESSION_KEY);
    }

    public static Account getUserFromSession(NativeWebRequest nativeWebRequest) {
        if (!isLogin(nativeWebRequest)) {
            return Account.GUEST_ACCOUNT;
        }
        return (Account) nativeWebRequest.getAttribute(USER_SESSION_KEY, NativeWebRequest.SCOPE_SESSION);
    }

    private static boolean isLogin(NativeWebRequest nativeWebRequest) {
        Object sessionUser = nativeWebRequest.getAttribute(USER_SESSION_KEY, NativeWebRequest.SCOPE_SESSION);
        if (sessionUser == null) {
            return false;
        }
        return true;
    }
}
