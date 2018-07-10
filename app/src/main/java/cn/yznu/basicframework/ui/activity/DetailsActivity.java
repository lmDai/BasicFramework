package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：详情
 * 版本：1.0
 * 修订历史：
 */
public class DetailsActivity extends BaseActivity {


    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initView() {
        setTopTitle("详情");
    }
}
