package com.automationpractice.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class EmailGenerator {
    public static String generateRandomEmail() {
        int length = 10;
        String generatedString = RandomStringUtils.random(length, true, false);
        return generatedString + "@test.com";
    }
}
