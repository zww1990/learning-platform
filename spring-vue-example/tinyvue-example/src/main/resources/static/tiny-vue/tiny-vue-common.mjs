import * as hooks from "vue";
import { t } from "@opentiny/vue-locale";
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
function _toConsumableArray(arr) {
  return _arrayWithoutHoles(arr) || _iterableToArray(arr) || _unsupportedIterableToArray(arr) || _nonIterableSpread();
}
function _arrayWithoutHoles(arr) {
  if (Array.isArray(arr))
    return _arrayLikeToArray(arr);
}
function _iterableToArray(iter) {
  if (typeof Symbol !== "undefined" && iter[Symbol.iterator] != null || iter["@@iterator"] != null)
    return Array.from(iter);
}
function _unsupportedIterableToArray(o, minLen) {
  if (!o)
    return;
  if (typeof o === "string")
    return _arrayLikeToArray(o, minLen);
  var n = Object.prototype.toString.call(o).slice(8, -1);
  if (n === "Object" && o.constructor)
    n = o.constructor.name;
  if (n === "Map" || n === "Set")
    return Array.from(o);
  if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n))
    return _arrayLikeToArray(o, minLen);
}
function _arrayLikeToArray(arr, len) {
  if (len == null || len > arr.length)
    len = arr.length;
  for (var i = 0, arr2 = new Array(len); i < len; i++)
    arr2[i] = arr[i];
  return arr2;
}
function _nonIterableSpread() {
  throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
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
function isNull(obj) {
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
    if (!isNull(ret))
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
        if (!isNull(ret2))
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
          if (!isNull(ret2))
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
      if (!isNull(ret))
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
function cached(fn) {
  var cache = /* @__PURE__ */ Object.create(null);
  return function cachedFn(str) {
    var hit = cache[str];
    return hit || (cache[str] = fn(str));
  };
}
var camelizeRE = /-(\w)/g;
var camelize = cached(function(str) {
  return str.replace(camelizeRE, function(_2, c) {
    return c ? c.toUpperCase() : "";
  });
});
var capitalize = cached(function(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
});
var hyphenateRE = /\B([A-Z])/g;
var hyphenate = cached(function(str) {
  return str.replace(hyphenateRE, "-$1").toLowerCase();
});
nanoid.random;
var emitter = function emitter2() {
  var listeners = {};
  var on = function on2(event, callback) {
    var once = arguments.length > 2 && arguments[2] !== void 0 ? arguments[2] : false;
    if (event && typeof event === "string" && typeof callback === "function") {
      var callbacks = listeners[event] || [];
      listeners[event] = callbacks;
      callbacks.push(callback);
      callback.once = once;
    }
  };
  var emitter22 = {
    emit: function emit(eventName) {
      var _arguments = arguments;
      var callbacks = listeners[eventName];
      if (callbacks) {
        callbacks.forEach(function(callback) {
          return callback.apply(null, [].slice.call(_arguments, 1));
        });
        listeners[eventName] = callbacks.filter(function(callback) {
          return !callback.once;
        });
      }
    },
    on,
    once: function once(event, callback) {
      on(event, callback, true);
    },
    off: function off(event, callback) {
      if (event && typeof event === "string") {
        var callbacks = listeners[event];
        if (typeof callback === "function") {
          listeners[event] = callbacks.filter(function(cb) {
            return cb !== callback;
          });
        } else {
          delete listeners[event];
        }
      } else {
        listeners = {};
      }
    }
  };
  return emitter22;
};
var bindFilter = function bindFilter2(props2) {
  var attrs = arguments.length > 1 && arguments[1] !== void 0 ? arguments[1] : {};
  var properties = {};
  for (var name in props2) {
    if (name.indexOf("_") !== 0) {
      properties[name] = props2[name];
    }
  }
  for (var _name in attrs) {
    properties[_name] = attrs[_name];
  }
  return properties;
};
var getElementStatusClass = function getElementStatusClass2(className, status) {
  if (!className || !status)
    return;
  var classNames = [];
  if (typeof className === "string") {
    classNames.push(className);
  } else if (Array.isArray(className)) {
    classNames = className;
  }
  var statusList = [];
  if (typeof status === "string") {
    statusList.push(status);
  } else if (Array.isArray(status)) {
    statusList = status;
  }
  var res = [];
  statusList.forEach(function(status2) {
    return classNames.forEach(function(name) {
      return res.push("".concat(status2, ":").concat(name, "-").concat(status2));
    });
  });
  return classNames.concat(res).join(" ");
};
var getElementCssClass = function getElementCssClass2() {
  var classes = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : {};
  var key = arguments.length > 1 ? arguments[1] : void 0;
  if (_typeof(key) === "object") {
    var keys = Object.keys(key);
    var cls = "";
    keys.forEach(function(k) {
      if (key[k] && classes[k])
        cls += "".concat(classes[k], " ");
    });
    return cls;
  } else {
    return classes[key] || "";
  }
};
var Teleport = hooks.Teleport;
var defineAsyncComponent = hooks.defineAsyncComponent;
var markRaw = hooks.markRaw;
var renderComponent = function renderComponent2(_ref) {
  var _ref$view = _ref.view, view = _ref$view === void 0 ? void 0 : _ref$view, _ref$component = _ref.component, component = _ref$component === void 0 ? void 0 : _ref$component, props2 = _ref.props, _ref$context = _ref.context, attrs = _ref$context.attrs, slots = _ref$context.slots, _ref$extend = _ref.extend, extend = _ref$extend === void 0 ? {} : _ref$extend;
  return function() {
    return hooks.h(view && view.value || component, _objectSpread2(_objectSpread2(_objectSpread2({}, props2), attrs), extend), slots);
  };
};
var rootConfig = function rootConfig2(context) {
  var instance = hooks.getCurrentInstance();
  context && setInstanceEmitter(instance);
  return instance === null || instance === void 0 ? void 0 : instance.appContext.config.globalProperties;
};
var getComponentName = function getComponentName2() {
  var _instance$type;
  var instance = hooks.getCurrentInstance();
  var componentName = instance === null || instance === void 0 ? void 0 : (_instance$type = instance.type) === null || _instance$type === void 0 ? void 0 : _instance$type.name;
  if (!componentName) {
    var _instance$parent, _instance$parent$type;
    componentName = instance === null || instance === void 0 ? void 0 : (_instance$parent = instance.parent) === null || _instance$parent === void 0 ? void 0 : (_instance$parent$type = _instance$parent.type) === null || _instance$parent$type === void 0 ? void 0 : _instance$parent$type.name;
  }
  return componentName || "";
};
var appContext = function appContext2() {
  var _hooks$getCurrentInst;
  return ((_hooks$getCurrentInst = hooks.getCurrentInstance()) === null || _hooks$getCurrentInst === void 0 ? void 0 : _hooks$getCurrentInst.appContext) || {
    component: function component() {
    }
  };
};
var appProperties = function appProperties2() {
  var instance = hooks.getCurrentInstance();
  return (instance === null || instance === void 0 ? void 0 : instance.appContext.config.globalProperties) || {};
};
var useRouter = function useRouter2() {
  var instance = arguments.length > 0 && arguments[0] !== void 0 ? arguments[0] : hooks.getCurrentInstance();
  var router = instance === null || instance === void 0 ? void 0 : instance.appContext.config.globalProperties.$router;
  var route = router && router.currentRoute.value;
  return {
    route,
    router
  };
};
var setInstanceEmitter = function setInstanceEmitter2(instance) {
  var $emitter = emitter();
  if (typeof instance.$emitter === "undefined")
    Object.defineProperty(instance, "$emitter", {
      get: function get() {
        return $emitter;
      }
    });
};
var emitEvent = function emitEvent2(vm) {
  var _broadcast = function broadcast(vm2, componentName, eventName, params) {
    var children22 = vm2.subTree && vm2.subTree.children || vm2.children;
    Array.isArray(children22) && children22.forEach(function(child) {
      var name = child.type && child.type.componentName;
      var component = child.component;
      if (name === componentName) {
        component.emit(eventName, params);
        component.$emitter && component.$emitter.emit(eventName, params);
      } else {
        _broadcast(child, componentName, eventName, params);
      }
    });
  };
  return {
    dispatch: function dispatch(componentName, eventName, params) {
      var parent22 = vm.parent || vm.root;
      var name = parent22.type && parent22.type.componentName;
      while (parent22 && (!name || name !== componentName)) {
        parent22 = parent22.parent;
        if (parent22)
          name = parent22.type && parent22.type.componentName;
      }
      if (parent22) {
        var _parent, _parent2$$emitter;
        (_parent = parent22).emit.apply(_parent, _toConsumableArray([eventName].concat(params)));
        parent22.$emitter && (_parent2$$emitter = parent22.$emitter).emit.apply(_parent2$$emitter, _toConsumableArray([eventName].concat(params)));
      }
    },
    broadcast: function broadcast(componentName, eventName, params) {
      _broadcast(vm, componentName, eventName, params);
    }
  };
};
var getRealParent = function getRealParent2(vm) {
  if (vm && vm.parent)
    return vm.parent.type.name === "AsyncComponentWrapper" && vm.parent.parent ? vm.parent.parent : vm.parent;
};
var parent = function parent2(vm) {
  return function(handler) {
    var parent22 = getRealParent(vm);
    var level = 0;
    var parentObject = function parentObject2(parent3) {
      return {
        level,
        vm: createVm({}, parent3),
        el: parent3.vnode.el,
        options: parent3.type
      };
    };
    if (typeof handler !== "function")
      return parent22 ? parentObject(parent22) : {};
    level++;
    while (parent22) {
      if (handler(parentObject(parent22)))
        break;
      parent22 = getRealParent(parent22);
      level++;
    }
  };
};
var children = function children2(vm) {
  return function(handler) {
    if (typeof handler !== "function")
      return generateChildren(vm.subTree);
    var layer = 1;
    var broadcast = function broadcast2(subTree) {
      if (subTree) {
        var children22 = subTree.children || subTree.dynamicChildren;
        var level = layer++;
        if (Array.isArray(children22)) {
          if (children22.some(function(child) {
            return child.component && handler({
              level,
              vm: createVm({}, child.component),
              el: child.el,
              options: child.type,
              isLevel1: true
            });
          }))
            return;
          children22.forEach(function(child) {
            return broadcast2(child);
          });
        }
      }
    };
    broadcast(vm.subTree);
  };
};
var regEventPrefix = /^on[A-Z]/;
var generateListeners = function generateListeners2(attrs) {
  var $attrs = {};
  var $listeners = {};
  for (var name in attrs) {
    var event = attrs[name];
    if (regEventPrefix.test(name) && typeof event === "function") {
      $listeners[hyphenate(name.substr(2))] = event;
      continue;
    }
    $attrs[name] = event;
  }
  return {
    $attrs,
    $listeners
  };
};
var generateChildren = function generateChildren2(subTree) {
  var children22 = [];
  children22.refs = {};
  if (subTree) {
    var arr = subTree.dynamicChildren || subTree.children;
    if (Array.isArray(arr)) {
      arr.forEach(function(child) {
        if (child.component) {
          var vm = createVm({}, child.component);
          children22.push(vm);
          child.props.ref && (children22.refs[child.props.ref] = vm);
        }
      });
    } else if (subTree.component) {
      children22.push(createVm({}, subTree.component));
    }
  }
  return children22;
};
var defineProperties = function defineProperties2(vm, instance, property, filter22) {
  var _loop = function _loop2(name2) {
    if (typeof filter22 === "function" && filter22(name2))
      return 1;
    Object.defineProperty(vm, name2, {
      configurable: true,
      enumerable: true,
      get: function get() {
        return instance[property][name2];
      },
      set: function set(value) {
        return instance[property][name2] = value;
      }
    });
  };
  for (var name in instance[property]) {
    if (_loop(name))
      continue;
  }
  return vm;
};
var filter = function filter2(name) {
  return name.indexOf("_") === 0;
};
var defineInstanceVm = function defineInstanceVm2(vm, instance) {
  defineProperties(vm, instance, "setupState", null);
  defineProperties(vm, instance, "props", filter);
  defineProperties(vm, instance, "ctx", filter);
  return vm;
};
var createVm = function createVm2(vm, instance) {
  var context = arguments.length > 2 && arguments[2] !== void 0 ? arguments[2] : null;
  var _generateListeners = generateListeners(instance.attrs), $attrs = _generateListeners.$attrs, $listeners = _generateListeners.$listeners;
  var $emitter = instance.$emitter;
  if (!$emitter) {
    setInstanceEmitter(instance);
    $emitter = instance.$emitter;
  }
  var emit = function emit2() {
    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }
    instance.emit.apply(instance, args);
    $emitter.emit.apply(vm, args);
  };
  var $set = function $set2(target, propertyName, value) {
    return target[propertyName] = value;
  };
  context || defineInstanceVm(vm, instance);
  Object.defineProperties(vm, {
    $attrs: {
      get: function get() {
        return $attrs;
      }
    },
    $children: {
      get: function get() {
        return generateChildren(instance.subTree);
      }
    },
    $constants: {
      get: function get() {
        return instance.props._constants;
      }
    },
    $emit: {
      get: function get() {
        return emit;
      }
    },
    $el: {
      get: function get() {
        return instance.vnode.el;
      }
    },
    $listeners: {
      get: function get() {
        return $listeners;
      }
    },
    $mode: {
      get: function get() {
        return instance._tiny_mode;
      }
    },
    $nextTick: {
      get: function get() {
        return hooks.nextTick;
      }
    },
    $off: {
      get: function get() {
        return $emitter.off;
      }
    },
    $on: {
      get: function get() {
        return $emitter.on;
      }
    },
    $once: {
      get: function get() {
        return $emitter.once;
      }
    },
    $options: {
      get: function get() {
        return {
          componentName: instance.type.componentName
        };
      }
    },
    $parent: {
      get: function get() {
        return instance.parent && createVm2({}, getRealParent(instance));
      }
    },
    $refs: {
      get: function get() {
        return instance.refs;
      }
    },
    $renderless: {
      get: function get() {
        return instance.props.tiny_renderless;
      }
    },
    $scopedSlots: {
      get: function get() {
        return instance.slots;
      }
    },
    $set: {
      get: function get() {
        return $set;
      }
    },
    $slots: {
      get: function get() {
        return instance.slots;
      }
    },
    $template: {
      get: function get() {
        return instance.props.tiny_template;
      }
    }
  });
  return vm;
};
var onBeforeMount = function onBeforeMount2(instance, refs) {
  for (var name in instance.refs) {
    if (Object.prototype.hasOwnProperty.call(instance.refs, name)) {
      refs[name] = instance.refs[name];
    }
  }
};
var tools = function tools2(context, mode) {
  var _instance$proxy, _instance$proxy$$root;
  var instance = hooks.getCurrentInstance();
  var root = instance === null || instance === void 0 ? void 0 : instance.appContext.config.globalProperties;
  var _useRouter = useRouter(instance), route = _useRouter.route, router = _useRouter.router;
  var i18n = instance === null || instance === void 0 ? void 0 : (_instance$proxy = instance.proxy) === null || _instance$proxy === void 0 ? void 0 : (_instance$proxy$$root = _instance$proxy.$root) === null || _instance$proxy$$root === void 0 ? void 0 : _instance$proxy$$root.$i18n;
  var _emitEvent = emitEvent(instance), dispatch = _emitEvent.dispatch, broadcast = _emitEvent.broadcast;
  var parentHandler = parent(instance);
  var childrenHandler = children(instance);
  var vm = createVm({}, instance, context);
  var emit = context.emit;
  var refs = {};
  var grandParent = typeof instance.props.tiny_template === "undefined" && getRealParent(instance);
  var parentVm = grandParent ? createVm({}, grandParent) : instance.parent ? createVm({}, instance.parent) : null;
  var setParentAttribute = function setParentAttribute2(_ref2) {
    var _instance$parent2;
    var name = _ref2.name, value = _ref2.value;
    var ctx = grandParent ? grandParent.ctx : instance === null || instance === void 0 ? void 0 : (_instance$parent2 = instance.parent) === null || _instance$parent2 === void 0 ? void 0 : _instance$parent2.ctx;
    ctx[name] = value;
    parentVm[name] = value;
  };
  var defineInstanceProperties = function defineInstanceProperties2(props2) {
    Object.defineProperties(vm, props2);
    Object.defineProperties(instance === null || instance === void 0 ? void 0 : instance.ctx, props2);
  };
  var defineParentInstanceProperties = function defineParentInstanceProperties2(props2) {
    parentVm && Object.defineProperties(parentVm, props2);
  };
  hooks.onBeforeMount(function() {
    return defineInstanceVm(vm, instance);
  });
  hooks.onMounted(function() {
    return onBeforeMount(instance, refs);
  });
  return {
    framework: "vue3",
    vm,
    emit,
    emitter,
    route,
    router,
    dispatch,
    broadcast,
    parentHandler,
    childrenHandler,
    i18n,
    refs,
    slots: instance === null || instance === void 0 ? void 0 : instance.slots,
    scopedSlots: instance === null || instance === void 0 ? void 0 : instance.slots,
    attrs: context.attrs,
    parent: parentVm,
    nextTick: hooks.nextTick,
    constants: instance === null || instance === void 0 ? void 0 : instance.props._constants,
    mode,
    isPCMode: mode === "pc",
    isMobileMode: mode === "mobile",
    service: root === null || root === void 0 ? void 0 : root.$service,
    getService: function getService() {
      return root === null || root === void 0 ? void 0 : root.$getService(vm);
    },
    setParentAttribute,
    defineInstanceProperties,
    defineParentInstanceProperties
  };
};
var mapping = function mapping2(content, before, after) {
  if (typeof content[before] !== "undefined") {
    var fn = content[before];
    content[after] = function(el, binding, vnode) {
      vnode.context = binding.instance;
      fn(el, binding, vnode);
    };
    delete content[before];
  }
};
var directive = function directive2(directives) {
  for (var name in directives) {
    var content = directives[name];
    mapping(content, "bind", "beforeMount");
    mapping(content, "update", "updated");
    mapping(content, "unbind", "unmounted");
  }
  return directives;
};
var parseVnode = function parseVnode2(vnode) {
  return vnode;
};
var parseProps = function parseProps2(propsData) {
  var props2 = {};
  for (var name in propsData) {
    if (name === "class" || name === "style") {
      props2[name] = propsData[name];
    } else if (name === "on" || name === "nativeOn") {
      var events = propsData[name];
      for (var eventName in events)
        props2["on".concat(capitalize(camelize(eventName)))] = events[eventName];
    } else if (name === "attrs" || name === "props" || name === "domProps") {
      var attrs = propsData[name];
      for (var key in attrs)
        props2[key] = attrs[key];
    } else {
      props2[name] = propsData[name];
    }
  }
  return props2;
};
var customResolveComponent = function customResolveComponent2(component) {
  var type = component;
  var customElement = false;
  if (typeof component === "string" && typeof document !== "undefined") {
    var el = document.createElement(component);
    var svgTagNames = ["SVG", "CIRCLE", "PATH"];
    if (el instanceof HTMLUnknownElement && !svgTagNames.includes(el.nodeName) || component.includes("-")) {
      component = component.toLowerCase();
      customElement = true;
      if (component === "transition")
        type = hooks.Transition;
      else if (component === "transition-group")
        type = hooks.TransitionGroup;
      else
        type = hooks.resolveComponent(component);
    } else {
      type = component;
    }
  }
  return {
    type,
    component,
    customElement
  };
};
var h = function h2(component, propsData, childData) {
  var props2 = {};
  var children22 = childData;
  var ret = customResolveComponent(component);
  var customElement = ret.customElement;
  var type = ret.type;
  component = ret.component;
  if (propsData && _typeof(propsData) === "object" && !Array.isArray(propsData)) {
    props2 = parseProps(propsData);
    propsData.scopedSlots && (children22 = propsData.scopedSlots);
  } else if (typeof propsData === "string" || Array.isArray(propsData)) {
    childData = propsData;
  }
  if (typeof childData === "string" || Array.isArray(childData))
    children22 = typeof component !== "string" || customElement ? function() {
      return childData;
    } : childData;
  return hooks.h(type, props2, children22);
};
var createComponentFn = function createComponentFn2(design2) {
  return function(_ref3) {
    var component = _ref3.component, propsData = _ref3.propsData, el = _ref3.el;
    var comp = Object.assign(component, {
      provide: _defineProperty({}, design2.configKey, design2.configInstance)
    });
    var vnode = hooks.createVNode(comp, propsData);
    hooks.render(vnode, el);
    return createVm({}, vnode.component);
  };
};
var defineComponent = hooks.defineComponent;
var isVue2 = false;
var isVue3 = true;
var stringifyCssClassObject = function stringifyCssClassObject2(cssClassObject) {
  var allCssClass = [];
  Object.keys(cssClassObject).forEach(function(cssClass) {
    return cssClassObject[cssClass] && allCssClass.push(cssClass);
  });
  return allCssClass.join(" ");
};
var stringifyCssClassArray = function stringifyCssClassArray2(cssClassArray) {
  var allCssClass = [];
  cssClassArray.forEach(function(cssClass) {
    if (typeof cssClass === "string") {
      allCssClass.push(cssClass);
    } else if (_typeof(cssClass) === "object") {
      allCssClass.push(stringifyCssClassObject(cssClass));
    }
  });
  return allCssClass.join(" ");
};
var stringifyCssClass = function stringifyCssClass2(cssClasses) {
  if (!cssClasses || Array.isArray(cssClasses) && !cssClasses.length)
    return "";
  var allCssClass = [];
  cssClasses.forEach(function(cssClass) {
    if (cssClass) {
      if (typeof cssClass === "string") {
        allCssClass.push(cssClass);
      } else if (Array.isArray(cssClass)) {
        allCssClass.push(stringifyCssClassArray(cssClass));
      } else if (_typeof(cssClass) === "object") {
        allCssClass.push(stringifyCssClassObject(cssClass));
      }
    }
  });
  return allCssClass.join(" ");
};
function twJoin() {
  var index2 = 0;
  var argument;
  var resolvedValue;
  var string = "";
  while (index2 < arguments.length) {
    if (argument = arguments[index2++]) {
      if (resolvedValue = toValue(argument)) {
        string && (string += " ");
        string += resolvedValue;
      }
    }
  }
  return string;
}
function toValue(mix) {
  if (typeof mix === "string") {
    return mix;
  }
  var resolvedValue;
  var string = "";
  for (var k = 0; k < mix.length; k++) {
    if (mix[k]) {
      if (resolvedValue = toValue(mix[k])) {
        string && (string += " ");
        string += resolvedValue;
      }
    }
  }
  return string;
}
function _extends() {
  _extends = Object.assign ? Object.assign.bind() : function(target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i];
      for (var key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
          target[key] = source[key];
        }
      }
    }
    return target;
  };
  return _extends.apply(this, arguments);
}
function createLruCache(maxCacheSize) {
  if (maxCacheSize < 1) {
    return {
      get: function get() {
        return void 0;
      },
      set: function set() {
      }
    };
  }
  var cacheSize = 0;
  var cache = /* @__PURE__ */ new Map();
  var previousCache = /* @__PURE__ */ new Map();
  function update(key, value) {
    cache.set(key, value);
    cacheSize++;
    if (cacheSize > maxCacheSize) {
      cacheSize = 0;
      previousCache = cache;
      cache = /* @__PURE__ */ new Map();
    }
  }
  return {
    get: function get(key) {
      var value = cache.get(key);
      if (value !== void 0) {
        return value;
      }
      if ((value = previousCache.get(key)) !== void 0) {
        update(key, value);
        return value;
      }
    },
    set: function set(key, value) {
      if (cache.has(key)) {
        cache.set(key, value);
      } else {
        update(key, value);
      }
    }
  };
}
var CLASS_PART_SEPARATOR = "-";
function createClassUtils(config) {
  var classMap = createClassMap(config);
  function getClassGroupId(className) {
    var classParts = className.split(CLASS_PART_SEPARATOR);
    if (classParts[0] === "" && classParts.length !== 1) {
      classParts.shift();
    }
    return getGroupRecursive(classParts, classMap) || getGroupIdForArbitraryProperty(className);
  }
  function getConflictingClassGroupIds(classGroupId) {
    return config.conflictingClassGroups[classGroupId] || [];
  }
  return {
    getClassGroupId,
    getConflictingClassGroupIds
  };
}
function getGroupRecursive(classParts, classPartObject) {
  var _classPartObject$vali;
  if (classParts.length === 0) {
    return classPartObject.classGroupId;
  }
  var currentClassPart = classParts[0];
  var nextClassPartObject = classPartObject.nextPart.get(currentClassPart);
  var classGroupFromNextClassPart = nextClassPartObject ? getGroupRecursive(classParts.slice(1), nextClassPartObject) : void 0;
  if (classGroupFromNextClassPart) {
    return classGroupFromNextClassPart;
  }
  if (classPartObject.validators.length === 0) {
    return void 0;
  }
  var classRest = classParts.join(CLASS_PART_SEPARATOR);
  return (_classPartObject$vali = classPartObject.validators.find(function(_ref) {
    var validator = _ref.validator;
    return validator(classRest);
  })) == null ? void 0 : _classPartObject$vali.classGroupId;
}
var arbitraryPropertyRegex = /^\[(.+)\]$/;
function getGroupIdForArbitraryProperty(className) {
  if (arbitraryPropertyRegex.test(className)) {
    var arbitraryPropertyClassName = arbitraryPropertyRegex.exec(className)[1];
    var property = arbitraryPropertyClassName == null ? void 0 : arbitraryPropertyClassName.substring(0, arbitraryPropertyClassName.indexOf(":"));
    if (property) {
      return "arbitrary.." + property;
    }
  }
}
function createClassMap(config) {
  var theme = config.theme, prefix = config.prefix;
  var classMap = {
    nextPart: /* @__PURE__ */ new Map(),
    validators: []
  };
  var prefixedClassGroupEntries = getPrefixedClassGroupEntries(Object.entries(config.classGroups), prefix);
  prefixedClassGroupEntries.forEach(function(_ref2) {
    var classGroupId = _ref2[0], classGroup = _ref2[1];
    processClassesRecursively(classGroup, classMap, classGroupId, theme);
  });
  return classMap;
}
function processClassesRecursively(classGroup, classPartObject, classGroupId, theme) {
  classGroup.forEach(function(classDefinition) {
    if (typeof classDefinition === "string") {
      var classPartObjectToEdit = classDefinition === "" ? classPartObject : getPart(classPartObject, classDefinition);
      classPartObjectToEdit.classGroupId = classGroupId;
      return;
    }
    if (typeof classDefinition === "function") {
      if (isThemeGetter(classDefinition)) {
        processClassesRecursively(classDefinition(theme), classPartObject, classGroupId, theme);
        return;
      }
      classPartObject.validators.push({
        validator: classDefinition,
        classGroupId
      });
      return;
    }
    Object.entries(classDefinition).forEach(function(_ref3) {
      var key = _ref3[0], classGroup2 = _ref3[1];
      processClassesRecursively(classGroup2, getPart(classPartObject, key), classGroupId, theme);
    });
  });
}
function getPart(classPartObject, path) {
  var currentClassPartObject = classPartObject;
  path.split(CLASS_PART_SEPARATOR).forEach(function(pathPart) {
    if (!currentClassPartObject.nextPart.has(pathPart)) {
      currentClassPartObject.nextPart.set(pathPart, {
        nextPart: /* @__PURE__ */ new Map(),
        validators: []
      });
    }
    currentClassPartObject = currentClassPartObject.nextPart.get(pathPart);
  });
  return currentClassPartObject;
}
function isThemeGetter(func) {
  return func.isThemeGetter;
}
function getPrefixedClassGroupEntries(classGroupEntries, prefix) {
  if (!prefix) {
    return classGroupEntries;
  }
  return classGroupEntries.map(function(_ref4) {
    var classGroupId = _ref4[0], classGroup = _ref4[1];
    var prefixedClassGroup = classGroup.map(function(classDefinition) {
      if (typeof classDefinition === "string") {
        return prefix + classDefinition;
      }
      if (_typeof(classDefinition) === "object") {
        return Object.fromEntries(Object.entries(classDefinition).map(function(_ref5) {
          var key = _ref5[0], value = _ref5[1];
          return [prefix + key, value];
        }));
      }
      return classDefinition;
    });
    return [classGroupId, prefixedClassGroup];
  });
}
var IMPORTANT_MODIFIER = "!";
function createSplitModifiers(config) {
  var separator = config.separator || ":";
  return function splitModifiers(className) {
    var bracketDepth = 0;
    var modifiers = [];
    var modifierStart = 0;
    for (var index2 = 0; index2 < className.length; index2++) {
      var _char = className[index2];
      if (bracketDepth === 0 && _char === separator[0]) {
        if (separator.length === 1 || className.slice(index2, index2 + separator.length) === separator) {
          modifiers.push(className.slice(modifierStart, index2));
          modifierStart = index2 + separator.length;
        }
      }
      if (_char === "[") {
        bracketDepth++;
      } else if (_char === "]") {
        bracketDepth--;
      }
    }
    var baseClassNameWithImportantModifier = modifiers.length === 0 ? className : className.substring(modifierStart);
    var hasImportantModifier = baseClassNameWithImportantModifier.startsWith(IMPORTANT_MODIFIER);
    var baseClassName = hasImportantModifier ? baseClassNameWithImportantModifier.substring(1) : baseClassNameWithImportantModifier;
    return {
      modifiers,
      hasImportantModifier,
      baseClassName
    };
  };
}
function sortModifiers(modifiers) {
  if (modifiers.length <= 1) {
    return modifiers;
  }
  var sortedModifiers = [];
  var unsortedModifiers = [];
  modifiers.forEach(function(modifier) {
    var isArbitraryVariant = modifier[0] === "[";
    if (isArbitraryVariant) {
      sortedModifiers.push.apply(sortedModifiers, unsortedModifiers.sort().concat([modifier]));
      unsortedModifiers = [];
    } else {
      unsortedModifiers.push(modifier);
    }
  });
  sortedModifiers.push.apply(sortedModifiers, unsortedModifiers.sort());
  return sortedModifiers;
}
function createConfigUtils(config) {
  return _extends({
    cache: createLruCache(config.cacheSize),
    splitModifiers: createSplitModifiers(config)
  }, createClassUtils(config));
}
var SPLIT_CLASSES_REGEX = /\s+/;
function mergeClassList(classList, configUtils) {
  var splitModifiers = configUtils.splitModifiers, getClassGroupId = configUtils.getClassGroupId, getConflictingClassGroupIds = configUtils.getConflictingClassGroupIds;
  var classGroupsInConflict = /* @__PURE__ */ new Set();
  return classList.trim().split(SPLIT_CLASSES_REGEX).map(function(originalClassName) {
    var _splitModifiers = splitModifiers(originalClassName), modifiers = _splitModifiers.modifiers, hasImportantModifier = _splitModifiers.hasImportantModifier, baseClassName = _splitModifiers.baseClassName;
    var classGroupId = getClassGroupId(baseClassName);
    if (!classGroupId) {
      return {
        isTailwindClass: false,
        originalClassName
      };
    }
    var variantModifier = sortModifiers(modifiers).join(":");
    var modifierId = hasImportantModifier ? variantModifier + IMPORTANT_MODIFIER : variantModifier;
    return {
      isTailwindClass: true,
      modifierId,
      classGroupId,
      originalClassName
    };
  }).reverse().filter(function(parsed) {
    if (!parsed.isTailwindClass) {
      return true;
    }
    var modifierId = parsed.modifierId, classGroupId = parsed.classGroupId;
    var classId = modifierId + classGroupId;
    if (classGroupsInConflict.has(classId)) {
      return false;
    }
    classGroupsInConflict.add(classId);
    getConflictingClassGroupIds(classGroupId).forEach(function(group) {
      return classGroupsInConflict.add(modifierId + group);
    });
    return true;
  }).reverse().map(function(parsed) {
    return parsed.originalClassName;
  }).join(" ");
}
function createTailwindMerge() {
  for (var _len = arguments.length, createConfig = new Array(_len), _key = 0; _key < _len; _key++) {
    createConfig[_key] = arguments[_key];
  }
  var configUtils;
  var cacheGet;
  var cacheSet;
  var functionToCall = initTailwindMerge;
  function initTailwindMerge(classList) {
    var firstCreateConfig = createConfig[0], restCreateConfig = createConfig.slice(1);
    var config = restCreateConfig.reduce(function(previousConfig, createConfigCurrent) {
      return createConfigCurrent(previousConfig);
    }, firstCreateConfig());
    configUtils = createConfigUtils(config);
    cacheGet = configUtils.cache.get;
    cacheSet = configUtils.cache.set;
    functionToCall = tailwindMerge;
    return tailwindMerge(classList);
  }
  function tailwindMerge(classList) {
    var cachedResult = cacheGet(classList);
    if (cachedResult) {
      return cachedResult;
    }
    var result = mergeClassList(classList, configUtils);
    cacheSet(classList, result);
    return result;
  }
  return function callTailwindMerge() {
    return functionToCall(twJoin.apply(null, arguments));
  };
}
function fromTheme(key) {
  var themeGetter = function themeGetter2(theme) {
    return theme[key] || [];
  };
  themeGetter.isThemeGetter = true;
  return themeGetter;
}
var arbitraryValueRegex = /^\[(.+)\]$/;
var fractionRegex = /^\d+\/\d+$/;
var stringLengths = /* @__PURE__ */ new Set(["px", "full", "screen"]);
var tshirtUnitRegex = /^(\d+)?(xs|sm|md|lg|xl)$/;
var lengthUnitRegex = /\d+(%|px|r?em|[sdl]?v([hwib]|min|max)|pt|pc|in|cm|mm|cap|ch|ex|r?lh)/;
var shadowRegex = /^-?((\d+)?\.?(\d+)[a-z]+|0)_-?((\d+)?\.?(\d+)[a-z]+|0)/;
function isLength(classPart) {
  return !Number.isNaN(Number(classPart)) || stringLengths.has(classPart) || fractionRegex.test(classPart) || isArbitraryLength(classPart);
}
function isArbitraryLength(classPart) {
  var _arbitraryValueRegex$;
  var arbitraryValue = (_arbitraryValueRegex$ = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$[1];
  if (arbitraryValue) {
    return arbitraryValue.startsWith("length:") || lengthUnitRegex.test(arbitraryValue);
  }
  return false;
}
function isArbitrarySize(classPart) {
  var _arbitraryValueRegex$2;
  var arbitraryValue = (_arbitraryValueRegex$2 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$2[1];
  return arbitraryValue ? arbitraryValue.startsWith("size:") : false;
}
function isArbitraryPosition(classPart) {
  var _arbitraryValueRegex$3;
  var arbitraryValue = (_arbitraryValueRegex$3 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$3[1];
  return arbitraryValue ? arbitraryValue.startsWith("position:") : false;
}
function isArbitraryUrl(classPart) {
  var _arbitraryValueRegex$4;
  var arbitraryValue = (_arbitraryValueRegex$4 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$4[1];
  return arbitraryValue ? arbitraryValue.startsWith("url(") || arbitraryValue.startsWith("url:") : false;
}
function isArbitraryNumber(classPart) {
  var _arbitraryValueRegex$5;
  var arbitraryValue = (_arbitraryValueRegex$5 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$5[1];
  return arbitraryValue ? !Number.isNaN(Number(arbitraryValue)) || arbitraryValue.startsWith("number:") : false;
}
function isInteger(classPart) {
  var _arbitraryValueRegex$6;
  var arbitraryValue = (_arbitraryValueRegex$6 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$6[1];
  if (arbitraryValue) {
    return Number.isInteger(Number(arbitraryValue));
  }
  return Number.isInteger(Number(classPart));
}
function isArbitraryValue(classPart) {
  return arbitraryValueRegex.test(classPart);
}
function isAny() {
  return true;
}
function isTshirtSize(classPart) {
  return tshirtUnitRegex.test(classPart);
}
function isArbitraryShadow(classPart) {
  var _arbitraryValueRegex$7;
  var arbitraryValue = (_arbitraryValueRegex$7 = arbitraryValueRegex.exec(classPart)) == null ? void 0 : _arbitraryValueRegex$7[1];
  if (arbitraryValue) {
    return shadowRegex.test(arbitraryValue);
  }
  return false;
}
function getDefaultConfig() {
  var colors = fromTheme("colors");
  var spacing = fromTheme("spacing");
  var blur = fromTheme("blur");
  var brightness = fromTheme("brightness");
  var borderColor = fromTheme("borderColor");
  var borderRadius = fromTheme("borderRadius");
  var borderSpacing = fromTheme("borderSpacing");
  var borderWidth = fromTheme("borderWidth");
  var contrast = fromTheme("contrast");
  var grayscale = fromTheme("grayscale");
  var hueRotate = fromTheme("hueRotate");
  var invert = fromTheme("invert");
  var gap = fromTheme("gap");
  var gradientColorStops = fromTheme("gradientColorStops");
  var inset = fromTheme("inset");
  var margin = fromTheme("margin");
  var opacity = fromTheme("opacity");
  var padding = fromTheme("padding");
  var saturate = fromTheme("saturate");
  var scale = fromTheme("scale");
  var sepia = fromTheme("sepia");
  var skew = fromTheme("skew");
  var space = fromTheme("space");
  var translate = fromTheme("translate");
  var getOverscroll = function getOverscroll2() {
    return ["auto", "contain", "none"];
  };
  var getOverflow = function getOverflow2() {
    return ["auto", "hidden", "clip", "visible", "scroll"];
  };
  var getSpacingWithAuto = function getSpacingWithAuto2() {
    return ["auto", spacing];
  };
  var getLengthWithEmpty = function getLengthWithEmpty2() {
    return ["", isLength];
  };
  var getIntegerWithAuto = function getIntegerWithAuto2() {
    return ["auto", isInteger];
  };
  var getPositions = function getPositions2() {
    return ["bottom", "center", "left", "left-bottom", "left-top", "right", "right-bottom", "right-top", "top"];
  };
  var getLineStyles = function getLineStyles2() {
    return ["solid", "dashed", "dotted", "double", "none"];
  };
  var getBlendModes = function getBlendModes2() {
    return ["normal", "multiply", "screen", "overlay", "darken", "lighten", "color-dodge", "color-burn", "hard-light", "soft-light", "difference", "exclusion", "hue", "saturation", "color", "luminosity", "plus-lighter"];
  };
  var getAlign = function getAlign2() {
    return ["start", "end", "center", "between", "around", "evenly"];
  };
  var getZeroAndEmpty = function getZeroAndEmpty2() {
    return ["", "0", isArbitraryValue];
  };
  var getBreaks = function getBreaks2() {
    return ["auto", "avoid", "all", "avoid-page", "page", "left", "right", "column"];
  };
  return {
    cacheSize: 500,
    theme: {
      colors: [isAny],
      spacing: [isLength],
      blur: ["none", "", isTshirtSize, isArbitraryLength],
      brightness: [isInteger],
      borderColor: [colors],
      borderRadius: ["none", "", "full", isTshirtSize, isArbitraryLength],
      borderSpacing: [spacing],
      borderWidth: getLengthWithEmpty(),
      contrast: [isInteger],
      grayscale: getZeroAndEmpty(),
      hueRotate: [isInteger],
      invert: getZeroAndEmpty(),
      gap: [spacing],
      gradientColorStops: [colors],
      inset: getSpacingWithAuto(),
      margin: getSpacingWithAuto(),
      opacity: [isInteger],
      padding: [spacing],
      saturate: [isInteger],
      scale: [isInteger],
      sepia: getZeroAndEmpty(),
      skew: [isInteger, isArbitraryValue],
      space: [spacing],
      translate: [spacing]
    },
    classGroups: {
      // Layout
      /**
       * Aspect Ratio
       * @see https://tailwindcss.com/docs/aspect-ratio
       */
      aspect: [{
        aspect: ["auto", "square", "video", isArbitraryValue]
      }],
      /**
       * Container
       * @see https://tailwindcss.com/docs/container
       */
      container: ["container"],
      /**
       * Columns
       * @see https://tailwindcss.com/docs/columns
       */
      columns: [{
        columns: [isTshirtSize]
      }],
      /**
       * Break After
       * @see https://tailwindcss.com/docs/break-after
       */
      "break-after": [{
        "break-after": getBreaks()
      }],
      /**
       * Break Before
       * @see https://tailwindcss.com/docs/break-before
       */
      "break-before": [{
        "break-before": getBreaks()
      }],
      /**
       * Break Inside
       * @see https://tailwindcss.com/docs/break-inside
       */
      "break-inside": [{
        "break-inside": ["auto", "avoid", "avoid-page", "avoid-column"]
      }],
      /**
       * Box Decoration Break
       * @see https://tailwindcss.com/docs/box-decoration-break
       */
      "box-decoration": [{
        "box-decoration": ["slice", "clone"]
      }],
      /**
       * Box Sizing
       * @see https://tailwindcss.com/docs/box-sizing
       */
      box: [{
        box: ["border", "content"]
      }],
      /**
       * Display
       * @see https://tailwindcss.com/docs/display
       */
      display: ["block", "inline-block", "inline", "flex", "inline-flex", "table", "inline-table", "table-caption", "table-cell", "table-column", "table-column-group", "table-footer-group", "table-header-group", "table-row-group", "table-row", "flow-root", "grid", "inline-grid", "contents", "list-item", "hidden"],
      /**
       * Floats
       * @see https://tailwindcss.com/docs/float
       */
      "float": [{
        "float": ["right", "left", "none"]
      }],
      /**
       * Clear
       * @see https://tailwindcss.com/docs/clear
       */
      clear: [{
        clear: ["left", "right", "both", "none"]
      }],
      /**
       * Isolation
       * @see https://tailwindcss.com/docs/isolation
       */
      isolation: ["isolate", "isolation-auto"],
      /**
       * Object Fit
       * @see https://tailwindcss.com/docs/object-fit
       */
      "object-fit": [{
        object: ["contain", "cover", "fill", "none", "scale-down"]
      }],
      /**
       * Object Position
       * @see https://tailwindcss.com/docs/object-position
       */
      "object-position": [{
        object: [].concat(getPositions(), [isArbitraryValue])
      }],
      /**
       * Overflow
       * @see https://tailwindcss.com/docs/overflow
       */
      overflow: [{
        overflow: getOverflow()
      }],
      /**
       * Overflow X
       * @see https://tailwindcss.com/docs/overflow
       */
      "overflow-x": [{
        "overflow-x": getOverflow()
      }],
      /**
       * Overflow Y
       * @see https://tailwindcss.com/docs/overflow
       */
      "overflow-y": [{
        "overflow-y": getOverflow()
      }],
      /**
       * Overscroll Behavior
       * @see https://tailwindcss.com/docs/overscroll-behavior
       */
      overscroll: [{
        overscroll: getOverscroll()
      }],
      /**
       * Overscroll Behavior X
       * @see https://tailwindcss.com/docs/overscroll-behavior
       */
      "overscroll-x": [{
        "overscroll-x": getOverscroll()
      }],
      /**
       * Overscroll Behavior Y
       * @see https://tailwindcss.com/docs/overscroll-behavior
       */
      "overscroll-y": [{
        "overscroll-y": getOverscroll()
      }],
      /**
       * Position
       * @see https://tailwindcss.com/docs/position
       */
      position: ["static", "fixed", "absolute", "relative", "sticky"],
      /**
       * Top / Right / Bottom / Left
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      inset: [{
        inset: [inset]
      }],
      /**
       * Right / Left
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      "inset-x": [{
        "inset-x": [inset]
      }],
      /**
       * Top / Bottom
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      "inset-y": [{
        "inset-y": [inset]
      }],
      /**
       * Top
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      top: [{
        top: [inset]
      }],
      /**
       * Right
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      right: [{
        right: [inset]
      }],
      /**
       * Bottom
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      bottom: [{
        bottom: [inset]
      }],
      /**
       * Left
       * @see https://tailwindcss.com/docs/top-right-bottom-left
       */
      left: [{
        left: [inset]
      }],
      /**
       * Visibility
       * @see https://tailwindcss.com/docs/visibility
       */
      visibility: ["visible", "invisible", "collapse"],
      /**
       * Z-Index
       * @see https://tailwindcss.com/docs/z-index
       */
      z: [{
        z: [isInteger]
      }],
      // Flexbox and Grid
      /**
       * Flex Basis
       * @see https://tailwindcss.com/docs/flex-basis
       */
      basis: [{
        basis: [spacing]
      }],
      /**
       * Flex Direction
       * @see https://tailwindcss.com/docs/flex-direction
       */
      "flex-direction": [{
        flex: ["row", "row-reverse", "col", "col-reverse"]
      }],
      /**
       * Flex Wrap
       * @see https://tailwindcss.com/docs/flex-wrap
       */
      "flex-wrap": [{
        flex: ["wrap", "wrap-reverse", "nowrap"]
      }],
      /**
       * Flex
       * @see https://tailwindcss.com/docs/flex
       */
      flex: [{
        flex: ["1", "auto", "initial", "none", isArbitraryValue]
      }],
      /**
       * Flex Grow
       * @see https://tailwindcss.com/docs/flex-grow
       */
      grow: [{
        grow: getZeroAndEmpty()
      }],
      /**
       * Flex Shrink
       * @see https://tailwindcss.com/docs/flex-shrink
       */
      shrink: [{
        shrink: getZeroAndEmpty()
      }],
      /**
       * Order
       * @see https://tailwindcss.com/docs/order
       */
      order: [{
        order: ["first", "last", "none", isInteger]
      }],
      /**
       * Grid Template Columns
       * @see https://tailwindcss.com/docs/grid-template-columns
       */
      "grid-cols": [{
        "grid-cols": [isAny]
      }],
      /**
       * Grid Column Start / End
       * @see https://tailwindcss.com/docs/grid-column
       */
      "col-start-end": [{
        col: ["auto", {
          span: [isInteger]
        }]
      }],
      /**
       * Grid Column Start
       * @see https://tailwindcss.com/docs/grid-column
       */
      "col-start": [{
        "col-start": getIntegerWithAuto()
      }],
      /**
       * Grid Column End
       * @see https://tailwindcss.com/docs/grid-column
       */
      "col-end": [{
        "col-end": getIntegerWithAuto()
      }],
      /**
       * Grid Template Rows
       * @see https://tailwindcss.com/docs/grid-template-rows
       */
      "grid-rows": [{
        "grid-rows": [isAny]
      }],
      /**
       * Grid Row Start / End
       * @see https://tailwindcss.com/docs/grid-row
       */
      "row-start-end": [{
        row: ["auto", {
          span: [isInteger]
        }]
      }],
      /**
       * Grid Row Start
       * @see https://tailwindcss.com/docs/grid-row
       */
      "row-start": [{
        "row-start": getIntegerWithAuto()
      }],
      /**
       * Grid Row End
       * @see https://tailwindcss.com/docs/grid-row
       */
      "row-end": [{
        "row-end": getIntegerWithAuto()
      }],
      /**
       * Grid Auto Flow
       * @see https://tailwindcss.com/docs/grid-auto-flow
       */
      "grid-flow": [{
        "grid-flow": ["row", "col", "dense", "row-dense", "col-dense"]
      }],
      /**
       * Grid Auto Columns
       * @see https://tailwindcss.com/docs/grid-auto-columns
       */
      "auto-cols": [{
        "auto-cols": ["auto", "min", "max", "fr", isArbitraryValue]
      }],
      /**
       * Grid Auto Rows
       * @see https://tailwindcss.com/docs/grid-auto-rows
       */
      "auto-rows": [{
        "auto-rows": ["auto", "min", "max", "fr", isArbitraryValue]
      }],
      /**
       * Gap
       * @see https://tailwindcss.com/docs/gap
       */
      gap: [{
        gap: [gap]
      }],
      /**
       * Gap X
       * @see https://tailwindcss.com/docs/gap
       */
      "gap-x": [{
        "gap-x": [gap]
      }],
      /**
       * Gap Y
       * @see https://tailwindcss.com/docs/gap
       */
      "gap-y": [{
        "gap-y": [gap]
      }],
      /**
       * Justify Content
       * @see https://tailwindcss.com/docs/justify-content
       */
      "justify-content": [{
        justify: getAlign()
      }],
      /**
       * Justify Items
       * @see https://tailwindcss.com/docs/justify-items
       */
      "justify-items": [{
        "justify-items": ["start", "end", "center", "stretch"]
      }],
      /**
       * Justify Self
       * @see https://tailwindcss.com/docs/justify-self
       */
      "justify-self": [{
        "justify-self": ["auto", "start", "end", "center", "stretch"]
      }],
      /**
       * Align Content
       * @see https://tailwindcss.com/docs/align-content
       */
      "align-content": [{
        content: [].concat(getAlign(), ["baseline"])
      }],
      /**
       * Align Items
       * @see https://tailwindcss.com/docs/align-items
       */
      "align-items": [{
        items: ["start", "end", "center", "baseline", "stretch"]
      }],
      /**
       * Align Self
       * @see https://tailwindcss.com/docs/align-self
       */
      "align-self": [{
        self: ["auto", "start", "end", "center", "stretch", "baseline"]
      }],
      /**
       * Place Content
       * @see https://tailwindcss.com/docs/place-content
       */
      "place-content": [{
        "place-content": [].concat(getAlign(), ["baseline", "stretch"])
      }],
      /**
       * Place Items
       * @see https://tailwindcss.com/docs/place-items
       */
      "place-items": [{
        "place-items": ["start", "end", "center", "baseline", "stretch"]
      }],
      /**
       * Place Self
       * @see https://tailwindcss.com/docs/place-self
       */
      "place-self": [{
        "place-self": ["auto", "start", "end", "center", "stretch"]
      }],
      // Spacing
      /**
       * Padding
       * @see https://tailwindcss.com/docs/padding
       */
      p: [{
        p: [padding]
      }],
      /**
       * Padding X
       * @see https://tailwindcss.com/docs/padding
       */
      px: [{
        px: [padding]
      }],
      /**
       * Padding Y
       * @see https://tailwindcss.com/docs/padding
       */
      py: [{
        py: [padding]
      }],
      /**
       * Padding Top
       * @see https://tailwindcss.com/docs/padding
       */
      pt: [{
        pt: [padding]
      }],
      /**
       * Padding Right
       * @see https://tailwindcss.com/docs/padding
       */
      pr: [{
        pr: [padding]
      }],
      /**
       * Padding Bottom
       * @see https://tailwindcss.com/docs/padding
       */
      pb: [{
        pb: [padding]
      }],
      /**
       * Padding Left
       * @see https://tailwindcss.com/docs/padding
       */
      pl: [{
        pl: [padding]
      }],
      /**
       * Margin
       * @see https://tailwindcss.com/docs/margin
       */
      m: [{
        m: [margin]
      }],
      /**
       * Margin X
       * @see https://tailwindcss.com/docs/margin
       */
      mx: [{
        mx: [margin]
      }],
      /**
       * Margin Y
       * @see https://tailwindcss.com/docs/margin
       */
      my: [{
        my: [margin]
      }],
      /**
       * Margin Top
       * @see https://tailwindcss.com/docs/margin
       */
      mt: [{
        mt: [margin]
      }],
      /**
       * Margin Right
       * @see https://tailwindcss.com/docs/margin
       */
      mr: [{
        mr: [margin]
      }],
      /**
       * Margin Bottom
       * @see https://tailwindcss.com/docs/margin
       */
      mb: [{
        mb: [margin]
      }],
      /**
       * Margin Left
       * @see https://tailwindcss.com/docs/margin
       */
      ml: [{
        ml: [margin]
      }],
      /**
       * Space Between X
       * @see https://tailwindcss.com/docs/space
       */
      "space-x": [{
        "space-x": [space]
      }],
      /**
       * Space Between X Reverse
       * @see https://tailwindcss.com/docs/space
       */
      "space-x-reverse": ["space-x-reverse"],
      /**
       * Space Between Y
       * @see https://tailwindcss.com/docs/space
       */
      "space-y": [{
        "space-y": [space]
      }],
      /**
       * Space Between Y Reverse
       * @see https://tailwindcss.com/docs/space
       */
      "space-y-reverse": ["space-y-reverse"],
      // Sizing
      /**
       * Width
       * @see https://tailwindcss.com/docs/width
       */
      w: [{
        w: ["auto", "min", "max", "fit", spacing]
      }],
      /**
       * Min-Width
       * @see https://tailwindcss.com/docs/min-width
       */
      "min-w": [{
        "min-w": ["min", "max", "fit", isLength]
      }],
      /**
       * Max-Width
       * @see https://tailwindcss.com/docs/max-width
       */
      "max-w": [{
        "max-w": ["0", "none", "full", "min", "max", "fit", "prose", {
          screen: [isTshirtSize]
        }, isTshirtSize, isArbitraryLength]
      }],
      /**
       * Height
       * @see https://tailwindcss.com/docs/height
       */
      h: [{
        h: [spacing, "auto", "min", "max", "fit"]
      }],
      /**
       * Min-Height
       * @see https://tailwindcss.com/docs/min-height
       */
      "min-h": [{
        "min-h": ["min", "max", "fit", isLength]
      }],
      /**
       * Max-Height
       * @see https://tailwindcss.com/docs/max-height
       */
      "max-h": [{
        "max-h": [spacing, "min", "max", "fit"]
      }],
      // Typography
      /**
       * Font Size
       * @see https://tailwindcss.com/docs/font-size
       */
      "font-size": [{
        text: ["base", isTshirtSize, isArbitraryLength]
      }],
      /**
       * Font Smoothing
       * @see https://tailwindcss.com/docs/font-smoothing
       */
      "font-smoothing": ["antialiased", "subpixel-antialiased"],
      /**
       * Font Style
       * @see https://tailwindcss.com/docs/font-style
       */
      "font-style": ["italic", "not-italic"],
      /**
       * Font Weight
       * @see https://tailwindcss.com/docs/font-weight
       */
      "font-weight": [{
        font: ["thin", "extralight", "light", "normal", "medium", "semibold", "bold", "extrabold", "black", isArbitraryNumber]
      }],
      /**
       * Font Family
       * @see https://tailwindcss.com/docs/font-family
       */
      "font-family": [{
        font: [isAny]
      }],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-normal": ["normal-nums"],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-ordinal": ["ordinal"],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-slashed-zero": ["slashed-zero"],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-figure": ["lining-nums", "oldstyle-nums"],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-spacing": ["proportional-nums", "tabular-nums"],
      /**
       * Font Variant Numeric
       * @see https://tailwindcss.com/docs/font-variant-numeric
       */
      "fvn-fraction": ["diagonal-fractions", "stacked-fractons"],
      /**
       * Letter Spacing
       * @see https://tailwindcss.com/docs/letter-spacing
       */
      tracking: [{
        tracking: ["tighter", "tight", "normal", "wide", "wider", "widest", isArbitraryLength]
      }],
      /**
       * Line Height
       * @see https://tailwindcss.com/docs/line-height
       */
      leading: [{
        leading: ["none", "tight", "snug", "normal", "relaxed", "loose", isLength]
      }],
      /**
       * List Style Type
       * @see https://tailwindcss.com/docs/list-style-type
       */
      "list-style-type": [{
        list: ["none", "disc", "decimal", isArbitraryValue]
      }],
      /**
       * List Style Position
       * @see https://tailwindcss.com/docs/list-style-position
       */
      "list-style-position": [{
        list: ["inside", "outside"]
      }],
      /**
       * Placeholder Color
       * @deprecated since Tailwind CSS v3.0.0
       * @see https://tailwindcss.com/docs/placeholder-color
       */
      "placeholder-color": [{
        placeholder: [colors]
      }],
      /**
       * Placeholder Opacity
       * @see https://tailwindcss.com/docs/placeholder-opacity
       */
      "placeholder-opacity": [{
        "placeholder-opacity": [opacity]
      }],
      /**
       * Text Alignment
       * @see https://tailwindcss.com/docs/text-align
       */
      "text-alignment": [{
        text: ["left", "center", "right", "justify", "start", "end"]
      }],
      /**
       * Text Color
       * @see https://tailwindcss.com/docs/text-color
       */
      "text-color": [{
        text: [colors]
      }],
      /**
       * Text Opacity
       * @see https://tailwindcss.com/docs/text-opacity
       */
      "text-opacity": [{
        "text-opacity": [opacity]
      }],
      /**
       * Text Decoration
       * @see https://tailwindcss.com/docs/text-decoration
       */
      "text-decoration": ["underline", "overline", "line-through", "no-underline"],
      /**
       * Text Decoration Style
       * @see https://tailwindcss.com/docs/text-decoration-style
       */
      "text-decoration-style": [{
        decoration: [].concat(getLineStyles(), ["wavy"])
      }],
      /**
       * Text Decoration Thickness
       * @see https://tailwindcss.com/docs/text-decoration-thickness
       */
      "text-decoration-thickness": [{
        decoration: ["auto", "from-font", isLength]
      }],
      /**
       * Text Underline Offset
       * @see https://tailwindcss.com/docs/text-underline-offset
       */
      "underline-offset": [{
        "underline-offset": ["auto", isLength]
      }],
      /**
       * Text Decoration Color
       * @see https://tailwindcss.com/docs/text-decoration-color
       */
      "text-decoration-color": [{
        decoration: [colors]
      }],
      /**
       * Text Transform
       * @see https://tailwindcss.com/docs/text-transform
       */
      "text-transform": ["uppercase", "lowercase", "capitalize", "normal-case"],
      /**
       * Text Overflow
       * @see https://tailwindcss.com/docs/text-overflow
       */
      "text-overflow": ["truncate", "text-ellipsis", "text-clip"],
      /**
       * Text Indent
       * @see https://tailwindcss.com/docs/text-indent
       */
      indent: [{
        indent: [spacing]
      }],
      /**
       * Vertical Alignment
       * @see https://tailwindcss.com/docs/vertical-align
       */
      "vertical-align": [{
        align: ["baseline", "top", "middle", "bottom", "text-top", "text-bottom", "sub", "super", isArbitraryLength]
      }],
      /**
       * Whitespace
       * @see https://tailwindcss.com/docs/whitespace
       */
      whitespace: [{
        whitespace: ["normal", "nowrap", "pre", "pre-line", "pre-wrap"]
      }],
      /**
       * Word Break
       * @see https://tailwindcss.com/docs/word-break
       */
      "break": [{
        "break": ["normal", "words", "all", "keep"]
      }],
      /**
       * Content
       * @see https://tailwindcss.com/docs/content
       */
      content: [{
        content: ["none", isArbitraryValue]
      }],
      // Backgrounds
      /**
       * Background Attachment
       * @see https://tailwindcss.com/docs/background-attachment
       */
      "bg-attachment": [{
        bg: ["fixed", "local", "scroll"]
      }],
      /**
       * Background Clip
       * @see https://tailwindcss.com/docs/background-clip
       */
      "bg-clip": [{
        "bg-clip": ["border", "padding", "content", "text"]
      }],
      /**
       * Background Opacity
       * @deprecated since Tailwind CSS v3.0.0
       * @see https://tailwindcss.com/docs/background-opacity
       */
      "bg-opacity": [{
        "bg-opacity": [opacity]
      }],
      /**
       * Background Origin
       * @see https://tailwindcss.com/docs/background-origin
       */
      "bg-origin": [{
        "bg-origin": ["border", "padding", "content"]
      }],
      /**
       * Background Position
       * @see https://tailwindcss.com/docs/background-position
       */
      "bg-position": [{
        bg: [].concat(getPositions(), [isArbitraryPosition])
      }],
      /**
       * Background Repeat
       * @see https://tailwindcss.com/docs/background-repeat
       */
      "bg-repeat": [{
        bg: ["no-repeat", {
          repeat: ["", "x", "y", "round", "space"]
        }]
      }],
      /**
       * Background Size
       * @see https://tailwindcss.com/docs/background-size
       */
      "bg-size": [{
        bg: ["auto", "cover", "contain", isArbitrarySize]
      }],
      /**
       * Background Image
       * @see https://tailwindcss.com/docs/background-image
       */
      "bg-image": [{
        bg: ["none", {
          "gradient-to": ["t", "tr", "r", "br", "b", "bl", "l", "tl"]
        }, isArbitraryUrl]
      }],
      /**
       * Background Color
       * @see https://tailwindcss.com/docs/background-color
       */
      "bg-color": [{
        bg: [colors]
      }],
      /**
       * Gradient Color Stops From
       * @see https://tailwindcss.com/docs/gradient-color-stops
       */
      "gradient-from": [{
        from: [gradientColorStops]
      }],
      /**
       * Gradient Color Stops Via
       * @see https://tailwindcss.com/docs/gradient-color-stops
       */
      "gradient-via": [{
        via: [gradientColorStops]
      }],
      /**
       * Gradient Color Stops To
       * @see https://tailwindcss.com/docs/gradient-color-stops
       */
      "gradient-to": [{
        to: [gradientColorStops]
      }],
      // Borders
      /**
       * Border Radius
       * @see https://tailwindcss.com/docs/border-radius
       */
      rounded: [{
        rounded: [borderRadius]
      }],
      /**
       * Border Radius Top
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-t": [{
        "rounded-t": [borderRadius]
      }],
      /**
       * Border Radius Right
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-r": [{
        "rounded-r": [borderRadius]
      }],
      /**
       * Border Radius Bottom
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-b": [{
        "rounded-b": [borderRadius]
      }],
      /**
       * Border Radius Left
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-l": [{
        "rounded-l": [borderRadius]
      }],
      /**
       * Border Radius Top Left
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-tl": [{
        "rounded-tl": [borderRadius]
      }],
      /**
       * Border Radius Top Right
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-tr": [{
        "rounded-tr": [borderRadius]
      }],
      /**
       * Border Radius Bottom Right
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-br": [{
        "rounded-br": [borderRadius]
      }],
      /**
       * Border Radius Bottom Left
       * @see https://tailwindcss.com/docs/border-radius
       */
      "rounded-bl": [{
        "rounded-bl": [borderRadius]
      }],
      /**
       * Border Width
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w": [{
        border: [borderWidth]
      }],
      /**
       * Border Width X
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-x": [{
        "border-x": [borderWidth]
      }],
      /**
       * Border Width Y
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-y": [{
        "border-y": [borderWidth]
      }],
      /**
       * Border Width Top
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-t": [{
        "border-t": [borderWidth]
      }],
      /**
       * Border Width Right
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-r": [{
        "border-r": [borderWidth]
      }],
      /**
       * Border Width Bottom
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-b": [{
        "border-b": [borderWidth]
      }],
      /**
       * Border Width Left
       * @see https://tailwindcss.com/docs/border-width
       */
      "border-w-l": [{
        "border-l": [borderWidth]
      }],
      /**
       * Border Opacity
       * @see https://tailwindcss.com/docs/border-opacity
       */
      "border-opacity": [{
        "border-opacity": [opacity]
      }],
      /**
       * Border Style
       * @see https://tailwindcss.com/docs/border-style
       */
      "border-style": [{
        border: [].concat(getLineStyles(), ["hidden"])
      }],
      /**
       * Divide Width X
       * @see https://tailwindcss.com/docs/divide-width
       */
      "divide-x": [{
        "divide-x": [borderWidth]
      }],
      /**
       * Divide Width X Reverse
       * @see https://tailwindcss.com/docs/divide-width
       */
      "divide-x-reverse": ["divide-x-reverse"],
      /**
       * Divide Width Y
       * @see https://tailwindcss.com/docs/divide-width
       */
      "divide-y": [{
        "divide-y": [borderWidth]
      }],
      /**
       * Divide Width Y Reverse
       * @see https://tailwindcss.com/docs/divide-width
       */
      "divide-y-reverse": ["divide-y-reverse"],
      /**
       * Divide Opacity
       * @see https://tailwindcss.com/docs/divide-opacity
       */
      "divide-opacity": [{
        "divide-opacity": [opacity]
      }],
      /**
       * Divide Style
       * @see https://tailwindcss.com/docs/divide-style
       */
      "divide-style": [{
        divide: getLineStyles()
      }],
      /**
       * Border Color
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color": [{
        border: [borderColor]
      }],
      /**
       * Border Color X
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-x": [{
        "border-x": [borderColor]
      }],
      /**
       * Border Color Y
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-y": [{
        "border-y": [borderColor]
      }],
      /**
       * Border Color Top
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-t": [{
        "border-t": [borderColor]
      }],
      /**
       * Border Color Right
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-r": [{
        "border-r": [borderColor]
      }],
      /**
       * Border Color Bottom
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-b": [{
        "border-b": [borderColor]
      }],
      /**
       * Border Color Left
       * @see https://tailwindcss.com/docs/border-color
       */
      "border-color-l": [{
        "border-l": [borderColor]
      }],
      /**
       * Divide Color
       * @see https://tailwindcss.com/docs/divide-color
       */
      "divide-color": [{
        divide: [borderColor]
      }],
      /**
       * Outline Style
       * @see https://tailwindcss.com/docs/outline-style
       */
      "outline-style": [{
        outline: [""].concat(getLineStyles())
      }],
      /**
       * Outline Offset
       * @see https://tailwindcss.com/docs/outline-offset
       */
      "outline-offset": [{
        "outline-offset": [isLength]
      }],
      /**
       * Outline Width
       * @see https://tailwindcss.com/docs/outline-width
       */
      "outline-w": [{
        outline: [isLength]
      }],
      /**
       * Outline Color
       * @see https://tailwindcss.com/docs/outline-color
       */
      "outline-color": [{
        outline: [colors]
      }],
      /**
       * Ring Width
       * @see https://tailwindcss.com/docs/ring-width
       */
      "ring-w": [{
        ring: getLengthWithEmpty()
      }],
      /**
       * Ring Width Inset
       * @see https://tailwindcss.com/docs/ring-width
       */
      "ring-w-inset": ["ring-inset"],
      /**
       * Ring Color
       * @see https://tailwindcss.com/docs/ring-color
       */
      "ring-color": [{
        ring: [colors]
      }],
      /**
       * Ring Opacity
       * @see https://tailwindcss.com/docs/ring-opacity
       */
      "ring-opacity": [{
        "ring-opacity": [opacity]
      }],
      /**
       * Ring Offset Width
       * @see https://tailwindcss.com/docs/ring-offset-width
       */
      "ring-offset-w": [{
        "ring-offset": [isLength]
      }],
      /**
       * Ring Offset Color
       * @see https://tailwindcss.com/docs/ring-offset-color
       */
      "ring-offset-color": [{
        "ring-offset": [colors]
      }],
      // Effects
      /**
       * Box Shadow
       * @see https://tailwindcss.com/docs/box-shadow
       */
      shadow: [{
        shadow: ["", "inner", "none", isTshirtSize, isArbitraryShadow]
      }],
      /**
       * Box Shadow Color
       * @see https://tailwindcss.com/docs/box-shadow-color
       */
      "shadow-color": [{
        shadow: [isAny]
      }],
      /**
       * Opacity
       * @see https://tailwindcss.com/docs/opacity
       */
      opacity: [{
        opacity: [opacity]
      }],
      /**
       * Mix Beldn Mode
       * @see https://tailwindcss.com/docs/mix-blend-mode
       */
      "mix-blend": [{
        "mix-blend": getBlendModes()
      }],
      /**
       * Background Blend Mode
       * @see https://tailwindcss.com/docs/background-blend-mode
       */
      "bg-blend": [{
        "bg-blend": getBlendModes()
      }],
      // Filters
      /**
       * Filter
       * @deprecated since Tailwind CSS v3.0.0
       * @see https://tailwindcss.com/docs/filter
       */
      filter: [{
        filter: ["", "none"]
      }],
      /**
       * Blur
       * @see https://tailwindcss.com/docs/blur
       */
      blur: [{
        blur: [blur]
      }],
      /**
       * Brightness
       * @see https://tailwindcss.com/docs/brightness
       */
      brightness: [{
        brightness: [brightness]
      }],
      /**
       * Contrast
       * @see https://tailwindcss.com/docs/contrast
       */
      contrast: [{
        contrast: [contrast]
      }],
      /**
       * Drop Shadow
       * @see https://tailwindcss.com/docs/drop-shadow
       */
      "drop-shadow": [{
        "drop-shadow": ["", "none", isTshirtSize, isArbitraryValue]
      }],
      /**
       * Grayscale
       * @see https://tailwindcss.com/docs/grayscale
       */
      grayscale: [{
        grayscale: [grayscale]
      }],
      /**
       * Hue Rotate
       * @see https://tailwindcss.com/docs/hue-rotate
       */
      "hue-rotate": [{
        "hue-rotate": [hueRotate]
      }],
      /**
       * Invert
       * @see https://tailwindcss.com/docs/invert
       */
      invert: [{
        invert: [invert]
      }],
      /**
       * Saturate
       * @see https://tailwindcss.com/docs/saturate
       */
      saturate: [{
        saturate: [saturate]
      }],
      /**
       * Sepia
       * @see https://tailwindcss.com/docs/sepia
       */
      sepia: [{
        sepia: [sepia]
      }],
      /**
       * Backdrop Filter
       * @deprecated since Tailwind CSS v3.0.0
       * @see https://tailwindcss.com/docs/backdrop-filter
       */
      "backdrop-filter": [{
        "backdrop-filter": ["", "none"]
      }],
      /**
       * Backdrop Blur
       * @see https://tailwindcss.com/docs/backdrop-blur
       */
      "backdrop-blur": [{
        "backdrop-blur": [blur]
      }],
      /**
       * Backdrop Brightness
       * @see https://tailwindcss.com/docs/backdrop-brightness
       */
      "backdrop-brightness": [{
        "backdrop-brightness": [brightness]
      }],
      /**
       * Backdrop Contrast
       * @see https://tailwindcss.com/docs/backdrop-contrast
       */
      "backdrop-contrast": [{
        "backdrop-contrast": [contrast]
      }],
      /**
       * Backdrop Grayscale
       * @see https://tailwindcss.com/docs/backdrop-grayscale
       */
      "backdrop-grayscale": [{
        "backdrop-grayscale": [grayscale]
      }],
      /**
       * Backdrop Hue Rotate
       * @see https://tailwindcss.com/docs/backdrop-hue-rotate
       */
      "backdrop-hue-rotate": [{
        "backdrop-hue-rotate": [hueRotate]
      }],
      /**
       * Backdrop Invert
       * @see https://tailwindcss.com/docs/backdrop-invert
       */
      "backdrop-invert": [{
        "backdrop-invert": [invert]
      }],
      /**
       * Backdrop Opacity
       * @see https://tailwindcss.com/docs/backdrop-opacity
       */
      "backdrop-opacity": [{
        "backdrop-opacity": [opacity]
      }],
      /**
       * Backdrop Saturate
       * @see https://tailwindcss.com/docs/backdrop-saturate
       */
      "backdrop-saturate": [{
        "backdrop-saturate": [saturate]
      }],
      /**
       * Backdrop Sepia
       * @see https://tailwindcss.com/docs/backdrop-sepia
       */
      "backdrop-sepia": [{
        "backdrop-sepia": [sepia]
      }],
      // Tables
      /**
       * Border Collapse
       * @see https://tailwindcss.com/docs/border-collapse
       */
      "border-collapse": [{
        border: ["collapse", "separate"]
      }],
      /**
       * Border Spacing
       * @see https://tailwindcss.com/docs/border-spacing
       */
      "border-spacing": [{
        "border-spacing": [borderSpacing]
      }],
      /**
       * Border Spacing X
       * @see https://tailwindcss.com/docs/border-spacing
       */
      "border-spacing-x": [{
        "border-spacing-x": [borderSpacing]
      }],
      /**
       * Border Spacing Y
       * @see https://tailwindcss.com/docs/border-spacing
       */
      "border-spacing-y": [{
        "border-spacing-y": [borderSpacing]
      }],
      /**
       * Table Layout
       * @see https://tailwindcss.com/docs/table-layout
       */
      "table-layout": [{
        table: ["auto", "fixed"]
      }],
      // Transitions and Animation
      /**
       * Tranisition Property
       * @see https://tailwindcss.com/docs/transition-property
       */
      transition: [{
        transition: ["none", "all", "", "colors", "opacity", "shadow", "transform", isArbitraryValue]
      }],
      /**
       * Transition Duration
       * @see https://tailwindcss.com/docs/transition-duration
       */
      duration: [{
        duration: [isInteger]
      }],
      /**
       * Transition Timing Function
       * @see https://tailwindcss.com/docs/transition-timing-function
       */
      ease: [{
        ease: ["linear", "in", "out", "in-out", isArbitraryValue]
      }],
      /**
       * Transition Delay
       * @see https://tailwindcss.com/docs/transition-delay
       */
      delay: [{
        delay: [isInteger]
      }],
      /**
       * Animation
       * @see https://tailwindcss.com/docs/animation
       */
      animate: [{
        animate: ["none", "spin", "ping", "pulse", "bounce", isArbitraryValue]
      }],
      // Transforms
      /**
       * Transform
       * @see https://tailwindcss.com/docs/transform
       */
      transform: [{
        transform: ["", "gpu", "none"]
      }],
      /**
       * Scale
       * @see https://tailwindcss.com/docs/scale
       */
      scale: [{
        scale: [scale]
      }],
      /**
       * Scale X
       * @see https://tailwindcss.com/docs/scale
       */
      "scale-x": [{
        "scale-x": [scale]
      }],
      /**
       * Scale Y
       * @see https://tailwindcss.com/docs/scale
       */
      "scale-y": [{
        "scale-y": [scale]
      }],
      /**
       * Rotate
       * @see https://tailwindcss.com/docs/rotate
       */
      rotate: [{
        rotate: [isInteger, isArbitraryValue]
      }],
      /**
       * Translate X
       * @see https://tailwindcss.com/docs/translate
       */
      "translate-x": [{
        "translate-x": [translate]
      }],
      /**
       * Translate Y
       * @see https://tailwindcss.com/docs/translate
       */
      "translate-y": [{
        "translate-y": [translate]
      }],
      /**
       * Skew X
       * @see https://tailwindcss.com/docs/skew
       */
      "skew-x": [{
        "skew-x": [skew]
      }],
      /**
       * Skew Y
       * @see https://tailwindcss.com/docs/skew
       */
      "skew-y": [{
        "skew-y": [skew]
      }],
      /**
       * Transform Origin
       * @see https://tailwindcss.com/docs/transform-origin
       */
      "transform-origin": [{
        origin: ["center", "top", "top-right", "right", "bottom-right", "bottom", "bottom-left", "left", "top-left", isArbitraryValue]
      }],
      // Interactivity
      /**
       * Accent Color
       * @see https://tailwindcss.com/docs/accent-color
       */
      accent: [{
        accent: ["auto", colors]
      }],
      /**
       * Appearance
       * @see https://tailwindcss.com/docs/appearance
       */
      appearance: ["appearance-none"],
      /**
       * Cursor
       * @see https://tailwindcss.com/docs/cursor
       */
      cursor: [{
        cursor: ["auto", "default", "pointer", "wait", "text", "move", "help", "not-allowed", "none", "context-menu", "progress", "cell", "crosshair", "vertical-text", "alias", "copy", "no-drop", "grab", "grabbing", "all-scroll", "col-resize", "row-resize", "n-resize", "e-resize", "s-resize", "w-resize", "ne-resize", "nw-resize", "se-resize", "sw-resize", "ew-resize", "ns-resize", "nesw-resize", "nwse-resize", "zoom-in", "zoom-out", isArbitraryValue]
      }],
      /**
       * Caret Color
       * @see https://tailwindcss.com/docs/just-in-time-mode#caret-color-utilities
       */
      "caret-color": [{
        caret: [colors]
      }],
      /**
       * Pointer Events
       * @see https://tailwindcss.com/docs/pointer-events
       */
      "pointer-events": [{
        "pointer-events": ["none", "auto"]
      }],
      /**
       * Resize
       * @see https://tailwindcss.com/docs/resize
       */
      resize: [{
        resize: ["none", "y", "x", ""]
      }],
      /**
       * Scroll Behavior
       * @see https://tailwindcss.com/docs/scroll-behavior
       */
      "scroll-behavior": [{
        scroll: ["auto", "smooth"]
      }],
      /**
       * Scroll Margin
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-m": [{
        "scroll-m": [spacing]
      }],
      /**
       * Scroll Margin X
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-mx": [{
        "scroll-mx": [spacing]
      }],
      /**
       * Scroll Margin Y
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-my": [{
        "scroll-my": [spacing]
      }],
      /**
       * Scroll Margin Top
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-mt": [{
        "scroll-mt": [spacing]
      }],
      /**
       * Scroll Margin Right
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-mr": [{
        "scroll-mr": [spacing]
      }],
      /**
       * Scroll Margin Bottom
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-mb": [{
        "scroll-mb": [spacing]
      }],
      /**
       * Scroll Margin Left
       * @see https://tailwindcss.com/docs/scroll-margin
       */
      "scroll-ml": [{
        "scroll-ml": [spacing]
      }],
      /**
       * Scroll Padding
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-p": [{
        "scroll-p": [spacing]
      }],
      /**
       * Scroll Padding X
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-px": [{
        "scroll-px": [spacing]
      }],
      /**
       * Scroll Padding Y
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-py": [{
        "scroll-py": [spacing]
      }],
      /**
       * Scroll Padding Top
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-pt": [{
        "scroll-pt": [spacing]
      }],
      /**
       * Scroll Padding Right
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-pr": [{
        "scroll-pr": [spacing]
      }],
      /**
       * Scroll Padding Bottom
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-pb": [{
        "scroll-pb": [spacing]
      }],
      /**
       * Scroll Padding Left
       * @see https://tailwindcss.com/docs/scroll-padding
       */
      "scroll-pl": [{
        "scroll-pl": [spacing]
      }],
      /**
       * Scroll Snap Align
       * @see https://tailwindcss.com/docs/scroll-snap-align
       */
      "snap-align": [{
        snap: ["start", "end", "center", "align-none"]
      }],
      /**
       * Scroll Snap Stop
       * @see https://tailwindcss.com/docs/scroll-snap-stop
       */
      "snap-stop": [{
        snap: ["normal", "always"]
      }],
      /**
       * Scroll Snap Type
       * @see https://tailwindcss.com/docs/scroll-snap-type
       */
      "snap-type": [{
        snap: ["none", "x", "y", "both"]
      }],
      /**
       * Scroll Snap Type Strictness
       * @see https://tailwindcss.com/docs/scroll-snap-type
       */
      "snap-strictness": [{
        snap: ["mandatory", "proximity"]
      }],
      /**
       * Touch Action
       * @see https://tailwindcss.com/docs/touch-action
       */
      touch: [{
        touch: ["auto", "none", "pinch-zoom", "manipulation", {
          pan: ["x", "left", "right", "y", "up", "down"]
        }]
      }],
      /**
       * User Select
       * @see https://tailwindcss.com/docs/user-select
       */
      select: [{
        select: ["none", "text", "all", "auto"]
      }],
      /**
       * Will Change
       * @see https://tailwindcss.com/docs/will-change
       */
      "will-change": [{
        "will-change": ["auto", "scroll", "contents", "transform", isArbitraryValue]
      }],
      // SVG
      /**
       * Fill
       * @see https://tailwindcss.com/docs/fill
       */
      fill: [{
        fill: [colors, "none"]
      }],
      /**
       * Stroke Width
       * @see https://tailwindcss.com/docs/stroke-width
       */
      "stroke-w": [{
        stroke: [isLength, isArbitraryNumber]
      }],
      /**
       * Stroke
       * @see https://tailwindcss.com/docs/stroke
       */
      stroke: [{
        stroke: [colors, "none"]
      }],
      // Accessibility
      /**
       * Screen Readers
       * @see https://tailwindcss.com/docs/screen-readers
       */
      sr: ["sr-only", "not-sr-only"]
    },
    conflictingClassGroups: {
      overflow: ["overflow-x", "overflow-y"],
      overscroll: ["overscroll-x", "overscroll-y"],
      inset: ["inset-x", "inset-y", "top", "right", "bottom", "left"],
      "inset-x": ["right", "left"],
      "inset-y": ["top", "bottom"],
      flex: ["basis", "grow", "shrink"],
      "col-start-end": ["col-start", "col-end"],
      "row-start-end": ["row-start", "row-end"],
      gap: ["gap-x", "gap-y"],
      p: ["px", "py", "pt", "pr", "pb", "pl"],
      px: ["pr", "pl"],
      py: ["pt", "pb"],
      m: ["mx", "my", "mt", "mr", "mb", "ml"],
      mx: ["mr", "ml"],
      my: ["mt", "mb"],
      "font-size": ["leading"],
      "fvn-normal": ["fvn-ordinal", "fvn-slashed-zero", "fvn-figure", "fvn-spacing", "fvn-fraction"],
      "fvn-ordinal": ["fvn-normal"],
      "fvn-slashed-zero": ["fvn-normal"],
      "fvn-figure": ["fvn-normal"],
      "fvn-spacing": ["fvn-normal"],
      "fvn-fraction": ["fvn-normal"],
      rounded: ["rounded-t", "rounded-r", "rounded-b", "rounded-l", "rounded-tl", "rounded-tr", "rounded-br", "rounded-bl"],
      "rounded-t": ["rounded-tl", "rounded-tr"],
      "rounded-r": ["rounded-tr", "rounded-br"],
      "rounded-b": ["rounded-br", "rounded-bl"],
      "rounded-l": ["rounded-tl", "rounded-bl"],
      "border-spacing": ["border-spacing-x", "border-spacing-y"],
      "border-w": ["border-w-t", "border-w-r", "border-w-b", "border-w-l"],
      "border-w-x": ["border-w-r", "border-w-l"],
      "border-w-y": ["border-w-t", "border-w-b"],
      "border-color": ["border-color-t", "border-color-r", "border-color-b", "border-color-l"],
      "border-color-x": ["border-color-r", "border-color-l"],
      "border-color-y": ["border-color-t", "border-color-b"],
      "scroll-m": ["scroll-mx", "scroll-my", "scroll-mt", "scroll-mr", "scroll-mb", "scroll-ml"],
      "scroll-mx": ["scroll-mr", "scroll-ml"],
      "scroll-my": ["scroll-mt", "scroll-mb"],
      "scroll-p": ["scroll-px", "scroll-py", "scroll-pt", "scroll-pr", "scroll-pb", "scroll-pl"],
      "scroll-px": ["scroll-pr", "scroll-pl"],
      "scroll-py": ["scroll-pt", "scroll-pb"]
    }
  };
}
var twMerge = /* @__PURE__ */ createTailwindMerge(getDefaultConfig);
const index$1 = "";
const version = "3.10.0";
var $prefix = "Tiny";
var $props = {
  "tiny_mode": String,
  "tiny_mode_root": Boolean,
  "tiny_template": [Function, Object],
  "tiny_renderless": Function,
  "tiny_theme": String,
  "tiny_chart_theme": Object
};
var props = ["tiny_mode", "tiny_mode_root", "tiny_template", "tiny_renderless", "_constants", "tiny_theme", "tiny_chart_theme"];
var resolveMode = function resolveMode2(props2, context) {
  var isRightMode = function isRightMode2(mode) {
    return ~["pc", "mobile", "mobile-first"].indexOf(mode);
  };
  var config = rootConfig(context);
  var tinyModeProp = typeof props2.tiny_mode === "string" ? props2.tiny_mode : null;
  var tinyModeInject = hooks.inject("TinyMode", null);
  var tinyModeGlobal = config.tiny_mode && config.tiny_mode.value;
  if (!isRightMode(tinyModeProp))
    tinyModeProp = null;
  if (!isRightMode(tinyModeInject))
    tinyModeInject = null;
  if (!isRightMode(tinyModeGlobal))
    tinyModeGlobal = null;
  var tinyMode = tinyModeProp || tinyModeInject || tinyModeGlobal || "pc";
  if (props2.tiny_mode_root) {
    hooks.provide("TinyMode", tinyMode);
  }
  var instance = hooks.getCurrentInstance();
  Object.defineProperty(instance, "_tiny_mode", {
    value: tinyMode
  });
  return tinyMode;
};
var resolveTheme = function resolveTheme2(_ref) {
  var props2 = _ref.props, context = _ref.context, utils = _ref.utils;
  var isRightTheme = function isRightTheme2(theme) {
    return ~["tiny", "saas"].indexOf(theme);
  };
  var config = rootConfig(context);
  var tinyThemeProp = typeof props2.tiny_theme === "string" ? props2.tiny_theme : null;
  var tinyThemeInject = hooks.inject("TinyTheme", null);
  var tinyThemeGlobal = config.tiny_theme && config.tiny_theme.value;
  if (!isRightTheme(tinyThemeProp))
    tinyThemeProp = null;
  if (!isRightTheme(tinyThemeInject))
    tinyThemeInject = null;
  if (!isRightTheme(tinyThemeGlobal))
    tinyThemeGlobal = null;
  var tinyTheme = tinyThemeProp || tinyThemeInject || tinyThemeGlobal || "tiny";
  return utils.vm.theme = tinyTheme;
};
var resolveChartTheme = function resolveChartTheme2(_ref2) {
  var props2 = _ref2.props, context = _ref2.context, utils = _ref2.utils;
  var config = rootConfig(context);
  var tinyChartProp = _typeof(props2.tiny_chart_theme) === "object" ? props2.tiny_chart_theme : null;
  var tinyChartInject = hooks.inject("TinyChartTheme", null);
  var tinyChartGlobal = config.tiny_chart_theme && config.tiny_chart_theme.value;
  var tinyChartTheme = tinyChartProp || tinyChartInject || tinyChartGlobal || null;
  return utils.vm.chart_theme = tinyChartTheme;
};
var $setup = function $setup2(_ref3) {
  var props2 = _ref3.props, context = _ref3.context, template = _ref3.template, _ref3$extend = _ref3.extend, extend = _ref3$extend === void 0 ? {} : _ref3$extend;
  var view = hooks.computed(function() {
    if (typeof props2.tiny_template !== "undefined")
      return props2.tiny_template;
    var component = template(resolveMode(props2, context), props2);
    return typeof component === "function" ? defineAsyncComponent(component) : component;
  });
  initComponent();
  return renderComponent({
    view,
    props: props2,
    context,
    extend
  });
};
var mergeClass = function mergeClass2() {
  for (var _len = arguments.length, cssClasses = new Array(_len), _key = 0; _key < _len; _key++) {
    cssClasses[_key] = arguments[_key];
  }
  return twMerge(stringifyCssClass(cssClasses));
};
var design = {
  configKey: Symbol("designConfigKey"),
  configInstance: null
};
var provideDesignConfig = function provideDesignConfig2(designConfig) {
  if (Object.keys(designConfig).length) {
    hooks.provide(design.configKey, designConfig);
    design.configInstance = designConfig;
  }
};
var createComponent = createComponentFn(design);
var setup = function setup2(_ref4) {
  var _globalDesignConfig$c;
  var props2 = _ref4.props, context = _ref4.context, renderless = _ref4.renderless, api2 = _ref4.api, _ref4$extendOptions = _ref4.extendOptions, extendOptions = _ref4$extendOptions === void 0 ? {} : _ref4$extendOptions, _ref4$mono = _ref4.mono, mono = _ref4$mono === void 0 ? false : _ref4$mono, _ref4$classes = _ref4.classes, classes = _ref4$classes === void 0 ? {} : _ref4$classes;
  var render = typeof props2.tiny_renderless === "function" ? props2.tiny_renderless : renderless;
  var globalDesignConfig = hooks.inject(design.configKey, {});
  var designConfig = globalDesignConfig === null || globalDesignConfig === void 0 ? void 0 : (_globalDesignConfig$c = globalDesignConfig.components) === null || _globalDesignConfig$c === void 0 ? void 0 : _globalDesignConfig$c[getComponentName().replace($prefix, "")];
  var utils = _objectSpread2(_objectSpread2({
    $prefix,
    t
  }, tools(context, resolveMode(props2, context))), {}, {
    mergeClass,
    designConfig,
    globalDesignConfig
  });
  resolveTheme({
    props: props2,
    context,
    utils
  });
  resolveChartTheme({
    props: props2,
    context,
    utils
  });
  var sdk = render(props2, hooks, utils, extendOptions);
  if (typeof (designConfig === null || designConfig === void 0 ? void 0 : designConfig.renderless) === "function") {
    Object.assign(sdk, designConfig.renderless(props2, hooks, utils, sdk));
  }
  var attrs = {
    t,
    vm: utils.vm,
    f: bindFilter,
    a: filterAttrs,
    d: utils.defineInstanceProperties,
    dp: utils.defineParentInstanceProperties,
    gcls: function gcls(key) {
      return getElementCssClass(classes, key);
    },
    m: mergeClass
  };
  attrs.d({
    slots: {
      get: function get() {
        return utils.vm.$slots;
      }
    },
    scopedSlots: {
      get: function get() {
        return utils.vm.$scopedSlots;
      }
    }
  });
  attrs.dp({
    slots: {
      get: function get() {
        return utils.parent.$slots;
      }
    },
    scopedSlots: {
      get: function get() {
        return utils.parent.$scopedSlots;
      }
    }
  });
  initComponent();
  Array.isArray(api2) && api2.forEach(function(name) {
    var value = sdk[name];
    if (typeof value !== "undefined") {
      attrs[name] = value;
      if (!mono) {
        utils.setParentAttribute({
          name,
          value
        });
      }
    }
  });
  return attrs;
};
var svg = function svg2(_ref5) {
  var _ref5$name = _ref5.name, name = _ref5$name === void 0 ? "Icon" : _ref5$name, component = _ref5.component;
  return function(propData) {
    return markRaw(defineComponent({
      name: $prefix + name,
      setup: function setup3(props2, context) {
        var _ref6 = context.attrs || {}, fill = _ref6.fill, width = _ref6.width, height = _ref6.height, customClass = _ref6["custom-class"];
        var mergeProps = Object.assign({}, props2, propData || null);
        var mode = resolveMode(mergeProps, context);
        var isMobileFirst = mode === "mobile-first";
        var tinyTag = {
          "data-tag": isMobileFirst ? "tiny-svg" : null
        };
        var attrs = tinyTag;
        var className = isMobileFirst ? mergeClass("h-4 w-4 inline-block", customClass || "", mergeProps.class || "") : "tiny-svg";
        var extend = Object.assign({
          style: {
            fill,
            width,
            height
          },
          class: className,
          isSvg: true
        }, attrs);
        {
          extend.nativeOn = context.listeners;
        }
        return renderComponent({
          component,
          props: mergeProps,
          context,
          extend
        });
      }
    }));
  };
};
var filterAttrs = function filterAttrs2(attrs, filters, include) {
  var props2 = {};
  var _loop = function _loop2(name2) {
    var find = filters.some(function(r) {
      return new RegExp(r).test(name2);
    });
    if (include && find || !include && !find) {
      props2[name2] = attrs[name2];
    }
  };
  for (var name in attrs) {
    _loop(name);
  }
  return props2;
};
var setupComponent = {};
var initComponent = function initComponent2() {
  for (var name in setupComponent) {
    var component = setupComponent[name];
    if (typeof component.install === "function") {
      component.install(appContext());
    }
    if (typeof component.init === "function") {
      component.init(appProperties());
    }
  }
  setupComponent = {};
};
var $install = function $install2(component) {
  component.install = function(Vue) {
    Vue.component(component.name, component);
  };
};
const index = {
  h,
  directive,
  parseVnode,
  useRouter,
  emitter,
  createComponent,
  defineAsyncComponent,
  filterAttrs,
  initComponent,
  setupComponent,
  svg,
  $prefix,
  $props,
  props,
  $setup,
  setup,
  hooks,
  getElementStatusClass,
  $install
};
export {
  $install,
  $prefix,
  $props,
  $setup,
  Teleport,
  appProperties,
  createComponent,
  index as default,
  defineAsyncComponent,
  defineComponent,
  design,
  directive,
  emitter,
  filterAttrs,
  getElementStatusClass,
  h,
  hooks,
  initComponent,
  isVue2,
  isVue3,
  mergeClass,
  parseVnode,
  props,
  provideDesignConfig,
  resolveMode,
  setup,
  setupComponent,
  svg,
  useRouter,
  version
};
