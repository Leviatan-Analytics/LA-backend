package com.leviatan.backend.utils;

import com.leviatan.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordEmailContext extends AbstractEmailContext {

    private String token;

    public void init(User user, String senderEmail){
        put("firstName", user.getUsername());
        setTemplateLocation("email-template.html");
        setSubject("Forgotten Password");
        setFrom(senderEmail);
        setTo(user.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

}
