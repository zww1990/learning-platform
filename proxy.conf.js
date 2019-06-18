const chalk = require('chalk');
const proxy = {
  '/cas': {
    target: 'http://sso.bacic5i5j.com/',
    secure: false,
    changeOrigin: true,
    pathRewrite: {
      '^/cas': ''
    }
  }
};

if (Object.keys(proxy).length > 0) {
  console.log(chalk.cyanBright('API代理'));
  for (const e in proxy) {
    console.log(chalk.magentaBright(`${e} ==>> ${proxy[e].target}`));
  }
  console.log();
}

const notifier = require('node-notifier');
notifier.notify({ title: '欢迎来到大前端', message: '大家一起来学习Angular哟！😄😄', icon: './src/assets/icons/icon-512x512.png' });

/**
 * @author zww
 * @description API代理配置
 */
module.exports = proxy;
