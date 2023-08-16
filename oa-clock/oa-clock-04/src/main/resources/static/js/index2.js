new Vue({
    el: '#app',
    vuetify: new Vuetify({
        theme: {
            dark: true
        },
    }),
    data: {
        user: {
            menu: null,
            clockTime: null,
            tabs: null,
            date: null,
            time: null,
        }
    },
    computed: {},
    mounted() {
        console.log('hello world');
    },
    methods: {
        clickDate(user) {
            if (user.time == null) {
                user.time = new Date().toLocaleTimeString();
            }
            user.clockTime = `${user.date} ${user.time}`;
        },
        clickSecond(user) {
            if (user.date == null) {
                user.date = new Date().toJSON().split('T')[0];
            }
            user.clockTime = `${user.date} ${user.time}`;
        },
    }
});
