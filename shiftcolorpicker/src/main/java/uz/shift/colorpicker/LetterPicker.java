package uz.shift.colorpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import uz.shift.colorpicker.util.helper;

/**
 * Created by zJJ on 4/24/2016.
 */
public class LetterPicker extends LineColorPicker {
    private Typeface mTypeface;
    /**
     * The max length for this tag view
     */
    private int mTagMaxLength = 5;
    private float fontH, fontW;
    private String mAbstractText, mOriginText;
    /**
     * Text size
     */
    private float mTextSize;
    private Paint mPaint;
    private RectF mRectF;
    private int mTextSelectedColor;
    private int mTextNormalColor;
    /**
     * The distance between baseline and descent
     */
    private float bdDistance;

    String[] str = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public LetterPicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        final TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LetterPicker, 0, 0);

        try {
            String name = a.getString(R.styleable.LetterPicker_fontname);
            mTypeface = helper.getTypface(name, context);

            final int stringArrayResId = a.getResourceId(R.styleable.LetterPicker_string_array, -1);
            if (stringArrayResId > 0) {
                final String[] item_array = context.getResources().getStringArray(stringArrayResId);
                str = item_array;
            }

            mTagMaxLength = a.getInt(R.styleable.LetterPicker_item_max_text_length, mTagMaxLength);
            mTextNormalColor = a.getColor(R.styleable.LetterPicker_normal_text_color,
                    Color.BLACK);
            mTextSelectedColor = a.getColor(R.styleable.LetterPicker_selected_text_color,
                    Color.BLACK);

            mTextSize = a.getDimension(R.styleable.LetterPicker_text_size, 80f);

        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();
        //mOriginText = text == null ? "" : text;
        //  flag_on = false;
    }

    public void setBdDistance(float bdDistance) {
        this.bdDistance = bdDistance;
    }

    private void onDealText(String mOriginText, Paint setPaint) {
        if (!TextUtils.isEmpty(mOriginText)) {
            mAbstractText = mOriginText.length() <= mTagMaxLength ? mOriginText : mOriginText.substring(0, mTagMaxLength - 3) + "...";
        } else {
            mAbstractText = "";
        }
        final Paint.FontMetrics fontMetrics = setPaint.getFontMetrics();
        fontH = fontMetrics.descent - fontMetrics.ascent;
        /*     if (mTextDirection == View.TEXT_DIRECTION_RTL) {
            fontW = 0;
            for (char c : mAbstractText.toCharArray()) {
                String sc = String.valueOf(c);
                fontW += mPaint.measureText(sc);
            }
        } else {
            fontW = mPaint.measureText(mAbstractText);
        }*/
        fontW = setPaint.measureText(mAbstractText);
    }


    @Override
    protected void additionalDrawCallInCell(Rect mBounce, Paint mPaint, Canvas canvas, int pos, boolean select) {
        super.additionalDrawCallInCell(mBounce, mPaint, canvas, pos, select);
        int wPos = mBounce.centerX();
        int hPos = mBounce.centerY();
        int mTextPos = pos % (str.length);
        mPaint.setStyle(Paint.Style.FILL);
        float factor = select ? 1.2f : 1f;
        mPaint.setTextSize(mTextSize * factor);
        mPaint.setTypeface(mTypeface);
        //mPaint.setColor(ContextCompat.getColor(getContext(), mTextSizeColor));
        mPaint.setColor(mTextNormalColor);
        mPaint.setAntiAlias(true);
        onDealText(str[mTextPos], mPaint);
        canvas.drawText(mAbstractText, wPos - fontW / 2, hPos + fontH / 2 - bdDistance, mPaint);
    }

    @Override
    protected void onChangePosition(int pos) {
        if (onchangeselectposition != null) {
            int mTextPos = pos % (str.length);
            onchangeselectposition.onSelectedChanged(pos, str[mTextPos]);
        }
    }


}
