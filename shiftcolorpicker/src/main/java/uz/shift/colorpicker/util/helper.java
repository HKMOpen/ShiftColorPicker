package uz.shift.colorpicker.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;

import uz.shift.colorpicker.LineColorPicker;

/**
 * Created by zJJ on 4/24/2016.
 */
public class helper {
    public static Typeface getTypface(@Nullable String font_name, Context mContext) {
        if (font_name == null) return Typeface.DEFAULT;
        return Typeface.createFromAsset(mContext.getAssets(), "fonts/" + font_name);
    }

    /**
     * get a list of colors
     *
     * @param from  from
     * @param to    to
     * @param count voun
     * @return list
     */
    public static int[] generateColors(int from, int to, int count) {
        int[] palette = new int[count];

        float[] hsvFrom = new float[3];
        float[] hsvTo = new float[3];

        Color.colorToHSV(from, hsvFrom);
        Color.colorToHSV(to, hsvTo);

        float step = (hsvTo[0] - hsvFrom[0]) / count;

        for (int i = 0; i < count; i++) {
            palette[i] = Color.HSVToColor(hsvFrom);

            hsvFrom[0] += step;
        }

        return palette;
    }

    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * Return color at x,y coordinate of view.
     *
     * @param x                    x
     * @param y                    y
     * @param mOrientation         either vertical or horizontal
     * @param colors               the list of colors
     * @param cellSize             the call size
     * @return the list of colors
     */
    public static int getColorAtXY(float x, float y, int mOrientation, int[] colors, int cellSize) {
        // FIXME: colors.length == 0 -> devision by ZERO.s
        if (mOrientation == LineColorPicker.HORIZONTAL) {
            int left = 0;
            int right = 0;
            for (int i = 0; i < colors.length; i++) {
                left = right;
                right += cellSize;
                if (left <= x && right >= x) {
                    return i;
                }
            }
        } else {
            int top = 0;
            int bottom = 0;
            for (int i = 0; i < colors.length; i++) {
                top = bottom;
                bottom += cellSize;
                if (y >= top && y <= bottom) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Returns darker version of specified <code>color</code>.
     *
     * @param color  color selected
     * @param factor factor
     * @return the color id
     */
    public static int darker(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }

    public static int darker(int color, float factor, float alpha) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return Color.argb((int) ((float) alpha * (float) 255),
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }
  /*  private static void blurRenderScript(Bitmap bitmap, float radius) {
        Allocation inAllocation = Allocation.createFromBitmap(renderScript, bitmap,
                Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation outAllocation = Allocation.createTyped(renderScript, inAllocation.getType());

        blurShader.setRadius(radius);
        blurShader.setInput(inAllocation);
        blurShader.forEach(outAllocation);

        outAllocation.copyTo(bitmap);
    }

    public static Shadow generateShadow(View view, float elevation) {
        if (!software && renderScript == null) {
            try {
                renderScript = RenderScript.create(view.getContext());
                blurShader = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
            } catch (RSRuntimeException ignore) {
                software = true;
            }
        }

        ShadowView shadowView = (ShadowView) view;
        CornerView cornerView = (CornerView) view;
        boolean isRect = shadowView.getShadowShape() == ShadowShape.RECT ||
                shadowView.getShadowShape() == ShadowShape.ROUND_RECT && cornerView.getCornerRadius() < view.getContext().getResources().getDimension(R.dimen.carbon_1dip) * 2.5;

        int e = (int) Math.ceil(elevation);
        Bitmap bitmap;
        if (isRect) {
            bitmap = Bitmap.createBitmap(e * 4 + 1, e * 4 + 1, Bitmap.Config.ARGB_8888);

            Canvas shadowCanvas = new Canvas(bitmap);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xff000000);

            shadowCanvas.drawRect(e, e, e * 3 + 1, e * 3 + 1, paint);

            blur(bitmap, elevation);

            return new NinePatchShadow(bitmap, elevation);
        } else {
            bitmap = Bitmap.createBitmap((int) (view.getWidth() / SHADOW_SCALE + e * 2), (int) (view.getHeight() / SHADOW_SCALE + e * 2), Bitmap.Config.ARGB_8888);

            Canvas shadowCanvas = new Canvas(bitmap);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xff000000);

            if (shadowView.getShadowShape() == ShadowShape.ROUND_RECT) {
                roundRect.set(e, e, (int) (view.getWidth() / SHADOW_SCALE - e), (int) (view.getHeight() / SHADOW_SCALE - e));
                shadowCanvas.drawRoundRect(roundRect, e, e, paint);
            } else {
                int r = (int) (view.getWidth() / 2 / SHADOW_SCALE);
                shadowCanvas.drawCircle(r + e, r + e, r, paint);
            }

            blur(bitmap, elevation);

            return new Shadow(bitmap, elevation);
        }
    }*/
}
