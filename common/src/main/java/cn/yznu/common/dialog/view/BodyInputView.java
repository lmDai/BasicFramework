package cn.yznu.common.dialog.view;

import android.content.Context;

import cn.yznu.common.dialog.callback.ProviderContentInput;
import cn.yznu.common.dialog.res.drawable.BgBtn;


/**
 * Created by hupei on 2017/3/21
 */
class BodyInputView extends AutoLinearLayout {
    private SuperEditText mEditText;
    private Controller.Params mParams;

    public BodyInputView(Context context, Controller.Params params) {
        super(context);
        mParams = params;
        initData();
    }

    private void initData() {
        if (mParams.mProviderHeader != null && mParams.mFooterNegative == null
                && mParams.mFooterPositive == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(0, 0, radius, radius, mParams.mBackgroundColor));
        } else if (mParams.mProviderHeader == null && (mParams.mFooterNegative != null
                || mParams.mFooterPositive != null)) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, 0, 0, mParams.mBackgroundColor));
        } else if (mParams.mFooterNegative == null && mParams.mFooterPositive == null
                && mParams.mProviderHeader == null) {
            int radius = mParams.mRadius;
            setBackgroundDrawable(new BgBtn(radius, radius, radius, radius, mParams
                    .mBackgroundColor));
        } else {
            setBackgroundColor(mParams.mBackgroundColor);
        }
        ProviderContentInput providerContent = (ProviderContentInput) mParams.mProviderContent;
        if (providerContent == null) return;
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        int[] margins = providerContent.getMargins();
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        mEditText = new SuperEditText(getContext());
        mEditText.setHint(providerContent.getItems());
        mEditText.setHintTextColor(providerContent.getHintTextColor());
        mEditText.setTextSize(providerContent.getTextSize());
        mEditText.setTextColor(providerContent.getTextColor());
        mEditText.setHeight(providerContent.getInputHeight());
        addView(mEditText, params);
    }

    public String getInputText() {
        return mEditText.getText().toString();
    }
}
