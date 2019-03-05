package zkch.com.exerdemo.cniaow.mvp.contract;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.bean.SubjectDetail;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public class SubjectContract {
    public interface SubjectView extends BaseView {
        void showSubjects(PageBean<Subject> data);

        void onLoadMoreComplete();  //加载更多完整的

        void showSubjectDetail(SubjectDetail detail);
    }

    public interface ISubjectModel {
        Observable<BaseBean<PageBean<Subject>>> getSubjects(int page);

        Observable<BaseBean<SubjectDetail>> getSubjectDetail(int id);
    }

}
