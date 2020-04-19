package com.imooc.controller;

import com.imooc.Constant.CookieConstant;
import com.imooc.Constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.form.UserInfoFrom;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/loginpage")
    public ModelAndView loginpage() {
        return new ModelAndView("/user/index");
    }

    @RequestMapping("/login")
    public ModelAndView login(@Valid UserInfoFrom userInfoFrom,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldErrors());
            map.put("url", "/seller/loginpage");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(userInfoFrom.getUsername());
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.USERERROR.getMsg());
            map.put("url", "/seller/loginpage");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        if (!sellerInfo.getPassword().equals(userInfoFrom.getPassword())) {
            map.put("msg", ResultEnum.USERERROR.getMsg());
            // TODO
            map.put("url", "/seller/loginpage");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }

        // 设置token到redis
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), sellerInfo.getOpenid(), RedisConstant.EXPIRE, TimeUnit.SECONDS);

        // 设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return new ModelAndView("redirect:/seller/order/list");
    }

    @RequestMapping("/logout")
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/seller/loginpage");
        map.put("status", ResultEnum.SUCCESS.getCode());
        return new ModelAndView("/common/error", map);
    }
}
