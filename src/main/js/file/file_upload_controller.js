angular.module('action').controller('FileUploadController', ['$scope', 'Upload', function ($scope, Upload) {
    $scope.$watch('image', function () {
        $scope.upload($scope.image);
    });
    $scope.$watch('$parent.$parent.setImage', function() {
        $scope.display = $scope.$parent.$parent.setImage;
    });

    $scope.upload = function (image) {
        console.log(image);
        if (image) {
            Upload.upload({
                url: '/api/file/upload',
                file: image
            }).progress(function (evt) {
                $scope.progress = parseInt(100.0 * evt.loaded / evt.total);
            }).success(function (data, status, headers, config) {
                $scope.$parent.$parent.image = {};
                $scope.$parent.$parent.image.id = data.id;
                $scope.$parent.$parent.image.url = data.url;
                $scope.display = $scope.$parent.$parent.image;
                delete $scope.error;
                delete $scope.progress;
            }).error(function (data) {
                delete $scope.progress;
                $scope.error = data;
            });
        }
    };
}]);