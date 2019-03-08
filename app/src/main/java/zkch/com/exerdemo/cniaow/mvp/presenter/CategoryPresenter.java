package zkch.com.exerdemo.cniaow.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import zkch.com.exerdemo.cniaow.bean.Category;
import zkch.com.exerdemo.cniaow.mvp.contract.CategoryContract;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class CategoryPresenter extends BasePresenter<CategoryContract.ICategoryModel, CategoryContract.CategoryView> {

    @Inject
    public CategoryPresenter(CategoryContract.ICategoryModel mModel, CategoryContract.CategoryView mView) {
        super(mModel, mView);
    }

    public void getAllCategory() {
        mModel.getCategories().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressObserver<List<Category>>(mContext, mView) {
                    @Override
                    public void onNext(List<Category> categories) {
                        mView.showData(categories);
                    }
                });
    }


}
