package zkch.com.exerdemo.cniaow.model;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.mvp.contract.CategoryContract;

public class CategoryModel implements CategoryContract.ICategoryModel {
    private ApiService apiService;

    public CategoryModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<Category>>> getCategories() {
        return apiService.getCategories();
    }
}
