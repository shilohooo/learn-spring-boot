package org.shiloh.web.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 15:03
 * @description 非阻塞Servlet
 */
@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

    private static final long serialVersionUID = -130855315825261929L;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * 在类上使用@WebServlet(asyncSupported = true)开启异步支持，通过AsyncContext asyncContext = request.startAsync();
     * 获取异步上下文AsyncContext，AsyncContext的complete方法用于标识异步调用结束。
     * CompletableFuture为Java 8提供的Future接口实现类，可以方便的处理异步调用
     * @return void
     **/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long start = System.currentTimeMillis();
        AsyncContext asyncContext = req.startAsync();
        CompletableFuture.runAsync(() -> execute(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));
        logger.info("总耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    private void execute(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
        try {
            TimeUnit.SECONDS.sleep(2);
            response.getWriter().append("Hello async servlet:)");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        asyncContext.complete();
    }
}
