function ownKeys(object, enumerableOnly) {
  var keys = Object.keys(object);
  if (Object.getOwnPropertySymbols) {
    var symbols = Object.getOwnPropertySymbols(object);
    enumerableOnly && (symbols = symbols.filter(function(sym) {
      return Object.getOwnPropertyDescriptor(object, sym).enumerable;
    })), keys.push.apply(keys, symbols);
  }
  return keys;
}
function _objectSpread2(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = null != arguments[i] ? arguments[i] : {};
    i % 2 ? ownKeys(Object(source), true).forEach(function(key) {
      _defineProperty(target, key, source[key]);
    }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)) : ownKeys(Object(source)).forEach(function(key) {
      Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
    });
  }
  return target;
}
function _typeof(obj) {
  "@babel/helpers - typeof";
  return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function(obj2) {
    return typeof obj2;
  } : function(obj2) {
    return obj2 && "function" == typeof Symbol && obj2.constructor === Symbol && obj2 !== Symbol.prototype ? "symbol" : typeof obj2;
  }, _typeof(obj);
}
function _classCallCheck(instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError("Cannot call a class as a function");
  }
}
function _defineProperties(target, props) {
  for (var i = 0; i < props.length; i++) {
    var descriptor = props[i];
    descriptor.enumerable = descriptor.enumerable || false;
    descriptor.configurable = true;
    if ("value" in descriptor)
      descriptor.writable = true;
    Object.defineProperty(target, _toPropertyKey(descriptor.key), descriptor);
  }
}
function _createClass(Constructor, protoProps, staticProps) {
  if (protoProps)
    _defineProperties(Constructor.prototype, protoProps);
  if (staticProps)
    _defineProperties(Constructor, staticProps);
  Object.defineProperty(Constructor, "prototype", {
    writable: false
  });
  return Constructor;
}
function _defineProperty(obj, key, value) {
  key = _toPropertyKey(key);
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }
  return obj;
}
function _toPrimitive(input, hint) {
  if (typeof input !== "object" || input === null)
    return input;
  var prim = input[Symbol.toPrimitive];
  if (prim !== void 0) {
    var res = prim.call(input, hint || "default");
    if (typeof res !== "object")
      return res;
    throw new TypeError("@@toPrimitive must return a primitive value.");
  }
  return (hint === "string" ? String : Number)(input);
}
function _toPropertyKey(arg) {
  var key = _toPrimitive(arg, "string");
  return typeof key === "symbol" ? key : String(key);
}
const zhCN$1 = {
  "en-US": "English",
  "zh-CN": "中文",
  "zh-TW": "中国台湾",
  hello: "你好 {name}",
  code: "zh-CN",
  ui: {
    buttonMessage: {
      cancel: "取消",
      confirm: "确定"
    },
    wizard: {
      previousStep: "上一步",
      nextStep: "下一步",
      save: "保存",
      submit: "提交"
    },
    linkMenu: {
      title: "消息",
      placeholder: "请输入关键字过滤...",
      sure: "确定",
      cancel: "取消"
    },
    todoList: {
      add: "提交",
      placeholder: "请输入内容..."
    },
    alert: {
      error: "错误",
      info: "消息",
      success: "成功",
      title: "消息提示",
      warning: "警告"
    },
    amount: {
      currency: "币种",
      amount: "金额",
      date: "日期"
    },
    actionMenu: {
      moreText: "更多"
    },
    base: {
      all: "全部",
      cancel: "取消",
      confirm: "确定",
      delete: "删除",
      edit: "编辑",
      more: "更多",
      reset: "重置"
    },
    button: {
      cancel: "取消",
      confirm: "确认"
    },
    cell: {
      placeholder: "请选择"
    },
    cascader: {
      noMatch: "无匹配数据",
      loading: "加载中",
      placeholder: "请选择",
      noData: "暂无数据"
    },
    chart: {
      auxiliary: "辅助",
      emptyText: "暂无数据",
      kName: "日K",
      other: "其他",
      summation: "总量",
      total: "总计",
      value: "数值"
    },
    colorPicker: {
      confirm: "选择",
      cancel: "取消"
    },
    creditCardForm: {
      submit: "提交"
    },
    crop: {
      cropImage: "图片裁剪",
      croppedImage: "裁剪后图像"
    },
    datepicker: {
      clear: "清空",
      cancel: "取消",
      endDate: "结束日期",
      confirm: "确定",
      month: "月",
      endTime: "结束时间",
      month2: "2 月",
      month1: "1 月",
      month4: "4 月",
      month3: "3 月",
      month6: "6 月",
      month5: "5 月",
      month8: "8 月",
      month7: "7 月",
      month10: "10 月",
      month9: "9 月",
      month12: "12 月",
      month11: "11 月",
      months: {
        feb: "二月",
        jan: "一月",
        apr: "四月",
        mar: "三月",
        jun: "六月",
        may: "五月",
        aug: "八月",
        jul: "七月",
        oct: "十月",
        sep: "九月",
        dec: "十二月",
        nov: "十一月"
      },
      nextYear: "后一年",
      nextMonth: "下个月",
      prevMonth: "上个月",
      now: "此刻",
      selectDate: "选择日期",
      prevYear: "前一年",
      startDate: "开始日期",
      selectTime: "选择时间",
      today: "今天",
      currentMonth: "本月",
      startTime: "开始时间",
      week: "周次",
      weeks: {
        mon: "一",
        sun: "日",
        wed: "三",
        tue: "二",
        fri: "五",
        thu: "四",
        sat: "六"
      },
      timezone: "选择时区",
      year: "年",
      to: "至"
    },
    calendar: {
      showType: {
        year: "年"
      }
    },
    dept: {
      code: "编码",
      company: "公司",
      dept1: "一级部门",
      dept2: "二级部门",
      dept3: "三级部门",
      dept4: "四级部门",
      dept5: "五级部门",
      dept6: "六级部门",
      dept7: "七级部门",
      dept8: "八级部门",
      input: "可输入部门编码或名称",
      name: "名称",
      search: "辅助查询",
      selected: "已选"
    },
    detailpage: {
      saveButtonText: "确认",
      cancelButtonText: "取消",
      localTips: "不能全部隐藏",
      localTitle: "数据",
      valueTitle: "勾选隐藏",
      labelTitle: "文本字段",
      dialogTitle: "个性化标题"
    },
    dialogBox: {
      confirm: "确定",
      cancel: "取消"
    },
    load: {
      dot: "加载中"
    },
    exception: {
      build: "模块正在建设中",
      busy: "系统繁忙，请稍等一下",
      noperm: "茫茫大海，找不到页面",
      weaknet: "网络不给力",
      pcview: "请到PC上查看文件",
      nodata: "休息一下",
      create: "创建",
      provide: "TINY 开发团队提供",
      nodatamf: "暂无数据",
      nopermmf: "无访问权限",
      weaknetmf: "网络异常",
      noresult: "无相关搜索结果",
      nonews: "暂无最新消息",
      pagenoperm: "403:无访问权限",
      pageweaknet: "网络异常",
      pagenothing: "404:你访问的页面不存在",
      pageservererror: "500:服务器异常"
    },
    fileUpload: {
      largefile: "文件过大，将会分片上传，请耐心等待!",
      folder: "文件所在文件夹层数已超过 5 层，将不会上传该文件",
      init: "服务报错，请重试",
      token: "请先进行 EDM 鉴权，获取 token",
      exceed: "文件上传失败：大小超过限制（{maxSize}MB）",
      largeFile: "文件大小超出限制 2G ！！！",
      fileSize: "{name}大小不能小于 ",
      deleteTip: "按 delete 键可删除",
      downloadFile: "下载文件",
      previewFile: "预览文件",
      updateFile: "更新文件",
      deleteFile: "删除文件",
      empty: "是空文件！",
      kiaScanTip: "抱歉，从公网接入下载文档，需要通过KIA检测；当前文档正在KIA检测中，请稍后几分钟后再下载！",
      fileNameExceeds: "超过255个字符，请修改文件名。",
      fileName: "该文件名",
      calcHash: "文档正在计算加密中",
      uploadFile: "文件上传",
      downloadAll: "全部下载",
      onlySupport: "仅支持{type}格式文件",
      fileNotLessThan: "文件不能小于",
      fileNotMoreThan: "文件不能超过",
      notSupport: "文件上传失败：不支持该格式（.{format}）",
      attachment: "附件",
      uploadList: "上传列表",
      numberExceed: "文件上传失败：批量上传个数超过限制（{number}）"
    },
    uploadList: {
      pictureUploading: "图片上传中",
      uploadFailed: "上传失败",
      uploading: "上传中",
      download: "下载",
      reUpload: "重新上传",
      delete: "删除",
      noAttachments: "暂无附件",
      cancel: "取消",
      preview: "预览",
      releaseAndUpload: "释放鼠标，上传文件",
      dragOrClickImport: "将文件拖到此处，或点击导入",
      shoot: "拍摄",
      selectFromAlbum: "从相册选择",
      uploadFailedAndReupload: "上传失败，点击重新上传"
    },
    upload: {
      addPicture: "添加图片",
      addAudio: "添加音频",
      addVideo: "添加视频"
    },
    grid: {
      dataUnchanged: "数据未改动！",
      deleteSelectRecord: "您确定要删除所选记录吗？",
      emptyText: "暂无数据",
      error: {
        cellEditRender: "渲染器 cell-render 和 edit-render 不能同时使用",
        delGetAllRecords: "方法 getAllRecords 已废弃，请使用 getRecordset",
        delGetRecords: "方法 getRecords 已废弃，请使用 getData",
        delLabel: "参数 label 已废弃，请使用 title",
        delProp: "参数 prop 已废弃，请使用 field",
        delRevert: "方法 revert 已废弃，请使用 revertData",
        groupFixed: "如果使用分组表头，固定列必须在左右两侧",
        notDelete: "Delete 方法不存在",
        notMouse: "虚拟滚动不支持 mouse-config",
        notQuery: "query 方法不存在",
        notResizable: "横向虚拟滚动不支持 resizable",
        notSave: "save 方法不存在",
        reqModule: "缺少 {{name}} 模块",
        rowIdEmpty: "参数 row-id 不允许为空",
        scrollOriginal: "虚拟滚动启用后只能导出源数据，请将设置 original=true",
        scrollYHeight: "启用虚拟滚动必须要设置 height 或 max-height",
        toolbarId: "工具栏需要设置唯一 id",
        treeFixedExpand: "树结构的固定列与展开行功能有冲突",
        treeInsert: "树结构不支持 insert 操作",
        treeRemove: "树结构不支持 remove 操作",
        unableInsert: "无法插入到指定位置",
        dargSelf: "不允许自己给自己拖动",
        dargFixed: "固定列不允许拖动",
        remoteMethod: "个性化模板管理远端存储需要设置 multipleHistory.remoteMethod",
        remoteSelectedMethod: "个性化模板管理远端存储需要设置 multipleHistory.remoteSelectedMethod",
        chainCallError: "列的默认插槽中存在语法错误，请检查。"
      },
      filter: {
        allFilter: "全部",
        allSelect: "(全选)",
        endDate: "结束日期",
        startDate: "开始日期",
        dateTips: "请至少输入一个日期",
        clear: "清除当前列筛选",
        clearAll: "清除所有列筛选",
        confirmFilter: "筛选",
        empty: "为空",
        emptyText: "暂无数据",
        equal: "等于",
        include: "包含",
        prefix: "开头是",
        resetFilter: "重置",
        unempty: "不为空"
      },
      individuation: {
        cancelBtn: "取消",
        colConfigs: {
          visible: "显示",
          invisible: "隐藏",
          asc: "正序",
          desc: "倒序",
          unsorted: "未排序",
          frozenLeft: "左冻结",
          frozenRight: "右冻结",
          unfrozen: "未冻结",
          unfreeze: "取消冻结",
          unsort: "取消排序"
        },
        toolbar: {
          set: "设置",
          selected: "已选",
          freeze: "冻结",
          sort: "排序",
          clear: "清空",
          search: "搜索",
          all: "全选"
        },
        columnSet: "列设置",
        overwriteSave: "覆盖保存",
        saveAs: "另存为",
        saveTemplate: "存模板",
        selectTemplate: "选择模板",
        hideMsg: "至少保留一列显示",
        maxFreezeNumMsg: "冻结列不可超过6项",
        defaultTemplateName: "请输入名称，如未填写由系统按时间生成",
        reserveTemplateName: "如未填写名称将保留之前的名称",
        resetBtn: "重置",
        saveBtn: "确定",
        tabs: {
          base: {
            title: "基础设置",
            tips: "点击图标按钮设置个性化"
          },
          other: {
            title: "其他设置",
            tips: "设置服务器排序或客户端排序、每页条数大小。",
            sortType: "排序类型",
            currPageSort: "当前页数据排序",
            allDataSort: "所有数据排序",
            pageSize: "每页条数"
          }
        },
        title: "个性化设置",
        switchtitle: "模板管理",
        switchsave: "保存配置",
        switchlabel: "配置列表：",
        switchapply: "使用",
        switchedit: "编辑",
        switchdel: "删除",
        switchconfirm: "确认",
        switchonlytemp: "保存模板",
        switchtempapply: "保存并使用模板",
        switchtempoverwrite: "覆盖并使用模板",
        switchdelcon: "确定要删除这个模板？",
        switchdelyes: "确定",
        switchdelno: "取消",
        switchapplycon: "确定要使用这个模板？"
      },
      removeSelectRecord: "您确定要移除所选记录吗？",
      saveSuccess: "保存成功",
      selectOneRecord: "请至少选择一条记录！",
      isSaveMsg: "有修改的数据，是否要保存？"
    },
    hrapprover: {
      approver: "权签人",
      noselected: "没有选择权签人",
      noapprover: "没有权签人",
      remark: "备注"
    },
    imageViewer: {
      loadErrorAlt: "加载失败"
    },
    navMenu: {
      moreText: "更多"
    },
    logout: {
      in: "登录",
      out: "注销"
    },
    page: {
      goto: "前往",
      item: "条",
      next: "下一页",
      page: "条/页",
      pageClassifier: "页",
      pagesize: "条/页",
      prev: "上一页",
      total: "总条数",
      totals: "总计：",
      jump: "跳至",
      hundredThousand: "10万+",
      million: "100万+",
      tenMillion: "1千万+",
      loadingTotals: "加载总条数…"
    },
    popeditor: {
      cancel: "取 消",
      confirm: "确 认",
      historyLists: "历史数据列表",
      reset: "重 置",
      search: "查 询",
      selectionLists: "选择数据列表",
      sourceLists: "所有数据列表",
      title: "选择",
      filterNode: "输入内容进行筛选"
    },
    popupload: {
      fileName: "文件名",
      fileSize: "文件大小",
      fileStatus: "文件状态",
      uploadError: "上传失败",
      dialogTitle: "文件上传",
      cancelButtonText: "取消",
      tipsFileText: "上传提示",
      saveButtonText: "开始上传",
      uploadSuccess: "上传成功！",
      uploadButtonText: "选择文件",
      uploadsButtonText: "选择批量文件",
      errorTypeTips: "上传文件类型不匹配",
      errorNumTips: "上传文件数量超出限制,已取消该操作",
      errorSizeTips: "上传文件大小超出限制",
      limitUploadFileNumber: "上传文件数限制为",
      limitUploadFileType: "上传文件类型限制为",
      limitUploadFileSize: "上传文件大小不超过"
    },
    rate: {
      level: {
        average: "一般",
        excellent: "很好",
        fair: "差",
        good: "好",
        poor: "很差"
      }
    },
    select: {
      loading: "加载中",
      noMatch: "无匹配数据",
      noData: "暂无相关数据",
      placeholder: "请选择"
    },
    search: {
      placeholder: "搜索"
    },
    tabs: {
      moreItem: "更多"
    },
    tag: {
      add: "添加"
    },
    toggleMenu: {
      placeholder: "请输入内容进行筛选"
    },
    treeMenu: {
      placeholder: "请输入内容进行筛选"
    },
    transfer: {
      filterPlaceholder: "请输入搜索内容",
      hasCheckedFormat: "已选 {checked}/{total} 项",
      noCheckedFormat: "共 {total} 项",
      noData: "无数据",
      noMatch: "无匹配数据",
      titles: ["列表 1", "列表 2"]
    },
    tree: {
      emptyText: "暂无数据",
      switchText: "同时勾选下级",
      deleteTip: "删除后数据不可恢复，确定删除吗？",
      preserveSubnodeTip: "该节点存在下级节点，是否保留下级节点数据？",
      preserveSubnodeData: "保留下级节点数据",
      newNodeTitle: "新增下级"
    },
    usercard: {
      address: "地址",
      collapse: "收起",
      department: "部门",
      email: "邮箱",
      employeeId: "员工 ID",
      "employee_id": "员工 ID",
      empno: "工号",
      expand: "展开",
      fax: "传真",
      internal: "内线",
      manager: "主管",
      mobile: "手机",
      other: "其他",
      phone: "固定电话",
      timezone: "时区",
      title: "用户信息: {0}",
      travelcode: "出差联系信息",
      viop: "VIOP",
      zipcode: "邮编"
    },
    richText: {
      bold: "加粗",
      italic: "倾斜",
      underline: "下划线",
      header: "段落格式",
      strike: "删除线",
      blockquote: "块引用",
      codeBlock: "插入代码段",
      size: "字体大小",
      listOrdered: "编号列表",
      listBullet: "项目列表",
      header1: "h1",
      header2: "h2",
      align: "对齐方式",
      color: "字体颜色",
      background: "背景颜色",
      image: "图像",
      video: "视频",
      link: "添加链接",
      formula: "插入公式",
      clean: "清除格式",
      indent1: "向左缩进",
      indent2: "向右缩进",
      pickerLabel: "标题大小",
      headerPicker1: "标题一",
      headerPicker2: "标题二",
      headerPicker3: "标题三",
      headerPicker4: "标题四",
      headerPicker5: "标题五",
      headerPicker6: "标题六",
      normal: "标准",
      sizeSmall: "小号",
      sizeLarge: "大号",
      sizeHuge: "超大号",
      alignPicker1: "居左对齐",
      alignPicker2: "居中对齐",
      alignPicker3: "居右对齐",
      alignPicker4: "两端对齐",
      subScript: "下标",
      superScript: "上标",
      directionRTL: "从右到左",
      font: "字体",
      file: "文件",
      betterTable: "表格",
      fullscreen: "全屏",
      insertColumnRight: "右插入列",
      insertColumnLeft: "左插入列",
      insertRowUp: "上插入行",
      insertRowDown: "下插入行",
      mergeCells: "合并单元格",
      unmergeCells: "拆分单元格",
      deleteColumn: "删除当前列",
      deleteRow: "删除当前行",
      deleteTable: "删除表格",
      colorPicker: "背景颜色",
      placeholder: "在此处插入文本...",
      maxLength: "文本长度超过限制，支持的最大长度是 "
    },
    steps: {
      done: "已完成",
      doing: "进行中",
      wait: "等待中"
    },
    actionSheet: {
      cancel: "取消"
    },
    image: {
      loadFail: "加载失败"
    },
    miniPicker: {
      cancel: "取消",
      confirm: "确定"
    },
    pullRefresh: {
      pullingDown: "下拉即可刷新",
      pullingUp: "上拉即可刷新",
      pulling: "下拉即可刷新",
      loosing: "释放即可刷新",
      success: "刷新成功",
      failed: "刷新失败",
      noMore: "没有更多了"
    },
    currency: {
      defaultCurrency: "默认币种",
      setDefault: "设为默认"
    },
    calendarBar: {
      week: {
        0: "日",
        1: "一",
        2: "二",
        3: "三",
        4: "四",
        5: "五",
        6: "六"
      },
      year: "%s年",
      yearMonth: "%y年%m月",
      month: {
        1: "1月",
        2: "2月",
        3: "3月",
        4: "4月",
        5: "5月",
        6: "6月",
        7: "7月",
        8: "8月",
        9: "9月",
        10: "10月",
        11: "11月",
        12: "12月"
      },
      monthAbbr: {
        1: "1",
        2: "2",
        3: "3",
        4: "4",
        5: "5",
        6: "6",
        7: "7",
        8: "8",
        9: "9",
        10: "10",
        11: "11",
        12: "12"
      }
    },
    selectedBox: {
      select: "已选（%s）",
      allSelect: "已全选（%s）",
      clear: "清空"
    },
    record: {
      record: "录音",
      cancel: "取消",
      confirm: "确定",
      clickToStartRecording: "点击开始录音",
      clickToResumeRecording: "点击继续录音"
    },
    dialogSelect: {
      treeSearch: "请输入关键字并回车"
    }
  },
  validation: {
    array: {
      len: "%s 的长度必须为 %s",
      min: "%s 长度不能小于 %s",
      max: "%s 的长度不能大于 %s",
      range: "%s 的长度必须介于 %s 和 %s 之间"
    },
    date: {
      format: "%s 日期 %s 对于格式 %s 无效",
      invalid: "%s 日期 %s 无效",
      parse: "无法分析 %s 日期， %s 无效"
    },
    default: "%s 字段校验错误",
    enum: "%s 必须是 %s 中的一个",
    number: {
      len: "%s 必须等于 %s",
      min: "%s 不能小于 %s",
      max: "%s 不能大于 %s",
      range: "%s 必须介于 %s 和 %s 之间"
    },
    pattern: {
      mismatch: "%s 值%s 与模式 %s 不匹配"
    },
    required: "必填",
    string: {
      len: "%s 必须是 %s 个字符",
      min: "%s 必须至少为 %s 个字符",
      max: "%s不 能大于 %s 个字符",
      range: "%s 必须介于 %s 和 %s 个字符之间"
    },
    types: {
      acceptFile: "只接受文件",
      acceptImg: "只接受图片格式",
      array: "非法数组",
      boolean: "非法布尔值",
      date: "不符合规则的日期格式",
      dateTime: "不符合规则的日期时间格式",
      dateYM: "不符合规则的日期格式(yyyy-mm)",
      dateYMD: "不符合规则的日期格式(yyyy-MM-dd)",
      digits: "非法纯数字",
      email: "非法邮件地址",
      fileSize: "文件大小的格式不正确,应如 3kb",
      float: "非法浮点数",
      hex: "非法十六进制",
      integer: "非法整数",
      longDateTime: "不符合规则的长日期格式",
      method: "必须是函数（Function）",
      number: "非法数字",
      object: "非法对象",
      regexp: "非法正则表达式",
      specialch: "只能包含数字、字母、下划线、横杠、点号",
      specialch2: "只能包含数字、字母、下划线、横杠",
      speczh: "只能包含数字、字母、下划线、汉",
      string: "非法字符串",
      time: "不符合规则的时间格式",
      url: "非法 URL 地址",
      version: "非法版本格式"
    },
    whitespace: "%s 不能为空"
  }
};
const enUS$1 = {
  "en-US": "English",
  "zh-CN": "中文",
  "zh-TW": "中国台湾",
  hello: "Hello {name}",
  code: "en-US",
  ui: {
    buttonMessage: {
      cancel: "Cancel",
      confirm: "Confirm"
    },
    wizard: {
      previousStep: "LastStep",
      nextStep: "NextStep",
      save: "Save",
      submit: "Submit"
    },
    linkMenu: {
      title: "news",
      placeholder: "Please enter keywords to filter...",
      sure: "Determine",
      cancel: "cancel"
    },
    todoList: {
      add: "Add",
      placeholder: "please input your todo things"
    },
    alert: {
      error: "Error",
      info: "Info",
      success: "Success",
      title: "Message notification",
      warning: "Warning"
    },
    amount: {
      currency: "Currency",
      amount: "Amount",
      date: "Date"
    },
    actionMenu: {
      moreText: "more"
    },
    base: {
      all: "All",
      cancel: "Cancel",
      confirm: "OK",
      delete: "Delete",
      edit: "Edit",
      more: "More",
      reset: "Reset"
    },
    button: {
      cancel: "Cancel",
      confirm: "OK"
    },
    cell: {
      placeholder: "Select"
    },
    cascader: {
      noMatch: "No matching data",
      loading: "Loading",
      placeholder: "Select",
      noData: "No data"
    },
    chart: {
      auxiliary: "Auxiliary",
      emptyText: "No Data",
      kName: "Day K",
      other: "Other",
      summation: "Summation",
      total: "Total",
      value: "Value"
    },
    colorPicker: {
      confirm: "Ok",
      cancel: "Cancel"
    },
    creditCardForm: {
      submit: "Submit"
    },
    crop: {
      cropImage: "crop image",
      croppedImage: "Post-Crop Image"
    },
    datepicker: {
      clear: "Clear",
      cancel: "Cancel",
      endDate: "End Date",
      confirm: "OK",
      month: "month",
      endTime: "End Time",
      month2: "February",
      month1: "January",
      month4: "April",
      month3: "March",
      month6: "June",
      month5: "May",
      month8: "August",
      month7: "July",
      month10: "October",
      month9: "September",
      month12: "December",
      month11: "November",
      months: {
        feb: "Feb",
        jan: "Jan",
        apr: "Apr",
        mar: "Mar",
        jun: "Jun",
        may: "May",
        aug: "Aug",
        jul: "Jul",
        oct: "Oct",
        sep: "Sep",
        dec: "Dec",
        nov: "Nov"
      },
      nextYear: "Next Year",
      nextMonth: "Next Month",
      prevMonth: "Previous Month",
      now: "Now",
      selectDate: "Select date",
      prevYear: "Previous Year",
      startDate: "Start Date",
      selectTime: "Select time",
      today: "Today",
      currentMonth: "Current Month",
      startTime: "Start Time",
      week: "week",
      weeks: {
        mon: "Mon",
        sun: "Sun",
        wed: "Wed",
        tue: "Tue",
        thu: "Thu",
        sat: "Sat",
        fri: "Fri"
      },
      timezone: "selecting a timezone",
      year: "",
      to: "to"
    },
    calendar: {
      showType: {
        year: "year"
      }
    },
    dept: {
      code: "Code",
      company: "Company",
      dept1: "First Level Department",
      dept2: "Second Level Department",
      dept3: "Third Level Department",
      dept4: "Fourth Level Department",
      dept5: "Fifth Level Department",
      dept6: "Sixth Level Department",
      dept7: "Seventh Level Department",
      dept8: "Eighth Level Department",
      input: "Input Dept Code or Name is available",
      name: "Name",
      search: "Quick Search",
      selected: "Selected"
    },
    detailpage: {
      saveButtonText: "Confirm",
      cancelButtonText: "Cancel",
      localTips: "Cannot hide all",
      localTitle: "Data",
      valueTitle: "Click to hide",
      labelTitle: "Title",
      dialogTitle: "Personalized title"
    },
    dialogBox: {
      confirm: "confirm",
      cancel: "cancel"
    },
    load: {
      dot: "Loading"
    },
    exception: {
      build: "Building",
      busy: "The network is busy. Please wait",
      noperm: "Not find the page",
      weaknet: "Poor network performance",
      pcview: "View the file on the PC",
      nodata: "Get some rest",
      create: "Create",
      provide: "Provided by the TINY Team DEV",
      nodatamf: "No data",
      nopermmf: "No access",
      weaknetmf: "Network anomaly",
      noresult: "No result",
      nonews: "No latest news",
      pagenoperm: "403:No access",
      pageweaknet: "Network anomaly",
      pagenothing: "404:The page you visited does not exist",
      pageservererror: "500:Server exception"
    },
    fileUpload: {
      largefile: " is too large and will be uploaded in segments. Please wait.",
      folder: " has more than five layers of folders. The file will not be uploaded",
      init: "Service error. Please try again.",
      token: "Perform EDM authentication first and obtain the token",
      exceed: "Failed to upload the file. The file size exceeds the upper limit ({maxSize} MB).",
      largeFile: "The file size exceeds the upper limit by 2 GB !!!",
      fileSize: "The file size of {name} cannot be less than ",
      deleteTip: "Press delete to remove",
      downloadFile: "DownLoad file",
      previewFile: "Preview file",
      updateFile: "Update file",
      deleteFile: "Delete file",
      empty: "is empty!",
      kiaScanTip: "Sorry, unable to download. Please pass the KIA test first to download a file using public network. Current document is being checked by the KIA, Please wait a while then redownload.",
      fileNameExceeds: "exceeds 255 characters. Please change the file name.",
      fileName: "The file name",
      calcHash: "Document is calculating encryption",
      uploadFile: "Upload file",
      downloadAll: "Download all",
      onlySupport: "Only support {type} file",
      fileNotLessThan: "The file cannot be less than",
      fileNotMoreThan: "The file cannot be more than",
      notSupport: "File upload failed: The format (.{format}) is not supported.",
      attachment: "Attachment",
      uploadList: "Upload List",
      numberExceed: "Failed to upload the file. The number of files to be uploaded in batches exceeds the upper limit ({number})."
    },
    uploadList: {
      pictureUploading: "Picture uploading",
      uploadFailed: "Upload failed",
      uploading: "Uploading",
      download: "Download",
      reUpload: "Re-upload",
      delete: "Delete",
      noAttachments: "No attachments",
      cancel: "Cancel",
      preview: "Preview",
      releaseAndUpload: "Release the mouse and upload the file",
      dragOrClickImport: "Drag the file here, or click Import",
      shoot: "Shoot",
      selectFromAlbum: "Select from album",
      uploadFailedAndReupload: "Upload failed. Click Upload again"
    },
    upload: {
      addPicture: "Add picture",
      addAudio: "Add audio",
      addVideo: "Add video"
    },
    grid: {
      dataUnchanged: "Data unchanged!",
      deleteSelectRecord: "Are you sure you want to delete the selected record?",
      emptyText: "No Data",
      error: {
        cellEditRender: "The renderer cell-render and edit-render cannot be used together.",
        delGetAllRecords: "The function getAllRecords is deprecated, please use getRecordset.",
        delGetRecords: "The function getRecords is deprecated, please use getData.",
        delLabel: "The property label is deprecated, please use title.",
        delProp: "The property prop is deprecated, please use field.",
        delRevert: "The function revert is deprecated, please use revertData.",
        groupFixed: "If grouping headers are used, fixed columns must be on the left and right sides.",
        notDelete: "delete method not exist.",
        notMouse: "Horizontal virtual scrolling does not support mouse-config.",
        notQuery: "query method not exist.",
        notResizable: "Horizontal virtual scrolling does not support resizable.",
        notSave: "save method not exist.",
        reqModule: "require {{name}} module.",
        rowIdEmpty: "The property row-id is not allowed to be empty.",
        scrollOriginal: "Virtual scrolling can only export source data, please set original=true.",
        scrollYHeight: "You must set the height or max-height to enable virtual scrolling.",
        toolbarId: "Toolbar must have a unique id",
        treeFixedExpand: "The fixed columns of the tree structure conflict with the expanded row.",
        treeInsert: "The tree structure does not support insert operations.",
        treeRemove: "The tree structure does not support remove operations.",
        unableInsert: "Unable to insert to the specified location.",
        dargSelf: "Self dragging is not allowed.",
        dargFixed: "Fixed columns cannot be dragged.",
        remoteMethod: '"remoteMethod" needs to be set for remote storage for personalized template management.',
        remoteSelectedMethod: '"remoteSelectedMethod" needs to be set for remote storage for personalized template management.',
        chainCallError: "There is a syntax error in the default slot for the column, please check."
      },
      filter: {
        allFilter: "All",
        allSelect: "(All)",
        endDate: "End Date",
        startDate: "Start Date",
        dateTips: "Please enter at least one date",
        clear: "Clear Current Filter",
        clearAll: "Clear All Filters",
        confirmFilter: "OK",
        empty: "Is Empty",
        emptyText: "No Data",
        equal: "Equal",
        include: "Include",
        prefix: "Starts With",
        resetFilter: "Reset",
        unempty: "Is not Empty"
      },
      individuation: {
        cancelBtn: "Cancel",
        colConfigs: {
          asc: "Ascending",
          desc: "Descending",
          frozenLeft: "Frozen on the left",
          frozenRight: "Frozen on the right",
          invisible: "Invisible",
          unsorted: "No sortting",
          unfrozen: "Unfrozen",
          visible: "Visible",
          unfreeze: "Unfrozen",
          unsort: "Unsort"
        },
        toolbar: {
          set: "Set",
          selected: "Selected",
          freeze: "Freeze",
          sort: "Sort",
          clear: "Clear",
          search: "Search",
          all: "All"
        },
        columnSet: "Column Setting",
        overwriteSave: "Overwrite Save",
        saveAs: "Save as",
        saveTemplate: "Save Template",
        selectTemplate: "Select Template",
        hideMsg: "Leave one column to show at least.",
        maxFreezeNumMsg: "Maxium number of frozen columns is 6",
        defaultTemplateName: "Please enter a name, if not filled in, it will be generated by the system according to time",
        reserveTemplateName: "If the name is not filled in, the previous name will be retained",
        resetBtn: "Reset",
        saveBtn: "OK",
        tabs: {
          base: {
            title: "Base Setting",
            tips: "Click the icon to set personalized."
          },
          other: {
            allDataSort: "Remote Data Sorting",
            currPageSort: "Current Page Data Sorting",
            pageSize: "Page Size",
            title: "Other Setting",
            tips: "Settings for Sorting and Pagesize.",
            sortType: "Sorting Type"
          }
        },
        title: "Personalized Settings",
        switchtitle: "Template Management",
        switchsave: "Save configuration",
        switchlabel: "List:",
        switchapply: "Apply",
        switchedit: "Edit",
        switchdel: "Delete",
        switchconfirm: "Confirm",
        switchonlytemp: "Save as template only",
        switchtempapply: "Save as template and use",
        switchtempoverwrite: "Overwrite template and use",
        switchdelcon: "Are you sure to delete this template?",
        switchdelyes: "Yes",
        switchdelno: "No",
        switchapplycon: "Are you sure to use this template?"
      },
      removeSelectRecord: "Are you sure you want to remove the selected record?",
      saveSuccess: "Save successfully.",
      selectOneRecord: "Please choose at least one piece of record!",
      isSaveMsg: "There are change records, do you want to save them?"
    },
    hrapprover: {
      approver: "Approver",
      noapprover: "There is no approver",
      noselected: "Select one record!",
      remark: "Remarks"
    },
    imageViewer: {
      loadErrorAlt: "Load Error"
    },
    navMenu: {
      moreText: "more"
    },
    logout: {
      in: "Login",
      out: "Logout"
    },
    page: {
      goto: "Go to",
      item: "",
      next: "Next",
      page: "Records/Page",
      pageClassifier: "",
      pagesize: "Records/Page",
      prev: "Prev",
      total: "Total ",
      totals: "Total: ",
      jump: "Go to",
      hundredThousand: "100K+",
      million: "1M+",
      tenMillion: "10M+",
      loadingTotals: "Loading Totals..."
    },
    popeditor: {
      cancel: "Cancel",
      confirm: "OK",
      historyLists: "History options",
      reset: "Reset",
      search: "Search",
      selectionLists: "Selected Items",
      sourceLists: "Available Items",
      title: "Select",
      filterNode: "input text to filter node"
    },
    popupload: {
      fileName: "File Name",
      fileSize: "File Size",
      fileStatus: "File Status",
      dialogTitle: "File Upload",
      cancelButtonText: "Cancel",
      tipsFileText: "upload hints",
      uploadError: "Upload failure!",
      uploadButtonText: "select file",
      uploadSuccess: "Upload Success!",
      saveButtonText: "Click To Upload",
      uploadsButtonText: "select files",
      errorTypeTips: "Upload file type mismatch",
      errorSizeTips: "Upload file size exceeds limit",
      limitUploadFileType: "Upload file type is limited to",
      limitUploadFileNumber: "Limit the number of uploaded files to",
      limitUploadFileSize: "The size of the uploaded file does not exceed",
      errorNumTips: "The number of uploaded files exceeds the limit. The operation has been cancelled"
    },
    rate: {
      level: {
        average: "Average",
        excellent: "Excellent",
        fair: "Fair",
        good: "Good",
        poor: "Poor"
      }
    },
    select: {
      loading: "Loading",
      noData: "No data",
      noMatch: "No matching data",
      placeholder: "Select"
    },
    search: {
      placeholder: "search"
    },
    tabs: {
      moreItem: "more"
    },
    tag: {
      add: "Add"
    },
    toggleMenu: {
      placeholder: "please input filter content"
    },
    treeMenu: {
      placeholder: "please input filter content"
    },
    transfer: {
      filterPlaceholder: "Enter keyword",
      hasCheckedFormat: "{checked}/{total} checked",
      noData: "No data",
      noMatch: "No matching data",
      noCheckedFormat: "{total} items",
      titles: ["List 1", "List 2"]
    },
    tree: {
      emptyText: "No data",
      switchText: "check easily",
      deleteTip: "Data will be permanently deleted, are you sure you want to proceed with the deletion?",
      preserveSubnodeTip: "This node have child nodes, Would you like to preserve the data of the child nodes?",
      preserveSubnodeData: "To preserve child nodes' data.",
      newNodeTitle: "Add Child Nodes"
    },
    usercard: {
      address: "Address",
      collapse: "Collapse",
      department: "Department",
      email: "email",
      employeeId: "Employee ID",
      "employee_id": "Employee ID",
      empno: "Employee Number",
      expand: "Expand",
      fax: "Fax",
      internal: "Internal",
      manager: "Manager",
      mobile: "Mobile",
      other: "Other",
      phone: "Telephone",
      timezone: "Time zone",
      title: "User information: {0}",
      travelcode: "Travel code",
      viop: "VIOP",
      zipcode: "Postal Code"
    },
    richText: {
      bold: "Bold",
      italic: "Tilt",
      underline: "Underline",
      header: "Paragraph Format",
      strike: "Delete Line",
      blockquote: "Block Reference",
      codeBlock: "Insert Code Segment",
      size: "Font Size",
      listOrdered: "No. List",
      listBullet: "Project List",
      header1: "H1",
      header2: "H2",
      align: "Alignment Mode",
      color: "Font Color",
      background: "Background Color",
      image: "Image",
      video: "Video",
      link: "Add Link",
      formula: "Insert Function",
      clean: "Clear Format",
      indent1: "Indent To The Left",
      indent2: "Indent To The Right",
      pickerLabel: "Title Size",
      headerPicker1: "Title 1",
      headerPicker2: "Title 2",
      headerPicker3: "Title 3",
      headerPicker4: "Title 4",
      headerPicker5: "Title 5",
      headerPicker6: "Title 6",
      normal: "Normal",
      sizeSmall: "Small",
      sizeLarge: "Large",
      sizeHuge: "Super Large Size",
      alignPicker1: "Align To The Left",
      alignPicker2: "Align In The Center",
      alignPicker3: "Align To The Right",
      alignPicker4: "Align The Two Ends",
      subScript: "Subscript",
      superScript: "Superscript",
      directionRTL: "Right To Left",
      font: "Font",
      file: "File",
      betterTable: "Table",
      fullscreen: "Fullscreen",
      insertColumnRight: "Insert Column Right",
      insertColumnLeft: "Insert Column Left",
      insertRowUp: "Insert Row Up",
      insertRowDown: "Insert Row Down",
      mergeCells: "Merge Cells",
      unmergeCells: "Split Cells",
      deleteColumn: "Delete Current Column",
      deleteRow: "Delete Current Row",
      deleteTable: "Delete Table",
      colorPicker: "Background Color",
      placeholder: "Insert text here...",
      maxLength: "Text Length exceeds the Limit, max Length config is "
    },
    steps: {
      done: "Completed",
      doing: "Ongoing",
      wait: "Waiting"
    },
    actionSheet: {
      cancel: "Cancel"
    },
    image: {
      loadFail: "Loading failed"
    },
    miniPicker: {
      cancel: "Cancel",
      confirm: "Confirm"
    },
    pullRefresh: {
      pullingDown: "Pull down to refresh",
      pullingUp: "Pull up to refresh",
      pulling: "Pull down to refresh",
      loosing: "Release to refresh",
      success: "Refresh success",
      failed: "Refresh failed",
      noMore: "There is no more"
    },
    currency: {
      defaultCurrency: "Default currency",
      setDefault: "Set as default"
    },
    calendarBar: {
      week: {
        0: "SUN",
        1: "MON",
        2: "TUE",
        3: "WED",
        4: "THU",
        5: "FRI",
        6: "SAT"
      },
      year: "Year %s",
      yearMonth: "%m %y",
      month: {
        1: "January",
        2: "February",
        3: "March",
        4: "April",
        5: "May",
        6: "June",
        7: "July",
        8: "August",
        9: "September",
        10: "October",
        11: "November",
        12: "December"
      },
      monthAbbr: {
        1: "Jan",
        2: "Feb",
        3: "Mar",
        4: "Apr",
        5: "May",
        6: "Jun",
        7: "Jul",
        8: "Aug",
        9: "Sept",
        10: "Oct",
        11: "Nov",
        12: "Dec"
      }
    },
    selectedBox: {
      select: "Selected (%s)",
      allSelect: "All selected (%s)",
      clear: "Clear"
    },
    record: {
      record: "Record",
      cancel: "Cancel",
      confirm: "Confirm",
      clickToStartRecording: "Click to start recording",
      clickToResumeRecording: "Click to resume recording"
    },
    dialogSelect: {
      treeSearch: "Enter a keyword and press Enter"
    }
  },
  validation: {
    array: {
      len: "must be exactly %s in length",
      min: "cannot be less than %s in length",
      max: "cannot be greater than %s in length",
      range: "must be between %s and %s in length"
    },
    date: {
      format: "date %s is invalid for format %s",
      invalid: "date %s is invalid",
      parse: "date could not be parsed, %s is invalid "
    },
    default: "Validation error on field %s",
    enum: "must be one of %s",
    number: {
      len: "must equal %s",
      min: "cannot be less than %s",
      max: "cannot be greater than %s",
      range: "must be between %s and %s"
    },
    pattern: {
      mismatch: "value %s does not match pattern %s"
    },
    required: "required",
    string: {
      len: "must be exactly %s characters",
      min: "must be at least %s characters",
      max: "cannot be longer than %s characters",
      range: "must be between %s and %s characters"
    },
    types: {
      acceptFile: "not a valid %s",
      acceptImg: "not a valid %s",
      array: "not an %s",
      boolean: "not a %s",
      date: "not a %s",
      dateTime: "not a valid %s",
      dateYM: "not a valid %s",
      dateYMD: "not a valid %s",
      digits: "not a valid %s",
      email: "not a valid %s",
      fileSize: "not a valid %s",
      float: "not a %s",
      hex: "not a valid %s",
      integer: "not an %s",
      longDateTime: "not a valid %s",
      method: "not a %s (function)",
      number: "not a %s",
      object: "not an %s",
      regexp: "not a valid %s",
      specialch: "not a valid %s",
      specialch2: "not a valid %s",
      speczh: "not a valid %s",
      string: "not a %s",
      time: "not a valid %s",
      url: "not a valid %s",
      version: "not a valid %s"
    },
    whitespace: "cannot be empty"
  }
};
var RE_NARGS = /(%|)\{([0-9a-zA-Z_]+)\}/g;
function format(string) {
  for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
    args[_key - 1] = arguments[_key];
  }
  if (args.length === 1 && _typeof(args[0]) === "object") {
    args = args[0];
  }
  if (!args || !args.hasOwnProperty) {
    args = {};
  }
  return string.replace(RE_NARGS, function(match, prefix, i, index2) {
    var result;
    if (string[index2 - 1] === "{" && string[index2 + match.length] === "}") {
      return i;
    } else {
      result = Object.prototype.hasOwnProperty.call(args, i) ? args[i] : null;
      if (result === null || result === void 0) {
        return "";
      }
      return result;
    }
  });
}
var toString = Object.prototype.toString;
var hasOwn = Object.prototype.hasOwnProperty;
var getProto = Object.getPrototypeOf;
var fnToString = hasOwn.toString;
var ObjectFunctionString = fnToString.call(Object);
var class2type = {
  "[object Error]": "error",
  "[object Object]": "object",
  "[object RegExp]": "regExp",
  "[object Date]": "date",
  "[object Array]": "array",
  "[object Function]": "function",
  "[object String]": "string",
  "[object Number]": "number",
  "[object Boolean]": "boolean"
};
var isNull$2 = function isNull(x) {
  return x === null || x === void 0 || x === "undefined";
};
var typeOf = function typeOf2(obj) {
  return isNull$2(obj) ? String(obj) : class2type[toString.call(obj)] || "object";
};
var isObject = function isObject2(obj) {
  return typeOf(obj) === "object";
};
var isPlainObject = function isPlainObject2(obj) {
  if (!obj || toString.call(obj) !== "[object Object]") {
    return false;
  }
  var proto = getProto(obj);
  if (!proto) {
    return true;
  }
  var Ctor = hasOwn.call(proto, "constructor") && proto.constructor;
  return typeof Ctor === "function" && fnToString.call(Ctor) === ObjectFunctionString;
};
var isNumber = function isNumber2(value) {
  return typeof value === "number" && isFinite(value);
};
var isNumeric = function isNumeric2(value) {
  return value - parseFloat(value) >= 0;
};
var isDate$1 = function isDate(value) {
  return typeOf(value) === "date";
};
var each = function each2(obj, handle) {
  if (typeof handle !== "function") {
    return;
  }
  for (var name in obj) {
    if (hasOwn.call(obj, name)) {
      if (handle(name, obj[name]) === false) {
        break;
      }
    }
  }
};
var extend$2;
var getObj = function getObj2(data, names, isExceptRoot) {
  if (!data || !isPlainObject(data) || !names || typeof names !== "string") {
    return;
  }
  names = names.split(".");
  var obj = data;
  var len = names.length;
  if (len > 1) {
    var startIndex = isExceptRoot ? 1 : 0;
    for (var i = startIndex; i < len; i++) {
      obj = obj[names[i]];
      if (isNull$2(obj)) {
        return obj;
      }
    }
    return obj;
  } else {
    return obj[names[0]];
  }
};
var setObj = function setObj2(data, names, value, isMerge) {
  if (!data || !isPlainObject(data) || !names || typeof names !== "string") {
    return data;
  }
  names = names.split(".");
  var obj = data;
  var len = names.length;
  var item = names[0];
  if (len > 1) {
    len--;
    var tmpl = obj;
    var name, target;
    for (var i = 0; i < len; i++) {
      name = names[i];
      target = tmpl[name];
      if (target === null || !isPlainObject(target)) {
        tmpl[name] = {};
        target = tmpl[name];
      }
      tmpl = target;
    }
    item = names[len];
    isMerge ? isPlainObject(tmpl[item]) ? extend$2(true, tmpl[item], value) : tmpl[item] = value : tmpl[item] = value;
  } else {
    isMerge ? isPlainObject(obj[item]) ? extend$2(true, obj[item], value) : obj[item] = value : obj[item] = value;
  }
  return obj;
};
var copyField = function copyField2(data, fields, isMerge, isExclude) {
  var setValue = function setValue2(obj, result, name, key, isMerge2) {
    var include = key.indexOf(name) === 0;
    var keySplit = key.split(name);
    var hasNextDot = keySplit[1] && keySplit[1].indexOf(".") === 0;
    if (name === key || include && hasNextDot) {
      if (name !== key) {
        each(getObj(obj, name), function(field) {
          setValue2(obj, result, "".concat(name, ".").concat(field), key);
        });
      }
    } else {
      if (!fields.includes(name)) {
        setObj(result, name, getObj(obj, name), isMerge2);
      }
    }
  };
  var innerCopyFields = function innerCopyFields2(obj, fields2, isMerge2, isExclude2) {
    var result = {};
    if (isExclude2) {
      each(obj, function(name) {
        return fields2.forEach(function(key) {
          return setValue(obj, result, name, key, isMerge2);
        });
      });
    } else {
      fields2.forEach(function(field) {
        return setObj(result, field, getObj(obj, field), isMerge2);
      });
    }
    return result;
  };
  if (isPlainObject(data)) {
    return Array.isArray(fields) ? innerCopyFields(data, fields, isMerge, isExclude) : extend$2(isMerge !== false, {}, data);
  }
  return data;
};
var copyArray = function copyArray2(arr) {
  return Array.isArray(arr) ? arr.map(function(item) {
    return copyField(item);
  }) : arr;
};
var deepCopy = function deepCopy2(target, name, deep, copy, src) {
  var copyIsArray;
  if (deep && copy && (isPlainObject(copy) || (copyIsArray = Array.isArray(copy)))) {
    if (copyIsArray) {
      copyIsArray = false;
      target[name] = copyArray(copy);
    } else {
      var clone = src && isPlainObject(src) ? src : {};
      target[name] = extend$2(deep, clone, copy);
    }
  } else if (copy !== void 0) {
    try {
      target[name] = copy;
    } catch (e) {
    }
  }
};
extend$2 = function extend() {
  var args = arguments;
  var length = args.length;
  var target = args[0] || {};
  var i = 1;
  var deep = false;
  if (typeOf(target) === "boolean") {
    deep = target;
    target = args[i] || {};
    i++;
  }
  if (!isObject(target) && typeOf(target) !== "function") {
    target = {};
  }
  if (i === length) {
    target = this;
    i--;
  }
  for (; i < length; i++) {
    var options = args[i];
    if (options !== null && isObject(options)) {
      var names = Object.keys(options);
      for (var _i = 0, _names = names; _i < _names.length; _i++) {
        var name = _names[_i];
        var src = target[name];
        var copy = options[name];
        if (target !== copy) {
          deepCopy(target, name, deep, copy, src);
        }
      }
    }
  }
  return target;
};
var lang = zhCN$1;
var i18nHandler = null;
var t$1 = function t(path) {
  var options = arguments.length > 1 && arguments[1] !== void 0 ? arguments[1] : void 0;
  if (i18nHandler)
    return i18nHandler.apply(this, arguments);
  var array = path.split(".");
  var value = null;
  var current = lang;
  for (var i = 0, j = array.length; i < j; i++) {
    var property = array[i];
    value = current[property] || "";
    if (i === j - 1)
      return format(value, options);
    if (!value)
      return "";
    current = value;
  }
  return "";
};
var use$1 = function use(l) {
  lang = l || lang;
  return lang;
};
var language$1 = function language() {
  return lang.code;
};
var i18n$1 = function i18n(fn) {
  i18nHandler = fn || t$1;
  return i18nHandler;
};
var extend$1 = extend$2;
var initI18n$1 = function initI18n(_ref) {
  var app = _ref.app, createI18n = _ref.createI18n, _ref$messages = _ref.messages, messages = _ref$messages === void 0 ? {} : _ref$messages, _ref$i18n = _ref.i18n, i18n22 = _ref$i18n === void 0 ? {} : _ref$i18n, merge = _ref.merge;
  if (typeof merge !== "function") {
    merge = function merge2(_ref2) {
      var lang3 = _ref2.lang, i18n3 = _ref2.i18n, messages2 = _ref2.messages;
      return extend$1(true, lang3, i18n3.messages, messages2);
    };
  }
  var lang2 = {
    zhCN: zhCN$1,
    enUS: enUS$1
  };
  if (typeof createI18n === "function") {
    var vueI18n = createI18n({
      locale: i18n22.locale || "zhCN",
      messages: merge({
        lang: lang2,
        i18n: i18n22,
        messages
      })
    });
    i18nHandler = function i18nHandler2(key, value) {
      return vueI18n.global.t(key, value);
    };
    return vueI18n;
  }
  if (app && app.config && app.config.globalProperties) {
    app.config.globalProperties.$t = t$1;
  }
  return merge({
    lang: lang2,
    i18n: i18n22,
    messages
  });
};
var isVue2 = true;
var isVue3 = false;
const index$1 = {
  isVue2,
  isVue3,
  use: use$1,
  t: t$1,
  i18n: i18n$1,
  initI18n: initI18n$1,
  extend: extend$1,
  zhCN: zhCN$1,
  enUS: enUS$1
};
var libExports$1 = {};
var lib$1 = {
  get exports() {
    return libExports$1;
  },
  set exports(v) {
    libExports$1 = v;
  }
};
var _default$1 = {};
var libExports = {};
var lib = {
  get exports() {
    return libExports;
  },
  set exports(v) {
    libExports = v;
  }
};
var _default = {};
function getDefaultWhiteList$1() {
  var whiteList = {};
  whiteList["align-content"] = false;
  whiteList["align-items"] = false;
  whiteList["align-self"] = false;
  whiteList["alignment-adjust"] = false;
  whiteList["alignment-baseline"] = false;
  whiteList["all"] = false;
  whiteList["anchor-point"] = false;
  whiteList["animation"] = false;
  whiteList["animation-delay"] = false;
  whiteList["animation-direction"] = false;
  whiteList["animation-duration"] = false;
  whiteList["animation-fill-mode"] = false;
  whiteList["animation-iteration-count"] = false;
  whiteList["animation-name"] = false;
  whiteList["animation-play-state"] = false;
  whiteList["animation-timing-function"] = false;
  whiteList["azimuth"] = false;
  whiteList["backface-visibility"] = false;
  whiteList["background"] = true;
  whiteList["background-attachment"] = true;
  whiteList["background-clip"] = true;
  whiteList["background-color"] = true;
  whiteList["background-image"] = true;
  whiteList["background-origin"] = true;
  whiteList["background-position"] = true;
  whiteList["background-repeat"] = true;
  whiteList["background-size"] = true;
  whiteList["baseline-shift"] = false;
  whiteList["binding"] = false;
  whiteList["bleed"] = false;
  whiteList["bookmark-label"] = false;
  whiteList["bookmark-level"] = false;
  whiteList["bookmark-state"] = false;
  whiteList["border"] = true;
  whiteList["border-bottom"] = true;
  whiteList["border-bottom-color"] = true;
  whiteList["border-bottom-left-radius"] = true;
  whiteList["border-bottom-right-radius"] = true;
  whiteList["border-bottom-style"] = true;
  whiteList["border-bottom-width"] = true;
  whiteList["border-collapse"] = true;
  whiteList["border-color"] = true;
  whiteList["border-image"] = true;
  whiteList["border-image-outset"] = true;
  whiteList["border-image-repeat"] = true;
  whiteList["border-image-slice"] = true;
  whiteList["border-image-source"] = true;
  whiteList["border-image-width"] = true;
  whiteList["border-left"] = true;
  whiteList["border-left-color"] = true;
  whiteList["border-left-style"] = true;
  whiteList["border-left-width"] = true;
  whiteList["border-radius"] = true;
  whiteList["border-right"] = true;
  whiteList["border-right-color"] = true;
  whiteList["border-right-style"] = true;
  whiteList["border-right-width"] = true;
  whiteList["border-spacing"] = true;
  whiteList["border-style"] = true;
  whiteList["border-top"] = true;
  whiteList["border-top-color"] = true;
  whiteList["border-top-left-radius"] = true;
  whiteList["border-top-right-radius"] = true;
  whiteList["border-top-style"] = true;
  whiteList["border-top-width"] = true;
  whiteList["border-width"] = true;
  whiteList["bottom"] = false;
  whiteList["box-decoration-break"] = true;
  whiteList["box-shadow"] = true;
  whiteList["box-sizing"] = true;
  whiteList["box-snap"] = true;
  whiteList["box-suppress"] = true;
  whiteList["break-after"] = true;
  whiteList["break-before"] = true;
  whiteList["break-inside"] = true;
  whiteList["caption-side"] = false;
  whiteList["chains"] = false;
  whiteList["clear"] = true;
  whiteList["clip"] = false;
  whiteList["clip-path"] = false;
  whiteList["clip-rule"] = false;
  whiteList["color"] = true;
  whiteList["color-interpolation-filters"] = true;
  whiteList["column-count"] = false;
  whiteList["column-fill"] = false;
  whiteList["column-gap"] = false;
  whiteList["column-rule"] = false;
  whiteList["column-rule-color"] = false;
  whiteList["column-rule-style"] = false;
  whiteList["column-rule-width"] = false;
  whiteList["column-span"] = false;
  whiteList["column-width"] = false;
  whiteList["columns"] = false;
  whiteList["contain"] = false;
  whiteList["content"] = false;
  whiteList["counter-increment"] = false;
  whiteList["counter-reset"] = false;
  whiteList["counter-set"] = false;
  whiteList["crop"] = false;
  whiteList["cue"] = false;
  whiteList["cue-after"] = false;
  whiteList["cue-before"] = false;
  whiteList["cursor"] = false;
  whiteList["direction"] = false;
  whiteList["display"] = true;
  whiteList["display-inside"] = true;
  whiteList["display-list"] = true;
  whiteList["display-outside"] = true;
  whiteList["dominant-baseline"] = false;
  whiteList["elevation"] = false;
  whiteList["empty-cells"] = false;
  whiteList["filter"] = false;
  whiteList["flex"] = false;
  whiteList["flex-basis"] = false;
  whiteList["flex-direction"] = false;
  whiteList["flex-flow"] = false;
  whiteList["flex-grow"] = false;
  whiteList["flex-shrink"] = false;
  whiteList["flex-wrap"] = false;
  whiteList["float"] = false;
  whiteList["float-offset"] = false;
  whiteList["flood-color"] = false;
  whiteList["flood-opacity"] = false;
  whiteList["flow-from"] = false;
  whiteList["flow-into"] = false;
  whiteList["font"] = true;
  whiteList["font-family"] = true;
  whiteList["font-feature-settings"] = true;
  whiteList["font-kerning"] = true;
  whiteList["font-language-override"] = true;
  whiteList["font-size"] = true;
  whiteList["font-size-adjust"] = true;
  whiteList["font-stretch"] = true;
  whiteList["font-style"] = true;
  whiteList["font-synthesis"] = true;
  whiteList["font-variant"] = true;
  whiteList["font-variant-alternates"] = true;
  whiteList["font-variant-caps"] = true;
  whiteList["font-variant-east-asian"] = true;
  whiteList["font-variant-ligatures"] = true;
  whiteList["font-variant-numeric"] = true;
  whiteList["font-variant-position"] = true;
  whiteList["font-weight"] = true;
  whiteList["grid"] = false;
  whiteList["grid-area"] = false;
  whiteList["grid-auto-columns"] = false;
  whiteList["grid-auto-flow"] = false;
  whiteList["grid-auto-rows"] = false;
  whiteList["grid-column"] = false;
  whiteList["grid-column-end"] = false;
  whiteList["grid-column-start"] = false;
  whiteList["grid-row"] = false;
  whiteList["grid-row-end"] = false;
  whiteList["grid-row-start"] = false;
  whiteList["grid-template"] = false;
  whiteList["grid-template-areas"] = false;
  whiteList["grid-template-columns"] = false;
  whiteList["grid-template-rows"] = false;
  whiteList["hanging-punctuation"] = false;
  whiteList["height"] = true;
  whiteList["hyphens"] = false;
  whiteList["icon"] = false;
  whiteList["image-orientation"] = false;
  whiteList["image-resolution"] = false;
  whiteList["ime-mode"] = false;
  whiteList["initial-letters"] = false;
  whiteList["inline-box-align"] = false;
  whiteList["justify-content"] = false;
  whiteList["justify-items"] = false;
  whiteList["justify-self"] = false;
  whiteList["left"] = false;
  whiteList["letter-spacing"] = true;
  whiteList["lighting-color"] = true;
  whiteList["line-box-contain"] = false;
  whiteList["line-break"] = false;
  whiteList["line-grid"] = false;
  whiteList["line-height"] = false;
  whiteList["line-snap"] = false;
  whiteList["line-stacking"] = false;
  whiteList["line-stacking-ruby"] = false;
  whiteList["line-stacking-shift"] = false;
  whiteList["line-stacking-strategy"] = false;
  whiteList["list-style"] = true;
  whiteList["list-style-image"] = true;
  whiteList["list-style-position"] = true;
  whiteList["list-style-type"] = true;
  whiteList["margin"] = true;
  whiteList["margin-bottom"] = true;
  whiteList["margin-left"] = true;
  whiteList["margin-right"] = true;
  whiteList["margin-top"] = true;
  whiteList["marker-offset"] = false;
  whiteList["marker-side"] = false;
  whiteList["marks"] = false;
  whiteList["mask"] = false;
  whiteList["mask-box"] = false;
  whiteList["mask-box-outset"] = false;
  whiteList["mask-box-repeat"] = false;
  whiteList["mask-box-slice"] = false;
  whiteList["mask-box-source"] = false;
  whiteList["mask-box-width"] = false;
  whiteList["mask-clip"] = false;
  whiteList["mask-image"] = false;
  whiteList["mask-origin"] = false;
  whiteList["mask-position"] = false;
  whiteList["mask-repeat"] = false;
  whiteList["mask-size"] = false;
  whiteList["mask-source-type"] = false;
  whiteList["mask-type"] = false;
  whiteList["max-height"] = true;
  whiteList["max-lines"] = false;
  whiteList["max-width"] = true;
  whiteList["min-height"] = true;
  whiteList["min-width"] = true;
  whiteList["move-to"] = false;
  whiteList["nav-down"] = false;
  whiteList["nav-index"] = false;
  whiteList["nav-left"] = false;
  whiteList["nav-right"] = false;
  whiteList["nav-up"] = false;
  whiteList["object-fit"] = false;
  whiteList["object-position"] = false;
  whiteList["opacity"] = false;
  whiteList["order"] = false;
  whiteList["orphans"] = false;
  whiteList["outline"] = false;
  whiteList["outline-color"] = false;
  whiteList["outline-offset"] = false;
  whiteList["outline-style"] = false;
  whiteList["outline-width"] = false;
  whiteList["overflow"] = false;
  whiteList["overflow-wrap"] = false;
  whiteList["overflow-x"] = false;
  whiteList["overflow-y"] = false;
  whiteList["padding"] = true;
  whiteList["padding-bottom"] = true;
  whiteList["padding-left"] = true;
  whiteList["padding-right"] = true;
  whiteList["padding-top"] = true;
  whiteList["page"] = false;
  whiteList["page-break-after"] = false;
  whiteList["page-break-before"] = false;
  whiteList["page-break-inside"] = false;
  whiteList["page-policy"] = false;
  whiteList["pause"] = false;
  whiteList["pause-after"] = false;
  whiteList["pause-before"] = false;
  whiteList["perspective"] = false;
  whiteList["perspective-origin"] = false;
  whiteList["pitch"] = false;
  whiteList["pitch-range"] = false;
  whiteList["play-during"] = false;
  whiteList["position"] = false;
  whiteList["presentation-level"] = false;
  whiteList["quotes"] = false;
  whiteList["region-fragment"] = false;
  whiteList["resize"] = false;
  whiteList["rest"] = false;
  whiteList["rest-after"] = false;
  whiteList["rest-before"] = false;
  whiteList["richness"] = false;
  whiteList["right"] = false;
  whiteList["rotation"] = false;
  whiteList["rotation-point"] = false;
  whiteList["ruby-align"] = false;
  whiteList["ruby-merge"] = false;
  whiteList["ruby-position"] = false;
  whiteList["shape-image-threshold"] = false;
  whiteList["shape-outside"] = false;
  whiteList["shape-margin"] = false;
  whiteList["size"] = false;
  whiteList["speak"] = false;
  whiteList["speak-as"] = false;
  whiteList["speak-header"] = false;
  whiteList["speak-numeral"] = false;
  whiteList["speak-punctuation"] = false;
  whiteList["speech-rate"] = false;
  whiteList["stress"] = false;
  whiteList["string-set"] = false;
  whiteList["tab-size"] = false;
  whiteList["table-layout"] = false;
  whiteList["text-align"] = true;
  whiteList["text-align-last"] = true;
  whiteList["text-combine-upright"] = true;
  whiteList["text-decoration"] = true;
  whiteList["text-decoration-color"] = true;
  whiteList["text-decoration-line"] = true;
  whiteList["text-decoration-skip"] = true;
  whiteList["text-decoration-style"] = true;
  whiteList["text-emphasis"] = true;
  whiteList["text-emphasis-color"] = true;
  whiteList["text-emphasis-position"] = true;
  whiteList["text-emphasis-style"] = true;
  whiteList["text-height"] = true;
  whiteList["text-indent"] = true;
  whiteList["text-justify"] = true;
  whiteList["text-orientation"] = true;
  whiteList["text-overflow"] = true;
  whiteList["text-shadow"] = true;
  whiteList["text-space-collapse"] = true;
  whiteList["text-transform"] = true;
  whiteList["text-underline-position"] = true;
  whiteList["text-wrap"] = true;
  whiteList["top"] = false;
  whiteList["transform"] = false;
  whiteList["transform-origin"] = false;
  whiteList["transform-style"] = false;
  whiteList["transition"] = false;
  whiteList["transition-delay"] = false;
  whiteList["transition-duration"] = false;
  whiteList["transition-property"] = false;
  whiteList["transition-timing-function"] = false;
  whiteList["unicode-bidi"] = false;
  whiteList["vertical-align"] = false;
  whiteList["visibility"] = false;
  whiteList["voice-balance"] = false;
  whiteList["voice-duration"] = false;
  whiteList["voice-family"] = false;
  whiteList["voice-pitch"] = false;
  whiteList["voice-range"] = false;
  whiteList["voice-rate"] = false;
  whiteList["voice-stress"] = false;
  whiteList["voice-volume"] = false;
  whiteList["volume"] = false;
  whiteList["white-space"] = false;
  whiteList["widows"] = false;
  whiteList["width"] = true;
  whiteList["will-change"] = false;
  whiteList["word-break"] = true;
  whiteList["word-spacing"] = true;
  whiteList["word-wrap"] = true;
  whiteList["wrap-flow"] = false;
  whiteList["wrap-through"] = false;
  whiteList["writing-mode"] = false;
  whiteList["z-index"] = false;
  return whiteList;
}
function onAttr(name, value, options) {
}
function onIgnoreAttr(name, value, options) {
}
var REGEXP_URL_JAVASCRIPT = /javascript\s*\:/img;
function safeAttrValue$1(name, value) {
  if (REGEXP_URL_JAVASCRIPT.test(value))
    return "";
  return value;
}
_default.whiteList = getDefaultWhiteList$1();
_default.getDefaultWhiteList = getDefaultWhiteList$1;
_default.onAttr = onAttr;
_default.onIgnoreAttr = onIgnoreAttr;
_default.safeAttrValue = safeAttrValue$1;
var util$1 = {
  indexOf: function indexOf(arr, item) {
    var i, j;
    if (Array.prototype.indexOf) {
      return arr.indexOf(item);
    }
    for (i = 0, j = arr.length; i < j; i++) {
      if (arr[i] === item) {
        return i;
      }
    }
    return -1;
  },
  forEach: function forEach(arr, fn, scope) {
    var i, j;
    if (Array.prototype.forEach) {
      return arr.forEach(fn, scope);
    }
    for (i = 0, j = arr.length; i < j; i++) {
      fn.call(scope, arr[i], i, arr);
    }
  },
  trim: function trim(str) {
    if (String.prototype.trim) {
      return str.trim();
    }
    return str.replace(/(^\s*)|(\s*$)/g, "");
  },
  trimRight: function trimRight(str) {
    if (String.prototype.trimRight) {
      return str.trimRight();
    }
    return str.replace(/(\s*$)/g, "");
  }
};
var _$3 = util$1;
function parseStyle$1(css2, onAttr2) {
  css2 = _$3.trimRight(css2);
  if (css2[css2.length - 1] !== ";")
    css2 += ";";
  var cssLength = css2.length;
  var isParenthesisOpen = false;
  var lastPos = 0;
  var i = 0;
  var retCSS = "";
  function addNewAttr() {
    if (!isParenthesisOpen) {
      var source = _$3.trim(css2.slice(lastPos, i));
      var j2 = source.indexOf(":");
      if (j2 !== -1) {
        var name = _$3.trim(source.slice(0, j2));
        var value = _$3.trim(source.slice(j2 + 1));
        if (name) {
          var ret = onAttr2(lastPos, retCSS.length, name, value, source);
          if (ret)
            retCSS += ret + "; ";
        }
      }
    }
    lastPos = i + 1;
  }
  for (; i < cssLength; i++) {
    var c = css2[i];
    if (c === "/" && css2[i + 1] === "*") {
      var j = css2.indexOf("*/", i + 2);
      if (j === -1)
        break;
      i = j + 1;
      lastPos = i + 1;
      isParenthesisOpen = false;
    } else if (c === "(") {
      isParenthesisOpen = true;
    } else if (c === ")") {
      isParenthesisOpen = false;
    } else if (c === ";") {
      if (isParenthesisOpen)
        ;
      else {
        addNewAttr();
      }
    } else if (c === "\n") {
      addNewAttr();
    }
  }
  return _$3.trim(retCSS);
}
var parser$2 = parseStyle$1;
var DEFAULT$1 = _default;
var parseStyle = parser$2;
function isNull$1(obj) {
  return obj === void 0 || obj === null;
}
function shallowCopyObject$1(obj) {
  var ret = {};
  for (var i in obj) {
    ret[i] = obj[i];
  }
  return ret;
}
function FilterCSS$2(options) {
  options = shallowCopyObject$1(options || {});
  options.whiteList = options.whiteList || DEFAULT$1.whiteList;
  options.onAttr = options.onAttr || DEFAULT$1.onAttr;
  options.onIgnoreAttr = options.onIgnoreAttr || DEFAULT$1.onIgnoreAttr;
  options.safeAttrValue = options.safeAttrValue || DEFAULT$1.safeAttrValue;
  this.options = options;
}
FilterCSS$2.prototype.process = function(css2) {
  css2 = css2 || "";
  css2 = css2.toString();
  if (!css2)
    return "";
  var me = this;
  var options = me.options;
  var whiteList = options.whiteList;
  var onAttr2 = options.onAttr;
  var onIgnoreAttr2 = options.onIgnoreAttr;
  var safeAttrValue2 = options.safeAttrValue;
  var retCSS = parseStyle(css2, function(sourcePosition, position, name, value, source) {
    var check = whiteList[name];
    var isWhite = false;
    if (check === true)
      isWhite = check;
    else if (typeof check === "function")
      isWhite = check(value);
    else if (check instanceof RegExp)
      isWhite = check.test(value);
    if (isWhite !== true)
      isWhite = false;
    value = safeAttrValue2(name, value);
    if (!value)
      return;
    var opts = {
      position,
      sourcePosition,
      source,
      isWhite
    };
    if (isWhite) {
      var ret = onAttr2(name, value, opts);
      if (isNull$1(ret)) {
        return name + ":" + value;
      } else {
        return ret;
      }
    } else {
      var ret = onIgnoreAttr2(name, value, opts);
      if (!isNull$1(ret)) {
        return ret;
      }
    }
  });
  return retCSS;
};
var css = FilterCSS$2;
(function(module, exports) {
  var DEFAULT2 = _default;
  var FilterCSS2 = css;
  function filterCSS(html, options) {
    var xss2 = new FilterCSS2(options);
    return xss2.process(html);
  }
  exports = module.exports = filterCSS;
  exports.FilterCSS = FilterCSS2;
  for (var i in DEFAULT2)
    exports[i] = DEFAULT2[i];
  if (typeof window !== "undefined") {
    window.filterCSS = module.exports;
  }
})(lib, libExports);
const require$$0 = libExports;
var util = {
  indexOf: function indexOf2(arr, item) {
    var i, j;
    if (Array.prototype.indexOf) {
      return arr.indexOf(item);
    }
    for (i = 0, j = arr.length; i < j; i++) {
      if (arr[i] === item) {
        return i;
      }
    }
    return -1;
  },
  forEach: function forEach2(arr, fn, scope) {
    var i, j;
    if (Array.prototype.forEach) {
      return arr.forEach(fn, scope);
    }
    for (i = 0, j = arr.length; i < j; i++) {
      fn.call(scope, arr[i], i, arr);
    }
  },
  trim: function trim2(str) {
    if (String.prototype.trim) {
      return str.trim();
    }
    return str.replace(/(^\s*)|(\s*$)/g, "");
  },
  spaceIndex: function spaceIndex(str) {
    var reg = /\s|\n|\t/;
    var match = reg.exec(str);
    return match ? match.index : -1;
  }
};
var FilterCSS$1 = require$$0.FilterCSS;
var getDefaultCSSWhiteList = require$$0.getDefaultWhiteList;
var _$2 = util;
function getDefaultWhiteList() {
  return {
    a: ["target", "href", "title"],
    abbr: ["title"],
    address: [],
    area: ["shape", "coords", "href", "alt"],
    article: [],
    aside: [],
    audio: ["autoplay", "controls", "crossorigin", "loop", "muted", "preload", "src"],
    b: [],
    bdi: ["dir"],
    bdo: ["dir"],
    big: [],
    blockquote: ["cite"],
    br: [],
    caption: [],
    center: [],
    cite: [],
    code: [],
    col: ["align", "valign", "span", "width"],
    colgroup: ["align", "valign", "span", "width"],
    dd: [],
    del: ["datetime"],
    details: ["open"],
    div: [],
    dl: [],
    dt: [],
    em: [],
    figcaption: [],
    figure: [],
    font: ["color", "size", "face"],
    footer: [],
    h1: [],
    h2: [],
    h3: [],
    h4: [],
    h5: [],
    h6: [],
    header: [],
    hr: [],
    i: [],
    img: ["src", "alt", "title", "width", "height"],
    ins: ["datetime"],
    li: [],
    mark: [],
    nav: [],
    ol: [],
    p: [],
    pre: [],
    s: [],
    section: [],
    small: [],
    span: [],
    sub: [],
    summary: [],
    sup: [],
    strong: [],
    strike: [],
    table: ["width", "border", "align", "valign"],
    tbody: ["align", "valign"],
    td: ["width", "rowspan", "colspan", "align", "valign"],
    tfoot: ["align", "valign"],
    th: ["width", "rowspan", "colspan", "align", "valign"],
    thead: ["align", "valign"],
    tr: ["rowspan", "align", "valign"],
    tt: [],
    u: [],
    ul: [],
    video: ["autoplay", "controls", "crossorigin", "loop", "muted", "playsinline", "poster", "preload", "src", "height", "width"]
  };
}
var defaultCSSFilter = new FilterCSS$1();
function onTag(tag, html, options) {
}
function onIgnoreTag(tag, html, options) {
}
function onTagAttr(tag, name, value) {
}
function onIgnoreTagAttr(tag, name, value) {
}
function escapeHtml(html) {
  return html.replace(REGEXP_LT, "&lt;").replace(REGEXP_GT, "&gt;");
}
function safeAttrValue(tag, name, value, cssFilter) {
  value = friendlyAttrValue(value);
  if (name === "href" || name === "src") {
    value = _$2.trim(value);
    if (value === "#")
      return "#";
    if (!(value.substr(0, 7) === "http://" || value.substr(0, 8) === "https://" || value.substr(0, 7) === "mailto:" || value.substr(0, 4) === "tel:" || value.substr(0, 11) === "data:image/" || value.substr(0, 6) === "ftp://" || value.substr(0, 2) === "./" || value.substr(0, 3) === "../" || value[0] === "#" || value[0] === "/")) {
      return "";
    }
  } else if (name === "background") {
    REGEXP_DEFAULT_ON_TAG_ATTR_4.lastIndex = 0;
    if (REGEXP_DEFAULT_ON_TAG_ATTR_4.test(value)) {
      return "";
    }
  } else if (name === "style") {
    REGEXP_DEFAULT_ON_TAG_ATTR_7.lastIndex = 0;
    if (REGEXP_DEFAULT_ON_TAG_ATTR_7.test(value)) {
      return "";
    }
    REGEXP_DEFAULT_ON_TAG_ATTR_8.lastIndex = 0;
    if (REGEXP_DEFAULT_ON_TAG_ATTR_8.test(value)) {
      REGEXP_DEFAULT_ON_TAG_ATTR_4.lastIndex = 0;
      if (REGEXP_DEFAULT_ON_TAG_ATTR_4.test(value)) {
        return "";
      }
    }
    if (cssFilter !== false) {
      cssFilter = cssFilter || defaultCSSFilter;
      value = cssFilter.process(value);
    }
  }
  value = escapeAttrValue(value);
  return value;
}
var REGEXP_LT = /</g;
var REGEXP_GT = />/g;
var REGEXP_QUOTE = /"/g;
var REGEXP_QUOTE_2 = /&quot;/g;
var REGEXP_ATTR_VALUE_1 = /&#([a-zA-Z0-9]*);?/gim;
var REGEXP_ATTR_VALUE_COLON = /&colon;?/gim;
var REGEXP_ATTR_VALUE_NEWLINE = /&newline;?/gim;
var REGEXP_DEFAULT_ON_TAG_ATTR_4 = /((j\s*a\s*v\s*a|v\s*b|l\s*i\s*v\s*e)\s*s\s*c\s*r\s*i\s*p\s*t\s*|m\s*o\s*c\s*h\s*a)\:/gi;
var REGEXP_DEFAULT_ON_TAG_ATTR_7 = /e\s*x\s*p\s*r\s*e\s*s\s*s\s*i\s*o\s*n\s*\(.*/gi;
var REGEXP_DEFAULT_ON_TAG_ATTR_8 = /u\s*r\s*l\s*\(.*/gi;
function escapeQuote(str) {
  return str.replace(REGEXP_QUOTE, "&quot;");
}
function unescapeQuote(str) {
  return str.replace(REGEXP_QUOTE_2, '"');
}
function escapeHtmlEntities(str) {
  return str.replace(REGEXP_ATTR_VALUE_1, function replaceUnicode(str2, code) {
    return code[0] === "x" || code[0] === "X" ? String.fromCharCode(parseInt(code.substr(1), 16)) : String.fromCharCode(parseInt(code, 10));
  });
}
function escapeDangerHtml5Entities(str) {
  return str.replace(REGEXP_ATTR_VALUE_COLON, ":").replace(REGEXP_ATTR_VALUE_NEWLINE, " ");
}
function clearNonPrintableCharacter(str) {
  var str2 = "";
  for (var i = 0, len = str.length; i < len; i++) {
    str2 += str.charCodeAt(i) < 32 ? " " : str.charAt(i);
  }
  return _$2.trim(str2);
}
function friendlyAttrValue(str) {
  str = unescapeQuote(str);
  str = escapeHtmlEntities(str);
  str = escapeDangerHtml5Entities(str);
  str = clearNonPrintableCharacter(str);
  return str;
}
function escapeAttrValue(str) {
  str = escapeQuote(str);
  str = escapeHtml(str);
  return str;
}
function onIgnoreTagStripAll() {
  return "";
}
function StripTagBody(tags, next) {
  if (typeof next !== "function") {
    next = function next2() {
    };
  }
  var isRemoveAllTag = !Array.isArray(tags);
  function isRemoveTag(tag) {
    if (isRemoveAllTag)
      return true;
    return _$2.indexOf(tags, tag) !== -1;
  }
  var removeList = [];
  var posStart = false;
  return {
    onIgnoreTag: function onIgnoreTag2(tag, html, options) {
      if (isRemoveTag(tag)) {
        if (options.isClosing) {
          var ret = "[/removed]";
          var end = options.position + ret.length;
          removeList.push([posStart !== false ? posStart : options.position, end]);
          posStart = false;
          return ret;
        } else {
          if (!posStart) {
            posStart = options.position;
          }
          return "[removed]";
        }
      } else {
        return next(tag, html, options);
      }
    },
    remove: function remove(html) {
      var rethtml = "";
      var lastPos = 0;
      _$2.forEach(removeList, function(pos) {
        rethtml += html.slice(lastPos, pos[0]);
        lastPos = pos[1];
      });
      rethtml += html.slice(lastPos);
      return rethtml;
    }
  };
}
function stripCommentTag(html) {
  var retHtml = "";
  var lastPos = 0;
  while (lastPos < html.length) {
    var i = html.indexOf("<!--", lastPos);
    if (i === -1) {
      retHtml += html.slice(lastPos);
      break;
    }
    retHtml += html.slice(lastPos, i);
    var j = html.indexOf("-->", i);
    if (j === -1) {
      break;
    }
    lastPos = j + 3;
  }
  return retHtml;
}
function stripBlankChar(html) {
  var chars = html.split("");
  chars = chars.filter(function(char) {
    var c = char.charCodeAt(0);
    if (c === 127)
      return false;
    if (c <= 31) {
      if (c === 10 || c === 13)
        return true;
      return false;
    }
    return true;
  });
  return chars.join("");
}
_default$1.whiteList = getDefaultWhiteList();
_default$1.getDefaultWhiteList = getDefaultWhiteList;
_default$1.onTag = onTag;
_default$1.onIgnoreTag = onIgnoreTag;
_default$1.onTagAttr = onTagAttr;
_default$1.onIgnoreTagAttr = onIgnoreTagAttr;
_default$1.safeAttrValue = safeAttrValue;
_default$1.escapeHtml = escapeHtml;
_default$1.escapeQuote = escapeQuote;
_default$1.unescapeQuote = unescapeQuote;
_default$1.escapeHtmlEntities = escapeHtmlEntities;
_default$1.escapeDangerHtml5Entities = escapeDangerHtml5Entities;
_default$1.clearNonPrintableCharacter = clearNonPrintableCharacter;
_default$1.friendlyAttrValue = friendlyAttrValue;
_default$1.escapeAttrValue = escapeAttrValue;
_default$1.onIgnoreTagStripAll = onIgnoreTagStripAll;
_default$1.StripTagBody = StripTagBody;
_default$1.stripCommentTag = stripCommentTag;
_default$1.stripBlankChar = stripBlankChar;
_default$1.cssFilter = defaultCSSFilter;
_default$1.getDefaultCSSWhiteList = getDefaultCSSWhiteList;
var parser$1 = {};
var _$1 = util;
function getTagName(html) {
  var i = _$1.spaceIndex(html);
  if (i === -1) {
    var tagName = html.slice(1, -1);
  } else {
    var tagName = html.slice(1, i + 1);
  }
  tagName = _$1.trim(tagName).toLowerCase();
  if (tagName.slice(0, 1) === "/")
    tagName = tagName.slice(1);
  if (tagName.slice(-1) === "/")
    tagName = tagName.slice(0, -1);
  return tagName;
}
function isClosing(html) {
  return html.slice(0, 2) === "</";
}
function parseTag$1(html, onTag2, escapeHtml2) {
  var rethtml = "";
  var lastPos = 0;
  var tagStart = false;
  var quoteStart = false;
  var currentPos = 0;
  var len = html.length;
  var currentTagName = "";
  var currentHtml = "";
  chariterator:
    for (currentPos = 0; currentPos < len; currentPos++) {
      var c = html.charAt(currentPos);
      if (tagStart === false) {
        if (c === "<") {
          tagStart = currentPos;
          continue;
        }
      } else {
        if (quoteStart === false) {
          if (c === "<") {
            rethtml += escapeHtml2(html.slice(lastPos, currentPos));
            tagStart = currentPos;
            lastPos = currentPos;
            continue;
          }
          if (c === ">") {
            rethtml += escapeHtml2(html.slice(lastPos, tagStart));
            currentHtml = html.slice(tagStart, currentPos + 1);
            currentTagName = getTagName(currentHtml);
            rethtml += onTag2(tagStart, rethtml.length, currentTagName, currentHtml, isClosing(currentHtml));
            lastPos = currentPos + 1;
            tagStart = false;
            continue;
          }
          if (c === '"' || c === "'") {
            var i = 1;
            var ic = html.charAt(currentPos - i);
            while (ic.trim() === "" || ic === "=") {
              if (ic === "=") {
                quoteStart = c;
                continue chariterator;
              }
              ic = html.charAt(currentPos - ++i);
            }
          }
        } else {
          if (c === quoteStart) {
            quoteStart = false;
            continue;
          }
        }
      }
    }
  if (lastPos < html.length) {
    rethtml += escapeHtml2(html.substr(lastPos));
  }
  return rethtml;
}
var REGEXP_ILLEGAL_ATTR_NAME = /[^a-zA-Z0-9_:\.\-]/gim;
function parseAttr$1(html, onAttr2) {
  var lastPos = 0;
  var retAttrs = [];
  var tmpName = false;
  var len = html.length;
  function addAttr(name, value) {
    name = _$1.trim(name);
    name = name.replace(REGEXP_ILLEGAL_ATTR_NAME, "").toLowerCase();
    if (name.length < 1)
      return;
    var ret = onAttr2(name, value || "");
    if (ret)
      retAttrs.push(ret);
  }
  for (var i = 0; i < len; i++) {
    var c = html.charAt(i);
    var v, j;
    if (tmpName === false && c === "=") {
      tmpName = html.slice(lastPos, i);
      lastPos = i + 1;
      continue;
    }
    if (tmpName !== false) {
      if (i === lastPos && (c === '"' || c === "'") && html.charAt(i - 1) === "=") {
        j = html.indexOf(c, i + 1);
        if (j === -1) {
          break;
        } else {
          v = _$1.trim(html.slice(lastPos + 1, j));
          addAttr(tmpName, v);
          tmpName = false;
          i = j;
          lastPos = i + 1;
          continue;
        }
      }
    }
    if (/\s|\n|\t/.test(c)) {
      html = html.replace(/\s|\n|\t/g, " ");
      if (tmpName === false) {
        j = findNextEqual(html, i);
        if (j === -1) {
          v = _$1.trim(html.slice(lastPos, i));
          addAttr(v);
          tmpName = false;
          lastPos = i + 1;
          continue;
        } else {
          i = j - 1;
          continue;
        }
      } else {
        j = findBeforeEqual(html, i - 1);
        if (j === -1) {
          v = _$1.trim(html.slice(lastPos, i));
          v = stripQuoteWrap(v);
          addAttr(tmpName, v);
          tmpName = false;
          lastPos = i + 1;
          continue;
        } else {
          continue;
        }
      }
    }
  }
  if (lastPos < html.length) {
    if (tmpName === false) {
      addAttr(html.slice(lastPos));
    } else {
      addAttr(tmpName, stripQuoteWrap(_$1.trim(html.slice(lastPos))));
    }
  }
  return _$1.trim(retAttrs.join(" "));
}
function findNextEqual(str, i) {
  for (; i < str.length; i++) {
    var c = str[i];
    if (c === " ")
      continue;
    if (c === "=")
      return i;
    return -1;
  }
}
function findBeforeEqual(str, i) {
  for (; i > 0; i--) {
    var c = str[i];
    if (c === " ")
      continue;
    if (c === "=")
      return i;
    return -1;
  }
}
function isQuoteWrapString(text) {
  if (text[0] === '"' && text[text.length - 1] === '"' || text[0] === "'" && text[text.length - 1] === "'") {
    return true;
  } else {
    return false;
  }
}
function stripQuoteWrap(text) {
  if (isQuoteWrapString(text)) {
    return text.substr(1, text.length - 2);
  } else {
    return text;
  }
}
parser$1.parseTag = parseTag$1;
parser$1.parseAttr = parseAttr$1;
var FilterCSS = require$$0.FilterCSS;
var DEFAULT = _default$1;
var parser = parser$1;
var parseTag = parser.parseTag;
var parseAttr = parser.parseAttr;
var _ = util;
function isNull2(obj) {
  return obj === void 0 || obj === null;
}
function getAttrs(html) {
  var i = _.spaceIndex(html);
  if (i === -1) {
    return {
      html: "",
      closing: html[html.length - 2] === "/"
    };
  }
  html = _.trim(html.slice(i + 1, -1));
  var isClosing2 = html[html.length - 1] === "/";
  if (isClosing2)
    html = _.trim(html.slice(0, -1));
  return {
    html,
    closing: isClosing2
  };
}
function shallowCopyObject(obj) {
  var ret = {};
  for (var i in obj) {
    ret[i] = obj[i];
  }
  return ret;
}
function FilterXSS(options) {
  options = shallowCopyObject(options || {});
  if (options.stripIgnoreTag) {
    if (options.onIgnoreTag) {
      console.error('Notes: cannot use these two options "stripIgnoreTag" and "onIgnoreTag" at the same time');
    }
    options.onIgnoreTag = DEFAULT.onIgnoreTagStripAll;
  }
  options.whiteList = options.whiteList || options.allowList || DEFAULT.whiteList;
  options.onTag = options.onTag || DEFAULT.onTag;
  options.onTagAttr = options.onTagAttr || DEFAULT.onTagAttr;
  options.onIgnoreTag = options.onIgnoreTag || DEFAULT.onIgnoreTag;
  options.onIgnoreTagAttr = options.onIgnoreTagAttr || DEFAULT.onIgnoreTagAttr;
  options.safeAttrValue = options.safeAttrValue || DEFAULT.safeAttrValue;
  options.escapeHtml = options.escapeHtml || DEFAULT.escapeHtml;
  this.options = options;
  if (options.css === false) {
    this.cssFilter = false;
  } else {
    options.css = options.css || {};
    this.cssFilter = new FilterCSS(options.css);
  }
}
FilterXSS.prototype.process = function(html) {
  html = html || "";
  html = html.toString();
  if (!html)
    return "";
  var me = this;
  var options = me.options;
  var whiteList = options.whiteList;
  var onTag2 = options.onTag;
  var onIgnoreTag2 = options.onIgnoreTag;
  var onTagAttr2 = options.onTagAttr;
  var onIgnoreTagAttr2 = options.onIgnoreTagAttr;
  var safeAttrValue2 = options.safeAttrValue;
  var escapeHtml2 = options.escapeHtml;
  var cssFilter = me.cssFilter;
  if (options.stripBlankChar) {
    html = DEFAULT.stripBlankChar(html);
  }
  if (!options.allowCommentTag) {
    html = DEFAULT.stripCommentTag(html);
  }
  var stripIgnoreTagBody = false;
  if (options.stripIgnoreTagBody) {
    var stripIgnoreTagBody = DEFAULT.StripTagBody(options.stripIgnoreTagBody, onIgnoreTag2);
    onIgnoreTag2 = stripIgnoreTagBody.onIgnoreTag;
  }
  var retHtml = parseTag(html, function(sourcePosition, position, tag, html2, isClosing2) {
    var info = {
      sourcePosition,
      position,
      isClosing: isClosing2,
      isWhite: whiteList.hasOwnProperty(tag)
    };
    var ret = onTag2(tag, html2, info);
    if (!isNull2(ret))
      return ret;
    if (info.isWhite) {
      if (info.isClosing) {
        return "</" + tag + ">";
      }
      var attrs = getAttrs(html2);
      var whiteAttrList = whiteList[tag];
      var attrsHtml = parseAttr(attrs.html, function(name, value) {
        var isWhiteAttr = _.indexOf(whiteAttrList, name) !== -1;
        var ret2 = onTagAttr2(tag, name, value, isWhiteAttr);
        if (!isNull2(ret2))
          return ret2;
        if (isWhiteAttr) {
          value = safeAttrValue2(tag, name, value, cssFilter);
          if (value) {
            return name + '="' + value + '"';
          } else {
            return name;
          }
        } else {
          var ret2 = onIgnoreTagAttr2(tag, name, value, isWhiteAttr);
          if (!isNull2(ret2))
            return ret2;
          return;
        }
      });
      var html2 = "<" + tag;
      if (attrsHtml)
        html2 += " " + attrsHtml;
      if (attrs.closing)
        html2 += " /";
      html2 += ">";
      return html2;
    } else {
      var ret = onIgnoreTag2(tag, html2, info);
      if (!isNull2(ret))
        return ret;
      return escapeHtml2(html2);
    }
  }, escapeHtml2);
  if (stripIgnoreTagBody) {
    retHtml = stripIgnoreTagBody.remove(retHtml);
  }
  return retHtml;
};
var xss = FilterXSS;
(function(module, exports) {
  var DEFAULT2 = _default$1;
  var parser2 = parser$1;
  var FilterXSS2 = xss;
  function filterXSS(html, options) {
    var xss2 = new FilterXSS2(options);
    return xss2.process(html);
  }
  exports = module.exports = filterXSS;
  exports.filterXSS = filterXSS;
  exports.FilterXSS = FilterXSS2;
  for (var i in DEFAULT2)
    exports[i] = DEFAULT2[i];
  for (var i in parser2)
    exports[i] = parser2[i];
  if (typeof window !== "undefined") {
    window.filterXSS = module.exports;
  }
  function isWorkerEnv() {
    return typeof self !== "undefined" && typeof DedicatedWorkerGlobalScope !== "undefined" && self instanceof DedicatedWorkerGlobalScope;
  }
  if (isWorkerEnv()) {
    self.filterXSS = module.exports;
  }
})(lib$1, libExports$1);
var getWindow = function getWindow2() {
  return typeof window === "undefined" ? global : window;
};
var isWeb = function isWeb2() {
  return !(typeof window === "undefined");
};
var _win$2 = getWindow();
var reverseUrlAlphabet = "tcirzywvqlkjhgfbZQG_FLOWHSUBDNIMYREVKCAJxp57XP043891T62-modnaesu";
var urlAlphabet$1 = reverseUrlAlphabet.split("").reverse().join("");
var buffer;
var bufferOffset;
var allocBuffer = function allocBuffer2(bytes) {
  return new Uint8Array(new ArrayBuffer(bytes));
};
var randomFill = function randomFill2(buffer2) {
  return _win$2.crypto.getRandomValues(buffer2);
};
var defFillPool = function defFillPool2(bytes) {
  if (!buffer || buffer.length < bytes) {
    buffer = allocBuffer(bytes * 128);
    randomFill(buffer);
    bufferOffset = 0;
  } else if (bufferOffset + bytes > buffer.length) {
    randomFill(buffer);
    bufferOffset = 0;
  }
  bufferOffset += bytes;
};
var nanoid$2 = function nanoid$22(size) {
  if (size === void 0) {
    size = 21;
  }
  defFillPool(size -= 0);
  var uniq = "";
  for (var i = bufferOffset - size; i < bufferOffset; i++) {
    uniq += urlAlphabet$1[buffer[i] & 63];
  }
  return uniq;
};
var defRandomFunc = function defRandomFunc2(bytes) {
  defFillPool(bytes -= 0);
  return buffer.subarray(bufferOffset - bytes, bufferOffset);
};
var defCustomRandom = function defCustomRandom2(alphabet, defaultSize, randomFunc) {
  var mask = (2 << 31 - Math.clz32(alphabet.length - 1 | 1)) - 1;
  var step = Math.ceil(1.6 * mask * defaultSize / alphabet.length);
  return function(size) {
    if (size === void 0) {
      size = defaultSize;
    }
    var uniq = "";
    var bytes = randomFunc(step);
    var i = step;
    while (i--) {
      uniq += alphabet[bytes[i] & mask] || "";
      if (uniq.length === size) {
        return uniq;
      }
    }
  };
};
var customAlphabet$1 = function customAlphabet$12(alphabet, defaultSize) {
  if (defaultSize === void 0) {
    defaultSize = 21;
  }
  return defCustomRandom(alphabet, defaultSize, defRandomFunc);
};
function isIE(window2) {
  return isWeb() && (window2.document.all || window2.document.documentMode) && !window2.crypto && window2.msCrypto;
}
function initForIE(window2) {
  if (isIE(window2)) {
    window2.crypto = window2.msCrypto;
    var getRandomValuesDef = window2.crypto.getRandomValues;
    window2.crypto.getRandomValues = function(array) {
      var values = getRandomValuesDef.call(window2.crypto, array);
      var result = [];
      for (var i = 0; i < array.length; i++) {
        result[i] = values[i];
      }
      return result;
    };
  }
}
var _win$1 = getWindow();
initForIE(_win$1);
var MAX_UINT32_PLUS_ONE = 4294967296;
var urlAlphabet = urlAlphabet$1;
var nanoid$1 = nanoid$2;
var customAlphabet = customAlphabet$1;
var random = function random2() {
  if (!isWeb()) {
    return 0;
  }
  return _win$1.crypto.getRandomValues(new _win$1.Uint32Array(1))[0] / MAX_UINT32_PLUS_ONE;
};
var api = {
  urlAlphabet,
  nanoid: nanoid$1,
  customAlphabet
};
var _nanoid = Object.freeze({
  __proto__: null,
  random,
  api,
  "default": api
});
var xssOptions = {
  enableAttrs: true,
  enableHtml: true,
  enableUrl: true,
  html: {
    whiteList: {
      a: ["class", "style", "contenteditable", "data-id", "data-title", "data-size", "data-last-modified"],
      address: ["class", "style"],
      area: ["class", "style"],
      article: ["class", "style"],
      aside: ["class", "style"],
      audio: ["class", "style"],
      b: ["class", "style"],
      bdi: ["class", "style"],
      bdo: ["class", "style"],
      big: ["class", "style"],
      blockquote: ["class", "style"],
      br: ["class", "style"],
      caption: ["class", "style"],
      center: ["class", "style"],
      cite: ["class", "style"],
      code: ["class", "style"],
      col: ["class", "style"],
      colgroup: ["class", "style"],
      dd: ["class", "style"],
      del: ["class", "style"],
      details: ["class", "style"],
      div: ["class", "style", "spellcheck", "data-gramm", "spellcheck", "data-mode", "data-position", "data-row", "data-cell", "data-rowspan", "data-colspan", "data-cell-bg", "data-parent-bg"],
      dl: ["class", "style"],
      dt: ["class", "style"],
      em: ["class", "style"],
      figcaption: ["class", "style"],
      figure: ["class", "style"],
      font: ["class", "style"],
      footer: ["class", "style"],
      h1: ["class", "style"],
      h2: ["class", "style"],
      h3: ["class", "style"],
      h4: ["class", "style"],
      h5: ["class", "style"],
      h6: ["class", "style"],
      header: ["class", "style"],
      hr: ["class", "style"],
      i: ["class", "style", "data-image-id", "data-image"],
      img: ["class", "style", "devui-editorx-image", "style", "data-image-id"],
      input: ["class", "style", "data-formula", "data-link", "data-video"],
      ins: ["class", "style"],
      li: ["class", "style"],
      mark: ["class", "style"],
      nav: ["class", "style"],
      ol: ["class", "style"],
      p: ["class", "style"],
      pre: ["class", "style"],
      s: ["class", "style"],
      section: ["class", "style"],
      small: ["class", "style"],
      span: ["class", "style", "contenteditable", "color", "style"],
      sub: ["class", "style"],
      summary: ["class", "style"],
      sup: ["class", "style"],
      strong: ["class", "style"],
      strike: ["class", "style"],
      svg: ["class", "style", "t", "viewBox", "version", "xmlns", "p-id", "xmlns:xlink"],
      path: ["d", "p-id"],
      table: ["class", "style"],
      tbody: ["class", "style"],
      td: ["class", "style", "data-row", "data-cell", "data-cell-bg", "data-parent-bg"],
      tfoot: ["class", "style"],
      th: ["class", "style"],
      thead: ["class", "style"],
      tr: ["class", "style", "data-row"],
      tt: ["class", "style"],
      u: ["class", "style"],
      ul: ["class", "style"],
      video: ["class", "style"]
    }
  }
};
var defaultWhiteList = libExports$1.getDefaultWhiteList && libExports$1.getDefaultWhiteList() || {};
xssOptions.html.whiteList = Object.assign(defaultWhiteList, xssOptions.html.whiteList);
new libExports$1.FilterXSS(xssOptions.html);
var _win = getWindow();
var _cnsl = "console";
var _console = _win[_cnsl] || {};
var _print = {};
var _log = function _log2(csl, type) {
  return function() {
    var args = [];
    for (var i = 0; i < arguments.length; i++) {
      args[i] = arguments[i];
    }
    if (csl[type] && typeof csl[type] === "function") {
      csl[type].apply(csl, args);
    }
  };
};
var _initPrint = function _initPrint2(csl) {
  Object.keys(csl).forEach(function(type) {
    _print[type] = _log(csl, type);
  });
  return _print;
};
_initPrint(_console);
var nanoid = _nanoid;
var fillChar = function fillChar2(string, length, append) {
  var chr = arguments.length > 3 && arguments[3] !== void 0 ? arguments[3] : "0";
  if (typeof string === "string" && typeof chr === "string" && isNumber(length)) {
    var len = string.length - length;
    if (len > 0) {
      return append ? string.substr(0, length) : string.substr(len, length);
    } else {
      var appendStr = [];
      len = Math.abs(len) / chr.length;
      for (; len > 0; len--) {
        appendStr.push(chr);
      }
      var s2 = appendStr.join("");
      return append ? string + s2 : s2 + string;
    }
  }
};
nanoid.random;
var getIEVersion = function getIEVersion2() {
  var version2 = 8;
  if (!!document.addEventListener && !!window.performance) {
    version2 = 9;
    if (!!window.atob && !!window.matchMedia) {
      version2 = 10;
      if (!window.attachEvent && !document.all) {
        version2 = 11;
      }
    }
  }
  return version2;
};
var isEdge = function isEdge2(browser) {
  if (browser.chrome && ~navigator.userAgent.indexOf("Edg")) {
    browser.name = "edge";
    browser.edge = true;
    delete browser.chrome;
  } else if (!document.documentMode && !!window.StyleMedia) {
    browser.name = "edge";
    browser.edge = true;
  }
};
var isBrowser = typeof window !== "undefined" && typeof document !== "undefined" && window.document === document;
(function() {
  var browser = {
    name: void 0,
    version: void 0,
    isDoc: typeof document !== "undefined",
    isMobile: false,
    isPC: true,
    isNode: typeof window === "undefined"
  };
  if (isBrowser) {
    var isMobile = /(Android|webOS|iPhone|iPad|iPod|SymbianOS|BlackBerry|Windows Phone)/.test(navigator.userAgent);
    browser.isMobile = isMobile;
    browser.isPC = !isMobile;
    var matches;
    if (!!window.chrome && (!!window.chrome.webstore || /^Google\b/.test(window.navigator.vendor))) {
      browser.name = "chrome";
      browser.chrome = true;
      matches = navigator.userAgent.match(/chrome\/(\d+)/i);
      browser.version = !!matches && !!matches[1] && parseInt(matches[1], 10);
      matches = void 0;
    } else if (!!document.all || !!document.documentMode) {
      browser.name = "ie";
      browser.version = getIEVersion();
      browser.ie = true;
    } else if (typeof window.InstallTrigger !== "undefined") {
      browser.name = "firefox";
      browser.firefox = true;
    } else if (Object.prototype.toString.call(window.HTMLElement).indexOf("Constructor") > 0) {
      browser.name = "safari";
      browser.safari = true;
    } else if (!!window.opr && !!window.opr.addons || !!window.opera) {
      browser.name = "opera";
      browser.opera = true;
    }
    isEdge(browser);
    if (!~["ie", "chrome"].indexOf(browser.name)) {
      var reg = browser.name + "/(\\d+)";
      matches = navigator.userAgent.match(new RegExp(reg, "i"));
      browser.version = !!matches && !!matches[1] && parseInt(matches[1], 10);
      matches = void 0;
    }
    if (browser.isDoc) {
      var bodyEl = document.body || document.documentElement;
      ["webkit", "khtml", "moz", "ms", "o"].forEach(function(core) {
        browser["-" + core] = !!bodyEl[core + "MatchesSelector"];
      });
    }
  }
  return browser;
})();
var BigInt = isBrowser ? window.BigInt : global.BigInt;
function supportBigInt() {
  return typeof BigInt === "function";
}
function trimNumber(numStr) {
  var string = numStr.toString().trim();
  var negative = string.startsWith("-");
  if (negative) {
    string = string.slice(1);
  }
  string = string.replace(/(\.\d*[^0])0*$/, "$1").replace(/\.0*$/, "").replace(/^0+/, "");
  if (string.startsWith(".")) {
    string = "0".concat(string);
  }
  var trimStr = string || "0";
  var splitNumber = trimStr.split(".");
  var integerStr = splitNumber[0] || "0";
  var decimalStr = splitNumber[1] || "0";
  if (integerStr === "0" && decimalStr === "0") {
    negative = false;
  }
  var negativeStr = negative ? "-" : "";
  return {
    negative,
    negativeStr,
    trimStr,
    integerStr,
    decimalStr,
    fullStr: "".concat(negativeStr).concat(trimStr)
  };
}
function isE(number) {
  var str = String(number);
  return !isNaN(Number(str)) && ~str.indexOf("e");
}
function validateNumber(num) {
  if (typeof num === "number") {
    return !isNaN(num);
  }
  if (!num) {
    return false;
  }
  return (
    // Normal type: 11.28
    /^\s*-?\d+(\.\d+)?\s*$/.test(num) || // Pre-number: 1.
    /^\s*-?\d+\.\s*$/.test(num) || // Post-number: .1
    /^\s*-?\.\d+\s*$/.test(num)
  );
}
function getNumberPrecision(number) {
  var numStr = String(number);
  if (isE(number)) {
    var precision = Number(numStr.slice(numStr.indexOf("e-") + 2));
    var decimalMatch = numStr.match(/\.(\d+)/);
    if (decimalMatch === null || decimalMatch === void 0 ? void 0 : decimalMatch[1]) {
      precision += decimalMatch[1].length;
    }
    return precision;
  }
  return ~numStr.indexOf(".") && validateNumber(numStr) ? numStr.length - numStr.indexOf(".") - 1 : 0;
}
function num2str(number) {
  var numStr = String(number);
  if (isE(number)) {
    if (number > Number.MAX_SAFE_INTEGER) {
      return String(supportBigInt() ? BigInt(number).toString() : Number.MAX_SAFE_INTEGER);
    }
    if (number < Number.MIN_SAFE_INTEGER) {
      return String(supportBigInt() ? BigInt(number).toString() : Number.MIN_SAFE_INTEGER);
    }
    numStr = number.toFixed(getNumberPrecision(numStr));
  }
  return trimNumber(numStr).fullStr;
}
function pluginDecimal(decimal) {
  if (!decimal.add) {
    Object.assign(decimal, {
      add: decimal.plus,
      lessEquals: decimal.isLessThan,
      equals: decimal.isEqualTo
    });
  }
  return decimal;
}
var DecimalCls = {
  CLS: null
};
var setDecimalClass;
function getMiniDecimal(value, decimal) {
  if (!DecimalCls.CLS) {
    setDecimalClass(decimal);
  }
  return pluginDecimal(new DecimalCls.CLS(value));
}
var BigIntDecimal = /* @__PURE__ */ function() {
  function BigIntDecimal2(value) {
    _classCallCheck(this, BigIntDecimal2);
    if (!value && value !== 0 || !String(value).trim()) {
      this.empty = true;
      return;
    }
    this.origin = String(value);
    this.negative = void 0;
    this.integer = void 0;
    this.decimal = void 0;
    this.decimalLen = void 0;
    this.empty = void 0;
    this.nan = void 0;
    if (value === "-") {
      this.nan = true;
      return;
    }
    var mergedValue = isE(value) ? Number(value) : value;
    if (typeof mergedValue !== "string") {
      num2str(mergedValue);
    }
    var f = Function;
    var convertBigInt = function convertBigInt2(str) {
      var validStr = str.replace(/^0+/, "") || "0";
      return f("return BigInt(".concat(validStr, ")"))();
    };
    if (validateNumber(mergedValue)) {
      var trimRet = trimNumber(mergedValue);
      this.negative = trimRet.negative;
      var numbers = trimRet.trimStr.split(".");
      this.integer = BigInt(numbers[0]);
      var decimalStr = numbers[1] || "0";
      this.decimal = convertBigInt(decimalStr);
      this.decimalLen = decimalStr.length;
    } else {
      this.nan = true;
    }
  }
  _createClass(BigIntDecimal2, [{
    key: "getDecimalStr",
    value: function getDecimalStr() {
      return this.decimal.toString().padStart(this.decimalLen, "0");
    }
  }, {
    key: "getIntegerStr",
    value: function getIntegerStr() {
      return this.integer.toString();
    }
  }, {
    key: "getMark",
    value: function getMark() {
      return this.negative ? "-" : "";
    }
    /**
     * Align BigIntDecimal with same decimal length. e.g. 12.3 + 5 = 1230000
     * This is used for add function only.
     */
  }, {
    key: "alignDecimal",
    value: function alignDecimal(decimalLength) {
      var string = "".concat(this.getMark()).concat(this.getIntegerStr()).concat(this.getDecimalStr().padEnd(decimalLength, "0"));
      return BigInt(string);
    }
  }, {
    key: "add",
    value: function add(value) {
      if (this.isInvalidate()) {
        return new BigIntDecimal2(value);
      }
      var offsetObj = new BigIntDecimal2(value);
      if (offsetObj.isInvalidate()) {
        return this;
      }
      var maxDecimalLength = Math.max(this.getDecimalStr().length, offsetObj.getDecimalStr().length);
      var offsetAlignedDecimal = offsetObj.alignDecimal(maxDecimalLength);
      var myAlignedDecimal = this.alignDecimal(maxDecimalLength);
      var valueStr = "".concat(myAlignedDecimal + offsetAlignedDecimal);
      var _trimNumber = trimNumber(valueStr), str = _trimNumber.negativeStr, trimStr = _trimNumber.trimStr;
      var hydrateValueStr = "".concat(str).concat(trimStr.padStart(maxDecimalLength + 1, "0"));
      return getMiniDecimal("".concat(hydrateValueStr.slice(0, -maxDecimalLength), ".").concat(hydrateValueStr.slice(-maxDecimalLength)));
    }
  }, {
    key: "negate",
    value: function negate() {
      var clone = new BigIntDecimal2(this.toString());
      clone.negative = !clone.negative;
      return clone;
    }
  }, {
    key: "isNaN",
    value: function isNaN2() {
      return this.nan;
    }
  }, {
    key: "isEmpty",
    value: function isEmpty() {
      return this.empty;
    }
  }, {
    key: "isInvalidate",
    value: function isInvalidate() {
      return this.isEmpty() || this.isNaN();
    }
  }, {
    key: "lessEquals",
    value: function lessEquals(target) {
      return this.add(target.negate().toString()).toNumber() <= 0;
    }
  }, {
    key: "equals",
    value: function equals(target) {
      return this.toString() === (target && target.toString());
    }
  }, {
    key: "toNumber",
    value: function toNumber() {
      if (this.isNaN()) {
        return NaN;
      }
      return Number(this.toString());
    }
  }, {
    key: "toString",
    value: function toString2() {
      var safe = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : true;
      if (!safe) {
        return this.origin;
      }
      if (this.isInvalidate()) {
        return "";
      }
      return trimNumber("".concat(this.getMark()).concat(this.getIntegerStr(), ".").concat(this.getDecimalStr())).fullStr;
    }
  }]);
  return BigIntDecimal2;
}();
var NumberDecimal = /* @__PURE__ */ function() {
  function NumberDecimal2() {
    var value = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : "";
    _classCallCheck(this, NumberDecimal2);
    if (!value && value !== 0 || !String(value).trim()) {
      this.empty = true;
      return;
    }
    this.origin = "";
    this.number = void 0;
    this.empty = void 0;
    this.origin = String(value);
    this.number = Number(value);
  }
  _createClass(NumberDecimal2, [{
    key: "negate",
    value: function negate() {
      return new NumberDecimal2(-this.toNumber());
    }
  }, {
    key: "add",
    value: function add(value) {
      if (this.isInvalidate()) {
        return new NumberDecimal2(value);
      }
      var target = Number(value);
      if (isNaN(target)) {
        return this;
      }
      var number = this.number + target;
      if (number < Number.MIN_SAFE_INTEGER) {
        return new NumberDecimal2(Number.MIN_SAFE_INTEGER);
      }
      if (number > Number.MAX_SAFE_INTEGER) {
        return new NumberDecimal2(Number.MAX_SAFE_INTEGER);
      }
      var maxPrecision = Math.max(getNumberPrecision(target), getNumberPrecision(this.number));
      return new NumberDecimal2(number.toFixed(maxPrecision));
    }
  }, {
    key: "isNaN",
    value: function(_isNaN) {
      function isNaN2() {
        return _isNaN.apply(this, arguments);
      }
      isNaN2.toString = function() {
        return _isNaN.toString();
      };
      return isNaN2;
    }(function() {
      return isNaN(this.number);
    })
  }, {
    key: "isEmpty",
    value: function isEmpty() {
      return this.empty;
    }
  }, {
    key: "isInvalidate",
    value: function isInvalidate() {
      return this.isEmpty() || this.isNaN();
    }
  }, {
    key: "equals",
    value: function equals(target) {
      return this.toNumber() === (target && target.toNumber());
    }
  }, {
    key: "lessEquals",
    value: function lessEquals(target) {
      return this.add(target.negate().toString()).toNumber() <= 0;
    }
  }, {
    key: "toNumber",
    value: function toNumber() {
      return this.number;
    }
  }, {
    key: "toString",
    value: function toString2() {
      var safe = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : true;
      if (!safe) {
        return this.origin;
      }
      if (this.isInvalidate()) {
        return "";
      }
      return num2str(this.number);
    }
  }]);
  return NumberDecimal2;
}();
setDecimalClass = function setDecimalClass2(decimaljs) {
  DecimalCls.CLS = supportBigInt() ? BigIntDecimal : typeof decimaljs === "function" ? decimaljs : NumberDecimal;
};
function toFixed(numStr, precision) {
  var rounding = arguments.length > 2 && arguments[2] !== void 0 ? arguments[2] : 5;
  if (numStr === "") {
    return "";
  }
  var separatorStr = ".";
  var _trimNumber2 = trimNumber(numStr), negativeStr = _trimNumber2.negativeStr, integerStr = _trimNumber2.integerStr, decimalStr = _trimNumber2.decimalStr;
  var precisionDecimalStr = "".concat(separatorStr).concat(decimalStr);
  var numberWithoutDecimal = "".concat(negativeStr).concat(integerStr);
  if (precision >= 0) {
    var advancedNum = Number(decimalStr[precision]);
    if (advancedNum >= rounding && rounding !== 0) {
      var advancedDecimal = getMiniDecimal("".concat(integerStr).concat(separatorStr).concat(decimalStr)).add("0.".concat(fillChar("", precision, true)).concat(10 - advancedNum));
      return toFixed(negativeStr + advancedDecimal.toString(), precision, 0);
    }
    if (precision === 0) {
      return numberWithoutDecimal;
    }
    return "".concat(numberWithoutDecimal).concat(separatorStr).concat(fillChar(decimalStr, precision, true).slice(0, precision));
  }
  if (precisionDecimalStr === ".0") {
    return numberWithoutDecimal;
  }
  return "".concat(numberWithoutDecimal).concat(precisionDecimalStr);
}
var formatInteger = function formatInteger2(value, _ref) {
  var _ref$secondaryGroupSi = _ref.secondaryGroupSize, secondaryGroupSize = _ref$secondaryGroupSi === void 0 ? 3 : _ref$secondaryGroupSi, _ref$groupSize = _ref.groupSize, groupSize = _ref$groupSize === void 0 ? 0 : _ref$groupSize, _ref$groupSeparator = _ref.groupSeparator, groupSeparator = _ref$groupSeparator === void 0 ? "," : _ref$groupSeparator;
  var negative = /^-\d+/.test(value);
  var result = negative ? value.slice(1) : value;
  var secSize = secondaryGroupSize || groupSize;
  if (groupSize && result.length > groupSize) {
    var left = result.slice(0, 0 - groupSize);
    var right = result.slice(0 - groupSize);
    left = left.replace(new RegExp("\\B(?=(\\d{".concat(secSize, "})+(?!\\d))"), "g"), groupSeparator);
    result = "".concat(left).concat(groupSeparator).concat(right);
  }
  return "".concat(negative ? "-" : "").concat(result);
};
var reverseString = function reverseString2(str) {
  var arr = [];
  for (var i = 0; i < str.length; i++) {
    arr.push(str[i]);
  }
  return arr.reverse().join("");
};
var formatDecimal = function formatDecimal2(num, _ref2) {
  var _ref2$fractionGroupSi = _ref2.fractionGroupSize, fractionGroupSize = _ref2$fractionGroupSi === void 0 ? 0 : _ref2$fractionGroupSi, _ref2$fractionGroupSe = _ref2.fractionGroupSeparator, fractionGroupSeparator = _ref2$fractionGroupSe === void 0 ? " " : _ref2$fractionGroupSe;
  var RE = new RegExp("\\B(?=(\\d{".concat(fractionGroupSize, "})+(?!\\d))"), "g");
  return reverseString(reverseString(num).replace(RE, fractionGroupSeparator));
};
var formatNumber = function formatNumber2(value) {
  var format2 = arguments.length > 1 && arguments[1] !== void 0 ? arguments[1] : {};
  var fraction = format2.fraction, rounding = format2.rounding, _format2$prefix = format2.prefix, prefix = _format2$prefix === void 0 ? "" : _format2$prefix, _format2$decimalSepar = format2.decimalSeparator, decimalSeparator = _format2$decimalSepar === void 0 ? "." : _format2$decimalSepar, _format2$suffix = format2.suffix, suffix = _format2$suffix === void 0 ? "" : _format2$suffix;
  var reslut = getMiniDecimal(value);
  if (reslut.isNaN() || !reslut.toString()) {
    return value;
  }
  reslut = toFixed(reslut.toString(), fraction, rounding);
  format2.zeroize === false && reslut.match(/\./) && (reslut = reslut.replace(/\.?0+$/g, ""));
  var number = reslut.toString().split(".").slice(0, 2).map(function(str, index2) {
    return index2 ? formatDecimal(str, format2) : formatInteger(str, format2);
  }).join(decimalSeparator);
  return "".concat(prefix).concat(number).concat(suffix);
};
var recoverNumber = function recoverNumber2(number) {
  var format2 = arguments.length > 1 && arguments[1] !== void 0 ? arguments[1] : {};
  var _format2$prefix2 = format2.prefix, prefix = _format2$prefix2 === void 0 ? "" : _format2$prefix2, _format2$suffix2 = format2.suffix, suffix = _format2$suffix2 === void 0 ? "" : _format2$suffix2, _format2$decimalSepar2 = format2.decimalSeparator, decimalSeparator = _format2$decimalSepar2 === void 0 ? "." : _format2$decimalSepar2;
  var result = number;
  if (typeof number === "string") {
    result = number.replace(new RegExp("^".concat(prefix, "(.+)").concat(suffix, "$")), function($1, $2) {
      return $2;
    }).split(decimalSeparator).map(function(s2) {
      return s2.replace(/[^\d]/g, "");
    }).join(".");
  }
  return Number(result);
};
var daysInMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
var yyyymmddReg = new RegExp("^(\\d{4})(/|-)(((0)?[1-9])|(1[0-2]))((/|-)(((0)?[1-9])|([1-2][0-9])|(3[0-1])))?( ((0)?[0-9]|1[0-9]|20|21|22|23):([0-5]?[0-9])((:([0-5]?[0-9]))?(.([0-9]{1,6}))?)?)?$");
var mmddyyyyReg = new RegExp("^(((0)?[1-9])|(1[0-2]))(/|-)(((0)?[1-9])|([1-2][0-9])|(3[0-1]))?(/|-)?(\\d{4})( ((0)?[0-9]|1[0-9]|20|21|22|23):([0-5]?[0-9])((:([0-5]?[0-9]))?(.([0-9]{1,6}))?)?)?$");
var iso8601Reg = new RegExp("^(\\d{4})-(((0)?[1-9])|(1[0-2]))-(((0)?[1-9])|([1-2][0-9])|(3[0-1]))T(((0)?[0-9]|1[0-9]|20|21|22|23):([0-5]?[0-9])((:([0-5]?[0-9]))?(.([0-9]{1,6}))?)?)?(Z|([+-])((0)?[0-9]|1[0-9]|20|21|22|23):?([0-5]?[0-9]))$");
var maxDateValues = {
  YEAR: 9999,
  MONTH: 11,
  DATE: 31,
  HOUR: 23,
  MINUTE: 59,
  SECOND: 59,
  MILLISECOND: 999
};
var timezone1 = "-12:00,-11:00,-10:00,-09:30,-08:00,-07:00,-06:00,-05:00,-04:30,-04:00,-03:30,-02:00,-01:00";
var timezone2 = "-00:00,+00:00,+01:00,+02:00,+03:00,+03:30,+04:00,+04:30,+05:00,+05:30,+05:45,+06:00";
var timezone3 = "+06:30,+07:00,+08:00,+09:00,+10:00,+10:30,+11:00,+11:30,+12:00,+12:45,+13:00,+14:00";
var timezones = [].concat(timezone1.split(","), timezone2.split(","), timezone3.split(","));
var isLeapYear = function isLeapYear2(year) {
  return year % 400 === 0 || year % 4 === 0 && year % 100 !== 0;
};
var getDateFromData = function getDateFromData2(_ref) {
  var year = _ref.year, month = _ref.month, date = _ref.date, hours = _ref.hours, minutes = _ref.minutes, seconds = _ref.seconds, milliseconds = _ref.milliseconds;
  var daysInMonth = daysInMonths[month];
  if (isLeapYear(year) && month === 1) {
    daysInMonth += 1;
  }
  if (date <= daysInMonth) {
    return new Date(year, month, date, hours, minutes, seconds, milliseconds);
  }
};
var yyyymmddDateParser = function yyyymmddDateParser2(m2) {
  if (m2.length === 23) {
    var year = Number(m2[1]);
    var month = m2[3] - 1;
    var date = Number(m2[9] || 1);
    var hours = m2[15] || 0;
    var minutes = m2[17] || 0;
    var seconds = m2[20] || 0;
    var milliseconds = m2[22] || 0;
    return getDateFromData({
      date,
      year,
      hours,
      month,
      seconds,
      minutes,
      milliseconds
    });
  }
};
var mmddyyyyDateParser = function mmddyyyyDateParser2(m2) {
  if (m2.length === 22) {
    var year = Number(m2[12]);
    var month = m2[1] - 1;
    var date = Number(m2[6] || 1);
    var hours = m2[14] || 0;
    var minutes = m2[16] || 0;
    var seconds = m2[19] || 0;
    var milliseconds = m2[21] || 0;
    return getDateFromData({
      year,
      month,
      date,
      hours,
      minutes,
      seconds,
      milliseconds
    });
  }
};
var iso8601DateParser = function iso8601DateParser2(m2) {
  if (m2.length !== 25) {
    return;
  }
  var year = Number(m2[1]);
  var month = m2[2] - 1;
  var date = Number(m2[6]);
  var offset = new Date(year, month, date).getTimezoneOffset();
  var hours = m2[12] || 0;
  var minutes = m2[14] || 0;
  var seconds = m2[17] || 0;
  var milliseconds = m2[19] || 0;
  var timeZone = m2[20];
  var sign = m2[21];
  var offsetHours = m2[22] || 0;
  var offsetMinutes = m2[24] || 0;
  var daysInMonth = daysInMonths[month];
  var actHours;
  var actMinutes;
  if (isLeapYear(year) && month === 1) {
    daysInMonth += 1;
  }
  if (date <= daysInMonth) {
    if (timeZone === "Z") {
      actHours = hours - offset / 60;
      actMinutes = minutes;
    } else {
      if (!timeZone.includes(":")) {
        timeZone = timeZone.substr(0, 3) + ":" + timeZone.substr(3);
      }
      if (!timezones.includes(timeZone)) {
        return;
      }
      actHours = sign === "+" ? hours - offsetHours - offset / 60 : Number(hours) + Number(offsetHours) - offset / 60;
      actMinutes = sign === "+" ? minutes - offsetMinutes : Number(minutes) + Number(offsetMinutes);
    }
    return new Date(year, month, date, actHours, actMinutes, seconds, milliseconds);
  }
};
var dateParsers = [[yyyymmddReg, yyyymmddDateParser], [mmddyyyyReg, mmddyyyyDateParser], [iso8601Reg, iso8601DateParser]];
var parseDate = function parseDate2(str) {
  for (var i = 0, len = dateParsers.length; i < len; i++) {
    var m2 = dateParsers[i][0].exec(str);
    if (m2 && m2.length > 0) {
      return dateParsers[i][1](m2);
    }
  }
};
var matchDateArray = function matchDateArray2(arr, value, text) {
  if (text) {
    switch (text) {
      case "yyyy":
      case "yy":
        arr[0] = value;
        break;
      case "M":
      case "MM":
        arr[1] = value - 1;
        break;
      case "d":
      case "dd":
        arr[2] = value;
        break;
      case "h":
      case "hh":
        arr[3] = value;
        break;
      case "m":
      case "mm":
        arr[4] = value;
        break;
      case "s":
      case "ss":
        arr[5] = value;
        break;
      case "S":
      case "SS":
      case "SSS":
        arr[6] = value;
        break;
    }
  }
};
var getDateArray = function getDateArray2(str, dateFormat) {
  var arr = [0, -1, 0, 0, 0, 0];
  if (str.length !== dateFormat.length) {
    return arr;
  }
  var valuePos = 0;
  var textPos = 0;
  for (var i = 0, len = str.length; i < len; i++) {
    var charValue = str.substr(i, 1);
    var notNum = isNaN(Number(charValue)) || charValue.trim() === "";
    if (notNum && charValue === dateFormat.substr(i, 1) || i === len - 1) {
      var value = void 0;
      var text = void 0;
      if (notNum) {
        value = str.substring(valuePos, i);
        valuePos = i + 1;
        var end = dateFormat.indexOf(charValue, textPos);
        text = dateFormat.substring(textPos, end === -1 ? dateFormat.length : end);
        textPos = end + 1;
      } else {
        value = str.substring(valuePos, len);
        text = dateFormat.substring(textPos, len);
      }
      if (value.length === text.length || value) {
        matchDateArray(arr, value, text);
      }
    }
  }
  return arr;
};
var invalideTime = function invalideTime2(time, min, max) {
  return isNaN(time) || time < min || time > max;
};
var invalideValue = function invalideValue2(_ref2) {
  var year = _ref2.year, month = _ref2.month, date = _ref2.date, hours = _ref2.hours, minutes = _ref2.minutes, seconds = _ref2.seconds, milliseconds = _ref2.milliseconds;
  return invalideTime(year, 0, maxDateValues.YEAR) || invalideTime(month, 0, maxDateValues.MONTH) || invalideTime(date, 0, maxDateValues.DATE) || invalideTime(hours, 0, maxDateValues.HOUR) || invalideTime(minutes, 0, maxDateValues.MINUTE) || invalideTime(seconds, 0, maxDateValues.SECOND) || invalideTime(milliseconds, 0, maxDateValues.MILLISECOND);
};
var innerParse = function innerParse2(value, dateFormat) {
  if (typeof dateFormat === "string") {
    var arr = getDateArray(value, dateFormat);
    var year = Number(arr[0]);
    var month = Number(arr[1]);
    var date = Number(arr[2] || 1);
    var hours = Number(arr[3] || 0);
    var minutes = Number(arr[4] || 0);
    var seconds = Number(arr[5] || 0);
    var milliseconds = Number(arr[6] || 0);
    if (invalideValue({
      year,
      month,
      date,
      hours,
      minutes,
      seconds,
      milliseconds
    })) {
      return;
    }
    return getDateFromData({
      year,
      date,
      month,
      minutes,
      hours,
      milliseconds,
      seconds
    });
  } else {
    return parseDate(value);
  }
};
var toDate$1 = function toDate(value, dateFormat, minDate) {
  var date;
  if (isNumber(value)) {
    date = new Date(value);
  } else if (typeof value === "string") {
    date = innerParse(value, dateFormat);
  }
  if (minDate) {
    var min = minDate && toDate(minDate) || new Date(1, 1, 1, 0, 0, 0);
    return date && date < min ? min : date;
  }
  return date;
};
var getDateWithNewTimezone = function getDateWithNewTimezone2(date, otz, ntz) {
  if (!isDate$1(date) || !isNumeric(otz) || !isNumeric(ntz)) {
    return;
  }
  var otzOffset = -otz * 60;
  var ntzOffset = -ntz * 60;
  var utc = date.getTime() + otzOffset * 6e4;
  return new Date(utc - ntzOffset * 6e4);
};
var TriggerTypes = "date,datetime,time,time-select,week,month,year,years,yearrange,daterange,monthrange,timerange,datetimerange,dates";
var DATEPICKER = {
  Day: "day",
  Date: "date",
  Dates: "dates",
  Year: "year",
  Years: "years",
  YearRange: "yearrange",
  PanelYearNum: 12,
  Month: "month",
  Week: "week",
  Normal: "normal",
  Today: "today",
  PreMonth: "pre-month",
  NextMonth: "next-month",
  YearI18n: "ui.datepicker.year",
  List: [38, 40, 37, 39],
  YearObj: {
    38: -4,
    40: 4,
    37: -1,
    39: 1
  },
  WeekObj: {
    38: -1,
    40: 1,
    37: -1,
    39: 1
  },
  DayObj: {
    38: -7,
    40: 7,
    37: -1,
    39: 1
  },
  Aviailable: "available",
  Default: "default",
  Current: "current",
  InRange: "in-range",
  StartDate: "start-date",
  EndDate: "end-date",
  Selected: "selected",
  Disabled: "disabled",
  Range: "range",
  fullMonths: "January,February,March,April,May,June,July,August,September,October,November,December".split(","),
  fullWeeks: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
  MonhtList: ["jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"],
  Weeks: ["sun", "mon", "tue", "wed", "thu", "fri", "sat"],
  PlacementMap: {
    left: "bottom-start",
    center: "bottom",
    right: "bottom-end"
  },
  TriggerTypes: TriggerTypes.split(","),
  DateFormats: {
    year: "yyyy",
    years: "yyyy",
    yearrange: "yyyy",
    month: "yyyy-MM",
    time: "HH:mm:ss",
    week: "yyyywWW",
    date: "yyyy-MM-dd",
    timerange: "HH:mm:ss",
    monthrange: "yyyy-MM",
    daterange: "yyyy-MM-dd",
    datetime: "yyyy-MM-dd HH:mm:ss",
    datetimerange: "yyyy-MM-dd HH:mm:ss"
  },
  Time: "time",
  TimeRange: "timerange",
  IconTime: "icon-time",
  IconDate: "icon-Calendar",
  DateRange: "daterange",
  DateTimeRange: "datetimerange",
  MonthRange: "monthrange",
  TimeSelect: "time-select",
  TimesTamp: "timestamp",
  DateTime: "datetime",
  SelectbaleRange: "selectableRange",
  Start: "09:00",
  End: "18:00",
  Step: "00:30",
  CompareOne: "-1:-1",
  CompareHundred: "100:100",
  selClass: ".selected",
  queryClass: ".tiny-picker-panel__content",
  disableClass: ".time-select-item:not(.disabled)",
  defaultClass: ".default",
  Qurtyli: "li",
  MappingKeyCode: {
    40: 1,
    38: -1
  },
  DatePicker: "DatePicker",
  TimePicker: "TimePicker"
};
var fecha = {};
var digitsReg = ["\\d\\d?", "\\d{3}", "\\d{4}"];
var twoDigits = digitsReg[0];
var threeDigits = digitsReg[1];
var fourDigits = digitsReg[2];
var word = "[^\\s]+";
var literal = /\[([^]*?)\]/gm;
var noop = function noop2() {
  return void 0;
};
var formats = {
  shortDate: "M/D/yy",
  mediumDate: "MMM d, yyyy",
  longDate: "MMMM d, yyyy",
  fullDate: "dddd, MMMM d, yyyy",
  default: "ddd MMM dd yyyy HH:mm:ss",
  shortTime: "HH:mm",
  mediumTime: "HH:mm:ss",
  longTime: "HH:mm:ss.SSS"
};
var shorten = function shorten2(arr, sLen) {
  var newArr = [];
  for (var i = 0, len = arr.length; i < len; i++) {
    newArr.push(arr[i].substr(0, sLen));
  }
  return newArr;
};
var monthUpdate = function monthUpdate2(arrName) {
  return function(date, value, i18n3) {
    var index2 = i18n3[arrName].indexOf(value.charAt(0).toUpperCase() + value.substr(1).toLowerCase());
    if (~index2) {
      date.month = index2;
    }
  };
};
var pad = function pad2(val, len) {
  val = String(val);
  len = len || 2;
  while (val.length < len) {
    val = "0" + val;
  }
  return val;
};
var regexEscape = function regexEscape2(str) {
  return str.replace(/[|\\{()[^$+*?.-]/g, "\\$&");
};
var fullTimeReg = /d{1,4}|M{1,4}|yy(?:yy)?|S{1,3}|Do|ZZ|([HhMsDm])\1?|[aA]|"[^"]*"|'[^']*'/g;
var dayNames = DATEPICKER.fullWeeks;
var monthNames = DATEPICKER.fullMonths;
var monthNamesShort = shorten(monthNames, 3);
var dayNamesShort = shorten(dayNames, 3);
var parts = ["th", "st", "nd", "rd"];
fecha.i18n = {
  dayNames,
  monthNames,
  dayNamesShort,
  monthNamesShort,
  amPm: ["am", "pm"],
  doFn: function doFn(D2) {
    return D2 + parts[D2 % 10 > 3 ? 0 : (D2 - D2 % 10 !== 10) * D2 % 10];
  }
};
var formatFlags = {
  D: function D(dateObj) {
    return dateObj.getDay();
  },
  DD: function DD(dateObj) {
    return pad(dateObj.getDay());
  },
  Do: function Do(dateObj, i18n3) {
    return i18n3.doFn(dateObj.getDate());
  },
  d: function d(dateObj) {
    return dateObj.getDate();
  },
  dd: function dd(dateObj) {
    return pad(dateObj.getDate());
  },
  ddd: function ddd(dateObj, i18n3) {
    return i18n3.dayNamesShort[dateObj.getDay()];
  },
  dddd: function dddd(dateObj, i18n3) {
    return i18n3.dayNames[dateObj.getDay()];
  },
  M: function M(dateObj) {
    return dateObj.getMonth() + 1;
  },
  MM: function MM(dateObj) {
    return pad(dateObj.getMonth() + 1);
  },
  MMM: function MMM(dateObj, i18n3) {
    return i18n3.monthNamesShort[dateObj.getMonth()];
  },
  MMMM: function MMMM(dateObj, i18n3) {
    return i18n3.monthNames[dateObj.getMonth()];
  },
  yy: function yy(dateObj) {
    return pad(String(dateObj.getFullYear()), 4).substr(2);
  },
  yyyy: function yyyy(dateObj) {
    return pad(dateObj.getFullYear(), 4);
  },
  h: function h(dateObj) {
    return dateObj.getHours() % 12 || 12;
  },
  hh: function hh(dateObj) {
    return pad(dateObj.getHours() % 12 || 12);
  },
  H: function H(dateObj) {
    return dateObj.getHours();
  },
  HH: function HH(dateObj) {
    return pad(dateObj.getHours());
  },
  m: function m(dateObj) {
    return dateObj.getMinutes();
  },
  mm: function mm(dateObj) {
    return pad(dateObj.getMinutes());
  },
  s: function s(dateObj) {
    return dateObj.getSeconds();
  },
  ss: function ss(dateObj) {
    return pad(dateObj.getSeconds());
  },
  S: function S(dateObj) {
    return Math.round(dateObj.getMilliseconds() / 100);
  },
  SS: function SS(dateObj) {
    return pad(Math.round(dateObj.getMilliseconds() / 10), 2);
  },
  SSS: function SSS(dateObj) {
    return pad(dateObj.getMilliseconds(), 3);
  },
  a: function a(dateObj, i18n3) {
    return dateObj.getHours() < 12 ? i18n3.amPm[0] : i18n3.amPm[1];
  },
  A: function A(dateObj, i18n3) {
    return dateObj.getHours() < 12 ? i18n3.amPm[0].toUpperCase() : i18n3.amPm[1].toUpperCase();
  },
  ZZ: function ZZ(dateObj) {
    var offset = dateObj.getTimezoneOffset();
    return (offset > 0 ? "-" : "+") + pad(Math.floor(Math.abs(offset) / 60) * 100 + Math.abs(offset) % 60, 4);
  }
};
var parseFlags = {
  d: [twoDigits, function(date, value) {
    date.day = value;
  }],
  Do: [twoDigits + word, function(date, value) {
    date.day = parseInt(value, 10);
  }],
  M: [twoDigits, function(date, value) {
    date.month = value - 1;
  }],
  yy: [twoDigits, function(date, value) {
    var now = /* @__PURE__ */ new Date();
    var cent = Number(String(now.getFullYear()).substr(0, 2));
    date.year = String(value > 68 ? cent - 1 : cent) + value;
  }],
  h: [twoDigits, function(date, value) {
    date.hour = value;
  }],
  m: [twoDigits, function(date, value) {
    date.minute = value;
  }],
  s: [twoDigits, function(date, value) {
    date.second = value;
  }],
  yyyy: [fourDigits, function(date, value) {
    date.year = value;
  }],
  S: ["\\d", function(date, value) {
    date.millisecond = value * 100;
  }],
  SS: ["\\d{2}", function(date, value) {
    date.millisecond = value * 10;
  }],
  SSS: [threeDigits, function(date, value) {
    date.millisecond = value;
  }],
  D: [twoDigits, noop],
  ddd: [word, noop],
  MMM: [word, monthUpdate("monthNamesShort")],
  MMMM: [word, monthUpdate("monthNames")],
  a: [word, function(date, value, i18n3) {
    var val = value.toLowerCase();
    if (val === i18n3.amPm[0]) {
      date.isPm = false;
    } else if (val === i18n3.amPm[1]) {
      date.isPm = true;
    }
  }],
  ZZ: ["[^\\s]*?[\\+\\-]\\d\\d:?\\d\\d|[^\\s]*?Z", function(date, value) {
    var parts2 = String(value).match(/([+-]|\d\d)/gi);
    var minutes;
    if (parts2) {
      minutes = Number(parts2[1] * 60) + parseInt(parts2[2], 10);
      date.timezoneOffset = parts2[0] === "+" ? minutes : -minutes;
    }
  }]
};
var fmts = ["A", "DD", "dd", "mm", "hh", "MM", "ss", "hh", "H", "HH"];
fecha.masks = formats;
parseFlags.dddd = parseFlags.ddd;
fmts.forEach(function(name) {
  if (name === "MM") {
    parseFlags[name] = parseFlags[name.substr(0, 1)];
  } else {
    parseFlags[name] = parseFlags[name.substr(0, 1).toLowerCase()];
  }
});
fecha.format = function(dateObj, mask, i18nSettings) {
  var i18n3 = i18nSettings || fecha.i18n;
  if (typeof dateObj === "number") {
    dateObj = new Date(dateObj);
  }
  if (!isDate$1(dateObj) || isNaN(dateObj.getTime())) {
    throw new Error("Invalid Date in fecha.format");
  }
  mask = fecha.masks[mask] || mask || fecha.masks.default;
  var literals = [];
  mask = mask.replace(literal, function($0, $1) {
    literals.push($1);
    return "@@@";
  });
  mask = mask.replace(fullTimeReg, function($0) {
    return $0 in formatFlags ? formatFlags[$0](dateObj, i18n3) : $0.slice(1, $0.length - 1);
  });
  return mask.replace(/@@@/g, function() {
    return literals.shift();
  });
};
var getNewFormat = function getNewFormat2(format2, parseInfo) {
  var literals = [];
  var newFormat = regexEscape(format2).replace(fullTimeReg, function($0) {
    if (parseFlags[$0]) {
      var info = parseFlags[$0];
      parseInfo.push(info[1]);
      return "(" + info[0] + ")";
    }
    return $0;
  });
  newFormat = newFormat.replace(/@@@/g, function() {
    return literals.shift();
  });
  return newFormat;
};
var getDate = function getDate2(dateInfo) {
  var date;
  var today = /* @__PURE__ */ new Date();
  if (!isNull$2(dateInfo.timezoneOffset)) {
    dateInfo.minute = Number(dateInfo.minute || 0) - Number(dateInfo.timezoneOffset);
    var year = dateInfo.year, month = dateInfo.month, day = dateInfo.day, hour = dateInfo.hour, minute = dateInfo.minute, second = dateInfo.second, millisecond = dateInfo.millisecond;
    date = new Date(Date.UTC(year || today.getFullYear(), month || 0, day || 1, hour || 0, minute || 0, second || 0, millisecond || 0));
  } else {
    var _year = dateInfo.year, _month = dateInfo.month, _day = dateInfo.day, _hour = dateInfo.hour, _minute = dateInfo.minute, _second = dateInfo.second, _millisecond = dateInfo.millisecond;
    date = new Date(_year || today.getFullYear(), _month || 0, _day || 1, _hour || 0, _minute || 0, _second || 0, _millisecond || 0);
  }
  return date;
};
fecha.parse = function(dateStr, format2, i18nSettings) {
  var i18n3 = i18nSettings || fecha.i18n;
  if (typeof format2 !== "string") {
    throw new TypeError("Invalid format in fecha.parse");
  }
  format2 = fecha.masks[format2] || format2;
  if (dateStr.length > 1e3) {
    return null;
  }
  var dateInfo = {};
  var parseInfo = [];
  format2 = format2.replace(literal, function($0, $1) {
    return "@@@";
  });
  var newFormat = getNewFormat(format2, parseInfo);
  var matches = dateStr.match(new RegExp(newFormat, "i"));
  if (!matches) {
    return null;
  }
  for (var i = 1, len = matches.length; i < len; i++) {
    parseInfo[i - 1](dateInfo, matches[i], i18n3);
  }
  if (dateInfo.isPm === true && !isNull$2(dateInfo.hour) && Number(dateInfo.hour) !== 12) {
    dateInfo.hour = Number(dateInfo.hour) + 12;
  } else if (dateInfo.isPm === false && Number(dateInfo.hour) === 12) {
    dateInfo.hour = 0;
  }
  return getDate(dateInfo);
};
const fecha$1 = fecha;
var weeks = DATEPICKER.Weeks;
var months = DATEPICKER.MonhtList;
var defaultYMD = DATEPICKER.DateFormats.date;
var getI18nSettings = function getI18nSettings2(t3) {
  return {
    dayNamesShort: weeks.map(function(week) {
      return t3("ui.datepicker.weeks.".concat(week));
    }),
    dayNames: weeks.map(function(week) {
      return t3("ui.datepicker.weeks.".concat(week));
    }),
    monthNamesShort: months.map(function(month) {
      return t3("ui.datepicker.months.".concat(month));
    }),
    monthNames: months.map(function(month, index2) {
      return t3("ui.datepicker.month".concat(index2 + 1));
    }),
    amPm: ["am", "pm"]
  };
};
var isDate2 = function isDate3(date) {
  if (isNull$2(date)) {
    return false;
  }
  if (isNaN(new Date(date).getTime())) {
    return false;
  }
  if (Array.isArray(date)) {
    return false;
  }
  return true;
};
var toDate2 = function toDate3(date) {
  return isDate2(date) ? new Date(date) : null;
};
var formatDate = function formatDate2(date, format2, t3) {
  date = toDate2(date);
  if (!date) {
    return "";
  }
  return fecha$1.format(date, format2 || defaultYMD, getI18nSettings(t3));
};
var getNumberFormat = function getNumberFormat2(config) {
  var groupSize = 3;
  var groupSeparator = ",";
  var decimalSeparator = ".";
  if (isPlainObject(config)) {
    return config;
  }
  if (typeof config === "string") {
    var match = config.match(/\d{2}([^\d]?)\d{3}([^\d]?)\d{2}/);
    if (match && match.length === 3) {
      groupSeparator = match[1];
      decimalSeparator = match[2];
    }
  }
  return {
    groupSeparator,
    groupSize,
    decimalSeparator
  };
};
var getDateFormat = function getDateFormat2(config) {
  var _config$DateFormat = config.DateFormat, DateFormat = _config$DateFormat === void 0 ? "yyyy-MM-dd" : _config$DateFormat, _config$TimeFormat = config.TimeFormat, TimeFormat = _config$TimeFormat === void 0 ? "HH:mm:ss" : _config$TimeFormat;
  return {
    DateFormat,
    DateTimeFormat: "".concat(DateFormat, " ").concat(TimeFormat),
    TimeFormat
  };
};
var TZRE = /(-|\+)(\d{2}):?(\d{2})$/;
var getStrTimezone = function getStrTimezone2(value) {
  var localTimeZone = 0 - /* @__PURE__ */ (/* @__PURE__ */ new Date()).getTimezoneOffset() / 60;
  var match = typeof value === "string" && value.match(TZRE);
  if (match) {
    var minoffset = match[2] * 1 + match[3] * 1 / 60;
    value = minoffset * "".concat(match[1], "1");
  }
  if (isNumber(value) && value >= -12 && value <= 12) {
    return value;
  }
  return localTimeZone;
};
function glob(t3) {
  return function(config) {
    var opt = _objectSpread2(_objectSpread2({}, getDateFormat(config)), {}, {
      NumberFormat: getNumberFormat(config.NumberFormat),
      DbTimezone: getStrTimezone(config.DbTimezone),
      Timezone: getStrTimezone(config.Timezone)
    });
    var tools = {
      getFormatConfig: function getFormatConfig() {
        return opt;
      },
      setFormatConfig: function setFormatConfig(obj) {
        Object.assign(opt, obj);
      },
      getNumberFormat: function getNumberFormat3() {
        return opt.NumberFormat;
      },
      getDateFormat: function getDateFormat3() {
        return {
          DateTimeFormat: opt.DateTimeFormat,
          TimeFormat: opt.TimeFormat,
          Timezone: opt.Timezone,
          DateFormat: opt.DateFormat,
          DbTimezone: opt.DbTimezone
        };
      },
      /**
       *
       * @param {Date|String} value 日期或日期字符串
       * @param {String} format 格式化模式
       * @returns {String}
       */
      formatDate: function formatDate$1(value, format2) {
        if (isNull$2(value)) {
          return value;
        }
        var date = isDate$1(value) ? value : toDate$1(value);
        var dbtimezone = opt.DbTimezone;
        var includeTz = value.match && value.match(TZRE);
        var convers = format2 === false || arguments[2] === false;
        if (includeTz) {
          dbtimezone = getStrTimezone(value);
          date = toDate$1(value.replace("T", " ").slice(0, -5));
        }
        if (!convers) {
          date = this.getDateWithNewTimezone(date, dbtimezone, opt.Timezone);
        }
        return isDate$1(date) ? formatDate(date, format2 || opt.DateFormat, t3) : null;
      },
      /**
       *
       * @param {Number} value 数字
       * @param {Object} format 格式化选项
       * @returns {String}
       */
      formatNumber: function formatNumber$1(value, format2) {
        return formatNumber(value, _objectSpread2(_objectSpread2({}, opt.NumberFormat), format2));
      },
      /**
       *
       * @param {String} value 格式化后的字符串
       * @param {Object} format 格式化选项
       * @returns {Number}
       */
      recoverNumber: function recoverNumber$1(value, format2) {
        return recoverNumber(value, _objectSpread2(_objectSpread2({}, opt.NumberFormat), format2));
      },
      /**
       *
       * @param {Date} value Date
       * @param {Number} from
       * @param {Number} to
       * @returns {String}
       */
      getDateWithNewTimezone: function getDateWithNewTimezone$1(value, from, to) {
        from = from === 0 ? from : from || opt.DbTimezone;
        to = to === 0 ? to : to || opt.Timezone;
        return getDateWithNewTimezone(value, from, to);
      }
    };
    return tools;
  };
}
const version = "3.10.0";
var use2 = use$1, t2 = t$1, i18n2 = i18n$1, initI18n2 = initI18n$1, extend2 = extend$1, zhCN = zhCN$1, enUS = enUS$1, language2 = language$1;
var globalization = glob(t2);
const index = _objectSpread2(_objectSpread2({}, index$1), {}, {
  language: language2,
  globalization
});
export {
  index as default,
  enUS,
  extend2 as extend,
  globalization,
  i18n2 as i18n,
  initI18n2 as initI18n,
  language2 as language,
  t2 as t,
  use2 as use,
  version,
  zhCN
};
