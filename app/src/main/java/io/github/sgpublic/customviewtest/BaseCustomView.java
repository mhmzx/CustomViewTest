package io.github.sgpublic.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.viewbinding.ViewBinding;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Madray Haven
 * @date 2022/11/11 16:44
 */
public abstract class BaseCustomView<VB extends ViewBinding> extends ViewGroup {
    public BaseCustomView(Context context) {
        super(context);
        init(context, null);
    }

    public BaseCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        ViewBinding = onCreateViewBinding(LayoutInflater.from(context));
        final AtomicReference<TypedArray> a = new AtomicReference<>();
        Kt.let(getStyleableAttrs(), v -> a.set(getContext().obtainStyledAttributes(attrs, v)));
        try {
            onInit(context, a.get());
        } finally {
            Kt.let(a.get(), TypedArray::recycle);
        }
    }

    private VB ViewBinding;

    /**
     * <pre>{@code
     * <VB extends ViewBinding>.inflate(inflater, this, true)
     * }</pre>
     * @param inflater LayoutInflater
     * @return ViewBinding
     */
    protected abstract VB onCreateViewBinding(LayoutInflater inflater);

    protected abstract void onInit(Context context, @Nullable TypedArray attrs);

    protected final VB ViewBinding() {
        return ViewBinding;
    }

    @Nullable
    @StyleableRes
    protected int[] getStyleableAttrs() {
        return null;
    }

    @CallSuper
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Kt.let(ViewBinding().getRoot(), root -> {
            measureChild(root, widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(root.getMeasuredWidth(), root.getMeasuredHeight());
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Kt.let(ViewBinding().getRoot(), root -> root.layout(
                0, 0, root.getMeasuredWidth(), root.getMeasuredHeight()
        ));
    }
}
