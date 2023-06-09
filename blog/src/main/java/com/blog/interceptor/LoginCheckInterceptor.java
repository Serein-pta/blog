package com.blog.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.BeanContext;
import com.blog.pojo.Result;
import com.blog.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源运行前运行，返回true：放行，返回false，不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行...");

            return true;
        }

        // 3.判断请求url中是否包含register，如果包含，说明是注册或注销操作，放行
        if (url.contains("register")) {
            log.info("注册操作，放行...");
            return true;
        }

        //4.获取请求头中的令牌
        String jwt = req.getHeader("token");

        //5.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            //手动装换 对象--json ----->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        // 获取请求方法
        /*String method = req.getMethod();
        if (method.equals("POST") || method.equals("PUT") ||method.equals("DELETE")) {
        // 进行token校验
            try {
                JwtUtils.parseJWT(jwt);
            } catch (Exception e) {
                e.printStackTrace();
                log.info("解析令牌失败，返回未登录错误信息");
                Result error = Result.error("NOT_LOGIN");
                //手动装换 对象--json ----->阿里巴巴fastJSON
                String notLogin = JSONObject.toJSONString(error);
                resp.getWriter().write(notLogin);
                return false;
            }
        }*/

        //6.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动装换 对象--json ----->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //7.放行
        log.info("令牌合法，放行");
        return true;

    }

    @Override//目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override//视图渲染后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion...");;
    }


}
