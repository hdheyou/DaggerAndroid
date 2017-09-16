package me.xiaobailong24.daggerlibrary;

import android.app.Application;

import javax.inject.Inject;

import me.xiaobailong24.daggerlibrary.di.component.DaggerComponent;
import me.xiaobailong24.daggerlibrary.di.component.DaggerDaggerComponent;
import me.xiaobailong24.daggerlibrary.di.module.DaggerModule;
import timber.log.Timber;

/**
 * Created by xiaobailong24 on 2017/9/16.
 * Dagger DaggerDelegate
 */

public class DaggerDelegate {
    @Inject
    DaggerActivityLifecycleCallbacks mActivityLifecycleCallbacks;
    
    private DaggerComponent mComponent;
    private final Application mApplication;

    public DaggerDelegate(Application application) {
        mApplication = application;
    }

    public void onCreate() {
        Timber.plant(new Timber.DebugTree());

        mComponent = DaggerDaggerComponent.builder()
                .daggerModule(new DaggerModule(mApplication))
                .build();
        mComponent.inject(this);

        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }


    public DaggerComponent getComponent() {
        return mComponent;
    }
}
