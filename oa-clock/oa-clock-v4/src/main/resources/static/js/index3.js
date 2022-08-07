new Vue({
    el: '#app',
    vuetify: new Vuetify({
        theme: {
            dark: true
        },
    }),
    data: {
        addresses: [
        	{ id: 1, address: '北京海淀' },
        	{ id: 2, address: '河北石家庄' },
        ],
    	selected: [],
        headers: [
          { text: 'Dessert (100g serving)', value: 'name' },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
        ],
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: '1%',
            addr: { id: 1, address: '北京海淀' }
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: '1%',
            addr: { id: 2, address: '河北石家庄' }
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: '7%',
            addr: { id: 2, address: '河北石家庄' }
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: '8%',
            addr: { id: 2, address: '河北石家庄' }
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: '16%',
            addr: { id: 1, address: '北京海淀' }
          },
        ],
    },
    computed: {},
    mounted() {
        console.log('hello world');
    },
    methods: {
    	showSelected(){
    		console.log(this.selected);
    	},
    }
});
