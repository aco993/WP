$(document).ready(function(){
	
	$.post("http://localhost:8080/Projekat/DobaviDrzaveServlet",
	    	function(data,status){ 
	    		for(var i=0; i<data.length; i++){
	        		$("#drzava").append($('<option>', { 
	        	        value: data[i],
	        	        text : data[i] 
	        		}));
	    		}
	});
	
	
    $("#btnSignIn").click(function(){
    	var korisnickoIme=$("#korisnickoIme").val();
    	var lozinka=$("#lozinka").val();
    	var ime=$("#ime").val();
    	var prezime=$("#prezime").val();
    	var uloga=$("#uloga").val();
    	var kontaktTelefon=$("#kontaktTelefon").val();
    	var email=$("#email").val();
    	var adresa=$("#adresa").val();
    	var drzava=$("#drzava").val();
    	
    	var check_ime = /^[A-Z]{1}[a-z]{1,9}$/;
    	var check_lozinka = /^[A-Za-z0-9]{3,15}$/;
    	var check_telefon = /^[+]{1}[0-9]{8,9}$/;
    	var check_email = /^[A-Za-z0-9]+([_|.]{1}[A-Za-z0-9]+)*[@]{1}[a-z]+([.]{1}[a-z]+)+$/;
    	var check_adresa = /^[A-Z]{1}[a-z]+([ ]{1}[A-Z]{1}[a-z]+)*[ ]{1}[0-9]{1,3}$/;
    	
    	var greska="";
    	if(!check_lozinka.test(korisnickoIme))
    		greska+="Neispravno uneseno korisnicko ime! :)";	
    	if(!check_lozinka.test(lozinka))
    		greska+="Neispravno unesena lozinka! :)";
    	if(!check_ime.test(ime))
    		greska+="Neispravno uneseno ime! :)";
    	if(!check_ime.test(prezime))
    		greska+="Neispravno uneseno prezime! :)";
    	if(!check_telefon.test(kontaktTelefon))
    		greska+="Neispravno unesen kontakt telefon! :)";
    	if(!check_email.test(email))
    		greska+="Neispravno unesen email! :)";
    	if(!check_adresa.test(adresa))
    		greska+="Neispravno unesena adresa! :)";
    	
    	if(greska==""){    	
	    	$.post("http://localhost:8080/Projekat/RegistracijaServlet",
	  			  {
	  			    data:JSON.stringify({
	  			    	korisnickoIme:korisnickoIme,
	  		  			lozinka:lozinka,
	  		  			ime:ime,
	  		  			prezime:prezime,
	  		  			uloga:uloga,
	  		  			kontaktTelefon:kontaktTelefon,
	  		  			email:email,
	  		  			adresa:adresa,
	  		  			drzava:drzava
	  	            })
	
	  			  },
	 			  function(data,status){
		  			  if(data.uspesno){
		  				alert("Data: " + data.razlog + "\nStatus: " + status);
		  			  }	
		  			  else{
		  				$("#radni_prostor").empty();
		  				alert("Data: " + data.razlog + "\nStatus: " + status);
		  			  } 
		 		  }
	  		);
    	}else
    		alert(greska);
    });
    
    
});