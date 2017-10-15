package com.vg.server.email;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GmailEmailServiceManualTest {

    private EmailService service;

    @Before
    public void setUp() throws Exception {
        service = new GmailEmailService();
    }

    @Test
    public void sendEmailMessageToReadEmailAddressTest() throws Exception {
        final String toEmail = "real-email@gmail.com";
        final String fromEmail = "real-email@gmail.com";
        final String subject = "test subject";
        final String body = "test body";

        service.sendMessage(fromEmail, toEmail, subject, body);
    }
}
