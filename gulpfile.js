/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var gulp = require('gulp');
var sass = require('gulp-sass');
var watch = require('gulp-watch');
var livereload = require('gulp-livereload');
/**
 * watch-assets in the target task to use
 *  
 */

var pageURL = 'http://localhost:8080/app';


var webappLocation = 'src/main/webapp';
var jspPages = webappLocation + '/WEB-INF/views/**/*.jsp';
var sassFiles = "src/sass/**/*.scss";
var jsFiles = [webappLocation + "/js/**/*.js"]

gulp.task('watch-assets', function () {
    livereload.listen();
    watch(sassFiles, function (events, done) {

        gulp.src(sassFiles)
                .pipe(sass().on('error', sass.logError))
                //.pipe(concat('style.min.css'))
                //.pipe(uglifycss())
                .pipe(gulp.dest(webappLocation + "/css/main"))
                .on('finish', function ( ) {
                    console.log("processing change in css");
                    livereload.reload(pageURL);
                });
    });


    watch(jsFiles, function (events, done) {

        gulp.start(['refresh']);
    });


    watch(jspPages, function (events, done) {

        gulp.start(['refresh']);
    });
});


gulp.task('refresh', function () {
    console.log('refresh')
    livereload.reload(pageURL);

});


  