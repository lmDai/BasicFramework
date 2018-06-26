package cn.yznu.basicframework.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：首页
 * 版本：1.0
 * 修订历史：
 */
public class IndexFragment extends BaseFragment {
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    @BindView(R.id.banner)
    MZBannerView mzBannerView;

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
        setTitle(false, getResources().getString(R.string.str_index));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(R.drawable.welcome_bg);
        }
        mzBannerView.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }


    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mzBannerView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mzBannerView.pause();//暂停轮播
    }
}
