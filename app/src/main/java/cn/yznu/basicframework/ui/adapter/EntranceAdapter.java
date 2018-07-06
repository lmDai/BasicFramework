package cn.yznu.basicframework.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.model.bean.ModelHomeEntrance;

/**
 * 作者：uiho_mac
 * 时间：2018/6/20
 * 描述：首页数据适配器
 * 版本：1.0
 * 修订历史：
 */
public class EntranceAdapter extends BaseQuickAdapter<ModelHomeEntrance, BaseViewHolder> {
    public EntranceAdapter(@Nullable List<ModelHomeEntrance> data) {
        super(R.layout.item_home_entrance, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelHomeEntrance item) {
        helper.setText(R.id.entrance_name, item.getName());
        ImageView imgHead = helper.getView(R.id.entrance_image);
        imgHead.setImageResource(item.getImage());
    }
}
