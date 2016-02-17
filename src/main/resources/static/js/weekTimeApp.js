/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("weekTimeApp", []);

app.service('lookupService', function($http) {
    
    this.getProjects = function() {return $http.get('/lookup/getProjects')};
    
    this.getTasksByProjectId = function(id) {return $http.get('/lookup/getTasksByProjectId/'+id)};
    
});

app.service('responseService', function(){
   
    this.success = function(){};
    this.failure = function(){alert('Error Updating Table')};
    
});

app.service('timeService', function($http, responseService) {
    
    this.timesheetSummary = function(userId, startDate) {
        var urlString = '/timesheet/getSummary/'+userId+'/'+startDate; 
        return $http.get(urlString); 
    };
    
    this.getDayDetail = function(userId, startDate){
        var url = '/timesheet/getEntriesForUserAndStartDate/'+userId+'/'+startDate;
        $http.get(url).success(function(data){
            return data;
            }
        );
    };
    
    this.getDayForUserAndTask = function(userId, taskId, index, startDate) {

        var urlString = '/timesheet/getEntriesForUserAndIndexAndTask/'+userId+'/'+taskId+'/'+index+'/'+startDate; 
        return $http.get(urlString); 
    };
    
    this.zeroDays = function(){
        
    }
    
    this.remove = function(userId, taskId){
        var urlString = '/entries/deleteSingle/'+userId+'/'+taskId; 
        $http.post(urlString, jsonOutput).
            then(function(response) {
              // this callback will be called asynchronously
              // when the response is available
              // updateTable(userId, startDate);
     
              return response.data;
            }, function(response) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
              responseService.failure();
            }); 
    };
    
    this.addDays = function(day1, day2, day3, day4, day5, day6, day7, startDate, userId, description, taskId) { 
        var url = '/entries/createAll/'+userId+'/'+taskId;
        debugger; 
        //var jsonData = buildWeekDays(day1, day2, day3, day4, day5, day6, day7, startDate, description); 
        var daysInput = [day1, day2, day3, day4, day5, day6, day7];
        var jsonOutput = [];
        for (i = 0; i < daysInput.length; i++) {
            
            //if(i == 0){
                //startDate = addDays(startDate, -1);
            //};
            
            if(i > 0){
                startDate = addDays(startDate, 1);
            };
            
            var day = buildDay(daysInput[i],startDate,description);

            if(day != null){
                jsonOutput.push(day);
                };
           };
        
        $http.post(url, jsonOutput).
            then(function(response) {
              // this callback will be called asynchronously
              // when the response is available
              // updateTable(userId, startDate);
     
              return response.data.weekSummary.weekSummary;
            }, function(response) {
              // called asynchronously if an error occurs
              // or server returns response with an error status.
              responseService.failure();
            });
            
           
    };
    
    function updateTable(userId, startDate){
        this.timesheetSummary(userId, startDate);
    }
    
    function addDays(theDate, days) {
        var returnDate = new Date(theDate.getTime() + days*24*60*60*1000);
        return returnDate;
    }
      
    function buildWeekDays(day1, day2, day3, day4, day5, day6, day7, startDate, description){
        debugger; 
        var daysInput = [day1, day2, day3, day4, day5, day6, day7];
        var jsonOutput = [];
        for (i = 0; i < daysInput.length; i++) {
            
            if(i>0){
                startDate.setDate(startDate.getDate()+i);
            };
            
            var day = buildDay(daysInput[i],startDate,description);

            if(day != null){
                jsonOutput.push(day);
                };
           };
           
        return jsonOutput;
    
    }
    
    function buildDay(value, date, description){
        var dateString = formatDate(date);
        if(value == null|| value == '0.0' || value =='0'){
            return null;
        }else
            return {	
                    "date":dateString,
                    "hours":value,
                    "reference":description
            };
    }
    
    function formatDate(date) {
        var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
        
    }
    
});


