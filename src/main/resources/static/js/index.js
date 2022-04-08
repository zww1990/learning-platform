new Vue({
	el:'#app',
	vuetify: new Vuetify({
		theme: { dark: true },
	}),
	data:{
		users: [],
		user: {
			userNo: null,
			username: null,
			dates: null,
			daterange: null,
			rangemenu: null, 
			staffClock: null,
		},
		addresses: [],
		addr: {
			id: null,
			address: null,
			longitude: null,
			latitude: null,
		},
		validation: {
			longitudeRules: [v => !!v || '经度必须填'],
			latitudeRules: [v => !!v || '纬度必须填'],
			addressRules: [v => !!v || '地址必须填'],
			usernoRules: [v => !!v || '员工编号必须填'],
			usernameRules: [v => !!v || '员工姓名必须填'],
		},
		logList: [],
		logDialog: false,
		userDialog: false,
		addrDialog: false,
		deviceList: [],
		deviceDialog: false,
	},
	computed:{
		style(){
			if(this.$vuetify.breakpoint.name === 'xs'){
				return 'width:250px';
			}
		}
	},
	async mounted(){
		let json1 = await (await fetch('/hello/addresses')).json();
		this.addresses = json1.data;
		let json2 = await (await fetch('/hello/users')).json();
		this.users = json2.data;
		let addr = this.addresses[0];
		this.users.forEach(user => {
			fetch('/hello/initstaffclock',{
				  method: 'POST',
				  body: JSON.stringify({ ...user, ...addr }),
				  headers: {'Content-Type': 'application/json'}
			})
			.then(res => res.json())
			.then(res => {
				user.status = res.status;
				user.message = res.message;
				user.staffClock = res.data;
				user.addr = addr;
			});
		});
	},
	methods: {
		rangeChange(){
			let tmp = this.user.dates;
			if(Date.parse(tmp[0]) > Date.parse(tmp[1])){
				this.user.dates = [tmp[1], tmp[0]];
			}
			this.user.daterange = this.user.dates.join(' ~ ');
		},
		clickDate(user){
			if(user.time == null){
				user.time = new Date().toLocaleTimeString();
			}
			user.clockTime = `${user.date} ${user.time}`;
		},
		clickSecond(user){
			if(user.date == null){
				user.date = new Date().toJSON().split('T')[0];
			}
			user.clockTime = `${user.date} ${user.time}`;
		},
		repairWork(user){
		  if (user.clockTime == null || user.clockTime.length === 0) {
			this.$toast.error('请输入日期', {x: 'center', y: 'top'});
	        return;
	      }
			fetch('/hello/v2/userloginandstaffclock', {
				  method: 'POST',
				  body: JSON.stringify({ ...user, ...user.addr }),
				  headers: {'Content-Type': 'application/json'}
			})
			.then(res => res.json())
			.then(res => {
				if (res.status === 0) {
					this.$toast.error(res.message, {x: 'center', y: 'top'});
				} else {
					this.$toast.success('补卡成功', {x: 'center', y: 'top'});
				}
			});
		},
		gotoWork(user){
			fetch('/hello/userloginandstaffclock', {
				  method: 'POST',
				  body: JSON.stringify({ ...user, ...user.addr }),
				  headers: {'Content-Type': 'application/json'}
			})
			.then(res => res.json())
			.then(res => {
				user.status = res.status;
				user.message = res.message;
				user.staffClock = res.data;
			});
		},
		saveUser(){
			if(this.$refs.userForm.validate()){
				this.users.push({ addr: this.addresses[0], status: 1, ...this.user });
				this.closeUser();
			}
		},
		openUser(){
			this.userDialog = true;
		},
		closeUser(){
			this.userDialog = false;
	        this.$refs.userForm.reset();
		},
		openAddr(){
			this.addrDialog = true;
		},
		closeAddr(){
			this.addrDialog = false;
	        this.$refs.addrForm.reset();
		},
		saveAddr() {
			if(this.$refs.addrForm.validate()){
				this.addr.id = this.addresses.length + 1;
				fetch('/hello/saveaddress',{
					  method: 'POST',
					  body: JSON.stringify(this.addr),
					  headers: {'Content-Type': 'application/json'}
				})
				.then(res => res.json())
				.then(res => {
					console.log(res);
				});
				this.addresses.push({ ...this.addr });
				this.closeAddr();
			}
        },
		showLogList(user){
			fetch('/hello/selectappstaffclockloglist',{
				  method: 'POST',
				  body: JSON.stringify(user),
				  headers: {'Content-Type': 'application/json'}
			})
			.then(res => res.json())
			.then(res => {
				this.logList = res.data || [];
				this.user = { ...user };
				this.logDialog = true;
			});
		},
		logDialogClose(){
			this.logDialog = false;
			this.user = { dates: null, daterange: null, rangemenu: null };
		},
		showDeviceList(user){
			fetch('/hello/selectdevicelist',{
				  method: 'POST',
				  body: JSON.stringify(user),
				  headers: {'Content-Type': 'application/json'}
			})
			.then(res => res.json())
			.then(res => {
				this.deviceList = res.data.items || [];
				this.user = { ...user };
				this.deviceDialog = true;
			});
		},
		resetBindDevice(dev){
			let formData = `staffNo=${dev.staffNo}&id=${dev.id}`;
			fetch('/hello/resetbinddevice',{
				  method: 'POST',
				  body: formData,
				  headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			})
			.then(res => res.json())
			.then(res => {
				this.deviceList = res.data.items || [];
			});
		}
    }
});
