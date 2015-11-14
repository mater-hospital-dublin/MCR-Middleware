'use strict';

angular.module('rippleDemonstrator')
  .controller('BleedsModalCtrl', function ($scope, $modalInstance, PatientService, bleed, patient, modal) {

    $scope.currentUser = PatientService.getCurrentUser();
    $scope.bleed = bleed;
    $scope.patient = patient;
    $scope.modal = modal;

    if (modal.title === 'Edit Bleed'){
      $scope.bleed.dateRecorded = new Date($scope.bleed.dateRecorded).toISOString().slice(0, 10);

    }else {
      $scope.bleed.dateRecorded = new Date().toISOString().slice(0, 10);
    }

    $scope.ok = function (bleedForm, bleed) {
      $scope.formSubmitted = true;

      if (bleedForm.$valid) {
        $modalInstance.close(bleed);
      }
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };

    $scope.openDatepicker = function ($event, name) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope[name] = true;
    };

  });
