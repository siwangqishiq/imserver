package com.xinlan.http.action;

import com.xinlan.exception.CommonException;
import com.xinlan.http.Resp;
import com.xinlan.http.StatusCode;
import com.xinlan.model.Account;
import com.xinlan.model.User;
import com.xinlan.security.SecurityHelper;
import com.xinlan.service.ServerContext;
import com.xinlan.service.UserService;
import com.xinlan.util.CheckUtil;
import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Map;

/**
 * 登陆
 */
@HttpComponent
public class LoginAction extends BaseAction {
    private UserService mUserService;

    public LoginAction(){
        mUserService = ServerContext.getInstance().getUserService();
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/login";
    }

    @Override
    public boolean needVertified() {
        return false;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        // response.content().writeBytes("Hello World1".getBytes());
        Map<String, String> params = parseParams(request);
        String account = params.get("account");
        String pwd = params.get("pwd");

        System.out.println("account = " + account + "   pwd = " + pwd);
        if (StringUtil.isNullOrEmpty(account)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名不能为空"));
            return;
        }

        if (StringUtil.isNullOrEmpty(pwd)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名密码不能为空"));
            return;
        }

        doLogin(account , pwd , response);
    }

    public boolean doLogin(final String account , final String pwd , DefaultFullHttpResponse response){
        User user = mUserService.queryUserByAccount(account);
        if(user == null || !StringUtils.equals(user.getPwd() , pwd)){
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名或密码错误"));
            return false;
        }

        //签发Token
        final String token = SecurityHelper.createToken(user.getUid() , pwd);
        if(StringUtil.isNullOrEmpty(token)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名或密码错误"));
            return false;
        }
        Account accountBean = Account.genFromUser(token , user);
        ServerContext.getInstance().putAccount(accountBean);

        writeHttpResponse(response, Resp.createSuccess(accountBean));
        return true;
    }
}//end class
