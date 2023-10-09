package io.online.videosite.controller;

import io.online.videosite.api.CategoryService;
import io.online.videosite.api.CommentService;
import io.online.videosite.api.VideoService;
import io.online.videosite.constant.AuditStatus;
import io.online.videosite.constant.Constants;
import io.online.videosite.domain.Category;
import io.online.videosite.domain.Comment;
import io.online.videosite.domain.User;
import io.online.videosite.domain.Video;
import io.online.videosite.model.VideoModel;
import io.online.videosite.properties.VideoSiteAppProperties;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 视频控制器
 *
 * @author 张维维
 * @since 2023-10-02 14:30:37
 */
@Controller
@RequestMapping(path = "/videohub")
@AllArgsConstructor
@Slf4j
public class VideoController {
    private final VideoService videoService;
    private final CommentService commentService;
    private final CategoryService categoryService;
    private final PathPatternParser pathPatternParser;
    private final VideoSiteAppProperties appProps;

    /**
     * 跳转到用户的视频列表页
     */
    @GetMapping(path = "/list")
    public ModelAndView list(@SessionAttribute(name = Constants.SESSION_USER_KEY, required = false) User user) {
        // 查询此用户所有的视频
        List<Video> videos = this.videoService.queryForUser(user);
        log.info("list(): 视频数量 = {}", videos.size());
        ModelAndView mav = new ModelAndView();
        mav.addObject("videos", videos);
        mav.setViewName("video/list");
        return mav;
    }

    /**
     * 跳转到查看页面
     */
    @GetMapping(path = "/show/{id}")
    public ModelAndView show(@PathVariable Integer id) {
        Video video = this.videoService.queryOneAndAddHits(id);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        List<Comment> comments = this.commentService.queryByVideoId(id);
        log.info("show(): 视频主键 = {}, 视频评论数量 = {}", id, comments.size());
        ModelAndView mav = new ModelAndView();
        mav.addObject("video", video);
        mav.addObject("comments", comments);
        mav.setViewName("video/show");
        return mav;
    }

    /**
     * 跳转到审核页面
     */
    @GetMapping(path = "/audit/{id}")
    public ModelAndView audit(@PathVariable Integer id) {
        Video video = this.videoService.queryOne(id, FetchType.EAGER);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("video", video);
        mav.setViewName("video/audit");
        return mav;
    }

    /**
     * 处理审核
     */
    @PostMapping(path = "/audit")
    public String handleAudit(@ModelAttribute Video param,
                              @SessionAttribute(Constants.SESSION_USER_KEY) User user) {
        Video video = this.videoService.queryOne(param.getId(), FetchType.LAZY);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        // 只有视频状态为待审核，且提交的审核状态为通过 或 不通过，才进行处理
        if (video.getAuditStatus() == AuditStatus.PENDING &&
                (param.getAuditStatus() == AuditStatus.PASSED ||
                        param.getAuditStatus() == AuditStatus.UNPASSED)) {
            if (AuditStatus.UNPASSED == param.getAuditStatus()) {
                video.setAuditReason(param.getAuditReason());
            }
            video.setAuditStatus(param.getAuditStatus());
            this.videoService.audit(video, user);
        }
        return String.format("%s/videohub/audit/%s", UrlBasedViewResolver.REDIRECT_URL_PREFIX, param.getId());
    }

    /**
     * 处理删除
     */
    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable Integer id, @SessionAttribute(Constants.SESSION_USER_KEY) User user) {
        Video video = this.videoService.queryOne(id, FetchType.LAZY);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        // 如果视频不是自己创建的，不允许删除
        if (!video.getCreator().equals(user.getUsername())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
        this.videoService.delete(video);
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/videohub/list";
    }

    /**
     * 跳转到添加页面
     */
    @GetMapping(path = "/add")
    public ModelAndView add() {
        return this.create(new VideoModel());
    }

    /**
     * 处理添加
     */
    @PostMapping(path = "/add")
    public ModelAndView handleAdd(@ModelAttribute VideoModel model,
                                  @SessionAttribute(Constants.SESSION_USER_KEY) User user) throws IOException {
        if (!StringUtils.hasText(model.getVideoName())) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "请输入视频名称！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (model.getCategoryId() == null) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "请选择视频类别！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (model.getVideoLogo().isEmpty()) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "请上传视频标志！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (model.getVideoLink().isEmpty()) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "请上传视频文件！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        // 如果上传视频标志的格式不正确
        if (!this.isMatch(this.appProps.getImageMimePatterns(), model.getVideoLogo().getContentType())) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "上传视频标志的格式不正确，请重新上传！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        // 如果上传视频文件的格式不正确
        if (!this.isMatch(this.appProps.getVideoMimePatterns(), model.getVideoLink().getContentType())) {
            ModelAndView mav = this.create(model)
                    .addObject("error", "上传视频文件的格式不正确，请重新上传！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        log.info("handleAdd(): VideoLogo = {}, VideoLink = {}",
                model.getVideoLogo().getOriginalFilename(), model.getVideoLink().getOriginalFilename());
        model.setVideoLogoPath(this.makeFileName(model.getVideoLogo().getOriginalFilename()));
        model.setVideoLinkPath(this.makeFileName(model.getVideoLink().getOriginalFilename()));
        // 写入文件
        model.getVideoLogo().transferTo(Paths.get(this.appProps.getImageUploadFolder(), model.getVideoLogoPath()));
        model.getVideoLink().transferTo(Paths.get(this.appProps.getVideoUploadFolder(), model.getVideoLinkPath()));
        this.videoService.save(model, user);
        return new ModelAndView(UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/videohub/list");
    }

    /**
     * 跳转到编辑页面
     */
    @GetMapping(path = "/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        Video video = this.videoService.queryOne(id, FetchType.LAZY);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        return this.create(video);
    }

    /**
     * 处理编辑
     */
    @PostMapping(path = "/edit")
    public ModelAndView handleEdit(@SessionAttribute(Constants.SESSION_USER_KEY) User user,
                                   @ModelAttribute VideoModel model) throws IOException {
        if (model.getId() == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        Video video = this.videoService.queryOne(model.getId(), FetchType.LAZY);
        // 如果视频不存在
        if (video == null) {
            throw new EntityNotFoundException("此视频不存在");
        }
        if (!StringUtils.hasText(model.getVideoName())) {
            ModelAndView mav = this.create(video)
                    .addObject("error", "请输入视频名称！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        if (model.getCategoryId() == null) {
            ModelAndView mav = this.create(video)
                    .addObject("error", "请选择视频类别！");
            mav.setStatus(HttpStatus.BAD_REQUEST);
            return mav;
        }
        // 如果重新上传了视频标志
        if (!model.getVideoLogo().isEmpty()) {
            // 如果上传视频标志的格式不正确
            if (!this.isMatch(this.appProps.getImageMimePatterns(), model.getVideoLogo().getContentType())) {
                ModelAndView mav = this.create(video)
                        .addObject("error", "上传视频标志的格式不正确，请重新上传！");
                mav.setStatus(HttpStatus.BAD_REQUEST);
                return mav;
            }
            log.info("handleEdit(): VideoLogo = {}", model.getVideoLogo().getOriginalFilename());
            model.setVideoLogoPath(this.makeFileName(model.getVideoLogo().getOriginalFilename()));
            // 写入文件
            model.getVideoLogo().transferTo(Paths.get(this.appProps.getImageUploadFolder(), model.getVideoLogoPath()));
        }
        // 如果重新上传了视频文件
        if (!model.getVideoLink().isEmpty()) {
            // 如果上传视频文件的格式不正确
            if (!this.isMatch(this.appProps.getVideoMimePatterns(), model.getVideoLink().getContentType())) {
                ModelAndView mav = this.create(video)
                        .addObject("error", "上传视频文件的格式不正确，请重新上传！");
                mav.setStatus(HttpStatus.BAD_REQUEST);
                return mav;
            }
            log.info("handleEdit(): VideoLink = {}", model.getVideoLink().getOriginalFilename());
            model.setVideoLinkPath(this.makeFileName(model.getVideoLink().getOriginalFilename()));
            // 写入文件
            model.getVideoLink().transferTo(Paths.get(this.appProps.getVideoUploadFolder(), model.getVideoLinkPath()));
        }
        this.videoService.update(model, user, video);
        return new ModelAndView(UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/videohub/list");
    }

    private boolean isMatch(String[] patterns, String contentType) {
        log.info("isMatch(): patterns = {}, contentType = {}", patterns, contentType);
        if (!StringUtils.hasText(contentType)) {
            return false;
        }
        return Stream.of(patterns).anyMatch(pattern ->
                this.pathPatternParser.parse(pattern)
                        .matches(PathContainer.parsePath(contentType)));
    }

    private String makeFileName(String originFileName) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (!StringUtils.hasText(originFileName)) {
            return uuid;
        }
        return String.format("%s.%s", uuid, StringUtils.getFilenameExtension(originFileName));
    }

    private ModelAndView create(VideoModel model) {
        List<Category> categories = this.categoryService.query();
        return new ModelAndView("video/add")
                .addObject("video", model)
                .addObject("categories", categories);
    }

    private ModelAndView create(Video video) {
        List<Category> categories = this.categoryService.query();
        return new ModelAndView("video/edit")
                .addObject("video", video)
                .addObject("categories", categories);
    }

}
