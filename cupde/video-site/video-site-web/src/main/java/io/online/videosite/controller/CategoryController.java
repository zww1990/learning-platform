package io.online.videosite.controller;

import io.online.videosite.api.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
