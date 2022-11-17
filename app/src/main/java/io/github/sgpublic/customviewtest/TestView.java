package io.github.sgpublic.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.github.sgpublic.customviewtest.databinding.ViewTestBinding;

/**
 * @author Madray Haven
 * @date 2022/11/17 17:00
 */
public class TestView extends BaseCustomView<ViewTestBinding> {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @NonNull
    private final Map<Boolean, Drawable> icon = new HashMap<>();
    
    @Override
    protected void onInit(Context context, @Nullable TypedArray attrs) {
        assert attrs != null;
        if (icon == null) throw new NullPointerException("TestView#icon is null!");
        icon.put(true, attrs.getDrawable(R.styleable.TestView_icon));
        icon.put(false, attrs.getDrawable(R.styleable.TestView_iconUnselect));
    }

    @Override
    public void setSelected(boolean selected) {
        ViewBinding().oibniIcon.setImageDrawable(icon.containsKey(selected) ? icon.get(selected) : icon.get(true));
    }

    @Nullable
    @Override
    protected int[] getStyleableAttrs() {
        return R.styleable.TestView;
    }

    @Override
    protected ViewTestBinding onCreateViewBinding(LayoutInflater inflater) {
        return ViewTestBinding.inflate(inflater, this, true);
    }
}
