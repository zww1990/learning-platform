const { ref } = Vue
const { useTheme } = Vuetify

export default {
  setup() {
    const theme = useTheme()

    const toggleTheme = () => {
      theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
    }

    return { toggleTheme, theme }
  },
  template: `
    <v-app id="inspire">
     <v-app-bar extended>
       <v-app-bar-nav-icon></v-app-bar-nav-icon>
       <v-app-bar-title>Application</v-app-bar-title>
       <v-spacer></v-spacer>
       <v-btn
           :icon="theme.global.current.value.dark ? 'mdi-weather-night' : 'mdi-weather-sunny'"
           @click="toggleTheme">
       </v-btn>
       <v-btn icon="mdi-dots-vertical"></v-btn>
     </v-app-bar>

     <v-main>
       <v-container>
         <v-row>
           <v-col v-for="n in 24" :key="n" cols="4">
             <v-card height="200"></v-card>
           </v-col>
         </v-row>
         <v-row>
           <v-col cols="4">
             <v-date-picker
                 cancel-text="取消"
                 header="请输入日期"
                 input-mode="keyboard"
                 input-placeholder="yyyy-mm-dd"
                 input-text="请输入日期"
                 ok-text="确定"
                 title="请选择日期">
             </v-date-picker>
           </v-col>
         </v-row>
       </v-container>
     </v-main>
    </v-app>
  `
}
