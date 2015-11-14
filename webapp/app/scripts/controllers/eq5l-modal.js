'use strict';

angular.module('rippleDemonstrator')
  .controller('Eq5lModalCtrl', function ($scope, $modalInstance, PatientService, eq5l, patient, modal) {

    $scope.currentUser = PatientService.getCurrentUser();
    $scope.eq5l = eq5l;
    $scope.patient = patient;
    $scope.modal = modal;

    if (modal.title === 'Edit EQ5L') {
      $scope.eq5l.dateRecorded = new Date($scope.eq5l.dateRecorded).toISOString().slice(0, 10);
    } else {
      $scope.eq5l.dateRecorded = new Date().toISOString().slice(0, 10);
      $scope.eq5l.lifeScore = 0;
    }

    $scope.ok = function (eq5lForm, eq5l) {
      $scope.formSubmitted = true;

      if (eq5lForm.$valid) {
        $modalInstance.close(eq5l);
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
