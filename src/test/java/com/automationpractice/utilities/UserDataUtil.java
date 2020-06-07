package com.automationpractice.utilities;

import com.automationpractice.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserDataUtil {

    private static final String JSON_INVALID_DATA_PATH = System.getProperty("user.dir") + PropertyHelper.getInstance().getJsonInvData();
    private static final String JSON_VALID_DATA_PATH = System.getProperty("user.dir") + PropertyHelper.getInstance().getJsonValidData();

    private static final Logger logger = LogManager.getLogger(UserDataUtil.class);

    public User getInvalidUserData() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(dateFormat);


        User user = objectMapper.readValue(new File(JSON_INVALID_DATA_PATH),
                User.class);

        return user;
    }

    public User getValidUserData() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        objectMapper.setDateFormat(dateFormat);


        User user = objectMapper.readValue(new File(JSON_VALID_DATA_PATH),
                User.class);

        return user;
    }

}
