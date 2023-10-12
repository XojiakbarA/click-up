package uz.pdp.clickup.service;

import uz.pdp.clickup.entity.WorkspaceUser;

public interface MessageService {
    void sendVerifyCode(String email, String verifyCode);

    void sendInviteMessage(String email, WorkspaceUser workspaceUser, String suggestedPersonFullName);
}
