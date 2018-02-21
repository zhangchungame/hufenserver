package com.dandinglong.hufenserver.controller;

import com.dandinglong.hufenserver.dao.RespRestfulDto;
import com.dandinglong.hufenserver.dto.HfUser;
import com.dandinglong.hufenserver.mapper.HfUserMapper;
import com.dandinglong.hufenserver.util.RespCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private HfUserMapper hfUserMapper;

    @RequestMapping(value = "")
    public RespRestfulDto login(@RequestBody HfUser hfUser, HttpServletRequest request) {
        try {
            Example example=new Example(HfUser.class);
            example.createCriteria().andEqualTo("account",hfUser.getAccount()).andEqualTo("password",hfUser.getPassword());
            List<HfUser> list=hfUserMapper.selectByExample(example);
            if(list.size()==1){
                HfUser record=list.get(0);
                record.setLastloginTime(new Date());
                hfUserMapper.updateByPrimaryKey(record);
                request.getSession().setAttribute("hfUser",list.get(0));
                record.setPassword("");
                return RespCommon.success(record);
            }else{
                throw new Exception("帐号或密码错误");
            }
        }catch (Exception e){
            return RespCommon.fail(e);
        }
    }

    @RequestMapping(value = "register")
    public RespRestfulDto register(@RequestBody HfUser hfUser){
        try {
            if(hfUser.getAccount().length()<8){
                throw new Exception("帐号不能小于8位");
            }
            if(hfUser.getPassword().length()<6){
                throw new Exception("密码不能小于6位");
            }
            Example example=new Example(HfUser.class);
            example.createCriteria().andEqualTo("account",hfUser.getAccount());
            List<HfUser> list=hfUserMapper.selectByExample(example);
            if(list.size()==0){
                hfUser.setLastloginTime(new Date());
                hfUserMapper.insert(hfUser);
                return RespCommon.success(1);
            }else{
                throw new Exception("帐号已存在");
            }
        }catch (Exception e){
            return RespCommon.fail(e);
        }
    }
}
