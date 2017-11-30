import axios from 'axios';

let base = '/static/data';

export const loadMenuData = params => {
  return axios.get(`${base}/menudata.json`, { params })
}

export const requestLogin = params => {
  return axios.get(`${base}/login.json`, { params })
};

export const getUserList = params => {
  return axios.get(`${base}/list.json`, { params });
};

export const getUserListPage = params => {
  return axios.get(`${base}/listpage.json`, { params });
};

export const removeUser = params => {
  return axios.get(`${base}/user.json`, { params });
};

export const batchRemoveUser = params => {
  return axios.get(`${base}/user.json`, { params });
};

export const editUser = params => {
  return axios.get(`${base}/user.json`, { params });
};

export const addUser = params => {
  return axios.get(`${base}/user.json`, { params });
};