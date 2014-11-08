<div id="wrapper">
    <header id="header">
        <h1><a href="#">QQO / Administration</a></h1>
    </header>
    <?php include("inc/menu.php"); 
     
        //Fonction de soumission de nouvelle fiche
    if($_POST['submit-new_fiche_ENT']=='ok'){
        include ('inc/verif_regex.php');
  
 
//Début des vérifications de sécurité...
if($regex_tel&&$regex_fax&&$regex_email&&$regex_siteweb&&$regex_description&&$regex_libelle&&$regex_adresse&&$regex_ville&&$regex_cp){
         
  $uploadpath = 'Photo/';      // directory to store the uploaded files
$max_size = 2000;          // maximum file size, in KiloBytes
$alwidth = 900;            // maximum allowed width, in pixels
$alheight = 1000;           // maximum allowed height, in pixels
$allowtype = array('bmp', 'gif', 'jpg', 'jpe', 'png');        // allowed extensions
 
if(isset($_FILES['avatar']) && strlen($_FILES['avatar']['name']) > 1) {
 
        // gets the file name
  $sepext = explode('.', strtolower($_FILES['avatar']['name']));
  $libelle = $sepext[0] .'.'.$sepext[1];
  $uploadpath = $uploadpath .$libelle; 
  $type = end($sepext);       // gets extension
  list($width, $height) = getimagesize($_FILES['avatar']['tmp_name']);     // gets image width and height
  $err = '';         // to store the errors
 
  // Checks if the file has allowed type, size, width and height (for images)
  if(!in_array($type, $allowtype)) $err .= 'The file: <b>'. $_FILES['avatar']['name']. '</b> not has the allowed extension type.'; echo($err);
  if($_FILES['avatar']['size'] > $max_size*1000) $err .= '<br/>Maximum file size must be: '. $max_size. ' KB.'; echo($err);
  if(isset($width) && isset($height) && ($width >= $alwidth || $height >= $alheight)) $err .= '<br/>The maximum Width x Height must be: '. $alwidth. ' x '. $alheight;
 
 echo($err);
  // If no errors, upload the image, else, output the errors
  if($err == '') {
    if(move_uploaded_file($_FILES['avatar']['tmp_name'], $uploadpath)) { 
    }
 
  }
}
         
            insertEntreprise($_POST['libelle'], $_POST['tel'], $_POST['fax'], $_POST['email'], $_POST['siteweb'], $_POST['description'], $libelle, $_POST['adresse'], $_POST['ville'], $_POST['codepostal'], $_POST['plageHoraire']);
        }
        //ou bien on affiche les regex qui ne sont pas valide
        else echo("<div class='overlay' style='display:block'><div class='popin'><p>".$regex)."</p><a href='#' class='no'>OK</a></div></div>";
    }
     
     
    //Fonction de modification de fiche existante, avec verification regex
    if($_POST['submit-edit_fiche_ENT']=='ok'){
        include ('inc/verif_regex.php');
        //Si toute les regex sont vrais, alors on peu executer la fonction UPDATE
        if($regex_id&&$regex_tel&&$regex_fax&&$regex_email&&$regex_siteweb&&$regex_description&&$regex_libelle&&$regex_adresse&&$regex_ville&&$regex_cp)
        {
          $uploadpath = 'Photo/';      // directory to store the uploaded files
$max_size = 2000;          // maximum file size, in KiloBytes
$alwidth = 900;            // maximum allowed width, in pixels
$alheight = 800;           // maximum allowed height, in pixels
$allowtype = array('bmp', 'gif', 'jpg', 'jpe', 'png');        // allowed extensions
 
if(isset($_FILES['avatar']) && strlen($_FILES['avatar']['name']) > 1) {
 
        // gets the file name
  $sepext = explode('.', strtolower($_FILES['avatar']['name']));
  $libelle = $sepext[0] .'.'.$sepext[1];
  $uploadpath = $uploadpath .$libelle; 
  $type = end($sepext);       // gets extension
  list($width, $height) = getimagesize($_FILES['avatar']['tmp_name']);     // gets image width and height
  $err = '';         // to store the errors
 
  // Checks if the file has allowed type, size, width and height (for images)
  if(!in_array($type, $allowtype)) $err .= 'The file: <b>'. $_FILES['avatar']['name']. '</b> not has the allowed extension type.';
  if($_FILES['avatar']['size'] > $max_size*1000) $err .= '<br/>Maximum file size must be: '. $max_size. ' KB.';
  if(isset($width) && isset($height) && ($width >= $alwidth || $height >= $alheight)) $err .= '<br/>The maximum Width x Height must be: '. $alwidth. ' x '. $alheight;
 
  // If no errors, upload the image, else, output the errors
  if($err == '') {
    if(move_uploaded_file($_FILES['avatar']['tmp_name'], $uploadpath)) { 
 
    }
 
  }
}
            updateEntreprise($_POST['id'], $_POST['tel'], $_POST['fax'], $_POST['email'], $_POST['siteweb'], $_POST['description'], $libelle, $_POST['libelle'], $_POST['adresse'], $_POST['ville'], $_POST['codepostal'], $_POST['plageHoraire'] );}
            //ou bien on affiche les regex qui ne sont pas valide
                else {
                    if ($_POST['submit-edit_fiche_ENT']=='ok')
                    echo("<div class='overlay' style='display:block'><div class='popin'><p>".$regex)."</p><a href='#' class='no'>OK</a></div></div>";}
    }
 
    if(isset($_GET['id_supp'])){
        deleteEntreprise($_GET['id_supp']);
        echo '<script type="text/javascript">setTimeout("window.location=\'http://gotenigatien.fr/appa/index.php\'",500);</script>';
    }
             
    ?>
 
    <section id="gestion">
        <header class="clearfix">
            <h2>Gestion des Bars</h2>
            <a href="#" class="addnew">Nouveau</a>
            <a href="#" class="close_add">X</a>
        </header>
        <form id="new" class="clearfix" action="/appa/index.php?page=gestion_entreprises" method="post" enctype="multipart/form-data" >
            <input type="text" name="libelle" class="text required" placeholder="Libellé de l'entreprise" size="35" maxlength="35">
            <input type="text" name="libelle" class="text required" placeholder="Type" size="35" maxlength="35">
            <input type="text" name="tel" class="text mid required" placeholder="Numéro de téléphone" size="10" maxlength="10">
            <input type="text" name="fax" class="text mid nomarg" placeholder="Numéro de fax" size="10" maxlength="10">
            <input type="text" name="adresse" class="text required" placeholder="Adresse" size="150" maxlength="150">
            <input type="text" name="codepostal" class="text mid required" placeholder="Code postal"  size="5" maxlength="5">
            <input type="text" name="ville" class="text mid nomarg required" placeholder="Ville" size="25" maxlength="25">
            <input type="text" name="email" class="text mid required" placeholder="Email" size="100" maxlength="100">
            <input type="text" name="siteweb" class="text mid nomarg" placeholder="Site web" size="255" maxlength="255">
            <textarea name="description" placeholder="Description" maxlength="1500"></textarea>
            <input type="text" name="plageHoraire" class="text" placeholder="Horaires" size="50" maxlength="50">
            <input type="file" name="avatar" id="file" />
            <input type="hidden" name="submit-new_fiche_ENT" value="ok">
            <input type="submit" value="Enregistrer">
        </form>
        <form id="edit_fiche" class="clearfix" action="/appa/index.php?page=gestion_entreprises" method="post" enctype="multipart/form-data" >
            <input type="hidden" name="id">
            <input type="text" name="libelle" class="text" placeholder="Libellé de l'entreprise" size="35" maxlength="35">
            <input type="text" name="libelle" class="text" placeholder="Type" size="35" maxlength="35">
            <input type="text" name="tel" class="text mid" placeholder="Numéro de téléphone" size="10" maxlength="10">
            <input type="text" name="fax" class="text mid nomarg" placeholder="Numéro de fax" size="10" maxlength="10">
            <input type="text" name="adresse" class="text" placeholder="Adresse" size="150" maxlength="150">
            <input type="text" name="codepostal" class="text mid" placeholder="Code postal" size="5" maxlength="5">
            <input type="text" name="ville" class="text mid nomarg" placeholder="Ville" size="25" maxlength="25">
            <input type="text" name="email" class="text mid" placeholder="Email" size="100" maxlength="100">
            <input type="text" name="siteweb" class="text mid nomarg" placeholder="Site web" size="255" maxlength="255">
            <textarea name="description" placeholder="Description" maxlength="1500"></textarea>
            <input type="text" name="plageHoraire" class="text" placeholder="Horaires" size="50" maxlength="50">
            <input type="file" name="avatar" id="filee" />
            <input type="hidden" name="submit-edit_fiche_ENT" value="ok">
            <input type="submit" value="Enregistrer">
            <a href="#" class="close_edit">Annuler</a>
        </form>
        <a href="#" class="search">Recherche avancée</a>
        <a href="#" class="close_search">X</a>
        <form id="search" class="clearfix" action="/appa/index.php?page=gestion_entreprises" method="post">
            <label for="what">Vous recherchez quoi ?</label>
            <input type="text" name="who" class="text" placeholder="Par exemple : ARAMA...">
            <label for="where" class="where">Vous recherchez où ?</label>
            <input type="text" name="where" class="text" placeholder="Par exemple : Paris...">
            <input type="hidden" name="search_fiche_ENT" value="ok">
            <input type="submit" value="Rechercher">
        </form>
        <table id="list" class="tablesorter" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>Libellé</th>
                    <th>Type</th>
                    <th>Adresse</th>
                    <th>CP</th>
                    <th>Ville</th>
                    <th>Téléphone</th>
                    <th>Fax</th>
                    <th>Mail</th>
                    <th>Site web</th>
                    <th id="hidden">Horaires</th>
                    <th id="hidden">Description</th>
                    <th id="hidden">Fichier</th>
                    <th id="hidden">ID</th>
                    <th class="pt">Modifier</th>
                    <th class="pt">Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <?php 
                if(isset($_POST['search_fiche_ENT'])) getBpListOfEntrepriseByParam($_POST['who'],$_POST['where']);
                else getBpListOfEntreprise(); ?>
            </tbody>
        </table>
        <div class="pagination">
            <?php 
            $nbPage = ceil(mysql_num_rows(mysql_query("SELECT id_pers FROM ann_personne WHERE nom_pers like '' AND prenom_pers like ''"))/10);
            if($nbPage>1){ ?>
            <ul class="clearfix">
                <?php 
                for($i=1; $i<=$nbPage ; $i++){
                    if(!isset($_GET['nb_page']))$_GET['nb_page']=1;
                    if($_GET['nb_page']==$i){
                        $echo="class='active'";
                    }
                    else $echo="class='pasactivedutout'";
                    echo "<li><a href='/appa/index.php?page=gestion_entreprises&nb_page=".$i."' ".$echo.">".$i."</a></li>";
                } ?>
            </ul> <?php } ?>
        </div>
    </section>
</div>