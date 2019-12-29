package org.shiloh.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.shiloh.web.dao.UserLogDao;
import org.shiloh.web.entity.UserLog;
import org.shiloh.web.util.HttpContextUtils;
import org.shiloh.web.util.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author Shiloh
 * @Date 2019/11/28 11:31
 * @Description 用户操作日志切面
 */
@Aspect
@Component
public class UserLogAspect {

    @Autowired
    private UserLogDao userLogDao;

    /**
     * 切点
     */
    @Pointcut("@annotation(org.shiloh.web.common.annotation.UserLog)")
    public void pointcut() {
    }

    /**
     * 环绕通知
     *
     * @param point
     * @return
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 执行时长
        long executionTime = System.currentTimeMillis() - beginTime;
        saveLog(point, executionTime);
        return result;
    }

    /**
     * 保存用户操作日志
     *
     * @param point
     * @param executionTime
     */
    private void saveLog(ProceedingJoinPoint point, long executionTime) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        UserLog userLog = new UserLog();
        org.shiloh.web.common.annotation.UserLog logAnnotation = method.getAnnotation(org.shiloh.web.common.annotation.UserLog.class);
        if (logAnnotation != null) {
            // 将注解上的操作描述赋值到用户日志实体的operation属性中
            userLog.setOperation(logAnnotation.value());
        }
        // 获取请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        userLog.setRequestMethod(String.format("%1$s.%2$s()", className, methodName));
        // 请求的方法参数值
        Object[] args = point.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (args.length > 0 && parameterNames.length > 0) {
            StringBuilder requestParams = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                requestParams.append(String.format("%1$s : %2$s,", parameterNames[i], args[i]));
            }
            userLog.setRequestParams(requestParams.toString());
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 获取ip地址
        String ipAddress = IpUtils.getIpAddress(request);
        userLog.setIpAddress(ipAddress);
        // 设置响应时间戳
        userLog.setResponseTime(executionTime);
        // 模拟用户名
        userLog.setUsername("shiloh");
        // 保存日志
        userLogDao.save(userLog);
    }
}
