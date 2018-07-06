package cn.yznu.basicframework.model.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * 作者：uiho_mac
 * 时间：2018/7/5
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class MySection extends SectionEntity<TestBean> {
    private boolean isMore;

    public MySection(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public MySection(TestBean testBean) {
        super(testBean);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }
}
