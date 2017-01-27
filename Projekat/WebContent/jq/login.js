$(document).ready(function(){	
	
    $("#btnLogIn").click(function(){
    	var korisnickoIme=$("#korisnickoIme").val();
    	var lozinka=$("#lozinka").val();

    	var check_lozinka = /^[A-Za-z0-9]{3,15}$/;

    	var greska="";
    	if(!check_lozinka.test(korisnickoIme))
    		greska+="Neispravno uneseno korisnicko ime! :)";	
    	if(!check_lozinka.test(lozinka))
    		greska+="Neispravno unesena lozinka! :)";

    	if(greska==""){    	
    		$.post("http://localhost:8080/Projekat/LogInServlet",
    	  			  {
    	  			    data:JSON.stringify({
    	  			    	korisnickoIme:korisnickoIme,
    	  		  			lozinka:lozinka,
    	  	            })

    	  			  },
    	 			  function(data,status){
    		  			  if(data.uspesno){
    		  				  if(data.korisnik.uloga=="kupac"){
    		  					  window.location.href = 'kupovina.html';
    		  				  }
    		  				  else if(data.korisnik.uloga=="administrator"){
    		  					  window.location.href ='administracija.html';
    		  				  }
    		  				  else if(data.korisnik.uloga=="prodavac"){
    		  					  window.location.href='administracija.html';
    		  				  }
    		  				  alert("Dobro dosli na sajt: "+data.korisnik.ime);
    		  			  }	
    		  			  else{
    		  				  alert("Uneli ste ili nepostojece korisnicko ime, ili vam se lozinka ne slaze sa korisnickim imenom");
    		  			  } 
    		 		  }
    	  		);
    	}else
    		alert(greska);
    });
    
    
});