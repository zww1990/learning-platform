export default {
  data() {
    return {
      visible: false
    }
  },
  methods: {
    show() {
      this.visible = true;
    }
  },
  template: `
    <div class="layout">
        <Layout>
            <Header>
                <Menu mode="horizontal" theme="dark" active-name="1">
                    <div class="layout-logo"></div>
                    <div class="layout-nav">
                        <MenuItem name="1">
                            <Icon type="ios-navigate"></Icon>
                            Item 1
                        </MenuItem>
                        <MenuItem name="2">
                            <Icon type="ios-keypad"></Icon>
                            Item 2
                        </MenuItem>
                        <MenuItem name="3">
                            <Icon type="ios-analytics"></Icon>
                            Item 3
                        </MenuItem>
                        <MenuItem name="4">
                            <Icon type="ios-paper"></Icon>
                            Item 4
                        </MenuItem>
                    </div>
                </Menu>
            </Header>
            <Content :style="{padding: '0 50px'}">
                <Breadcrumb :style="{margin: '20px 0'}">
                    <BreadcrumbItem>Home</BreadcrumbItem>
                    <BreadcrumbItem>Components</BreadcrumbItem>
                    <BreadcrumbItem>Layout</BreadcrumbItem>
                </Breadcrumb>
                <Card>
                    <div style="min-height: 200px;">
                        <DatePicker type="datetime" placeholder="请输入日期和时间" />
                        <i-button @click="show">Click me!</i-button>
                        <Modal v-model="visible" title="Welcome">Welcome to View UI Plus</Modal>
                    </div>
                </Card>
            </Content>
            <Footer class="layout-footer-center">2011-2016 &copy; View Design</Footer>
        </Layout>
    </div>
  `
}
