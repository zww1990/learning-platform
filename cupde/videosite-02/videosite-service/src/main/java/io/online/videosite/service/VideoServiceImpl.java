package io.online.videosite.service;

import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.UserType;
import io.online.videosite.domain.Category;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import io.online.videosite.model.VideoModel;
import io.online.videosite.properties.VideoSiteAppProperties;
import io.online.videosite.repository.CategoryRepository;
import io.online.videosite.repository.CommentRepository;
import io.online.videosite.repository.UserRepository;
import io.online.videosite.repository.VideoRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final CommentRepository commentRepository;
    private final VideoSiteAppProperties appProps;

    @Override
    public List<Video> query(Integer categoryId, AuditStatus... auditStatus) {
        log.info("query(): categoryId = {}, auditStatus = {}", categoryId, auditStatus);
        List<Video> videos = this.videoRepository.findAll((root, query, builder) -> {
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
        // 收集视频作者
        List<String> creators = videos.stream().map(Video::getCreator).distinct().toList();
        Map<String, User> userMap = this.userRepository.findAll((root, query, builder) -> root.get("username").in(creators))
                .stream().collect(Collectors.toMap(User::getUsername, Function.identity()));
        videos.forEach(f -> {
            // 组装视频封面、文件在服务器的存储路径
            f.setVideoLogo(String.format("%s/%s", this.appProps.getImageUploadFolder(), f.getVideoLogo()));
            f.setVideoLink(String.format("%s/%s", this.appProps.getVideoUploadFolder(), f.getVideoLink()));
            // 设置作者昵称
            f.setCreatorNick(userMap.get(f.getCreator()).getNickname());
        });
        return videos;
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
        }, Sort.by(Direction.DESC, "id")).stream().peek(p -> {
            // 组装视频封面、文件在服务器的存储路径
            p.setVideoLogo(String.format("%s/%s", this.appProps.getImageUploadFolder(), p.getVideoLogo()));
            p.setVideoLink(String.format("%s/%s", this.appProps.getVideoUploadFolder(), p.getVideoLink()));
        }).collect(Collectors.toList());
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
                // 组装视频封面、文件在服务器的存储路径
                m.setVideoLogo(String.format("%s/%s", this.appProps.getImageUploadFolder(), m.getVideoLogo()));
                m.setVideoLink(String.format("%s/%s", this.appProps.getVideoUploadFolder(), m.getVideoLink()));
                return m;
            }).orElse(null);
        }
        return this.videoRepository.findById(id).map(m -> {
            // 组装视频封面、文件在服务器的存储路径
            m.setVideoLogo(String.format("%s/%s", this.appProps.getImageUploadFolder(), m.getVideoLogo()));
            m.setVideoLink(String.format("%s/%s", this.appProps.getVideoUploadFolder(), m.getVideoLink()));
            return m;
        }).orElse(null);
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
            // 组装视频封面、文件在服务器的存储路径
            m.setVideoLogo(String.format("%s/%s", this.appProps.getImageUploadFolder(), m.getVideoLogo()));
            m.setVideoLink(String.format("%s/%s", this.appProps.getVideoUploadFolder(), m.getVideoLink()));
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
        video.setVideoLogo(video.getVideoLogo()
                .replace(this.appProps.getImageUploadFolder(), "")
                .replace("/", ""));
        video.setVideoLink(video.getVideoLink()
                .replace(this.appProps.getVideoUploadFolder(), "")
                .replace("/", ""));
        this.videoRepository.save(video);
    }

    @Override
    public void save(VideoModel model, User user) {
        log.info("save(): model = {}, user = {}", model, user);
        Video video = new Video();
        video.setVideoName(model.getVideoName());
        video.setCategoryId(model.getCategoryId());
        video.setVideoHits(0);
        video.setVideoLogo(model.getVideoLogoPath());
        video.setVideoLink(model.getVideoLinkPath());
        video.setCreatedDate(LocalDateTime.now());
        video.setModifiedDate(video.getCreatedDate());
        video.setCreator(user.getUsername());
        video.setModifier(user.getUsername());
        if (user.getUserType() == UserType.ADMIN) {
            // 如果是管理员用户添加视频，直接审核通过
            video.setAuditStatus(AuditStatus.PASSED);
            video.setAuditedDate(video.getCreatedDate());
            video.setAuditor(user.getUsername());
        } else {
            video.setAuditStatus(AuditStatus.PENDING);
        }
        this.videoRepository.save(video);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Video video) {
        log.info("delete(): video = {}", video);
        // 先删除视频评论、视频数据
        this.commentRepository.delete((root, query, builder) ->
                builder.equal(root.get("videoId"), video.getId()));
        this.videoRepository.delete(video);
        // 然后再删除视频封面、链接文件，避免删除失败，误删。
        try {
            Files.deleteIfExists(Paths.get(video.getVideoLogo()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        try {
            Files.deleteIfExists(Paths.get(video.getVideoLink()));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void update(VideoModel model, User user, Video video) {
        log.info("update(): model = {}, user = {}, video = {}", model, user, video);
        String logoPath = null;
        if (StringUtils.hasText(model.getVideoLogoPath())) {
            logoPath = video.getVideoLogo();
            video.setVideoLogo(model.getVideoLogoPath());
        } else {
            video.setVideoLogo(video.getVideoLogo()
                    .replace(this.appProps.getImageUploadFolder(), "")
                    .replace("/", ""));
        }
        String linkPath = null;
        if (StringUtils.hasText(model.getVideoLinkPath())) {
            linkPath = video.getVideoLink();
            video.setVideoLink(model.getVideoLinkPath());
        } else {
            video.setVideoLink(video.getVideoLink()
                    .replace(this.appProps.getVideoUploadFolder(), "")
                    .replace("/", ""));
        }
        video.setVideoName(model.getVideoName());
        video.setCategoryId(model.getCategoryId());
        video.setVideoHits(0);
        video.setModifiedDate(LocalDateTime.now());
        video.setModifier(user.getUsername());
        if (user.getUserType() == UserType.ADMIN) {
            // 如果是管理员用户添加视频，直接审核通过
            video.setAuditStatus(AuditStatus.PASSED);
            video.setAuditedDate(video.getModifiedDate());
            video.setAuditor(user.getUsername());
        } else {
            video.setAuditStatus(AuditStatus.PENDING);
        }
        // 先保存数据
        this.videoRepository.save(video);
        // 然后再删除之前的视频封面、文件，避免保存失败，误删。
        if (logoPath != null) {
            try {
                // 删除之前的视频封面
                Files.deleteIfExists(Paths.get(logoPath));
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
        }
        if (linkPath != null) {
            try {
                // 删除之前的视频文件
                Files.deleteIfExists(Paths.get(linkPath));
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }
}
