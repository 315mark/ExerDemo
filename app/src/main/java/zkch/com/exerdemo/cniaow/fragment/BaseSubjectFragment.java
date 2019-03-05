package zkch.com.exerdemo.cniaow.fragment;

import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.bean.SubjectDetail;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerSubjectComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.SubjectContract;
import zkch.com.exerdemo.cniaow.mvp.module.SubjecctModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.SubjectPresenter;

public abstract class BaseSubjectFragment extends ProgressFragment<SubjectPresenter> implements SubjectContract.SubjectView {

    @Override
    protected void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerSubjectComponent.builder().appComponent(appComponent)
                .subjecctModule(new SubjecctModule(this)).build().inject(this);
    }

    @Override
    public void showSubjects(PageBean<Subject> data) {

    }

    @Override
    public void onLoadMoreComplete() {

    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {

    }
}
