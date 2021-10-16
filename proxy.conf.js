const chalk = require('chalk');
const proxy = {
  '/hello': {
    target: 'http://192.168.34.156:8080/',
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
