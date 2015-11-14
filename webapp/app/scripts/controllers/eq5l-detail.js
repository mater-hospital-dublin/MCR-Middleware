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
      $scope.mobility = $scope.convertMobilityString($scope.eq5l.mobility);
      $scope.selfCare = $scope.convertSelfCareString($scope.eq5l.selfCare);
      $scope.usualActivities = $scope.convertActivitiesString($scope.eq5l.usualActivities);
      $scope.pain = $scope.convertPainString($scope.eq5l.pain);
      $scope.anxiety = $scope.convertAnxietyString($scope.eq5l.anxiety);
      usSpinnerService.stop('eq5lDetail-spinner');
    });

    $scope.convertMobilityString = function (s) {
      var toBeReturned = '';
      switch (s) {
      case 'No problems':
        toBeReturned = 'I have no problems doing my usual activities in walking about'
        break;
      case 'Slight problems':
        toBeReturned = 'I have slight problems in walking about';
        break;
      case 'Moderate problems':
        toBeReturned = 'procedures';
        break;
      case 'Severe problems':
        toBeReturned = 'medications';
        break;
      case 'Unable to walk about.':
        toBeReturned = 'I am unable to walk about';
        break;
      }
      return toBeReturned;
    };

    $scope.convertSelfCareString = function (s) {
      var toBeReturned = '';
      switch (s) {
      case 'No problems':
        toBeReturned = 'I have no problems washing or dressing myself'
        break;
      case 'Slight problems':
        toBeReturned = 'I have slight problems washing or dressing myself';
        break;
      case 'Moderate problems':
        toBeReturned = 'I have moderate problems washing or dressing myself';
        break;
      case 'Severe problems':
        toBeReturned = 'I have severe problems washing or dressing myself';
        break;
        case 'Unable to wash or dress':
        toBeReturned = 'I am unable to wash or dress myself';
        break;
      }
      return s;
    };

    $scope.convertActivitiesString = function (s) {
      var toBeReturned = '';
      switch (s) {
      case 'No problems':
        toBeReturned = 'I have no problems doing my usual activities'
        break;
      case 'Slight problems':
        toBeReturned = 'I have slight problems doing my usual activitiese';
        break;
      case 'Moderate problems':
        toBeReturned = 'I have moderate problems doing my usual activities';
        break;
      case 'Severe problems':
        toBeReturned = 'I have severe problems doing my usual activities';
        break;
        case 'Unable to do my usual activities':
        toBeReturned = 'I am unable to do my usual activities';
        break;
      }
      return toBeReturned;
    };


    $scope.convertPainString = function (s) {
      var toBeReturned = '';
      switch (s) {
      case 'No pain or discomfort':
        toBeReturned = 'I have no pain or discomfort'
        break;
      case 'Slight pain or discomfort':
        toBeReturned = 'I have slight problems doing my usual activitiese';
        break;
      case 'Moderate pain or discomfort':
        toBeReturned = 'I have moderate problems doing my usual activities';
        break;
      case 'Severe pain or discomfort':
        toBeReturned = 'I have severe problems doing my usual activities';
        break;
        case 'Extreme pain or discomfort':
        toBeReturned = 'I am unable to do my usual activities';
        break;
      }
      return toBeReturned;
    };


    $scope.convertAnxietyString = function (s) {
      var toBeReturned = '';
      switch (s) {
      case 'No anxiety or depression':
        toBeReturned = 'I am not anxious or depressed'
        break;
      case 'Slight anxiety or depression':
        toBeReturned = 'I am slightly anxious or depressed';
        break;
      case 'Moderate anxiety or depression':
        toBeReturned = 'I am moderately anxious or depressed';
        break;
      case 'Severe anxiety or depression':
        toBeReturned = 'I am severely anxious or depressed';
        break;
        case 'Extreme anxiety or depression':
        toBeReturned = 'I am extremely anxious or depressed';
        break;
      }
      return toBeReturned;
    };

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
