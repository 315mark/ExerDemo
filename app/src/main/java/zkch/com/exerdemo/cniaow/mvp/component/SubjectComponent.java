package zkch.com.exerdemo.cniaow.mvp.component;

import dagger.Component;
import zkch.com.exerdemo.cniaow.fragment.BaseSubjectFragment;
import zkch.com.exerdemo.cniaow.mvp.module.SubjecctModule;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@ScopeFragment
@Component(modules = SubjecctModule.class, dependencies = AppComponent.class)
public interface SubjectComponent {
    void inject(BaseSubjectFragment fragment);
}
