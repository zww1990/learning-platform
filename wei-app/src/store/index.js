import { reactive } from "vue";
import { message, theme } from "ant-design-vue";
import dayjs from "dayjs";
import { saveAs } from "file-saver";

export const app = reactive({
    themeStyle: 'light',
    fontColor: 'black',
    algorithm: theme.defaultAlgorithm,
    isDesktopApp: !!window.electron,
    isBrowserApp: !window.electron,
    initBackgroundColor() {
        document.body.style.backgroundColor = 'white';
    },
    changeTheme(checked) {
        this.themeStyle = checked ? 'dark' : 'light';
        this.algorithm = checked ? theme.darkAlgorithm : theme.defaultAlgorithm;
        this.fontColor = checked ? 'white' : 'black';
        document.body.style.backgroundColor = checked ? 'black' : 'white';
        this.isDesktopApp && window.electron.toggle();
    },
    isDarkTheme() {
        return this.themeStyle === 'dark';
    },
    today() {
        return dayjs().format('YYYY年MM月DD日, dddd');
    }
});

export const download = reactive({
    // 下载进度
    progress: 0,
    // 是否正在下载
    isDownloading: false,
    // 下载状态
    status: '',
    // 当前正在下载的文件名
    currentFile: '',
    init() {
        if (app.isBrowserApp) {
            console.warn('浏览器模式不存在 electron 对象。');
            return;
        }
        // 监听下载进度
        window.electron.onDownloadProgress((event, newProgress, filename) => {
            this.isDownloading = true;
            this.progress = newProgress;
            this.status = 'active';
            this.currentFile = filename;
        });
        // 监听下载完成
        window.electron.onDownloadComplete((event, savePath) => {
            this.status = 'success';
            message.success(`下载完成! 文件保存至[ ${savePath} ]`);
        });
        // 监听下载失败
        window.electron.onDownloadFailed((event) => {
            this.status = 'exception';
            message.error('下载失败!');
        });
    },
    downloadFile(href) {
        const link = document.createElement('a');
        link.href = href;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    },
    downloadJson(content, prefix) {
        const data = JSON.stringify(content, null, 2);
        const blob = new Blob([data], { type: "text/plain;charset=utf-8" });
        const now = dayjs().format('YYYYMMDDHHmmss');
        saveAs(blob, `${prefix}-${now}.json`);
    }
});
