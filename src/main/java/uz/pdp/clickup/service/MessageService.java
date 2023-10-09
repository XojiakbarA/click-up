package uz.pdp.clickup.service;

public interface MessageService {
    void sendVerifyCode(String email, String verifyCode);
}
