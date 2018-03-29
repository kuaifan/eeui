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
/******/ 	return __webpack_require__(__webpack_require__.s = 40);
/******/ })
/************************************************************************/
/******/ ({

/***/ 40:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(41)
)

/* script */
__vue_exports__ = __webpack_require__(42)

/* template */
var __vue_template__ = __webpack_require__(43)
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

/***/ 41:
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

/***/ 42:
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

var weiui = weex.requireModule('weiui');

exports.default = {
    data: function data() {
        return {
            components: [{
                title: '常用按钮',
                title_en: 'weiui_button',
                icon: 'android-checkbox-blank',
                url: 'component_button.js'
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
                title: '图片选择器',
                title_en: 'third_picture',
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

            history: []
        };
    },
    mounted: function mounted() {
        this.history = JSON.parse(weiui.getCachesString("scaner", []));
    },


    methods: {
        clearHistory: function clearHistory() {
            this.history = [];
            weiui.setCachesString("scaner", JSON.stringify(this.history), 0);
        },
        scaner: function scaner() {
            var _this = this;

            weiui.openScaner(null, function (res) {
                if (res.status === "success") {
                    _this.history.unshift(res.text);
                    weiui.setCachesString("scaner", JSON.stringify(_this.history), 0);
                    _this.openUrl(res.text);
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
        refresh: function refresh() {
            weiui.reloadPage();
        }
    }
};

/***/ }),

/***/ 43:
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
        icon: 'tb-scan'
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
        icon: 'refresh'
      }
    }
  })], 1)], 1), _c('weiui_list', {
    staticClass: ["list"],
    attrs: {
      "weiui": {
        pullTips: false
      }
    }
  }, [_c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Components")]), _c('weiui_recyler', _vm._l((_vm.components), function(item) {
    return _c('div', {
      staticClass: ["list-item"],
      on: {
        "click": function($event) {
          _vm.openUrl(item.url)
        }
      }
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          icon: item.icon
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
          icon: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Module")]), _c('weiui_recyler', _vm._l((_vm.module), function(item) {
    return _c('div', {
      staticClass: ["list-item"],
      on: {
        "click": function($event) {
          _vm.openUrl(item.url)
        }
      }
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          icon: item.icon
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
          icon: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("Third Module")]), _c('weiui_recyler', _vm._l((_vm.third_module), function(item) {
    return _c('div', {
      staticClass: ["list-item"],
      on: {
        "click": function($event) {
          _vm.openUrl(item.url)
        }
      }
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          icon: item.icon
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
          icon: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("About Weiui")]), _c('weiui_recyler', _vm._l((_vm.about_lists), function(item) {
    return _c('div', {
      staticClass: ["list-item"],
      on: {
        "click": function($event) {
          _vm.openWeb(item.url)
        }
      }
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          icon: item.icon
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
          icon: 'ios-arrow-right 70%'
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
  }, [_vm._v("清空历史")])]) : _vm._e(), (_vm.history.length > 0) ? _c('weiui_recyler', _vm._l((_vm.history), function(text) {
    return _c('div', {
      staticClass: ["list-item"],
      on: {
        "click": function($event) {
          _vm.openUrl(text)
        }
      }
    }, [_c('div', {
      staticClass: ["list-item-left"]
    }, [_c('weiui_icon', {
      staticClass: ["list-left-icon"],
      attrs: {
        "weiui": {
          icon: 'ionic'
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
          icon: 'ios-arrow-right 70%'
        }
      }
    })], 1)])
  })) : _vm._e()], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });