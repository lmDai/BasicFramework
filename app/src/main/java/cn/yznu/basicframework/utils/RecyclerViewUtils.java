package cn.yznu.basicframework.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.ui.widget.DividerItemDecoration;

/**
 * 作者：uiho_mac
 * 时间：2018/6/20
 * 描述：recyclerview工具类
 * 版本：1.0
 * 修订历史：
 */
public class RecyclerViewUtils {
    private static RecyclerViewUtils ourInstance = new RecyclerViewUtils();

    public static RecyclerViewUtils getInstance() {
        return ourInstance;
    }

    //私有构造方法
    private RecyclerViewUtils() {

    }

    public RecyclerView buildDefault(Context context, RecyclerView recyclerView) {
        return buildDefault(context, recyclerView, 0);
    }

    private RecyclerView buildDefault(Context context, RecyclerView recyclerView, int dividerRes) {
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST);
        if (dividerRes != 0) {
            dividerItemDecoration.setDivider(context.getResources().getDrawable(dividerRes));
        } else {
            dividerItemDecoration.setDivider(context.getResources().getDrawable(R.drawable.order_divider));
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new MyLinearLayoutManagerWrapper(context));
        return recyclerView;
    }

    public class MyLinearLayoutManagerWrapper extends LinearLayoutManager {

        private MyLinearLayoutManagerWrapper(Context context) {
            super(context);
        }

        public MyLinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public MyLinearLayoutManagerWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }
}
