<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <span :title="message">鼠标悬停几秒钟查看此处动态绑定的提示信息！</span>
    <p v-if="seen">现在你看到我了</p>
    <p v-else>我是else</p>
    <ul>
      <li v-for="(todo,index) in todos" :key="todo.id">{{index}} - {{todo.title}}</li>
    </ul>
    <button @click="reverseMessage">逆转消息</button>
    <input v-model="msg">
    <div>
      <input v-model="newTodoText" @keyup.enter="addNewTodo" placeholder="请输入内容">
      <ol>
        <li 
        is="todo-item" 
        v-for="(item,index) in groceryList" 
        :key="item.id" 
        :title="item.title" 
        @remove="groceryList.splice(index,1)"></li>
      </ol>
    </div>
    <span v-once>这个将不会改变:{{msg}}</span>
    <div>{{rawHtml}}</div>
    <div v-html="rawHtml"></div>
    <div v-show="isShow">isShow:{{isShow}}</div>
    <ul>
      <li v-for="(value,key,index) in user" :key="key">{{index}}.{{key}}:{{value}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      message:'页面加载于 '+new Date().toLocaleString(),
      seen:false,
      newTodoText:'',
      todos:[
        { id:1,title: '学习 JavaScript' },
        { id:2,title: '学习 Vue' },
        { id:3,title: '整个牛项目' }
      ],
      nextTodoId:4,
      groceryList:[
        { id: 1, title: '蔬菜' },
        { id: 2, title: '奶酪' },
        { id: 3, title: '随便其他什么人吃的东西' }
      ],
      rawHtml:'<button>我是一个按钮</button>',
      isShow:false,
      user:{
        firstName:'zhang',
        lastName:'san',
        age:23
      }
    }
  },
  methods:{
    reverseMessage(event){
      console.log(JSON.stringify(event))
      this.msg=this.msg.split('').reverse().join('')
    },
    addNewTodo(){
      if (!this.newTodoText) {
        return
      }
      this.groceryList.push({
        id:this.nextTodoId++,
        title:this.newTodoText
      })
      this.newTodoText=''
    }
  },
  computed:{
    reverse:{
      get(){
        return this.msg.split('').reverse().join('')
      },
      set(value){
        this.msg=value
      }
    }
  },
  created(){
    console.log('created')
  },
  beforeCreate(){
    console.log('beforeCreate')
  },
  destroyed(){
    console.log('destroyed')
  },
  beforeDestroy(){
    console.log('beforeDestroy')
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
