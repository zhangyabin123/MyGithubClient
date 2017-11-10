package com.durian.demo.presentation.repositories;

import com.durian.demo.BasePresent;
import com.durian.demo.BaseView;

/**
 * @author zhangyb
 * @description
 * @date 2017/11/9
 */

public class RepositoriesContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresent {
    }
}
