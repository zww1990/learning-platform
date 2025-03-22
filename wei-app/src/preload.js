// See the Electron documentation for details on how to use preload scripts:
// https://www.electronjs.org/docs/latest/tutorial/process-model#preload-scripts

import { contextBridge, ipcRenderer } from 'electron';

// 主进程和渲染进程通过 IPC 通信传递下载进度和状态。
contextBridge.exposeInMainWorld('electron', {
    onDownloadProgress: (callback) => ipcRenderer.on('download-progress', callback),
    onDownloadComplete: (callback) => ipcRenderer.on('download-complete', callback),
    onDownloadFailed: (callback) => ipcRenderer.on('download-failed', callback),
    readFile: (fileName) => ipcRenderer.invoke('read-file', fileName),
    writeFile: (fileName, fileContent) => ipcRenderer.invoke('write-file', fileName, fileContent),
    toggle: () => ipcRenderer.invoke('dark-mode:toggle'),
});
