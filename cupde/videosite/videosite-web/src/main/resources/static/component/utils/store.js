import { logoutApi } from './api.js'

const { ref, reactive } = Vue

export const store = reactive({
  user: JSON.parse(sessionStorage.getItem('CURRENT_USER')),

  setUser(currentUser) {
    this.user = currentUser
    sessionStorage.setItem('CURRENT_USER', JSON.stringify(currentUser))
  },

  clearUser() {
    this.user = null
    sessionStorage.removeItem('CURRENT_USER')
    logoutApi()
      .then(res => res.text())
      .then(res => console.log(res))
  }
})
