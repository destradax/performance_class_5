var eliteMovieControllers = angular.module('eliteMovieControllers', []);

eliteMovieControllers.controller('IndexCtrl', function ($scope, $http) {
	$http.get('rest/movie/').success(function(data) {
	    $scope.movies = data;
	  });
});

eliteMovieControllers.controller('HourSelectionCtrl', function ($scope, $http, $routeParams, $location) {
	var movieId = $routeParams.movieId;
	$http.get('rest/movie/' + movieId).success(function(data) {
	    $scope.movie = data;
	  });
	
	$scope.selectSeats = function(selection){
		if (selection.id == undefined){
			$scope.showTimeError = "Por favor seleccione la función";
		}
		else if (selection.seats == undefined){
			$scope.seatsError = "Por favor seleccione la cantidad de sillas";
		} else {
			
			$http.get('rest/showtime/available/' + selection.id).success(function(availableSeats) {
				if (parseInt(availableSeats) < selection.seats){
					alert("Solo hay " + availableSeats + " sillas disponibles.");
				} else {
					$location.path("/seat_selection/" + selection.id + "/" + selection.seats);
				}
			  });
			
		}
	};
});

eliteMovieControllers.controller('SeatSelectionCtrl', function ($scope, $http, $routeParams, $location) {
	var showtimeId = $routeParams.showtimeId;
	$scope.numberOfSeats = $routeParams.numberOfSeats;
	$scope.selectedSeats = [];

	$http.get('rest/showtime/' + showtimeId).success(function(data) {
	    $scope.showtime = data;
	    
		$http.get('rest/seatrecommendation/' + showtimeId + '/' + $scope.numberOfSeats).success(function(data) {
		    $scope.recommendations = data;
		    for (var i = 0; i < $scope.recommendations.length; i++){
		    	$scope.selectSeat($scope.recommendations[i].row, $scope.recommendations[i].column);
		    }
		});
	});
	
	$scope.deselectSeat = function(selectedRow,selectedColumn){
		$scope.showtime.seats[selectedRow][selectedColumn].checked = false;
		for (var i = 0; i < $scope.selectedSeats.length; i++){
			if ($scope.selectedSeats[i].row ==selectedRow && $scope.selectedSeats[i].column == selectedColumn){
				$scope.selectedSeats.splice(i, 1);
				return;
			}
		}
	}
	
	$scope.selectSeat = function(selectedRow,selectedColumn){
		if ($scope.showtime.seats[selectedRow][selectedColumn].checked == true){
			$scope.deselectSeat(selectedRow, selectedColumn);
			return;
		}
		
		if ($scope.selectedSeats.length < $scope.numberOfSeats){
			$scope.showtime.seats[selectedRow][selectedColumn].checked = true;
			$scope.selectedSeats.push({"row" : selectedRow, "column" : selectedColumn});
		} else {
			$scope.showtime.seats[selectedRow][selectedColumn].checked = false;
		}
	};
	
	$scope.isBooked = function(row,column){
		if ($scope.showtime.seats[row][column].booked == true){
			return true;
		}
		return false;
	};
	
	$scope.columnBetween = function(minColumn, maxColumn){
	    return function(item){
	      if (item.column >= minColumn && item.column <= maxColumn) return true;
	    }
	}
	
	$scope.submitSeats = function(){
		if ($scope.selectedSeats.length != $scope.numberOfSeats){
			alert("Por favor seleccione las " + $scope.numberOfSeats + " sillas antes de continuar.");
			return;
		}
		
		$http.post('rest/transaction/' + showtimeId, $scope.selectedSeats).success(function(data) {
			$location.path("/confirmation/" + data);
		}).error(function(data){
			alert("las sillas ya fueron seleccionadas por otro usuario. Por favor intentelo de nuevo");
			$scope.selectedSeats = [];
			$http.get('rest/showtime/' + showtimeId).success(function(data) {
			    $scope.showtime = data;
			});
		});
		
	}
});

eliteMovieControllers.controller('ConfirmationCtrl', function($scope, $http, $routeParams, $location){
	
	var transactionId = $routeParams.transactionId;

	$http.get('rest/transaction/' + transactionId).success(function(data) {
	    $scope.transaction = data;
	    $http.get('rest/movie/' + data.movieId).success(function(movieData) {
	    	$scope.movie = movieData;
	    });
	});
	
	$scope.endTransaction = function(){
		$location.path("/movies");
	}
});