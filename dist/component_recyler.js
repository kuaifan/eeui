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
/******/ 	return __webpack_require__(__webpack_require__.s = 20);
/******/ })
/************************************************************************/
/******/ ({

/***/ 20:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(21)
)

/* script */
__vue_exports__ = __webpack_require__(22)

/* template */
var __vue_template__ = __webpack_require__(23)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/component_recyler.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-773b0c64"
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

/***/ 21:
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "width": "750",
    "flex": 1
  },
  "title": {
    "fontSize": "28",
    "color": "#ffffff"
  },
  "iconr": {
    "width": "100",
    "color": "#ffffff"
  },
  "recyler": {
    "width": "750",
    "flex": 1
  },
  "panel": {
    "width": "750"
  },
  "panel-item": {
    "width": "750",
    "paddingTop": "22",
    "paddingBottom": "22",
    "flexDirection": "column",
    "justifyContent": "center"
  },
  "panel-text": {
    "fontSize": "50",
    "textAlign": "center"
  }
}

/***/ }),

/***/ 22:
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

var weiui = weex.requireModule('weiui');

exports.default = {
    data: function data() {
        return {
            swipe: [{
                text: '选项1',
                size: '12',
                padding: '20',
                color: '#ffff00',
                backgroundColor: '#ff0000'
            }, {
                text: '选项2',
                size: '12',
                padding: '30',
                color: '#ecedf0',
                backgroundColor: '#00ffff'
            }],

            lists: []
        };
    },
    mounted: function mounted() {
        var _this = this;

        for (var i = 1; i <= 20; i++) {
            this.lists.push(i);
        }
        this.$refs.reflectName.setHasMore(true);
        //
        setTimeout(function () {
            _this.lists.splice(2, 1, "改变第三项文字");
            // splice 详细用法http://www.w3school.com.cn/jsref/jsref_splice.asp
        }, 1000);
    },

    methods: {
        viewCode: function viewCode(str) {
            weiui.openPage({
                url: "http://kuaifan.vip/weiui/#/" + str,
                pageType: 'web'
            });
        },
        itemClick: function itemClick(params) {
            weiui.toast("点击了" + (params.position + 1) + "项");
        },
        itemSwipeClick: function itemSwipeClick(params) {
            weiui.toast("点击了" + (params.position + 1) + "项侧滑的第" + (params.swipePosition + 1) + "个菜单");
        },
        pullLoadListener: function pullLoadListener() {
            var _this2 = this;

            var count = this.lists.length;
            if (count >= 100) {
                this.$refs.reflectName.setHasMore(false);
                return;
            }
            setTimeout(function () {
                for (var i = 1; i <= 20; i++) {
                    _this2.lists.push(count + i);
                }
                _this2.$refs.reflectName.pullloaded();
                weiui.toast("加载" + (count + 1) + "~" + _this2.lists.length + "数据成功");
            }, 1000);
        },
        refreshListener: function refreshListener() {
            var _this3 = this;

            var newList = [];
            for (var i = 1; i <= 20; i++) {
                newList.push(i);
            }
            setTimeout(function () {
                _this3.lists = newList;
                _this3.$refs.reflectName.setHasMore(true);
                _this3.$refs.reflectName.refreshed();
                weiui.toast("刷新数据成功");
            }, 1000);
        }
    }
};

/***/ }),

/***/ 23:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["app"]
  }, [_c('weiui_navbar', [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', {
    staticClass: ["title"]
  }, [_vm._v("列表容器")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    },
    on: {
      "click": function($event) {
        _vm.viewCode('component/weiui_recyler')
      }
    }
  }, [_c('weiui_icon', {
    staticClass: ["iconr"],
    attrs: {
      "icon": "code-working"
    }
  })], 1)], 1), _c('weiui_recyler', {
    ref: "reflectName",
    staticClass: ["recyler"],
    attrs: {
      "weiui": {
        row: 1,
        pullTips: true,
        dividerColor: '#e4e4e4',
        dividerHeight: '1',
      },
      "swipe": _vm.swipe
    },
    on: {
      "itemClick": _vm.itemClick,
      "itemSwipeClick": _vm.itemSwipeClick,
      "pullLoadListener": _vm.pullLoadListener,
      "refreshListener": _vm.refreshListener
    }
  }, _vm._l((_vm.lists), function(num) {
    return _c('div', {
      staticClass: ["panel"]
    }, [_c('div', {
      staticClass: ["panel-item"]
    }, [_c('text', {
      staticClass: ["panel-text"]
    }, [_vm._v(_vm._s(num))])])])
  }))], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });