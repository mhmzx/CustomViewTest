package io.github.sgpublic.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.github.sgpublic.customviewtest.databinding.ViewTestBinding;
import kotlin.Lazy;
import kotlin.LazyKt;

/**
 * @author Madray Haven
 * @date 2022/11/17 17:00
 */
public class TestView extends ViewGroup {
    public TestView(Context context) {
        super(context);
        onInit(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onInit(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onInit(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        onInit(context, attrs);
    }

    private ViewTestBinding ViewBinding;

    @NonNull
    private final Map<Boolean, Drawable> icon = new HashMap<>();

    private void onInit(Context context, @Nullable AttributeSet attrs) {
        ViewBinding = ViewTestBinding.inflate(LayoutInflater.from(context), this, true);
        if (attrs == null) return;
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TestView);
        if (icon == null) throw new NullPointerException("TestView#icon is null!");
        icon.put(true, array.getDrawable(R.styleable.TestView_icon));
        icon.put(false, array.getDrawable(R.styleable.TestView_iconUnselect));
        array.recycle();
    }

    @Override
    public void setSelected(boolean selected) {
        ViewBinding.oibniIcon.setImageDrawable(icon.containsKey(selected) ? icon.get(selected) : icon.get(true));
    }

    @CallSuper
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View root = ViewBinding.getRoot();
        measureChild(root, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(root.getMeasuredWidth(), root.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View root = ViewBinding.getRoot();
        root.layout(0, 0, root.getMeasuredWidth(), root.getMeasuredHeight());
    }
}
