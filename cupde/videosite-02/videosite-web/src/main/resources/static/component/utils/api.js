const categoryAddApi = params => fetch('/category/add', {
  method: 'POST',
  body: JSON.stringify(params),
  headers: { 'Content-Type': 'application/json' }
})

const loginApi = params => fetch('/user/login', {
  method: 'POST',
  body: JSON.stringify(params),
  headers: { 'Content-Type': 'application/json' }
})

const registerApi = params => fetch('/user/register', {
  method: 'POST',
  body: JSON.stringify(params),
  headers: { 'Content-Type': 'application/json' }
})

const logoutApi = () => fetch('/user/logout')

export { loginApi, logoutApi, registerApi, categoryAddApi }
