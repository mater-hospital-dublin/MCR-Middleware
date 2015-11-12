'use strict';

angular.module('rippleDemonstrator')
  .controller('BleedsListCtrl', function ($scope, $location, $stateParams, SearchInput, $modal, $state, usSpinnerService, PatientService, Bleed) {

    $scope.query = {};
    $scope.queryBy = '$';
    SearchInput.update();


    $scope.currentPage = 1;

    $scope.pageChangeHandler = function (newPage) {
      $scope.currentPage = newPage;
    }

    if ($stateParams.page) {
      $scope.currentPage = $stateParams.page;
    }

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    if ($stateParams.filter) {
      $scope.query.$ = $stateParams.filter;
    }

    Bleed.all($stateParams.patientId).then(function (result) {
      $scope.bleeds = result.data;
      usSpinnerService.stop('patientSummary-spinner');
    });

    $scope.go = function (id) {
      $state.go('bleeds-detail', {
        patientId: $scope.patient.id,
        bleedIndex: id,
        filter: $scope.query.$,
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
          source: 'openehr'
        };

        Bleed.create($scope.patient.id, toAdd).then(function () {
          setTimeout(function () {
            $state.go('bleeds', {
              patientId: $scope.patient.id,
              filter: $scope.query.$,
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
