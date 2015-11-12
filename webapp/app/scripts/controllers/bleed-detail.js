'use strict';

angular.module('rippleDemonstrator')
  .controller('BleedsDetailCtrl', function ($scope, $stateParams, SearchInput, $modal, $location, $state, Helper, usSpinnerService, PatientService, Bleed) {

    SearchInput.update();

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    Bleed.get($stateParams.patientId, $stateParams.bleedIndex).then(function (result) {
      $scope.bleed = result.data;
      usSpinnerService.stop('bleedsDetail-spinner');
    });

    $scope.edit = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/bleeds/bleed-modal.html',
        size: 'lg',
        controller: 'BleedsModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Edit Bleed'
            };
          },
          bleed: function () {
            return angular.copy($scope.bleed);
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (bleed) {
        bleed.dateRecorded = new Date(bleed.dateRecorded);

        var toUpdate = {
          sourceId: bleed.sourceId,
          cause: bleed.cause,
          dateRecorded : bleed.dateRecorded,
          pain: bleed.pain,
          severity: bleed.severity,
          site: bleed.site,
          type: bleed.type,
          source: 'openehr'
        };

        Bleed.update($scope.patient.id, toUpdate).then(function () {
          setTimeout(function () {
            $state.go('bleeds-detail', {
              patientId: $scope.patient.id,
              bleedIndex: Helper.updateId(bleed.sourceId),
              page: $scope.currentPage,
              reportType: $stateParams.reportType,
              searchString: $stateParams.searchString,
              queryType: $stateParams.queryType
            });
          }, 2000);
        });
      });
    };

  });
