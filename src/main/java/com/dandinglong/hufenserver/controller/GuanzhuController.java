package com.dandinglong.hufenserver.controller;


import com.dandinglong.hufenserver.dao.RespRestfulDto;
import com.dandinglong.hufenserver.dto.HfToutiaoAccount;
import com.dandinglong.hufenserver.dto.HfUser;
import com.dandinglong.hufenserver.mapper.HfToutiaoAccountMapper;
import com.dandinglong.hufenserver.util.RespCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/guanzhu")
public class GuanzhuController {
    @Autowired
    private HfToutiaoAccountMapper hfToutiaoAccountMapper;
    @RequestMapping("/accountSave")
    public RespRestfulDto accountSave(@RequestBody HfToutiaoAccount hfToutiaoAccount){
//        try {
            Example example=new Example(HfToutiaoAccount.class);
            example.createCriteria().andEqualTo("toutiaoAccount",hfToutiaoAccount.getToutiaoAccount());
            List<HfToutiaoAccount> list=hfToutiaoAccountMapper.selectByExample(example);
            if(list.size()==0){
                hfToutiaoAccount.setLastLoginTime(new Date());
                hfToutiaoAccountMapper.insert(hfToutiaoAccount);
            }
            return RespCommon.success(1);
//        }catch (Exception e){
//            return RespCommon.fail(e);
//        }

    }
}
