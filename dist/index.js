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
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(1)
)

/* script */
__vue_exports__ = __webpack_require__(2)

/* template */
var __vue_template__ = __webpack_require__(3)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_plugin/weiui/src/index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-877a68a0"
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
/* 1 */
/***/ (function(module, exports) {

module.exports = {
  "wrapper": {
    "flex": 1
  },
  "tabbar": {
    "flex": 1,
    "width": "750"
  }
}

/***/ }),
/* 2 */
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

var weiui = weex.requireModule('weiui');
var weiui_picture = weex.requireModule('weiui_picture');

exports.default = {
    data: function data() {
        return {
            tabPages: [{
                name: "a",
                title: '页面1',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/c99b8282d1c822a1eb78dc5bc94c877e.bundle.wx',
                message: 5
            }, {
                name: "b",
                title: '页面2',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/e1b7ff7c02bf7898d9f2619d9c1fc966.bundle.wx',
                dot: true
            }, {
                name: "c",
                title: '页面3',
                selectedIcon: 'https://gd2.alicdn.com/bao/uploaded/i2/T14H1LFwBcXXXXXXXX_!!0-item_pic.jpg',
                unSelectedIcon: 'https://gd1.alicdn.com/bao/uploaded/i1/TB1PXJCJFXXXXciXFXXXXXXXXXX_!!0-item_pic.jpg',
                url: 'http://dotwe.org/raw/dist/6087fb97013406c0623e86ef7786a532.bundle.wx'
            }],
            tabPagesUI: {},
            pictureList: []

        };
    },
    mounted: function mounted() {

        /*setTimeout(() => {
            console.log("aaaaaaaaaaaIP", weiui.screenUtils("getScreenWidth"));
            console.log("aaaaaaaaaaaIP", weiui.permissionUtils("getMatches"));
             /!*var aaa = weiui.regex("getMatches", "1(.*?)3", "1a31b31c31d3");
             console.log("aaaaaaaD", typeof aaa);
            console.log("aaaaaaaD1|", aaa.length);
            for (let key in aaa) {
                console.log("aaaaaaaD2|", key);
                console.log("aaaaaaaD3|", aaa[key]);
            }*!/
             /!*weiui.toast({
                "gravity": "top",
                "y": 100,
                "long": true,
                "message": "getMatches",
            });
            setTimeout(() => {
                weiui.toast();
            }, 1000);*!/
         }, 2000);*/

        /*setTimeout(() => {
            this.$refs.myTabbar.showMsg("BB", 12);
        }, 1000);*/
        /*setTimeout(() => {
            this.$refs.myTabbar.setCurrentItem("c");
            this.$refs.myTabbar.goUrl("c", "http://dotwe.org/raw/dist/ac78df2f9db68ce3222b164d00458a4f.bundle.wx");
        }, 2000);*/
    },

    methods: {
        count: function count(obj) {
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
        },
        tabSelect: function tabSelect(params) {
            console.log("tabSelect", params);
        },
        refreshListener: function refreshListener(params) {
            this.$refs.myTabbar.setRefreshing(params['name'], false);
        },
        pageSelected: function pageSelected(params) {
            console.log("pageSelected", params);
        },
        scaner: function scaner() {
            weiui.openScaner(null, function (res) {
                if (res.status === "success") {
                    weiui.toast(res.status + " | " + res.text);
                }
            });
        },
        pictureCreate: function pictureCreate() {
            var _this = this;

            weiui_picture.create({
                selected: this.pictureList
            }, function (res) {
                if (res.status === "success") {
                    _this.pictureList = res.lists;
                }
                console.log(res);
            });
        },
        pictureView: function pictureView(index) {
            weiui_picture.picturePreview(index, this.pictureList);
        },
        LoadingIcon: function LoadingIcon() {
            weiui.loading({
                title: '000000'
            });
            setTimeout(function () {
                weiui.loadingClose();
            }, 5000);
        },
        swipeCaptcha: function swipeCaptcha() {
            weiui.swipeCaptcha(null, function (res) {
                weiui.toast(res.status);
            });
        }
    }
};

/***/ }),
/* 3 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["wrapper"]
  }, [_c('weiui_tabbar', {
    ref: "myTabbar",
    staticClass: ["tabbar"],
    attrs: {
      "type": "top|bottom",
      "weiui": _vm.tabPagesUI
    },
    on: {
      "tabSelect": _vm.tabSelect,
      "refreshListener": _vm.refreshListener,
      "pageSelected": _vm.pageSelected
    }
  }, [_c('weiui_tabbar_page', {
    attrs: {
      "name": "AA",
      "title": "首页",
      "message": "10",
      "weiui": {
        message: 3,
        selectedIcon: 'ion-ionic'
      }
    }
  }, [_c('div', [_c('text', [_vm._v("page test")])]), _c('div', [_c('text', [_vm._v("page test1")])]), _c('div', [_c('text', [_vm._v("page test2")])]), _c('div', [_c('text', [_vm._v("page test3")])])]), _c('weiui_tabbar_page', {
    attrs: {
      "name": "BB",
      "message": 2
    }
  }, [_c('div', [_c('text', [_vm._v("page test2")])]), _c('weiui_icon', {
    staticStyle: {
      width: "100px",
      height: "100px"
    },
    attrs: {
      "iconClickColor": "#ff0000"
    },
    on: {
      "click": _vm.LoadingIcon
    }
  })], 1), _c('weiui_tabbar_page', [_c('div', [_c('text', [_vm._v("page test3")])]), _c('weiui_icon', {
    staticStyle: {
      width: "100px",
      height: "100px"
    },
    attrs: {
      "iconClickColor": "#ff0000"
    },
    on: {
      "click": _vm.swipeCaptcha
    }
  }), _c('image', {
    staticStyle: {
      width: "100px",
      height: "100px"
    },
    attrs: {
      "src": "http://img.lanrentuku.com/img/allimg/1212/5-121204193R7-51.gif"
    }
  })], 1), _c('weiui_tabbar_page', [_c('div', [_c('text', [_vm._v("page test4")])]), _c('weiui_icon', {
    staticStyle: {
      width: "100px",
      height: "100px"
    },
    attrs: {
      "iconClickColor": "#ff0000"
    },
    on: {
      "click": _vm.scaner
    }
  }), _c('weiui_icon', {
    staticStyle: {
      width: "100px",
      height: "100px"
    },
    attrs: {
      "iconClickColor": "#ff0000"
    },
    on: {
      "click": _vm.pictureCreate
    }
  }), _vm._l((_vm.pictureList), function(item, index) {
    return _c('image', {
      staticStyle: {
        width: "100px",
        height: "100px",
        marginRight: "10px",
        marginTop: "10px"
      },
      attrs: {
        "src": 'file://' + item.path
      },
      on: {
        "click": function($event) {
          _vm.pictureView(index)
        }
      }
    })
  })], 2)], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);