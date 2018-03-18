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
/******/ 	return __webpack_require__(__webpack_require__.s = 4);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */,
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(5)
)

/* script */
__vue_exports__ = __webpack_require__(6)

/* template */
var __vue_template__ = __webpack_require__(7)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_plugin/weiui/src/recyler.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-6f832424"
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
/* 5 */
/***/ (function(module, exports) {

module.exports = {
  "wrapper": {
    "flex": 1
  },
  "side_panel": {
    "width": "750",
    "height": "500",
    "backgroundColor": "#808000"
  },
  "tabbar": {
    "width": "750",
    "height": "500"
  },
  "recyler": {
    "width": "750",
    "height": "500"
  },
  "panel": {
    "width": "750",
    "height": "110",
    "backgroundColor": "#ff9e14"
  },
  "panel-item": {
    "width": "600",
    "height": "80",
    "marginLeft": "75",
    "marginTop": "15",
    "marginBottom": "15",
    "flexDirection": "column",
    "justifyContent": "center",
    "backgroundColor": "#2af700"
  },
  "text": {
    "fontSize": "50",
    "textAlign": "center",
    "color": "#000000"
  }
}

/***/ }),
/* 6 */
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

exports.default = {
    data: function data() {
        return {
            lists: [],

            tabPages: [{
                title: '页面1',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/c99b8282d1c822a1eb78dc5bc94c877e.bundle.wx',
                message: 5
            }, {
                title: '页面2',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/e1b7ff7c02bf7898d9f2619d9c1fc966.bundle.wx',
                dot: true
            }, {
                title: '页面3',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/6087fb97013406c0623e86ef7786a532.bundle.wx'
            }],
            tabPagesUI: {},

            scrollText: "aaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbcccccccccccaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbccccccccccc"
        };
    },
    mounted: function mounted() {
        var _this = this;

        setTimeout(function () {
            _this.$refs.myTabbar.setCurrentItem(2);
            _this.$refs.myTabbar.goUrl(2, "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx");
        }, 1000);

        for (var i = 0; i <= 10; i++) {
            this.lists.push(i);
        }

        setTimeout(function () {
            _this.lists.splice(1, 1, "aaa");
        }, 1000);

        setTimeout(function () {
            _this.scrollText = "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx";
        }, 5000);

        this.$refs.myRecyler.setHasMore(true);
    },

    methods: {
        refreshListener: function refreshListener(params) {
            this.$refs.myTabbar.setRefreshing(params['position'], false);
        },
        pageSelected: function pageSelected(params) {
            console.log("pageSelected", params);
        },
        itemClickScroll: function itemClickScroll(params) {
            console.log("itemClickScroll", params);
        },
        itemClickRecyler: function itemClickRecyler(params) {
            console.log("itemClickRecyler", params);
        },
        itemLongClickRecyler: function itemLongClickRecyler(params) {
            console.log("itemLongClickRecyler", params);
        },
        itemSwipeClickRecyler: function itemSwipeClickRecyler(params) {
            console.log("itemSwipeClickRecyler", params);
        },
        pullloadListenerRecyler: function pullloadListenerRecyler(params) {
            if (this.lists.length > 50) {
                this.$refs.myRecyler.setHasMore(false);
                return;
            }
            for (var i = 1; i <= 10; i++) {
                this.lists.push(i);
            }
            this.$refs.myRecyler.pullloaded();
        },
        refreshListenerRecyler: function refreshListenerRecyler(params) {
            var tmp = [];
            for (var i = 1; i <= 11; i++) {
                tmp.push(i);
            }
            this.lists = tmp;
            this.$refs.myRecyler.setHasMore(true);
            this.$refs.myRecyler.refreshed();
        }
    }
};

/***/ }),
/* 7 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["wrapper"]
  }, [_c('scroller', [_c('weiui_navbar', [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "middle"
    }
  }, [_c('text', {
    staticStyle: {
      color: "#ffffff"
    }
  }, [_vm._v("╮(╯_╰)╭")])])], 1), _c('weiui_scroll_text', {
    staticStyle: {
      height: "500px"
    },
    attrs: {
      "text": _vm.scrollText,
      "weiui": {
        speed: 2,
        fontSize: 16,
        color: '#ff0000',
        backgroundColor: '#00ffff'
      }
    },
    on: {
      "itemClick": _vm.itemClickScroll
    }
  }), _c('weiui_side_panel', {
    ref: "mySidePanel",
    staticClass: ["side_panel"]
  }, [_c('weiui_side_menu', {
    attrs: {
      "weiui": {
        scrollbar: false,
        width: 400,
        backgroundColor: '#00ffff'
      }
    }
  }, [_c('text', [_vm._v("bbbb")]), _c('text', [_vm._v("bbbb2")]), _c('texwt', [_vm._v("bbbb3")]), _c('text', [_vm._v("bbbb3-1")]), _c('div', [_c('text', [_vm._v("bbbb4")])]), _c('text', [_vm._v("bbbb")]), _c('text', [_vm._v("bbbb2")]), _c('texwt', [_vm._v("bbbb3")]), _c('text', [_vm._v("bbbb3-1")]), _c('div', [_c('text', [_vm._v("bbbb4")])]), _c('text', [_vm._v("bbbb")]), _c('text', [_vm._v("bbbb2")]), _c('texwt', [_vm._v("bbbb3")]), _c('text', [_vm._v("bbbb3-1")]), _c('div', [_c('text', [_vm._v("bbbb4")])]), _c('text', [_vm._v("bbbb")]), _c('text', [_vm._v("bbbb2")]), _c('texwt', [_vm._v("bbbb3")]), _c('text', [_vm._v("bbbb3-1")]), _c('div', [_c('text', [_vm._v("bbbb4")])]), _c('text', [_vm._v("bbbb")]), _c('text', [_vm._v("bbbb2")]), _c('texwt', [_vm._v("bbbb3")]), _c('text', [_vm._v("bbbb3-1")]), _c('div', [_c('text', [_vm._v("bbbb4")])])], 1), _c('weiui_recyler', {
    ref: "myRecyler",
    staticClass: ["recyler"],
    attrs: {
      "weiui": {
        row: 1,
        pullTips: true,
        dividerColor: '#ffffff',
        dividerHeight: '1',
      },
      "swipe": [{
        text: '选项1',
        size: '12',
        padding: '20',
        color: '#ffff00',
        backgroundColor: '#ff0000',
      }, {
        text: '选项2',
        size: '12',
        padding: '30',
        color: '#ecedf0',
        backgroundColor: '#00ffff',
      }]
    },
    on: {
      "itemClick": _vm.itemClickRecyler,
      "itemLongClick": _vm.itemLongClickRecyler,
      "itemSwipeClick": _vm.itemSwipeClickRecyler,
      "pullLoadListener": _vm.pullloadListenerRecyler,
      "refreshListener": _vm.refreshListenerRecyler
    }
  }, _vm._l((_vm.lists), function(num) {
    return _c('div', {
      staticClass: ["panel"]
    }, [_c('div', {
      staticClass: ["panel-item"]
    }, [_c('text', {
      staticClass: ["text"]
    }, [_vm._v(_vm._s(num))])])])
  }))], 1), _c('weiui_tabbar', {
    ref: "myTabbar",
    staticClass: ["tabbar"],
    attrs: {
      "weiui": _vm.tabPagesUI,
      "pages": _vm.tabPages
    },
    on: {
      "refreshListener": _vm.refreshListener,
      "pageSelected": _vm.pageSelected
    }
  })], 1)])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);