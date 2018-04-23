let weiui = weex.requireModule('weiui');

let app = {

    openViewCode(str) {
        weiui.openPage({
            url: "http://kuaifan.vip/weiui/#/" + str,
            pageType: 'web'
        });
    },

};

module.exports = app;