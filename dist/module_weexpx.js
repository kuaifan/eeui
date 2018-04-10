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
/******/ 	return __webpack_require__(__webpack_require__.s = 100);
/******/ })
/************************************************************************/
/******/ ({

/***/ 100:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(101)
)

/* script */
__vue_exports__ = __webpack_require__(102)

/* template */
var __vue_template__ = __webpack_require__(103)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/module_weexpx.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-6fb2d37c"
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

/***/ 101:
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
  "content": {
    "flex": 1,
    "alignItems": "center"
  },
  "subtitle": {
    "paddingTop": "56",
    "paddingRight": "24",
    "paddingBottom": "24",
    "paddingLeft": "24",
    "fontSize": "28",
    "color": "#757575"
  },
  "button": {
    "width": "276",
    "textAlign": "center",
    "marginTop": "20",
    "marginBottom": "20",
    "paddingTop": "26",
    "paddingBottom": "26",
    "paddingLeft": "30",
    "paddingRight": "30",
    "color": "#ffffff",
    "backgroundColor": "#00B4FF"
  },
  "inBox": {
    "flexDirection": "row",
    "justifyContent": "center",
    "alignItems": "center"
  },
  "inPut": {
    "width": "120",
    "height": "60",
    "fontSize": "26",
    "textAlign": "center",
    "marginLeft": "10",
    "marginRight": "10",
    "backgroundColor": "#e4f5ff"
  }
}

/***/ }),

/***/ 102:
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

var weiui = weex.requireModule('weiui');

exports.default = {
    data: function data() {
        return {
            px2dpA: '',
            px2dpB: '',
            dp2pxA: '',
            dp2pxB: ''
        };
    },

    methods: {
        viewCode: function viewCode(str) {
            weiui.openPage({
                url: "http://kuaifan.vip/weiui/#/" + str,
                pageType: 'web'
            });
        },
        weexPx2dp: function weexPx2dp() {
            this.px2dpB = weiui.weexPx2dp(this.px2dpA);
        },
        weexDp2px: function weexDp2px() {
            this.dp2pxB = weiui.weexDp2px(this.dp2pxA);
        }
    }
};

/***/ }),

/***/ 103:
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
  }, [_vm._v("像素转换")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    },
    on: {
      "click": function($event) {
        _vm.viewCode('module/weexpx')
      }
    }
  }, [_c('weiui_icon', {
    staticClass: ["iconr"],
    attrs: {
      "icon": "code-working"
    }
  })], 1)], 1), _c('div', {
    staticClass: ["content"]
  }, [_c('text', {
    staticClass: ["subtitle"]
  }, [_vm._v("weex px转屏幕像素")]), _c('div', {
    staticClass: ["inBox"]
  }, [_c('input', {
    staticClass: ["inPut"],
    attrs: {
      "value": (_vm.px2dpA)
    },
    on: {
      "input": function($event) {
        _vm.px2dpA = $event.target.attr.value
      }
    }
  }), _c('text', [_vm._v("=")]), _c('input', {
    staticClass: ["inPut"],
    attrs: {
      "value": (_vm.px2dpB)
    },
    on: {
      "input": function($event) {
        _vm.px2dpB = $event.target.attr.value
      }
    }
  })]), _c('text', {
    staticClass: ["button"],
    on: {
      "click": function($event) {
        _vm.weexPx2dp()
      }
    }
  }, [_vm._v("转换")]), _c('text', {
    staticClass: ["subtitle"]
  }, [_vm._v("屏幕像素转weex px")]), _c('div', {
    staticClass: ["inBox"]
  }, [_c('input', {
    staticClass: ["inPut"],
    attrs: {
      "value": (_vm.dp2pxA)
    },
    on: {
      "input": function($event) {
        _vm.dp2pxA = $event.target.attr.value
      }
    }
  }), _c('text', [_vm._v("=")]), _c('input', {
    staticClass: ["inPut"],
    attrs: {
      "value": (_vm.dp2pxB)
    },
    on: {
      "input": function($event) {
        _vm.dp2pxB = $event.target.attr.value
      }
    }
  })]), _c('text', {
    staticClass: ["button"],
    on: {
      "click": function($event) {
        _vm.weexDp2px()
      }
    }
  }, [_vm._v("转换")])])], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })

/******/ });