package org.shiloh.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 14:54
 * @description 传统阻塞式的servlet
 */
@WebServlet(urlPatterns = "/sync")
public class SyncServlet extends HttpServlet {

    private static final long serialVersionUID = -130855315825261929L;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        this.execute(req, resp);
        logger.info("总耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            TimeUnit.SECONDS.sleep(2);
            resp.getWriter().append("Hello :)");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
