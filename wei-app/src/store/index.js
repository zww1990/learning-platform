import { reactive } from "vue";
import { theme } from "ant-design-vue";
import dayjs from "dayjs";

export const app = reactive({
    themeStyle: 'dark',
    fontColor: 'white',
    algorithm: theme.darkAlgorithm,
    initBackgroundColor() {
        document.body.style.backgroundColor = 'black';
    },
    changeTheme(checked) {
        this.themeStyle = checked ? 'dark' : 'light';
        this.algorithm = checked ? theme.darkAlgorithm : theme.defaultAlgorithm;
        this.fontColor = checked ? 'white' : 'black';
        document.body.style.backgroundColor = checked ? 'black' : 'white';
        window.electron.toggle();
    },
    isDarkTheme() {
        return this.themeStyle === 'dark';
    },
    today() {
        return dayjs().format('YYYY年MM月DD日, dddd');
    }
})
