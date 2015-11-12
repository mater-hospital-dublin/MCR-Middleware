'use strict';

angular.module('rippleDemonstrator')
  .factory('JointScores', function ($http) {

    var all = function (patientId) {
      return $http.get('/api/patients/' + patientId + '/jointScores');
    };

    var get = function (patientId, compositionId) {
      return $http.get('/api/patients/' + patientId + '/jointScores/' + compositionId);
    };

    var create = function (patientId, composition) {
      return $http.post('/api/patients/' + patientId + '/jointScores', composition);
    };

    var update = function (patientId, composition) {
      return $http.put('/api/patients/' + patientId + '/jointScores', composition);
    };

    return {
      all: all,
      get: get,
      update: update,
      create: create
    };

  });
