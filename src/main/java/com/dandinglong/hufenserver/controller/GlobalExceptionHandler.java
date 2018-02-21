package com.dandinglong.hufenserver.controller;

import com.dandinglong.hufenserver.dao.RespRestfulDto;
import com.dandinglong.hufenserver.exception.LoginException;
import com.dandinglong.hufenserver.util.RespCommon;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler{

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespRestfulDto ajaxException(HttpServletRequest req, Exception e){
        if(e instanceof LoginException){
            return RespCommon.loginFail(e);
        }
        return RespCommon.fail(e);
    }


}
