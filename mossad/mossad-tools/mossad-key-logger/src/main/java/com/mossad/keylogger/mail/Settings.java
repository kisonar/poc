package com.mossad.keylogger.mail;

import java.util.Properties;

public final class Settings {




    private Settings(){

    }

    public static Properties getSettings(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.wp.pl");
        properties.put("mail.smtp.port", "465");
        return properties;
    }


}
