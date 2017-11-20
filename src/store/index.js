import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store=new Vuex.Store({
    state:{
        count:0,
        todos:[
            {
                id:1,
                text:'...',
                done:true
            },
            {
                id:2,
                text:'...',
                done:false
            }
        ]
    },
    mutations:{//mutation 必须是同步函数。
        increment(state,payload){
            state.count++
        }
    },
    actions:{//action 提交的是 mutation，而不是直接变更状态。action 可以包含任意异步操作。
        increment({commit}){
            commit('increment')
        },
        incrementAsync({commit}){
            setTimeout(()=>{
                commit('increment')
            },1000)
        }
    },
    getters:{
        doneTodos:state=>{
            return state.todos.filter(todo=>todo.done)
        },
        doneTodosCount:(state,getters)=>{
            return getters.doneTodos.length
        }
    }
})

export default store