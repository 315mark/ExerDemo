package zkch.com.exerdemo.cniaow.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.cniaow.adapter.AppInfoAdapter;
import zkch.com.exerdemo.cniaow.bean.Subject;
import zkch.com.exerdemo.cniaow.bean.SubjectDetail;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.widget.DividerItemDecoration;
import zlc.season.rxdownload2.RxDownload;

@SuppressLint("ValidFragment")
public class SubjectDetailFragment extends BaseSubjectFragment {

    @BindView(R.id.imgView)
    ImageView imgView;
    @BindView(R.id.txt_desc)
    TextView txtDesc;

    @BindView(R.id.recyleView)
    RecyclerView recyleView;

    @Inject
    RxDownload rxDownload;

    private Subject subject;
    private AppInfoAdapter mAdapter;


    @Override
    protected int setLayout() {
        return R.layout.fragment_subject_detail;
    }

    public SubjectDetailFragment(Subject subject) {
        this.subject = subject;
    }

    @Override
    protected void init() {
        inintRecyclerView();
        mPresenter.getSubjectDetail(subject.getRelatedId());
    }

    @Override
    public void showSubjectDetail(SubjectDetail detail) {
        Glide.with(this).load(Constant.BASE_IMG_URL + detail.getPhoneBigIcon()).into(imgView);

        txtDesc.setText(detail.getDescription());
        mAdapter.addData(detail.getListApp());
    }

    private void inintRecyclerView() {

        mAdapter = new AppInfoAdapter.Builder().rxDownload(rxDownload)
                .showBrief(false).showCategoryName(true).build();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyleView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyleView.addItemDecoration(decoration);
        recyleView.setAdapter(mAdapter);
    }


}
