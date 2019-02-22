package zkch.com.exerdemo.cniaow.mvp.module;


import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.MainModel;
import zkch.com.exerdemo.cniaow.mvp.contract.MainContract;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@Module
public class MainModule {

    private MainContract.MainView mView;

    public MainModule(MainContract.MainView mView) {
        this.mView = mView;
    }

    @ScopeFragment
    @Provides
    public MainContract.MainView provideView() {
        return mView;
    }

    @Provides
    @ScopeFragment
    public MainContract.IMainModel provideModel(ApiService apiService) {
        return new MainModel(apiService);
    }


}
