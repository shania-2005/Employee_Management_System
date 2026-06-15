package com.ems.utility;

public class TestEmail {

    public static void main(String[] args) {

        EmailUtility.sendEmail(
            "receiver@gmail.com", 
            "Test Email Subject",
            "Hello! This is a test email from Java EMS project."
        );

        System.out.println("Request sent ✔ Check email inbox");
    }
}