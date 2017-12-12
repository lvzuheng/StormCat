package com.lzh.storm.custom.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.lzh.storm.R;


/**
 * Created by lzh on 2017/12/11.
 */

@SuppressLint("AppCompatCustomView")
public class SwapperButton extends Button {

    private String swapper;
    private boolean firstDisplay;

    public SwapperButton(Context context) {
        super(context);

    }

    public SwapperButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwapperButton);
        swapper = a.getString(R.styleable.SwapperButton_swapper);
        firstDisplay = a.getBoolean(R.styleable.SwapperButton_firstDisplay, false);
        a.recycle();
    }

    public SwapperButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwapperButton);
        swapper = a.getString(R.styleable.SwapperButton_swapper);
        firstDisplay = a.getBoolean(R.styleable.SwapperButton_firstDisplay, false);
        a.recycle();

    }


    public String getSwapper() {
        return swapper;
    }

    public boolean isFirstDisplay() {
        return firstDisplay;
    }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener l) {
        final OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSwapperListener!=null){
                    onSwapperListener.swapperListener(v);
                }
                 l.onClick(v);
            }
        };
        super.setOnClickListener(onClickListener);

    }

    private OnSwapperListener onSwapperListener;

    public void setOnSwapperListener(OnSwapperListener onSwapperListener){
        this.onSwapperListener = onSwapperListener;
    }

    public interface OnSwapperListener{
        void swapperListener(View v);
    }

    public boolean callOnSwapper(){
        if (onSwapperListener != null ) {
            onSwapperListener.swapperListener(this);
            return true;
        }
        return false;
    }
}
