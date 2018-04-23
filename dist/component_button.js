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
/******/ 	return __webpack_require__(__webpack_require__.s = 5);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var weiui = weex.requireModule('weiui');

var app = {
    openViewCode: function openViewCode(str) {
        weiui.openPage({
            url: "http://kuaifan.vip/weiui/#/" + str,
            pageType: 'web'
        });
    }
};

module.exports = app;

/***/ }),
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(6)
)

/* script */
__vue_exports__ = __webpack_require__(7)

/* template */
var __vue_template__ = __webpack_require__(8)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/component_button.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-22c86864"
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
/* 6 */
/***/ (function(module, exports) {

module.exports = {
  "app": {
    "width": "750",
    "flex": 1,
    "backgroundColor": "#ffffff"
  },
  "title": {
    "fontSize": "28",
    "color": "#ffffff"
  },
  "iconr": {
    "width": "100",
    "color": "#ffffff"
  },
  "list": {
    "width": "750",
    "flex": 1
  },
  "list-title": {
    "marginTop": "24",
    "marginBottom": "12",
    "paddingTop": "36",
    "paddingRight": "24",
    "paddingBottom": "24",
    "paddingLeft": "24",
    "fontSize": "28",
    "color": "#757575"
  },
  "list-item": {
    "width": "750",
    "flexDirection": "row"
  },
  "button": {
    "fontSize": "24",
    "marginLeft": "37.5",
    "marginRight": "37.5",
    "marginBottom": "20",
    "width": "300",
    "height": "80",
    "backgroundColor": "#00B4FF"
  },
  "list-input-item": {
    "width": "750",
    "height": "90",
    "flexDirection": "row",
    "alignItems": "center",
    "borderBottomColor": "#e4e4e4",
    "borderBottomStyle": "solid",
    "borderBottomWidth": "1"
  },
  "cell": {
    "paddingLeft": "50",
    "fontSize": "24",
    "color": "#666666"
  },
  "switch": {
    "paddingRight": "35"
  },
  "input": {
    "paddingRight": "50",
    "flex": 1,
    "height": "90",
    "textAlign": "right",
    "fontSize": "28"
  },
  "demo-item": {
    "width": "750",
    "paddingTop": "30",
    "paddingBottom": "30",
    "alignItems": "center"
  }
}

/***/ }),
/* 7 */
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
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
            loading: false,

            text: '按钮文字',
            width: '702px',
            height: '80px',
            loading2: false,
            disabled: false,
            backgroundColor: '#FF5000',
            borderColor: '#FF5000',
            borderRadius: '8px',
            fontSize: '28px',
            color: '#FFFFFF'
        };
    },
    mounted: function mounted() {
        var _this = this;

        setTimeout(function () {
            _this.loading = true;
        }, 3000);
    },

    computed: {
        demoStyle: function demoStyle() {
            var backgroundColor = this.backgroundColor,
                borderColor = this.borderColor,
                borderRadius = this.borderRadius,
                text = this.text,
                color = this.color,
                loading2 = this.loading2,
                disabled = this.disabled;

            var customStyle = {};
            if (backgroundColor) {
                customStyle.backgroundColor = backgroundColor;
            }
            if (borderColor) {
                customStyle.borderColor = borderColor;
                customStyle.borderWidth = "1px";
            }
            if (borderRadius) {
                customStyle.borderRadius = borderRadius;
            }
            if (text) {
                customStyle.text = text;
            }
            if (color) {
                customStyle.color = color;
            }
            customStyle.loading = loading2;
            customStyle.disabled = disabled;
            return customStyle;
        }
    },
    methods: {
        viewCode: function viewCode(str) {
            (0, _app.openViewCode)(str);
        }
    }
};

/***/ }),
/* 8 */
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
  }, [_vm._v("常用按钮")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    },
    on: {
      "click": function($event) {
        _vm.viewCode('component/weiui_button')
      }
    }
  }, [_c('weiui_icon', {
    staticClass: ["iconr"],
    attrs: {
      "content": "code-working"
    }
  })], 1)], 1), _c('weiui_list', {
    staticClass: ["list"],
    attrs: {
      "weiui": {
        dividerHeight: 0,
        pullTips: false
      }
    }
  }, [_c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("预设样式")]), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '默认'
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '红色',
        model: 'red'
      }
    }
  })], 1), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '绿色',
        model: 'green'
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '蓝色',
        model: 'blue'
      }
    }
  })], 1), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '粉红',
        model: 'pink'
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '黄色',
        model: 'yellow'
      }
    }
  })], 1), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '橙色',
        model: 'orange'
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '灰色',
        model: 'gray'
      }
    }
  })], 1), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '黑色',
        model: 'black'
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '白色',
        model: 'white'
      }
    }
  })], 1), _c('div', {
    staticClass: ["list-item"]
  }, [_c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '加载中',
        loading: true
      }
    }
  }), _c('weiui_button', {
    staticClass: ["button"],
    attrs: {
      "weiui": {
        text: '禁用',
        disabled: true
      }
    }
  })], 1), _c('text', {
    staticClass: ["list-title"]
  }, [_vm._v("自定义样式")]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("按钮文字")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "文字",
      "value": _vm.text
    },
    on: {
      "input": function($event) {
        _vm.text = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"],
    staticStyle: {
      flex: "1"
    }
  }, [_vm._v("是否加载中")]), _c('switch', {
    staticClass: ["switch"],
    attrs: {
      "checked": _vm.loading2
    },
    on: {
      "change": function($event) {
        _vm.loading2 = !_vm.loading2
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"],
    staticStyle: {
      flex: "1"
    }
  }, [_vm._v("是否禁用")]), _c('switch', {
    staticClass: ["switch"],
    attrs: {
      "checked": _vm.disabled
    },
    on: {
      "change": function($event) {
        _vm.disabled = !_vm.disabled
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("按钮宽度")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "自定义样式实现, 默认全屏",
      "value": _vm.width
    },
    on: {
      "input": function($event) {
        _vm.width = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("按钮高度")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "自定义样式实现, 默认高度80px",
      "value": _vm.height
    },
    on: {
      "input": function($event) {
        _vm.height = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("背景颜色")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "自定义样式实现",
      "value": _vm.backgroundColor
    },
    on: {
      "input": function($event) {
        _vm.backgroundColor = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("边框颜色")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "自定义样式实现",
      "value": _vm.borderColor
    },
    on: {
      "input": function($event) {
        _vm.borderColor = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["list-input-item"]
  }, [_c('text', {
    staticClass: ["cell"]
  }, [_vm._v("边框圆角")]), _c('input', {
    staticClass: ["input"],
    attrs: {
      "placeholder": "自定义样式实现,默认8px",
      "value": _vm.borderRadius
    },
    on: {
      "input": function($event) {
        _vm.borderRadius = $event.value
      }
    }
  })]), _c('div', {
    staticClass: ["demo-item"]
  }, [_c('weiui_button', {
    staticClass: ["demoButton"],
    style: {
      width: _vm.width,
      height: _vm.height
    },
    attrs: {
      "weiui": _vm.demoStyle
    }
  })], 1)])], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);