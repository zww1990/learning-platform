package io.online.videosite.service;

import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.UserType;
import io.online.videosite.domain.Category;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import io.online.videosite.repository.CategoryRepository;
import io.online.videosite.repository.UserRepository;
import io.online.videosite.repository.VideoRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 视频服务接口实现类
 *
 * @author 张维维
 * @since 2023-10-02 14:18:25
 */
@Service
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public List<Video> query(Integer categoryId, AuditStatus... auditStatus) {
        log.info("query(): categoryId = {}, auditStatus = {}", categoryId, auditStatus);
        return this.videoRepository.findAll((root, query, builder) -> {
            List<Predicate> list = new ArrayList<>();
            // 如果指定审核状态
            if (auditStatus.length != 0) {
                list.add(root.get("auditStatus").in(auditStatus));
            }
            // 如果指定类别
            if (categoryId != null) {
                list.add(builder.equal(root.get("categoryId"), categoryId));
            }
            return query.where(list.toArray(Predicate[]::new)).getRestriction();
        }, Sort.by(Direction.DESC, "videoHits"));
    }

    @Override
    public List<Video> queryForUser(User user) {
        log.info("queryForUser(): user = {}", user);
        return this.videoRepository.findAll((root, query, builder) -> {
            // 如果是管理员，查询所有视频
            if (user.getUserType() == UserType.ADMIN) {
                return query.getRestriction();
            }
            return builder.equal(root.get("creator"), user.getUsername());
        }, Sort.by(Direction.DESC, "id"));
    }

    @Override
    public Video queryOne(Integer id, FetchType fetchType) {
        log.info("queryOne(): id = {}, fetchType = {}", id, fetchType);
        if (fetchType == FetchType.EAGER) {
            return this.videoRepository.findById(id).map(m -> {
                m.setCategoryName(this.categoryRepository.findById(m.getCategoryId())
                        .map(Category::getCategoryName).orElseGet(String::new));
                m.setCreatorNick(this.userRepository.findByUsername(m.getCreator())
                        .map(User::getNickname).orElseGet(String::new));
                Optional.ofNullable(m.getAuditor()).ifPresent(c ->
                        m.setAuditorNick(this.userRepository.findByUsername(c)
                                .map(User::getNickname).orElseGet(String::new)));
                return m;
            }).orElse(null);
        }
        return this.videoRepository.findById(id).orElse(null);
    }

    @Override
    public Video queryOneAndAddHits(Integer id) {
        log.info("queryOneAndAddHits(): id = {}", id);
        return this.videoRepository.findById(id).map(m -> {
            // 增加点击量
            m.setVideoHits(m.getVideoHits() + 1);
            this.videoRepository.save(m);
            m.setCategoryName(this.categoryRepository.findById(m.getCategoryId())
                    .map(Category::getCategoryName).orElseGet(String::new));
            m.setCreatorNick(this.userRepository.findByUsername(m.getCreator())
                    .map(User::getNickname).orElseGet(String::new));
            Optional.ofNullable(m.getAuditor()).ifPresent(c ->
                    m.setAuditorNick(this.userRepository.findByUsername(c)
                            .map(User::getNickname).orElseGet(String::new)));
            return m;
        }).orElse(null);
    }

    @Override
    public void audit(Video video, User user) {
        log.info("audit(): video = {}, user = {}", video, user);
        video.setAuditor(user.getUsername());
        video.setAuditedDate(LocalDateTime.now());
        video.setModifiedDate(video.getAuditedDate());
        video.setModifier(user.getUsername());
        this.videoRepository.save(video);
    }
}
