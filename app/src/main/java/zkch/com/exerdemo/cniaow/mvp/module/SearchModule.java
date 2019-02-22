package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.SearchModel;
import zkch.com.exerdemo.cniaow.mvp.contract.SearchContract;
import zkch.com.exerdemo.cniaow.mvp.scope.ScopeFragment;

@Module
public class SearchModule {

    private SearchContract.SearchView mView;

    public SearchModule(SearchContract.SearchView mView) {
        this.mView = mView;
    }

    @ScopeFragment
    @Provides
    public SearchContract.SearchView provideView() {
        return mView;
    }

    @ScopeFragment
    @Provides
    public SearchContract.ISearchModel provideModel(ApiService apiService) {
        return new SearchModel(apiService);
    }

}
