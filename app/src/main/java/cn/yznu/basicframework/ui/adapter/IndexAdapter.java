package cn.yznu.basicframework.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.model.bean.TestBean;

/**
 * 作者：uiho_mac
 * 时间：2018/6/20
 * 描述：首页数据适配器
 * 版本：1.0
 * 修订历史：
 */
public class IndexAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder> {
    public IndexAdapter(@Nullable List<TestBean> data) {
        super(R.layout.adapter_index, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {

    }
}
