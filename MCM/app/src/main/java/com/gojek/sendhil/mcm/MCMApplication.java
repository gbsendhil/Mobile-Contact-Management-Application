package com.gojek.sendhil.mcm;

import android.app.Application;
import android.content.Context;

import com.gojek.sendhil.mcm.model.MCMService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class MCMApplication extends Application {

    private MCMService mcmService;
    private Scheduler defaultSubscribeScheduler;

    public static MCMApplication get(Context context) {
        return (MCMApplication) context.getApplicationContext();
    }

    public MCMService getMCMService() {
        if (mcmService == null) {
            mcmService = MCMService.Factory.create();
        }
        return mcmService;
    }

    //For setting mocks during testing
    public void setMCMService(MCMService MCMService) {
        this.mcmService = MCMService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }

    //User to change scheduler from tests
    public void setDefaultSubscribeScheduler(Scheduler scheduler) {
        this.defaultSubscribeScheduler = scheduler;
    }
}
