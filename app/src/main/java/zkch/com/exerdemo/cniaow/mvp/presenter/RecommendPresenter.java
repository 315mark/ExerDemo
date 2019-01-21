package zkch.com.exerdemo.cniaow.mvp.presenter;

import javax.inject.Inject;

import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.model.AppInfoModel;
import zkch.com.exerdemo.cniaow.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class RecommendPresenter extends BasePresenter<AppInfoModel, AppInfoContract.RecyView> {

    //此处需要在对应Fragment 添加注入功能
    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.RecyView mView) {
        super(model, mView);
    }

    //
    public void requestDatas() {
        mModel.getIndex().compose(RxHttpReponseCompat.compatResult()).subscribe(new ProgressObserver<IndexBean>(mContext, mView) {
            @Override
            public void onNext(IndexBean indexBean) {
                mView.showResult(indexBean);
            }
        });
    }


}
