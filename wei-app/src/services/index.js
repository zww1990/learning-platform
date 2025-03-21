import axios from "axios";
import { message } from "ant-design-vue";

// 添加响应拦截器
axios.interceptors.response.use(res => {
    return res;
}, err => {
    message.error(err.message).then(() => {});
    return Promise.reject(err);
});

export function getProductsReleasesByCode(code) {
    const url = `https://data.services.jetbrains.com/products/releases?code=${code}&latest=true&type=release,preview`;
    return axios.get(url);
}

export function getProductsReleasesByCodeAndType(code, type) {
    const url = `https://data.services.jetbrains.com/products/releases?code=${code}&type=${type}`;
    return axios.get(url);
}
