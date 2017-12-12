package com.lzh.storm.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lzh on 2017/12/8.
 */

public abstract class UniteFragment extends Fragment{


    protected int rId;
    public UniteFragment(){
        initialize();
    }

    protected abstract void initialize();
    public void setRid(int rId) {
        this.rId = rId;
    }
    public int getRid() {
        return this.rId;
    }

}
