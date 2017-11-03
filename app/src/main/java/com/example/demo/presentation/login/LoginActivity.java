package com.example.demo.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.demo.GitDataInjection;
import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.data.net.bean.UserInfo;
import com.example.demo.presentation.SystemUtil;

/**
 * @author zhangyb
 * @description
 * @date 2017/11/2
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getCanonicalName();
    private LoginContract.Presenter presenter;
    private UserInfo userInfo;
    AutoCompleteTextView usernameView;
    EditText passwordView;
    Button signInButton;
    LinearLayout loginFormPane;

    public AlertDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        initViews();
        new LoginPresenter(this,this, GitDataInjection.provideGgetUserInfo());
        this.presenter.start();
    }


    @Override
    public void initViews() {
        usernameView = (AutoCompleteTextView) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        loginFormPane = (LinearLayout) findViewById(R.id.login_form_pane);
        initSignView();
    }

    private void initSignView() {
        signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(view -> doLogin());
    }

    @Override
    public void showLoginDialog() {

        if (loadDialog == null) {
            loadDialog = SystemUtil.getAndShowLoadingDialog(this, getString(R.string.login_load));
        }
        loadDialog.show();
    }

    @Override
    public void dismissLoginDialog() {
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
    }

    @Override
    public void doLogin() {
        String userName = usernameView.getText().toString().trim();
        String passWord = passwordView.getText().toString().trim();
        if (checkUserNamePasswordVaildate(userName,passWord)){
            showLoginDialog();
            presenter.fetchUserInfoByUserName(userName, passWord);
        }
    }

    @Override
    public void onLoginSuccess(UserInfo userInfo) {
        this.userInfo = userInfo;
        jumpToMain();
    }

    @Override
    public void onLoginFailed(Throwable error) {

    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("userInfo", userInfo);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private boolean checkUserNamePasswordVaildate(String userNameOnUI, String passwordOnUI) {
        if (TextUtils.isEmpty(userNameOnUI) || TextUtils.isEmpty(passwordOnUI)) {
            Log.w(TAG, "username or password wrong...");
            return false;
        }
        return true;
    }
}