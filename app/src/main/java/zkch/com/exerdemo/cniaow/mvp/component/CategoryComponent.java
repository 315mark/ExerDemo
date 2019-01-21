package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.CategoryFragment;
import zkch.com.exerdemo.cniaow.mvp.module.CategoryModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);

}
