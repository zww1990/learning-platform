const chalk = require('chalk');
const proxy = {
  '/cas': {
    target: 'http://sso.bacic5i5j.com/',
    pathRewrite: {
      '^/cas': ''
    },
    secure: false
  }
};

if (Object.keys(proxy).length > 0) {
  console.log(chalk.cyanBright('API代理'));
  for (const e in proxy) {
    console.log(chalk.magentaBright(`${e} ==>> ${proxy[e].target}`));
  }
  console.log();
}

/**
 * @author zww
 * @description API代理配置
 */
module.exports = proxy;
