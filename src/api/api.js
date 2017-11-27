import axios from 'axios';

let base = '/static/data';

export const requestLogin = params => {
  return axios.get(`${base}/login.json`, params).then(res => res.data);
};

export const getUserList = params => {
  return axios.get(`${base}/list.json`, { params: params });
};

export const getUserListPage = params => {
  return axios.get(`${base}/listpage.json`, { params: params });
};

export const removeUser = params => {
  return axios.get(`${base}/user.json`, { params: params });
};

export const batchRemoveUser = params => {
  return axios.get(`${base}/user.json`, { params: params });
};

export const editUser = params => {
  return axios.get(`${base}/user.json`, { params: params });
};

export const addUser = params => {
  return axios.get(`${base}/user.json`, { params: params });
};