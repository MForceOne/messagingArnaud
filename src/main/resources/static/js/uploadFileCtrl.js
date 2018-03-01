// CONTROLLER UPLOAD FILE
igApp.controller('uploadFileController', ['$scope', '$http', function($scope, $http){
    $scope.doUploadFile = function(){
       var file = $scope.uploadedFile;
       var url = "/api/uploadfile";
       var brokerDetails = {
    		   url: $scope.broker.url,
    		   username: $scope.broker.username,
    		   password: $scope.broker.password,
    		   destination: $scope.broker.destination,
    		   topic: $scope.broker.topic
    		};
       var data = new FormData();
       data.append('uploadfile', file);
       data.append('brokerDetails', JSON.stringify(brokerDetails));

       var config = {
    	   	transformRequest: angular.identity,
    	   	transformResponse: angular.identity,
	   		headers : {
	   			'Content-Type': undefined
	   	    }
       }
       
       $http.post(url, data, config).then(function (response) {
			$scope.uploadResult=response.data;
		}, function (response) {
			$scope.uploadResult=response.data;
		});
    };
}]);
