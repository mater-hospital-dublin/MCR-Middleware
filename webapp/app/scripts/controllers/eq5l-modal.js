'use strict';

angular.module('rippleDemonstrator')
  .controller('Eq5lModalCtrl', function ($scope, $modalInstance, PatientService, eq5l, patient, modal) {

    $scope.currentUser = PatientService.getCurrentUser();
    $scope.eq5l = eq5l;
    $scope.patient = patient;
    $scope.modal = modal;

    $scope.MobilityOptions = [
      'I have no problems in walking about',
      'I have slight problems in walking about',
      'I have moderate problems in walking about',
      'I have severe problems in walking about',
      'I am unable to walk about',
      ];

    $scope.SelfCareOptions = [
      'I have no problems washing or dressing myself',
      'I have slight problems washing or dressing myself',
      'I have moderate problems washing or dressing myself',
      'I have severe problems washing or dressing myself',
      'I am unable to wash or dress myself'
      ];

    $scope.ActivitiesOptions = [
      'I have no problems doing my usual activities',
      'I have slight problems doing my usual activities',
      'I have moderate problems doing my usual activities',
      'I have severe problems doing my usual activities',
      'I am unable to do my usual activities'
      ];

    $scope.PainOptions = [
      'I have no pain or discomfort',
      'I have slight pain or discomfort',
      'I have moderate pain or discomfort',
      'I have severe pain or discomfort',
      'I have extreme pain or discomfort'
      ];

    $scope.AnxietyOptions = [
      'I am not anxious or depressed',
      'I am slightly anxious or depressed',
      'I am moderately anxious or depressed',
      'I am severely anxious or depressed',
      'I am extremely anxious or depressed'
      ];

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
