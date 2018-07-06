package cn.yznu.basicframework.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;
import cn.yznu.basicframework.model.bean.ModelHomeEntrance;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.activity.DetailsActivity;
import cn.yznu.basicframework.ui.adapter.CagegoryViewPagerAdapter;
import cn.yznu.basicframework.ui.adapter.EntranceAdapter;
import cn.yznu.basicframework.ui.adapter.IndexAdapter;
import cn.yznu.basicframework.ui.contract.HomeContract;
import cn.yznu.basicframework.ui.model.HomeModel;
import cn.yznu.basicframework.ui.presenter.HomePresenter;
import cn.yznu.basicframework.utils.Glide.ShowImageUtils;
import cn.yznu.basicframework.utils.IntentUtils;
import cn.yznu.common.indicator.CircleIndicatorView;
import cn.yznu.common.toasthelper.TastyToast;
import cn.yznu.common.toasthelper.ToastHelper;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：首页
 * 版本：1.0
 * 修订历史：
 */
public class IndexFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    MZBannerView mzBannerView;
    public static final int HOME_ENTRANCE_PAGE_SIZE = 10;//首页菜单单页显示数量
    ViewPager entranceViewPager;
    CircleIndicatorView entranceIndicatorView;
    LinearLayout homeEntranceLayout;
    private List<ModelHomeEntrance> homeEntrances;
    private IndexAdapter indexAdapter;

    public static IndexFragment newInstance() {
        Bundle bundle = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        showBack = false;
        setTopTitle(getResources().getString(R.string.str_index));
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initAdapter();
        addHeadView();
        initRefreshLayout();
        refresh();

    }

    private void initAdapter() {
        indexAdapter = new IndexAdapter(null);
        indexAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);
        indexAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(indexAdapter);
        indexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                IntentUtils.get().goActivity(mContext, DetailsActivity.class);
            }
        });
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.layout_index_top, (ViewGroup) recyclerView.getParent(), false);
        mzBannerView = headView.findViewById(R.id.banner);
        homeEntranceLayout = headView.findViewById(R.id.home_entrance);
        entranceViewPager = headView.findViewById(R.id.main_home_entrance_vp);
        entranceIndicatorView = headView.findViewById(R.id.main_home_entrance_indicator);
        List<TestBean> testBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestBean testBean = new TestBean();
            testBean.setUrl("http://ww1.sinaimg.cn/large/0065oQSqly1frv032vod8j30k80q6gsz.jpg");
            testBeans.add(testBean);
        }
        setBanner(testBeans);
        initIndicatorData();
        init();
        indexAdapter.addHeaderView(headView);
    }

    //初始化首页indicator数据
    private void initIndicatorData() {
        homeEntrances = new ArrayList<>();
        homeEntrances.add(new ModelHomeEntrance("美食", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("电影", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("酒店住宿", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("生活服务", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("KTV", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("旅游", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("学习培训", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("汽车服务", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("摄影写真", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("休闲娱乐", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("丽人", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("运动健身", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("大保健", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("团购", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("景点", R.drawable.pic_zjz));
        homeEntrances.add(new ModelHomeEntrance("全部分类", R.drawable.pic_zjz));
    }

    private void init() {
        int pageSize = HOME_ENTRANCE_PAGE_SIZE;
        //一共的页数等于 总数/每页数量，并取整。
        int pageCount = (int) Math.ceil(homeEntrances.size() * 1.0 / pageSize);
        List<View> viewList = new ArrayList<View>();
        for (int index = 0; index < pageCount; index++) {
            //每个页面都是inflate出一个新实例
            RecyclerView recyclerView1 = new RecyclerView(mContext);
            recyclerView1.setLayoutManager(new GridLayoutManager(mContext, 5));
            int last = (index + 1) * 10 > homeEntrances.size() ? homeEntrances.size() : (index + 1) * 10;
            EntranceAdapter entranceAdapter = new EntranceAdapter(homeEntrances.subList(index * 10, last));
            recyclerView1.setAdapter(entranceAdapter);
            viewList.add(recyclerView1);
        }
        CagegoryViewPagerAdapter adapter = new CagegoryViewPagerAdapter(viewList);
        entranceViewPager.setAdapter(adapter);
        entranceIndicatorView.setUpWithViewPager(entranceViewPager);
    }

    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        indexAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresenter.getGanks("福利", true);
    }

    private void loadMore() {
        mPresenter.getGanks("福利", false);
    }

    @Override
    public void setData(boolean isRefresh, final List data) {
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            swipeLayout.setRefreshing(false);
            indexAdapter.setNewData(data);
        } else {
            if (size > 0) {
                indexAdapter.addData(data);
            }
        }
        if (size < 10) {
            //第一页如果不够一页就不显示没有更多数据布局
            indexAdapter.loadMoreEnd(false);
        }
        indexAdapter.loadMoreComplete();

    }

    public void setBanner(List<TestBean> userBaseRespose) {

        // 设置数据
        mzBannerView.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int i) {
                ToastHelper.showToast(mContext, i + "", TastyToast.INFO);
            }
        });
        mzBannerView.setPages(userBaseRespose, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mzBannerView.start();
    }

    @Override
    public void showErrorTip(String msg) {

    }

    public static class BannerViewHolder implements MZViewHolder<TestBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, TestBean data) {
            // 数据绑定
            ShowImageUtils.showImageView(context, R.drawable.pic_notfound, data.getUrl(), mImageView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mzBannerView.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mzBannerView.start();//开始轮播
    }
}
