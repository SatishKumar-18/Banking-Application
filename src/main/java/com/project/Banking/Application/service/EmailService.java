package com.project.Banking.Application.service;

import com.project.Banking.Application.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
