

var chart;

$(function () {
	var data1,data2,data3,data4,data5;
	var allData,arr;
	 
	 /* $.ajax({ 
        type: "GET",
        url: "DataProvider",  
        async: false,
        success : function(data){  
        	var result = data; 
        	allData=result;
        	console.log(allData);
        } 
      });  */  
      Highcharts.setOptions({
          global: { 
              timezoneOffset: -330    
          }
      });
      
      $( "#timeFrom" ).datepicker({  
          numberOfMonths: 1,
          onClose: function( selectedDate ) {
            $( "#timeTo" ).datepicker( "option", "minDate", selectedDate );
          }
        });
        $( "#timeTo" ).datepicker({  
          numberOfMonths: 1,
          onClose: function( selectedDate ) {
            $( "#timeFrom" ).datepicker( "option", "maxDate", selectedDate );
          }
        });
        $("#hourFrom").timepicker();
        $("#hourTo").timepicker(); 
	  $("#Plot").click(function(e){
         
		  if($("#timeFrom").val()==''){
			  alert("Select from date");
			  return false;
		  }
		  if($( "#timeTo" ).val()==''){
			  alert("Select to date");
			  return false;
		  }
		  if($("#hourFrom").val()==''){
			  alert("Select from time");
			  return false;
		  }
		  if($( "#hourTo" ).val()==''){
			  alert("Select to time");
			  return false;
		  }
			  
       $.ajax({type: "GET", 
               url: "DataProvider",
               async: false,
               data: { ft : $("#temp").val(), ot : $("#otemp").val(),mv : $("#mv").val(),timeFrom :$("#timeFrom").val() ,timeTo :$("#timeTo").val(),hoursFrom :$("#hourFrom").val() ,hoursTo :$("#hourTo").val() ,monitor :$("#monitor").val()  },
               success:function(data){
            	   var result = data; 
               	allData=result;
               },
               error: function(xhr, status, error) {
                   alert("ERROR : NO DATA FOUND FOR INPUT");
               }
               });
        
       arr=allData.split(";");   
       chart = $('#container').highcharts();
       
       console.log("li1"+arr[0]);
       console.log("li12"+arr[1]); 
       console.log(arr[2]);
       
       chart.series[0].setData(JSON.parse(arr[0]));
       chart.series[1].setData(JSON.parse(arr[1]));
       chart.series[2].setData(JSON.parse(arr[2])); 
//       chart.setTitle(null, { text: ["<b>Customer: </b>"+$("#lt").val(),
//    	   "<b>Customer : </b>"+$("#customer").val(),
//    	   "<b>Process/Control Plan No : </b>"+$("#cpn").val(),
//    	   "<b>Loading Time : </b>"+$("#lt").val(),
//    	   "<b>Part Name : </b>"+$("#pn").val(),
//    	   "<b>Batch Date : </b>"+$("#bt").val(),
//    	   "<b>Soaking Start :  </b>"+$("#st").val(),
//    	   "<b>Part No : </b>"+$("#pno").val(),
//    	   "<b>Batch No : </b>"+$("#bno").val(),
//    	   "<b>Soaking End : </b>"+$("#se").val(),
//    	   "<b>Heat Treatment : </b>"+$("#ht").val(),
//    	   "<b>Batch Qty : </b>"+$("#bq").val(),
//    	   "<b>Unloading Time : </b>"+$("#ut").val()]});
         
	     }); 
	  
	  
	 
	 $('#container').highcharts({
                    	chart: {
                            zoomType: 'x'
                        },
                        
                         
                        title: { 
                            text: '<b>Time-Temprature-Carbon Potential</b>'
                        },   
                        tooltip: {
                            shared: true,
                            crosshairs: true
                        },
                        xAxis: {  
                            type: 'datetime',
                            gridLineWidth: 1
                             
                        },
                        yAxis: [{ // Primary yAxis
                            labels: {
                                format: '<b>{value}°C</b>',
                                style: {
                                   // color: Highcharts.getOptions().colors[5]
                                }
                            },
                            title: {
                                text: '<b>Temperature</b>',
                                style: {
                                   // color: Highcharts.getOptions().colors[5]
                                }
                            }
                        }, { // Secondary yAxis
                            title: {
                                text: '<b>CP</b>',
                                style: {
                                 //  color: Highcharts.getOptions().colors[5]
                                }
                            },
                            labels: {
                                format: '<b>{value} %</b>',
                                style: {
                                   // color: Highcharts.getOptions().colors[5]
                                }
                            },
                            opposite: true
                        }],
                        exporting: {
                            sourceWidth: 3000,
                            sourceHeight: 400, 
                        },
                        
                        plotOptions: {
                            line: {
                                dataLabels: {
                                    enabled: true
                                } 
                            }
                        },
                        series: [{
                             name: 'Furnace Temprature ', 
                             color: Highcharts.getOptions().colors[5],
                            data: []
                        }, {
                             name: 'CP',
                             yAxis: 1,
                             color: Highcharts.getOptions().colors[6],
                            data:[] 
                        },{
                             name: 'Oil Temprature',
                             color: Highcharts.getOptions().colors[7],
                            data:[] 
                        },
                        ]
                        
                       
                    }); 
	 
	 $('#save_btn').click(function() {
	        save_chart($('#container').highcharts(), 'chart');
	    });
                     
	  
                });
