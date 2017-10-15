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
        final String toEmail = "gleb.streltsov.4by@gmail.com";
        final String fromEmail = "ka1oken4by@gmail.com";
        final String subject = "test subject";
        final String body = "test body";

        service.sendMessage(fromEmail, toEmail, subject, body);
    }
}