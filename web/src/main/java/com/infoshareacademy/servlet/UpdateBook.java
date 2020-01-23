package com.infoshareacademy.servlet;

import com.infoshareacademy.dto.BookDTO;
import com.infoshareacademy.freemarker.TemplateProvider;
import com.infoshareacademy.service.BookService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/update")
public class UpdateBook extends HttpServlet {

    @Inject
    private BookService bookService;
    @Inject
    private TemplateProvider templateProvider;

    private Logger logger = LoggerFactory.getLogger(getClass().getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Template template = templateProvider.getTemplate(getServletContext(), "Admin-site.ftlh");

        PrintWriter printWriter = resp.getWriter();
        Map<String, Object> dataModel = new HashMap<>();

        try {
            template.process(dataModel, printWriter);
        } catch (TemplateException e) {
            logger.error(e.getMessage());
            logger.info("Error-info4");
            logger.debug("Error -debug4");
        }
        logger.info("Error-info5");
        logger.debug("Error -debug5");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Long bookId = Long.valueOf(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String kind = req.getParameter("kind");
        String genre = req.getParameter("genre");
        String hasAudio = req.getParameter("has_audio");
        String cover = req.getParameter("cover");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookService.update(bookId,bookDTO);

    }
}
