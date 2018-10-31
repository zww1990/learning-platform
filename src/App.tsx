import React from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import { Layout, Menu, Icon, Switch } from 'antd';
import './App.less';
import Page1 from './components/Page1';
import Home from './components/Home';
import Page2 from './components/Page2';

class App extends React.Component {
  rootSubmenuKeys = ['sub1', 'sub2', 'sub4'];
  state = {
    collapsed: false,
    openKeys: new Array<string>(),
    theme: true
  };
  toggle = () => this.setState({ collapsed: !this.state.collapsed });
  onOpenChange = (openKeys: any) => {
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
  currentTheme = () => (this.state.theme ? 'dark' : 'light');
  changeTheme = (value: boolean) => this.setState({ theme: value });
  render() {
    return (
      <Router>
        <Layout>
          <Layout.Sider
            style={{ overflow: 'auto' }}
            trigger={null}
            collapsible={true}
            collapsed={this.state.collapsed}
            theme={this.currentTheme()}
          >
            <div className="logo" />
            <Menu
              theme={this.currentTheme()}
              mode="inline"
              openKeys={this.state.openKeys}
              onOpenChange={this.onOpenChange}
              inlineCollapsed={this.state.collapsed}
            >
              <Menu.SubMenu
                key="sub1"
                title={
                  <span>
                    <Icon type="mail" />
                    <span>导航1</span>
                  </span>
                }
              >
                <Menu.Item key="1">
                  <Link to="/page1">菜单 1</Link>
                </Menu.Item>
                <Menu.Item key="2">
                  <Link to="/page2">菜单 2</Link>
                </Menu.Item>
                <Menu.Item key="3">菜单 3</Menu.Item>
                <Menu.Item key="4">菜单 4</Menu.Item>
              </Menu.SubMenu>
              <Menu.SubMenu
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
                <Menu.SubMenu key="sub3" title="子菜单">
                  <Menu.Item key="7">菜单 7</Menu.Item>
                  <Menu.Item key="8">菜单 8</Menu.Item>
                </Menu.SubMenu>
              </Menu.SubMenu>
              <Menu.SubMenu
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
                <Menu.Item key="13">菜单 12</Menu.Item>
                <Menu.Item key="14">菜单 12</Menu.Item>
                <Menu.Item key="15">菜单 12</Menu.Item>
                <Menu.Item key="16">菜单 12</Menu.Item>
                <Menu.Item key="17">菜单 12</Menu.Item>
                <Menu.Item key="18">菜单 12</Menu.Item>
                <Menu.Item key="19">菜单 12</Menu.Item>
                <Menu.Item key="20">菜单 12</Menu.Item>
                <Menu.Item key="21">菜单 12</Menu.Item>
              </Menu.SubMenu>
            </Menu>
          </Layout.Sider>
          <Layout>
            <Layout.Header style={{ background: '#fff', padding: 0 }}>
              <Icon
                className="trigger"
                type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
                onClick={this.toggle}
              />
              <Switch
                checked={this.state.theme}
                onChange={this.changeTheme}
                checkedChildren="Dark"
                unCheckedChildren="Light"
              />
            </Layout.Header>
            <Layout.Content
              style={{
                margin: '10px',
                padding: 24,
                background: '#fff',
                overflow: 'auto'
              }}
            >
              <Route path="/" component={Home} exact={true} />
              <Route path="/page1" component={Page1} />
              <Route path="/page2" component={Page2} />
            </Layout.Content>
          </Layout>
        </Layout>
      </Router>
    );
  }
}

export default App;
