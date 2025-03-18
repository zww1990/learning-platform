import { reactive } from "vue";
import { theme } from "ant-design-vue";

export const store = reactive({
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
    }
})
