package io.online.videosite.controller;

import io.online.videosite.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局控制器通知
 *
 * @author 张维维
 * @since 2023-10-05 16:16:15
 */
@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {
    /**
     * 跳转到404页面
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException() {
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.NOT_FOUND);
        mav.setViewName("error/404");
        return mav;
    }

    /**
     * 跳转到401页面
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFoundException() {
        ModelAndView mav = new ModelAndView();
        mav.setStatus(HttpStatus.UNAUTHORIZED);
        mav.setViewName("error/401");
        return mav;
    }
}
