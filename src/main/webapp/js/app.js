var eliteMovieApp = angular.module('eliteMovieApp', [ 'ngRoute',
		'eliteMovieControllers' ]);

eliteMovieApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/movies', {
		templateUrl : 'partials/movie-selection.html',
		controller : 'IndexCtrl'
	}).when('/movies/:movieId', {
		templateUrl : 'partials/hour-selection.html',
		controller : 'HourSelectionCtrl'
	}).when('/seat_selection/:showtimeId/:numberOfSeats', {
		templateUrl : 'partials/seat-selection.html',
		controller : 'SeatSelectionCtrl'
	}).when('/confirmation/:transactionId', {
		templateUrl : 'partials/confirmation.html',
		controller : 'ConfirmationCtrl'
	}).otherwise({
		redirectTo : '/movies'
	});
} ]);