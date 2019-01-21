package zkch.com.exerdemo.cniaow.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public interface CategoryContract {
    interface ICategoryModel {
        Observable<BaseBean<List<Category>>> getCategories();
    }

    interface CategoryView extends BaseView {
        public void showData(List<Category> categories);
    }


}
