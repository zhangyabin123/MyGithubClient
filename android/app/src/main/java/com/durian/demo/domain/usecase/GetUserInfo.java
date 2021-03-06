package com.durian.demo.domain.usecase;


import com.durian.demo.base.RxUseCase;
import com.durian.demo.data.net.bean.UserInfo;
import com.durian.demo.domain.GitDataSource;

import io.reactivex.Observable;

/**
 * @author zhangyb
 * @description useCase
 * @date 2017/11/2
 */

public class GetUserInfo extends RxUseCase<String, GetUserInfo.Response> {

    private GitDataSource gitDataSource;

    public GetUserInfo(GitDataSource gitDataSource) {
        this.gitDataSource = gitDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(String requestValues) {
        return gitDataSource.getPostList(requestValues);
    }

    public static class Response implements RxUseCase.ResponseValue {
        private UserInfo userInfo;

        public Response(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }
    }
}
