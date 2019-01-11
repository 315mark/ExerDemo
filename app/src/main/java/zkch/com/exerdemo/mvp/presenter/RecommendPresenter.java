package zkch.com.exerdemo.mvp.presenter;

import javax.inject.Inject;
import zkch.com.exerdemo.mvp.module.AppInfoModel;
import zkch.com.exerdemo.mvp.contract.AppInfoContract;

public class RecommendPresenter extends BasePresenter<AppInfoModel,AppInfoContract.RecyView> {

    @Inject
    public RecommendPresenter(AppInfoModel mModel, AppInfoContract.RecyView mView) {
        super(mModel, mView);
    }

    public void requestDatas() {

    }


}
