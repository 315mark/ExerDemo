package zkch.com.exerdemo.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.RecommendFragment;
import zkch.com.exerdemo.mvp.module.RecommendModule;
import zkch.com.exerdemo.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = RecommendModule.class ,dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
