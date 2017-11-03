package com.example.demo.domain.usecase;


import com.example.demo.base.RxUseCase;
import com.example.demo.data.net.bean.UserInfo;
import com.example.demo.domain.GitDataSource;

import io.reactivex.Observable;

/**
 * @author zhangyb
 * @description
 * @date 2017/11/2
 */

public class GetUserInfo extends RxUseCase<GetUserInfo.Request, GetUserInfo.Response> {

    private GitDataSource gitDataSource;

    public GetUserInfo(GitDataSource gitDataSource) {
        this.gitDataSource = gitDataSource;
    }

    @Override
    protected Observable<Response> buildUseCaseObservable(GetUserInfo.Request requestValues) {
        return gitDataSource.getPostList(requestValues);
    }

    public static class Request implements RxUseCase.RequestValues {
        String credentials;

        public Request(String credentials) {
            this.credentials = credentials;
        }
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
