import { app, BrowserWindow, ipcMain, nativeTheme } from 'electron';
import path from 'node:path';
import started from 'electron-squirrel-startup';
import * as fs from "node:fs";

// Handle creating/removing shortcuts on Windows when installing/uninstalling.
if (started) {
  app.quit();
}

const createWindow = () => {
  // Create the browser window.
  const mainWindow = new BrowserWindow({
    width: 1024,
    height: 768,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
    autoHideMenuBar: true,
    center: true,
  });

  // and load the index.html of the app.
  if (MAIN_WINDOW_VITE_DEV_SERVER_URL) {
    // 本地开发环境
    mainWindow.loadURL(MAIN_WINDOW_VITE_DEV_SERVER_URL);
    // Open the DevTools.
    mainWindow.webContents.openDevTools();
  } else {
    // 构建运行环境
    mainWindow.loadFile(path.join(__dirname, `../renderer/${MAIN_WINDOW_VITE_NAME}/index.html`));
  }

  // 监听下载事件
  mainWindow.webContents.session.on('will-download', (event, item, webContents) => {
    // 设置文件保存位置后，界面就不再弹出对话框
    const savePath = path.join(app.getPath('downloads'), item.getFilename());
    item.setSavePath(savePath);

    item.on('updated', (event, state) => {
      if (state === 'progressing') {
        const progress = Number((Number((item.getReceivedBytes() / item.getTotalBytes()).toFixed(2)) * 100).toFixed());
        webContents.send('download-progress', progress, item.getFilename());
        mainWindow.setProgressBar(progress); // 设置下载进度值
      }
    })

    item.once('done', (event, state) => {
      if (state === 'completed') {
        webContents.send('download-complete', item.getSavePath());
        mainWindow.setProgressBar(-1); // 下载完成，隐藏进度条
      } else {
        webContents.send('download-failed');
        mainWindow.setProgressBar(0); // 下载失败，重置进度条
      }
    })

  })
};

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.whenReady().then(() => {
  createWindow();

  // On OS X it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and import them here.
// 监听渲染进程的请求
ipcMain.handle('read-file', async (event, fileName) => {
  const filePath = path.join(app.getPath('userData'), '/', fileName)
  try {
    const data = fs.readFileSync(filePath, 'utf-8');
    return JSON.parse(data);
  } catch (error) {
    console.error('Error reading file:', error);
    return [];
  }
});
ipcMain.handle('write-file', (event, fileName, fileContent) => {
  const filePath = path.join(app.getPath('userData'), '/', fileName)
  fs.writeFile(filePath, fileContent, 'utf-8', err => {
    if (err) {
      console.error('Error writing file:', err)
    } else {
      console.log('Writing file success.')
    }
  })
});
ipcMain.handle('dark-mode:toggle', () => {
  if (nativeTheme.shouldUseDarkColors) {
    nativeTheme.themeSource = 'light'
  } else {
    nativeTheme.themeSource = 'dark'
  }
  return nativeTheme.shouldUseDarkColors
});
