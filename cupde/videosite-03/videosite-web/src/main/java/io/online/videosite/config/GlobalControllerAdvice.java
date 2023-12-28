package io.online.videosite.config;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局控制器通知
 *
 * @author 张维维
 * @since 2023-10-05 16:16:15
 */
@ControllerAdvice(annotations = Controller.class)
@Slf4j
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
     * 跳转到 error/xxx 页面
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ModelAndView handleHttpClientErrorException(HttpClientErrorException ex) {
        log.info("handleHttpClientErrorException(): {} = {} - {}",
                ex.getStatusCode(), ex.getStatusText(), ex.getLocalizedMessage());
        ModelAndView mav = new ModelAndView();
        if (ex.getStatusCode() instanceof HttpStatus status) {
            switch (status) {
                case UNAUTHORIZED -> {
                    mav.setStatus(HttpStatus.UNAUTHORIZED);
                    mav.setViewName("error/401");
                }
                case FORBIDDEN -> {
                    mav.setStatus(HttpStatus.FORBIDDEN);
                    mav.setViewName("error/403");
                }
                case NOT_FOUND -> {
                    mav.setStatus(HttpStatus.NOT_FOUND);
                    mav.setViewName("error/404");
                }
                default -> {
                    mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    mav.setViewName("error/500");
                }
            }
            return mav;
        }
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mav.setViewName("error/500");
        return mav;
    }
}
