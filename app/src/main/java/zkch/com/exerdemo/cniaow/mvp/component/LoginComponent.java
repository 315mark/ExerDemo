package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.mvp.module.LoginModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;
import zkch.com.exerdemo.cniaow.ui.LoginActivity;

@ScopeFragment
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
