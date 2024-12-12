;(function(config) {
    const webpack = require('webpack');
    config.plugins.push(new webpack.NormalModuleReplacementPlugin(
        /^\.\/skiko.mjs$/,
        function(resource) { resource.request = "aaa-kilua-assets/index.js" }
    ));
})(config);
