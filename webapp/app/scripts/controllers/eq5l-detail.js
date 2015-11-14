'use strict';

angular.module('rippleDemonstrator')
  .controller('Eq5lDetailCtrl', function ($scope, $stateParams, SearchInput, $modal, Helper, $state, $location, usSpinnerService, PatientService, Eq5l) {

    SearchInput.update();

    PatientService.get($stateParams.patientId).then(function (patient) {
      $scope.patient = patient;
    });

    Eq5l.get($stateParams.patientId, $stateParams.eq5lIndex).then(function (result) {
      $scope.eq5l = result.data;
      $scope.dateRecorded = moment($scope.eq5l.dateRecorded).format('DD-MMM-YYYY');
      usSpinnerService.stop('eq5lDetail-spinner');
    });

    $scope.edit = function () {
      var modalInstance = $modal.open({
        templateUrl: 'views/eq5l/eq5l-modal.html',
        size: 'md',
        controller: 'Eq5lModalCtrl',
        resolve: {
          modal: function () {
            return {
              title: 'Edit EQ5L'
            };
          },
          eq5l: function () {
            return angular.copy($scope.eq5l);
          },
          patient: function () {
            return $scope.patient;
          }
        }
      });

      modalInstance.result.then(function (eq5l) {
        eq5l.dateRecorded = new Date(eq5l.dateRecorded);

        var toUpdate = {
          dateRecorded: eq5l.dateRecorded,
          anxiety: eq5l.anxiety,
          mobility: eq5l.mobility,
          pain: eq5l.pain,
          selfCare: eq5l.selfCare,
          usualActivities: eq5l.usualActivities,
          lifeScore: eq5l.lifeScore,
          source: 'openehr',
          sourceId: eq5l.sourceId
        };

        Eq5l.update($scope.patient.id, toUpdate).then(function () {
          setTimeout(function () {
            $state.go('eq5l-detail', {
              patientId: $scope.patient.id,
              eq5lIndex: Helper.updateId(eq5l.sourceId)
            });
          }, 2000);
        });
      });
    };

  });
