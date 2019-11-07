package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {

    private String getCaptchaForm(Integer code) {
        String encodedImage = Base64.getEncoder().encodeToString(ImageUtils.toPng(code.toString()));
        String htmlFirstPart = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<img src=\"data:image/png;base64, ";
        String htmlSecondPart = "\"/>\n" +
                "\t<form method=\"get\" action=\"\">\n" +
                "\t<label for=\"captcha\">Enter: </label>\n" +
                "\t<input name=\"captcha\" id=\"captcha\"/>\n" +
                "\t</form>\n" +
                "</body>\n" +
                "</html>";
        return htmlFirstPart + encodedImage + htmlSecondPart;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String submittedValue = request.getParameter("captcha");
        if (submittedValue != null) {
            Object actualValue = session.getAttribute("codeNumber");
            if (actualValue != null) {
                if (actualValue.equals(submittedValue)) {
                    session.setAttribute("passedCaptcha", "true");
                    response.sendRedirect(request.getRequestURI());
                    return;
                }
            }
        }
        Object passedCaptcha = session.getAttribute("passedCaptcha");
        if (passedCaptcha == null) session.setAttribute("passedCaptcha", "false");
        String passedCaptchaValue = (String) session.getAttribute("passedCaptcha");

        if (!passedCaptchaValue.equals("true")) {
            int codeNumber = Math.abs(new Random().nextInt(900)) + 100;
            session.setAttribute("codeNumber", Integer.toString(codeNumber));
            response.setContentType("text/html");
            response.getWriter().print(getCaptchaForm(codeNumber));
            response.getWriter().flush();
        } else {
            chain.doFilter(request, response);
        }
    }
}