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
/******/ 	return __webpack_require__(__webpack_require__.s = 49);
/******/ })
/************************************************************************/
/******/ ({

/***/ 49:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(50)
)

/* script */
__vue_exports__ = __webpack_require__(51)

/* template */
var __vue_template__ = __webpack_require__(52)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-68835252"
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

/***/ 50:
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "flex": 1
  },
  "navbar": {
    "width": "750",
    "height": "100"
  },
  "navbar-title": {
    "fontSize": "32",
    "color": "#ffffff"
  },
  "navbar-icon": {
    "width": "100",
    "height": "100",
    "color": "#ffffff"
  },
  "version-box": {
    "width": "750",
    "height": "82",
    "flexDirection": "row",
    "backgroundColor": "#ff6666"
  },
  "version-text": {
    "flex": 1,
    "paddingLeft": "20",
    "fontSize": "26",
    "color": "#ffffff",
    "height": "82",
    "lineHeight": "82"
  },
  "version-button": {
    "color": "#ffffff",
    "fontSize": "22",
    "marginTop": "13",
    "marginRight": "20",
    "paddingLeft": "18",
    "paddingRight": "18",
    "height": "56",
    "lineHeight": "56",
    "borderWidth": "1",
    "borderColor": "#e4e4e4",
    "borderStyle": "solid"
  },
  "list": {
    "width": "750",
    "flex": 1
  },
  "list-title-box": {
    "flexDirection": "row",
    "alignItems": "center"
  },
  "list-title": {
    "paddingTop": "36",
    "paddingRight": "24",
    "paddingBottom": "24",
    "paddingLeft": "24",
    "fontSize": "28",
    "color": "#757575"
  },
  "list-subtitle": {
    "position": "absolute",
    "right": "24",
    "bottom": "24"
  },
  "list-item": {
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "space-between",
    "height": "100",
    "flex": 1,
    "paddingLeft": "20",
    "paddingRight": "20",
    "borderTopWidth": "1",
    "borderTopColor": "#e8e8e8",
    "borderTopStyle": "solid"
  },
  "list-item-left": {
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "flex-start",
    "height": "100",
    "flex": 1
  },
  "list-left-icon": {
    "width": "60",
    "color": "#3EB4FF"
  },
  "list-left-title": {
    "color": "#242424",
    "paddingLeft": "12",
    "width": "380",
    "fontSize": "26",
    "textOverflow": "ellipsis",
    "lines": 1
  },
  "list-left-title-history": {
    "color": "#242424",
    "paddingLeft": "12",
    "width": "600",
    "fontSize": "26",
    "textOverflow": "ellipsis",
    "lines": 1
  },
  "list-right-title": {
    "color": "#a2a2a2",
    "paddingRight": "3",
    "fontSize": "22",
    "textOverflow": "ellipsis",
    "lines": 1
  },
  "list-right-icon": {
    "width": "40",
    "color": "#C9C9CE"
  },
  "list-item-right": {
    "flexDirection": "row",
    "alignItems": "center",
    "justifyContent": "flex-end",
    "height": "100"
  }
}

/***/ }),

/***/ 51:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
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

var weiui = weex.requireModule('weiui');

exports.default = {
    data: function data() {
        return {
            components: [{
                title: '轮播控件',
                title_en: 'weiui_banner',
                icon: 'easel',
                url: 'component_banner.js'
            }, {
                title: '常用按钮',
                title_en: 'weiui_button',
                icon: 'android-checkbox-blank',
                url: 'component_button.js'
            }, {
                title: '网格容器',
                title_en: 'weiui_grid',
                icon: 'grid',
                url: 'component_grid.js'
            }, {
                title: '字体图标',
                title_en: 'weiui_icon',
                icon: 'ionic',
                url: 'component_icon.js'
            }, {
                title: '跑马文字',
                title_en: 'weiui_marquee',
                icon: 'code-working',
                url: 'component_marquee.js'
            }, {
                title: '导航栏',
                title_en: 'weiui_navbar',
                icon: 'navicon',
                url: 'component_navbar.js'
            }, {
                title: '列表容器',
                title_en: 'weiui_recyler',
                icon: 'ios-list 90%',
                url: 'component_recyler.js'
            }, {
                title: '滚动文字',
                title_en: 'weiui_scroll_text',
                icon: 'more',
                url: 'component_scroll_text.js'
            }, {
                title: '侧边栏',
                title_en: 'weiui_side_panel',
                icon: 'ios-box',
                url: 'component_side_panel.js'
            }, {
                title: '标签页面',
                title_en: 'weiui_tabbar',
                icon: 'filing',
                url: 'component_tabbar.js'
            }],

            module: [{
                title: '页面功能',
                title_en: 'newPage',
                icon: 'ios-book-outline 96%',
                url: 'module_page.js'
            }, {
                title: '系统信息',
                title_en: 'system',
                icon: 'gear-a',
                url: 'module_system.js'
            }, {
                title: '数据缓存',
                title_en: 'caches',
                icon: 'soup-can-outline',
                url: 'module_caches.js'
            }, {
                title: '单位转换',
                title_en: 'weex px',
                icon: 'ios-calculator',
                url: 'module_weexpx.js'
            }, {
                title: '确认对话框',
                title_en: 'alert',
                icon: 'android-alert 90%',
                url: 'module_alert.js'
            }, {
                title: '等待弹窗',
                title_en: 'loading',
                icon: 'load-d',
                url: 'module_loading.js'
            }, {
                title: '验证弹窗',
                title_en: 'captcha',
                icon: 'ios-checkmark-outline',
                url: 'module_captcha.js'
            }, {
                title: '二维码扫描',
                title_en: 'scaner',
                icon: 'tb-scan',
                url: 'module_scaner.js'
            }, {
                title: '跨域异步请求',
                title_en: 'ajax',
                icon: 'pull-request',
                url: 'module_ajax.js'
            }, {
                title: '剪切板',
                title_en: 'clipboard',
                icon: 'ios-copy-outline',
                url: 'module_plate.js'
            }, {
                title: '提示消息',
                title_en: 'toast',
                icon: 'ios-barcode-outline',
                url: 'module_toast.js'
            }, {
                title: '广告弹窗',
                title_en: 'adDialog',
                icon: 'social-buffer-outline',
                url: 'module_ad_dialog.js'
            }, {
                title: '更多拓展模块',
                title_en: 'expandModule',
                icon: 'more',
                url: 'index_expand.js'
            }],

            third_module: [{
                title: '城市选择器',
                title_en: 'citypicker',
                icon: 'android-pin',
                url: 'third_citypicker.js'
            }, {
                title: '图片选择器',
                title_en: 'pictureSelector',
                icon: 'ios-camera-outline',
                url: 'third_picture.js'
            }],

            about_lists: [{
                title: '开发文档',
                title_en: 'document',
                icon: 'code-working',
                url: 'http://kuaifan.vip/weiui'
            }, {
                title: '托管平台',
                title_en: 'github',
                icon: 'social-github-outline',
                url: 'https://github.com/kuaifan/weiui'
            }, {
                title: '个人博客',
                title_en: 'http://kuaifan.vip',
                icon: 'social-rss-outline',
                url: 'http://kuaifan.vip'
            }],

            history: [],

            newApp: false
        };
    },
    mounted: function mounted() {
        var _this = this;

        this.history = JSON.parse(weiui.getCachesString("scaner", []));
        //
        var variable = parseInt(weiui.getLocalVersion());
        if (variable < 12) {
            this.newApp = true;
            weiui.confirm({
                title: "版本更新",
                message: "你当前使用的版本比较低，部分功能可能无法正常显示，建议升级至最新版本！",
                buttons: [{
                    title: "稍后再说",
                    type: "negative"
                }, {
                    title: "立即下载",
                    type: "positive"
                }],
                cancelable: false
            }, function (result) {
                if (result.status === "click" && result.title === "立即下载") {
                    _this.downApp();
                }
            });
        }
        //
        weiui.setPageBackPressed(null, function () {
            weiui.confirm({
                title: "温馨提示",
                message: "你确定要退出WEIUI吗？",
                buttons: ["取消", "确定"]
            }, function (result) {
                if (result.status === "click" && result.title === "确定") {
                    weiui.closePage(null);
                }
            });
        });
    },


    methods: {
        downApp: function downApp() {
            weiui.openWeb("http://kuaifan.vip/weiui/app/android.apk");
        },
        scaner: function scaner() {
            var _this2 = this;

            weiui.openScaner(null, function (res) {
                if (res.status === "success") {
                    _this2.history.unshift(res.text);
                    weiui.setCachesString("scaner", JSON.stringify(_this2.history), 0);
                    _this2.openAuto(res.text);
                }
            });
        },
        refresh: function refresh() {
            weiui.reloadPage();
        },
        componentsClick: function componentsClick(data) {
            this.openUrl(this.components[data.position].url);
        },
        moduleClick: function moduleClick(data) {
            this.openUrl(this.module[data.position].url);
        },
        thirdModuleClick: function thirdModuleClick(data) {
            this.openUrl(this.third_module[data.position].url);
        },
        aboutListsClick: function aboutListsClick(data) {
            this.openWeb(this.about_lists[data.position].url);
        },
        historyClick: function historyClick(data) {
            this.openAuto(this.history[data.position]);
        },
        clearHistory: function clearHistory() {
            var _this3 = this;

            weiui.confirm({
                title: "删除提示",
                message: "你确定要删除扫码记录吗？",
                buttons: ["取消", "确定"]
            }, function (result) {
                if (result.status === "click" && result.title === "确定") {
                    _this3.history = [];
                    weiui.setCachesString("scaner", JSON.stringify(_this3.history), 0);
                }
            });
        },
        openUrl: function openUrl(url) {
            weiui.openPage({
                url: url
            });
        },
        openWeb: function openWeb(url) {
            weiui.openPage({
                url: url,
                pageType: 'web'
            });
        },
        openAuto: function openAuto(url) {
            weiui.openPage({
                url: url,
                pageType: 'auto'
            });
        }
    }
};

/***/ }),

/***/ 52:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["app"]
  }, [_c('weiui_navbar', {
    staticClass: ["navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "left"
    },
    on: {
      "click": _vm.scaner
    }
  }, [_c('weiui_icon', {
    staticClass: ["navbar-icon"],
    attrs: {
      "weiui": {
        content: 'tb-scan'
      }
    }
  })], 1), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["navbar-title"]
  }, [_vm._v("WEIUI")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    },
    on: {
      "click": _vm.refresh
    }
  }, [_c('weiui_icon', {
    staticClass: ["navbar-icon"],
    attrs: {
      "weiui": {
        content: 'refresh'
      }
    }
  })], 1)], 1), (_vm.newApp) ? _c('div', {
    staticClass: ["version-box"],
    on: {
      "click": _vm.downApp
    }
  }, [_c('text', {
    staticClass: ["version-text"]
  }, [_vm._v("发现新版本")]), _c('text', {
    staticClass: ["version-button"]
  }, [_vm._v("立即下载")])]) : _vm._e(), _c('weiui_list', {
    staticClass: ["list"],
    attrs: {
      "weiui": {
        pullTips: false
      }
    }
  }, [_c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Components")]), _c('weiui_recyler', {
    on: {
      "itemClick": _vm.componentsClick
    }
  }, _vm._l((_vm.components), function(item) {
    return _c('div', {
      staticClass: ["list-item"]
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          content: item.icon
        }
      }
    }), _c('text', {
      staticClass: ["list-left-title"]
    }, [_vm._v(_vm._s(item.title))])], 1), _c('div', {
      staticClass: ["list-item-right"]
    }, [_c('text', {
      staticClass: ["list-right-title"]
    }, [_vm._v(_vm._s(item.title_en))]), _c('weiui_icon', {
      staticClass: ["list-right-icon"],
      attrs: {
        "weiui": {
          content: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Module")]), _c('weiui_recyler', {
    on: {
      "itemClick": _vm.moduleClick
    }
  }, _vm._l((_vm.module), function(item) {
    return _c('div', {
      staticClass: ["list-item"]
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          content: item.icon
        }
      }
    }), _c('text', {
      staticClass: ["list-left-title"]
    }, [_vm._v(_vm._s(item.title))])], 1), _c('div', {
      staticClass: ["list-item-right"]
    }, [_c('text', {
      staticClass: ["list-right-title"]
    }, [_vm._v(_vm._s(item.title_en))]), _c('weiui_icon', {
      staticClass: ["list-right-icon"],
      attrs: {
        "weiui": {
          content: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Third Module")]), _c('weiui_recyler', {
    on: {
      "itemClick": _vm.thirdModuleClick
    }
  }, _vm._l((_vm.third_module), function(item) {
    return _c('div', {
      staticClass: ["list-item"]
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          content: item.icon
        }
      }
    }), _c('text', {
      staticClass: ["list-left-title"]
    }, [_vm._v(_vm._s(item.title))])], 1), _c('div', {
      staticClass: ["list-item-right"]
    }, [_c('text', {
      staticClass: ["list-right-title"]
    }, [_vm._v(_vm._s(item.title_en))]), _c('weiui_icon', {
      staticClass: ["list-right-icon"],
      attrs: {
        "weiui": {
          content: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("About Weiui")]), _c('weiui_recyler', {
    on: {
      "itemClick": _vm.aboutListsClick
    }
  }, _vm._l((_vm.about_lists), function(item) {
    return _c('div', {
      staticClass: ["list-item"]
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          content: item.icon
        }
      }
    }), _c('text', {
      staticClass: ["list-left-title"]
    }, [_vm._v(_vm._s(item.title))])], 1), _c('div', {
      staticClass: ["list-item-right"]
    }, [_c('text', {
      staticClass: ["list-right-title"]
    }, [_vm._v(_vm._s(item.title_en))]), _c('weiui_icon', {
      staticClass: ["list-right-icon"],
      attrs: {
        "weiui": {
          content: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), (_vm.history.length > 0) ? _c('div', {
    staticClass: ["list-title-box"]
  }, [_c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("扫码历史")]), _c('text', {
    staticClass: ["list-subtitle"],
    on: {
      "click": function($event) {
        _vm.clearHistory()
      }
    }
  }, [_vm._v("清空历史")])]) : _vm._e(), (_vm.history.length > 0) ? _c('weiui_recyler', {
    on: {
      "itemClick": _vm.historyClick
    }
  }, _vm._l((_vm.history), function(text) {
    return _c('div', {
      staticClass: ["list-item"]
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          content: 'ionic'
        }
      }
    }), _c('text', {
      staticClass: ["list-left-title-history"]
    }, [_vm._v(_vm._s(text))])], 1), _c('div', {
      staticClass: ["list-item-right"]
    }, [_c('weiui_icon', {
      staticClass: ["list-right-icon"],
      attrs: {
        "weiui": {
          content: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })) : _vm._e()], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });