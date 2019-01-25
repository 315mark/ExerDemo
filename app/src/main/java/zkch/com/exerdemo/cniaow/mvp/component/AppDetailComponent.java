package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.AppDetailFragment;
import zkch.com.exerdemo.cniaow.mvp.module.AppDetailModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = AppDetailModule.class, dependencies = AppComponent.class)
public interface AppDetailComponent {
    void inject(AppDetailFragment fragment);
}
