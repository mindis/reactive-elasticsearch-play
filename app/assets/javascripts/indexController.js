(function() {
    
    'use strict';
    
    angular.module('App').controller('IndexController', ['$scope', '$log', '$http', '$window', function ($scope, $log, $http, $window) {
        $scope.loading = false;
        $scope.actionsLoading = false;
        
        $scope.errorMessage = '';
        $scope.actionsValidationMessage = '';
    
        $scope.searchResults = [];
        $scope.searchString = '';
        
        $scope.startSearching = function () {
            if ($scope.searchString) {
                $scope.loading = true;
                $scope.errorMessage = '';
        		
                $http.get($window.hostName + '/search/by/query/' + $scope.searchString)
                .success(function(data) {
                    $log.debug(data);
                    $scope.searchResults = data;
                    $scope.loading = false;
    				
                    if (data.length < 1) {
                        $scope.errorMessage = 'Aucun document ne correspond aux termes de recherche spécifiés.'
                    }
                })
                .error(function(data, status, headers, config) {
                    $log.error(data);
                    $scope.loading = false;
                });
        	}
        }
        
        $scope.selectOrUnSelectAll = function () {
            angular.forEach($scope.searchResults, function (item) {
                item.selected = $scope.selectedAll;
            });
        }
        
        $scope.downloadFiles = function () {
            if ($scope.searchResults && $scope.searchResults.length > 0) {
                $scope.actionserrorMessage = '';
                
                var documentIds = '';
                angular.forEach($scope.searchResults, function (document) {
                    if (document.selected) {
                        documentIds = documentIds + document.id + ';'
                    }
                });
                
                if (documentIds && documentIds != '') {
                    $scope.actionsLoading = true;
                    
                    $http.get($window.hostName + '/file/download/by/ids/' + documentIds)
                    .success(function(data) {
                        $scope.actionsLoading = false;
                        $window.location = $window.hostName + '/file/download/zip';
                    })
                    .error(function(data, status, headers, config) {
                        $log.error(data);
                        $scope.actionsValidationMessage = 'Il y a une erreur pendant la création du zip.';
                        $scope.actionsLoading = false;
                    }); 
                } else {
                    $scope.actionsValidationMessage = 'Veuillez sélectionner au minimum un document.'
                }
            }
            
        }
    }]);
    
})();