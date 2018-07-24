// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 38);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _global = __webpack_require__(1);

var weiui = weex.requireModule('weiui');

var app = {
    openViewCode: function openViewCode(str) {
        weiui.openPage({
            url: "http://kuaifan.vip/weiui/#/" + str,
            pageType: 'web'
        });
    },
    checkVersion: function checkVersion(compareVersion) {
        if (typeof weiui.getVersion !== "function") {
            return false;
        }
        return (0, _global.runNum)(weiui.getVersion()) >= (0, _global.runNum)(compareVersion);
    }
};

module.exports = app;

/***/ }),

/***/ 1:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; };

function _objectWithoutProperties(obj, keys) { var target = {}; for (var i in obj) { if (keys.indexOf(i) >= 0) continue; if (!Object.prototype.hasOwnProperty.call(obj, i)) continue; target[i] = obj[i]; } return target; }

function _toArray(arr) { return Array.isArray(arr) ? arr : Array.from(arr); }

var global = {
    isFunction: function isFunction(value) {
        return typeof value === "function";
    },
    isObject: function isObject(obj) {
        return obj === null ? false : (typeof obj === "undefined" ? "undefined" : _typeof(obj)) === "object";
    },
    likeArray: function likeArray(obj) {
        return typeof obj.length === 'number';
    },
    getObject: function getObject(obj, keys) {
        var object = obj;
        if (global.count(obj) > 0 && global.count(keys) > 0) {
            var arr = keys.replace(/,/g, "|").replace(/\./g, "|").split("|");
            global.each(arr, function (index, key) {
                if (typeof object[key] !== "undefined") {
                    object = object[key];
                }
            });
        }
        return object;
    },


    /**
     * 遍历数组、对象
     * @param elements
     * @param callback
     * @returns {*}
     */
    each: function each(elements, callback) {
        var i = void 0,
            key = void 0;
        if (global.likeArray(elements)) {
            if (typeof elements.length === "number") {
                for (i = 0; i < elements.length; i++) {
                    if (callback.call(elements[i], i, elements[i]) === false) return elements;
                }
            }
        } else {
            for (key in elements) {
                if (!elements.hasOwnProperty(key)) continue;
                if (callback.call(elements[key], key, elements[key]) === false) return elements;
            }
        }

        return elements;
    },


    /**
     * 获取数组最后一个值
     * @param array
     * @returns {*}
     */
    last: function last(array) {
        var str = false;
        if ((typeof array === "undefined" ? "undefined" : _typeof(array)) === 'object' && array.length > 0) {
            str = array[array.length - 1];
        }
        return str;
    },


    /**
     * 删除数组最后一个值
     * @param array
     * @returns {Array}
     */
    delLast: function delLast(array) {
        var newArray = [];
        if ((typeof array === "undefined" ? "undefined" : _typeof(array)) === 'object' && array.length > 0) {
            global.each(array, function (index, item) {
                if (index < array.length - 1) {
                    newArray.push(item);
                }
            });
        }
        return newArray;
    },


    /**
     * 字符串是否包含
     * @param string
     * @param find
     * @returns {boolean}
     */
    strExists: function strExists(string, find) {
        string += "";
        find += "";
        return string.indexOf(find) !== -1;
    },


    /**
     * 字符串是否左边包含
     * @param string
     * @param find
     * @returns {boolean}
     */
    leftExists: function leftExists(string, find) {
        string += "";
        find += "";
        return string.substring(0, find.length) === find;
    },


    /**
     * 字符串是否右边包含
     * @param string
     * @param find
     * @returns {boolean}
     */
    rightExists: function rightExists(string, find) {
        string += "";
        find += "";
        return string.substring(string.length - find.length) === find;
    },


    /**
     * 取字符串中间
     * @param string
     * @param start
     * @param end
     * @returns {*}
     */
    getMiddle: function getMiddle(string, start, end) {
        string += "";
        if (global.ishave(start) && global.strExists(string, start)) {
            string = string.substring(string.indexOf(start) + start.length);
        }
        if (global.ishave(end) && global.strExists(string, end)) {
            string = string.substring(0, string.indexOf(end));
        }
        return string;
    },


    /**
     * 截取字符串
     * @param string
     * @param start
     * @param end
     * @returns {string}
     */
    subString: function subString(string, start, end) {
        string += "";
        if (!global.ishave(end)) {
            end = string.length;
        }
        return string.substring(start, end);
    },


    /**
     * 随机字符
     * @param len
     * @returns {string}
     */
    randomString: function randomString(len) {
        len = len || 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678oOLl9gqVvUuI1';
        var maxPos = $chars.length;
        var pwd = '';
        for (var i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    },


    /**
     * 判断是否有
     * @param set
     * @returns {boolean}
     */
    ishave: function ishave(set) {
        return !!(set !== null && set !== "null" && set !== undefined && set !== "undefined" && set);
    },


    /**
     * 补零
     * @param str
     * @param length
     * @param after
     * @returns {*}
     */
    zeroFill: function zeroFill(str, length, after) {
        str += "";
        if (str.length >= length) {
            return str;
        }
        var _str = '',
            _ret = '';
        for (var i = 0; i < length; i++) {
            _str += '0';
        }
        if (after || typeof after === 'undefined') {
            _ret = (_str + "" + str).substr(length * -1);
        } else {
            _ret = (str + "" + _str).substr(0, length);
        }
        return _ret;
    },


    /**
     * 时间戳转时间格式
     * @param format
     * @param v
     * @returns {string}
     */
    formatDate: function formatDate(format, v) {
        if (format === '') {
            format = 'Y-m-d H:i:s';
        }
        if (typeof v === 'undefined') {
            v = new Date().getTime();
        } else if (/^(-)?\d{1,10}$/.test(v)) {
            v = v * 1000;
        } else if (/^(-)?\d{1,13}$/.test(v)) {
            v = v * 1000;
        } else if (/^(-)?\d{1,14}$/.test(v)) {
            v = v * 100;
        } else if (/^(-)?\d{1,15}$/.test(v)) {
            v = v * 10;
        } else if (/^(-)?\d{1,16}$/.test(v)) {
            v = v * 1;
        } else {
            return v;
        }
        var dateObj = new Date(v);
        if (parseInt(dateObj.getFullYear()) + "" === "NaN") {
            return v;
        }
        //
        format = format.replace(/Y/g, dateObj.getFullYear());
        format = format.replace(/m/g, global.zeroFill(dateObj.getMonth() + 1, 2));
        format = format.replace(/d/g, global.zeroFill(dateObj.getDate(), 2));
        format = format.replace(/H/g, global.zeroFill(dateObj.getHours(), 2));
        format = format.replace(/i/g, global.zeroFill(dateObj.getMinutes(), 2));
        format = format.replace(/s/g, global.zeroFill(dateObj.getSeconds(), 2));
        return format;
    },


    /**
     * 检测手机号码格式
     * @param str
     * @returns {boolean}
     */
    isMobile: function isMobile(str) {
        return (/^1(3|4|5|7|8)\d{9}$/.test(str)
        );
    },


    /**
     * 手机号码中间换成****
     * @param phone
     * @returns {string}
     */
    formatMobile: function formatMobile(phone) {
        if (global.count(phone) === 0) {
            return "";
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length - 4);
    },


    /**
     * 设置网页标题
     * @param title
     */
    setTile: function setTile(title) {
        document.title = title;
        var mobile = navigator.userAgent.toLowerCase();
        if (/iphone|ipad|ipod/.test(mobile)) {
            var iframe = document.createElement('iframe');
            iframe.style.display = 'none';
            iframe.setAttribute('src', '/favicon.ico');
            var iframeCallback = function iframeCallback() {
                setTimeout(function () {
                    iframe.removeEventListener('load', iframeCallback);
                    document.body.removeChild(iframe);
                }, 0);
            };
            iframe.addEventListener('load', iframeCallback);
            document.body.appendChild(iframe);
        }
    },


    /**
     * 克隆对象
     * @param myObj
     * @returns {*}
     */
    clone: function clone(myObj) {
        if ((typeof myObj === "undefined" ? "undefined" : _typeof(myObj)) !== 'object') return myObj;
        if (myObj === null) return myObj;
        //
        if (global.likeArray(myObj)) {
            var _myObj = _toArray(myObj),
                myNewObj = _myObj.slice(0);

            return myNewObj;
        } else {
            var _myNewObj = _objectWithoutProperties(myObj, []);

            return _myNewObj;
        }
    },


    /**
     * 统计数组或对象长度
     * @param obj
     * @returns {number}
     */
    count: function count(obj) {
        try {
            if (typeof obj === "undefined") {
                return 0;
            }
            if (typeof obj === "number") {
                obj += "";
            }
            if (typeof obj.length === 'number') {
                return obj.length;
            } else {
                var i = 0,
                    key = void 0;
                for (key in obj) {
                    i++;
                }
                return i;
            }
        } catch (e) {
            return 0;
        }
    },


    /**
     * 相当于 intval
     * @param str
     * @param fixed
     * @returns {number}
     */
    runNum: function runNum(str, fixed) {
        var _s = Number(str);
        if (_s + "" === "NaN") {
            _s = 0;
        }
        if (/^[0-9]*[1-9][0-9]*$/.test(fixed)) {
            _s = _s.toFixed(fixed);
            var rs = _s.indexOf('.');
            if (rs < 0) {
                _s += ".";
                for (var i = 0; i < fixed; i++) {
                    _s += "0";
                }
            }
        }
        return _s;
    },


    /**
     * 秒转化为天小时分秒字符串
     * @param value
     * @returns {string}
     */
    formatSeconds: function formatSeconds(value) {
        var theTime = parseInt(value); // 秒
        var theTime1 = 0; // 分
        var theTime2 = 0; // 小时
        if (theTime > 60) {
            theTime1 = parseInt(theTime / 60);
            theTime = parseInt(theTime % 60);
            if (theTime1 > 60) {
                theTime2 = parseInt(theTime1 / 60);
                theTime1 = parseInt(theTime1 % 60);
            }
        }
        var result = parseInt(theTime) + "秒";
        if (theTime1 > 0) {
            result = parseInt(theTime1) + "分" + result;
        }
        if (theTime2 > 0) {
            result = parseInt(theTime2) + "小时" + result;
        }
        return result;
    },


    /**
     * 获取星座
     * @param m
     * @param d
     * @returns {string}
     */
    getAstro: function getAstro(m, d) {
        return "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯".substr(m * 2 - (d < "102223444433".charAt(m - 1) - -19) * 2, 2) + "座";
    }
};

module.exports = global;

/***/ }),

/***/ 38:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(39)
)

/* script */
__vue_exports__ = __webpack_require__(40)

/* template */
var __vue_template__ = __webpack_require__(41)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "/Users/GAOYI/wwwroot/weex_weiui/src/component_tabbar.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-2d98aefe"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof weex === "object" && weex && weex.document) {
  try {
    weex.document.registerStyleSheets(__vue_options__._scopeId, __vue_styles__)
  } catch (e) {}
}

module.exports = __vue_exports__
module.exports.el = 'true'
new Vue(module.exports)


/***/ }),

/***/ 39:
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "flex": 1
  },
  "iconr": {
    "width": "100",
    "color": "#ffffff"
  },
  "tabbar": {
    "width": "750",
    "flex": 1
  },
  "page-content": {
    "width": "750",
    "flex": 1,
    "justifyContent": "center",
    "alignItems": "center"
  },
  "page-navbar": {
    "width": "750",
    "height": "90"
  },
  "page-navbar-title": {
    "color": "#ffffff",
    "fontSize": "28"
  },
  "page-content-image": {
    "width": "480",
    "height": "480",
    "marginTop": "30",
    "marginBottom": "30"
  }
}

/***/ }),

/***/ 40:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});

var _app = __webpack_require__(0);

var weiui = weex.requireModule('weiui'); //
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

exports.default = {
    methods: {
        viewCode: function viewCode(str) {
            (0, _app.openViewCode)(str);
        },
        pageSelected: function pageSelected(params) {
            weiui.toast("切换到第" + (params.position + 1) + "个标签页");
        },
        tabReselect: function tabReselect(params) {
            weiui.toast("第" + (params.position + 1) + "个标签页被再次点击");
        },
        refreshListener: function refreshListener(params) {
            var _this = this;

            setTimeout(function () {
                weiui.toast("刷新成功：第" + (params.position + 1) + "个标签页");
                _this.$refs.reflectName.setRefreshing(params['tabName'], false);
            }, 1000);
        }
    }
};

/***/ }),

/***/ 41:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["app"]
  }, [_c('weiui_tabbar', {
    ref: "reflectName",
    staticClass: ["tabbar"],
    attrs: {
      "weiui": {
        tabType: 'bottom'
      }
    },
    on: {
      "pageSelected": _vm.pageSelected,
      "tabReselect": _vm.tabReselect,
      "refreshListener": _vm.refreshListener
    }
  }, [_c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_1',
        title: '首页',
        selectedIcon: 'home'
      }
    }
  }, [_c('weiui_navbar', {
    staticClass: ["page-navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["page-navbar-title"]
  }, [_vm._v("首页")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    },
    on: {
      "click": function($event) {
        _vm.viewCode('component/weiui_tabbar')
      }
    }
  }, [_c('weiui_icon', {
    staticClass: ["iconr"],
    attrs: {
      "content": "code-working"
    }
  })], 1)], 1), _c('div', {
    staticClass: ["page-content"]
  }, [_c('text', {
    staticStyle: {
      fontSize: "24px"
    }
  }, [_vm._v("页签里面可以放任何子组件，感谢你对weiui的支持")]), _c('image', {
    staticClass: ["page-content-image"],
    attrs: {
      "src": "http://demo.sc.chinaz.com/Files/pic/icons/6430/m2.png"
    }
  })])], 1), _c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_2',
        title: '好友',
        message: 3,
        selectedIcon: 'http://demo.sc.chinaz.com/Files/pic/icons/6749/g7.png',
        unSelectedIcon: 'http://demo.sc.chinaz.com/Files/pic/icons/6749/g4.png'
      }
    }
  }, [_c('weiui_navbar', {
    staticClass: ["page-navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["page-navbar-title"]
  }, [_vm._v("好友")])])], 1), _c('div', {
    staticClass: ["page-content"]
  }, [_c('text', {
    staticStyle: {
      fontSize: "24px"
    }
  }, [_vm._v("page 2，图标支持网络图片")])])], 1), _c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_3',
        title: '圈子',
        message: 99,
        selectedIcon: 'aperture 26sp'
      }
    }
  }, [_c('weiui_navbar', {
    staticClass: ["page-navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["page-navbar-title"]
  }, [_vm._v("圈子")])])], 1), _c('div', {
    staticClass: ["page-content"]
  }, [_c('text', {
    staticStyle: {
      fontSize: "24px"
    }
  }, [_vm._v("page 3")])])], 1), _c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_4',
        title: '设置',
        dot: true,
        selectedIcon: 'gear-a'
      }
    }
  }, [_c('weiui_navbar', {
    staticClass: ["page-navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["page-navbar-title"]
  }, [_vm._v("设置")])])], 1), _c('div', {
    staticClass: ["page-content"]
  }, [_c('text', {
    staticStyle: {
      fontSize: "24px"
    }
  }, [_vm._v("page 4")])])], 1)], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });