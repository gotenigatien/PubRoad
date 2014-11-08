<?php

		if(isset($_POST['id'])){
			if(preg_match("#^[0-9]{1,4}$#", $_POST["id"]))$regex_id=true;
			else $regex.="Format de l'ID incorect : <b>".$_POST["id"]."</b><br>";}
		
		if(isset($_POST['nom'])){
			if(preg_match("#^[a-zA-Z- ]{2,30}$#", $_POST["nom"]))$regex_nom=true; 
			else $regex.="Le format du nom (<i>".$_POST["nom"]."</i>) est incorrect !<br>";}
		
		if(isset($_POST['prenom'])){
			if(preg_match("#^[a-zA-Z- ]{2,30}$#", $_POST["prenom"]))$regex_prenom=true;
			else $regex.="Le format du prénom (<i>".$_POST["prenom"]."</i> est incorrect !<br>";}
			
		if(isset($_POST['libelle'])){
			if(preg_match("#^[a-zA-Z0-9- ]{2,30}$#", $_POST["libelle"]))$regex_libelle=true;
			else $regex.="Le nom de l'entreprise (<i>".$_POST["libelle"]."</i>) est incorrect !<br>";}
			
		if(isset($_POST['tel'])){
			if (preg_match("#^0[0-9]{9}$#", $_POST["tel"]))$regex_tel=true;
			else $regex.="Le numéro de téléphone est inexact !<br>";}
			
		if(isset($_POST['fax'])){
			if (preg_match("#^0[0-9]{9}$#", $_POST["fax"])||$_POST["fax"]=='')$regex_fax=true;
			else $regex.="Le numéro de fax est inexact !<br>";}
			
		if(isset($_POST['email'])){
			if(preg_match("#^[a-z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$#", $_POST["email"]))$regex_email=true;
			else $regex.="L'email <i>".$_POST["email"]."</i> ne fonctionne pas.<br>";}
			
		if(isset($_POST['siteweb'])){
			if(preg_match("((www.)(([a-zA-Z0-9-]){2,}\.){1,4}([a-zA-Z]){2,6}(\/([a-zA-Z-_\/\.0-9#:?=&;,]*)?)?)", $_POST["siteweb"])||$_POST["siteweb"]=='')$regex_siteweb=true;
			else $regex.="Format de l'ID incorect : <b>".$_POST["siteweb"]."</b><br>";}
			
		if(isset($_POST['descript'])){
			if(preg_match("#^[a-zA-Z0-9- [:punct:]àáâãäåçèéêëìíîïðòóôõöùúûüýÿ]{0,1500}$#", $_POST["descript"])||$_POST["descript"]=='')$regex_description=true; 
			else $regex.="La description est incorect !<b><br>";}
		if(isset($_POST['description'])){
			if(preg_match("#^[a-zA-Z0-9- [:punct:]àáâãäåçèéêëìíîïðòóôõöùúûüýÿ]{0,1500}$#", $_POST["description"])||$_POST["description"]=='')$regex_description=true; 
			else $regex.="La description est incorect !<b><br>";}
			
		if(isset($_POST['photo'])){
			if(preg_match("#^[a-z0-9.-_]+@[a-z-]{2,}+.+[a-z]{2,4}$#", $_POST["photo"]))$regex_photo=true;
			else $regex.="Format de l'ID incorect : <b>".$_POST["photo"]."</b><br>";}
			
		if(isset($_POST['adresse'])){
			if(preg_match("#^[A-Za-z0-9 ,-àáâãäåçèéêëìíîïðòóôõöùúûüýÿ]{0,150}$#", $_POST["adresse"]))$regex_adresse=true;
			else $regex.="L'adresse <i>".$_POST["adresse"]."</i> n'existe pas !<br>";}
			
		if(isset($_POST['ville'])){
			if(preg_match("#^[a-zA-Z- ]{2,25}$#", $_POST["ville"]))$regex_ville=true;
			else $regex.="La ville de <i>".$_POST["ville"]."</i> ne peux pas exister !<br>";}
			
		if(isset($_POST['cp'])){
			if(preg_match("#^[0-9]{5}$#", $_POST["cp"]))$regex_cp=true;
			else $regex.="Le format du code postal (<i>".$_POST["cp"]."</i>) est incorrect !<br>";}
		if(isset($_POST['codepostal'])){
			if(preg_match("#^[0-9]{5}$#", $_POST["codepostal"]))$regex_cp=true;
			else $regex.="Le format du code postal (<i>".$_POST["codepostal"]."</i>) est incorrect !<br>";}
			
		if(isset($_POST['plageHoraire'])){
			if(preg_match("#^[A-Za-z0-9- [:punct:]à]{2,50}$#", $_POST["plageHoraire"])||$_POST["plageHoraire"]=='')$regex_plagehoraire=true;
			else $regex.="Le format des horaires (<i>".$_POST["plageHoraire"]."</i>) est incorrect !<br>";}
