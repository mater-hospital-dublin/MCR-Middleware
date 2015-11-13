'use strict';

angular.module('rippleDemonstrator')
  .factory('Eq5l', function ($http) {

    var all = function (patientId) {
      return $http.get('/api/patients/' + patientId + '/eq5l');
    };

    var get = function (patientId, compositionId) {
      return $http.get('/api/patients/' + patientId + '/eq5l/' + compositionId);
    };

    var create = function (patientId, composition) {
      return $http.post('/api/patients/' + patientId + '/eq5l', composition);
    };

    var update = function (patientId, composition) {
      return $http.put('/api/patients/' + patientId + '/eq5l', composition);
    };

    return {
      all: all,
      get: get,
      update: update,
      create: create
    };

  });
