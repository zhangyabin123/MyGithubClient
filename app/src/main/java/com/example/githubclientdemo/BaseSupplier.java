package com.example.githubclientdemo;

import com.google.android.agera.MutableRepository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;

/**
 * Created by zhangyb on 2016/12/20.
 */

public abstract class BaseSupplier<T> implements Supplier<Result<T>>{

    protected BasePresenter mPresenter;

    public  BaseSupplier(){

    }
    public BaseSupplier(BasePresenter presenter, MutableRepository supplier) {
        this.mPresenter = presenter;
    }

    public abstract Result<T> loadData();

}