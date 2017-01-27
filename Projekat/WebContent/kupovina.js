$(document).ready(function(){
	//kad se klikne na logout
    $("#b1").click(function(){ 	    	
    	$.post("http://localhost:8080/Projekat/LogOutServlet",
    	function(data,status){
				  window.location.href="pocetna.html";
				  alert("izlogovali ste se");
			  }			
    	);
    });
    
    $("#kupovina").click(function(){    	
    	$("#radni_prostor").empty();
    	$("#radni_prostor").append("<div class=one><div><p>	PRODAVNICE:</p><ul class=klasa></ul></div> <div id=hoho><p>PRETRAGA PRODAVNICA</p><p> PO NAZIVU: </p>" +
				"<input type=text class=pretragaProdavnica id=pretragaNaziv /><p>PO DRZAVI</p><input type=text class=pretragaProdavnica id=pretragaDrzava /></div><div id=trol></div><div id=troool></div></div>" +
				"<div class=two id=keke></div>");
    	
    	$.post("http://localhost:8080/Projekat/DobaviProdavniceServlet",
    	    	function(data,status){
    				for(var i=0; i<data.length; i++){
    					$(".klasa").append("<li class=klasaR><a class=linkovi href=#"+data[i].naziv+" class="+data[i].drzava+" id="+data[i].naziv+">"+data[i].naziv+"</a></li>");
    				}
    		
    		}
    	);  	
    });
    
    $('#radni_prostor').on('keyup', '.pretragaProdavnica', function(){
    	var pretragaNaziv=$("#pretragaNaziv").val();
    	var pretragaDrzava=$("#pretragaDrzava").val();
    	
    	$.post("http://localhost:8080/Projekat/PretraziProdavniceServlet",
    			{
    				data:JSON.stringify({
    					sifra:pretragaNaziv,
    					ocenka:pretragaDrzava
    				})
    			},
    	    	function(data,status){
    				$("ul.klasa").empty();
    				for(var i=0; i<data.length; i++){
    					$("ul.klasa").append("<li class=klasaR><a href=#"+data[i].naziv+" class="+data[i].drzava+" id="+data[i].naziv+">"+data[i].naziv+"</a></li>");
    				}
    		
    		}
    	);
    });
    
    $('#radni_prostor').on('click', 'a.linkovi', function(){
    	var hjohjo=$(this).attr("id");
    	$.post("http://localhost:8080/Projekat/DobaviProizvodeZaProdavnicuServlet",
    			{
					data:JSON.stringify({
						naslov:$(this).attr("id")
					})
				},
    	    	function(data,status){ 
    					  	$("#keke").empty();
    					  	$("#keke").append("<div id=kosara class=four></div><div id="+hjohjo+" class=three><p>PRETRAGA PROIZVODA PO:</p>" +
    					  			"<p>NAZIVU:</p><input type=text  class=pretragaProizvoda id=pretragaPPN />" +
    					  			"<p id=aj>OPISU:</p><input type=text  class=pretragaProizvoda id=pretragaPPOp />" +
    					  			"<p>KATEGORIJI:</p><input type=text class=pretragaProizvoda id=pretragaPPK />" +
    					  			"<p>ZEMLJI PROIZVODNJE:</p><input type=text class=pretragaProizvoda id=pretragaPPZP />" +
    					  			"<p id=ej>OCENI:</p><input type=text class=pretragaProizvoda id=pretragaPPO />" +
    					  			"<p id=ij>CENA VECA OD:</p><input type=text class=pretragaProizvoda id=pretragaPPCV />" +
    					  			"<p id=oj>CENA MANJA OD:</p><input type=text class=pretragaProizvoda id=pretragaPPCM />" +
    					  			"</div>");
    					  	$("#pretragaPPO").hide();
    					  	//$("#pretragaPPCV").hide();
    					  	$("#pretragaPPCM").hide();
    					  	$("#pretragaPPOp").hide();
    					  	$("#aj").hide();
    					  	$("#ej").hide();
    					  	//$("#ij").hide();
    					  	$("#oj").hide();
    				    	for(var i = 0; i<data.length; i++){
    				    		$("#kosara").append(
    				    							"<div class=divovi id="+data[i].sifra+">" +
    				    							"<div><img width=150 height=150 border=2 align=middle id=\"slikaID\" src="+data[i].slika+" /></div>" +
    				    							"<div class=nazivi>"+data[i].naziv+"</div></div>");
    				    	}
    				    	
    				    	$("#trol").empty();
    				    	$("#trol").append("<p>Napisi Recenziju za prodavnicu</p><input type=text id=RZP /><button id="+hjohjo+" class=btnRecenzujP>OK</button>");
    				    	$.post("http://localhost:8080/Projekat/MogucnostOcenjivanjaProdavniceServlet",
					    			{
					    			data:JSON.stringify({
					    				naslov:hjohjo
					    			})
					    			},
					    	    	function(data,status){
					    				$("#troool").empty();
					    				if(data=="da"){
									    	$("#troool").append("<div><p>Oceni proizvod: </p><input type=text id=OCN><button id="+hjohjo+" class=btnOCN>Oceni!</button>");
					    				}
					    			}			
					    	);
    			}			
    	);
    	
    	
    });
    
    
    
    
    $('#radni_prostor').on('click', '.btnOCN', function(){
    	var ocenka=$("#OCN").val();
    	alert(ocenka);
    	var sifra=$(this).attr("id");
    	alert(sifra);
    	
    	var greska="";
    	if(ocenka==null || ocenka=="")
    		greska+="Ocena mora biti popunjena! :)";
    	if(isNaN(ocenka))
    		greska+="Ocena mora biti broj! :)";
    	if(parseInt(ocenka)<1 || parseInt(ocenka)>5)
    		greska+="Ocena mora biti izmedju 1 i 5! :)";
    	
    	if(greska==""){ 
    		
    		$.post("http://localhost:8080/Projekat/OceniProdavnicuServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
  	  			    	ocenka:ocenka
  	  	            })},
  	  	            function(data,status){
  	  	            	alert("uspesno proslo ocenivanje!");
  	  	            }
    		);
    	}else
    		alert(greska);
    });
    
    
    $('#radni_prostor').on('click', '.btnRecenzujP', function(){
    	var sifra=Math.floor((Math.random() * 900000) + 100000);
    	alert(sifra);
    	
    	var kupac='';
    	$.post("DobaviLogovanogKorisnikaServlet",
    			function(data,status){
    				kupac=data.korisnickoIme;
    				alert(kupac);
    	});
    	var nazivProdavnice=$(this).attr("id");
    	alert(nazivProdavnice);
    	
    	var d = new Date();
    	var datum = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate();
    	alert(datum);
    	
    	var komentar=$("#RZP").val();
    	alert(komentar);
    	
    	var greska="";
    	if(komentar==null || komentar=="")
    		greska+="Komentar mora biti popunjen! :)";
    	
    	if(greska==""){ 
    		
    		$.post("http://localhost:8080/Projekat/RecenzujProdavnicuServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
	  			    	kupac:kupac,
	  		  			nazivProdavnice:nazivProdavnice,    				    	  		  			
	  		  			datum:datum,
	  		  			komentar:komentar
  	  	            })},
  	  	            function(data,status){
  	  	            	alert("uspesno proslo recenzurisanje!");
  	  	            }
    		);
    	}else
    		alert(greska);
    });
    
    
    
    
    
    
    
    
    
    
    
    
    $('#radni_prostor').on('keyup', '.pretragaProizvoda', function(){
    	var pretragaPPN=$("#pretragaPPN").val();
    	var pretragaPPOp=$("#pretragaPPOp").val();
    	var pretragaPPK=$("#pretragaPPK").val();
    	var pretragaPPZP=$("#pretragaPPZP").val();
    	var pretragaPPO=$("#pretragaPPO").val();
    	var pretragaPPCV=$("#pretragaPPCV").val();
    	var pretragaPPCM=$("#pretragaPPCM").val();
    	
    	var prodavnica=$(".three").attr('id');
    	
    	var greska="";
    	if(isNaN(pretragaPPO) || pretragaPPO.length>4 )
    		greska+="Ocena mora biti broj, manje duzine od 4 cifre! :)";
    	if(isNaN(pretragaPPCV) || pretragaPPCV.length>4 )
    		greska+="Cena mora biti broj, manje duzine od 4 cifre! :)";
    	if(isNaN(pretragaPPCM) || pretragaPPCM.length>4 )
    		greska+="Cena mora biti broj, manje duzine od 4 cifre! :)";
    	
    	if(greska==""){
	    	$.post("http://localhost:8080/Projekat/PretraziProizvodeZaProdavnicuServlet",
	    			{
	    				data:JSON.stringify({
	    					a:prodavnica,
	    					b:pretragaPPN,
	    					c:pretragaPPOp,
	    					d:pretragaPPK,
	    					e:pretragaPPZP,
	    					f:pretragaPPO,
	    					g:pretragaPPCV,
	    					h:pretragaPPCM
	    				})
	    			},
	    	    	function(data,status){
	    				$("#kosara").empty();
	    				for(var i = 0; i<data.length; i++){
	    					alert(data.length);
				    		$("#kosara").append(
				    							"<div class=divovi id="+data[i].sifra+">" +
				    							"<div><img width=150 height=150 border=2 align=middle id=\"slikaID\" src="+data[i].slika+" /></div>" +
				    							"<div class=nazivi>"+data[i].naziv+"</div></div>");
				    	}
	    		
	    		}
	    	);
    	}else
    		alert(greska);
    });
    
    
    
    $('#radni_prostor').on('click', '.divovi', function(){
    	alert($(this).attr("id"));
    	
    	$.post("http://localhost:8080/Projekat/IzmeniProizvodServlet",
	  			  {
	  			    dataa:JSON.stringify({
	  			    	naslov:$(this).attr("id")
	  	            })},
	  	          function(dataa,status){
						  alert("Uspesno preuzet proizvod!!!"+dataa.sifra);
					    	$("#radni_prostor").empty();
					    	
					    	$("#radni_prostor").append("<h1>PREGLED PROIZVODA</h1>");

					    	$("#radni_prostor").append("<div class=tabel><table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
					    			"<tr><td>Naziv:</td><td><input type=\"text\" id=\"naziv\"></td></tr>"+
					    			"<tr><td>Boja: </td><td><input type=\"color\" id=\"boja\" value=\"#ff0000\"></td></tr>"+
					    			"<tr><td>Dimenzije: </td><td><input type=\"text\" id=\"dimenzije\"></select></tr>"+
					    			"<tr><td>Tezina: </td><td><input type=\"text\" id=\"tezina\"></td></tr>"+
					    			"<tr><td>Zemlja Proizvodnje: </td><td><input type=text id=\"zemljaProizvodnje\"></td></tr>"+
					    			"<tr><td>Naziv proizvodjaca: </td><td><input type=\"text\" id=\"nazivProizvodjaca\"></td></tr>"+
					    			"<tr><td>Jedinicna cena: </td><td><input type=\"text\" id=\"jedinicnaCena\"></td></tr>"+
					    			"<tr><td>Kategorija proizvoda: </td><td><input type=text id=\"kategorijaProizvoda\"></td></tr>"+
					    			
					    			"<tr><td>Slika: </td><td><img width=170 height=170 border=2 align=middle src='' id=slikaID /></td></tr>"+
					    			
					    			"<tr><td>Video: </td><td><video width=170 height=94 border=2 src='' align=middle id=videoID autoplay muted loop/></td></tr>"+
					    			"<tr><td>Kolicina u magaciju: </td><td><input type=\"text\" id=\"kolicinaUMagacinu\"></td></tr>"+
					    			"<tr><td>Prodavnica: </td><td><input type=text id=\"prodavnica\"></td></tr>" +
					    			"<tr><td>Ocena: </td><td><input type=text id=\"ocena\"></td></tr><table></div>"+
					    			"<div class=tabel><table><tr><tr><th>Kupovina proizvoda: </th></tr><tr><td>Dostavljac: <select id=dostavljaci></td></tr>" +
					    			"<tr><td> Kolicina: <input type=text id=kolicina> </td></tr>" +
					    			" <tr><td><button id=btnKupi>Kupi!</button></td></tr></table></div>"+
					    			
					    			"<div class=tabel><table id=tabelaZaRecenzije><tr><th>Recenzije za proizvod: </th></tr>" +
					    			"<tr><th>Korisnik</th><th>Komentar<th></tr></table></div>"+
					    			
					    			"<div class=tabel><table><tr><th>Dodaj recenziju</th></tr>"+
					    			"<tr><td>Opis: <input type=text id=recenzija></td></tr>" +
					    			"<tr><td><button id=btnRecenzuj>Recenzuj!</button></td></tr></table></div>");
					    	
					    	$("#sifra").val(dataa.sifra);
					    	$("#sifra").prop("readonly",true);
					    	$("#naziv").val(dataa.naziv);
					    	$("#naziv").prop("readonly",true);
					    	$("#boja").val(dataa.boja);
					    	$("#boja").prop("readonly",true);
					    	$("#dimenzije").val(dataa.dimenzije);
					    	$("#dimenzije").prop("readonly",true);
					    	$("#tezina").val(dataa.tezina);
					    	$("#tezina").prop("readonly",true);
					    	$("#zemljaProizvodnje").val(dataa.zemljaProizvodnje);
					    	$("#zemljaProizvodnje").prop("readonly",true);
					    	$("#nazivProizvodjaca").val(dataa.nazivProizvodjaca);
					    	$("#nazivProizvodjaca").prop("readonly",true);
					    	$("#jedinicnaCena").val(dataa.jedinicnaCena);
					    	$("#jedinicnaCena").prop("readonly",true);
					    	$("#kategorijaProizvoda").val(dataa.kategorijaProizvoda);
					    	$("#kategorijaProizvoda").prop("readonly",true);
					    	$("#kolicinaUMagacinu").val(dataa.kolicinaUMagacinu);
					    	$("#kolicinaUMagacinu").prop("readonly",true);
					    	$("#prodavnica").val(dataa.prodavnica);
					    	$("#prodavnica").prop("readonly",true);
					    	$("#ocena").val(dataa.ocena);
					    	$("#ocena").prop("readonly",true);
					    	$("#slikaID").attr("src",dataa.slika);
					    	$("#videoID").attr("src",dataa.video);
					    	
					    	$.post("http://localhost:8080/Projekat/DobaviDostavljaceIzZemlje",
					    			{
					  			    data:JSON.stringify({
					  			    	naslov:dataa.zemljaProizvodnje
					  	            })},
					    	    	function(data,status){ 
							    		for(var i=0; i<data.length; i++){
							        		$("#dostavljaci").append($('<option>', { 
							        	        value: data[i].cena,
							        	        text : data[i].naziv 
							        		}));
							    		}
					    	});
					    	
					    	$.post("http://localhost:8080/Projekat/DobaviRecenzijeZaProizvodServlet",
					    			{
					    			data:JSON.stringify({
					    				naslov:dataa.sifra
					    			})
					    			},
					    	    	function(data,status){ 
					    				    	for(var i = 0; i<data.length; i++){
					    				    		$("#tabelaZaRecenzije").append("<tr ><td>"+data[i].kupac+"</td><td>"+data[i].komentar+"</td></tr>");
					    				    	}
					    			}			
					    	);
					    	
					    	$.post("http://localhost:8080/Projekat/DozvoljenoOcenjivanjeServlet",
					    			{
					    			data:JSON.stringify({
					    				naslov:dataa.sifra
					    			})
					    			},
					    	    	function(data,status){ 
					    				if(data=="da"){
									    	$("#radni_prostor").append("<div class=tabel><table><tr><th>Oceni proizvod: </th></tr>" +
							    			"<tr><td>Ocena: <input type=text id=ocenka></td></tr>" +
							    			"<tr><td><button id=btnOceni>Oceni!</button></td></tr></table></div>");
					    				}
					    			}			
					    	);
					    	
					    	
					    	
	  	            });
    });
    
    
    $('#radni_prostor').on('click', '#btnKupi', function(){
    	var sifra=Math.floor((Math.random() * 900000) + 100000);
    	alert(sifra);
    	
    	var kupac='';
    	$.post("DobaviLogovanogKorisnikaServlet",
    			function(data,status){
    				kupac=data.korisnickoIme;
    				alert(kupac);
    	});
    	var nazivProizvoda=$("#naziv").val();
    	alert(nazivProizvoda);
    	var sifraProizvoda=$("#sifra").val();
    	alert(sifraProizvoda);
    	
    	var d = new Date();
    	var datum = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate();
    	alert(datum);
    	
    	var dostavljac=$("#dostavljaci").find(":selected").text();
    	alert(dostavljac);
    	
    	var cenaDostavljaca=$("#dostavljaci").find(":selected").val();
    	alert(cenaDostavljaca);
    	
    	var kolicina=$("#kolicina").val();
    	alert(kolicina);
    	
    	var jedinicnaCenaProizvoda=$("#jedinicnaCena").val();
    	alert(jedinicnaCenaProizvoda);
    	
    	var kolkoima=$("#kolicinaUMagacinu").val();
    	alert("u magacinu ima "+kolkoima);
    	
    	
    	var greska="";
    	if(kolicina==null || kolicina=="")
    		greska+="Kolicina mora biti popunjena! :)";
    	if(isNaN(kolicina))
    		greska+="Kolicina mora biti broj! :)";	
    	if(parseFloat(kolicina)>parseFloat($("#kolicinaUMagacinu").val()))
    		greska+="Ne mozete naruciti vise nekog proizvoda, nego sto ima na zalihama! :)";
    	if(parseFloat(kolicina)<0)
    		greska+="Kolicina mora biti pozitivan ceo broj! :)";
    	
    	if(greska==""){ 
    		$.post("http://localhost:8080/Projekat/KupiProizvodServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
  	  			    	kupac:kupac,
  	  		  			nazivProizvoda:nazivProizvoda,
  	  		  			sifraProizvoda:sifraProizvoda,
  	  		  			datum:datum,
  	  		  			dostavljac:dostavljac,
  	  		  			cenaDostavljaca:cenaDostavljaca,
  	  		  			kolicina:kolicina,
  	  		  			jedinicnaCenaProizvoda:jedinicnaCenaProizvoda
  	  	            })},
  	  	            function(data,status){
  	  	            	windows.location.href="kupovina.html";
  	  	            	alert("uspesno proslo dodavanje!");
  	  	            }
    		);
    	}else
    		alert(greska);
    });
    
    $('#radni_prostor').on('click', '#btnOceni', function(){
    	var ocenka=$("#ocenka").val();
    	alert(ocenka);
    	var sifra=$("#sifra").val();
    	alert(sifra);
    	
    	var greska="";
    	if(ocenka==null || ocenka=="")
    		greska+="Ocena mora biti popunjena! :)";
    	if(isNaN(ocenka))
    		greska+="Ocena mora biti broj! :)";
    	if(parseInt(ocenka)<1 || parseInt(ocenka)>5)
    		greska+="Ocena mora biti izmedju 1 i 5! :)";
    	
    	if(greska==""){ 
    		
    		$.post("http://localhost:8080/Projekat/OceniProizvodServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
  	  			    	ocenka:ocenka
  	  	            })},
  	  	            function(data,status){
  	  	            	alert("uspesno proslo ocenivanje!");
  	  	            }
    		);
    	}else
    		alert(greska);
    });
    
    $('#radni_prostor').on('click', '#btnRecenzuj', function(){
    	var sifra=Math.floor((Math.random() * 900000) + 100000);
    	alert(sifra);
    	
    	var kupac='';
    	$.post("DobaviLogovanogKorisnikaServlet",
    			function(data,status){
    				kupac=data.korisnickoIme;
    				alert(kupac);
    	});
    	var nazivProizvoda=$("#naziv").val();
    	alert(nazivProizvoda);
    	var sifraProizvoda=$("#sifra").val();
    	alert(sifraProizvoda);
    	
    	var d = new Date();
    	var datum = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate();
    	alert(datum);
    	
    	var komentar=$("#recenzija").val();
    	alert(komentar);
    	
    	var greska="";
    	if(komentar==null || komentar=="")
    		greska+="Komentar mora biti popunjen! :)";
    	
    	if(greska==""){ 
    		
    		$.post("http://localhost:8080/Projekat/RecenzujProizvodServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
	  			    	kupac:kupac,
	  		  			nazivProizvoda:nazivProizvoda,
	  		  			sifraProizvoda:sifraProizvoda,
	  		  			datum:datum,
	  		  			komentar:komentar
  	  	            })},
  	  	            function(data,status){
  	  	            	alert("uspesno proslo recenzurisanje!");
  	  	            }
    		);
    	}else
    		alert(greska);
    });
    
    
    $("#istorijaKupovine").click(function(){
    	PregledIstorijeKupovine();   	
    });
    
    $("#radni_prostor").on('click','#btnUkloni',function(){    	
    	$.post("http://localhost:8080/Projekat/PonistiKupovinuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledIstorijeKupovine();
  			  }
  		);
    	
    });
    
    
    $("#mojeRecenzije").click(function(){
    	PregledRecenzija();
    });
    
    $("#radni_prostor").on('click','#btnObrisiRecenziju',function(){    	
    	$.post("http://localhost:8080/Projekat/ObrisiRecenzijuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledRecenzija();
  			  }
  		);
    	
    });
    
    $("#radni_prostor").on('click','#btnObrisiRecenzijuP',function(){    	
    	$.post("http://localhost:8080/Projekat/ObrisiRecenzijuPServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				PregledRecenzija();
  			  }
  		);
    	
    });
    
    $("#radni_prostor").on('click','#btnIzmeniRecenziju',function(){    	
    	$.post("http://localhost:8080/Projekat/IzmeniRecenzijuServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				$("#radni_prostor").empty();
		    	
		    	$("#radni_prostor").append("<h1>IZMENA RECENZIJE</h1>");

		    	$("#radni_prostor").append("<div class=tabel><table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
		    			"<tr><td>Naziv Proizvoda:</td><td><input type=\"text\" id=\"nazivProizvoda\"></td></tr>"+
		    			"<tr><td>Komentar: </td><td><input type=\"text\" id=\"komentar\" ></td></tr>"+
		    			"<tr><td><button id=btnSacuvajIzmenuRecenzije>Sacuvaj Izmenjenu Recenziju!</button></td></tr></table></div>");
		    	
		    	$("#sifra").val(data.sifra);
		    	$("#sifra").prop("readonly",true);
		    	$("#nazivProizvoda").val(data.nazivProizvoda);
		    	$("#nazivProizvoda").prop("readonly",true);
		    	$("#komentar").val(data.komentar);
  			}
  		);
    	
    });
    
    $("#radni_prostor").on('click','#btnIzmeniRecenzijuP',function(){    	
    	$.post("http://localhost:8080/Projekat/IzmeniRecenzijuPServlet",
  			  {
  			    data:JSON.stringify({
  			    	naslov:$(this).parent().parent().attr("id")
  	            })
  			  },
  			  function(data,status){
  				$("#radni_prostor").empty();
		    	
		    	$("#radni_prostor").append("<h2>IZMJENA RECENZIJE</h2>");
		    	$("#radni_prostor").append("<div class=tabel><table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
		    			"<tr><td>Naziv Prodavnice:</td><td><input type=\"text\" id=\"nazivProdavnice\"></td></tr>"+
		    			"<tr><td>Komentar: </td><td><input type=\"text\" id=\"komentar\" ></td></tr>"+
		    			"<tr><td><button id=btnSacuvajIzmenuRecenzijeP>Sacuvaj izmijenjenu recenziju</button></td></tr></table></div>");
		    	
		    	$("#sifra").val(data.sifra);
		    	$("#sifra").prop("readonly",true);
		    	$("#nazivProdavnice").val(data.nazivProdavnice);
		    	$("#nazivProdavnice").prop("readonly",true);
		    	$("#komentar").val(data.komentar);
  			  }
  		);
    	
    });
    
    $("#radni_prostor").on('click','#btnSacuvajIzmenuRecenzije',function(){
    	var sifra=$("#sifra").val();
    	var ocenka=$("#komentar").val();
    	
    	var greska="";
    	if(ocenka==null || ocenka=="")
    		greska+="Komentar mora biti popunjen! :)";
    	
    	if(greska==""){
    		$.post("http://localhost:8080/Projekat/SacuvajIzmenjenuRecenzijuServlet",
    	  			  {
    	  			    data:JSON.stringify({
    	  			    	sifra:sifra,
    	  			    	ocenka:ocenka
    	  	            })
    	  			  },
    	  			  function(data,status){
    	  				  alert("uspesno izmenjeno!");
    	  			  }
    	  			  );
    		
    	}else
    		alert(greska);
    });
    
    $("#radni_prostor").on('click','#btnSacuvajIzmenuRecenzijeP',function(){
    	var sifra=$("#sifra").val();
    	var ocenka=$("#komentar").val();
    	
    	var greska="";
    	if(ocenka==null || ocenka=="")
    		greska+="Komentar mora biti popunjen! :)";
    	
    	if(greska==""){
    		$.post("http://localhost:8080/Projekat/SacuvajIzmenjenuRecenzijuServletP",
    	  			  {
    	  			    data:JSON.stringify({
    	  			    	sifra:sifra,
    	  			    	ocenka:ocenka
    	  	            })
    	  			  },
    	  			  function(data,status){
    	  				  alert("Uspjesna izmjena recenzije!");
    	  			  }
    	  			  );
    		PregledRecenzija();
    		
    	}else
    		alert(greska);
    });
    
    $("#radni_prostor").on('click','#btnZalba',function(){
    	var d = new Date();
    	alert(d.getTime());
    	var datum = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + d.getDate();
    	alert(datum);
    	
    	var dr=null;
    	
    	$.post("http://localhost:8080/Projekat/DobaviKupovinuServlet",
    			  {
    			    data:JSON.stringify({
    			    	naslov:$(this).parent().parent().attr("id")
    	            })
    			  },
    			  function(data,status){

    				  	var sifrarnik=Math.floor((Math.random() * 900000) + 100000);
    				  	
    				  	$.post("http://localhost:8080/Projekat/ProveriZalbuServlet",
    			    			  {
    			    			    dataa:JSON.stringify({
    			    			    	sifra:data.sifra,
    			    			    	ocenka:sifrarnik
    			    	            })
    			    			  },
    			    			  function(dataa,status){
    			    				  if(dataa.opis==""){
    			    					  $("#radni_prostor").empty();
    			    				    	
    			    				    	$("#radni_prostor").append("<h1>UNOS ZALBE</h1>");

    			    				    	$("#radni_prostor").append("<div class=tabel><table border=2><tr><td>Sifra:</td><td><input type=\"text\" id=\"sifra\"></td></tr>"+
    			    				    			"<tr><td>Sifra Kupovine:</td><td><input type=\"text\" id=\"sifraKupovine\"></td></tr>"+
    			    				    			"<tr><td>Opis Zalbe: </td><td><input type=\"text\" id=\"opisZalbe\" ></td></tr>"+
    			    				    			"<tr><td><button id=btnUnesiZalbu>Unesi zalbu!</button></td></tr></table></div>");
    			    				    	
    			    				    	$("#sifra").val(dataa.sifra);
    			    				    	$("#sifra").prop("readonly",true);
    			    				    	$("#sifraKupovine").val(dataa.sifraKupovine);
    			    				    	$("#sifraKupovine").prop("readonly",true);
    			    				    	$("#opisZalbe").val(dataa.opis);
    			    				  }else
    			    					  alert("vec ste ulozili zalbu");
    			    				  
    			    			  }); 	
    			  }
    		);
    	
    });
    
    $("#radni_prostor").on('click','#btnUnesiZalbu',function(){
    	var sifra=$("#sifra").val();
    	alert(sifra);
    	
    	var kupac='';
    	$.post("DobaviLogovanogKorisnikaServlet",
    			function(data,status){
    				kupac=data.korisnickoIme;
    				alert(kupac);
    	});
    	var sifraKupovine=$("#sifraKupovine").val();
    	alert(sifraKupovine);
    	var opis=$("#opisZalbe").val();
    	alert(opis);
    	
    	
    	var greska="";
    	if(opis==null || opis=="")
    		greska+="Opis zalbe mora biti popunjen! :)";
    	
    	if(greska==""){ 
    		
    		$.post("http://localhost:8080/Projekat/UloziZalbuServlet",
  	  			  {
  	  			    data:JSON.stringify({
  	  			    	sifra:sifra,
  	  			    	sifraKupovine:sifraKupovine,
	  			    	kupac:kupac,
	  		  			opis:opis
  	  	            })},
  	  	            function(data,status){
  	  	            	alert("uspesno proslo zaljenje!");
  	  	            }
    		);
    		PregledIstorijeKupovine();
    	}else
    		alert(greska); 	
    });
    
    
});
					    		
    

$( window ).load(function() {
	$.post("http://localhost:8080/Projekat/ProveraUlogovanostiServlet",
	    	function(data,status){
				if(data==false)
					window.location.href="pocetna.html";
					
		});
});

function PregledIstorijeKupovine(){
	$("#radni_prostor").empty();
	$("#radni_prostor").append("<table border=2 id=\"tblIstorijaKupovine\"><tr><th>Sifra</th><th>Kupac</th><th>Naziv proizvoda</th><th>Sifra proizvoda</th><th>Datum kupovine</th>" +
			"<th>Dostavljac</th><th>Cena dostavljaca</th><th>Kolicina</th><th>Jedinicna cena proizvoda</th><th id=headerUklonjanja>Ukloni iz istorije kupovine</th>"+
			"<th>Zalba</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviIstorijuKupovineServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblIstorijaKupovine").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].kupac+"</td><td>"+data[i].nazivProizvoda+"</td>" +
				    				"<td>"+data[i].sifraProizvoda+"</td><td>"+data[i].datum+"</td><td>"+data[i].dostavljac+"</td>" +
				    				"<td>"+data[i].cenaDostavljaca+"</td><td>"+data[i].kolicina+"</td><td>"+data[i].jedinicnaCenaProizvoda+"</td>"+
				    				"<td><button id=\"btnUkloni\">Ukloni!</button></td><td><button id=\"btnZalba\">Zali se!</button></td></tr>");
				    	}
			}			
	);
}

function PregledRecenzija(){
	$("#radni_prostor").empty();
	$("#radni_prostor").append("<h2>Recenzije proizvoda</h2><table border=2 id=\"tblRecenzije\"><tr><th>Sifra</th><th>Kupac</th><th>Naziv Proizvoda</th><th>Sifra Proizvoda</th>" +
			"<th>Datum</th><th>Komentar</th><th>Ocena</th><th>Izmeni</th><th>Obrisi</th></tr> </table> ");
	
	$("#radni_prostor").append("<h2>Recenzije prodavnica</h2><table border=2 id=\"tblRecenzijeProd\"><tr><th>Sifra</th><th>Kupac</th><th>Naziv Prodavnice</th>" +
	"<th>Datum</th><th>Komentar</th><th>Ocena</th><th>Izmeni</th><th>Obrisi</th></tr> </table> ");
	
	$.post("http://localhost:8080/Projekat/DobaviRecenzijeServlet",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblRecenzije").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].kupac+"</td><td>"+data[i].nazivProizvoda+"</td>" +
				    				"<td>"+data[i].sifraProizvoda+"</td><td>"+data[i].datum+"</td><td>"+data[i].komentar+"</td>" +
				    				"<td>"+data[i].ocena+"</td><td><button id=\"btnIzmeniRecenziju\">Izmeni Recenziju!</button></td>" +
				    						"<td><button id=\"btnObrisiRecenziju\">Obrisi Recenziju!</button></td></tr>");
				    	}
			}			
	);
	$.post("http://localhost:8080/Projekat/DobaviRecenzijeProdavnica",
	    	function(data,status){ 
				    	for(var i = 0; i<data.length; i++){
				    		$("#tblRecenzijeProd").append("<tr id="+data[i].sifra+"><td>"+data[i].sifra+"</td><td>"+data[i].kupac+"</td><td>"+data[i].nazivProdavnice+"</td>" +
				    				"<td>"+data[i].datum+"</td><td>"+data[i].komentar+"</td>" +
				    				"<td>"+data[i].ocena+"</td><td><button id=\"btnIzmeniRecenzijuP\">Izmeni Recenziju!</button></td>" +
				    						"<td><button id=\"btnObrisiRecenzijuP\">Obrisi Recenziju!</button></td></tr>");
				    	}
			}			
	);
}