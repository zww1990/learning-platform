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

module.exports = proxy;
