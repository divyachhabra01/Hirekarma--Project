package com.springboot.excel.service;

import com.springboot.excel.model.Email;

public interface EmailService {
    void sendEmailAsync ( final  Email email) throws Exception;
    void populateAndSendEmail();

}
