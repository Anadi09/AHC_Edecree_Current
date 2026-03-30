<form class="form-horizontal reduce-gap" name="uploadForm" novalidate enctype="multipart/form-data" role="form">
    <div class="modal-body">
        <div ng-if="errorlist" class="alert alert-block alert-danger">
            <ul>
                <span ng-repeat="errors in errorlist "> <span
						ng-repeat="n in errors track by $index">
							<li>{{(n)}}</li>
					</span>
                </span>
            </ul>
        </div>
       
       
        <div class="row">

        
        
        <div class="row">
        	<div class="col-md-4 calendar" id="calendar"></div>
        	<div class="col-md-4 calendar" id="calendar2"></div>
        	<div class="col-md-4 calendar" id="calendar3"></div>
        </div>

    </div>

	<script>
	 var d = new Date();
		var month = d.getMonth() + 1;
		var year = d.getFullYear();
		var day=d.getDay();
		
		$('#calendar').pignoseCalendar({
		    week: 1,
		    disabledWeekdays: [day ],
		    disabledDates: [
		    	'2021-09-15',
		    	'2021-09-16'
		    ],
		    scheduleOptions: {
		        colors: {
		        	offer: '#2fabb7',
					ad: '#5c6270'
		        }
		    },

		    schedules: [{
		        name: 'holiday',
		        date: moment('2021-08-08')
		    }, {
		        name: 'meetup',
		        date: moment('2021-08-16')
		    }],

		});
		
		var cal2Year = (month === 12) ? (year + 1) : year;
		var cal2Month = (month === 12) ? 1 : (month + 1);
		$('#calendar2').pignoseCalendar({
			date: cal2Year + '-' + cal2Month + '-' + 1,
			initialize: false,
			week: 1,
			disabledWeekdays: [day ]
		});
		
		var cal3Year = (cal2Month === 12) ? (cal2Year + 1) : cal2Year;
		var cal3Month = (cal2Month === 12) ? 1 : (cal2Month + 1);
		$('#calendar3').pignoseCalendar({
			date: cal3Year + '-' + cal3Month + '-' + 1,
			initialize: false,
			week: 1,
			disabledWeekdays: [day ],
		    scheduleOptions: {
		        colors: {
		        	offer: '#2fabb7',
					ad: '#5c6270'
		        }
		    },

			schedules: [{
		        name: 'offer',
		        date: moment('2021-11-02')
		    }, {
		        name: 'offer',
		        date: moment('2021-11-10')
		    }],
		});
	</script>
    <!-- 	<script>
			$(document).ready(function() {
				$("#txtEditor").Editor();
			});
		</script> -->
</form>