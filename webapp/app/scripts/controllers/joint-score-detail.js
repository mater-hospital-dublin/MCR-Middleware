'use strict';

angular.module('rippleDemonstrator')
  .controller('JointScoreDetailCtrl', function ($scope, $stateParams, SearchInput, $modal, Helper, $state, $location, usSpinnerService, PatientService, JointScores) {

    SearchInput.update();

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    JointScores.get($stateParams.patientId, $stateParams.jointScoreIndex).then(function (result) {
      $scope.jointScore = result.data;
      $scope.dateRecorded = moment($scope.jointScore.dateRecorded).format('DD-MMM-YYYY');
      usSpinnerService.stop('jointScoreDetail-spinner');
    });

    $scope.edit = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/joint-score/joint-score-modal.html',
        size: 'md',
        controller: 'JointScoreModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Edit Joint Score'
            };
          },
          jointScore: function () {
            return angular.copy($scope.jointScore);
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (jointScore) {
        jointScore.dateRecorded = new Date(jointScore.dateRecorded);

        var toUpdate = {
          dateRecorded: jointScore.dateRecorded,
          leftAnkle: jointScore.leftAnkle,
          rightAnkle: jointScore.rightAnkle,
          leftElbow: jointScore.leftElbow,
          rightElbow: jointScore.rightElbow,
          leftKnee: jointScore.leftKnee,
          rightKnee: jointScore.rightKnee,
          totalScore: jointScore.totalScore,
          source: jointScore.source,
          sourceId: jointScore.sourceId
        };

        JointScores.update($scope.patient.id, toUpdate).then(function () {
          setTimeout(function () {
            $state.go('jointScore-detail', {
              patientId: $scope.patient.id,
              jointScoreIndex: Helper.updateId(jointScore.sourceId)
            });
          }, 2000);
        });
      });
    };

  });
