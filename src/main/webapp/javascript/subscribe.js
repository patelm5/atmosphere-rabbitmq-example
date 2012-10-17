function subscribe(url){
	
	 var socket = $.atmosphere;
     var subSocket;
     var request = { url : url,
             transport: 'websocket',
             trackMessageLength : true
         };

     var self = this; 
     self.model = ko.observableArray(); 	 
  
    	
     request.onMessage = function (response) {
             detectedTransport = response.transport;
             if (response.status == 200) {
                 var data = response.responseBody;

                 try {
                     var result = $.parseJSON(data);

                     var i = 0;
                     for (r in result) {
                        self.model.push(r); 
                     }
                 } catch (err) {
                     alert("Exception: " + err)
                 }
             }
         };

         subSocket = socket.subscribe(request);
         ko.applyBindings(self.model);
}