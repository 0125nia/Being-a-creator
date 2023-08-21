package com.nia.reactor;

import com.nia.dao.loader.ConfigLoader;
import com.nia.dao.persistent.PersistenceContext;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimingSave {
    private static Timer timer;
    private static final boolean isOpen = ConfigLoader.getString("switch").equals("1");
    private static final long timing = ConfigLoader.getInt("timing");

    public TimingSave() {
        timer = new Timer();
    }

    public void start(){
        System.out.println(isOpen);
        if (isOpen){
            timing();
        }
    }

    private void timing() {
        // 定期执行任务
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PersistenceContext.getPERSISTENCESTRATEGY().save();
                Reactor.LOGGER.info("定期执行save方法");
            }
        }, new Date(), timing);

        // 关闭定时器
        timer.cancel();
    }


    public void stop(){
        timer.cancel();
    }
}
