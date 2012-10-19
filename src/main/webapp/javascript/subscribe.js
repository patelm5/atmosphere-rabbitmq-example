function subscriber(){
	
	 var socket = $.atmosphere;
     var subSocket;
     var self = this; 
     
     var obj = {
    		 
    		 subscribe : function(){ 
    			  var request = { url : document.location.toString()+'atmosphere/',
    					     transport : 'websocket' ,
    					     fallbackTransport: 'long-polling'
    			         };

    			   
    			     self.model = ko.observableArray(); 	 
    			  
    			    	
    			     request.onMessage = function (response) {
    			             detectedTransport = response.transport;
    			             if (response.status == 200) {
    			                 var data = response.responseBody;

    			                 try {
    			                     var result = $.parseJSON(data);
    			                     self.model.push(result); 
    			                     
    			                 } catch (err) {
    			                     alert("Exception: " + err)
    			                 }
    			             }
    			         };

    			         subSocket = socket.subscribe(request);
    			         ko.applyBindings(self.model);
    		 }
     }
     
     return obj; 
   
         
}