package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.mvp.module.SearchModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;
import zkch.com.exerdemo.cniaow.ui.SearchActivity;

@ScopeFragment
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {

    void inject(SearchActivity activity);
}
