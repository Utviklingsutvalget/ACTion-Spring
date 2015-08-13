angular.module('action').controller('FileUploadController', function ($scope, Upload) {
    $scope.$watch('image', function () {
        $scope.upload($scope.image);
    });

    $scope.upload = function (image) {
        if (image) {
            Upload.upload({
                url: '/api/file/upload',
                file: image
            }).progress(function (evt) {
                $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
            }).success(function (data, status, headers, config) {
                config.image.id = data.id;
                config.image.url = data.url;
                $scope.image.error = undefined;
                $scope.progress = undefined;
            }).error(function (data) {
                $scope.progress = undefined;
                $scope.image.error = data;
            });
        }
    };
});