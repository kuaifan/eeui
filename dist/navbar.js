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
/******/ 	return __webpack_require__(__webpack_require__.s = 8);
/******/ })
/************************************************************************/
/******/ ({

/***/ 10:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('weiui_navbar', {
    staticClass: ["navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', [_vm._v("标题")])])], 1), _c('weiui_navbar', {
    staticClass: ["navbar"],
    attrs: {
      "weiui": {
        titleType: 'left'
      }
    }
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', [_vm._v("标题")])])], 1), _c('weiui_navbar', {
    staticClass: ["navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', [_vm._v("标题")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    }
  }, [_c('weiui_icon', {
    staticClass: ["icon"],
    attrs: {
      "icon": "refresh"
    }
  })], 1)], 1), _c('weiui_navbar', {
    staticClass: ["navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "left"
    }
  }, [_c('weiui_icon', {
    staticClass: ["icon"],
    attrs: {
      "icon": "navicon-round"
    }
  })], 1), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', [_vm._v("标题")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    }
  }, [_c('weiui_icon', {
    staticClass: ["icon"],
    attrs: {
      "icon": "refresh"
    }
  })], 1)], 1), _c('weiui_navbar', {
    staticClass: ["navbar"]
  }, [_c('weiui_navbar_item', {
    attrs: {
      "type": "back"
    }
  }), _c('weiui_navbar_item', {
    attrs: {
      "type": "left"
    }
  }, [_c('text', [_vm._v("左边内容")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "title"
    }
  }, [_c('text', [_vm._v("中间(标题)内容")])]), _c('weiui_navbar_item', {
    attrs: {
      "type": "right"
    }
  }, [_c('text', [_vm._v("右边内容")])])], 1)], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ }),

/***/ 8:
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(9)
)

/* template */
var __vue_template__ = __webpack_require__(10)
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
__vue_options__.__file = "/Users/WEIDONGMING/wwwroot/weex_weiui/src/navbar.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-715d13c0"
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

/***/ 9:
/***/ (function(module, exports) {

module.exports = {
  "navbar": {
    "width": "750",
    "height": "100",
    "marginBottom": "50"
  },
  "icon": {
    "width": "100",
    "height": "100"
  }
}

/***/ })

/******/ });