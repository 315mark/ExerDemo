package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.CategoryModel;
import zkch.com.exerdemo.cniaow.mvp.contract.CategoryContract;

@Module
public class CategoryModule {
    private CategoryContract.CategoryView mView;

    public CategoryModule(CategoryContract.CategoryView mView) {
        this.mView = mView;
    }

    @Provides
    public CategoryContract.CategoryView provideLoginView() {
        return mView;
    }

    @Provides
    public CategoryContract.ICategoryModel provideICategoryModel(ApiService apiService) {
        return new CategoryModel(apiService);
    }

}
