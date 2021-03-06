'use strict';
var gulp = require('gulp');
var concat = require('gulp-concat');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var minifyCss = require('gulp-minify-css');
var cached = require('gulp-cached');
var remember = require('gulp-remember');
var sass = require('gulp-sass');

gulp.task('default', ['scripts', 'sass', 'watch']);

var sources = [
    'node_modules/jquery/dist/jquery.js',
    'node_modules/moment/moment.js',
    'node_modules/moment/locale/nb.js',
    'node_modules/moment-timezone/moment-timezone.js',
    'node_modules/showdown/src/showdown.js',
    'node_modules/angular/angular.js',
    'node_modules/angular-route/angular-route.js',
    'node_modules/angular-sanitize/angular-sanitize.js',
    'node_modules/angular-markdown-directive/markdown.js',
    'node_modules/angular-bootstrap-datetimepicker/src/js/datetimepicker.js',
    'node_modules/angular-moment/angular-moment.js',
    'node_modules/angular-filter/dist/angular-filter.js',
    'node_modules/ngstorage/ngStorage.js',
    'node_modules/oauth-ng/dist/oauth-ng.js',
    'node_modules/angular/angular.min.js',
    'node_modules/ng-file-upload/dist/ng-file-upload-all.min.js',
    'node_modules/foundation-sites/js/vendor/modernizr.js',
    'node_modules/foundation-sites/js/foundation.js',

    'src/main/js/**/*.js'
];

var internalSources = [
    'src/main/js/**/*.js'
];

var styles = [
    // Internal sources last
    'src/main/styles/main.scss'
];
var scriptsDest = 'src/main/resources/assets/js/';
var stylesDest = 'src/main/resources/assets/styles/';

gulp.task('lint', function () {
    return gulp.src(internalSources)
        .pipe(jshint())
        .pipe(jshint.reporter('default'));

});

gulp.task('sass', function () {
    gulp.src(styles)
        .pipe(sass())
        .pipe(gulp.dest(stylesDest))
        .pipe(concat("main.css"))
        .pipe(gulp.dest(stylesDest));
});

gulp.task('scripts', ['lint'], function () {
    return gulp.src(sources)
        .pipe(cached('scripts'))        // only pass through changed files
        .pipe(remember('scripts'))      // add back all files to the stream
        .pipe(concat('app.js'))         // do things that require all files
        .pipe(gulp.dest(scriptsDest));
});

gulp.task('dist', ['distStyle', 'distScripts']);

gulp.task('distScripts', ['lint'], function () {
    return gulp.src(sources)
        .pipe(uglify())
        .pipe(concat('app.js'))
        .pipe(gulp.dest(scriptsDest));
});

gulp.task('distStyle', function () {
    gulp.src(styles)
        .pipe(sass())
        .pipe(gulp.dest(stylesDest))
        .pipe(concat("main.css"))
        .pipe(minifyCss({compatibility: 'ie8'}))
        .pipe(gulp.dest(stylesDest));
});

gulp.task('watch', ['scripts', 'sass'], function () {
    var scriptWatcher = gulp.watch(internalSources, ['scripts']);

    scriptWatcher.on('change', function (event) {
        if (event.type === 'deleted') {
            delete cached.caches.scripts[event.path];
            remember.forget('scripts', event.path);
        }
    });

    var styleWatcher = gulp.watch(styles, ['sass']);

    styleWatcher.on('change', function (event) {
        if (event.type === 'deleted') {
            delete cached.caches.sass[event.path];
            remember.forget('sass', event.path);
        }
    });
});