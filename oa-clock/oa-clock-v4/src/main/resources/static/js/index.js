new Vue({
    el: '#app',
    vuetify: new Vuetify({
        theme: { dark: true },
    }),
    data: {
    	selected: [],
    	cronExpression: null,
    	headers: [
    		{ text: '编号', value: 'a' },
    		{ text: '姓名', value: 'username' },
    		{ text: '地址', value: 'b' },
    		{ text: '上班', value: 'c' },
    		{ text: '下班', value: 'd' },
    		{ text: '日期', value: 'e' },
    		{ text: '操作', value: 'f' },
    	],
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
            cronRules: [v => !!v || 'cron表达式必须填'],
        },
        logList: [],
        logDialog: false,
        userDialog: false,
        addrDialog: false,
        jobDialog: false,
        deviceList: [],
        deviceDialog: false,
        snackbar: {
            value: false,
            text: '你好，世界！',
            timeout: 3000,
            color: 'info',
        },
        minDate: '2020-01-01',
        maxDate: `${new Date().getFullYear()}-12-31`,
    },
    computed: {
        style() {
            if (this.$vuetify.breakpoint.name === 'xs') {
                return 'width:250px';
            }
        }
    },
    async mounted() {
        let json1 = await(await fetch('/hello/addresses')).json();
        this.addresses = json1.data;
        let json2 = await(await fetch('/hello/users')).json();
        this.users = json2.data;
        this.users.forEach(user => this.initStaffClockV1(user));
    },
    methods: {
		initStaffClockV1(user) {
        	let addr = this.addresses[0];
            fetch('/hello/initstaffclock', {
                method: 'POST',
                body: JSON.stringify({ ...user }),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                user.status = res.status;
                user.message = res.message;
                user.staffClock = res.data;
                user.addr = addr;
            });
		},
		initStaffClockV2(user) {
            fetch('/hello/initstaffclock', {
                method: 'POST',
                body: JSON.stringify({ ...user }),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                user.status = res.status;
                user.message = res.message;
                user.staffClock = res.data;
            });
		},
        rangeChange() {
            let tmp = this.user.dates;
            if (Date.parse(tmp[0]) > Date.parse(tmp[1])) {
                this.user.dates = [tmp[1], tmp[0]];
            }
            this.user.daterange = this.user.dates.join(' ~ ');
        },
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
        repairWork(user) {
            if (user.clockTime == null || user.clockTime.length === 0) {
                this.snackbar.value = true;
                this.snackbar.text = `[ ${user.userNo} - ${user.username} ] 请输入日期`;
                this.snackbar.color = 'error';
                return;
            }
            fetch('/hello/v2/userloginandstaffclock', {
                method: 'POST',
                body: JSON.stringify({
                    ...user,
                    ...user.addr
                }),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                this.snackbar.value = true;
                if (res.status === 0) {
                    this.snackbar.text = res.message;
                    this.snackbar.color = 'error';
                } else {
                    this.snackbar.text = `[ ${user.userNo} - ${user.username} ] 补卡成功`;
                    this.snackbar.color = 'success';
                    this.initStaffClockV2(user);
                }
            });
        },
        gotoWork(user) {
            fetch('/hello/v1/userloginandstaffclock', {
                method: 'POST',
                body: JSON.stringify({
                    ...user,
                    ...user.addr
                }),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                user.status = res.status;
                user.message = res.message;
                user.staffClock = res.data;
            });
        },
        saveUser() {
            if (this.$refs.userForm.validate()) {
                this.users.push({
                    addr: this.addresses[0],
                    status: 1,
                    ...this.user
                });
                this.closeUser();
            }
        },
        openUser() {
            this.userDialog = true;
        },
        closeUser() {
            this.userDialog = false;
            this.$refs.userForm.reset();
        },
        openAddr() {
            this.addrDialog = true;
        },
        closeAddr() {
            this.addrDialog = false;
            this.$refs.addrForm.reset();
        },
        openJob() {
        	if (this.selected.length === 0) {
        		this.snackbar.value = true;
                this.snackbar.text = `至少选择一个员工！`;
                this.snackbar.color = 'error';
                return;
        	}
        	this.jobDialog = true;
        },
        closeJob() {
        	this.jobDialog = false;
            this.$refs.jobForm.reset();
        },
        saveJob() {
            if (this.$refs.jobForm.validate()) {
            	fetch('/hello/job', {
                    method: 'POST',
                    body: JSON.stringify({
                    	cronExpression: this.cronExpression,
                    	users: this.selected.map(item => {
	            			return { ...item, ...item.addr };
	            		})
                    }),
                    headers: { 'Content-Type': 'application/json' }
                })
                .then(res => res.json())
                .then(res => {
	                this.snackbar.value = true;
	                this.snackbar.text = res.message;
	                if (res.status === 1) {
	                    this.snackbar.color = 'success';
	                } else {
	                    this.snackbar.color = 'error';
	                }
                });
            }
        },
        saveAddr() {
            if (this.$refs.addrForm.validate()) {
                this.addr.id = this.addresses.length + 1;
                fetch('/hello/saveaddress', {
                    method: 'POST',
                    body: JSON.stringify(this.addr),
                    headers: { 'Content-Type': 'application/json' }
                })
                .then(res => res.json())
                .then(res => console.log(res));
                this.addresses.push({ ...this.addr });
                this.closeAddr();
            }
        },
        showLogList(user) {
            fetch('/hello/selectappstaffclockloglist', {
                method: 'POST',
                body: JSON.stringify(user),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                this.logList = res.data || [];
                this.user = { ...user };
                this.logDialog = true;
            });
        },
        logDialogClose() {
            this.logDialog = false;
            this.user = {
                dates: null,
                daterange: null,
                rangemenu: null
            };
        },
        showDeviceList(user) {
            fetch('/hello/selectdevicelist', {
                method: 'POST',
                body: JSON.stringify(user),
                headers: { 'Content-Type': 'application/json' }
            })
            .then(res => res.json())
            .then(res => {
                this.deviceList = res.data.items || [];
                this.user = { ...user };
                this.deviceDialog = true;
            });
        },
        resetBindDevice(dev) {
            let formData = `staffNo=${dev.staffNo}&id=${dev.id}`;
            fetch('/hello/resetbinddevice', {
                method: 'POST',
                body: formData,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
            })
            .then(res => res.json())
            .then(res => {
                this.deviceList = res.data.items || [];
            });
        }
    }
});
