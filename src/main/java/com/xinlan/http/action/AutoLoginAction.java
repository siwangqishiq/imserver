package com.xinlan.http.action;

import com.xinlan.http.Resp;
import com.xinlan.http.StatusCode;
import com.xinlan.model.Account;
import com.xinlan.model.User;
import com.xinlan.security.SecurityHelper;
import com.xinlan.service.ServerContext;
import com.xinlan.service.UserService;
import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Map;

/**
 * 自动登录接口
 *  用于更新token有效期
 *
 */
@HttpComponent
public class AutoLoginAction extends BaseAction {
    private UserService mUserService;

    public AutoLoginAction(){
        mUserService = ServerContext.getInstance().getUserService();
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/autologin";
    }

    @Override
    public boolean needVertified() {
        return true;
    }

    @Override
    public void service(FullHttpRequest request, DefaultFullHttpResponse response) {
        autoLogin(request , response);
    }

    public boolean autoLogin(FullHttpRequest request, DefaultFullHttpResponse response){
        Account account = getAccount();
        if(account == null){
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "状态错误"));
            return false;
        }

        User user = mUserService.queryUser(Long.parseLong(account.getUid()));
        if(user == null){
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "状态错误"));
            return false;
        }

        //签发Token
        final String token = SecurityHelper.createToken(user.getUid() , user.getPwd());
        if(StringUtil.isNullOrEmpty(token)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "签发token失败"));
            return false;
        }
        Account accountBean = Account.genFromUser(token , user);
        ServerContext.getInstance().putAccount(accountBean);

        writeHttpResponse(response, Resp.createSuccess(accountBean.getToken()));
        return true;
    }
}//end class
