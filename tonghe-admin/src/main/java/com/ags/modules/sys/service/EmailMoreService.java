package com.ags.modules.sys.service;

/**
 * 多邮箱发送邮件
 * */

public interface EmailMoreService {

    /**
     * 发送文本邮件
     * */
    boolean sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML邮件
     * */
    boolean sendHtmlMail(String to, String subject, String content);

}
