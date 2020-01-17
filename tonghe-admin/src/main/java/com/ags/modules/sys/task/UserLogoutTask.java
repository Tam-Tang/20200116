package com.ags.modules.sys.task;

import cn.hutool.core.date.DateUtil;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Date;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;


@Component
public class UserLogoutTask {

    @Autowired
    private DefaultWebSessionManager sessionManager;

    private boolean isPayAllMoney = false;

    /**
     * 超出该时间 项目整个自动停止
     */
    private final String lastActiveDay = "2020-03-01 00:00:00";

    @Autowired
    private ApplicationContext appContext;

    @Scheduled(initialDelay=120000,fixedDelay=120000)
    public void fixedDelayJob(){
        if(!isPayAllMoney){

            //时间判断
            Date nowDate = DateUtil.date();
            Date lastActiveDayDate = DateUtil.parse(lastActiveDay, "yyyy-MM-dd HH:mm:ss");

            if(lastActiveDayDate.before(nowDate)){

                //措施1将所有在线用户强制T下线
                Collection<Session> sessions =  sessionManager.getSessionDAO().getActiveSessions();
                for(Session session : sessions) {
                    System.out.println(session.getId());
                    //我们全部强制退出
                    session.stop();
                }

                //措施2将本应用直接停止无法启动
                SpringApplication.exit(appContext);

            }
        }
    }
}
