package myapplication.config.security;

import myapplication.model.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;

/*
Класс обеспечивает редирект на указанный урл в соответствии с ролью.
Handler (то что выберет дальнейший url в зависимости от роли)

*/

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger log = Logger.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    // переменная отвечающая за редирект
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request  ,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }


    //Переоопределяем метод для редиректа
    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            log.info("Can't redirect!");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    // Определяем урл редиректа.
    private String determineTargetUrl(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        /*Если коллекция ролей у юзера не содержит роль ADMIN , но содержит роль USER - отправляет на url /user
        Если не содержит ни одну из указанных ролей - бросает исключение*/
        if (authorities.contains(new Role("ROLE_ADMIN"))){
            return "/admin/list";
        } else if (authorities.contains(new Role("ROLE_USER"))) {
            return "/user/list";
        } else {
            return "/login/accessDenied";
            //throw new IllegalStateException();
        }

    }


    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }



    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }


    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
