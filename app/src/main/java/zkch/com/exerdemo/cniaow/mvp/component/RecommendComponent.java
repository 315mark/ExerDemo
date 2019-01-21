package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.RecommendFragment;
import zkch.com.exerdemo.cniaow.mvp.module.RecommendModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;


@ScopeFragment
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
