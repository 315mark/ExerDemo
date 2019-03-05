package zkch.com.exerdemo.cniaow.mvp.presenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.bean.SubjectDetail;
import zkch.com.exerdemo.cniaow.mvp.contract.SubjectContract;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ErrorHandlerObserver;
import zkch.com.exerdemo.common.subscriber.ProgressDialogObserver;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class SubjectPresenter extends BasePresenter<SubjectContract.ISubjectModel, SubjectContract.SubjectView> {

    @Inject
    public SubjectPresenter(SubjectContract.ISubjectModel mModel, SubjectContract.SubjectView mView) {
        super(mModel, mView);
    }

    public void getSujects(int page) {
        Observer observer;
        if (page == 0) {
            observer = new ProgressDialogObserver<PageBean<Subject>>(mContext) {
                @Override
                public void onNext(PageBean<Subject> subjectPageBean) {
                    mView.showSubjects(subjectPageBean);
                }
            };
        } else {
            observer = new ErrorHandlerObserver<PageBean<Subject>>(mContext) {
                @Override
                public void onNext(PageBean<Subject> data) {
                    mView.showSubjects(data);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }
        mModel.getSubjects(page)
                .compose(RxHttpReponseCompat.compatResult())
                .subscribe(observer);
    }

    public void getSubjectDetail(int id) {
        mModel.getSubjectDetail(id).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressObserver<SubjectDetail>(mContext, mView) {
                    @Override
                    public void onNext(SubjectDetail detail) {
                        mView.showSubjectDetail(detail);
                    }
                });
    }

}
