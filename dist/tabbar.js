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
/******/ 	return __webpack_require__(__webpack_require__.s = 35);
/******/ })
/************************************************************************/
/******/ ({

/***/ 35:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(36)
)

/* script */
__vue_exports__ = __webpack_require__(37)

/* template */
var __vue_template__ = __webpack_require__(38)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/tabbar.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-0d7e390e"
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

/***/ 36:
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "flex": 1
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

/***/ 37:
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

var weiui = weex.requireModule('weiui');

exports.default = {
    methods: {
        tabSelect: function tabSelect(params) {
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

/***/ 38:
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
      "tabSelect": _vm.tabSelect,
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
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["page-navbar-title"]
  }, [_vm._v("首页")])])], 1), _c('div', {
    staticClass: ["page-content"]
  }, [_c('text', [_vm._v("页签里面可以放任何子组件，感谢你对weiui的支持")]), _c('image', {
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
  }, [_c('div', {
    staticClass: ["page-content"]
  }, [_c('text', [_vm._v("page 2，图标支持网络图片")])])]), _c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_3',
        title: '圈子',
        message: 99,
        selectedIcon: 'aperture 26sp'
      }
    }
  }, [_c('div', {
    staticClass: ["page-content"]
  }, [_c('text', [_vm._v("page 3")])])]), _c('weiui_tabbar_page', {
    attrs: {
      "weiui": {
        tabName: 'name_4',
        title: '设置',
        dot: true,
        selectedIcon: 'gear-a'
      }
    }
  }, [_c('div', {
    staticClass: ["page-content"]
  }, [_c('text', [_vm._v("page 4")])])])], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });