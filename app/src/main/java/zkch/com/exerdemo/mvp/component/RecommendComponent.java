package zkch.com.exerdemo.mvp.component;

import android.support.v7.widget.RecyclerView;

import dagger.Component;
import zkch.com.exerdemo.mvp.module.AppModule;
import zkch.com.exerdemo.mvp.module.RecommendModule;
import zkch.com.exerdemo.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = RecommendModule.class ,dependencies = AppModule.class)
public interface RecommendComponent {

}
