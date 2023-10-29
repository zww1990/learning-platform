const categoryAddApi = params => fetch('/category/add', {
  method: 'POST',
  body: JSON.stringify(params),
  headers: { 'Content-Type': 'application/json' }
})

const categoryListApi = async () => await (await fetch('/category/list')).json()

const commentAddApi = params => fetch('/comment/add', {
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

const homeApi = async (id) => {
  let url = '/home'
  if(id !== undefined && id !== null){
    const params = new URLSearchParams()
    params.append('categoryId', id)
    url = `${url}?${params.toString()}`
  }
  return await (await fetch(url)).json()
}

const videoDelApi = params => fetch(`/videohub/delete/${params.id}`, {
  method: 'DELETE'
})

const videoShowApi = id => fetch(`/videohub/show/${id}`)

const videoListApi = () => fetch('/videohub/list')

const videoAddApi = params => {
  const formData = new FormData()
  Object.entries(params).forEach(([ k, v ]) => formData.append(k, v))
  return fetch('/videohub/add', {
    method: 'POST',
    body: formData,
  })
}

export {
  loginApi,
  logoutApi,
  registerApi,
  categoryAddApi,
  categoryListApi,
  homeApi,
  commentAddApi,
  videoDelApi,
  videoShowApi,
  videoListApi,
  videoAddApi,
}
