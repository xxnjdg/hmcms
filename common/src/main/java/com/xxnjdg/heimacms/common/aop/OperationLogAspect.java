package com.xxnjdg.heimacms.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.xxnjdg.heimacms.common.Utils.IpUtil;
import com.xxnjdg.heimacms.common.Utils.UuidUtil;
import com.xxnjdg.heimacms.common.model.aop.ExceptionLog;
import com.xxnjdg.heimacms.common.model.aop.OperationLogRetention;
import com.xxnjdg.heimacms.common.model.aop.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.cnblogs.com/wm-dv/p/11735828.html
 * 日志切面
 *
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Aspect
@Component
public  class OperationLogAspect {

    /**
     * 操作版本号
     * <p>
     * 项目启动时从命令行传入，例如：java -jar xxx.war --version=201902
     * </p>
     */
//    @Value("${version}")
//    private String operVer;
//
//    @Autowired
//    private OperationLogService operationLogService;
//
//    @Autowired
//    private ExceptionLogService exceptionLogService;

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.xxnjdg.heimacms.common.model.aop.OperationLogRetention)")
    public void operationLogPointCut() {
    }
    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(* com.xxnjdg.heimacms.cmsservice.controller..*.*(..))")
    public void exceptionLogPointCut() {
    }


    @Pointcut("execution(* com.xxnjdg.heimacms.cmsservice.test.TestAop.test(..))")
    public void myTestTest() {
    }

    @Pointcut("exceptionLogPointCut() ||myTestTest()")
    public void cutAll() {
    }


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operationLogPointCut()", returning = "keys")
    public void saveOperationLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperationLog operationLog = new OperationLog();
        try {
            // 主键ID
            operationLog.setOperationId(UuidUtil.getUUID32());

            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperationLogRetention opLog = method.getAnnotation(OperationLogRetention.class);
            if (opLog != null) {
                String operationModel = opLog.operationModel();
                String operationType = opLog.operationType();
                String operationDesc = opLog.operationDesc();
                // 操作模块
                operationLog.setOperationModel(operationModel);
                // 操作类型
                operationLog.setOperationType(operationType);
                // 操作描述
                operationLog.setOperationDesc(operationDesc);
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            // 请求方法
            operationLog.setOperationMethod(methodName);

            //请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONObject.toJSONString(rtnMap);

            // 请求参数
            operationLog.setOperationRequestParam(params);
            // 返回结果
            operationLog.setOperationResponseParam(JSONObject.toJSONString(keys));
//            operationLog.setOperationUserId(UserShiroUtil.getCurrentUserLoginName()); // 请求用户ID
//            operationLog.setOperationUserName(UserShiroUtil.getCurrentUserName()); // 请求用户名称
            // 请求IP
            operationLog.setOperationIp(IpUtil.getIpAddrByRequest(request));
            // 请求URI
            operationLog.setOperationUri(request.getRequestURI());
            // 创建时间
            operationLog.setOperationCreateTime(new Date());
//            operationLog.setOperationVersion(operVer); // 操作版本
//            operationLogService.insert(operationLog);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(operationLog);
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     * 这里捕获到的异常会自动往全局异常处理抛
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "cutAll()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExceptionLog exceptionLog = new ExceptionLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            exceptionLog.setExceptionId(UuidUtil.getUUID32());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = JSONObject.toJSONString(rtnMap);
            // 请求参数
            exceptionLog.setExceptionRequestParam(params);
            // 请求方法名
            exceptionLog.setOperationMethod(methodName);
            // 异常名称
            exceptionLog.setExceptionName(e.getClass().getName());
            // 信息
            exceptionLog.setExceptionMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
//            exceptionLog.setOperUserId(UserShiroUtil.getCurrentUserLoginName()); // 操作员ID
//            exceptionLog.setOperUserName(UserShiroUtil.getCurrentUserName()); // 操作员名称
            // 操作URI
            exceptionLog.setOperationUri(request.getRequestURI());
            // 操作员IP
            exceptionLog.setOperationIp(IpUtil.getIpAddrByRequest(request));
//            exceptionLog.setOperVer(operVer); // 操作版本号
            // 发生异常时间
            exceptionLog.setOperationCreateTime(new Date());

//            exceptionLogService.insert(exceptionLog);

        } catch (Exception e2) {
            e2.printStackTrace();
        }


        System.out.println(exceptionLog);
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}