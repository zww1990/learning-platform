package io.online.videosite.service;

import io.online.videosite.api.CategoryService;
import io.online.videosite.domain.Category;
import io.online.videosite.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频类别服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:12:26
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> query() {
        return this.categoryRepository.findAll();
    }
}
