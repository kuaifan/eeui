const commonConfig = require('./webpack.common.conf');
const webpackMerge = require('webpack-merge'); // used to merge webpack configs
// tools
const chalk = require('chalk');
const path = require('path');
const webpack = require('webpack');
const ip = require('ip').address();

/**
 * Webpack Plugins
 */
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ScriptExtHtmlWebpackPlugin = require('script-ext-html-webpack-plugin');
const FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin');
const portfinder = require('portfinder');

const config = require('./config');
const utils = require('./utils');
const helper = require('./helper');

/**
 * Generate multiple entrys
 * @param {Array} entry
 */
const generateHtmlWebpackPlugin = (entry) => {
    const entrys = Object.keys(entry);
    const htmlPlugin = entrys.map(name => {

        return new HtmlWebpackPlugin({
            filename: name + '.html',
            template: helper.rootNode(`web/index.html`),
            isDevServer: true,
            chunksSortMode: 'dependency',
            inject: true,
            chunks: [name]
        })
    });
    return htmlPlugin;
};

/**
 * Webpack configuration for browser.
 */
const devWebpackConfig = webpackMerge(commonConfig[0], {
    /*
     * Options affecting the resolving of modules.
     *
     * See: http://webpack.github.io/docs/configuration.html#module
     */
    module: {
        rules: utils.styleLoaders({sourceMap: config.dev.cssSourceMap, usePostCSS: true})
    },
    /**
     * Developer tool to enhance debugging
     *
     * See: http://webpack.github.io/docs/configuration.html#devtool
     * See: https://github.com/webpack/docs/wiki/build-performance#sourcemaps
     */
    devtool: config.dev.devtool,
    /*
     * Add additional plugins to the compiler.
     *
     * See: http://webpack.github.io/docs/configuration.html#plugins
     */
    plugins: [
        /**
         * Plugin: webpack.DefinePlugin
         * Description: The DefinePlugin allows you to create global constants which can be configured at compile time.
         *
         * See: https://webpack.js.org/plugins/define-plugin/
         */
        new webpack.DefinePlugin({
            'process.env': {
                'NODE_ENV': config.dev.env
            }
        }),
        /*
         * Plugin: HtmlWebpackPlugin
         * Description: Simplifies creation of HTML files to serve your webpack bundles.
         * This is especially useful for webpack bundles that include a hash in the filename
         * which changes every compilation.
         *
         * See: https://github.com/ampedandwired/html-webpack-plugin
         */
        ...generateHtmlWebpackPlugin(commonConfig[0].entry),
        /*
         * Plugin: ScriptExtHtmlWebpackPlugin
         * Description: Enhances html-webpack-plugin functionality
         * with different deployment options for your scripts including:
         *
         * See: https://github.com/numical/script-ext-html-webpack-plugin
         */
        new ScriptExtHtmlWebpackPlugin({
            defaultAttribute: 'defer'
        })
    ],
    /**
     * Webpack Development Server configuration
     * Description: The webpack-dev-server is a little node.js Express server.
     * The server emits information about the compilation state to the client,
     * which reacts to those events.
     *
     * See: https://webpack.github.io/docs/webpack-dev-server.html
     */
    devServer: {
        clientLogLevel: 'warning',
        compress: true,
        contentBase: config.dev.contentBase,
        host: config.dev.host,
        port: config.dev.port,
        historyApiFallback: config.dev.historyApiFallback,
        public: config.dev.public,
        open: config.dev.open,
        watchContentBase: config.dev.watchContentBase,
        overlay: config.dev.errorOverlay
            ? {warnings: false, errors: true}
            : false,
        proxy: config.dev.proxyTable,
        quiet: true, // necessary for FriendlyErrorsPlugin
        openPage: config.dev.openPage,
        watchOptions: config.dev.watchOptions
    }
});

/**
 * Webpack configuration for weex.
 */
const weexConfig = webpackMerge(commonConfig[1], {
    watch: true
});

// build source to weex_bundle with watch mode.
webpack(weexConfig, (err, stats) => {
    if (err) {
        console.err('COMPILE ERROR:', err.stack)
    }
});

module.exports = new Promise((resolve, reject) => {
    portfinder.basePort = process.env.PORT || config.dev.port;
    portfinder.getPort((err, port) => {
        if (err) {
            reject(err)
        } else {
            // publish the new Port, necessary for e2e tests
            process.env.PORT = port;
            // add port to devServer config
            devWebpackConfig.devServer.port = port;
            devWebpackConfig.devServer.public = `${ip}:${port}`;
            // Add FriendlyErrorsPlugin
            devWebpackConfig.plugins.push(new FriendlyErrorsPlugin({
                compilationSuccessInfo: {
                    messages: [`Your application is running here: http://${devWebpackConfig.devServer.host}:${port}`],
                },
                onErrors: config.dev.notifyOnErrors
                    ? utils.createNotifierCallback()
                    : undefined
            }));

            resolve(devWebpackConfig)
        }
    })
});
