package vn.couple_app.notification_service.helper;

import org.apache.logging.log4j.util.Strings;
import vn.couple_app.api.definition.RegexDefine;

import java.util.regex.Pattern;

public class ValidatorCustom {
    public static boolean isEmailValid(String email) {
        if (Strings.isEmpty(email)) return false;
        return Pattern.matches(RegexDefine.EMAIL, email);
    }
}