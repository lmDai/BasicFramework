package cn.yznu.basicframework.utils.Glide;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * 作者：uiho_mac
 * 时间：2018/7/5
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class RSBlur {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blur(Context context, Bitmap blurredBitmap, int radius)
            throws RSRuntimeException {
        try {
            RenderScript rs = RenderScript.create(context);
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap,
                    Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs,
                    Element.U8_4(rs));

            blur.setInput(input);
            blur.setRadius(radius);
            blur.forEach(output);
            output.copyTo(blurredBitmap);
            rs.destroy();
        } catch (RSRuntimeException e) {
            blurredBitmap = FastBlur.blur(blurredBitmap, radius, true);
        }
        return blurredBitmap;
    }
}