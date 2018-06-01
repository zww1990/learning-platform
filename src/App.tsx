import * as React from 'react';
import { Layout, Menu, Icon } from 'antd';
import './App.css';

const { Header, Sider, Content } = Layout;
const SubMenu = Menu.SubMenu;

class App extends React.Component {
  public rootSubmenuKeys = ['sub1', 'sub2', 'sub4'];
  public state = {
    collapsed: false,
    openKeys: new Array<string>()
  };

  public toggle = () => {
    this.setState({
      collapsed: !this.state.collapsed
    });
  };
  public onOpenChange = (openKeys: any) => {
    const latestOpenKey = openKeys.find(
      (key: any) => this.state.openKeys.indexOf(key) === -1
    );
    if (this.rootSubmenuKeys.indexOf(latestOpenKey) === -1) {
      this.setState({ openKeys });
    } else {
      this.setState({
        openKeys: latestOpenKey ? [latestOpenKey] : []
      });
    }
  };

  public render() {
    return (
      <Layout>
        <Sider
          trigger={null}
          collapsible={true}
          collapsed={this.state.collapsed}
        >
          <div className="logo" />
          <Menu
            theme="dark"
            mode="inline"
            openKeys={this.state.openKeys}
            onOpenChange={this.onOpenChange}
          >
            <SubMenu
              key="sub1"
              title={
                <span>
                  <Icon type="mail" />
                  <span>导航1</span>
                </span>
              }
            >
              <Menu.Item key="1">菜单 1</Menu.Item>
              <Menu.Item key="2">菜单 2</Menu.Item>
              <Menu.Item key="3">菜单 3</Menu.Item>
              <Menu.Item key="4">菜单 4</Menu.Item>
            </SubMenu>
            <SubMenu
              key="sub2"
              title={
                <span>
                  <Icon type="appstore" />
                  <span>导航2</span>
                </span>
              }
            >
              <Menu.Item key="5">菜单 5</Menu.Item>
              <Menu.Item key="6">菜单 6</Menu.Item>
              <SubMenu key="sub3" title="子菜单">
                <Menu.Item key="7">菜单 7</Menu.Item>
                <Menu.Item key="8">菜单 8</Menu.Item>
              </SubMenu>
            </SubMenu>
            <SubMenu
              key="sub4"
              title={
                <span>
                  <Icon type="setting" />
                  <span>导航3</span>
                </span>
              }
            >
              <Menu.Item key="9">菜单 9</Menu.Item>
              <Menu.Item key="10">菜单 10</Menu.Item>
              <Menu.Item key="11">菜单 11</Menu.Item>
              <Menu.Item key="12">菜单 12</Menu.Item>
            </SubMenu>
          </Menu>
        </Sider>
        <Layout>
          <Header style={{ background: '#fff', padding: 0 }}>
            <Icon
              className="trigger"
              type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
              onClick={this.toggle}
            />
          </Header>
          <Content
            style={{
              margin: '10px',
              padding: 24,
              background: '#fff',
              minHeight: 280
            }}
          >
            欢迎使用我的APP
          </Content>
        </Layout>
      </Layout>
    );
  }
}

export default App;
