package cn.yznu.common.loadviewhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * 作者：uiho_mac
 * 时间：2018/7/3
 * 描述：用于切换布局,用一个新的布局替换掉原先的布局
 * 版本：1.0
 * 修订历史：
 */
class VaryViewHelper implements IVaryViewHelper {
    private View currentView;
    private ViewGroup parentView;
    private ViewGroup.LayoutParams params;
    private boolean isLoad;
    private ViewPropertyAnimatorCompat animatorCompat;
    private ViewPropertyAnimatorCompat animatorCompat2;

    VaryViewHelper(@NonNull View view) {
        super();
        this.currentView = view;
        currentView.setTag(currentView.getClass().getName());
        init();
    }

    /***
     *初始化
     * **/
    private void init() {
        params = currentView.getLayoutParams();
        if (currentView.getParent() != null) {
            parentView = (ViewGroup) currentView.getParent();
        } else {
            parentView = currentView.getRootView().findViewById(android.R.id.content);
        }
    }

    @Override
    public View getCurrentLayout() {
        return currentView;
    }

    @Override
    public void restoreView() {
        showLayout(currentView);
    }

    @Override
    public synchronized void showLayout(@NonNull View view) {
        if (parentView == null) {
            return;
        }
        // 如果已经是那个view，那就不需要再进行替换操作了
        View view1 = parentView.getChildAt(0);
        if (view1 != null && !view1.toString().equals(view.toString())) {
            final ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            //第一次进入也不需要动画
            if (!isLoad) {
                isLoad = true;
                parentView.removeAllViews();
                parentView.addView(view, params);
            } else {
                if (animatorCompat != null) {
                    parentView.removeAllViews();
                    parentView.addView(view, params);
                }
                release();
                view.setAlpha(0);
                animatorCompat = ViewCompat.animate(view1)
                        .alpha(0)
                        .setDuration(350)
                        .setListener(new ViewPropertyAnimator(this,view));
                animatorCompat.start();
                animatorCompat2 = ViewCompat
                        .animate(view)
                        .alpha(1)
                        .setListener(new ViewPropertyAnimatorListener() {
                            @Override
                            public void onAnimationStart(View view) {

                            }

                            @Override
                            public void onAnimationEnd(View view) {

                            }

                            @Override
                            public void onAnimationCancel(View view) {
                                if (view!=null) {
                                    view.setAlpha(1);
                                }

                            }
                        })
                        .setDuration(800)
                        .setStartDelay(350);
                animatorCompat2.start();
            }

        }
    }

    @Override
    public void showLayout(int layoutId) {
        showLayout(inflate(layoutId));
    }

    @Override
    public View inflate(int layoutId) {
        return LayoutInflater.from(getContext()).inflate(layoutId, null);
    }

    @Override
    public Context getContext() {
        return currentView.getContext();
    }

    @Override
    public View getView() {
        return currentView;
    }
    @Override
    public void release() {
        if (animatorCompat != null) {
            animatorCompat.cancel();
            animatorCompat.setListener(null);
        }
        if (animatorCompat2 != null) {
            animatorCompat2.cancel();
            animatorCompat.setListener(null);
        }
    }

    class ViewPropertyAnimator implements ViewPropertyAnimatorListener {
        private WeakReference<VaryViewHelper> ssl;
        private View v;

        ViewPropertyAnimator(VaryViewHelper varyViewHelper, View view) {
            ssl = new WeakReference<>(varyViewHelper);
            v = view;

        }

        @Override
        public void onAnimationStart(View view) {

        }

        @Override
        public void onAnimationEnd(View view) {
            if (ssl.get() == null) {
                return;
            }
            parentView.removeAllViews();
            parentView.addView(v, params);
        }

        @Override
        public void onAnimationCancel(View view) {
        }
    }

}

