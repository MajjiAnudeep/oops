package com.demo.oops.utility;

import com.demo.oops.constant.Constants;

import static com.demo.oops.constant.Constants.*;

public class MessageUtils {

    public static String createContent(String userName, Integer contentIndex) {
        return String.format(SAMPLE_MESSAGE, userName, (contentIndex + 1));
    }

    public static String createUser(Integer userIndex) {
        return String.format(Constants.SAMPLE_USER, (userIndex + 1));
    }
}
