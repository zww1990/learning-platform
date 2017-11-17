import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      components: {
        default:()=>import('@/components/HelloWorld'),
        a:()=>import('@/components/FooComponent'),
        b:()=>import('@/components/BarComponent')
      }
    },
    {
      path:'/foo',
      redirect:'/bar'
    },
    {
      path:'/foo',
      component:()=>import('@/components/FooComponent')
    },
    {
      path:'/bar',
      component:()=>import('@/components/BarComponent')
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
      component:()=>import('@/components/User'),
      props:true,
      meta:{
        requiresAuth:true
      },
      children:[
        {
          path:'',
          component:()=>import('@/components/UserHome')
        },
        {
          path:'profile',
          component:()=>import('@/components/UserProfile')
        },
        {
          path:'posts',
          component:()=>import('@/components/UserPosts')
        }
      ]
    },
    {
      path:'/login',
      component:()=>import('@/components/LoginPage')
    },
    {
      path:'/post',
      component:()=>import('@/components/Post')
    },
    {
      path:'/post2',
      component:()=>import('@/components/Post2')
    },
    {
      path:'/counter',
      component:()=>import('@/components/Counter')
    }
  ]
})
