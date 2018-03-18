exports.install = (Vue, options) => {
    const global = {

        class2type: {},

        type(obj) {
            return obj == null ? String(obj) :
                this.class2type[toString.call(obj)] || "object"
        },

        isFunction(value) {
            return this.type(value) === "function"
        },

        isWindow(obj) {
            return obj != null && obj === obj.window
        },

        isDocument(obj) {
            return obj != null && obj.nodeType === obj.DOCUMENT_NODE
        },

        isObject(obj) {
            return this.type(obj) === "object"
        },

        isPlainObject(obj) {
            return this.isObject(obj) && !this.isWindow(obj) && Object.getPrototypeOf(obj) === Object.prototype
        },

        likeArray(obj) {
            return typeof obj.length === 'number'
        },

        /**
         * 遍历数组、对象
         * @param elements
         * @param callback
         * @returns {*}
         */
        each(elements, callback){
            let i, key;
            if (this.likeArray(elements)) {
                for (i = 0; i < elements.length; i++)
                    if (callback.call(elements[i], i, elements[i]) === false) return elements
            } else {
                for (key in elements)
                    if (callback.call(elements[key], key, elements[key]) === false) return elements
            }

            return elements
        },

        /**
         * 获取数组最后一个值
         * @param array
         * @returns {boolean}
         */
        last(array) {
            let str = false;
            if (typeof array === 'object' && array.length > 0) {
                str = array[array.length - 1];
            }
            return str;
        },


        /**
         * 字符串是否包含
         * @param string
         * @param find
         * @param lower
         * @returns {boolean}
         */
        strExists(string, find, lower) {
            string += "";
            find += "";
            if (lower !== true) {
                string = string.toLowerCase();
                find = find.toLowerCase();
            }
            return (string.indexOf(find) !== -1);
        },

        /**
         * 字符串是否左边包含
         * @param string
         * @param find
         * @param lower
         * @returns {boolean}
         */
        leftExists(string, find, lower) {
            string += "";
            find += "";
            if (lower !== true) {
                string = string.toLowerCase();
                find = find.toLowerCase();
            }
            return (string.substring(0, find.length) === find);
        },

        /**
         * 字符串是否右边包含
         * @param string
         * @param find
         * @param lower
         * @returns {boolean}
         */
        rightExists(string, find, lower) {
            string += "";
            find += "";
            if (lower !== true) {
                string = string.toLowerCase();
                find = find.toLowerCase();
            }
            return (string.substring(string.length - find.length) === find);
        },

        /**
         * 取字符串中间
         * @param string
         * @param start
         * @param end
         * @returns {*}
         */
        getMiddle(string, start, end) {
            if (this.ishave(start) && this.strexists(string, start)) {
                string = string.substring(string.indexOf(start) + start.length);
            }
            if (this.ishave(end) && this.strexists(string, end)) {
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
        subString(string, start, end) {
            string += "";
            if (!this.ishave(end)) {
                end = string.length;
            }
            return string.substring(start, end);
        },

        /**
         * 随机字符
         * @param len
         * @returns {string}
         */
        randomString(len) {
            len = len || 32;
            let $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678oOLl9gqVvUuI1';
            let maxPos = $chars.length;
            let pwd = '';
            for (let i = 0; i < len; i++) {
                pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
            }
            return pwd;
        },

        /**
         * 判断是否有
         * @param set
         * @returns {boolean}
         */
        ishave(set) {
            return !!(set !== null && set !== "null" && set !== undefined && set !== "undefined" && set);
        },

        /**
         * 相当于 intval
         * @param str
         * @param fixed
         * @returns {number}
         */
        runNum(str, fixed) {
            let _s = Number(str);
            if (_s + "" === "NaN") {
                _s = 0;
            }
            if (/^[0-9]*[1-9][0-9]*$/.test(fixed)) {
                _s = _s.toFixed(fixed);
                let rs = _s.indexOf('.');
                if (rs < 0) {
                    _s += ".";
                    for (let i = 0; i < fixed; i++) {
                        _s += "0";
                    }
                }
            }
            return _s;
        },

        /**
         * 补零
         * @param str
         * @param length
         * @param after
         * @returns {*}
         */
        zeroFill(str, length, after) {
            str+= "";
            if (str.length >= length) {
                return str;
            }
            let _str = '', _ret = '';
            for (let i = 0; i < length; i++) {
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
        formatDate(format, v) {
            if (format === '') {
                format = 'Y-m-d H:i:s';
            }
            if (typeof v === 'undefined') {
                v = new Date().getTime();
            }else if (/^(-)?\d{1,10}$/.test(v)) {
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
            let dateObj = new Date(v);
            if (parseInt(dateObj.getFullYear()) + "" === "NaN") { return v; }
            //
            format = format.replace(/Y/g, dateObj.getFullYear());
            format = format.replace(/m/g, this.zeroFill(dateObj.getMonth() + 1, 2));
            format = format.replace(/d/g, this.zeroFill(dateObj.getDate(), 2));
            format = format.replace(/H/g, this.zeroFill(dateObj.getHours(), 2));
            format = format.replace(/i/g, this.zeroFill(dateObj.getMinutes(), 2));
            format = format.replace(/s/g, this.zeroFill(dateObj.getSeconds(), 2));
            return format;
        },

        /**
         * 检测手机号码格式
         * @param str
         * @returns {boolean}
         */
        isMobile(str) {
            return /^1(3|4|5|7|8)\d{9}$/.test(str);
        },

        /**
         * 是否手机号码
         * @param phone
         * @returns {boolean}
         */
        isPhone(phone) {
            return this.isMobile(phone);
        },

        /**
         * 根据两点间的经纬度计算距离
         * @param lng1
         * @param lat1
         * @param lng2
         * @param lat2
         * @returns {string|*}
         */
        getDistance(lng1, lat1, lng2, lat2) {
            let DEF_PI = 3.14159265359;         // PI
            let DEF_2PI = 6.28318530712;        // 2*PI
            let DEF_PI180 = 0.01745329252;      // PI/180.0
            let DEF_R = 6370693.5;              // radius of earth
            //
            let ew1, ns1, ew2, ns2;
            let dx, dy, dew;
            let distance;
            // 角度转换为弧度
            ew1 = lng1 * DEF_PI180;
            ns1 = lat1 * DEF_PI180;
            ew2 = lng2 * DEF_PI180;
            ns2 = lat2 * DEF_PI180;
            // 经度差
            dew = ew1 - ew2;
            // 若跨东经和西经180 度，进行调整
            if (dew > DEF_PI)
                dew = DEF_2PI - dew;
            else if (dew < -DEF_PI)
                dew = DEF_2PI + dew;
            dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
            dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
            // 勾股定理求斜边长
            distance = Math.sqrt(dx * dx + dy * dy).toFixed(0);
            return distance;
        },

        /**
         * 设置网页标题
         * @param title
         */
        setTile(title) {
            document.title = title;
            let mobile = navigator.userAgent.toLowerCase();
            if (/iphone|ipad|ipod/.test(mobile)) {
                let iframe = document.createElement('iframe');
                iframe.style.display = 'none';
                iframe.setAttribute('src', '/favicon.ico');
                let iframeCallback = () => {
                    setTimeout(() => {
                        iframe.removeEventListener('load', iframeCallback);
                        document.body.removeChild(iframe)
                    }, 0)
                };
                iframe.addEventListener('load', iframeCallback);
                document.body.appendChild(iframe)
            }
        },

        /**
         * 克隆对象
         * @param myObj
         * @returns {*}
         */
        clone(myObj) {
            if(typeof(myObj) !== 'object') return myObj;
            if(myObj === null) return myObj;
            //
            if (this.likeArray(myObj)) {
                let [ ...myNewObj ] = myObj;
                return myNewObj;
            }else{
                let { ...myNewObj } = myObj;
                return myNewObj;
            }
        },

        /**
         * 是否IOS
         * @returns {boolean|string}
         */
        isIos() {
            let ua = typeof window !== 'undefined' && window.navigator.userAgent.toLowerCase();
            return ua && /iphone|ipad|ipod|ios/.test(ua);
        },

        /**
         * 是否安卓
         * @returns {boolean|string}
         */
        isAndroid() {
            let ua = typeof window !== 'undefined' && window.navigator.userAgent.toLowerCase();
            return ua && ua.indexOf('android') > 0;
        },

        /**
         * 是否微信
         * @returns {boolean}
         */
        isWeixin() {
            let ua = typeof window !== 'undefined' && window.navigator.userAgent.toLowerCase();
            return (ua.match(/MicroMessenger/i) + '' === 'micromessenger');
        },

        /**
         * 统计数组或对象长度
         * @param obj
         * @returns {number}
         */
        count(obj) {
            if (typeof obj.length === 'number') {
                return obj.length;
            } else {
                let i = 0, key;
                for (key in obj) {
                    i++;
                }
                return i;
            }
        },

        /**
         * 将数组或对象内容部分拼成字符串
         * @param obj
         * @returns {string}
         */
        objImplode(obj) {
            let i, key, val = '';
            if (typeof obj !== "object" || obj === null) {
                return "";
            }
            if (typeof obj.length === 'number') {
                for (i = 0; i < obj.length; i++) {
                    val+= typeof obj[i] === 'object'?this.objImplode(obj[i]):(obj[i] + '');
                }
            } else{
                for (key in obj) {
                    if (obj.hasOwnProperty(key)) {
                        val+= typeof obj[key] === 'object'?this.objImplode(obj[key]):(obj[key] + '');
                    }
                }
            }
            return val.replace(/\s/g, "");
        },

        /**
         * 指定键获取hash参数
         * @param key
         * @returns {*}
         */
        hashParameter(key) {
            let arr = (location.hash || "").replace(/^\#/, '').split("&");
            let params = {};
            for (let i = 0; i < arr.length; i++) {
                let data = arr[i].split("=");
                if (data.length === 2) {
                    params[data[0]] = data[1];
                }
            }
            return params[key];
        },
    };

    global.each("Boolean Number String Function Array Date RegExp Object Error".split(" "), (i, name) => {
        global.class2type["[object " + name + "]"] = name.toLowerCase();
    });

    global.each(global, (index, element) => {
        Vue.prototype[index] = element;
    });
};