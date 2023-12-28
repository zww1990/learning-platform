package io.online.videosite.controller;

import io.online.videosite.api.CategoryService;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Category;
import io.online.videosite.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * 视频类别控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:27:10
 */
@Controller
@RequestMapping(path = "/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 跳转到添加类别页面
     */
    @GetMapping(path = "/add")
    public ModelAndView add() {
        return new ModelAndView("category/add").addObject("category", new Category());
    }

    /**
     * 处理添加类别
     */
    @PostMapping(path = "/add")
    public ModelAndView handleAdd(@ModelAttribute Category category,
                                  @SessionAttribute(Constants.SESSION_USER_KEY) User user) {
        ModelAndView mav = new ModelAndView();
        if (!StringUtils.hasText(category.getCategoryName())) {
            mav.setStatus(HttpStatus.BAD_REQUEST);
            mav.setViewName("category/add");
            mav.addObject("error", "请输入视频类别名称！");
            return mav;
        }
        if (this.categoryService.checkExist(category)) {
            mav.setStatus(HttpStatus.BAD_REQUEST);
            mav.setViewName("category/add");
            mav.addObject("error", "此视频类别名称已存在！");
            return mav;
        }
        this.categoryService.save(category, user);
        mav.setViewName(UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/");
        return mav;
    }
}
