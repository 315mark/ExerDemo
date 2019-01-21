package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.CategoryFragment;
import zkch.com.exerdemo.cniaow.fragment.GamesFragment;
import zkch.com.exerdemo.cniaow.fragment.TopListFragment;
import zkch.com.exerdemo.cniaow.mvp.module.AppInfoModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;


@ScopeFragment
@Component(modules = AppInfoModule.class, dependencies = AppComponent.class)
public interface AppInfoComponent {

    void injectFragment(TopListFragment fragment);

    void injectFragment(GamesFragment fragment);

    void injectFragment(CategoryFragment fragment);
}
