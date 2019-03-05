package zkch.com.exerdemo.cniaow.model;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.bean.SubjectDetail;
import zkch.com.exerdemo.cniaow.mvp.contract.SubjectContract;

public class SubjecctModel implements SubjectContract.ISubjectModel {
    private ApiService mApiService;

    public SubjecctModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<PageBean<Subject>>> getSubjects(int page) {
        return mApiService.getSubjects(page);
    }

    @Override
    public Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id) {
        return mApiService.getSubjectDetail(id);
    }
}
