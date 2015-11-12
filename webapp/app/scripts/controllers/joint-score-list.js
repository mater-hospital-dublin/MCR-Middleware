'use strict';

angular.module('rippleDemonstrator')
  .controller('JointScoreListCtrl', function ($scope, $state, $stateParams, SearchInput, $location, $modal, usSpinnerService, PatientService, JointScores) {

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
        angular.lowercase(row.totalScore.toString()).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
        angular.lowercase(row.dateRecorded).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
        angular.lowercase(row.source).indexOf(angular.lowercase($scope.query) || '') !== -1
      );
    };

    if ($stateParams.filter) {
      $scope.query = $stateParams.filter;
    }

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    JointScores.all($stateParams.patientId).then(function (result) {
      $scope.jointScores = result.data;

      for (var i = 0; i < $scope.jointScores.length; i++) {
        $scope.jointScores[i].dateRecorded = moment($scope.jointScores[i].dateRecorded).format('DD-MMM-YYYY');
      }
      usSpinnerService.stop('patientSummary-spinner');
    });

    $scope.go = function (id, jointScoreSource) {
      $state.go('jointScore-detail', {
        patientId: $scope.patient.id,
        jointScoreIndex: id,
        filter: $scope.query,
        page: $scope.currentPage,
        reportType: $stateParams.reportType,
        searchString: $stateParams.searchString,
        queryType: $stateParams.queryType,
        source: jointScoreSource
      });
    };

    $scope.selected = function (jointScoreIndex) {
      return jointScoreIndex === $stateParams.jointScoreIndex;
    };

    $scope.create = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/joint-score/joint-score-modal.html',
        size: 'md',
        controller: 'JointScoreModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Create Joint Score'
            };
          },
          jointScore: function () {
            return {};
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (jointScore) {
        jointScore.dateRecorded = new Date(jointScore.dateRecorded);

        var toAdd = {
          dateRecorded: jointScore.dateRecorded,
          leftAnkle: jointScore.leftAnkle,
          rightAnkle: jointScore.rightAnkle,
          leftElbow: jointScore.leftElbow,
          rightElbow: jointScore.rightElbow,
          leftKnee: jointScore.leftKnee,
          rightKnee: jointScore.rightKnee,
          totalScore: jointScore.totalScore,
          source: 'openehr',
          sourceId: ''
        };

        JointScores.create($scope.patient.id, toAdd).then(function () {
          setTimeout(function () {
            $state.go('jointScore', {
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
