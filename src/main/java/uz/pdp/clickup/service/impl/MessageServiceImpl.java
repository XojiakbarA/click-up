package uz.pdp.clickup.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uz.pdp.clickup.entity.WorkspaceUser;
import uz.pdp.clickup.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public void sendHtmlMessage(String email, String subject, String templateName, Context context) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setTo(email);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void sendVerifyCode(String email, String verifyCode) {
        Context context = new Context();
        context.setVariable("code", verifyCode);
        sendHtmlMessage(email, "Verify Email", "verify", context);
    }

    @Override
    public void sendInviteMessage(String email, WorkspaceUser workspaceUser, String suggestedPersonFullName) {
        Context context = new Context();
        context.setVariable("code", workspaceUser.getJoinCode());
        context.setVariable("workspaceName", workspaceUser.getWorkspace().getName());
        context.setVariable("workspaceUserId", workspaceUser.getId());
        context.setVariable("invitedPersonFullName", workspaceUser.getPerson().getFullName());
        context.setVariable("suggestedPersonFullName", suggestedPersonFullName);
        sendHtmlMessage(email, "Invite Message", "invite", context);
    }
}
