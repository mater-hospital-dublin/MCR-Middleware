'use strict';

angular.module('rippleDemonstrator')
  .factory('Bleed', function ($http) {

    var all = function (patientId) {
      return $http.get('/api/patients/' + patientId + '/bleeds/');
    };

    var get = function (patientId, compositionId) {
      return $http.get('/api/patients/' + patientId + '/bleeds/' + compositionId);
    };

    var create = function (patientId, composition) {
      return $http.post('/api/patients/' + patientId + '/bleeds', composition);
    };

    var update = function (patientId, composition) {
      return $http.put('/api/patients/' + patientId + '/bleeds', composition);
    };

    return {
      all: all,
      get: get,
      update: update,
      create: create
    };

  });
