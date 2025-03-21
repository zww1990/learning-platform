import axios from "axios";
import { message } from "ant-design-vue";

// 添加响应拦截器
axios.interceptors.response.use(res => {
    return res;
}, err => {
    message.error(err.message).then(() => {});
    return Promise.reject(err);
});

// 获取jetbrains开发者工具最新版本接口
export function getProductsReleasesByCode(code) {
    const url = `https://data.services.jetbrains.com/products/releases?code=${code}&latest=true&type=release,preview`;
    return axios.get(url);
}

// 获取jetbrains开发者工具其他版本接口
export function getProductsReleasesByCodeAndType(code, type) {
    const url = `https://data.services.jetbrains.com/products/releases?code=${code}&type=${type}`;
    return axios.get(url);
}

// 微软在线翻译接口
export function postTranslateText(data) {
    const url = 'https://edge.microsoft.com/translate/translatetext?from=en&to=zh-Hans';
    return axios.post(url, data);
}
