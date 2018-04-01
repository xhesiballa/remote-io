module.exports = {
	messageBuilder: function(){
		this.target;
		this.action;
		this.parameters = [];
		this.setTarget = function(target){
			this.target = target;
			return this;
		}

		this.setAction = function(action){
			this.action = action;
			return this;
		}

		this.addParameter = function(parameter){
			this.parameters.push(parameter);
			return this;
		}	
		
		this.build = function(){
			var message = this.target + ':' + this.action + ':';
			for(var i=0; i<this.parameters.length; i++){
				message += this.parameters[i] + ':';
			}
			return message.substring(0, message.length-1);
		}
	}
}