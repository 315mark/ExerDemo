package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.SubjecctModel;
import zkch.com.exerdemo.cniaow.mvp.contract.SubjectContract;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@Module
public class SubjecctModule {
    private SubjectContract.SubjectView mView;

    public SubjecctModule(SubjectContract.SubjectView mView) {
        this.mView = mView;
    }

    @ScopeFragment
    @Provides
    public SubjectContract.ISubjectModel provideModel(ApiService apiService) {
        return new SubjecctModel(apiService);
    }

    @ScopeFragment
    @Provides
    public SubjectContract.SubjectView provideView() {
        return mView;
    }

}
