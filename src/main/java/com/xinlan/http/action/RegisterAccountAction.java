package com.xinlan.http.action;

import com.xinlan.exception.CommonException;
import com.xinlan.http.Resp;
import com.xinlan.http.StatusCode;
import com.xinlan.model.User;
import com.xinlan.service.ServerContext;
import com.xinlan.service.UserService;
import com.xinlan.util.CheckUtil;
import com.xinlan.widget.HttpComponent;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.StringUtil;

import java.util.Map;


/**
 * 注册
 */
@HttpComponent
public class RegisterAccountAction extends BaseAction {
    private UserService mUserService;

    public RegisterAccountAction(){
        mUserService = ServerContext.getInstance().getUserService();
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String uri() {
        return "/createUser";
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
        String avatar = params.get("avatar");
        int male = Integer.parseInt(params.get("male"));

        System.out.println("account = " + account + "   pwd = " + pwd + " avatar= " + avatar + " male = " + male);

        if (StringUtil.isNullOrEmpty(account)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名不能为空"));
            return;
        }

        if (StringUtil.isNullOrEmpty(pwd)) {
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名密码不能为空"));
            return;
        }

        if(!CheckUtil.checkAccountMark(account)){
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "用户名只能包含字母，数字，中文"));
            return;
        }

        try {
            User user = mUserService.addUser(account , pwd , avatar , male);
            if(user.getUid() > 0){
                Resp<User> success =  Resp.createSuccess(user);
                writeHttpResponse(response , success);
            }else{
                writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, "数据插入失败"));
            }
        } catch (CommonException e) {
            e.printStackTrace();
            writeHttpResponse(response, Resp.error(StatusCode.CODE_ERROR_LOGIC, e.getErrorMsg()));
        }
    }
}//end class
