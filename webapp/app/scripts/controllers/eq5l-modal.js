'use strict';

angular.module('rippleDemonstrator')
  .controller('Eq5lModalCtrl', function ($scope, $modalInstance, PatientService, eq5l, patient, modal) {

    $scope.currentUser = PatientService.getCurrentUser();
    $scope.eq5l = eq5l;
    $scope.patient = patient;
    $scope.modal = modal;

    $scope.MobilityOptions = [
      'I have no problems',
      'trembling',
      'Slight weakness',
      ];

    $scope.SelfCareOptions = [
      'I have no problems',
      'I have slight problems',
      'I am unable to care for myself'
      ];

    $scope.ActivitiesOptions = [
      'I have no problems',
      'I am unable',
      'I have slight problem with activities',
      'I am unable to carry out any activities'
      ];

    $scope.PainOptions = [
      'I have no pain',
      'I have no discomfort',
      'I have no discomfort or pain',
      'I have mild pain',
      'I have mild discomfort',
      'I am under intense pain'
      ];

    $scope.AnxietyOptions = [
      'I have no anxiety',
      'I have slight problem with anxiety',
      'I have a minor problem with anxiety',
      'I have severe anxiety'
      ];

    if (modal.title === 'Edit Joint Score') {
      $scope.eq5l.dateRecorded = new Date($scope.eq5l.dateRecorded).toISOString().slice(0, 10);
    } else {
      $scope.eq5l.dateRecorded = new Date().toISOString().slice(0, 10);
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
