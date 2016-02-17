/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

app.controller("weekTimeCtrl", function($scope, lookupService, timeService) {
    //$scope.message = "";
    //$scope.left  = function() {return 100 - $scope.message.length;};
    //$scope.clear = function() {$scope.message = "";};
    
    //$http.get('/lookup/getProjects').success(function (response) {$scope.projectData = response;});
    //lookupService.getProjects(function(data){
     //  $scope.projectData = data; 
   // });
    
    lookupService.getProjects().success(function(data){
        $scope.projectData = data;
    });
    
    $scope.projectSelectedValue = '0';
    $scope.taskSelectedValue = '0';
    
    $scope.taskDescription = '';
    $scope.day1 = '0';
    $scope.day2 = '0';
    $scope.day3 = '0';
    $scope.day4 = '0';
    $scope.day5 = '0';
    $scope.day6 = '0';
    $scope.day7 = '0'; 
    $scope.daySelected = null;
        
    var pathArray = window.location.pathname.split( '/' );
    
    timeService.timesheetSummary(pathArray[2], pathArray[3]).success(function(data){
        // DATE PROBLEMS START HERE !!!!!!!!!!!!!!!!!!!!!!
        $scope.startDate = new Date(data.startDate);
        $scope.userName = data.userName;
        $scope.userId = pathArray[2];
        $scope.projectSummaries = data.weekRows;

    });
    
    $scope.showDay = false;
    
       
    $scope.getDay = function(user,task,index){
        
         timeService.getDayForUserAndTask(user, task, index, pathArray[3]).success(function(data){
            $scope.dayData = data;
            $scope.showDay = true;
            $scope.daySelected = index;
        });
       
    };
    
    $scope.remove = function(user,entry){
        
        timeService.remove(user,entry).success(function(data){
            $scope.dayData = data.daySummary.entries;
            $scope.projectSummaries = data.weekSummary.weekSummary;
        });
        
        
        
    };
    
    $scope.updateTasks = function(selectedValue){
        //lookupService.getTasksById($scope.projectSelectedValue * 1);
        lookupService.getTasksByProjectId(selectedValue).success(function(data){
            $scope.taskData = data;
        });
    };
    
    $scope.zeroValues = function(){
        $scope.projectSelectedValue = '0';
        $scope.taskSelectedValue = '0';
    
        $scope.taskDescription = '';
        $scope.day1 = '0';
        $scope.day2 = '0';
        $scope.day3 = '0';
        $scope.day4 = '0';
        $scope.day5 = '0';
        $scope.day6 = '0';
        $scope.day7 = '0'; 
        $scope.timeEntryForm.$setUntouched();
        
    };
    
    $scope.save = function() {
        $scope.projectSummaries = timeService.addDays($scope.day1, $scope.day2, $scope.day3, $scope.day4, $scope.day5, $scope.day6,  $scope.day7, $scope.startDate, $scope.userId, $scope.taskDescription, $scope.taskSelectedValue);
        
    };

});