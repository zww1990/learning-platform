import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

const Foo={
  template:'<div>我是foo</div>'
}
const Bar={
  template:'<div>我是bar</div>'
}
const User={
  template:`
            <div class="user">
              <h2>我是:{{id}}</h2>
              <router-view></router-view>
            </div>
  `,
  props:['id']
}
const UserProfile={
  template:`<h1>UserProfile</h1>`
}
const UserPosts={
  template:`<h1>UserPosts</h1>`
}
const UserHome={
  template:`<h1>UserHome</h1>`
}

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'Hello',
      components: {
        default:HelloWorld,
        a:Foo,
        b:Bar
      }
    },
    {
      path:'/foo',
      redirect:'/bar'
    },
    {
      path:'/foo',
      component:Foo
    },
    {
      path:'/bar',
      component:Bar
    },
    {
      path:'/abc',
      beforeEnter:(to,from,next)=>{
        alert('禁止访问')
        next(false)
      }
    },
    {
      path:'/user/:id',
      component:User,
      props:true,
      children:[
        {
          path:'',
          component:UserHome
        },
        {
          path:'profile',
          component:UserProfile
        },
        {
          path:'posts',
          component:UserPosts
        }
      ]
    }
  ]
})
