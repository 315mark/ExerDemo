package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.mvp.module.MainModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;
import zkch.com.exerdemo.cniaow.ui.SimpleActivity;

@ScopeFragment
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(SimpleActivity activity);
}
