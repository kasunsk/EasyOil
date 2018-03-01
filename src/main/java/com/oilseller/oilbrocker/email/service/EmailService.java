package com.oilseller.oilbrocker.email.service;

import com.oilseller.oilbrocker.email.dto.EmailParam;

public interface EmailService {

    Boolean sendEmail(EmailParam emailParam);

}
