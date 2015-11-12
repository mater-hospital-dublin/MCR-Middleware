'use strict';

angular.module('rippleDemonstrator')
  .controller('JointScoreModalCtrl', function ($scope, $modalInstance, PatientService, jointScore, patient, modal) {

    $scope.currentUser = PatientService.getCurrentUser();
    $scope.jointScore = jointScore;
    $scope.patient = patient;
    $scope.modal = modal;

    if (modal.title === 'Edit Joint Score') {
      $scope.jointScore.dateRecorded = new Date($scope.jointScore.dateRecorded).toISOString().slice(0, 10);
    } else {
      $scope.jointScore.dateRecorded = new Date().toISOString().slice(0, 10);
      $scope.jointScore.leftElbow = 0;
      $scope.jointScore.rightElbow = 0;
      $scope.jointScore.leftKnee = 0;
      $scope.jointScore.rightKnee = 0;
      $scope.jointScore.leftAnkle = 0;
      $scope.jointScore.rightAnkle = 0;
    }

    $scope.totalScore = $scope.jointScore.leftElbow +
      $scope.jointScore.rightElbow +
      $scope.jointScore.leftKnee +
      $scope.jointScore.rightKnee +
      $scope.jointScore.leftAnkle +
      $scope.jointScore.rightAnkle;


    $scope.ok = function (jointScoreForm, jointScore) {
      $scope.formSubmitted = true;

      if (jointScoreForm.$valid) {
        $modalInstance.close(jointScore);
      }
    };

    $scope.updateTotal = function () {
    $scope.totalScore = $scope.jointScore.leftElbow +
      $scope.jointScore.rightElbow +
      $scope.jointScore.leftKnee +
      $scope.jointScore.rightKnee +
      $scope.jointScore.leftAnkle +
      $scope.jointScore.rightAnkle;
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
