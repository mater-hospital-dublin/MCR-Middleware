'use strict';

angular.module('rippleDemonstrator')
  .controller('BleedsListCtrl', function ($scope, $location, $stateParams, SearchInput, $modal, $state, usSpinnerService, PatientService, Bleed) {

    SearchInput.update();

    $scope.currentPage = 1;

    $scope.pageChangeHandler = function (newPage) {
      $scope.currentPage = newPage;
    }

    if ($stateParams.page) {
      $scope.currentPage = $stateParams.page;
    }

    $scope.search = function (row) {
      return (
        angular.lowercase(row.cause).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
        angular.lowercase(row.site).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
        angular.lowercase(row.dateRecorded).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
        angular.lowercase(row.source).indexOf(angular.lowercase($scope.query) || '') !== -1
      );
    };

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    if ($stateParams.filter) {
      $scope.query = $stateParams.filter;
    }

    Bleed.all($stateParams.patientId).then(function (result) {
      $scope.bleeds = result.data;

      for (var i = 0; i < $scope.bleeds.length; i++) {
        $scope.bleeds[i].dateRecorded = moment($scope.bleeds[i].dateRecorded).format('DD-MMM-YYYY');
      }

      usSpinnerService.stop('patientSummary-spinner');
    });

    $scope.go = function (id) {
      $state.go('bleeds-detail', {
        patientId: $scope.patient.id,
        bleedIndex: id,
        filter: $scope.query,
        page: $scope.currentPage,
        reportType: $stateParams.reportType,
        searchString: $stateParams.searchString,
        queryType: $stateParams.queryType
      });
    };

    $scope.selected = function (bleedIndex) {
      return bleedIndex === $stateParams.bleedIndex;
    };

    $scope.create = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/bleeds/bleed-modal.html',
        size: 'lg',
        controller: 'BleedsModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Create Bleed'
            };
          },
          bleed: function () {
            return {};
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (bleed) {
        bleed.dateRecorded = new Date(bleed.dateRecorded);

        var toAdd = {
          sourceId: '',
          cause: bleed.cause,
          dateRecorded : bleed.dateRecorded,
          pain: bleed.pain,
          severity: bleed.severity,
          site: bleed.site,
          type: bleed.type,
          source: bleed.source
        };

        Bleed.create($scope.patient.id, toAdd).then(function () {
          setTimeout(function () {
            $state.go('bleeds', {
              patientId: $scope.patient.id,
              filter: $scope.query,
              page: $scope.currentPage,
              reportType: $stateParams.reportType,
              searchString: $stateParams.searchString,
              queryType: $stateParams.queryType
            }, {
              reload: true
            });
          }, 2000);
        });
      });
    };

  });
