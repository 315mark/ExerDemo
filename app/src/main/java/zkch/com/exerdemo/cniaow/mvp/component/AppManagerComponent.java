package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.AppManagerFragment;
import zkch.com.exerdemo.cniaow.mvp.module.AppManagerModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = AppManagerModule.class, dependencies = AppComponent.class)
public interface AppManagerComponent {
    void inject(AppManagerFragment fragment);

    /*void injectDownloaded(DownloadedFragment fragment);

    void injectInstalled(InstalledAppFragment fragment);*/
}
