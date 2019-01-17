package zkch.com.exerdemo.mvp.module;

import android.app.ProgressDialog;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.fragment.RecommendFragment;
import zkch.com.exerdemo.mvp.contract.AppInfoContract;

@Module
public class RecommendModule {

    private AppInfoContract.RecyView mView;

    public RecommendModule(AppInfoContract.RecyView mView) {
        this.mView = mView;
    }

    @Provides
    public AppInfoContract.RecyView provideView() {

        return mView;
    }

    @Provides
    public AppInfoModel provideRecommendModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

    @Provides
    public android.app.ProgressDialog provideProgressDialog(AppInfoContract.RecyView reView) {
        return new ProgressDialog(((RecommendFragment) reView).getActivity());
    }

}
