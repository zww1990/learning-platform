package io.online.videosite.service;

import io.online.videosite.api.CommentService;
import io.online.videosite.domain.Comment;
import io.online.videosite.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视频评论服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:15:10
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> queryByVideoId(Integer videoId) {
        return this.commentRepository.findAll(
                (root, query, builder) -> builder.equal(root.get("videoId"), videoId),
                Sort.by(Sort.Direction.DESC, "id"));
    }
}
