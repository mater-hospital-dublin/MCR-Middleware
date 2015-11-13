'use strict';

angular.module('rippleDemonstrator')
  .controller('Eq5lListCtrl', function ($scope, $state, $stateParams, SearchInput, $location, $modal, usSpinnerService, PatientService, Eq5l) {

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
        angular.lowercase(row.lifeScore.toString()).indexOf(angular.lowercase($scope.query) || '') !== -1 ||
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

    Eq5l.all($stateParams.patientId).then(function (result) {
      $scope.eq5ls = result.data;

      for (var i = 0; i < $scope.eq5ls.length; i++) {
        $scope.eq5ls[i].dateRecorded = moment($scope.eq5ls[i].dateRecorded).format('DD-MMM-YYYY');
      }
      usSpinnerService.stop('patientSummary-spinner');
    });

    $scope.go = function (id, eq5lSource) {
      $state.go('eq5l-detail', {
        patientId: $scope.patient.id,
        eq5lIndex: id,
        filter: $scope.query,
        page: $scope.currentPage,
        reportType: $stateParams.reportType,
        searchString: $stateParams.searchString,
        queryType: $stateParams.queryType,
        source: eq5lSource
      });
    };

    $scope.selected = function (eq5lIndex) {
      return eq5lIndex === $stateParams.eq5lIndex;
    };

    $scope.create = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/eq5l/eq5l-modal.html',
        size: 'md',
        controller: 'Eq5lModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Create Eq5l'
            };
          },
          eq5l: function () {
            return {};
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (eq5l) {
        eq5l.dateRecorded = new Date(eq5l.dateRecorded);

        var toAdd = {
          dateRecorded: eq5l.dateRecorded,
          anxiety: eq5l.anxiety,
          mobility: eq5l.mobility,
          pain: eq5l.pain,
          selfCare: eq5l.selfCare,
          usualActivities: eq5l.unsualActivities,
          lifeScore: eq5l.lifeScore,
          source: 'openehr',
          sourceId: ''
        };

        Eq5l.create($scope.patient.id, toAdd).then(function () {
          setTimeout(function () {
            $state.go('eq5l', {
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
