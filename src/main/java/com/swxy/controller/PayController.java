//package com.swxy.controller;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpSession;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//@Controller
//public class PayController {
////    @Autowired
////    DonaItemService donaItemService;
////    @Autowired
////    DonaService donaService;
//    //appid
//    private final static String APP_ID = "2021000122683946";
//    //应用私钥
//    private final static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDlw2RabUNY1C4nVAOQqa/uXz38PLufj+o9VXStt2jQyLzywlDNZDqt32IEwmu28f2EzOvLRS49jKxrA/nj+Qf5ly34rtqZZQqxeJrwTqR3py1nH6UoPPyT5IS7UYHAxCi5HfknT19SNDrCPXGYpe2CNsNRXSVZqf6RpGGgJhJj4o2HSsoLWX7HUG0kq6GXkXNDkk5leL/hM/3zKlxjJ9Q19OEhvFVEpBXu2Y5kC5iZiDR0QIy19pRTiK2XpYSzkjaRQPvgGtu95PijzrTP+qTD6ZskiPG07CeMtGmdI2PeXIt+ZaO0QzWpg4SABhBPumVU8qFanY2G4V+3JBhhB+5DAgMBAAECggEABrebq3keSu6JGuU95XDQj+uA6GruIVAXzQs+igWertvvijlcG2Uewg6R0wnMFGEmxEcpbZvsKih+sfI/0AZr0UQ2N9sE0Q8/LMkV3oiuDkF49lbagT6nM7uulbtgS75wYlkZ+6g6ZTDZtuwM8iWHuBLZyvsqKGDr7F4d7IywZPMgeUWwT1VKYvVrpRM+BpvMbznMTSP2E6Ja1EKh+zADtQaRKF3dp4A7WMzD2QxfmpKb34BLvJunlYMPqMfXKNUUF0/PFAQQYfXi5RqjCcbv09m+6LcYuej4aFrq1r3LMoc0VOPHJQcG6MIpNJ7+c8RLYNdgR3w7q6k8+chEqlRBgQKBgQD2sJc9LwdaVHCfDz52eBGdpwjrOlFSp8CNzinBvcvFeOaII3lpIIJDJhYTDtcOoA6YIALHurG2XL4zp3Di985lH/AtsoRi29LJDzPSLoVu1u9r85GNGYl3fFHlPIYXNGWyMU3wHdNSFaO1mGqWpCxB3B2/n9jFpbc39nYCIo6RUwKBgQDub0OjtI7W669bYO6f62oMyjNnlgIetvz/BuDFsWKeXZ7kF4LJODBoSp6EtekYRzVovYE2KdjiFW92wLToMMbmxLPkpfPyd2m4iKR8fv1HuXXOqXu+QnDO4oM53h/62b5ZjkfMEIkENSJaBgtMaGHkyjvhWOV+rKrfMuKrisbhUQKBgGxasHNtXrtsb3NODM+YeZRXs/EuUl4N1OP0dB7nMMPyEMAnZMKqRs0bw4JneEqyPvbOyKnV9Qz2u4yQtWLcGcoNNz+7M68AgxtRFB/z0yQWQF+YQoxJdToIXyhjZzp2B103jtYzI4jgfGHdKqQ0RERBXMe4fEyEN2/bT4Ysi5aFAoGALXk06jzeXSj3+bKD8CYCxMuvgBsA7oCxwaW7KpkJGPD8A75kKCI6QTmx/9d9zC6QhhhWU1Ep1vyFlJ1uIzZiSNLNCFeKLf0OQa51yHSjs5EwYai7O+8J+rsCkf/GE56Biizzjny81PuZQJgUgY8K1IqpXvIIWt9te0IGLvc5tPECgYAdS1DyogqzYNjbmM0P7ieR2Okzoob8ZCnFpv2lxngPts1GGdsit9Cd1rtLzhTVPMzlBP4GS1J25vlr2STYncCf/+offen8FmazLolIoCuQ1crkoH0UhwGnn8uC1HAJ/0OGqCVC6ONMlFGFfXncqyfpNwE6+UtaQkAXE164KS2u6g==";
//        private final static String CHARSET = "UTF-8";
//    // 支付宝公钥
//    private final static String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA23C0uMcjGoPr1tfvjAoYICKNmWCNhG+Aw/nwH8L2wV+IDlK6eL898hc6psndDeBx0CefKhPkncJVhzRqmZQdnLvjO8NvMjUpNvOzuXYN7BN9rpLeZX7IVYiaNO97NFHdHYavTgQU5OskK60f1CpHZl87wlz38c3IxnvsR1HsfMuGLhcJQkos3WCCqVsqeoYTNk/x9Nyz5bXYkZHZIkhg04wK3aMzNxrVayrB3xsWHXhiruBUt/eKqSm/6DZ2ojrtMtTyC3WHzMBG1dDyACdT2pXMpVQWYWsYQ8gK4+O3iG70utKG2lRPJvOZk/DOwhM5dsGV2mMVuzYg+ef7F+o8wQIDAQAB";
//    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
//    private final static String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
//    private final static String FORMAT = "JSON";
//    //签名方式
//    private final static String  SIGN_TYPE = "RSA2";
//    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
//    private final static String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
//    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
//    private final static String RETURN_URL = "http://localhost:8080/returnUrl";
//
//
//    //必须加ResponseBody注解，否则spring会寻找thymeleaf页面
//    @ResponseBody
//    @RequestMapping("/pay/alipay")
//    public String alipay(HttpSession session, Model model,
//                         @RequestParam("dona_money") float dona_money,
//                         @RequestParam("dona_id") int dona_id) throws AlipayApiException {
//        //把dona_id项目id 放在session中
//        session.setAttribute("dona_id",dona_id);
//
//        //生成订单号（支付宝的要求？）
//        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String user = UUID.randomUUID().toString().replace("-","").toUpperCase();
//
//        String OrderNum = time+user;
//
//        //调用封装好的方法（给支付宝接口发送请求）
//        return sendRequestToAlipay(OrderNum,dona_money,"ghjk");
//    }
//    /*
//参数1：订单号
//参数2：订单金额
//参数3：订单名称
// */
//    //支付宝官方提供的接口
//    public static String sendRequestToAlipay(String outTradeNo,Float totalAmount,String subject) throws AlipayApiException, AlipayApiException {
//        //获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
//
//        //设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(RETURN_URL);
//        alipayRequest.setNotifyUrl(NOTIFY_URL);
//
//        //商品描述（可空）
//        String body="";
//        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
//                + "\"total_amount\":\"" + totalAmount + "\","
//                + "\"subject\":\"" + subject + "\","
//                + "\"body\":\"" + body + "\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//
//        //请求
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        return result;
//    }
//
//}
//
