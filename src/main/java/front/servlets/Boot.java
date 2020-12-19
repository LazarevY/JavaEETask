package front.servlets;

import core.dao.DAO;
import core.dao.SQLBasedDAO;
import core.inverseofcontrol.boot.Application;
import core.inverseofcontrol.boot.ApplicationContext;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lazarev Yaroslav
 */

@WebServlet(value = "/", loadOnStartup = 0)
public class Boot extends HttpServlet {

    @Getter
    private static ApplicationContext context;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect(req.getContextPath() + "/main");
    }

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        super.init();
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        context = Application.run("",
                new HashMap<>(Map.of(DAO.class, SQLBasedDAO.class)));
    }
}
