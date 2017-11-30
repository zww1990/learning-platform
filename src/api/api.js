import axios from 'axios';

let base = '/static/data';

export const loadMenuData = params => {
  return axios.get(`${base}/menudata.json`)
}

export const requestLogin = params => {
  return axios.get(`${base}/login.json`)
};

export const getUserList = params => {
  return axios.get(`${base}/list.json`);
};

export const getUserListPage = params => {
  return axios.get(`${base}/listpage.json`);
};

export const removeUser = params => {
  return axios.get(`${base}/user.json`);
};

export const batchRemoveUser = params => {
  return axios.get(`${base}/user.json`);
};

export const editUser = params => {
  return axios.get(`${base}/user.json`);
};

export const addUser = params => {
  return axios.get(`${base}/user.json`);
};

export const loadTableData = params => {
  return axios.get(`${base}/tabledata.json`)
}

export const loadChildTableData = params => {
  return axios.get(`${base}/tabledata-${params}.json`)
}