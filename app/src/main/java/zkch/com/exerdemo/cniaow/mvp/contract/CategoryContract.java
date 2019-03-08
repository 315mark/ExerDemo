package zkch.com.exerdemo.cniaow.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public class CategoryContract {
    public interface ICategoryModel {
        Observable<BaseBean<List<Category>>> getCategories();
    }

    public interface CategoryView extends BaseView {
        void showData(List<Category> categories);
    }


}
