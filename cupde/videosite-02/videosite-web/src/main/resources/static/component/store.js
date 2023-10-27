const { ref, reactive } = Vue

export const store = reactive({
  user: JSON.parse(localStorage.getItem('CURRENT_USER')),

  setUser(currentUser) {
    this.user = currentUser
    localStorage.setItem('CURRENT_USER', JSON.stringify(currentUser))
  }
})
