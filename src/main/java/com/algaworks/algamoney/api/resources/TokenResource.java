package com.algaworks.algamoney.api.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1")
public class TokenResource {

    @DeleteMapping("/token/revoke")
    public void revoke(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // TODO: change for true at production
        cookie.setPath(servletRequest.getContextPath() + "/oauth/token");
        cookie.setMaxAge(0);

        servletResponse.addCookie(cookie);
        servletResponse.setStatus(HttpStatus.NO_CONTENT.value());
    }
}
