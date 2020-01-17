package com.ags.modules.sys.controller;

import com.ags.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/mail")
public class MailController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 郵件發送
     * @param boardType 看板类型
     * @return
     */
    @RequestMapping("/send")
//    @RequiresPermissions("sys:mail:send")
    public R send(@RequestParam String boardType){
        //简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("james_chen@amaxgs.com");
        simpleMailMessage.setTo("lanccj@163.com");
        // 标题
        simpleMailMessage.setSubject("Happy New Year");
        // 内容
        simpleMailMessage.setText("新年快乐！");
        mailSender.send(simpleMailMessage);

        return R.ok();
    }

}
