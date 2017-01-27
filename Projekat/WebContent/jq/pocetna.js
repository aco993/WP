$(document).ready(function(){
    $("#b1").click(function(){ 	    	
    	$.post("http://localhost:8080/Projekat/LogOutServlet",
    	function(data,status){
	    		  $("#radni_prostor").empty();
				  $("#logout").hide();
				  $("#login").show();
				  $("#signin").show();
				  $("#prodavnice").hide();
				  $("#kategorije").hide();
				  $("#dostavljaci").hide();
				  $("#proizvodi").hide();
				  $("#zalbe").hide();
				  alert("izlogovali ste se");
			  }			
    	);
    });
    
    //kad se klikne na login pravi se forma u divu
    $("#b3").click(function(){
    	$("#radni_prostor").empty();
    	$("#radni_prostor").load("jq/login.html");
    });
    
    //kad se klikne na sign in
    $("#b2").click(function(){
    	$("#radni_prostor").empty();
    	$("#radni_prostor").load("jq/registracija.html");
    });
    
    //ocitaj sve da kategorije
    $("#kategorije").click(function(){
    	PregledKategorija();    	
    });
    
    //udji u opciju dodavanja kategorija
    $('#radni_prostor').on('click', '#btnDodajKategoriju', function(){
    	var tip="dodavanje";
    	DodajKategoriju(tip);
    });
    
    //sacuvaj kategoriju
    $('#radni_prostor').on('click', '#btnAddKategoriju', function(){
    	var naziv=$("#naziv").val();
    	var opis=$("#opis").val();
    	var podkategorija=$("#selekcija").find(":selected").val();
    	
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14}){0,1}$/;
    	
    	var greska="";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	if(greska==""){
	    	$.post("http://localhost:8080/Projekat/DodajKategorijuServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	naziv:naziv,
	    		  			opis:opis,
	    		  			podkategorija:podkategorija
	    	            })
	
	    			  }
	    		);
	    	
	    	PregledKategorija();
    	}
    	else
    		alert(greska);
    	
    	
    });
    
    //obrisi kategoriju
    $("#radni_prostor").on('click','#btnObrisi',function(){    	
    	$.post("http://localhost:8080/Projekat/IzbrisiKategorijuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  }
  		);
    	
    	PregledKategorija();
    	
    	
    });
    
    //klik na dugme izmeni
    $("#radni_prostor").on('click','#btnIzmeni',function(){    	
    	$.post("http://localhost:8080/Projekat/IzmeniKategorijuServlet",
  			  {
  			    dataa:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })},
  	          function(dataa,status){
  	            	  DodajKategoriju(dataa);	    	
			  }
  		);
    });
    
    //sacuvaj izmenu
    $('#radni_prostor').on('click', '#btnReplaceKategoriju', function(){
    	var naziv=$("#naziv").val();
    	var opis=$("#opis").val();
    	var podkategorija=$("#selekcija").find(":selected").val();
    	
    	$.post("http://localhost:8080/Projekat/SacuvajIzmenjenuKategorijuServlet",
    			  {
    			    data:JSON.stringify({
    			    	naziv:naziv,
    		  			opis:opis,
    		  			podkategorija:podkategorija
    	            })

    			  }
    		);
    	PregledKategorija();    	
    });
    
    //kartica dostavljaci
    $("#dostavljaci").click(function(){
    	PregledDostavljaca();    	
    });
    
    //dodaj novog dostavljaca
    $('#radni_prostor').on('click', '#btnDodajDostavljaca', function(){
    	DodajDostavljaca("dodavanje");
    });
    
    //sacuvaj dostavljaca
    $('#radni_prostor').on('click', '#btnAddDostavljac', function(){
    	var sifra=$("#sifra").val()
    	var naziv=$("#naziv").val();
    	var opis=$("#opis").val();
    	var cena=$("#cena").val();
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	
    	var drzave=[];
    	$('#drzave:checked').each(function() {
    		   drzave.push(this.value);
    		});
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	alert("proslo");
    	if(isNaN(cena) || cena.length>4 || cena<1)
    		greska+="Neispravno unesena cena! :)";
    	alert("proslo");
    	if(drzave.length==0)
    		greska+="Neispravno unesene drzave! :)";
    	alert("proslo");
    	
    	if(greska==""){    	
	    	$.post("http://localhost:8080/Projekat/DodajDostavljacaServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	sifra:sifra,
	    			    	naziv:naziv,
	    		  			opis:opis,
	    		  			drzave:drzave,
	    		  			cena:cena
	    	            })	
	    			  }
	    		);
	    	PregledDostavljaca();
    	}
    	else
    		alert(greska);
    	
    });
    
    //obrisi dostavljaca
    $("#radni_prostor").on('click','#btnObrisiDostavljaca',function(){    	
    	$.post("http://localhost:8080/Projekat/IzbrisiDostavljacaServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  }
  		);
    	PregledDostavljaca();
    });
    
    
    $("#radni_prostor").on('click','#btnIzmeniDostavljaca',function(){  	
    	$.post("http://localhost:8080/Projekat/IzmeniDostavljacaServlet",
  			  {
  			    dataa:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })},
  	          function(dataa,status){
  	            	DodajDostavljaca(dataa);	    	
			  }
  		);
    });
    
    $('#radni_prostor').on('click', '#btnReplaceDostavljac', function(){
    	var sifra=$("#sifra").val()
    	var naziv=$("#naziv").val();
    	var opis=$("#opis").val();
    	var cena=$("#cena").val();
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	
    	var drzave=[];
    	$('#drzave:checked').each(function() {
    		   drzave.push(this.value);
    		});
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	alert("proslo");
    	if(isNaN(cena) || cena.length>4 || cena<1)
    		greska+="Neispravno unesena cena! :)";
    	alert("proslo");
    	if(drzave.length==0)
    		greska+="Neispravno unesene drzave! :)";
    	alert("proslo");
    	
    	if(greska==""){    	
	    	$.post("http://localhost:8080/Projekat/SacuvajIzmenjenogDostavljacaServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	sifra:sifra,
	    			    	naziv:naziv,
	    		  			opis:opis,
	    		  			drzave:drzave,
	    		  			cena:cena
	    	            })	
	    			  }
	    		);
	    	PregledDostavljaca();
    	}
    	else
    		alert(greska);
    	
    });
    
    
  //kartica prodavnice
    $("#prodavnice").click(function(){
    	PregledProdavnica();
    });
    
  //dodaj novu prodavnicu
    $('#radni_prostor').on('click', '#btnDodajProdavnicu', function(){
    	DodajProdavnicu("dodavanje");
    });
    
  //sacuvaj novu prodavnicu
    $('#radni_prostor').on('click', '#btnAddProdavnica', function(){
    	var sifra=$("#sifra").val()
    	var naziv=$("#naziv").val();
    	var adresa=$("#adresa").val();
    	var drzava=$("#selekcijaDrzava").find(":selected").val();
    	var telefon=$("#telefon").val();
    	var email=$("#email").val();
    	var odgovorniProdavac=$("#selekcijaProdavca").find(":selected").val();
    	alert(odgovorniProdavac);
    	alert($("#selekcijaProdavca").find(":selected").text());
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	var check_telefon = /^[+]{1}[0-9]{8,9}$/;
    	var check_email = /^[A-Za-z0-9]+([_|.]{1}[A-Za-z0-9]+)*[@]{1}[a-z]+([.]{1}[a-z]+)+$/;
    	var check_adresa = /^[A-Z]{1}[a-z]+([ ]{1}[A-Z]{1}[a-z]+)*[ ]{1}[0-9]{1,3}$/;
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	if(!check_telefon.test(telefon))
    		greska+="Neispravno unesen kontakt telefon! :)";
    	if(!check_email.test(email))
    		greska+="Neispravno unesen email! :)";
    	if(!check_adresa.test(adresa))
    		greska+="Neispravno unesena adresa! :)";
    	
    	if(greska==""){   
    	
	    	$.post("http://localhost:8080/Projekat/DodajProdavnicuServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	sifra:sifra,
	    			    	naziv:naziv,
	    			    	adresa:adresa,
	    			    	drzava:drzava,
	    			    	telefon:telefon,
	    			    	email:email,
	    			    	odgovorniProdavac:odgovorniProdavac
	    	            })
	
	    			  }
	    		);
	    	PregledProdavnica();
    	}
    	else
    		alert(greska);
    });
    
    //obrisi prodavnicu
    $("#radni_prostor").on('click','#btnObrisiProdavnicu',function(){
    	$.post("http://localhost:8080/Projekat/IzbrisiProdavnicuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })

  			  }
  		);
    	PregledProdavnica();
    });
    
    //izmeni postojecu prodavnicu
    $("#radni_prostor").on('click','#btnIzmeniProdavnicu',function(){
    	$.post("http://localhost:8080/Projekat/IzmeniProdavnicuServlet",
  			  {
  			    dataa:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })},
  	          function(dataa,status){
  	            	DodajProdavnicu(dataa);
			  }
  		);
    });
    
    //sacuvaj izmenjenu prodavnicu
    $('#radni_prostor').on('click', '#btnReplaceProdavnica', function(){
    	var sifra=$("#sifra").val()
    	var naziv=$("#naziv").val();
    	var adresa=$("#adresa").val();
    	var drzava=$("#selekcijaDrzava").find(":selected").val();
    	var telefon=$("#telefon").val();
    	var email=$("#email").val();
    	var odgovorniProdavac=$("#selekcijaProdavca").find(":selected").val();
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	var check_telefon = /^[+]{1}[0-9]{8,9}$/;
    	var check_email = /^[A-Za-z0-9]+([_|.]{1}[A-Za-z0-9]+)*[@]{1}[a-z]+([.]{1}[a-z]+)+$/;
    	var check_adresa = /^[A-Z]{1}[a-z]+([ ]{1}[A-Z]{1}[a-z]+)*[ ]{1}[0-9]{1,3}$/;
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	if(!check_telefon.test(telefon))
    		greska+="Neispravno unesen kontakt telefon! :)";
    	if(!check_email.test(email))
    		greska+="Neispravno unesen email! :)";
    	if(!check_adresa.test(adresa))
    		greska+="Neispravno unesena adresa! :)";
    	
    	if(greska==""){   
    	
	    	$.post("http://localhost:8080/Projekat/SacuvajIzmenjenuProdavnicuServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	sifra:sifra,
	    			    	naziv:naziv,
	    			    	adresa:adresa,
	    			    	drzava:drzava,
	    			    	telefon:telefon,
	    			    	email:email,
	    			    	odgovorniProdavac:odgovorniProdavac
	    	            })
	
	    			  }
	    		);
	    	PregledProdavnica();
    	}
    	else
    		alert(greska)
    	
    });
    
    
    //kartica proizvodi
    $("#proizvodi").click(function(){    	
    	$("#radni_prostor").empty();
    	$("#radni_prostor").append("<input type=\"button\" value=\"Dodaj proizvod!\" id=\"btnDodajProizvod\" />");
    	
    	
    	$("#radni_prostor").append("<table border=2 id=\"tblProizvodi\"><tr><th>Sifra</th><th>Naziv</th><th>Boja</th><th>Dimenzije</th>" +
    			"<th>Tezina</th><th>Zemlja proizvodnje</th><th>Naziv proizvodjaca</th><th>Jedinicna cena</th><th>Kategorija Proizvoda</th>" +
    			"<th>Slika</th><th>Video</th><th>Kolicina u magacinu</th><th>Prodavnica</th>" +
    			"<th>Ocena</th><th>Recenzije</th><th>Izmena</th><th>Brisanje</th></tr> </table> ");
    	
    	$.post("http://localhost:8080/Projekat/DobaviProizvodeServlet",
    	    	function(data,status){ 
    				    	for(var i = 0; i<data.length; i++){
    				    		$("#tblProizvodi").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].naziv+"</td>" +
    				    				"<td>"+data[i].boja+"</td><td>"+data[i].dimenzije+"</td><td>"+data[i].tezina+"</td>" +
    				    				"<td>"+data[i].zemljaProizvodnje+"</td><td>"+data[i].nazivProizvodjaca+"</td><td>"+data[i].jedinicnaCena+"</td>"+
    				    				"<td>"+data[i].kategorijaProizvoda+"</td>"+"<td><img width=100 height=100 border=2 align=middle src="+data[i].slika+" /></td>"+
    				    				"<td><video width=100 height=100 border=2 src="+data[i].video+" align=middle autoplay muted loop></video></td>"+
    				    				"<td>"+data[i].kolicinaUMagacinu+"</td><td>"+data[i].prodavnica+"</td><td>"+data[i].ocena+"</td>"+
    				    				"<td><button id=\"btnPregledRecenzijaProizvoda\">Pregled Recenzija Proizvoda!</button></td>"+
    				    				"<td><button id=\"btnIzmeniProizvod\">Izmeni Proizvod!</button></td><td><button id=\"btnObrisiProizvod\">Obrisi proizvod!</button></td></tr>");
    				    	}
    			}			
    	);    	
    });
    
    
    $('#radni_prostor').on('change', '#btnslika', function(){
    	var oFReader = new FileReader();
        oFReader.readAsDataURL(document.getElementById("btnslika").files[0]);

        oFReader.onload = function (oFREvent) {
            document.getElementById("slikaID").src = oFREvent.target.result;
            var slika222 = oFREvent.target.result;
        };
    });
    
    $('#radni_prostor').on('change', '#btnvideo', function(){
    	var oFReader = new FileReader();
        oFReader.readAsDataURL(document.getElementById("btnvideo").files[0]);

        oFReader.onload = function (oFREvent) {
            document.getElementById("videoID").src = oFREvent.target.result;
            var video222 = oFREvent.target.result;
        };
    });

    
    //dodaj proizvod
    $('#radni_prostor').on('click', '#btnDodajProizvod', function(){
    	$("#radni_prostor").empty();
    	$("#radni_prostor").append("<h1>DODAJ NOV PROIZVOD<h1>");

    	$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
    			"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\"></td></tr>"+
    			"<tr><td>Boja: </td><td><input type=\"color\" id=\"boja\" value=\"#ff0000\"></td></tr>"+
    			"<tr><td>Dimenzije: </td><td><input type=\"text\" id=\"dimenzije\"></select></tr>"+
    			"<tr><td>Tezina: </td><td><input type=\"text\" id=\"tezina\"></td></tr>"+
    			"<tr><td>Zemlja Proizvodnje: </td><td><select id=\"zemljaProizvodnje\"></td></tr>"+
    			"<tr><td>Naziv proizvodjaca: </td><td><input type=\"text\" id=\"nazivProizvodjaca\"></td></tr>"+
    			"<tr><td>Jedinicna cena: </td><td><input type=\"text\" id=\"jedinicnaCena\"></td></tr>"+
    			"<tr><td>Kategorija proizvoda: </td><td><select id=\"kategorijaProizvoda\"></td></tr>"+
    			
    			"<tr><td>Slika: </td><td><input type=file id=btnslika accept=\"image/*\" /><img width=\"170\" height=\"170\" border=2 align=\"middle\" id=\"slikaID\" /></td></tr>"+
    			
    			"<tr><td>Video: </td><td><input type=file id=btnvideo accept=\"video/*\" /><video width=170 height=94 border=2 align=middle id=videoID autoplay muted loop>"+
  				   "<source type=video/mp4><source type=video/ogg></video></td></tr>"+

    			"<tr><td>Kolicina u magacinu: </td><td><input type=\"text\" id=\"kolicinaUMagacinu\"></td></tr>"+
    			"<tr><td>Prodavnica: </td><td><select id=\"prodavnica\"></td></tr>"+
    			"<tr><td></td><td><input type=\"button\" id=\"btnAddProizvod\" value=\"Add Proizvod!\"></td></tr></table>");
    	
    	$.post("http://localhost:8080/Projekat/DobaviDrzaveServlet",
    	    	function(data,status){ 
		    		for(var i=0; i<data.length; i++){
		        		$("#zemljaProizvodnje").append($('<option>', { 
		        	        value: data[i],
		        	        text : data[i] 
		        		}));
		    		}
    	});
    	
    	$.post("http://localhost:8080/Projekat/DobaviKategorijeServlet",
    	    	function(data,status){ 
		    		for(var i=0; i<data.length; i++){
		        		$("#kategorijaProizvoda").append($('<option>', { 
		        	        value: data[i].naziv,
		        	        text : data[i].naziv 
		        		}));
		    		}
    	});
    	
    	$.post("http://localhost:8080/Projekat/ObicnoDobavljanjeProdavnicaServlet",
    	    	function(data,status){ 
		    		for(var i=0; i<data.length; i++){
		        		$("#prodavnica").append($('<option>', { 
		        	        value: data[i].naziv,
		        	        text : data[i].naziv 
		        		}));
		    		}
    	});
    });
    
    $('#radni_prostor').on('click', '#btnAddProizvod', function(){
    	alert("usao jebeno");
    	var sifra=$("#sifra").val();
    	var naziv=$("#naziv").val();
    	var boja=$("#boja").val();
    	var dimenzije=$("#dimenzije").val();
    	var tezina=$("#tezina").val();
    	var zemljaProizvodnje=$("#zemljaProizvodnje").find(":selected").val();
    	var nazivProizvodjaca=$("#nazivProizvodjaca").val();
    	var jedinicnaCena=$("#jedinicnaCena").val();
    	var kategorijaProizvoda=$("#kategorijaProizvoda").find(":selected").val();
    	var kolicinaUMagacinu=$("#kolicinaUMagacinu").val();
    	var prodavnica=$("#prodavnica").find(":selected").val();
    	var slika=$("#btnslika").val();
    	var video=$("#btnvideo").val();
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	if(isNaN(dimenzije) || dimenzije.length>4 || dimenzije<1)
    		greska+="Neispravno unesene dimenzije! :)";
    	if(isNaN(tezina) || tezina.length>4 || tezina<1)
    		greska+="Neispravno unesene tezina! :)";
    	if(!check_ime.test(nazivProizvodjaca))
    		greska+="Neispravno unesen naziv proizvodjaca! :)";
    	if(isNaN(jedinicnaCena) || jedinicnaCena.length>4 || jedinicnaCena<1)
    		greska+="Neispravno unesene jedinicna cena! :)";
    	if(isNaN(kolicinaUMagacinu) || kolicinaUMagacinu.length>4 || kolicinaUMagacinu<1)
    		greska+="Neispravno unesene kolicina u magacinu! :)";
    	if(slika=="" || slika==null)
    		greska+="Slika proizvoda mora biti izabrana! :)";
    	if(video=="" || video==null)
    		greska+="Video proizvoda mora biti izabrana! :)";
    	
    	if(greska==""){
		
			var formData = new FormData();
			$.each($('#btnslika')[0].files, function(i, file) {
				formData.append('slika-'+i, file);
			});
			$.each($('#btnvideo')[0].files, function(i, file) {
				formData.append('video-'+i, file);
			});
			
			$.post("http://localhost:8080/Projekat/ProizvodDobaviSifru",
					{
						data:JSON.stringify({
							naslov:sifra
						})
	  	            },function(data,status){
						
						$.ajax({ url: "http://localhost:8080/Projekat/DodajProizvodServlet", 
							  type: "POST",
							  data: formData,
							  mimeType:"multipart/form-data",
						    cache: false,
						    contentType: false,
						    processData: false,
						    success:function(datar,status){
							  alert("uspesno!");
							  alert(datar);
							  alert(datar.length);
							  var konj=JSON.parse(datar);
							  alert(konj);
							  for(var i=0; i<konj.length; i++){
								  	if(i==0)
								  		slika=konj[i];
								  	else if(i==1)
								  		video=konj[i];
					    		}
							  alert(slika);
							  alert(video);
							  $.post("http://localhost:8080/Projekat/AddProizvodServlet",
					    			  {
					    			    data:JSON.stringify({
					    			    	sifra:sifra,
					    			    	naziv:naziv,
					    			    	boja:boja,
					    			    	dimenzije:dimenzije,
					    			    	tezina:tezina,
					    			    	zemljaProizvodnje:zemljaProizvodnje,
					    			    	nazivProizvodjaca:nazivProizvodjaca,
					    			    	jedinicnaCena:jedinicnaCena,
					    			    	kategorijaProizvoda:kategorijaProizvoda,
					    			    	slika:slika,
					    			    	video:video,
					    			    	kolicinaUMagacinu:kolicinaUMagacinu,
					    			    	prodavnica:prodavnica
					    	            })
					    			  }
					    		);
							  
							  
						    },
							  error:function(datar,status){
								  alert("neuspesno!");
							  }
						})
	  	            }			
			);
    	}else
    		alert(greska);
    });
    
  //obrisi proizvod
    $("#radni_prostor").on('click','#btnObrisiProizvod',function(){
    	alert($(this).parent()
        .parent().attr("id"));
    	$(this).parent()
        .parent().remove();
    	
    	$.post("http://localhost:8080/Projekat/IzbrisiProizvodServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })

  			  }
  		);
    });
    
    //izmeni postojeci proizvod
    $("#radni_prostor").on('click','#btnIzmeniProizvod',function(){
    	alert($(this).parent()
        .parent().attr("id"));
    	
    	$.post("http://localhost:8080/Projekat/IzmeniProizvodServlet",
  			  {
  			    dataa:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })},
  	          function(dataa,status){
					  alert("Uspesno preuzet proizvod!!!"+dataa.sifra);
				    	$("#radni_prostor").empty();
				    	$("#radni_prostor").append("<h1>IZMENI POSTOJECI PROIZVOD<h1>");

				    	$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
				    			"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\"></td></tr>"+
				    			"<tr><td>Boja: </td><td><input type=\"color\" id=\"boja\" value=\"#ff0000\"></td></tr>"+
				    			"<tr><td>Dimenzije: </td><td><input type=\"text\" id=\"dimenzije\"></select></tr>"+
				    			"<tr><td>Tezina: </td><td><input type=\"text\" id=\"tezina\"></td></tr>"+
				    			"<tr><td>Zemlja Proizvodnje: </td><td><select id=\"zemljaProizvodnje\"></td></tr>"+
				    			"<tr><td>Naziv proizvodjaca: </td><td><input type=\"text\" id=\"nazivProizvodjaca\"></td></tr>"+
				    			"<tr><td>Jedinicna cena: </td><td><input type=\"text\" id=\"jedinicnaCena\"></td></tr>"+
				    			"<tr><td>Kategorija proizvoda: </td><td><select id=\"kategorijaProizvoda\"></td></tr>"+
				    			
				    			"<td>Slika: </td><td><img width=\"170\" height=\"170\" border=2 align=\"middle\" id=\"slikaID\" /></td></tr>"+
				    			"<td>Video: </td><td><video width=170 height=94 border=2 align=middle id=videoID autoplay muted loop ></video></td></tr>"+

				    			"<tr><td>Kolicina u magacinu: </td><td><input type=\"text\" id=\"kolicinaUMagacinu\"></td></tr>"+
				    			"<tr><td>Prodavnica: </td><td><select id=\"prodavnica\"></td></tr>"+
				    			"<tr><td></td><td><input type=\"button\" id=\"btnReplaceProizvod\" value=\"Replace Proizvod!\"></td></tr></table>");
				    	
				    	$.post("http://localhost:8080/Projekat/DobaviDrzaveServlet",
				    	    	function(data,status){ 
						    		for(var i=0; i<data.length; i++){
						        		$("#zemljaProizvodnje").append($('<option>', { 
						        	        value: data[i],
						        	        text : data[i] 
						        		}));
						        		if(dataa.zemljaProizvodnje==data[i]){
						        			$("#zemljaProizvodnje").val(data[i]);
						        		}
						    		}
				    	});
				    	
				    	$.post("http://localhost:8080/Projekat/DobaviKategorijeServlet",
				    	    	function(data,status){ 
						    		for(var i=0; i<data.length; i++){
						        		$("#kategorijaProizvoda").append($('<option>', { 
						        	        value: data[i].naziv,
						        	        text : data[i].naziv 
						        		}));
						        		if(dataa.kategorijaProizvoda==data[i].naziv){
						        			$("#kategorijaProizvoda").val(data[i].naziv);
						        		}
						    		}
				    	});
				    	
				    	$.post("http://localhost:8080/Projekat/ObicnoDobavljanjeProdavnicaServlet",
				    	    	function(data,status){ 
						    		for(var i=0; i<data.length; i++){
						        		$("#prodavnica").append($('<option>', { 
						        	        value: data[i].naziv,
						        	        text : data[i].naziv 
						        		}));
						        		if(dataa.prodavnica==data[i].naziv){
						        			$("#prodavnica").val(data[i].naziv)
						        		}
						    		}
				    	});
				    	
				   
				    	
	
				    	$("#sifra").val(dataa.sifra);
				    	$("#sifra").prop("readonly",true);
				    	$("#naziv").val(dataa.naziv);
				    	$("#boja").val(dataa.boja);
				    	$("#dimenzije").val(dataa.dimenzije);
				    	$("#tezina").val(dataa.tezina);
				    	$("#nazivProizvodjaca").val(dataa.nazivProizvodjaca);
				    	$("#jedinicnaCena").val(dataa.jedinicnaCena);
				    	$("#slikaID").attr("src",dataa.slika);
				    	$("#videoID").attr("src",dataa.video);
				    	$("#kolicinaUMagacinu").val(dataa.kolicinaUMagacinu);

			  }
  		);
    });
    
    $('#radni_prostor').on('click', '#btnReplaceProizvod', function(){
    	var sifra=$("#sifra").val()
    	var naziv=$("#naziv").val();
    	var boja=$("#boja").val();
    	var dimenzije=$("#dimenzije").val();
    	var tezina=$("#tezina").val();
    	var zemljaProizvodnje=$("#zemljaProizvodnje").find(":selected").val();
    	var nazivProizvodjaca=$("#nazivProizvodjaca").val();
    	var jedinicnaCena=$("#jedinicnaCena").val();
    	var kategorijaProizvoda=$("#kategorijaProizvoda").find(":selected").val();
    	var slika=$("#slikaID").attr('src');
    	var video=$("#videoID").attr('src');
    	var kolicinaUMagacinu=$("#kolicinaUMagacinu").val();
    	var prodavnica=$("#prodavnica").find(":selected").val();
    	
    	var check_sifra = /^[A-Za-z0-9]{10}$/;
    	var check_ime = /^[A-Z]{1}[a-z]{2,14}([_]{1}[A-Z]{1}[a-z]{2,14})*$/;
    	
    	var greska="";
    	if(!check_sifra.test(sifra))
    		greska+="Neispravno unesena sifra! :)";
    	if(!check_ime.test(naziv))
    		greska+="Neispravno unesen naziv! :)";
    	if(isNaN(dimenzije) || dimenzije.length>4 || dimenzije<1)
    		greska+="Neispravno unesene dimenzije! :)";
    	if(isNaN(tezina) || tezina.length>4 || tezina<1)
    		greska+="Neispravno unesene tezina! :)";
    	if(!check_ime.test(nazivProizvodjaca))
    		greska+="Neispravno unesen naziv proizvodjaca! :)";
    	if(isNaN(jedinicnaCena) || jedinicnaCena.length>4 || jedinicnaCena<1)
    		greska+="Neispravno unesene jedinicna cena! :)";
    	if(isNaN(kolicinaUMagacinu) || kolicinaUMagacinu.length>4 || kolicinaUMagacinu<1)
    		greska+="Neispravno unesene kolicina u magacinu! :)";
    	if(slika=="" || slika==null)
    		greska+="Slika proizvoda mora biti izabrana! :)";
    	if(video=="" || video==null)
    		greska+="Video proizvoda mora biti izabrana! :)";
    	
    	if(greska==""){
    	
	    	$.post("http://localhost:8080/Projekat/SacuvajIzmenjenProizvodServlet",
	    			  {
	    			    data:JSON.stringify({
	    			    	sifra:sifra,
	    			    	naziv:naziv,
	    			    	boja:boja,
	    			    	dimenzije:dimenzije,
	    			    	tezina:tezina,
	    			    	zemljaProizvodnje:zemljaProizvodnje,
	    			    	nazivProizvodjaca:nazivProizvodjaca,
	    			    	jedinicnaCena:jedinicnaCena,
	    			    	kategorijaProizvoda:kategorijaProizvoda,
	    			    	slika:slika,
	    			    	video:video,
	    			    	kolicinaUMagacinu:kolicinaUMagacinu,
	    			    	prodavnica:prodavnica
	    	            })
	
	    			  }
	    		);
    	}else
    		alert(greska);
    });
    
    $('#radni_prostor').on('click', '#btnPregledRecenzijaProizvoda', function(){
    	PregledRecenzijaProizvoda($(this).parent().parent().attr("id"));
    });
    
    $("#radni_prostor").on('click','#btnObrisiRecenziju',function(){
    	var pera=$(this).parent().parent().attr("id");
    	$.post("http://localhost:8080/Projekat/ObrisiRecenzijuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledRecenzijaProizvoda(pera);
  			  }
  		);
    	
    });
    
    
    $("#zalbe").click(function(){    	
    	PregledZalbiNaKupovine();
    });
    
    $("#radni_prostor").on('click','#btnPrihvatiZalbu',function(){
    	var pera=$(this).parent().parent().attr("id");
    	$.post("http://localhost:8080/Projekat/PrihvatiZalbuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledZalbiNaKupovine();
  			  }
  		);
    	
    });
    
    $("#radni_prostor").on('click','#btnOdbijZalbu',function(){
    	var pera=$(this).parent().parent().attr("id");
    	$.post("http://localhost:8080/Projekat/OdbijZalbuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledZalbiNaKupovine();
  			  }
  		);
    	
    });
    
});

$( window ).load(function() {
	
	$.post("http://localhost:8080/Projekat/LogOutServlet",
	    	function(data,status){
		    		  $("#radni_prostor").empty();
					  $("#logout").hide();
					  $("#login").show();
					  $("#signin").show();
					  $("#prodavnice").hide();
					  $("#kategorije").hide();
					  $("#dostavljaci").hide();
					  $("#proizvodi").hide();
					  $("#zalbe").hide();
				  }			
	    	);
});



function PregledKategorija()
{
	$("#radni_prostor").empty();
	$("#radni_prostor").append("<input type=\"button\" value=\"Dodaj kategoriju!\" id=\"btnDodajKategoriju\">");
	$("#radni_prostor").append("<table border=2 id=\"tblKategorije\"><tr><th>Naziv</th><th>Opis</th><th>Podkategorija</th><th>Izmena</th><th>Brisanje</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviKategorijeServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblKategorije").append("<tr id="+data[i].naziv+"><td>"+data[i].naziv+"</td><td>"+data[i].opis+"</td><td>"+data[i].podkategorija+"</td><td><button id=\"btnIzmeni\">Izmeni</button></td><td><button id=\"btnObrisi\">Obrisi</button></td></tr>");
				    	}
			}			
	);
}

////////////////////////ZA KATEGORIJE////////////////////////////
function DodajKategoriju(tip)
{
	$("#radni_prostor").empty();
	if(tip=="dodavanje"){
		$("#radni_prostor").append("<h1>DODAJ NOVU KATEGORIJU<h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Naziv kategorije:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td>"+
				"</tr><tr><td>Opis kategorije:</td><td><input type=\"text\" id=\"opis\" maxlength=200></td></tr>"+
				"<tr><td>Podkategorija: </td><td><select id=\"selekcija\"></select></td></tr>"+
				"<td></td><td><input type=\"button\" id=\"btnAddKategoriju\" value=\"Add kategoriju!\"></td></tr></table>");
	}
	else{
		$("#radni_prostor").append("<h1>IZMENI POSTOJECU KATEGORIJU<h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Naziv kategorije:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td>"+
				"</tr><tr><td>Opis kategorije:</td><td><input type=\"text\" id=\"opis\" maxlength=200></td></tr>"+
				"<tr><td>Podkategorija: </td><td><select id=\"selekcija\"></select></td></tr>"+
				"<td></td><td><input type=\"button\" id=\"btnReplaceKategoriju\" value=\"Replace kategoriju!\"></td></tr></table>");
	}
	
	$.post("http://localhost:8080/Projekat/DobaviKategorijeServlet",
	    	function(data,status){
	    		$("#selekcija").append($('<option>', { 
	    	        value: "Nema podkategoriju!",
	    	        text : "Nema podkategoriju!" 
	    		}));
	    		for(var i=0; i<data.length; i++){
	        		$("#selekcija").append($('<option>', { 
	        	        value: data[i].naziv,
	        	        text : data[i].naziv 
	        		}));
	        		if(tip!="dodavanje"){
		        		if(tip.podkategorija==data[i].naziv){
		        			$("#selekcija").val(data[i].naziv);
		        		}
	        		}    		
	    		}
			}			
	);
	
	if(tip!="dodavanje"){
		$("#naziv").val(tip.naziv);
    	$("#naziv").prop("readonly",true);
    	$("#opis").val(tip.opis);
	}
}

/////////////////ZA DOSTAVLJACE//////////////////////////////
function PregledDostavljaca()
{
	$("#radni_prostor").empty();
	$("#radni_prostor").append("<input type=\"button\" value=\"Dodaj dostavljaca!\" id=\"btnDodajDostavljaca\" />");
	$("#radni_prostor").append("<table border=2 id=\"tblDostavljaci\"><tr><th>Sifra</th><th>Naziv</th><th>Opis</th><th>Drzave</th><th>Cena</th><th>Izmena</th><th>Brisanje</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviDostavljaceServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblDostavljaci").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].naziv+"</td><td>"+data[i].opis+"</td><td>"+data[i].drzave+"</td><td>"+data[i].cena+"</td><td><button id=\"btnIzmeniDostavljaca\">Izmeni Dostavljaca!</button></td><td><button id=\"btnObrisiDostavljaca\">Obrisi Dostavljaca!</button></td></tr>");
				    	}
			}			
	);
}

function DodajDostavljaca(tip)
{
	$("#radni_prostor").empty();
	if(tip=="dodavanje"){
		$("#radni_prostor").append("<h1>DODAJ NOVOG DOSTAVLJACA</h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\" maxlength=10></td></tr>"+
			"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td></tr>"+
			"<tr><td>Opis: </td><td><input type=\"text\" id=\"opis\" maxlength=200></td></tr>"+
			"<tr><td>Zemlje: </td><td id=\"zadodavanje\"></td></tr>"+
			"<tr><td>Cena: </td><td><input type=\"text\" id=\"cena\" maxlength=4></td></tr>"+
			"<tr><td></td><td><input type=\"button\" id=\"btnAddDostavljac\" value=\"Add dostavljac!\"></td></tr></table>");
	}else{
		$("#radni_prostor").append("<h1>IZMENI POSTOJECEG DOSTAVLJACA</h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\" maxlength=10></td></tr>"+
			"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td></tr>"+
			"<tr><td>Opis: </td><td><input type=\"text\" id=\"opis\" maxlength=200></td></tr>"+
			"<tr><td>Zemlje: </td><td id=\"zadodavanje\"></td></tr>"+
			"<tr><td>Cena: </td><td><input type=\"text\" id=\"cena\" maxlength=4></td></tr>"+
			"<tr><td></td><td><input type=\"button\" id=\"btnReplaceDostavljac\" value=\"Replace dostavljac!\"></td></tr></table>");
		$("#sifra").val(tip.sifra);
    	$("#sifra").prop("readonly",true);
    	$("#naziv").val(tip.naziv);
    	$("#opis").val(tip.opis);
    	$("#cena").val(tip.cena);	
	}
	
	$.post("http://localhost:8080/Projekat/DobaviDrzaveServlet",
	    	function(data,status){ 
	    		for(var i=0; i<data.length; i++){
	    			$("#zadodavanje").append("<input type=\"checkbox\" name=\"drzave\" id=\"drzave\" class="+data[i]+" value="+data[i]+">"+data[i]+"<br>");
	    			if(tip!="dodavanje"){
	    				alert("proslo dodavanje");
	    				for(var j=0; j<tip.drzave.length; j++){
	    					alert("broji petlju");
			    			if(data[i]==tip.drzave[j]){
			    				alert("nasao");
			    				$("."+data[i]).attr("checked",true);
			    			}
			    		}
	    			}
	    		}});
}

/////////////////////ZA PRODAVNICE/////////////////////////////
function PregledProdavnica(){
	$("#radni_prostor").empty();
	$("#radni_prostor").append("<input type=\"button\" value=\"Dodaj prodavnicu!\" id=\"btnDodajProdavnicu\" />");
	$("#radni_prostor").append("<table border=2 id=\"tblProdavnice\"><tr><th>Sifra</th><th>Naziv</th><th>Adresa</th><th>Drzava</th>" +
			"<th>Telefon</th><th>Email</th><th>Odgovorni prodavac</th><th>Ocena</th><th>Recenzije</th><th>Proizvodi</th>"+
			"<th>Izmena</th><th id=headerBrisanjeProdavnice>Brisanje</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviProdavniceServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblProdavnice").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].naziv+"</td>" +
				    				"<td>"+data[i].adresa+"</td><td>"+data[i].drzava+"</td><td>"+data[i].telefon+"</td>" +
				    				"<td>"+data[i].email+"</td><td>"+data[i].odgovorniProdavac+"</td><td>"+data[i].ocena+"</td>"+
				    				"<td><button id=\"btnPregledRecenzija\">Pregled Recenzija!</button></td><td><button id=\"btnPregledProizvodaProdavnice\">Pregled Proizvoda Prodavnice!</button></td>"+
				    				"<td><button id=\"btnIzmeniProdavnicu\">Izmeni Prodavnicu!</button></td><td><button id=\"btnObrisiProdavnicu\">Obrisi Prodavnicu!</button></td></tr>");
				    	}
			}			
	);
	
	$.post("DobaviLogovanogKorisnikaServlet",
			function(data,status){
				if(data.uloga=="prodavac"){
					$("#btnDodajProdavnicu").hide();
					$("#headerBrisanjeProdavnice").hide();
					$('td:nth-child(11)').hide();
				}
	});
}

function DodajProdavnicu(tip){
	$("#radni_prostor").empty();
	if(tip=="dodavanje"){
		$("#radni_prostor").append("<h1>DODAJ NOVU PRODAVNICU<h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\" maxlength=10></td></tr>"+
				"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td></tr>"+
				"<tr><td>Adresa: </td><td><input type=\"text\" id=\"adresa\" maxlength=15></td></tr>"+
				"<tr><td>Drzava: </td><td><select id=\"selekcijaDrzava\"></select></tr>"+
				"<tr><td>Telefon: </td><td><input type=\"text\" id=\"telefon\" maxlength=10></td></tr>"+
				"<tr><td>Email: </td><td><input type=\"text\" id=\"email\" maxlength=20></td></tr>"+
				"<tr><td>Odgovorni prodavac: </td><td><select id=\"selekcijaProdavca\"></select></td></tr>"+
				"<tr><td></td><td><input type=\"button\" id=\"btnAddProdavnica\" value=\"Add Prodavnica!\"></td></tr></table>");
	}else{
		$("#radni_prostor").append("<h1>IZMENA POSTOJECE PRODAVNICE<h1>");
		$("#radni_prostor").append("<table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\" maxlength=10></td></tr>"+
				"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\" maxlength=15></td></tr>"+
				"<tr><td>Adresa: </td><td><input type=\"text\" id=\"adresa\" maxlength=15></td></tr>"+
				"<tr><td>Drzava: </td><td><select id=\"selekcijaDrzava\"></select></tr>"+
				"<tr><td>Telefon: </td><td><input type=\"text\" id=\"telefon\" maxlength=10></td></tr>"+
				"<tr><td>Email: </td><td><input type=\"text\" id=\"email\" maxlength=20></td></tr>"+
				"<tr><td>Odgovorni prodavac: </td><td><select id=\"selekcijaProdavca\"></select></td></tr>"+
				"<tr><td></td><td><input type=\"button\" id=\"btnReplaceProdavnica\" value=\"Replace Prodavnica!\"></td></tr></table>");
		
		$("#sifra").val(tip.sifra);
    	$("#sifra").prop("readonly",true);
    	$("#naziv").val(tip.naziv);
    	$("#naziv").prop("readonly",true);
    	$("#adresa").val(tip.adresa);
    	$("#telefon").val(tip.telefon);
    	$("#email").val(tip.email);
				
	}
	$.post("http://localhost:8080/Projekat/DobaviDrzaveServlet",
	    	function(data,status){ 
	    		for(var i=0; i<data.length; i++){
	        		$("#selekcijaDrzava").append($('<option>', { 
	        	        value: data[i],
	        	        text : data[i] 
	        		}));
	        		if(tip!="dodavanje"){
		        		if(tip.drzava==data[i]){
		        			$("#selekcijaDrzava").val(data[i]);
		        		}
	        		}
	    		}
	});
	
	$.post("http://localhost:8080/Projekat/DobaviProdavceServlet",
	    	function(data,status){ 
	    		for(var i=0; i<data.length; i++){
	        		$("#selekcijaProdavca").append($('<option>', { 
	        	        value: data[i].korisnickoIme,
	        	        text : data[i].ime 
	        		}));
	        		if(tip!="dodavanje"){
	        			if(tip.odgovorniProdavac==data[i].korisnickoIme){
		        			$("#selekcijaProdavca").val(data[i].korisnickoIme);
		        		}
	        		}
	    		}
	});	
}

/////////////////////////////ZA RECENZIJE PROIZVODA//////////////////////////////////
function PregledRecenzijaProizvoda(tip){

	$("#radni_prostor").empty();
	$("#radni_prostor").append("<table border=2 id=\"tblZaRecenzijeProizvoda\"><tr><th>Sifra</th><th>Kupac</th><th>Naziv Proizvoda</th><th>Sifra Proizvoda</th>" +
			"<th>Datum</th><th>Komentar</th><th>Ocena</th><th>Obrisi</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviRecenzijeZaProizvodServlet",
			{
			data:JSON.stringify({
				naslov:tip
			})
			},
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblZaRecenzijeProizvoda").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].kupac+"</td><td>"+data[i].nazivProizvoda+"</td>" +
				    				"<td>"+data[i].sifraProizvoda+"</td><td>"+data[i].datum+"</td><td>"+data[i].komentar+"</td>" +
				    				"<td>"+data[i].ocena+"</td><td><button id=\"btnObrisiRecenziju\">Obrisi Recenziju!</button></td></tr>");
				    	}
			}			
	);
}

//////////////////////////////ZA ZALBE NA KUPOVINU////////////////////////////////////
function PregledZalbiNaKupovine(){

	$("#radni_prostor").empty();
	$("#radni_prostor").append("<table border=2 id=\"tblZaZalbe\"><tr><th>Sifra</th><th>Sifra Kupovine</th><th>Kupac</th><th>Opis</th>" +
			"<th>Pregledaj kupovinu</th><th>Prihvati</th><th>Odbij</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviZalbeServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblZaZalbe").append("<tr id="+data[i].sifra+" class="+data[i].sifraKupovine+"><td>"+data[i].sifra+"</td>" +
				    				"<td>"+data[i].sifraKupovine+"</td><td>"+data[i].kupac+"</td><td>"+data[i].opis+"</td>" +
				    				"<td><button id=\"btnPogledajKupovinu\">Pogledaj Kupovinu!</button></td>" +
				    				"<td><button id=\"btnPrihvatiZalbu\">Prihvati Zalbu!</button></td>" +
				    				"<td><button id=\"btnOdbijZalbu\">Odbij Zalbu!</button></td></tr>");
				    	}
			}			
	);
}
