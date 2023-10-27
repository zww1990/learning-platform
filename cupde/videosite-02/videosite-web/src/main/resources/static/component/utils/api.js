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

function homeApi() {
  let url = '/home'
  if(arguments.length > 0){
    const params = new URLSearchParams()
    params.append('categoryId', arguments[0])
    url = `${url}?${params.toString()}`
  }
  return fetch(url)
}

export { loginApi, logoutApi, registerApi, categoryAddApi, homeApi }
